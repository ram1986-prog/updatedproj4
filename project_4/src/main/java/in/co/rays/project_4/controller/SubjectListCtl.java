package in.co.rays.project_4.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.SubjectModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class SubjectListCtl.
 */
@WebServlet(urlPatterns = "/ctl/SubjectListCtl")
public class SubjectListCtl extends BaseCtl {

	/** The log. */
	private static Logger log = Logger.getLogger(SubjectListCtl.class);

	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	protected void preload(HttpServletRequest request) {
		log.debug("prload debug started");
		CourseModel cModel = new CourseModel();

		try {
			List l = cModel.list();

			request.setAttribute("courseList", l);

		} catch (Exception e) {
			e.printStackTrace();
		}

		log.debug("preload debug completed");
	}
	/**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("subjectListCtl populateBean debug started");

		SubjectBean bean = new SubjectBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setCourseName(DataUtility.getStringData(request.getParameter("courseName")));
		bean.setSubjectName(DataUtility.getStringData(request.getParameter("subjectName")));
		
		bean.setDescription(DataUtility.getStringData(request.getParameter("description")));

		log.debug("SubjectListCtl populate bean debug completed");
		populateDTO(bean, request);

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

		log.debug("SubjectListCtl doget debug started");
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		SubjectBean bean = (SubjectBean) populateBean(request);
		System.err.println("subjectListCtl doGet: "+bean.getSubjectName());

		String op = DataUtility.getString(request.getParameter("operation"));

		SubjectModel model = new SubjectModel();
		try {
			try {
				list = model.search(bean, pageNo, pageSize);

				if (list == null || list.size() == 0) {
					ServletUtility.setErrorMessage("record not found", request);
					request.setAttribute("next", list);
					return;
				}
			} catch (SQLException e) {
				throw new ApplicationException("exception in doget search");
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);

			/*
			 * ServletUtility.handleException(e, request, response);
			 */ return;
		}
		log.debug("SubjectListCtl doget debug completed");
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
		log.debug("courseListctl debug started");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));
		String ids[] = request.getParameterValues("ids");

		SubjectBean bean = (SubjectBean) populateBean(request);
		SubjectModel model = new SubjectModel();

		try {
			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
				return;
			} else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
				return;
			} else if (OP_BACK.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
				return;
			}

			else if (OP_DELETE.equalsIgnoreCase(op)) {
				System.out.println("SubjectListClt OP_DELETE started:");
				pageNo = 1;
				if (ids != null && ids.length > 0) {
					SubjectBean deleteBean = new SubjectBean();
					System.out.println("SubjectListClt OP_DELETE started deletebean :"+deleteBean);
					for (String id : ids) {
						deleteBean.setId(DataUtility.getInt(id));
						try {
							model.delete(deleteBean);
							bean.setId(0);
						} catch (Exception e) {
							e.printStackTrace();
						}
						ServletUtility.setSuccessMessage("Data successfully deleted", request);
					}
					

				} else {
					ServletUtility.setErrorMessage("select atleast one record", request);
				}
			}

			try {

				list = model.search(bean, pageNo, pageSize);
				ServletUtility.setBean(bean, request); 
				if (list == null || list.size() == 0) {
					
					if(!OP_DELETE.equalsIgnoreCase(op)){
					ServletUtility.setErrorMessage("No Record Found...!!!", request);
					}
					//request.setAttribute("next", list);
				}

			} catch (Exception e) {
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
			/* ServletUtility.handleException(e, request, response); */
			return;
		}
		log.debug("courseListctl debug completed");

	}


    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_LIST_VIEW;
	}

}
