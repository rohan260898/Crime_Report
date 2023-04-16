package com.sp.crime.Ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Utility.ServletUtility;

/**
 * Servlet implementation class AboutCtl
 */
@WebServlet(name = "AboutCtl" ,urlPatterns = "/about")
public class AboutCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    public AboutCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected String getView() {
		return OCRView.ABOUT_VIEW;
	}

}
