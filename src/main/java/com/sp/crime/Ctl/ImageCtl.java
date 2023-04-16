package com.sp.crime.Ctl;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Utility.JDBCDataSource;

/**
 * Servlet implementation class ImageCtl
 */
@WebServlet(name = "ImageCtl",urlPatterns = "/image")
public class ImageCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
 
    public ImageCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.setContentType("image/jpeg");
		  int id = Integer.parseInt(request.getParameter("id"));
		  System.out.println("id:"+id);
		  Connection conn;
		try {
			conn = JDBCDataSource.getConnection();
			 String sql = "SELECT * FROM crimnalinfo WHERE ID ='"+id+"'";
			  PreparedStatement ps;
			  ps = conn.prepareStatement(sql);
			   ResultSet rs = ps.executeQuery();
			   if(rs.next()){
			    byte [] imageData = rs.getBytes("image"); // extract byte data from the resultset..
			    OutputStream os = response.getOutputStream(); // output with the help of outputStream 
			             os.write(imageData);
			             os.flush();
			             os.close();		             		             
			   }
			   
			   }catch (Exception e1) {
					e1.printStackTrace();
			   }
		 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected String getView() {
		return null;
	}

}
