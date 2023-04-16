package com.sp.crime.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sp.crime.Bean.CrimnalBean;
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Utility.JDBCDataSource;

public class CriminalModel {

	public long nextpk() throws Exception {

		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM  crimnalinfo");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}


	public CrimnalBean findByPk(long pk) throws Exception {

		CrimnalBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM crimnalinfo WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new CrimnalBean();
				bean.setId(rs.getLong(1));
				bean.setCrimnal_name(rs.getString(2));
				bean.setCrimnal_address(rs.getString(3));
				bean.setGender(rs.getString(4));
                bean.setCrime_time(rs.getTime(5));
				bean.setPolicestationname(rs.getString(6));
				bean.setImage(rs.getBlob(7));
				bean.setState(rs.getString(8));
				bean.setCreatedby(rs.getString(9));
				bean.setModifiedby(rs.getString(10));
				bean.setCreatedatetime(rs.getTimestamp(11));
				bean.setModifieddatetime(rs.getTimestamp(12));

			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}


	public long add(CrimnalBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		long pk = 0;

	

		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO crimnalinfo VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getCrimnal_name());
			ps.setString(3, bean.getCrimnal_address());
			ps.setString(4, bean.getGender());
			ps.setObject(5, bean.getCrime_time());
			ps.setString(6, bean.getPolicestationname());
			ps.setBlob(7, bean.getImage());
			ps.setString(8, bean.getState());
			ps.setString(9, bean.getCreatedby());
			ps.setString(10, bean.getModifiedby());
			ps.setTimestamp(11, bean.getCreatedatetime());
			ps.setTimestamp(12, bean.getModifieddatetime());
			System.out.println("in add end method111111111111");
			System.out.println("id:" + bean.getId());
			ps.setLong(13, bean.getUserid());
			ps.executeUpdate();
			System.out.println("in add end method");
			conn.commit();
			ps.close();
		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception e2) {
				e.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + e.getMessage());
			}
		} finally {
			JDBCDataSource.closeconnection(conn);
		}
		return pk;
	}

	public List list() throws Exception {
		System.out.println("in model list");

		  ArrayList<CrimnalBean> list=new ArrayList<CrimnalBean>();
		  Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM crimnalinfo");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			CrimnalBean bean = new CrimnalBean();
			System.out.println("11111111111111");
			bean.setId(rs.getLong(1));
			bean.setCrimnal_name(rs.getString(2));
			bean.setCrimnal_address(rs.getString(3));
			bean.setGender(rs.getString(4));
			System.out.println("111122222222222");
			bean.setCrime_time(rs.getTime(5).toLocalTime());
			bean.setPolicestationname(rs.getString(6));
			bean.setImage(rs.getBlob(7));
			System.out.println("111333333333333");
			bean.setState(rs.getString(8));
			bean.setCreatedby(rs.getString(9));
			bean.setModifiedby(rs.getString(10));
			bean.setCreatedatetime(rs.getTimestamp(11));
			bean.setModifieddatetime(rs.getTimestamp(12));
			System.out.println("list end");

			list.add(bean);
		}
		return list;
	}
	
	public List Showlist(long userid) throws Exception {
		System.out.println("in model list");

		  ArrayList<CrimnalBean> list=new ArrayList<CrimnalBean>();
		  Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM crimnalinfo  where userid=?");
		pstmt.setLong(1, userid);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			CrimnalBean bean = new CrimnalBean();
			System.out.println("11111111111111");
			bean.setId(rs.getLong(1));
			bean.setCrimnal_name(rs.getString(2));
			bean.setCrimnal_address(rs.getString(3));
			bean.setGender(rs.getString(4));
			System.out.println("111122222222222");
			bean.setCrime_time(rs.getTime(5).toLocalTime());
			bean.setPolicestationname(rs.getString(6));
			bean.setImage(rs.getBlob(7));
			System.out.println("111333333333333");
			bean.setState(rs.getString(8));
			bean.setCreatedby(rs.getString(9));
			bean.setModifiedby(rs.getString(10));
			bean.setCreatedatetime(rs.getTimestamp(11));
			bean.setModifieddatetime(rs.getTimestamp(12));
			System.out.println("list end");

			list.add(bean);
		}
		return list;
	}
	

	public static long delete(long id) {
		System.out.println("in delete method");
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from crimnalinfo where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	/*
	 * public long Update(FirBean bean) {
	 * System.out.println("in model update method"); int i = 0; try { Connection
	 * conn = JDBCDataSource.getConnection(); PreparedStatement ps =
	 * conn.prepareStatement(
	 * "update crimnalinfo set name=?, father_name=?, mother_name=?,address=?,mobileno=?,email=?,date=?, information=?,gender=?,crime=? where id=?"
	 * ); ps.setString(1, bean.getName()); ps.setString(2, bean.getFather_name());
	 * ps.setString(3, bean.getMother_name()); ps.setString(4, bean.getAddress());
	 * ps.setString(5, bean.getMobileno()); ps.setString(6, bean.getEmail());
	 * System.out.println("date" + bean.getDate()); ps.setDate(7, new
	 * Date(bean.getDate().getTime())); ps.setString(8, bean.getInformation());
	 * System.out.println("info:" + bean.getInformation()); ps.setString(9,
	 * bean.getGender()); ps.setString(10, bean.getCrime());
	 * System.out.println("crime:" + bean.getCrime()); ps.setString(11,
	 * bean.getCreatedby()); ps.setString(12, bean.getModifiedby());
	 * ps.setTimestamp(13, bean.getCreatedatetime()); ps.setTimestamp(14,
	 * bean.getModifieddatetime()); System.out.println("in end update method");
	 * ps.setLong(15, bean.getId()); i = ps.executeUpdate();
	 * 
	 * } catch (Exception e) { e.printStackTrace();}
	 * 
	 * return i; }
	 */
	public List search(CrimnalBean bean) throws Exception {

		StringBuffer sql = new StringBuffer("SELECT * FROM crimnalinfo WHERE 1=1");

		System.out.println("search method called:"+bean.getCrimnal_name());

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCrimnal_name()!= null && bean.getCrimnal_name().length() > 0) {
				sql.append(" AND crimnal_name like '" + bean.getCrimnal_name() + "%'");
			}
				
		}
		List list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CrimnalBean();
				bean.setId(rs.getLong(1));
				bean.setCrimnal_name(rs.getString(2));
				bean.setCrimnal_address(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setCrime_time(rs.getTime(5).toLocalTime());
				bean.setPolicestationname(rs.getString(6));
				bean.setImage(rs.getBlob(7));
				bean.setState(rs.getString(8));
				bean.setCreatedby(rs.getString(9));
				bean.setModifiedby(rs.getString(10));
				bean.setCreatedatetime(rs.getTimestamp(11));
				bean.setModifieddatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {

		} finally {
			
			JDBCDataSource.closeconnection(conn);
		}
		    return list;
		}
}
	