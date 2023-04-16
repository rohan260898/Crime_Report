package com.sp.crime.Ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.RoleBean;
import com.sp.crime.Bean.UserBean;
import com.sp.crime.Model.RoleModel;
import com.sp.crime.Model.UserModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.DataValidator;
import com.sp.crime.Utility.PropertyReader;
import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "LoginCt", urlPatterns = "/login")
public class LoginCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public static final String OP_SINGIN = "SignIn";
	public static final String OP_SING_UP = "SignUp";
	public static final String OP_LOGOUT = "Logout";

	public LoginCtl() {
		super();
	}

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		String op = request.getParameter("operation");

		if (OP_SING_UP.equalsIgnoreCase(op) || OP_LOGOUT.equalsIgnoreCase(op)) {
			return true;
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getvalue("error.require", "Login Id"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getvalue("error.require", "Password"));
			pass = false;
		}
		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		UserBean bean = new UserBean();

		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		populateDTO(bean, request);

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));

		HttpSession session = request.getSession(false);

		UserBean bean = (UserBean) populateBean(request);
		if (OP_LOGOUT.equalsIgnoreCase(op)) {
			session = request.getSession(false);
			session.invalidate();
			ServletUtility.setSuccessMessage("LogoutSucessfully", request);
			ServletUtility.forward(OCRView.LOGIN_VIEW, request, response);
			return;
		}
		ServletUtility.setbean(bean, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		String op = DataUtility.getString(request.getParameter("operation"));

		UserModel model = new UserModel();
		RoleModel role = new RoleModel();

		if (OP_SING_UP.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OCRView.USER_REGISTRATION_CTL, request, response);
			return;
		}

		if (OP_SINGIN.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				bean = model.Authenticate(bean.getEmail(), bean.getPassword());
				if (bean != null) {
					long rollId = bean.getRoleid();
					RoleBean rolebean = role.findByPK(rollId);
					if (rolebean != null) {
						session.setAttribute("role", rolebean.getRolename());
						session.setAttribute("user", bean);
						ServletUtility.setbean(bean, request);
						ServletUtility.redirect(OCRView.WELCOME_CTL, request, response);
						return;
					}
				} else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setbean(bean, request);
					ServletUtility.setErrorMessage("Invalid Id and Password", request);
					ServletUtility.forward(getView(), request, response);
				}
			} catch (Exception e) {
			}

		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return OCRView.LOGIN_VIEW;
	}

}
