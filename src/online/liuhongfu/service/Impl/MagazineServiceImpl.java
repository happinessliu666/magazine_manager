package online.liuhongfu.service.Impl;

import online.liuhongfu.dao.MagazineDao;
import online.liuhongfu.entity.Magazine;
import online.liuhongfu.service.MagazineService;
import online.liuhongfu.util.ApplicationContext;
import online.liuhongfu.util.TransactionManager;

public class MagazineServiceImpl implements MagazineService {

    ApplicationContext<MagazineDao> ac = new ApplicationContext<MagazineDao>();
    @Override
    public Magazine queryISSN(Magazine magazine) {
        MagazineDao md = ac.getBean("magazineDao");
        TransactionManager.begin();
        Magazine m = null;
        try {
            m = md.selectByISSN(magazine);
            TransactionManager.closeRs();
        } catch (Exception e){
            e.printStackTrace();
            TransactionManager.closeRs();
        }
        return m;
    }
}
