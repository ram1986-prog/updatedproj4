package in.co.rays.project_4.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mchange.util.DuplicateElementException;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.FacultyBean;
import in.co.rays.project_4.bean.MarksheetBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.exception.DuplicateRecordsException;
import in.co.rays.project_4.model.MarksheetModel;
import in.co.rays.project_4.model.StudentModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class MarksheetCtl.
 */
@WebServlet(name = "MarksheetCtl", urlPatterns = { "/ctl/MarksheetCtl" })
public class MarksheetCtl extends BaseCtl {
	
	/** The log. */
	private static Logger log = Logger.getLogger(MarksheetCtl.class);

	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		StudentModel model = new StudentModel();
		try {
			List l = model.list();
			System.out.println("marksheetCtl: "+l);
			request.setAttribute("studentList", l);
		} catch (Exception e) { // ApplicationException
			log.error(e);
		}

	}

	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("MarksheetCtl Method validate Started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
		pass = false;
		System.out.println("rollno"+request.getParameter("rollNo"));
		
	
		}else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "Roll must be proper format");
			pass = false;
			System.out.println("MarksheetListCtl is rollno: "+request.getParameter("rollNo"));
			
		}
		 if (DataValidator.isNull(request.getParameter("studentId"))) {
	            request.setAttribute("studentId",PropertyReader.getValue("error.require", "Student Name"));
	            pass = false;
	        }
		if (DataValidator.isNull(request.getParameter("physics"))
				&& !DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
			System.out.println("physics"+request.getParameter("physics"));
		}
		else if (DataUtility.getInt(request.getParameter("physics")) > 100  ){
			request.setAttribute("physics", "Marks can not be greater than 100");
			pass = false;
		}
			else if (DataUtility.getInt(request.getParameter("physics")) < 0  ){
				request.setAttribute("physics", "Marks can not be less than 0");
				pass = false;
		}else if (!DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("chemistry"))
				&& !DataValidator.isInteger(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
			System.out.println("chemistry"+request.getParameter("chemistry"));
		}
        
		else if (DataUtility.getInt(request.getParameter("chemistry")) > 100) {
			request.setAttribute("chemistry", "Marks can not be greater than 100");
			pass = false;
		}
		else if (DataUtility.getInt(request.getParameter("chemistry")) < 0) {
			request.setAttribute("chemistry", "Marks can not be less than 0");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("maths"))
				&& !DataValidator.isInteger(request.getParameter("maths"))) {
			request.setAttribute("maths", PropertyReader.getValue("error.integer", "Marks"));
			pass = false;
			
			System.out.println("maths"+request.getParameter("maths"));
		}

		else if (DataUtility.getInt(request.getParameter("maths")) > 100) {
			request.setAttribute("maths", "Marks can not be greater than 100");
			pass = false;
		}
		else if (DataUtility.getInt(request.getParameter("maths")) < 0) {
			request.setAttribute("maths", "Marks can not be less than 0");
			pass = false;
		}

		

		log.debug("MarksheetCtl Method validate Ended");

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

		log.debug("MarksheetCtl Method populatebean Started");
		
		System.out.println("MarksheetCtl populatebean started");

		MarksheetBean bean = new MarksheetBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		System.out.println(bean.getId());

		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		System.out.println(bean.getRollNo());
		bean.setName(DataUtility.getString(request.getParameter("name")));
		System.out.println(bean.getName());
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		System.out.println(bean.getPhysics());
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		System.out.println(bean.getChemistry());
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));
		System.out.println(bean.getMaths());
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		System.out.println("Student Id:"+bean.getStudentId());
		populateDTO(bean, request);

		System.out.println("marksheet Population done");

		log.debug("MarksheetCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains Display logics.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("MarksheetCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		MarksheetModel model = new MarksheetModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0) {
			MarksheetBean bean;
			
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) { // ApplicationException
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("MarksheetCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("MarksheetCtl Method doPost Started");
		System.out.println("MarksheetCtl Method doPost Started");

		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		System.out.println("operation is: "+op);
		// get model
		MarksheetModel model = new MarksheetModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("marksheetCtl id: "+id);

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			System.out.println("MarksheetCtl doPost OP_SAVE");
         
			MarksheetBean bean = (MarksheetBean) populateBean(request);
			//System.out.println(bean.getChemistry());
			
			try {
				System.out.println(">>>>>>>>>>>>>>>>---"+id);
				if (id>0) {
					System.out.println("helllllllllll");
					model.update(bean);
					System.out.println("MarksheetCtl update");
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
				} else  {
					System.out.println("add method started");
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully saved",request);
					//bean.setId(pk);
				}
				
				
			} 
			catch (DuplicateRecordException e) {
			      ServletUtility.setBean(bean, request);
				  ServletUtility.setErrorMessage("Roll no already exists", request);
				  }
				
				catch (Exception e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				} 
			 
			 
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			MarksheetBean bean = (MarksheetBean) populateBean(request);
			System.out.println("in try");
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
				System.out.println("in try");
				return;
			} catch (Exception e) { // ApplicationException
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
			return;

		}
		else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
			return;

		}
		ServletUtility.forward(getView(), request, response);

		log.debug("MarksheetCtl Method doPost Ended");
	}


    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		return ORSView.MARKSHEET_VIEW;
	}

}
