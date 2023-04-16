package com.sp.crime.Ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.CrimeBean;
import com.sp.crime.Bean.RoleBean;
import com.sp.crime.Model.CrimeModel;
import com.sp.crime.Model.RoleModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.DataValidator;
import com.sp.crime.Utility.PropertyReader;
import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "RoleCtl", urlPatterns = "/role")
public class RoleCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SAVE = "Save";

	public RoleCtl() {
		super();
	}

	 @Override
		protected boolean validate(HttpServletRequest request) {
			System.out.println("in validation");
			boolean pass = true;

			if (DataValidator.isNull(request.getParameter("rolename"))) {
				request.setAttribute("rolename", PropertyReader.getvalue("error.require", "Role Name"));
				pass = false;
			}

		return pass;
	}
	    
	    protected BaseBean populateBean(HttpServletRequest request) {

			RoleBean bean = new RoleBean();

			bean.setId(DataUtility.getLong(request.getParameter("id")));
			bean.setRolename(DataUtility.getString(request.getParameter("rolename")));
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
		RoleModel model = new RoleModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		RoleBean bean = new RoleBean();
		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (RoleBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("Role Successfully Add", request);
				ServletUtility.forward(getView(), request, response);
				return;
			} catch (Exception e) {

				e.printStackTrace();
			}
			ServletUtility.forward(getView(), request, response);
		}
	}

	@Override
	protected String getView() {
		return OCRView.ROLE_VIEW;
	}
}
