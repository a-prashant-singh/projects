





<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.bank.dao.UserDao"%>
<html>
<head>


<%@page import="com.bank.user.VerifyLogin1, com.bank.bean.User"%>
<%@page import="com.bank.GetCon,java.util.*"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Global Banking ..</title>
<link href="NewFile.css" rel="stylesheet" type="text/css">

<%
int id=Integer.parseInt(request.getParameter("id"));
User user=UserDao.getUserById(id);
%>


</head>

<body>
	<header id="header">
	<h1>WELCOME TO DIGITAL BANKING</h1>
	</header>

	<nav id="navigation">
	<ul class="nav-list">
		<li><a href="userCheckBalance.jsp">CHECK BALANCE</a></li>
		<li><a href="userTransferBalance.jsp">TRANSFER FUND</a></li>
		<li><a href="adminCloseAccount1.jsp">CLOSE A/C</a></li>
		<li><a href="about.jsp">Contact Us</a></li>
		<li><a href="logout.jsp">LOGOUT</a></li>
	</ul>
	</nav>
	<main id="main-content"> <section>
	<h1>EDIT ACCOUNT FORM</h1>
	<form name="F1" onsubmit="return validateForm(this)"
		action="adminUserEdit.jsp" method="post">
		<fieldset>
			<legend>Personal Information</legend>
			<div class="form-group">
				<input type="hidden" name="id" value="<%=user.getId() %>">
			</div>
			<div class="form-group">
				<label for="firstname">First Name:</label> <input type="text"
					name="firstname" value="<%=user.getFirstname() %>"
					pattern="[a-zA-Z ]+" required placeholder="John">
			</div>
			<div class="form-group">
				<label for="lastname">Last Name:</label> <input type="text"
					name="lastname" value="<%=user.getLastname() %>"
					pattern="[a-zA-Z ]+" required placeholder="Doe">
			</div>
			<div class="form-group">
				<label for="emailid">Email Id:</label> <input type="email"
					name="emailid" id="emailid" value="<%=user.getEmailid() %>"
					pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" required
					placeholder="johndoe@example.com"> <span
					id="emailAvailability"></span>
			</div>




		</fieldset>

		<fieldset>
			<legend>Date of Birth</legend>


			<div class="label-group-gender">
				<label>Date Of Birth:</label>
			</div>
			<div class="date-group">

				<input type="date" name="dob" value="<%=user.getDob() %>"
					placeholder="DOB">
			</div>
			</div>
		</fieldset>

		<fieldset>
			<legend>Contact information</legend>
			</div>
			<div class="form-group">
				<label for="address">Address:</label> <input type="text"
					name="address" value="<%=user.getAddress() %>"
					placeholder="123 Main St">
			</div>
			<div class="form-group">
				<label for="phone">Phone:</label> <input type="tel" name="phone"
					value="<%=user.getPhone() %>" pattern="[0-9]{10}"
					title="Enter a valid 10-digit phone number" required
					placeholder="1234567890">
			</div>
		</fieldset>
		<div class="form-submit-reset-button">
			<input type="submit" value="Update" class="submit-button"> <input
				type="reset" value="Clear" class="reset-button">
		</div>
	</form>
	</section> </main>

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
				you with a selection of different kinds of webdesign starting from
				business template, fashion template, media template, Science
				template, Arts template and much more.</p>

			Copyright © Your Company Name
		</div>
	</div>
	</footer>


</body>

</html>


