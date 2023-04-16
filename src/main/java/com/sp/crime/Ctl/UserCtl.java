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

@WebServlet(name = "UserCtl", urlPatterns = "/user")
public class UserCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_NEW = "New";
	public static final String OP_UPDATE = "Update";
	public static final String OP_SAVE = "Save";

	public UserCtl() {
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
			request.setAttribute("email", PropertyReader.getvalue("error.require", "Login Id"));
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
		System.out.println("in do get");
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
		UserModel model = new UserModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			UserBean bean = null;
			try {
				bean = model.findByPk(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ServletUtility.setbean(bean, request);
		}
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = new UserModel();
		System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		UserBean bean = new UserBean();
		bean = (UserBean) populateBean(request);

		if (bean.getId() > 0) {
			System.out.println("in do post2");
			long i = model.Update(bean);
			ServletUtility.setSuccessMessage("Data Updated Successfully", request);
		} else {
			try {
				long pk = model.add(bean);
				ServletUtility.setSuccessMessage("Data Add Successfully", request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {

		return OCRView.USER_VIEW;
	}

}
