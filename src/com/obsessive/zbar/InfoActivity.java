package com.obsessive.zbar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import online.liuhongfu.entity.Magazine;
import online.liuhongfu.service.MagazineService;
import online.liuhongfu.util.ApplicationContext;

public class InfoActivity extends Activity {
    private TextView magazineName;
    private TextView magazineLocation;
    private TextView magazineISSN;
    private TextView magazinePostalCode;
    private TextView notExit;

    private Button scanButton;
    private String issn;
    private Button telephone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        Intent intent = getIntent();
        issn = intent.getStringExtra("issn");

        findViewById();
        addEvent();
        addEvents();
    }


    private void findViewById() {
        magazineName = (TextView)findViewById(R.id.magazine_name);
        magazineLocation = (TextView)findViewById(R.id.magazine_location);
        magazineISSN = (TextView)findViewById(R.id.magazine_issn);
        magazinePostalCode = (TextView)findViewById(R.id.magazine_postal_code);
        notExit = (TextView) findViewById(R.id.not_exit);
        scanButton = (Button) findViewById(R.id.capture_restart_scan);
        telephone = (Button)findViewById(R.id.telephone);

    }


    /**
     * 调用拨号界面
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0){
                notExit.setText("抱歉，您所查找的杂志不存在，请核对条形码，若条码一致，请与管理员取得联系！");
                telephone.setVisibility(1);
                telephone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        call("18317276011");
                    }
                });
            }
        }

    };

    public void addEvent(){
        magazineISSN.setText(issn);

        new Thread(){
            @Override
            public void run() {
                Magazine magazine;
                Magazine m = new Magazine(issn);
                ApplicationContext<MagazineService> ac = new ApplicationContext<MagazineService>();
                MagazineService ms = ac.getBean("magazineService");
                magazine = ms.queryISSN(m);

                if (magazine != null){
                    magazineName.setText(magazine.getName());
                    magazineLocation.setText(magazine.getLocation());
                    magazinePostalCode.setText(magazine.getPostalCode());
                }else{
                    Message message = new Message();
                    message.what = 0;
                    handler.sendMessage(message);
                }
            }
        }.start();






    }

    private void addEvents() {
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, CaptureActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
