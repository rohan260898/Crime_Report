package com.sp.crime.Ctl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Exception.DuplicateRecordException;
import com.sp.crime.Model.CriminalModel;
import com.sp.crime.Model.FirModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.DataValidator;
import com.sp.crime.Utility.PropertyReader;
import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "CriminalCtl", urlPatterns = "/criminalctl")
@MultipartConfig(maxFileSize = 16177215)
public class CriminalCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_SAVE = "Save";
	public static final String OP_RESET = "Reset";

	public CriminalCtl() {
		super();
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validation method");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("crimnal_name"))) {
			request.setAttribute("crimnal_name", PropertyReader.getvalue("error.require", "Criminal Name"));
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("crimnal_address"))) {
			request.setAttribute("crimnal_address", PropertyReader.getvalue("error.require", "Address"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getvalue("error.require", "Gender"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("policestationname"))) {
			request.setAttribute("policestationname", PropertyReader.getvalue("error.require", "Police Station Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", PropertyReader.getvalue("error.require", "City/State/Country"));
			pass = false;
		}
		System.out.println("pass:" + pass);
		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {
		CrimnalBean bean = new CrimnalBean();
		HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean)session.getAttribute("user");
		Long userId = existBean.getId();
		bean.setUserid(userId);
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCrimnal_name(DataUtility.getString(request.getParameter("crimnal_name")));
		bean.setCrimnal_address(DataUtility.getString(request.getParameter("crimnal_address")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setCrime_time(LocalTime.parse(request.getParameter("crime_time")));
		bean.setPolicestationname(DataUtility.getString(request.getParameter("policestationname")));
		Blob blob = null;
		Part filepart;
		try {
			filepart = request.getPart("image");
			blob = medicinePacketUpload(filepart);

		} catch (Exception e) { 
			e.printStackTrace();
		} 
		bean.setImage((blob));
		bean.setState(DataUtility.getString(request.getParameter("state")));
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
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do post");

		CriminalModel model = new CriminalModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		CrimnalBean bean = new CrimnalBean();
		bean = (CrimnalBean) populateBean(request);

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OCRView.CRIMINAL_CTL, request, response);
			return;
		}

		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (CrimnalBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("Criminal Registered Successfully", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setbean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
				ServletUtility.forward(getView(), request, response);

			} catch (ApplicationException e) {

				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);

		}
	}

	@Override
	protected String getView() {

		return OCRView.CRIMINAL_VIEW;
	}

}
