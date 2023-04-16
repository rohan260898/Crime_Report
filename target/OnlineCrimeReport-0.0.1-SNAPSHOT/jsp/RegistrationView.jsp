<%@page import="com.sp.crime.Utility.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.sp.crime.Ctl.UserRegisterCtl"%>
<%@page import="com.sp.crime.Utility.DataUtility"%>
<%@page import="com.sp.crime.Utility.ServletUtility"%>
<%@page import="java.util.List"%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.sp.crime.Utility.JDBCDataSource"%>
<%@page import="java.sql.Connection"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
	$('.datepicker').datepicker();
</script>

<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<%@ include file= "Header.jsp"%>
	<div class="container">
		<br>
		<nav style="-bs-breadcrumb-divider: '&gt;';" aria-label="breadcrumb">
			<ol class="breadcrumb" style="background-color: silver;">
				<li class="breadcrumb-item"><a href="<%=OCRView.WELCOME_CTL%>">Home</a></li>
				<li class="breadcrumb-item active" aria-current="page">User
					Registration</li>
			</ol>
		</nav>
		<h2 style="background-color: green;">User Registration</h2>
		<h6 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h6>
		<h6 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h6>
		<hr>
		<form action="<%=OCRView.USER_REGISTRATION_CTL%>" method="post">

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8" style="background-color: beige;">


					<jsp:useBean id="bean" scope="request"
						class="com.sp.crime.Bean.UserBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">

					<div class="mb-3">
						<label class="form-label">First Name</label> <input type="text"
							class="form-control" name="firstName"
							placeholder="Enter First Name here..."
							value="<%=DataUtility.getStringData(bean.getFirstName())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></div>
					</div>

					<div class="mb-3">
						<label class="form-label">Last Name</label> <input type="text"
							class="form-control" name="lastName"
							placeholder="Enter Last Name here..."
							value="<%=DataUtility.getStringData(bean.getLastName())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></div>
					</div>

					<div class="mb-3">
						<label class="form-label">Email Id</label> <input type="text"
							class="form-control" name="email"
							placeholder="Enter Email Id here..."
							value="<%=DataUtility.getStringData(bean.getEmail())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></div>
					</div>
					<div class="mb-3">
						<label class="form-label">Password</label> <input type="password"
							class="form-control" name="password"
							placeholder="Enter Password here..."
							value="<%=DataUtility.getStringData(bean.getPassword())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></div>
					</div>

					<div class="mb-3">
						<label class="form-label">PhoneNo</label> <input type="text"
							class="form-control" name="phoneNo"
							placeholder="Enter Phone No here..."
							value="<%=DataUtility.getStringData(bean.getPhoneNo())%>">

						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></div>
					</div>


					<%
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("Male", "Male");
						map.put("Female", "Female");
					%>

					<div class="mb-3">
						<label class="form-label">Gender</label>
						<%=HTMLUtility.getList("gender", String.valueOf(bean.getGender()), map)%>
						<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></div>
					</div>

   

					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=UserRegisterCtl.OP_SAVE%>"> &nbsp;&nbsp;<input
						type="submit" class="btn btn-primary" name="operation" value="<%=UserRegisterCtl.OP_RESET %>">

				</div>
				<div class="col-2"></div>

			</div>
		</form>
	</div>
	<br>

</body>
</html>