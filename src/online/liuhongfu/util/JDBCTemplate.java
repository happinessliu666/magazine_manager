package online.liuhongfu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCTemplate<T> {
	public  void update(String sql,Object... objs){
		Connection conn = null;
		PreparedStatement  ps = null;
		try{
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			hasNextParameter(ps, objs);
			ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(null, ps);
		}
	}
	public  T selectOne(String sql,Rowmapper<T> rm,Object...obj){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		T t = null;
		try{
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			hasNextParameter(ps, obj);
			rs = ps.executeQuery();
			if(rs.next()){
				 t = rm.mapperRow(rs);
			}
			return t;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			JDBCUtil.close(null, ps,rs);
		}
	}
	public List<T> selectMuch(String sql,Rowmapper<T> rm,Object...obj){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = new ArrayList<T>();
		try{
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			hasNextParameter(ps, obj);
			rs = ps.executeQuery();
			while(rs.next()){
				T t = rm.mapperRow(rs);
				list.add(t);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
			}finally{
				JDBCUtil.close(null, ps,rs);
			}
		
	}
	private static void hasNextParameter(PreparedStatement ps, Object... objs) throws SQLException {
		if(objs.length!=0){
			for(int i=1;i<=objs.length;i++){
				ps.setObject(i, objs[i-1]);
			}
		}
	}
}
