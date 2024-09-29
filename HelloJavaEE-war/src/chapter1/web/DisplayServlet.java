package chapter1.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter1.model.MessageException;
import chapter1.model.ModelEJB;

/**
 * Servlet implementation class DisplayServlet
 */
@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ModelEJB modelEJB;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println(" <head>");
			out.println("  <title>Hello Java EE</title>");
			out.println(" </head>");
			out.println(" <body>");
			out.println(" 	<br>");
			out.println(" 	<div align = 'center'>");
			out.println(" 	<h2>Hello Java EE </h2>");
			out.println(" 	Enter a message for JavaEE which will pass"
					+ "     through the web tier, the EJB tier to the database"
					+ "     and back again!");
			out.println(" <br><br><br>");
			out.println(" <form action='./WriteServlet' method='POST'>");
			out.println(" <input type='submit' value='Enter'>");
			out.println(" <input type='text' name='put_message'>");
			out.println(" </form>");
			out.println(" <br>");
			String displayMessage = "";
			try {
				String storedMessage = modelEJB.getStoredMessage();
				displayMessage = "Hello from (" + storedMessage + "), inside a web component";
			} catch (MessageException e) {
				displayMessage = "you should enter a value...";
			}
			out.println(" The current message from JavaEE: <br><b>" + displayMessage + "</b>");
			out.println(" </div>");
			out.println(" </body>");
			out.println("</html>");
			
		} finally {
			out.close();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
