package com.sp.crime.Ctl;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.FirBean;
import com.sp.crime.Bean.UserBean;
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Exception.DuplicateRecordException;
import com.sp.crime.Model.FirModel;
import com.sp.crime.Model.UserModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.DataValidator;
import com.sp.crime.Utility.PropertyReader;
import com.sp.crime.Utility.ServletUtility;

/**
 * Servlet implementation class FirCtl
 */
@WebServlet(name = "FirCtl", urlPatterns = "/firctl")
public class FirCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_SAVE = "Save";
	public static final String OP_RESET = "Reset";
	public static final String OP_NEW = "New";
	public static final String OP_MYPROFILE = "MyProfile";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirCtl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validation");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getvalue("error.require", "Name"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getvalue("error.name", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("father_name"))) {
			request.setAttribute("father_name", PropertyReader.getvalue("error.require", "Father Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("father_name"))) {
			request.setAttribute("father_name", PropertyReader.getvalue("error.name", "Father Name"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("mother_name"))) {
			request.setAttribute("mother_name", PropertyReader.getvalue("error.require", "Mother Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("mother_name"))) {
			request.setAttribute("mother_name", PropertyReader.getvalue("error.name", "Mother Name"));
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", PropertyReader.getvalue("error.require", "Address"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileno"))) {
			request.setAttribute("mobileno", PropertyReader.getvalue("error.require", "Mobile No"));
			pass = false;

		}
		/*
		 * if (DataValidator.isNull(request.getParameter("email"))) {
		 * request.setAttribute("email", PropertyReader.getvalue("error.require",
		 * "Email Id")); pass = false;
		 * 
		 * } else if (!DataValidator.isEmail(request.getParameter("email"))) {
		 * request.setAttribute("email", PropertyReader.getvalue("error.login",
		 * "Email Id")); pass = false; }
		 */
		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getvalue("error.require", "date"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("information"))) {
			request.setAttribute("infotmation", PropertyReader.getvalue("error.require", "Information"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getvalue("error.require", "Gender"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("crime"))) {
			request.setAttribute("crime", PropertyReader.getvalue("error.require", "Crime"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("role"))) {
			request.setAttribute("role", PropertyReader.getvalue("error.require", "Role"));
			pass = false;
		}

		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		FirBean bean = new FirBean();
		HttpSession session = request.getSession(false);
		UserBean existBean = (UserBean)session.getAttribute("user");
		Long userId = existBean.getId();
		bean.setUserid(userId);
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setFather_name(DataUtility.getString(request.getParameter("father_name")));
		bean.setMother_name(DataUtility.getString(request.getParameter("mother_name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setMobileno(DataUtility.getString(request.getParameter("mobileno")));
		bean.setDate(DataUtility.getDate(request.getParameter("date")));
		bean.setInformation(DataUtility.getString(request.getParameter("information")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setCrime(DataUtility.getString(request.getParameter("crime")));
		populateDTO(bean, request);
		return bean;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do post");

		FirModel model = new FirModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		FirBean bean = new FirBean();
		bean = (FirBean) populateBean(request);

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OCRView.FIR_CTL, request, response);
			return;
		}

		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (FirBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("FIR Successfully Registered", request);
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
		return OCRView.FIR_VIEW;
	}

}
