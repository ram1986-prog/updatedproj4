package in.co.rays.project_4.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.StudentBean;
import in.co.rays.project_4.bean.TimeTableBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DatabaseException;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.StudentModel;
import in.co.rays.project_4.model.SubjectModel;
import in.co.rays.project_4.model.TimeTableModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class TimeTableListCtl.
 */
@WebServlet(name="TimeTableListCtl",urlPatterns = { "/ctl/TimeTableListCtl" })

public class TimeTableListCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(TimeTableListCtl.class);

	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	protected void preload(HttpServletRequest request) {
		log.debug("TimeTable preload method started");

		SubjectModel smodel=new SubjectModel();
		CourseModel cmodel = new CourseModel();
		TimeTableModel tmodel=new TimeTableModel();
		

		try {
			
			List clist = cmodel.list();
			List slist=smodel.list();
			List tlist=tmodel.list();
			
			request.setAttribute("courseList", clist);
			request.setAttribute("subjectList", slist);
			request.setAttribute("TimetableList", tlist);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
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
		log.debug("timetable list ctl debug started");

		TimeTableBean bean = new TimeTableBean();

		bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));

		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

		bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));

		bean.setExamDate(DataUtility.getSearchDate(request.getParameter("examDate")));

		bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setSemester(DataUtility.getString(request.getParameter("semester")));

		log.debug("timetable list ctl debug completed");

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
		log.debug("timetable list doGet Started");

		List list = null;

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		TimeTableBean bean = (TimeTableBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("chk_1");
		TimeTableModel model = new TimeTableModel();
		try {

			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record found...!!!! ", request);
				request.setAttribute("next", list);
				return;

			} else {
				ServletUtility.setBean(bean, request);
				ServletUtility.setList(list, request);
				ServletUtility.setPageNo(pageNo, request);
				ServletUtility.setPageSize(pageSize, request);
				ServletUtility.forward(getView(), request, response);
			}
		} catch (Exception e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("timetablelistCtl doGet Ended");
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
		log.debug("TimeTableListCtl doPost Start");
		System.out.println("TimeTableListCtl doPost started-------++" + request.getParameter("operation"));
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		TimeTableBean bean = (TimeTableBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println("TimeTableListCtl doPost Operation" + op);
		System.out.println("TimeTableListCtl doPost started-------++" + request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");
		System.out.println("TimeTableListCtl doPost ids " + ids);
		TimeTableModel model = new TimeTableModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {
				System.out.println("TimeTableListCtl if1");
				if (OP_SEARCH.equalsIgnoreCase(op)) {
					System.err.println("TimeTableListCtl if2");
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				}

			}
			/*
			 * else if(OP_BACK.equalsIgnoreCase(op)){ System.out.println(
			 * "TimeTableListCtl OP_BACK<<---------------");
			 * //ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request,
			 * response); ServletUtility.forward(ORSView.TIME_TABLE_LIST_VIEW,
			 * request, response); return; }
			 */
			else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
				return;
			}

			else if (OP_NEW.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.TIME_TABLE_CTL, request, response);
				return;
			} else if (OP_DELETE.equalsIgnoreCase(op)) {

				System.err.println("TimeTableListCtl if Delete block");

				pageNo = 1;
				if (ids != null && ids.length > 0) {
					TimeTableBean deletebean = new TimeTableBean();
					for (String id : ids) {
						deletebean.setId(DataUtility.getInt(id));
						try {
							model.delete(deletebean);
							System.out.println("TimeTableListCtl if Delete");
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					ServletUtility.setSuccessMessage("Data successfully deleted", request);

				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}

			}
			if (OP_BACK.equalsIgnoreCase(op)) {
				System.out.println("TimeTableListCtl OP_BACK<<---------------");
				ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
				// ServletUtility.forward(ORSView.TIME_TABLE_LIST_VIEW, request,
				// response);
				return;
			}
			list = model.search(bean, pageNo, pageSize);

			ServletUtility.setList(list, request);
			if (list == null || list.size() == 0) {

				if (!OP_DELETE.equalsIgnoreCase(op)) {
					ServletUtility.setErrorMessage("No record found ", request);
				}
				
			}
			

			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			System.err.println("TimeTableListCt doPost <<<");
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("StudentListCtl doGet End");
	}


    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		System.out.println("TimeTableListCtl getView ");
		// TODO Auto-generated method stub
		return ORSView.TIME_TABLE_LIST_VIEW;
	}

}
