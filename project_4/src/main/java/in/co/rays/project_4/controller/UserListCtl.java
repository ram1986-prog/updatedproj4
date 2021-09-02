package in.co.rays.project_4.controller;



import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.UserBean;
import in.co.rays.project_4.model.RoleModel;
import in.co.rays.project_4.model.UserModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class UserListCtl.
 */
/**
 * @author Himshikha
 *
 */
@WebServlet(name="UserListCtl",urlPatterns={"/ctl/UserListCtl"})
public class UserListCtl extends BaseCtl {

	
 	private static Logger log = Logger.getLogger(UserListCtl.class);

	 
 	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
 	@Override
		protected void preload(HttpServletRequest request) {
			System.out.println("UserCtl preload method");
			RoleModel model = new RoleModel();
  			try {
				List l = model.list();
				System.out.println("UserListCtl prelode:"+ l);
				request.setAttribute("roleList", l);
			} catch (Exception e) {
				log.error(e);
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
	        UserBean bean = new UserBean();

	        bean.setFirstName(DataUtility.getString(request
	                .getParameter("firstName")));

	        bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

	        bean.setLogin(DataUtility.getString(request.getParameter("login")));
	        
	        bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
	        
	        bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
	        
	       bean.setGender(DataUtility.getString(request.getParameter("gender")));

	      
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
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        log.debug("UserListCtl doGet Start");
	        List list = null;
	        int pageNo = 1;
	        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
	        UserBean bean = (UserBean) populateBean(request);
	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get the selected checkbox ids array for delete list
	        String[] ids = request.getParameterValues("ids");
	        UserModel model = new UserModel();
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
	        } catch (Exception e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }
	        log.debug("UserListCtl doPOst End");
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
	        log.debug("UserListCtl doPost Start");
	        List list = null;
	        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

	        pageNo = (pageNo == 0)?1 : pageNo;
	        pageSize = (pageSize == 0)?DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
	        
	        UserBean bean = (UserBean) populateBean(request);
	        
	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get the selected checkbox ids array for delete list
	        String[] ids = request.getParameterValues("ids");
	        UserModel model = new UserModel();
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
	                ServletUtility.redirect(ORSView.USER_CTL, request, response);
	                return;
	            } 
	            else if(OP_RESET.equalsIgnoreCase(op)){
	            	ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
	            	return;
	            }
	            else if (OP_DELETE.equalsIgnoreCase(op)) {
	                pageNo = 1;
	                if (ids != null && ids.length > 0) {
	                    UserBean deletebean = new UserBean();
	                    for (String id : ids) {
	                        deletebean.setId(DataUtility.getInt(id));
	                        model.delete(deletebean);
	                        ServletUtility.setSuccessMessage( "Data successfully deleted", request);
	                    }
	                   
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
		
		 return ORSView.USER_LIST_VIEW;
	}
	

}
