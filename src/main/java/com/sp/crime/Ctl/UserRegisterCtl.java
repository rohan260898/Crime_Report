package com.sp.crime.Ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.UserBean;
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Exception.DuplicateRecordException;
import com.sp.crime.Model.UserModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.DataValidator;
import com.sp.crime.Utility.PropertyReader;
import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "UserRegisterCtl", urlPatterns = "/register")
public class UserRegisterCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public static final String OP_SIGN_UP = "SignUp";
	public static final String OP_SAVE = "Save";
	public static final String OP_RESET = "Reset";
	public static final String OP_NEW = "New";
	public static final String OP_MYPROFILE = "MyProfile";

	public UserRegisterCtl() {
		super();
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validation");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getvalue("error.require", "First Name"));
			pass = false;

		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getvalue("error.name", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getvalue("error.require", "Last Name"));
			pass = false;
			
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getvalue("error.name", "Last Name"));
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getvalue("error.require", "Email Id"));
			pass = false;

		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getvalue("error.login", "Email Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getvalue("error.require", "Password"));
			pass = false;

		}

		else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getvalue("error.password", "Password"));
			return false;

		}
		if (DataValidator.isNull(request.getParameter("phoneNo"))) {
			request.setAttribute("phoneNo", PropertyReader.getvalue("error.require", "Phone No"));
			pass = false;

		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getvalue("error.require", "Gender"));
			pass = false;
		}

		if ("-----Select-----".equalsIgnoreCase(request.getParameter("roleName"))) {
			request.setAttribute("roleName", PropertyReader.getvalue("error.require", "RoleName"));
			pass = false;
		}

		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setRoleid(2);
		populateDTO(bean, request);
		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do post");
		UserModel model = new UserModel();

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		UserBean bean = new UserBean();
		bean = (UserBean) populateBean(request);

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OCRView.USER_REGISTRATION_CTL, request, response);
			return;
		}

		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (UserBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("User Successfully Registered", request);
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
		return OCRView.USER_REGISTRATION_VIEW;
	}

}
