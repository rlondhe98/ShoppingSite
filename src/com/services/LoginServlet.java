package com.services;
import com.utilities.ConnectionManager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import com.model.Customer;
import com.dao.CustomerDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.utilities.*;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Customer customer = null;
    private CustomerDaoImpl custDao = new CustomerDaoImpl();
    private RequestDispatcher rd = null;
    private PrintWriter out = null;
    private HttpSession session = null;
    public LoginServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("uname");
		String password=request.getParameter("pass");
		customer = new Customer(username, password);
		boolean isValid = custDao.validateCustomer(customer);
		if(isValid) {
			session = request.getSession();
			session.setAttribute("customer", customer);
			rd = request.getRequestDispatcher("Profile.jsp");
			rd.forward(request, response);
		}
		else
		{
			out = response.getWriter();
			out.println("<b>Invalid Credentials</b>");
			rd = request.getRequestDispatcher("Login.jsp");
			rd.include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
