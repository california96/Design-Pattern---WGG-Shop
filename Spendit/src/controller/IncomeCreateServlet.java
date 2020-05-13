package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import model.Expense;
import model.Income;
import model.User;
import utility.DBConnection;

/**
 * Servlet implementation class ExpenseCreateServlet
 */
@WebServlet("/createincome.action")
public class IncomeCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int categoryID = Integer.parseInt(request.getParameter("categoryID"));
		double amount = Double.parseDouble(request.getParameter("amount"));	
		String date = request.getParameter("date");
		String comment = request.getParameter("comment");
		
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("user");
		Connection connection = DBConnection.getConnection(getServletContext());
		if(StringUtils.isBlank(comment)) {
			response.sendRedirect("400.jsp");
			return;
		}
		IncomeOperations inOps = new IncomeOperations();
		if(inOps.insert(connection, user.getUserID(), categoryID, amount, date, comment)) {
			ArrayList<Income> income = inOps.getAllIncome(connection, user.getUserID());
			request.setAttribute("income", income);
			request.getRequestDispatcher("incomeindex.jsp").forward(request, response);
		}
	}

}
