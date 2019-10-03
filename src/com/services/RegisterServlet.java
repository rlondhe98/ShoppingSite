package com.services;
import com.dao.CustomerDaoImpl;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.model.Customer;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CustomerDaoImpl customerdao = new CustomerDaoImpl();
    private Customer customer = null;
    private RequestDispatcher rd= null;
    private PrintWriter out = null;
    public RegisterServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username= request.getParameter("uname");
		String password= request.getParameter("pass");
		String rpassword= request.getParameter("rpass");
		
		if(password.equals(rpassword))
		{
			customer = new Customer(username, password);
			boolean isCreated=customerdao.createCustomer(customer);
			if (isCreated) {
				rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			}
			else {
				out= response.getWriter();
				out.println("<b>Password doesnt match</b>");
				rd = request.getRequestDispatcher("Register.jsp");
				rd.include(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
