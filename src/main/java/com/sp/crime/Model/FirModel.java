package com.sp.crime.Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sp.crime.Bean.FirBean;
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Utility.JDBCDataSource;

public class FirModel {

	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM firinfo");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public FirBean findByLogin(String email) throws Exception {

		FirBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM firinfo WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new FirBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFather_name(rs.getString(3));
				bean.setMother_name(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setMobileno(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setInformation(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setCrime(rs.getString(10));
				bean.setCreatedby(rs.getString(11));
				bean.setModifiedby(rs.getString(12));
				bean.setCreatedatetime(rs.getTimestamp(13));
				bean.setModifieddatetime(rs.getTimestamp(14));
				bean.setUserid(rs.getLong(15));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return bean;
	}

	public FirBean findByPk(long pk) throws Exception {

		FirBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM firinfo WHERE id=?");
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean = new FirBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFather_name(rs.getString(3));
				bean.setMother_name(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setMobileno(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setInformation(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setCrime(rs.getString(10));
				bean.setCreatedby(rs.getString(11));
				bean.setModifiedby(rs.getString(12));
				bean.setCreatedatetime(rs.getTimestamp(13));
				bean.setModifieddatetime(rs.getTimestamp(14));
				bean.setUserid(rs.getLong(15));
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bean;
	}

	public FirBean Authenticate(String Email, String Password) throws Exception {
		FirBean bean = null;
		Connection conn = null;

		conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM firinfo WHERE EMAIL =? AND PASSWORD =?");
		ps.setString(1, Email);
		ps.setString(2, Password);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bean = new FirBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setFather_name(rs.getString(3));
			bean.setMother_name(rs.getString(4));
			bean.setAddress(rs.getString(5));
			bean.setMobileno(rs.getString(6));
			bean.setDate(rs.getDate(7));
			bean.setInformation(rs.getString(8));
			bean.setGender(rs.getString(9));
			bean.setCrime(rs.getString(10));
			bean.setCreatedby(rs.getString(11));
			bean.setModifiedby(rs.getString(12));
			bean.setCreatedatetime(rs.getTimestamp(13));
			bean.setModifieddatetime(rs.getTimestamp(14));
			bean.setUserid(rs.getLong(15));
		}
		return bean;
	}

	public long add(FirBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;


		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			System.out.println("in add method");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO firinfo VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			System.out.println("in add method");
			ps.setString(2, bean.getName());
			ps.setString(3, bean.getFather_name());
			ps.setString(4, bean.getMother_name());
			ps.setString(5, bean.getAddress());
			ps.setString(6, bean.getMobileno());
			ps.setDate(7, new Date(bean.getDate().getTime()));
			ps.setString(8, bean.getInformation());
			ps.setString(9, bean.getGender());
			ps.setString(10, bean.getCrime());
			ps.setString(11, bean.getCreatedby());
			ps.setString(12, bean.getModifiedby());
			ps.setTimestamp(13, bean.getCreatedatetime());
			ps.setTimestamp(14, bean.getModifieddatetime());
			System.out.println("in add method");
			ps.setLong(15, bean.getUserid());
			ps.executeUpdate();
			System.out.println("in add method");
			conn.commit();
			ps.close();
		} catch (Exception e) {
e.printStackTrace();
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
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(

				"SELECT firinfo.id, name, firinfo.father_name,mother_name,address,mobileno,date,information,gender,crime_name FROM firinfo INNER JOIN crimeinfo ON firinfo.crime=crimeinfo.id");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			FirBean bean = new FirBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setFather_name(rs.getString(3));
			bean.setMother_name(rs.getString(4));
			bean.setAddress(rs.getString(5));
			bean.setMobileno(rs.getString(6));
			bean.setDate(rs.getDate(7));
			bean.setInformation(rs.getString(8));
			bean.setGender(rs.getString(9));
			bean.setCrime(rs.getString(10));
			list.add(bean);
		}
		return list;
	}
	
	public List ShowFirlist(long userid) throws Exception {
		System.out.println("in model list");
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(

				"SELECT firinfo.id, name, firinfo.father_name,mother_name,address,mobileno,date,information,gender,crime_name FROM firinfo INNER JOIN crimeinfo ON firinfo.crime=crimeinfo.id  where userid=?");
		ps.setLong(1, userid);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			FirBean bean = new FirBean();
			bean.setId(rs.getLong(1));
			bean.setName(rs.getString(2));
			bean.setFather_name(rs.getString(3));
			bean.setMother_name(rs.getString(4));
			bean.setAddress(rs.getString(5));
			bean.setMobileno(rs.getString(6));
			bean.setDate(rs.getDate(7));
			bean.setInformation(rs.getString(8));
			bean.setGender(rs.getString(9));
			bean.setCrime(rs.getString(10));
			list.add(bean);
		}
		return list;
	}
	

	public static long delete(long id) {
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement("DELETE from firinfo where id=?");
			stmt.setLong(1, id);
			i = stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public long Update(FirBean bean) {
		System.out.println("in model update method");
		int i = 0;
		try {
			Connection conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"update firinfo set name=?, father_name=?, mother_name=?,address=?,mobileno=?,date=?, information=?,gender=?,crime=? where id=?");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getFather_name());
			ps.setString(3, bean.getMother_name());
			ps.setString(4, bean.getAddress());
			ps.setString(5, bean.getMobileno());
			System.out.println("date" + bean.getDate());
			ps.setDate(6, new Date(bean.getDate().getTime()));
			ps.setString(7, bean.getInformation());
			System.out.println("info:" + bean.getInformation());
			ps.setString(8, bean.getGender());
			ps.setString(9, bean.getCrime());
			System.out.println("crime:" + bean.getCrime());
			ps.setString(10, bean.getCreatedby());
			ps.setString(11, bean.getModifiedby());
			ps.setTimestamp(12, bean.getCreatedatetime());
			ps.setTimestamp(13, bean.getModifieddatetime());
			System.out.println("in end update method");
			i = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public List search(FirBean bean) throws Exception {

		StringBuffer sql = new StringBuffer(

				"SELECT firinfo.id, name, firinfo.father_name,mother_name,address,mobileno,date,information,gender,crime_name"
						+ " FROM firinfo  INNER JOIN crimeinfo ON firinfo.id=crimeinfo.id WHERE 1=1");

		System.out.println("search method called:");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND name like '" + bean.getName() + "%'");

				System.out.println("First Name" + bean.getName());
			}
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FirBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setFather_name(rs.getString(3));
				bean.setMother_name(rs.getString(4));
				bean.setAddress(rs.getString(5));
				bean.setMobileno(rs.getString(6));
				bean.setDate(rs.getDate(7));
				bean.setInformation(rs.getString(8));
				bean.setGender(rs.getString(9));
				bean.setCrime(rs.getString(10));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search user");
		} finally {

		}
		return list;

	}
}
