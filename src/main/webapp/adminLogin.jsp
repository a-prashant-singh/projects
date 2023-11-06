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
if(session!=null){
session.invalidate();
}
response.setHeader ("Clear-Site-Data", "\"cache\"");


 %>

	<nav id="navigation">
		<ul class="nav-list">
			<li><a href="index.html">Home</a></li>
			<li><a href="about.jsp">About</a></li>
			<li><a href="#">Services</a></li>
			<li><a href="#">Contact</a></li>
		</ul>
	</nav>

	<main id="main-content">
		<section>
			<h1>ADMIN LOGIN</h1>
			<form name=F1 onSubmit="return dil(this)" action="AdminLoginServlet"
				method="post">
				<fieldset>

					<div class="form-group">
						<label for="emailid">Email Id:</label> <input type="email"
							name="emailid" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
							required placeholder="johndoe@example.com">
					</div>
					<div class="form-group">
						<label for="password">Password:</label> <input type="password"
							name="password" required>
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
