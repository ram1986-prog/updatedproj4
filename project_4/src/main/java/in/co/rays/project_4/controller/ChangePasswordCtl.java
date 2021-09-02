package in.co.rays.project_4.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.RecordNotFoundException;
import in.co.rays.project_4.model.UserModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class ChangePasswordCtl.
 */
@WebServlet(name = "ChangePasswordCtl", urlPatterns = { "/ctl/ChangePasswordCtl" })
public class ChangePasswordCtl extends BaseCtl {

	/** The Constant OP_CHANGE_MY_PROFILE. */
	public static final String OP_CHANGE_MY_PROFILE = "Change My Profile";

	/** The log. */
	private static Logger log = Logger.getLogger(ChangePasswordCtl.class);

	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	//validate|populatebean|doget|dopost|getview|5
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ChangePasswordCtl Method validate Started");
		System.out.println("ChangePasswordCtl Method validate Started");

		boolean pass = true;

		String op = request.getParameter("operation");

		if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {

			return pass;
		}
		if (DataValidator.isNull(request.getParameter("oldPassword"))) {
			request.setAttribute("oldPassword", PropertyReader.getValue("error.require", "Old Password"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("newPassword"))) {
			request.setAttribute("newPassword", PropertyReader.getValue("error.require", "New Password"));
			pass = false;
		}else if (!DataValidator.isPassword(request.getParameter("newPassword")))  {
			request.setAttribute("newPassword", "Password must contain uppercase,lowercase,specialcharacter,digit ");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "Confirm Password"));
			pass = false;
		}
		if (!request.getParameter("newPassword").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "Conform Password Should be Match");
			pass = false;
		}
         
		log.debug("ChangePasswordCtl Method validate Ended");
		System.out.println("ChangePasswordCtl Method validate ended"+pass);

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
		log.debug("ChangePasswordCtl Method populatebean Started");
		System.out.println("ChangePasswordCtl Method populatebean Started");

		UserBean bean = new UserBean();

		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));

		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));

		populateDTO(bean, request);

		log.debug("ChangePasswordCtl Method populatebean Ended");
		System.out.println("ChangePasswordCtl Method populatebean Ended"+bean.getConfirmPassword()+"<><<>><>>"+bean.getPassword());

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		log.debug("ChangePasswordCtl Method doGet Started");
		System.out.println("ChangePasswordCtl Method dopost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("changePassword "+op);
		// get model
		UserModel model = new UserModel();
		UserBean bean = (UserBean) populateBean(request);
		UserBean UserBean = (UserBean) session.getAttribute("user");
		String newPassword = (String) request.getParameter("newPassword");
		long id = UserBean.getId();
		System.out.println("id");

		if (OP_SAVE.equalsIgnoreCase(op)) {
			
			System.out.println("change password doPost If()");
			try {
				System.out.println("<<.....>>>>>>");
				boolean flag = model.changePassword(id, bean.getPassword(), newPassword);
				System.out.println(flag);
				if (flag == true) {
					
					bean = model.findByLogin(UserBean.getLogin());
                        System.out.println("<<<>>>>>>"+bean);
					//ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);
					session.setAttribute("user", bean);
					ServletUtility.setBean(bean, request);

					ServletUtility.setSuccessMessage("Password has been changed Successfully.", request);
					
					System.out.println("Password has been changed Successfully");
					
					ServletUtility.forward(getView(), request, response);
				}
			} catch (ApplicationException e) {
				log.error(e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;

			} catch (RecordNotFoundException e) {
				e.printStackTrace();
				ServletUtility.setErrorMessage("Old PassWord is Invalid", request);
			}
		} else if (OP_CHANGE_MY_PROFILE.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.MY_PROFILE_CTL, request, response);
			return;
		}
		ServletUtility.forward(ORSView.CHANGE_PASSWORD_VIEW, request, response);

		log.debug("ChangePasswordCtl Method doGet Ended");
		System.out.println("ChangePasswordCtl Method doGet Ended");

	}

	/**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		return ORSView.CHANGE_PASSWORD_VIEW;
	}

}
