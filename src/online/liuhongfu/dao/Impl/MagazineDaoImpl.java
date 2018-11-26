package online.liuhongfu.dao.Impl;

import online.liuhongfu.dao.MagazineDao;
import online.liuhongfu.entity.Magazine;
import online.liuhongfu.rowmapper.MagazineRowmapper;
import online.liuhongfu.util.JDBCTemplate;

public class MagazineDaoImpl implements MagazineDao {
    private static final String SELECT_USER_BY_ISSN = "select * from magazine where issn=?";
    JDBCTemplate<Magazine> jt = new JDBCTemplate<Magazine>();


   @Override
    public Magazine selectByISSN(Magazine magazine) {
        Magazine m = jt.selectOne(SELECT_USER_BY_ISSN,new MagazineRowmapper(),magazine.getIssn());
        System.out.println(m);
        return m;
    }
}
