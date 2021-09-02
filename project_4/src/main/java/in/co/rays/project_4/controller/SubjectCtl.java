package in.co.rays.project_4.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.SubjectBean;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.SubjectModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class SubjectCtl.
 */
@WebServlet(urlPatterns="/ctl/SubjectCtl")
public class SubjectCtl extends BaseCtl {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID=1L;
	
	/** The log. */
	private static Logger log=Logger.getLogger(SubjectCtl.class);
    
	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
	protected void preload(HttpServletRequest request){
		
	
		
		try {
			CourseModel model= new CourseModel();
	       List l=model.list();
			System.out.println("SubjectCtl :"+ l);
			request.setAttribute("courseList", l);
		} catch (Exception e) {
			log.error(e);
			// TODO: handle exception
		}
		}
	
	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
	protected boolean validate(HttpServletRequest request){
		log.debug("SubjectCtl validate method started");
      boolean pass=true;

   if(DataValidator.isNull(request.getParameter("subjectName"))){
	   request.setAttribute("subjectName", PropertyReader.getValue("error.require","SubjectName"));
	   pass=false;
   }
   else if (!DataValidator.isName(request.getParameter("subjectName"))) {
	   request.setAttribute("subjectName","SubjectName must be character ");
	   pass=false;
}
   if(DataValidator.isNull(request.getParameter("courseId"))){
	   request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));
	   pass=false;
	   
	   
	   
   }
   if(DataValidator.isNull(request.getParameter("description"))){
	   request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
	   pass=false;
	   
   }
   log.debug("SubjectCtl validate ended");
   
   
		return pass;
		
	}
	
	/**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */
	protected BaseBean populateBean(HttpServletRequest request){
		log.debug("subjectCtl populateBean started");
		SubjectBean bean=new SubjectBean();
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseId")));
		
		bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
        bean.setDescription(DataUtility.getString(request.getParameter("description")));

		populateDTO(bean, request);
		log.debug("SubjectCtl populateBean ended");
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
		log.debug("SubjectCtl doGet method started");
		String op=DataUtility.getString(request.getParameter("operation"));
		
		SubjectBean bean=null;
		SubjectModel model= new SubjectModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(id>0){
			try {
				bean=model.findByPk(id);
				ServletUtility.setBean(bean, request);
				
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("subjectCtl doGet completed");
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
		log.debug("SubjectCtl doPodt Method started");
		String op=DataUtility.getString(request.getParameter("operation"));
		
		SubjectModel model= new SubjectModel();
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)){              //|| OP_UPDATE.equalsIgnoreCase(op)
			SubjectBean bean=(SubjectBean) populateBean(request);
			System.out.println(" SubjectCtl doPost subject bean :"+bean);
			if(id>0){
				try {
					model.Update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("You have Successfully Updated record", request);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			else{
				try {
					long pk=0;
					
					pk=model.add(bean);
					 ServletUtility.setBean(bean, request);
					System.out.println("SubjectCtl ");
					ServletUtility.setSuccessMessage("record have been saved successfully ", request);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		else if(OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op))
		{
			System.out.println("reset");
		ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
		return;
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("subject ctl dopost debug completed");
	}
	

    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_VIEW;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 

}
