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
import in.co.rays.project_4.bean.StudentBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.model.MarksheetModel;
import in.co.rays.project_4.model.StudentModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class MarksheetListCtl.
 */
@ WebServlet(name="MarksheetListCtl",urlPatterns={"/ctl/MarksheetListCtl"})

public class MarksheetListCtl extends BaseCtl
	{
	  
  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The log. */
  	private static Logger log = Logger.getLogger(MarksheetListCtl.class);
	  
  	/**
	 * Loads list and other data required to display at HTML form.
	 *
	 * @param request the request
	 */
  	@Override
  		protected void preload(HttpServletRequest request)
  		{
		  
  		  MarksheetModel model=new MarksheetModel();
		  StudentBean sbean=new StudentBean();
		  StudentModel smodel=new StudentModel();
		  
		 
		try {
			List slist=smodel.list();
			request.setAttribute("studentList", slist);
				//	 System.out.println("rollno List :"+l);
			} catch (ApplicationException e) 
			{
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
    	protected BaseBean populateBean(HttpServletRequest request)
  		{
  			MarksheetBean bean = new MarksheetBean();
         	       
	        bean.setRollNo(DataUtility.getString(request.getParameter("RollNo")));
            bean.setName(DataUtility.getString(request.getParameter("name")));
	        bean.setStudentId(DataUtility.getInt(request.getParameter("subjectId")));
	       
	        return bean;
	    }

	    /**
    	 * ContainsDisplaylogics.
    	 *
    	 * @param request the request
    	 * @param response the response 
    	 * @throws ServletException the servlet exception
    	 * @throws IOException Signals that an I/O exception has occurred.
    	 */
    	@Override
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException 
    		{
	        
	    	//int pageNo=1;
    		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

	        pageNo = (pageNo == 0) ? 1 : pageNo;

	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

	        MarksheetBean bean = (MarksheetBean) populateBean(request);

	        List list = null;
	        
	        MarksheetModel model = new MarksheetModel();
	     
	        try
	        {
	          list = model.search(bean, pageNo, pageSize);
	        } catch (ApplicationException e) 
	        {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            
	            return;
	        }

	        if (list == null || list.size() == 0)
	        {
	            ServletUtility.setErrorMessage("No record found ", request);
	        }
	        ServletUtility.setList(list, request);
	        ServletUtility.setPageNo(pageNo, request);
	        ServletUtility.setPageSize(pageSize, request);
	        ServletUtility.forward(getView(), request, response);
	        log.debug("MarksheetListCtl doGet End");

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
	            HttpServletResponse response) throws ServletException, IOException
	    	{

	        log.debug("MarksheetListCtl doPost Start");

	        List list = null;

	        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

	        
	        pageNo = (pageNo == 0) ? 1 : pageNo;
	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

	        MarksheetBean bean = (MarksheetBean) populateBean(request);

	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get the selected checkbox ids array for delete list
	        String[] ids = request.getParameterValues("ids");

	        MarksheetModel model = new MarksheetModel();

	        try {

	            if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op))
	            {

	                if (OP_SEARCH.equalsIgnoreCase(op))
	                {
	                  pageNo = 1;
	                } else if (OP_NEXT.equalsIgnoreCase(op))
	                {
	                  pageNo++;
	                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1)
	                {
	                  pageNo--;
	                }

	            }
	            //
	            else if(OP_RESET.equalsIgnoreCase(op))
	            {
                	ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
                	
                	return;
                }
	            
	            //new button from marksheet list view will direct to controller of respected view where add 
	           // operation is written in view with condition (id>0 else part)
	            else if (OP_NEW.equalsIgnoreCase(op))
	            {
	                ServletUtility.redirect(ORSView.MARKSHEET_CTL, request,response);
	                                
	                return;
	            } 
	            
	            //delete operation from the marksheet list view delete button will take to select atleast on record from checkbox.
	            else if (OP_DELETE.equalsIgnoreCase(op)) {
	                pageNo = 1;
	                if (ids != null && ids.length > 0) {
	                    MarksheetBean deletebean = new MarksheetBean();
	                    for (String id : ids) {
	                        deletebean.setId(DataUtility.getInt(id));
	                        model.delete(deletebean);
	                    }
	                    ServletUtility.setSuccessMessage( "Data successfully deleted", request);
	                } else {
	                    ServletUtility.setErrorMessage("Select at least one record", request);
	                }
	            }
	            System.out.println("id in bean is:["+bean.getId()+"]");
	            
 	            list = model.search(bean, pageNo, pageSize);
	            
 	            ServletUtility.setList(list, request);
	            if (list == null || list.size() == 0)
	              {
	            	if(!OP_DELETE.equalsIgnoreCase(op)){
		                ServletUtility.setErrorMessage("No record found ", request);}
		          }
	            ServletUtility.setBean(bean, request);
	            ServletUtility.setList(list, request);
	            ServletUtility.setPageNo(pageNo, request);
	            ServletUtility.setPageSize(pageSize, request);
	            ServletUtility.forward(getView(), request, response);
	        } catch (ApplicationException e) {
	            log.error(e);
	            ServletUtility.handleException(e, request, response);
	            return;
	        }

	        log.debug("MarksheetListCtl doPost End");
	    }


	    /**
		 * Returns the VIEW page of this Controller.
		 *
		 * @return the view
		 */
    	@Override
	    protected String getView() {
	        return ORSView.MARKSHEET_LIST_VIEW;
	    }

}
 