package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import utility.DBConnection;
import utility.Mailer;
import utility.BCrypt;

/**
 * Servlet implementation class ProcessForgotPasswordServlet
 */
@WebServlet("/processpassword.action")
public class ProcessForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		UserOperations userOps = new UserOperations();
		ServletContext context = getServletContext();
		Connection connection = DBConnection.getConnection(getServletContext());
		if(userOps.checkEmail(connection, email)) {
			System.out.println("Yes!");
			String newPass = userOps.generatePassword();
			System.out.println(newPass);
			userOps.updatePassword(connection, email, BCrypt.hashpw(newPass, BCrypt.gensalt()));
			Mailer mailer = new Mailer(context);
			String messageBody = "<h2>Hello!</h2>\n"
					+ "<p>We have received your request to change your password. Your new password is <b>" + newPass + "</b>\n";
			mailer.sendEmail(context.getInitParameter("MailerEmail"), context.getInitParameter("MailerPass"), email, "Change Password Request", messageBody);
		}
		else {
			System.err.println("No!");
		}
	}

}
