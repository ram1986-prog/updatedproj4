package in.co.rays.project_4.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class BaseCtl.
 */
public abstract class BaseCtl extends HttpServlet {
	
	/** The Constant OP_SAVE. */
	public static final String OP_SAVE = "Save";//1
	
	/** The Constant OP_CANCEL. */
	public static final String OP_CANCEL = "Cancel";//2
	
	/** The Constant OP_DELETE. */
	public static final String OP_DELETE = "Delete";//3
	
	/** The Constant OP_LIST. */
	public static final String OP_LIST = "List";//4
	
	/** The Constant OP_SEARCH. */
	public static final String OP_SEARCH = "Search";//5
	
	/** The Constant OP_VIEW. */
	public static final String OP_VIEW = "View";//6
	
	/** The Constant OP_NEXT. */
	public static final String OP_NEXT = "Next";//7
	
	/** The Constant OP_PREVIOUS. */
	public static final String OP_PREVIOUS = "Previous";//8
	
	/** The Constant OP_NEW. */
	public static final String OP_NEW = "New";//9
	
	/** The Constant OP_GO. */
	public static final String OP_GO = "Go";//10
	
	/** The Constant OP_BACK. */
	public static final String OP_BACK = "Back";//11
	
	/** The Constant OP_LOG_OUT. */
	public static final String OP_LOG_OUT = "Logout";//12
	
	/** The Constant OP_CLEAR. */
	public static final String OP_CLEAR="Clear";//13
	
	/** The Constant OP_RESET. */
	public static final String OP_RESET="Reset";//14
	
	/** The Constant OP_UPDATE. */
	public static final String OP_UPDATE="Update";//15 
	//save| cancel| delete| list| search| view |next| previous| new| go| back| logout| clear| reset| update
	//scd lsv np ngb l cru
	
	

	/** Success message key constant. */
	public static final String MSG_SUCCESS = "success";//1

	/** Error message key constant. */
	public static final String MSG_ERROR = "error";//2
	//success| error
	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	//validate|preload|populatebean|populatedto|service|getview|6
	protected boolean validate(HttpServletRequest request) {

		System.out.println("BaseCtl validate method");
		return true;
	}

	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	protected void preload(HttpServletRequest request) {

		System.out.println("BaseCtl preload method");
	}

	/**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */
	protected BaseBean populateBean(HttpServletRequest request) {
		System.out.println("BaseCtl populateBean method");

		return null;
	}

	/**
	 * Populates Generic attributes in DTO.
	 *
	 * @param dto the dto
	 * @param request the request
	 * @return the base bean
	 */
	protected BaseBean populateDTO(BaseBean dto, HttpServletRequest request) {

		System.out.println("BaseCtl populateDTO method started");
		String createdBy = request.getParameter("createdBy");
		String modifiedBy = null;

		UserBean userbean = (UserBean) request.getSession().getAttribute("user");

		if (userbean == null) {
			System.out.println("BaseCtl populateDTO method  if()");
			// If record is created without login
			createdBy = "root";
			modifiedBy = "root";
		} else {
			System.out.println("BaseCtl populateDTO method else");
			modifiedBy = userbean.getLogin();

			// If record is created first time  
			if ("null".equalsIgnoreCase(createdBy) || DataValidator.isNull(createdBy)) {
				createdBy = modifiedBy;
			}

		}

		dto.setCreatedBy(createdBy);
		dto.setModifiedBy(modifiedBy);

		long cdt = DataUtility.getLong(request.getParameter("createdDatetime"));

		if (cdt > 0) {
			dto.setCreatedDatetime(DataUtility.getTimestamp(cdt));
		} else {
			dto.setCreatedDatetime(DataUtility.getCurrentTimestamp());
		}

		dto.setModifiedDatetime(DataUtility.getCurrentTimestamp());

		return dto;
		

	}

	/**
	 * Populates Generic workflow.
	 *
	 * 
	 * @param request response the request response
	 * @return the base bean
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("BaseCtl service Method starts");

		// Load the preloaded data required to display at HTML form
		preload(request);

		String op = DataUtility.getString(request.getParameter("operation"));
               System.out.println("BaseCtl operation: "+op);
		//  Check if operation is not DELETE, VIEW, CANCEL, and Reset then
		// perform input data validation

		if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op) && !OP_VIEW.equalsIgnoreCase(op)
				&& !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op) ){
			System.out.println("Basectl servicemethod 1 if() ");
			// Check validation, If fail then send back to page with error
			// messages

			if (!validate(request)) {
				System.out.println("BaseCtl Sercice method If check : !validate(request)");
				BaseBean bean = (BaseBean) populateBean(request);
				System.out.println("BaseCtl bean: "+bean);
				ServletUtility.setBean(bean, request);
				ServletUtility.forward(getView(), request, response);
				return;
			}
			System.out.println("Basectl servicemethod 2 if() ");
		}

		System.out.println("BaseCtl super.service method");
		super.service(request, response);
	}

	/**
	 * Returns the VIEW page of this Controller.
	  *
	 * @return the view
	 */
	protected abstract String getView();

}
