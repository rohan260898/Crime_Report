package com.sp.crime.Ctl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.CrimnalBean;
import com.sp.crime.Bean.FirBean;
import com.sp.crime.Bean.UserBean;
import com.sp.crime.Model.CriminalModel;
import com.sp.crime.Model.FirModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.ServletUtility;

/**
 * Servlet implementation class CriminalListCtl
 */
@WebServlet(name = "CriminalListCtl", urlPatterns = "/criminallistctl")
public class CriminalListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriminalListCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void preload(HttpServletRequest request) {
		CriminalModel model = new CriminalModel();
		try {
			List list = model.list();
			request.setAttribute("crimnallist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		CrimnalBean bean = new CrimnalBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCrimnal_name(DataUtility.getString(request.getParameter("crimnal_name")));
		/*
		 * bean.setCrimnal_address(DataUtility.getString(request.getParameter(
		 * "crimnal_address")));
		 * bean.setGender(DataUtility.getString(request.getParameter("gender")));
		 * bean.setCrime_time(LocalTime.parse(request.getParameter("crime_time")));
		 * bean.setPolicestationname(DataUtility.getString(request.getParameter(
		 * "policestationname"))); Blob blob = null; Part filepart; try { filepart =
		 * request.getPart("image"); blob = medicinePacketUpload(filepart);
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } // Upload material method called
		 * bean.setImage((blob));
		 * bean.setState(DataUtility.getString(request.getParameter("state")));
		 * 
		 * System.out.println("name1:" + bean.getCrimnal_name());
		 * System.out.println("name2:" + bean.getCrimnal_address());
		 * System.out.println("name3:" + bean.getCrime_time());
		 * System.out.println("name4:" + bean.getGender()); System.out.println("name1:"
		 * + bean.getPolicestationname()); System.out.println("name2:" +
		 * bean.getImage()); System.out.println("name3:" + bean.getState());
		 * System.out.println("name3:" + bean.getId());
		 */

		populateDTO(bean, request);
		return bean;

	}

	public Blob medicinePacketUpload(Part part) throws IOException {
		System.out.println("this si part :" + part);

		InputStream inputStream = null;
		Blob blob = null;
		inputStream = part.getInputStream();
		byte[] b = new byte[inputStream.available()];
		inputStream.read(b);

		try {
			blob = new SerialBlob(b);

		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return blob;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CriminalModel model = new CriminalModel();
		CrimnalBean bean = new CrimnalBean();
		List list = null;
		HttpSession session = request.getSession(false);
		UserBean bean2 = (UserBean) session.getAttribute("user");
		long roleid = bean2.getRoleid();
		if (roleid == 2) {
			try {
				list = model.Showlist(bean2.getId());
				ServletUtility.setList(list, request);

			} catch (Exception e) {
			}
		} else {
			try {
				list = model.list();
				ServletUtility.setList(list, request);

			} catch (Exception e) {

			}
			long id = DataUtility.getLong(request.getParameter("id"));

			if (id > 0) {
				model.delete(id);
				ServletUtility.setSuccessMessage("FIR Deleted Successfully", request);
			}

		}
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { CriminalModel model = new
	 * CriminalModel(); CrimnalBean bean = null; long id =
	 * DataUtility.getLong(request.getParameter("id"));
	 * System.out.println("this is:"+ id); if (id > 0) {
	 * 
	 * model.delete(id);
	 * ServletUtility.setSuccessMessage("Criminal Record Deleted Successfully",
	 * request); }
	 * 
	 * List list = null; try { System.out.println("in do get"); list = model.list();
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * if (list == null && list.size() == 0) {
	 * ServletUtility.setErrorMessage("No record found", request); }
	 * 
	 * ServletUtility.setList(list, request); ServletUtility.forward(getView(),
	 * request, response);
	 * 
	 * }
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		CriminalModel model = new CriminalModel();
		CrimnalBean bean = new CrimnalBean();
		bean = (CrimnalBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OCRView.CRIMINAL_LIST_CTL, request, response);
			return;

		}
		if (OP_SEARCH.equalsIgnoreCase(op)) {
			try {
				list = model.search(bean);
				ServletUtility.setList(list, request);
				ServletUtility.setbean(bean, request);

			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OCRView.CRIMINAL_LIST_VIEW;
	}

}
