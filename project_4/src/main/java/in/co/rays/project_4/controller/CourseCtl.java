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
import in.co.rays.project_4.exception.DuplicateRecordException;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.StudentModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class CourseCtl.
 */
@WebServlet(name="CourseCtl",urlPatterns="/ctl/CourseCtl")
public class CourseCtl extends BaseCtl{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID=1L;
	
	/** The log. */
	private static Logger log=Logger.getLogger(CourseCtl.class);
	
	
	
	
	
	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
    protected boolean validate(HttpServletRequest request){
    	log.debug("courseCtl debug statrd in validate");
    	
    	boolean pass=true;
    	if(DataValidator.isNull(request.getParameter("courseName"))){
    		request.setAttribute("courseName", PropertyReader.getValue("error.require","course name"));
    	   pass= false;
    	}
        else if (!DataValidator.isName(request.getParameter("courseName"))) {
        	request.setAttribute("courseName","course name must be character");
     	   pass= false;
		}
    	if(DataValidator.isNull(request.getParameter("description"))){
    		request.setAttribute("description", PropertyReader.getValue("error.require", "description"));
            pass=false;    		
    	}
    	log.debug("courseCtl debug ended in validate");
		return pass;
    	
    }
    
    /**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */
    protected BaseBean populateBean(HttpServletRequest request){
    	log.debug("courseCtl debug started in populateBean");
    	System.out.println("courseCtl debug started in populateBean");
    	CourseBean bean=new CourseBean();
    	bean.setId(DataUtility.getLong(request.getParameter("id")));
    	bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
    	bean.setDescription(DataUtility.getString(request.getParameter("description")));
    	populateDTO(bean, request);
    	log.debug("courseCtl ended in populateBean");
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
    	log.debug("courseCtl doGet Method started");
    	System.out.println("courseCtl doGet Method started");
    	 String op=DataUtility.getString(request.getParameter("operation"));
    	 CourseModel model=new CourseModel();
    	 long id=DataUtility.getLong(request.getParameter("id"));
    	 if(id>0){
    		 CourseBean bean;
    		 try {
    			 bean =model.findByPk(id);
        		 ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				// TODO: handle exception
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
				
			}
    		 
    	 }
    	ServletUtility.forward(getView(), request, response);
    	log.debug("courseCtl doGet method ended");
    }
	
    /**
	 * Submit logic inside it.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		log.debug("CourseCtl doPost method started");
		System.out.println("CourseCtl doPost method started");
		String op= DataUtility.getString(request.getParameter("operation"));
		CourseModel model=new CourseModel();
		
		long id=DataUtility.getLong(request.getParameter("id"));
		System.out.println("CourseCtl doPost method id: "+ id);
		
		if(OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)){
			CourseBean bean=(CourseBean) populateBean(request);
			if(id>0){
				try {
					 model.update(bean);
					 System.out.println("hi");
						ServletUtility.setBean(bean, request);
						ServletUtility.setSuccessMessage("Data Successfully Updated....!!!", request);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}else{
				long pk=0;
				try {
					pk= model.add(bean);
					ServletUtility.setSuccessMessage("Data is saved sucessfully", request);
					ServletUtility.forward(getView(), request, response);
					return;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				//bean.setId(pk);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("courseName is alrady exist", request);
				
			}
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			CourseBean bean=(CourseBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
				return;
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		else if(OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}
		/*else if(OP_RESET.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.COURSE_CTL, request, response);  
			return;
		}*/
		ServletUtility.forward(getView(), request, response);
		log.debug("courseCtl doPost debug completed");
		
	}
     
    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_VIEW;
	}	

}
