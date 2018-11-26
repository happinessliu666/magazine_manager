package online.liuhongfu.util;

import java.sql.ResultSet;

public interface Rowmapper<T> {
	//   rs-----T
	T mapperRow(ResultSet rs);
}
