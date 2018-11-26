package online.liuhongfu.rowmapper;

import online.liuhongfu.entity.Magazine;
import online.liuhongfu.util.Rowmapper;

import java.sql.ResultSet;

public class MagazineRowmapper implements Rowmapper<Magazine> {
    @Override
    public Magazine mapperRow(ResultSet rs) {
        Magazine m = null;
        try {
                m = new Magazine();
                m.setId(rs.getInt(1));
                m.setIssn(rs.getString(2));
                m.setName(rs.getString(3));
                m.setLocation(rs.getString(4));
                m.setPostalCode(rs.getString(5));
        }catch (Exception e){
            e.printStackTrace();
        }
        return m;
    }

}
