package com.sp.crime.Ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.CrimeBean;
import com.sp.crime.Bean.FirBean;
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Exception.DuplicateRecordException;
import com.sp.crime.Model.CrimeModel;
import com.sp.crime.Model.FirModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.DataValidator;
import com.sp.crime.Utility.PropertyReader;
import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "CrimeCtl", urlPatterns = "/crime")
public class CrimeCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SAVE = "Save";

    public CrimeCtl() {
        super();
    }

    @Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validation");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("crime_name"))) {
			request.setAttribute("crime_name", PropertyReader.getvalue("error.require", "Crime Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getvalue("error.require", "Description"));
			pass = false;
		}
	return pass;
}
    
    protected BaseBean populateBean(HttpServletRequest request) {

		CrimeBean bean = new CrimeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCrime_name(DataUtility.getString(request.getParameter("crime_name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		return bean;

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in do post");

		CrimeModel model = new CrimeModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		CrimeBean bean = new CrimeBean();
		if (OP_SAVE.equalsIgnoreCase(op)) {
			bean = (CrimeBean) populateBean(request);
			try {
				long pk = model.add(bean);
				ServletUtility.setbean(bean, request);
				ServletUtility.setSuccessMessage("Crime Successfully Add", request);
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
		return OCRView.CRIME_VIEW;
	}

}
