package in.co.rays.project_4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.ServletUtility;

/**
 * Error functionality controller.perform error page operation
 * @author himshikha
 *
 */
@WebServlet(name="ErrorCtl", urlPatterns={"/ErrorCtl"})
public class ErrorCtl extends BaseCtl{
	private static final Long servialVersionUID=1L;
	
	
	/**
	 * Display Logics inside this method.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		
		ServletUtility.forward(getView(), request, response);
		
	}
	/**
	 * Submit logic inside it.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
 protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	
  ServletUtility.forward(getView(), request, response);
 
   
 }

 /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ERROR_VIEW;
	}

}