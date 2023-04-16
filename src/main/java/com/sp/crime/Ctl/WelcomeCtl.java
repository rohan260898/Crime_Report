package com.sp.crime.Ctl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sp.crime.Utility.ServletUtility;

@WebServlet(name = "WelcomeCtl" ,urlPatterns = "/index")
public class WelcomeCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WelcomeCtl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   ServletUtility.forward(getview(), request, response);
	}

	private String getview() {
		
		return OCRView.INDEX_VIEW;
	}

}
