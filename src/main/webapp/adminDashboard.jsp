<!DOCTYPE HTML>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Global Banking</title>
<link href="NewFile.css" rel="stylesheet" type="text/css">
</head>
<%@page
	import="com.bank.user.VerifyLogin1,com.bank.dao.UserDao,com.bank.bean.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.bank.GetCon"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.*"%>
<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  
  if(session.getAttribute("emailid")==null)
      response.sendRedirect("adminLogin.jsp");
  %>
<%
session=request.getSession(false);  

if(session!=null){
String emailid = (String) session.getAttribute("emailid");
boolean status=(Boolean)session.getAttribute("isLoggedIn");


List<User> list = UserDao.getAllUser();

request.setAttribute("list", list);
}
else
{
    request.getRequestDispatcher("adminLogin.jsp").include(request, response);  

}
%>

<body>
	<header id="header">
		<h1>WELCOME TO DIGITAL BANKING</h1>
	</header>


	<nav id="navigation">
		<ul class="nav-list">
			<li><a href="register.html">NEW ACCOUNT</a></li>
			<li><a href="adminDeposit.jsp">DEPOSIT</a></li>
			<li><a href="adminWithdraw.jsp">WITHDRAW</a></li>
			<li><a href="adminTransferBalance.jsp">TRANSFER FUND</a></li>
			<li><a href="adminDashboard.jsp">ALL USER</a></li>
			<li><a href="logout.jsp">LOGOUT</a></li>
		</ul>
	</nav>

	<main>
		<section class="account-info">
			<table class="styled-table">





				<tr>
					<th>Sr.No</th>
					<th>ACCOUNT NO</th>
					<th>FIRSTNAME</th>
					<th>LASTNAME</th>
					<th>PASSWORD</th>
					<th>GENDER</th>
					<th>DOB</th>
					<th>AMOUNT</th>
					<th>ADDRESS</th>
					<th>PHONE</th>
					<th>EDIT</th>
					<th>DELETE</th>
				</tr>


				<tr>

					<c:forEach items="${list}" var="user">

						<tr>
							<td>${user.getId()}</td>
							<td>${user.getAccountno()}</td>
							<td>${user.getFirstname()}</td>
							<td>${user.getLastname()}</td>
							<td>${user.getPassword()}</td>
							<td>${user.getGender()}</td>
							<td>${user.getDob()}</td>
							<td>${user.getBalance()}</td>
							<td>${user.getAddress()}</td>
							<td>${user.getPhone()}</td>

							<td><a href="adminUserEdit1.jsp?id=${user.getId()}">EDIT</a></td>
							<td><a href="adminUserDelete1.jsp?id=${user.getId()}">DELETE</a></td>
						</tr>
					</c:forEach>


				</tr>

			</table>
		</section>
	</main>

	<footer>
		<div class="footer-navigation">
			<p>Connect with us on +91 7021369170</p>
		</div>

		<div class="footer-copyright">
			<div id="footer_copyright">

				<center>
					<img alt="business" width="196" height="106">
				</center>
				<br>
				<p>Each people plan their site layouts depending upon their
					business type. Here comes a free designer template which provides
					you with a selection of different kinds of web design starting from
					business template, fashion template, media template, Science
					template, Arts template and much more.</p>

				Copyright © Your Company Name
			</div>
		</div>
	</footer>

</body>

</html>












