<%@page import="com.sp.crime.Ctl.LoginCtl"%>
<%@page import="com.sp.crime.Utility.DataUtility"%>
<%@page import="com.sp.crime.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%@include file="Header.jsp"%>

	<form action="<%=OCRView.LOGIN_CTL%>" method="post">

		<jsp:useBean id="bean" scope="request"
			class="com.sp.crime.Bean.UserBean" />

		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedby()%>"> <input type="hidden"
			name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
		<input type="hidden" name="modifiedDateTime"
			value="<%=bean.getModifieddatetime()%>">

		<div class="container">
			<div class="row">
				<div class="col-2"></div>

				<div class="col-8" style="margin-top: 100px;">

					<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
					<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>
					<hr>

					<p class="h4 mb-4 text-center" style="background-color: white;">Sign
						in</p>
					<input type="email" id="defaultLoginFormEmail"
						class="form-control mb-4" name="email" placeholder="Enter E-mail"
						value="<%=DataUtility.getStringData(bean.getEmail())%>">
					<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></div>


					<input type="password" id="defaultLoginFormPassword"
						class="form-control mb-4" name="password"
						placeholder="Enter Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
					<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></div>


					<input type="submit" class="btn btn-primary" name="operation"
						value="<%=LoginCtl.OP_SINGIN%>">
					<div>


						<div class="col-2"></div>
					</div>
				</div>
			</div>
	</form>
	<br>
	</div>
	<br>
	<br><br>
	<br>
	<br><br>
	
</body>
</html>