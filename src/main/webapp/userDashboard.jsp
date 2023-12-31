<!DOCTYPE HTML>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">



<title>Global Banking</title>

<link href="NewFile.css" rel="stylesheet" type="text/css">
</head>
<%@page
	import="com.bank.user.VerifyLogin1,com.bank.dao.UserDao,com.bank.bean.User"%>
<%@page import="com.bank.GetCon"%>
<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>

<%
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Cache-Control","no-store");
  response.setHeader("Pragma","no-cache");
  response.setDateHeader ("Expires", 0);

  if(session.getAttribute("emailid")==null)
      response.sendRedirect("userLogin.jsp");

  %>
<%
User user=null;
session=request.getSession(false);  

if(session!=null){
	String emailid = (String) session.getAttribute("emailid");	
	 user = UserDao.getUserByEmailId(emailid);

}
else
{
    request.getRequestDispatcher("userLogin.jsp").include(request, response);  

}

%>
<body>
	<header id="header">
		<h1>WELCOME TO DIGITAL BANKING</h1>
	</header>


	<nav id="navigation">
		<ul class="nav-list">
			<li><a href="userDashboard.jsp">DASHBOARD</a></li>
			<li><a href="userCheckBalance.jsp">CHECK BALANCE</a></li>
			<li><a href="userTransferBalance.jsp">TRANSFER FUND</a></li>
			<li><a href="about.jsp">Contact Us</a></li>
			<li><a href="logout.jsp">LOGOUT</a></li>
		</ul>
	</nav>

	<main>
		<section class="account-info">
			<table class="styled-table">



				<tr>
					<td colspan="10">Welcome <%=user.getFirstname()%> <%=user.getLastname()%></td>
				</tr>

				<tr>
					<th>ACCOUNT NO</th>
					<th>FIRSTNAME</th>
					<th>LASTNAME</th>
					<th>EMAILID</th>
					<th>PASSWORD</th>
					<th>GENDER</th>
					<th>DOB</th>
					<th>AMOUNT</th>
					<th>ADDRESS</th>
					<th>PHONE</th>
				</tr>


				<tr>

					<td><%=user.getAccountno()%></td>
					<td><%=user.getFirstname()%></td>
					<td><%=user.getLastname()%></td>
					<td><%=user.getEmailid()%></td>
					<td><%=user.getPassword()%></td>
					<td><%=user.getGender()%></td>
					<td><%=user.getDob()%></td>
					<td><%=user.getBalance()%></td>
					<td><%=user.getAddress()%></td>
					<td><%=user.getPhone()%></td>


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

				Copyright � Your Company Name
			</div>
		</div>
	</footer>

</body>

</html>










