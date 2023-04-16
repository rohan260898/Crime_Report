<%@page import="com.sp.crime.Bean.RoleBean"%>
<%@page import="com.sp.crime.Ctl.MyProfileCtl"%>
<%@page import="com.sp.crime.Ctl.UserRegisterCtl"%>
<%@page import="com.sp.crime.Ctl.LoginCtl"%>
<%@page import="com.sp.crime.Bean.UserBean"%>
<%@page import="com.sp.crime.Bean.RoleBean"%>

<%@page import="com.sp.crime.Ctl.OCRView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HeaderView</title>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
	crossorigin="anonymous"></script>


</head>
<body>

	<%
		UserBean userBean = (UserBean) session.getAttribute("user");

	%>
	<%		boolean userLoggedIn = userBean != null;

		String welcomeMsg = "Hello, ";

		if (userLoggedIn) {
			String role = (String) session.getAttribute("role");
                 //String lastname =  userBean.getLastName();
			welcomeMsg += userBean.getFirstName() + " (" + role + ")";
		} else {
			welcomeMsg += "Guest";
		}
		%>
		
	<!-- As a heading -->
<nav class="navbar bg-light">
		<span class="navbar-brand mb-0 h1"
			style="color: green;">Crime-Report<a style="margin-left: 1270px;"><%=welcomeMsg%></a>
		</span>
	</nav>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="<%=OCRView.WELCOME_CTL%>">Home</a></li>

				<%
					if (userBean != null) {
				%>

				<%
					if (userBean.getRoleid() == 1) {
				%>

				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> User </a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item" href="<%=OCRView.USER_CTL%>">Add User</a></li>
								<li><a class="dropdown-item" href="<%=OCRView.USER_LIST_CTL%>">UserList</a></li>
							</ul></li>
					</ul>
				</div>


				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> CriminalReport </a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item" href="<%=OCRView.CRIMINAL_CTL%>">Add Criminal Report</a></li>
								<li><a class="dropdown-item" href="<%=OCRView.CRIMINAL_LIST_CTL%>">View Criminal Report</a></li>
							</ul></li>
					</ul>
				</div>

            


				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">Report</a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item" href="<%=OCRView.FIR_CTL %>">Write Report</a></li>
								<li><a class="dropdown-item" href="<%=OCRView.FIR_LIST_CTL%>">Show Report</a></li>
							</ul></li>
					</ul>
				</div>

<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">Crime Category</a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item" href="<%=OCRView.CRIME_CTL%>">Add Crime Category</a></li>
								<li><a class="dropdown-item" href="<%=OCRView.CRIME_LIST_CTL%>">Crime Category List </a></li>
							</ul></li>
					</ul>
				</div>		

				<%
					}
				%>
				<%
					}
				%>

			</ul>

		</div>


		<ul class="nav justify-content-end">

			<%
				if (userBean == null) {
			%>
			<ul class="nav justify-content-end" style="margin-right: 30px;">
				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">Guest</a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item" href="<%=OCRView.LOGIN_CTL%>">SignIn</a></li>
								<li><a class="dropdown-item"
									href="<%=OCRView.USER_REGISTRATION_CTL%>">SignUp</a></li>
							</ul></li>
					</ul>
				</div>
			</ul>
			<%
				} else {
			%>
			<ul class="nav justify-content-end" style="margin-right: 30px;">
				<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
					<ul class="navbar-nav">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDarkDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false">User</a>
							<ul class="dropdown-menu dropdown-menu-dark"
								aria-labelledby="navbarDarkDropdownMenuLink">
								<li><a class="dropdown-item"
									href="<%=OCRView.MYPROFILE_CTL%>?operation=<%=MyProfileCtl.OP_MYPROFILE%>">MYProfile</a></li>
								<li><a class="dropdown-item"
									href="<%=OCRView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOGOUT%>">Logout</a></li>

							</ul></li>
					</ul>
				</div>
			</ul>


			<%
				}
			%>

		</ul>
	</nav>
</body>
</html>