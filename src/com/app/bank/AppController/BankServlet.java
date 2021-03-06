package com.app.bank.AppController;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.bank.pojo.SavingsAccount;
import com.app.bank.service.BankAccountService;

/**
 * Servlet implementation class BankServlet
 */
@WebServlet("*.app")
public class BankServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public BankServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	private BankAccountService service = new BankAccountService();
	double balance;
	Collection<SavingsAccount> bank1;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {

		case "/viewAllSav.app":
			Collection<SavingsAccount> bank = service.showSavings();
			request.setAttribute("bank", bank);
			RequestDispatcher dispatcher = request.getRequestDispatcher("viewAllSav.jsp");
			dispatcher.forward(request, response);
			break;
			
		case "/withdraw1.app":
			bank1 = service.showSavings();
			int accountNumber=Integer.parseInt(request.getParameter("accno"));
			double withdrawAmount=Double.parseDouble(request.getParameter("amount"));
//			String redirect="";
			for(SavingsAccount account1: bank1)
			{
				if(account1.getAccountNo()==accountNumber)
				{
					 balance = service.withdraw(accountNumber,withdrawAmount);
					 System.out.println(balance);
					 request.setAttribute("banker", bank1);
					 //redirect="";	 
				}
				dispatcher = request.getRequestDispatcher("withdraw2.jsp");
				 dispatcher.forward(request, response);
				 break;
			}
			
		case "/deposit1.app":
			bank1 = service.showSavings();
			accountNumber=Integer.parseInt(request.getParameter("accno"));
			double depositAmount=Double.parseDouble(request.getParameter("amount"));
			String redirect="";
			for(SavingsAccount account1: bank1)
			{
				if(account1.getAccountNo()==accountNumber)
				{
						balance=service.deposit(accountNumber,depositAmount);
						 System.out.println(balance);
					 request.setAttribute("banker", bank1);
					 redirect="deposit2.jsp";
				}
			}
			dispatcher = request.getRequestDispatcher(redirect);
			dispatcher.forward(request, response);
			break;
		/*case "/deposit2.app":
			break;
		case "/fundTransfer1.app":
			break;
		case "/fundTransfer2.app":
			break;*/
			
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}