package in.co.rays.project_4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.MarksheetBean;
import in.co.rays.project_4.bean.RoleBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.model.CourseModel;
import in.co.rays.project_4.model.RoleModel;
import in.co.rays.project_4.model.SubjectModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class RoleListCtl.
 */
@WebServlet(name="RoleListCtl",urlPatterns={"/ctl/RoleListCtl"})
public class RoleListCtl extends BaseCtl {

	 /** The log. */
 	private static Logger log = Logger.getLogger(RoleListCtl.class);
	 
	 
 	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
 	protected void preload(HttpServletRequest request){
			log.debug("RoleListCtl preload method started");
			RoleModel model=new RoleModel();
			 
			try {
				List l=model.list();
				
				request.setAttribute("role",l );
			
				
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
    	@Override
	    protected BaseBean populateBean(HttpServletRequest request) {
	        RoleBean bean = new RoleBean();
	       bean.setId(DataUtility.getLong(request.getParameter("role")));
	        bean.setName(DataUtility.getString(request.getParameter("name")));

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
	    @Override
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("RoleListCtl doGet Start");
	        List list = null;
	        int pageNo = 1;
	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
	        RoleBean bean = (RoleBean) populateBean(request);
	        String op = DataUtility.getString(request.getParameter("operation"));
	        RoleModel model = new RoleModel();
	        try {
	            list = model.search(bean, pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0) {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("RoleListCtl doGet End");
	    }

	    /**
    	 * Contains Submit logics.
    	 *
    	 * @param request the request
    	 * @param response the response
    	 * @throws ServletException the servlet exception
    	 * @throws IOException Signals that an I/O exception has occurred.
    	 */
	    @Override
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("RoleListCtl doPost Start");
	        List list = null;
	        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
	        pageNo = (pageNo == 0) ? 1 : pageNo;
	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader
	                .getValue("page.size")) : pageSize;
	        RoleBean bean = (RoleBean) populateBean(request);
	        String op = DataUtility.getString(request.getParameter("operation"));
	        
	        String[] ids = request.getParameterValues("chk_1");

	        RoleModel model = new RoleModel();

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

	            }else if(OP_RESET.equalsIgnoreCase(op)){
	            	ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
	            	return;
	            	
	            }else if(OP_NEW.equalsIgnoreCase(op)){
	            	ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
	            	return;
	            	
	            } else if (OP_DELETE.equalsIgnoreCase(op)) {
	                pageNo = 1;
	                if (ids != null && ids.length > 0) {
	                    RoleBean deletebean = new RoleBean();
	                    for (String id : ids) {
	                        deletebean.setId(DataUtility.getInt(id));
	                        model.delete(deletebean);
	                    }
	                    ServletUtility.setSuccessMessage( "Data successfully deleted", request);
	                } else {
	                    ServletUtility.setErrorMessage(
	                            "Select at least one record", request);
	                }
	              
	                
	            }
	            list = model.search(bean, pageNo, pageSize);
	            ServletUtility.setList(list, request);
	            ServletUtility.setBean(bean, request);
	            if (list == null || list.size() == 0) {
	            	
	            	if(!OP_DELETE.equalsIgnoreCase(op)){
	                ServletUtility.setErrorMessage("No record found ", request);
	            	}
	            }
	            
	            ServletUtility.setList(list, request);

	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);

	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("RoleListCtl doPost End");
	    }


	    /**
		 * Returns the VIEW page of this Controller.
		 *
		 * @return the view
		 */
    	@Override
	    protected String getView() {
	        return ORSView.ROLE_LIST_VIEW;
	    }
	
}
