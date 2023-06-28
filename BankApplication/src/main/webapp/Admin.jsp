<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<html>
<body>
	<%
	String id = request.getParameter("id");
	String email = request.getParameter("email");
	String password = request.getParameter("password");

	int id2 = Integer.parseInt(id);

	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
	PreparedStatement ps = conn.prepareStatement("insert into admin (id , email, password) values (?,?,?)");
	ps.setInt(1, id2);
	ps.setString(2, email);
	ps.setString(3, password);
	ps.execute();

	out.println("<h1 align='center'>Account Created successfully</h1>");//jspWriter out - (is ref var of jspWriter class)to print the content on browser
	%>
</body>
</html>
