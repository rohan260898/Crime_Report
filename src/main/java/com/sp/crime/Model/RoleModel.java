package com.sp.crime.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sp.crime.Bean.RoleBean;
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Utility.JDBCDataSource;

public class RoleModel {
	
	public Integer nextpk() throws Exception {

		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT MAX(ID) FROM role");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pk + 1;
	}

	public long add(RoleBean bean) throws Exception {
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
			PreparedStatement ps = conn.prepareStatement("INSERT INTO role VALUES(?,?,?,?,?,?)");
			ps.setLong(1, pk);
			ps.setString(2, bean.getRolename());
			ps.setString(3, bean.getCreatedby());
			ps.setString(4, bean.getModifiedby());
			ps.setTimestamp(5, bean.getCreatedatetime());
			ps.setTimestamp(6, bean.getModifieddatetime());
			ps.executeUpdate();
			System.out.println("in add end");
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
	
	public RoleBean findByPK(long pk) {
		Connection conn = null;
		RoleBean bean = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM role WHERE ID=?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new RoleBean();
				bean.setId(rs.getLong(1));
				bean.setRolename(rs.getString(2));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
