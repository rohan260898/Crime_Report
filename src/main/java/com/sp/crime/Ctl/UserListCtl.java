package com.sp.crime.Ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.UserBean;
import com.sp.crime.Model.UserModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "UserListCtl", urlPatterns = "/userlist")
public class UserListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_BACK = "Back";
	public static final String OP_NEXT = "Next";
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";

	public UserListCtl() {
		super();

	}

	@Override
	protected void preload(HttpServletRequest request) {
		UserModel model = new UserModel();
		try {
			List list = model.list();
			request.setAttribute("userlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		bean.setEmail(DataUtility.getString(request.getParameter("email")));

		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		bean.setPhoneNo(DataUtility.getString(request.getParameter("phone")));

		bean.setGender(DataUtility.getString(request.getParameter("gender")));

		bean.setRoleid(DataUtility.getLong(request.getParameter("rolename")));

		populateDTO(bean, request);

		return bean;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = new UserModel();
		UserBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {
			model.delete(id);
			ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
		}

		List list = null;
		try {
			System.out.println("in do get");
			list = model.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null && list.size() == 0) {
			ServletUtility.setErrorMessage("No record found", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.forward(getView(), request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		bean = (UserBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OCRView.USER_LIST_CTL, request, response);
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

	protected String getView() {
		return OCRView.USER_LIST_VIEW;
	}
}
