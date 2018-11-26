package online.liuhongfu.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class JDBCUtil {
	private static Properties p = new Properties();
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	static{
		InputStream is = JDBCUtil.class.getResourceAsStream("/online/liuhongfu/conf/jdbc.properties");
		try{
			p.load(is);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		Connection conn = tl.get();
		if(conn==null){
			try {
				Class.forName(p.getProperty("drivername"));
				conn = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
				tl.set(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
		if(ps!=null){try{ps.close();}catch(Exception e){e.printStackTrace();}}
		if(conn!=null){try{conn.close();tl.remove();}catch(Exception e){e.printStackTrace();}}
	}
	public static void close(Connection conn,PreparedStatement ps){
		if(ps!=null){try{ps.close();}catch(Exception e){e.printStackTrace();}}
		if(conn!=null){try{conn.close();tl.remove();}catch(Exception e){e.printStackTrace();}}
	}
	//  String ----  java.sql.Date
	public static java.sql.Date dealSqlDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date sqlDate = null;
		try{
			java.util.Date utilDate = sdf.parse(time);
			sqlDate = new java.sql.Date(utilDate.getTime());
		}catch(Exception e){
			e.printStackTrace();
		}
		return sqlDate;
	}
	//String  ---  java.util.Date
	public static java.util.Date dealUtilDate(String time){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate = null;
		try{
			 utilDate = sdf.parse(time);
		}catch(Exception e){
			e.printStackTrace();
		}
		return utilDate;
	}
}
