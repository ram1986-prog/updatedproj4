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
import in.co.rays.project_4.bean.TimeTableBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.exception.DuplicateRecordException;

import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.SubjectModel;
import in.co.rays.project_4.model.TimeTableModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class TimeTableCtl.
 */
@WebServlet(name="TimeTableCtl", urlPatterns="/ctl/TimeTableCtl")
public class TimeTableCtl extends BaseCtl{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(UserCtl.class);
	
	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	protected void preload(HttpServletRequest request){
		CourseModel cmodel= new CourseModel();
	SubjectModel smodel=new SubjectModel();
		
		try {
			List clist=cmodel.list();
			List slist=smodel.list();
			
			request.setAttribute("courselist", clist);
			request.setAttribute("subjectlist", slist);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	protected boolean validate(HttpServletRequest request){
		log.debug("TimeTableCtl validate started");
		boolean pass=true;
		if(DataValidator.isNull(request.getParameter("courseId"))){

			 request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
			
			 pass=false;
			
		}
		if(DataValidator.isNull(request.getParameter("subjectId"))){
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "subject Name"));
			pass=false;
		}
		if(DataValidator.isNull(request.getParameter("semester"))){
			request.setAttribute("semester", PropertyReader.getValue("error.require", "semester"));
			pass=false;
		}
		 if(DataValidator.isNull(request.getParameter("examTime")))
		 {
			 request.setAttribute("examTime", PropertyReader.getValue("error.require", "Exam Time"));
				
			 pass=false;
		 }
		 
		 if(DataValidator.isNull(request.getParameter("examDate")))
		 {
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Exam Date"));
		
			pass=false;
		 } 
		 
		 log.debug("time table ctl validate debug completed");
		
		
		return pass;
		
	}
	
	/**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */
	protected BaseBean populateBean(HttpServletRequest request)
	 {
		 log.debug("time table ctl populatebean debug completed");
		 
		 TimeTableBean bean=new TimeTableBean();
		 
		 bean.setId(DataUtility.getLong(request.getParameter("id")));
		 
		// bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		 bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		 
		 bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		 
	     bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
	     bean.setSemester(DataUtility.getString(request.getParameter("semester")));
		 
		 bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
		
		 bean.setExamTime(DataUtility.getString(request.getParameter("examTime")));
		
		 populateDTO(bean, request);
		 
		 log.debug("time table ctl populatebean debug completed");
		 
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
 	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	 {
		 log.debug("time table ctl doget debug completed");
		 
		 String op=DataUtility.getString(request.getParameter("operation"));
		 System.out.println("hi timetable doget"+op);
		 
		 TimeTableModel model=new TimeTableModel();
		 
		 long id=DataUtility.getLong(request.getParameter("id"));
		 System.out.println("hi timetable doget"+id);
		if(id>0)
		{
			TimeTableBean bean=null;
			try {
				bean=model.findByPk(id);
				//System.out.println("id in time table do get"+bean.getId());
				ServletUtility.setBean(bean, request);
			
			
			} 
			catch (Exception e) 
			{
           e.printStackTrace();
	            //log.error(e);
	           // ServletUtility.handleException(e, request, response);
	            return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("time table ctl doget debug completed");
	 }
	 
 	/**
	 * Submit logic inside it.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
 	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	 {
		 log.debug("time table ctl dopost debug completed");
		 
		 String op=DataUtility.getString(request.getParameter("operation"));
		 
		 System.out.println("hi dopost in timetable ctl"+op);
		 
		 TimeTableModel model=new TimeTableModel();
		 
		 long id=DataUtility.getLong(request.getParameter("id"));
		 
		 System.out.println("hi dopost in timetable ctl"+id);
		 
		 if(OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op))
		 {
			 
			 TimeTableBean bean=(TimeTableBean)populateBean(request);
			 try{
			     if(id>0)
			 {
				  model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data updated successfully", request);
			
			 }else{
				 long pk=0;
				  pk=model.add(bean);
				 ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
					//ServletUtility.forward(getView(), request, response);
					//bean.setId(pk);
					

			 }
			 }
			 catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			    catch(DuplicateRecordException e){
			    	ServletUtility.setBean(bean, request);
					  ServletUtility.setErrorMessage("record is already exists", request);
			    	
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
		 } 
		 else if(OP_DELETE.equalsIgnoreCase(op))
		 {
			 TimeTableBean bean = (TimeTableBean) populateBean(request);
	         try 
	         {
	             model.delete(bean);
	             ServletUtility.setSuccessMessage("Record Deleted Successfully...!!!", request);
	             ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request,response);
	             return;
	          } 
	         catch (Exception e) 
	         {
	        	 e.printStackTrace();
	             //log.error(e);
	             ServletUtility.handleException(e, request, response);
	             return;
	         }

		 }
		 else if(OP_CANCEL.equalsIgnoreCase(op))
		 {
		  ServletUtility.redirect(ORSView.TIME_TABLE_LIST_CTL, request, response);
		  return;
		 }
		 else if(OP_RESET.equalsIgnoreCase(op))
		 {
		  ServletUtility.redirect(ORSView.TIME_TABLE_CTL, request, response);
		  return;
		 }
		 ServletUtility.forward(getView(), request, response);
		 
		 log.debug("time table ctl dopost debug completed");
	 
	 }



    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIME_TABLE_VIEW;
	}

}
