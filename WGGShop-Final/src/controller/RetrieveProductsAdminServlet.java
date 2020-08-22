package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import utility.AESHelper;
import utility.CookieHelper;
import utility.SingletonDB;

/**
 * Servlet implementation class RetrieveProductsServlet
 */
@WebServlet({"/retrieveproducts.action", "/admin"})
public class RetrieveProductsAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println(CookieHelper.getCookie(request.getCookies(), "user").getValue());
		if((CookieHelper.getCookie(request.getCookies(), "user") != null) &&AESHelper.decrypt(CookieHelper.getCookie(request.getCookies(), "user").getValue()).equals(getServletContext().getInitParameter("adminUser"))) {
		Connection connection = SingletonDB.getConnection(getServletContext());
		//ArrayList<Product> products = new ProductController().getAllProducts(connection);
		Iterator<Product> products = new ProductController().getProductIterator(connection);
		request.setAttribute("products", products);
		request.getRequestDispatcher("index-admin.jsp").forward(request,response);
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
