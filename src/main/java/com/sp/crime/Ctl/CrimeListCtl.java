package com.sp.crime.Ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.CrimeBean;
import com.sp.crime.Model.CrimeModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "CrimeListCtl", urlPatterns = "/crimelist")
public class CrimeListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	public CrimeListCtl() {
		super();
	}

	@Override
	protected void preload(HttpServletRequest request) {
		CrimeModel model = new CrimeModel();
		try {
			List list = model.list();
			request.setAttribute("crimelist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		CrimeBean bean = new CrimeBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCrime_name(DataUtility.getString(request.getParameter("crime_name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		populateDTO(bean, request);
		return bean;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CrimeModel model = new CrimeModel();
		CrimeBean bean = null;
		long id = DataUtility.getLong(request.getParameter("id"));


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
		doGet(request, response);
	}

	@Override
	protected String getView() {
		return OCRView.CRIME_LIST_VIEW;
	}

}
