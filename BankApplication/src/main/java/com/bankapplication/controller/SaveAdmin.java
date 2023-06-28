package com.bankapplication.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/saveadmin")
public class SaveAdmin extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		String email = req.getParameter("email");
		String pass = req.getParameter("password");

		int id1 = Integer.parseInt(id);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			PreparedStatement ps = conn.prepareStatement("insert into admin(id, email, password) values(?,?,?)");
			ps.setInt(1, id1);
			ps.setString(2, email);
			ps.setString(3, pass);

			ps.execute();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		PrintWriter pout = resp.getWriter();
		pout.print("<h1 align = 'center' style = 'color:green'>Account Created Succsessfully</h1>");
	}

}
