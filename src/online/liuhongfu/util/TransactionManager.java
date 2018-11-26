package online.liuhongfu.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
	public static void begin(){
		Connection conn = JDBCUtil.getConnection();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void commit(){
		Connection conn = JDBCUtil.getConnection();
		try{conn.commit();}catch(Exception e){e.printStackTrace();}finally{JDBCUtil.close(conn, null);}
	}
	public static void rollback(){
		Connection conn = JDBCUtil.getConnection();
		try{conn.rollback();}catch(Exception e){e.printStackTrace();}finally{JDBCUtil.close(conn, null);}
	}
	public static void closeRs(){
		Connection conn = JDBCUtil.getConnection();
		JDBCUtil.close(conn,null,null);
	}
}
