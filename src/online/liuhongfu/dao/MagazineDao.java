package online.liuhongfu.dao;

import online.liuhongfu.entity.Magazine;

public interface MagazineDao {
    Magazine selectByISSN(Magazine magazine);
}
