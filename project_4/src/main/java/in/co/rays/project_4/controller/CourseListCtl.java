package in.co.rays.project_4.controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mysql.jdbc.DatabaseMetaDataUsingInfoSchema;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.CourseBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.RoleModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class CourseListCtl.
 */
@WebServlet(name="CourseListCtl", urlPatterns="/ctl/CourseListCtl")
public class CourseListCtl extends BaseCtl {
	
	/** The log. */
	private static Logger log=Logger.getLogger(CourseListCtl.class);
	
	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
 	protected void preload(HttpServletRequest request){
			log.debug("RoleListCtl preload method started");
			CourseModel model=new CourseModel();
			 
			try {
				List l=model.list();
				
				request.setAttribute("course",l );
			
				
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
	protected BaseBean populateBean(HttpServletRequest request){
		System.out.println("CourseListCtl populatBean started");
		log.debug("CourseListCtl populatBean started");
	
		CourseBean bean= new CourseBean();
		//bean.setCourseName(DataUtility.getStringData(request.getParameter("course")));
		bean.setDescription(DataUtility.getStringData(request.getParameter("description")));
		bean.setId(DataUtility.getLong(request.getParameter("course")));
		System.out.println("kkkkkkkkkk"+bean.getId());
		log.debug("CourseListCtl populateBean method ended");
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
		log.debug("CourseListCtl doGet method started");
		System.out.println("CourseListCtl doGet method started");
		
		List list=null;
		int pageNo=1;
		int pageSize=DataUtility.getInt(PropertyReader.getValue("page.size"));
		CourseBean bean=(CourseBean) populateBean(request);
		CourseModel model = new CourseModel();
		
		
		String op=DataUtility.getString(request.getParameter("operation"));
		String[] ids= request.getParameterValues("ids");
		
		
		try {
			
		
		
		try {
			list= model.search(bean, pageNo, pageSize);
			ServletUtility.setList(list, request);
			ServletUtility.setBean(bean, request);
		} catch (Exception e) {
			throw new ApplicationException("Exception in Course doGet");
			
			
			
		}
		if(list==null || list.size()==0){
            ServletUtility.setErrorMessage("no record found", request);
             request.setAttribute("next", list);
             
		}
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
	         log.error(e);
	         ServletUtility.handleException(e, request, response);
	         return;
		}
		log.debug("CourseListCtl do get method ended");
		
	}
	
	/**
	 * Submit logic inside it.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException{
		log.debug("courseListCtl doPost method started");
		System.out.println("courseListCtl doPost method started");
		  List list= null;
		   int pageNo= DataUtility.getInt(request.getParameter("pageNo"));
		   int pageSize= DataUtility.getInt(request.getParameter("pageSise"));
		   
		   pageNo=(pageNo==0)?1:pageNo;
		   pageSize=(pageSize==0)?DataUtility.getInt(PropertyReader.getValue("page.size")):pageSize;
		   
		   CourseBean bean= (CourseBean) populateBean(request);
		   String op=DataUtility.getString(request.getParameter("operation"));
		   
		   String[] ids=request.getParameterValues("ids");
		   CourseModel model= new CourseModel();
		   
		   try {

	            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
	                    || "Previous".equalsIgnoreCase(op)) {

	                if (OP_SEARCH.equalsIgnoreCase(op)) {
	                    pageNo = 1;
	                } else if (OP_NEXT.equalsIgnoreCase(op)) {
	                    pageNo++;
	                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
	                    pageNo--;
	                }

	            } else if (OP_NEW.equalsIgnoreCase(op)) {
	                ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
	                return;
	            } 
	            else if(OP_RESET.equalsIgnoreCase(op)){
	            	ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
	            	return;
	            }
	            else if (OP_DELETE.equalsIgnoreCase(op)) {
	                pageNo = 1;
	                if (ids != null && ids.length > 0) {
	                   CourseBean deletebean = new CourseBean();
	                    for (String id : ids) {
	                        deletebean.setId(DataUtility.getInt(id));
	                        model.delete(deletebean);
	                    }
	                    ServletUtility.setSuccessMessage( "Data successfully deleted", request);
	                } else {
	                    ServletUtility.setErrorMessage("Select at least one record", request);
	                }
	            }
	         
	            list = model.search(bean, pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            
	            if (list == null || list.size() == 0) {
	            	if(!OP_DELETE.equalsIgnoreCase(op)){
	                ServletUtility.setErrorMessage("No record found ", request);}
	                
	            }
	            ServletUtility.setBean(bean, request);
	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);

	        } catch (Exception e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("UserListCtl doGet End");
	}


    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.COURSE_LIST_VIEW;
	}
	
}
