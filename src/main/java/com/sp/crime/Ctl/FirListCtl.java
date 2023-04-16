package com.sp.crime.Ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sp.crime.Bean.BaseBean;
import com.sp.crime.Bean.FirBean;
import com.sp.crime.Bean.UserBean;
import com.sp.crime.Model.FirModel;
import com.sp.crime.Utility.DataUtility;
import com.sp.crime.Utility.ServletUtility;

/**
 * Servlet implementation class FirList
 */
@WebServlet(name = "FirListCtl", urlPatterns = "/firlistctl")
public class FirListCtl extends BaseCtl{
	private static final long serialVersionUID = 1L;
	public static final String OP_PREVIOUS = "Previous";
	public static final String OP_NEW = "New";
	public static final String OP_BACK = "Back";
	public static final String OP_NEXT = "Next";
	public static final String OP_SEARCH = "Search";
	public static final String OP_RESET = "Reset";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirListCtl() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
	protected void preload(HttpServletRequest request) {
		FirModel model = new FirModel();
		try {
			List list = model.list();
			request.setAttribute("firlist", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	@Override
    protected BaseBean populateBean(HttpServletRequest request) {

		FirBean bean = new FirBean();

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
		bean.setRoleid(DataUtility.getLong(request.getParameter("role")));
		populateDTO(bean, request);
		return bean;

	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		FirModel model = new FirModel();
		FirBean bean = new FirBean();
        List list = null;
        HttpSession session = request.getSession(false);
    	UserBean bean2 = (UserBean) session.getAttribute("user");
    	long roleid = bean2.getRoleid();
    	if (roleid==2) {
    		   try {
    			     list =	model.ShowFirlist(bean2.getId());
    			     ServletUtility.setList(list, request);
    			     
    			} catch (Exception e) {
    			}
		}else{
			 try {
			     list =	model.list();
			     ServletUtility.setList(list, request);
			     
			} catch (Exception e) {
			
		}
			 long id = DataUtility.getLong(request.getParameter("id"));
				
			  if (id > 0) { model.delete(id);
			  ServletUtility.setSuccessMessage("FIR Deleted Successfully", request); }
			 
		}
        ServletUtility.forward(getView(), request, response);
	}

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { FirModel model = new
	 * FirModel(); FirBean bean = null; long id =
	 * DataUtility.getLong(request.getParameter("id"));
	 * 
	 * if (id > 0) { model.delete(id);
	 * ServletUtility.setSuccessMessage("FIR Deleted Successfully", request); }
	 * 
	 * List list = null; try { System.out.println("in do get"); list = model.list();
	 * } catch (Exception e) { e.printStackTrace(); } if (list == null &&
	 * list.size() == 0) { ServletUtility.setErrorMessage("No record found",
	 * request); } ServletUtility.setList(list, request);
	 * ServletUtility.forward(getView(), request, response);
	 * 
	 * }
	 */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		FirModel model = new FirModel();
		FirBean bean = new FirBean();
		bean = (FirBean) populateBean(request);
		List list = null;

		if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(OCRView.FIR_LIST_CTL, request, response);
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

	@Override
	protected String getView() {
		return OCRView.FIR_LIST_VIEW;
	}

}
