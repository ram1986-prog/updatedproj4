package in.co.rays.project_4.controller;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_4.util.ServletUtility;

@WebServlet(name="WelcomeCtl",urlPatterns={"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** The log. */
    private static Logger log = Logger.getLogger(WelcomeCtl.class);

    /**
     * Do get.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("i am in welcomeCtl Do Get Started");
    	
        log.debug("WelcomeCtl Method doGet Started");

        ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);

        log.debug("WelcomeCtl Method doGet Ended");
        System.out.println("i am in welcomeCtl Do Get Ended ");
    }

	

    /**
	 * Returns the VIEW page of this Controller.
	 *
	 * @return the view
	 */
	@Override
	protected String getView() {
		System.out.println("i am in welcome getView");
		// TODO Auto-generated method stub
		return null;
	}

}
