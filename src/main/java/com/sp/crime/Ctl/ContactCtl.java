package com.sp.crime.Ctl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.ContactBean;
import com.sp.crime.Exception.ApplicationException;
import com.sp.crime.Exception.DuplicateRecordException;
import com.sp.crime.Model.ContactModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.DataValidator;
import com.sp.crime.Utility.PropertyReader;
import com.sp.crime.Utility.ServletUtility;

/**
 * Servlet implementation class ContactCtl
 */
@WebServlet(name = "ContactCtl" ,urlPatterns = "/contact")
public class ContactCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	public static final String OP_SEND= "send";

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("in validation");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getvalue("error.require", "Name"));
			pass = false;

		} 
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", PropertyReader.getvalue("error.require", "Email"));
			pass = false;

		} 
		if (DataValidator.isNull(request.getParameter("subject"))) {
			request.setAttribute("subject", PropertyReader.getvalue("error.require", "Subject"));
			pass = false;

		} 
		if (DataValidator.isNull(request.getParameter("message"))) {
			request.setAttribute("message", PropertyReader.getvalue("error.require", "Message"));
			pass = false;
		} 
		return pass;
	}

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected BaseBean populateBean(HttpServletRequest request) {
    	System.out.println("in populateBean");
		ContactBean bean = new ContactBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setSubject(DataUtility.getString(request.getParameter("subject")));
		bean.setMessage(DataUtility.getString(request.getParameter("message")));
		populateDTO(bean, request);
		return bean;

	}
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   ContactModel model = new ContactModel();
        System.out.println("in do post");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		ContactBean bean = new ContactBean();

			if (OP_SEND.equalsIgnoreCase(op)) {
				bean = (ContactBean) populateBean(request);
				try {
					long pk = model.add(bean);
					ServletUtility.setbean(bean, request);
					ServletUtility.setSuccessMessage("Contact Submit", request);
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
		return OCRView.CONTACT_VIEW;
	}

}
