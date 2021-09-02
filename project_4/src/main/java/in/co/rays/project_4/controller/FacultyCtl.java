package in.co.rays.project_4.controller;

import java.util.List;

import java.io.IOException;
import java.sql.SQLException;
import java.util.AbstractMap.SimpleImmutableEntry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

//inheriting javax.servlet.http.httpservlet
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.FacultyBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.CollegeModel;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.FacultyModel;
import in.co.rays.project_4.model.SubjectModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator; 
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class FacultyCtl.
 */
//annotation to maping a servlet with urlpattern
@WebServlet(name="FacultyCtl", urlPatterns="/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl{
	
	/** The log. */
	private static Logger log=Logger.getLogger(FacultyCtl.class);
	
	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	protected void preload(HttpServletRequest request){
		log.debug("FacultyCtl Preload started");
		

		CollegeModel collegemodel=new CollegeModel();
	    SubjectModel subjectmodel=new SubjectModel();
	    CourseModel coursemodel=new CourseModel();
		try {
			List collegelist=collegemodel.list();
			request.setAttribute("collegeList", collegelist);
			
			List subjectlist=subjectmodel.list();
			request.setAttribute("subjectList", subjectlist);
			
			List courselist=coursemodel.list();
			request.setAttribute("courseList", courselist);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.equals(e);
		}
		log.debug("FacultyCtl Preload ended");
	}
	
	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	protected boolean validate(HttpServletRequest request){
		
		boolean pass=true;
		
 		String emailId=request.getParameter("emailId");
		String doj=request.getParameter("doj");
		
		
		
		if(DataValidator.isNull(request.getParameter("firstName"))){
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "FirstName"));
			pass= false;
			
		}
		else if(!DataValidator.isName(request.getParameter("firstName"))){
			request.setAttribute("firstName", "Name must be character ");
			pass= false;
		}
		System.out.println(",,,,,,");
		if(DataValidator.isNull(request.getParameter("lastName"))){
			request.setAttribute("lastName", PropertyReader.getValue("error.require","LastName"));
			pass= false;
		}
		else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("lastName","LastName must be character");
			pass= false;
		}
		if(DataValidator.isNull(request.getParameter("doj"))){
			request.setAttribute("doj", PropertyReader.getValue("error.require", "doj"));
			pass= false;
			
		}
		if(DataValidator.isNull(request.getParameter("qualification"))){
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));
			pass= false;
			
		}else if (!DataValidator.isName(request.getParameter("qualification"))) {
			request.setAttribute("qualification","Enter Qualification");
			pass= false;
			
		}
		if(DataValidator.isNull(request.getParameter("mobileNo"))){
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "MobileNo"));
			pass= false;
			
		}
		else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Enter valid MobileNo");
			pass= false;
		}
		else if(!DataValidator.isPhoneLength(request.getParameter("mobileNo"))){
			request.setAttribute("mobileNo","MobileNo contain 10 digit");
			pass= false;
		}
		if(DataValidator.isNull(request.getParameter("emailId"))){
			request.setAttribute("emailId", PropertyReader.getValue("error.require", "EmailId"));
			pass= false;
			
		}else if(!DataValidator.isEmail(request.getParameter("emailId"))){
			request.setAttribute("emailId", PropertyReader.getValue("error.require", "Enter valid EmailId"));
			pass= false;	
		}
		
		if(DataValidator.isNull(request.getParameter("gender"))){
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass= false;
			
		}
		if(DataValidator.isNull(request.getParameter("collegeId"))){
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));
			pass= false;
			
		}
		if(DataValidator.isNull(request.getParameter("courseId"))){
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "course Name"));
			pass= false;
			
		}
		if(DataValidator.isNull(request.getParameter("subjectId"))){
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));
			pass= false;
		}

		log.debug("facultyCtl validate ended");
	return pass;
		
	}
	
	/**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */
	protected BaseBean populateBean(HttpServletRequest request){
		log.debug("FacultyCtl PopulateBean started");
		
		FacultyBean bean=new FacultyBean();
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setDoj(DataUtility.getDate(request.getParameter("doj")));
		bean.setQualification(DataUtility.getString(request.getParameter("qualification")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
	
		
		populateDTO(bean, request);
		log.debug("FacultyCtl populateBean ended");
		
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		log.debug("FacultyCtl doGet started");
		
		String op=DataUtility.getString(request.getParameter("operation"));
		
		FacultyModel model=new FacultyModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(id>0){
			FacultyBean bean= new FacultyBean();
			try {
				bean=model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
               log.error(e);
               ServletUtility.handleException(e, request, response);
               
				return;
			}
			
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("FacultyCtl doGet ended");
	}
	/**
	 * Submit logic inside it.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		log.debug("FacultyCtl doGet started");
		
		String op=DataUtility.getString(request.getParameter("operation"));
		FacultyModel model= new FacultyModel();
		
		long id=DataUtility.getLong(request.getParameter("id"));
		System.out.println("FacultyCtl ID:<<<"+id);
		

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			FacultyBean bean= (FacultyBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
					//bean.setId(pk);
				}
				
			
				
		}catch (DuplicateRecordException e) {
		      ServletUtility.setBean(bean, request);
			  ServletUtility.setErrorMessage("Login id already exists", request);
			  }
			
			catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} 
				
		} 
		else if (OP_DELETE.equalsIgnoreCase(op)) {

			FacultyBean bean= (FacultyBean) populateBean(request);
			try {
				model.Delete(bean);
				ServletUtility.setSuccessMessage("Record Deleted Successfully...!!!", request);
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;
			} catch (Exception e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} 
		else if(OP_RESET.equalsIgnoreCase(op)){
         
          ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
          return;
		}
		
	
		else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("facultyCtl Method doPostEnded");
	}
	


    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_VIEW;
	}

	
	
	
	

}
