<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>APANA - BANK</title>
<link rel="stylesheet" href="NewFile.css">

</head>
<body>
	<header id="header">
		<h1>WELCOME TO DIGITAL BANKING</h1>
	</header>

	<%
if(session!=null)
{
	boolean status=(Boolean)session.getAttribute("isLoggedIn");

}
else{
    request.getRequestDispatcher("adminLogin.jsp").include(request, response);  
}

%>

	<nav id="navigation">
		<ul class="nav-list">

			<li><a href="adminDashboard.jsp">DASHBOARD</a></li>
			<li><a href="about.jsp">CONTACT US</a></li>
			<li><a href="logout.jsp">LOGOUT</a></li>
		</ul>
	</nav>

	<main id="main-content">
		<section>
			<h1>DEPOSIT</h1>
			<form name=F1 onSubmit="return dil(this)"
				action="AdminDepositBalanceServlet" method="post">
				<fieldset>

					<div class="form-group">
						<label for="accountno">ACCOUNT NO:</label> <input type="text"
							name="accountno" pattern="[0-9]{13}" required
							placeholder="16**********">
					</div>
					<div class="form-group">
						<label for="amount">AMOUNT:</label> <input type="text"
							name="amount" required>
					</div>


				</fieldset>




				<div class="form-submit-reset-button">
					<input type="submit" value="Submit" class="submit-button">
					<input type="reset" value="Clear" class="reset-button">
				</div>
			</form>
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
					you with a selection of different kinds of webdesign starting from
					business template, fashion template, media template, Science
					template, Arts template and much more.</p>

				Copyright © Your Company Name
			</div>
		</div>
	</footer>
	<script type="text/javascript">
            window.onload = ctck;
        </script>
	</div>
</body>
</html>


