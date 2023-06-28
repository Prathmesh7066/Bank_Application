package com.bankapplication.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankapplication.helper.Helper;

@WebServlet("/loginvalidate")
public class CustomerLogin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String accountnumber = req.getParameter("accountnumber");
		String pin = req.getParameter("pin");

		long accountnumber1 = Long.parseLong(accountnumber);
		int pin2 = Integer.parseInt(pin);

		HttpSession hs = req.getSession();
		hs.setAttribute("accountnumber", accountnumber1);
		hs.setAttribute("pin", pin2);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = Helper.getConection();

			PreparedStatement ps = conn.prepareStatement("select * from account where accountnumber = ? and pin = ?");
			ps.setLong(1, accountnumber1);
			ps.setInt(2, pin2);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				PrintWriter pout = resp.getWriter();
				pout.println("<h1 align = 'center'>Login Succsessfully</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("customeroption.html");
				rd.include(req, resp);
			} else {
				PrintWriter pout = resp.getWriter();
				pout.println("<h1 align='center' style = 'color : red'> Invalid Credintials </h1> ");
				RequestDispatcher rd = req.getRequestDispatcher("login.html");

				rd.include(req, resp);
			}

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
