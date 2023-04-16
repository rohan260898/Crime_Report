package com.sp.crime.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sp.crime.Bean.CrimeBean;
import com.sp.crime.Bean.FirBean;
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Utility.JDBCDataSource;

public class CrimeModel {
	
	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM crimeinfo");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public long add(CrimeBean bean) throws Exception {
		System.out.println("in add method");
		Connection conn = null;
		int pk = 0;

		/*FirBean existbean = findByLogin(bean.getEmail());
		
		 * if (existbean != null) { throw new
		 * DuplicateRecordException("Login Id already exite"); }
		 */

		try {

			conn = JDBCDataSource.getConnection();
			pk = nextpk();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO crimeinfo VALUES(?,?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getCrime_name());
			ps.setString(3, bean.getDescription());
			ps.setString(4, bean.getCreatedby());
			ps.setString(5, bean.getModifiedby());
			ps.setTimestamp(6, bean.getCreatedatetime());
			ps.setTimestamp(7, bean.getModifieddatetime());
			ps.executeUpdate();
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
		ArrayList list = new ArrayList();
		Connection conn = null;
		conn = JDBCDataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM crimeinfo");
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			CrimeBean bean = new CrimeBean();
			bean.setId(rs.getLong(1));
			bean.setCrime_name(rs.getString(2));
			bean.setDescription(rs.getString(3));
			bean.setCreatedby(rs.getString(4));
			bean.setModifiedby(rs.getString(5));
			bean.setCreatedatetime(rs.getTimestamp(6));
			bean.setModifieddatetime(rs.getTimestamp(7));			
			list.add(bean);
		}
		return list;
	}

	
}
