package com.sp.crime.Ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.UserBean;
import com.sp.crime.Model.UserModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.DataValidator;
import com.sp.crime.Utility.PropertyReader;
import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "MyProfileCtl", urlPatterns = "/myprofile")
public class MyProfileCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_MYPROFILE = "MyProfile";
	public static final String OP_UPDATE = "Update";

	
	public MyProfileCtl() {
		super();
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

		  HttpSession session = request.getSession(true);

	        UserBean UserBean = (UserBean) session.getAttribute("user");
	        long id = UserBean.getId();
	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        UserModel model = new UserModel();
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            UserBean bean;
	            try {
	                bean = model.findByPk(id);
	                ServletUtility.setbean(bean, request);
	            } catch (Exception e) {          //ApplicationException
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }	       
	        ServletUtility.forward(getView(), request, response);
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		UserBean UserBean = (UserBean) session.getAttribute("user");
		long id = UserBean.getId();
		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();
		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OCRView.MYPROFILE_CTL, request, response);
			return;
		}

		if (OP_UPDATE.equalsIgnoreCase(op)) {
			UserBean bean = (UserBean) populateBean(request);
			try {
				if (id > 0) {
					UserBean.setFirstName(bean.getFirstName());
					UserBean.setLastName(bean.getLastName());
					UserBean.setGender(bean.getGender());
					UserBean.setPhoneNo(bean.getPhoneNo());
					model.Update(UserBean);
					ServletUtility.setbean(bean, request);
					ServletUtility.setSuccessMessage("Profile has been updated Successfully. ", request);
					ServletUtility.forward(getView(), request, response);

				}
			} catch (Exception e) { // ApplicationException
				ServletUtility.handleException(e, request, response);
				return;
			}
			return;

		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected String getView() {
		return OCRView.MYPROFILE_VIEW;
	}

}
