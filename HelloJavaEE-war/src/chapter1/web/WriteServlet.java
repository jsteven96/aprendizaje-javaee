package chapter1.web;


import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter1.model.MessageException;
import chapter1.model.ModelEJB;

/**
 * Servlet implementation class WriteServlet
 */
@WebServlet("/WriteServlet")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ModelEJB modelEJB;
	
	private static String PUT_MESSAGE = "put_message";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = request.getParameter(PUT_MESSAGE);
		if(message.equals("")) {
			modelEJB.deleteMessage();
		} else {
			try {
				modelEJB.putUserMessage(message);
			} catch (MessageException e) {
				throw new ServletException(e);
			}
		}
		response.sendRedirect("./DisplayServlet");
	}

}
