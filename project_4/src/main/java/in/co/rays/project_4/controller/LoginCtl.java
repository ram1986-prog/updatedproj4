package in.co.rays.project_4.controller;

import java.io.IOException;


import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.RoleBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.model.RoleModel;
import in.co.rays.project_4.model.UserModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class LoginCtl.
 */
@WebServlet(name = "LoginCtl", urlPatterns = { "/LoginCtl" })
public class LoginCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Constant OP_REGISTER. */
	public static final String OP_REGISTER = "Register";
	
	/** The Constant OP_SIGN_IN. */
	public static final String OP_SIGN_IN = "SignIn";
	
	/** The Constant OP_SIGN_UP. */
	public static final String OP_SIGN_UP = "SignUp";
	
	/** The Constant OP_LOG_OUT. */
	public static final String OP_LOG_OUT = "logout";

	/** The log. */
	private static Logger log = Logger.getLogger(LoginCtl.class);

	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("LoginCtl Method validate Started");
		System.out.println("LoginCtl Method validate Started");
		boolean pass = true;

		String op = request.getParameter("operation");
		System.out.println("loginCtl validate Operation: " + op);
		if (OP_SIGN_UP.equals(op) || OP_LOG_OUT.equals(op)) {
			return pass;
		}

		String login = request.getParameter("login");
		System.out.println("loginCtl validate login: " + login);
		if (DataValidator.isNull(login)) {
			System.out.println(" i am in loginCtl validate if (DataValidator.isNull(login)) case");
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} /*
			 * else if (!DataValidator.isEmail(login)) { System.out.println(
			 * "i am in loginCtl validate else if (!DataValidator.isEmail(login)):"
			 * ); request.setAttribute("login",
			 * PropertyReader.getValue("error.email", "Login ")); pass = false;
			 * }
			 */
		if (DataValidator.isNull(request.getParameter("password"))) {
			System.out.println("i am in loginCtl validate if (DataValidator.isNull(request.getParameter password:");
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} /*
			 * else
			 * if(!DataValidator.isPassword(request.getParameter("password"))){
			 * request.setAttribute("password",
			 * PropertyReader.getValue("error.require","password")); pass=false;
			 * }
			 */

		System.out.println("loginCtl validate() Pass:" + pass);
		log.debug("LoginCtl Method validate Ended");
		return pass;

	}

	/**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("LoginCtl Method populatebean Started");

		System.out.println("LoginCtl Method populatebean Started");

		UserBean bean = new UserBean();
		System.out.println("LoginCtl Method populatebean bean: " + bean);
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		log.debug("LoginCtl Method populatebean Ended");

		System.out.println("LoginCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Display Logics inside this method.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(-1); 
		System.out.println("new session from login ctl doGet()");
		log.debug(" Method doGet Started");
   
		System.out.println(" LoginCtl Method doGet Started");

	 	String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("LoginCtl Method do  Get operation: " + op);
		// get model
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("LoginCtl doGet ID: " + id);

		if (id > 0) {
			UserBean userbean;
			try {
				userbean = model.findByPK(id);
				ServletUtility.setBean(userbean, request);
         return;
			} catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
			
			} 

		}

		if (OP_LOG_OUT.equals(op)) { 
				System.out.println("LoginCtl Method doGet    if (OP_LOG_OUT.equals(op))");
				session = request.getSession();

			 
				String uri =(String)session.getAttribute("uri");
			  
				System.out.println("Uri in doGet :"+uri);
			 
 
				session.invalidate();

		//long id = DataUtility.getLong(request.getParameter("id"));

			/*
			 * System.out.println("LoginCtl Method doGet ID: "+id);
			 * ServletUtility.setSuccessMessage("Data is successfully saved",
			 * request);
			 */

			if (id == 0) {
				ServletUtility.setSuccessMessage("logout successfully ", request);
		
			ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
 
			return;
			}
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");

		System.out.println(" LoginCtl Method doGet Ended");
	}

	/**
	 * Submit logic inside it.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		log.debug(" LoginCtl Method doPost Started");

		System.out.println(" LoginCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model
		UserModel model = new UserModel();
		RoleModel role = new RoleModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("LoginCtl doPost Id:" + id);
		UserBean bean = null;
		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			bean = (UserBean) populateBean(request);

			try {
				bean = model.authenticate(bean.getLogin(), bean.getPassword());
				String URI = (String) request.getParameter("uri");
				System.out.println("LoginCtl doPost bean:" + bean);
								System.out.println(URI+"--->>uri");
				if (bean != null) {

					session.setAttribute("user", bean);

					long rollId = bean.getRoleId();
					
					RoleBean rolebean = role.findByPK(rollId);
					System.out.println("LoginCtl doPost rollID :" + rollId);

					if (rolebean != null) {
						session.setAttribute("role", rolebean.getName());
					}
					
					
					if ("null".equalsIgnoreCase(URI)) {
						ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
						return;
					} else {
						
							ServletUtility.redirect(URI, request, response);
							return;
						} /*else {
							ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);
						}
						return;*/
				}
				
				else {
					bean = (UserBean) populateBean(request);
					ServletUtility.setBean(bean, request);
					ServletUtility.setErrorMessage("Invalid LoginId And Password", request);
				}

				}

				

			 catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.USER_REGISTRATION_CTL, request, response);
			return;

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPost Ended");
		System.out.println(" LoginCtl Method doPost ended");

	}


    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {

		System.out.println(" LoginCtl getView");
		return ORSView.LOGIN_VIEW;
	}

}
