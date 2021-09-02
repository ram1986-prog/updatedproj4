package in.co.rays.project_4.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;



import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.bean.FacultyBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.model.CollegeModel;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.FacultyModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class FacultyListCtl.
 */
@WebServlet(name = " FacultyListCtl", urlPatterns = "/ctl/FacultyListCtl")
public class FacultyListCtl extends BaseCtl {

	/** The log. */
	private static Logger log = Logger.getLogger(FacultyListCtl.class);

	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	protected void preload(HttpServletRequest request) {

		log.debug("FacultyListCtl preload started");

		CollegeModel clgcmodel = new CollegeModel();
		CourseModel coursemodel = new CourseModel();

		try {
			List clglist = clgcmodel.list();
			List courselist = coursemodel.list();

			request.setAttribute("college", clglist);
			request.setAttribute("course", courselist);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("FacultyListCtl populateBean started");
		FacultyBean bean = new FacultyBean();
	
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
        bean.setCollegeId(DataUtility.getLong(request.getParameter("college")));
		bean.setEmailId(DataUtility.getString(request.getParameter("user")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("course")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
        System.out.println("............"+bean.getCollegeId());
		log.debug("FacultyListCtl populateBean ended");
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
			throws IOException, ServletException {
		log.debug("FacultyListCtl doGet started");

		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		FacultyBean bean = (FacultyBean) populateBean(request);
		FacultyModel model = new FacultyModel();
		String op = DataUtility.getString(request.getParameter("operation"));
		String[] ids = request.getParameterValues("ids");
		
		
		
		
		
		try {
			try {
				list = model.search(bean, pageNo, pageSize);
				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("no record found", request);
					request.setAttribute("next", list);

				}
			} catch (SQLException e) {
				throw new ApplicationException("error in faculty doGet");
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		}

		catch (Exception e) {
			// TODO: handle exception
			log.error(e);
			e.printStackTrace();
			// ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("facultyListCtl doGet method Ended");

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
			throws IOException, ServletException {
		log.debug("FacultyListCtl doPost method started");

		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		FacultyBean bean = (FacultyBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");
		FacultyModel model = new FacultyModel();
		
		
		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
				if (OP_SEARCH.equalsIgnoreCase(op)) {

					pageNo = 1;
					// System.out.println("hi doget of fac ctl inside
					// search"+pageNo);
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
				return;
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;
			}

			else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					FacultyBean deletebean = new FacultyBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						model.Delete(deletebean);
					}
                    ServletUtility.setSuccessMessage( "Data successfully deleted", request);

				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}

			else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;
			}
			try {

				list = model.search(bean, pageNo, pageSize);

				if (list == null || list.size() == 0) {
					
					if(!OP_DELETE.equalsIgnoreCase(op)){

					ServletUtility.setErrorMessage("Record not found ...!!!!", request);
					}
					request.setAttribute("list", list);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			// ServletUtility.handleException(e, request, response);
			return;
		}
	}


    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_LIST_VIEW;
	}

}
