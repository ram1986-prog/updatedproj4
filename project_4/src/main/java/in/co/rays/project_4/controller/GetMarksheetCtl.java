package in.co.rays.project_4.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.bean.BaseBean;
import in.co.rays.project_4.bean.MarksheetBean;
import in.co.rays.project_4.exception.ApplicationException;
import in.co.rays.project_4.model.MarksheetModel;
import in.co.rays.project_4.util.DataUtility;
import in.co.rays.project_4.util.DataValidator;
import in.co.rays.project_4.util.PropertyReader;
import in.co.rays.project_4.util.ServletUtility;

/**
 * The Class GetMarksheetCtl.
 */

@WebServlet(name="GetMarksheetCtl",urlPatterns={"/ctl/GetMarksheetCtl"})

public class GetMarksheetCtl  extends BaseCtl{

	/** The log. */
	private static Logger log = Logger.getLogger(GetMarksheetCtl.class);

	/**
	 * Validates input data entered by User.
	 *
	 * @param request the request
	 * @return true, if successful
	 */
    @Override
    protected boolean validate(HttpServletRequest request) {

        log.debug("GetMarksheetCTL Method validate Started");

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("rollNo"))) {
            request.setAttribute("rollNo",
                    PropertyReader.getValue("error.require", "Roll Number"));
            pass = false;
        }
        else if (!DataValidator.isRollNo(request.getParameter("rollNo"))) {
        	request.setAttribute("rollNo","Enter valid  Roll Number");
            pass = false;
		}

        log.debug("GetMarksheetCTL Method validate Ended");

        return pass;
    }

    /**
	 * Populates bean object from request parameters.
	 *
	 * @param request the request
	 * @return the base bean
	 */    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        log.debug("GetMarksheetCtl Method populatebean Started");

        MarksheetBean bean = new MarksheetBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));

        bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));

        bean.setName(DataUtility.getString(request.getParameter("name")));

        bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));

        bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

        bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

        log.debug("GetMarksheetCtl Method populatebean Ended");
        populateDTO(bean, request);

        return bean;
    }

    /**
     * Concept of Display method.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Concept of Submit Method.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        log.debug("GetMarksheetCtl Method doGet Started");
        String op = DataUtility.getString(request.getParameter("operation"));

        // get model
        MarksheetModel model = new MarksheetModel();

        MarksheetBean bean = (MarksheetBean) populateBean(request);

        long id = DataUtility.getLong(request.getParameter("id"));

        try {

        	if (OP_GO.equalsIgnoreCase(op) || OP_RESET.equalsIgnoreCase(op)) {

                if (OP_GO.equalsIgnoreCase(op)) {
                	  {

                         try {
                             bean = model.findByRollNo(bean.getRollNo());
                             if (bean != null) {
                                 ServletUtility.setBean(bean, request);
                                 ServletUtility.forward(getView(), request, response);
                             } else {
                                 ServletUtility.setErrorMessage("RollNo Does Not exists",
                                         request);
                                 ServletUtility.forward(getView(), request, response);
                             }
                         } catch (Exception e) {           //ApplicationException
                             log.error(e);
                             ServletUtility.handleException(e, request, response);
                             return;
                         }
                	  }}

             
        	else if(OP_RESET.equalsIgnoreCase(op)){
        		ServletUtility.redirect(ORSView.GET_MARKSHEET_CTL, request, response);
        		return;
        	}}}
                catch (Exception e) {           //ApplicationException
                    log.error(e);
                    ServletUtility.handleException(e, request, response);
                    return;
                }
                log.debug("Marksheetctl method doget ended");
        	}
        
        
        
        
        
        
	/*
	 * if (OP_GO.equalsIgnoreCase(op)) {
	 * 
	 * try { bean = model.findByRollNo(bean.getRollNo()); if (bean != null) {
	 * ServletUtility.setBean(bean, request); ServletUtility.forward(getView(),
	 * request, response); } else {
	 * ServletUtility.setErrorMessage("RollNo Does Not exists", request);
	 * ServletUtility.forward(getView(), request, response); } } catch (Exception e)
	 * { //ApplicationException log.error(e); ServletUtility.handleException(e,
	 * request, response); return; }
	 * 
	 * }
	 * 
	 * log.debug("MarksheetCtl Method doGet Ended"); }
	 */


    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
    @Override
    protected String getView() {
        return ORSView.GET_MARKSHEET_VIEW;
    }
}
