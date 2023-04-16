<%@page import="java.time.LocalTime"%>
<%@page import="com.sp.crime.Ctl.CriminalCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import = "java.time.LocalTime" %>
<%@page import="com.sp.crime.Utility.ServletUtility"%>
<%@page import="com.sp.crime.Utility.HTMLUtility"%>
<%@page import="com.sp.crime.Utility.DataUtility"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.sp.crime.Utility.JDBCDataSource"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<meta charset="ISO-8859-1">
<title>Criminal View</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<br>
	<h6 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h6>
	<h6 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h6>
	<hr>
	<form action="<%=OCRView.CRIMINAL_CTL%>" method="post"
		enctype="multipart/form-data">


		<jsp:useBean id="bean" scope="request"
			class="com.sp.crime.Bean.CrimnalBean" />

		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedby()%>"> <input type="hidden"
			name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
		<input type="hidden" name="modifiedDateTime"
			value="<%=bean.getModifieddatetime()%>">

		<div class="container">
			<h3>Add Criminal</h3>
			<p>Fill in the data below.</p>
			<table class="table table-striped">
				<tbody>
					<tr>
						<td colspan="1">
							<form class="well form-horizontal">
								<fieldset>
									<div class="form-group">
										<label class="col-md-4 control-label">Criminal Name:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-user"></i></span><input id="fullName"
													name="crimnal_name" placeholder="Enter Name"
													class="form-control" value="<%=DataUtility.getStringData(bean.getCrimnal_name())%>" type="text">
											</div>
										</div>
									</div>
	<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("crimnal_name", request)%></div>

									<br>
									<div class="form-group">
										<label class="col-md-4 control-label">Criminal Address:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-home"></i></span><input
													id="addressLine1" name="crimnal_address"
													placeholder="Criminal Address" class="form-control"
													value="<%=DataUtility.getStringData(bean.getCrimnal_address())%>" type="text">
											</div>
										</div>
									</div>
	<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("crimnal_address", request)%></div>

									<br>
									<%
										HashMap<String, String> map = new HashMap<String, String>();
										map.put("Male", "Male");
										map.put("Female", "Female");
									%>

									<div class="mb-1" style="width: 67%">
										<label class="form-label">Gender:</label>
										<%=HTMLUtility.getList("gender", String.valueOf(bean.getGender()), map)%>
  <div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></div>
									</div>

	<br>
									<div class="form-group">

										<label class="col-md-4 control-label">PoliceStationName:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-home"></i></span><input
													id="addressLine2" name="policestationname"
													placeholder="PoliceStationName" class="form-control"
													value="<%=DataUtility.getStringData(bean.getPolicestationname())%>" type="text">
											</div>
										</div>
									</div>
	<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("policestationname", request)%></div>

<br>
	<div class="form-group">
										<label class="col-md-4 control-label">City/State/Country:</label>
										<div class="col-md-8 inputGroupContainer">
											<div class="input-group">
												<span class="input-group-addon"><i
													class="glyphicon glyphicon-home"></i></span><input id="city"
													name="state" placeholder="City/State/Country"
													class="form-control" value="<%=DataUtility.getStringData(bean.getState())%>" type="text">
											</div>
										</div>
									</div>
  <div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("state", request)%></div>


								

									<br>
									
									 <label for="appt">Crime Time:</label> <input
			type="time"  name="crime_time" value="<%=bean.getCrime_time()%>" style="width: 10%"   required>
<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("crime_time", request)%></div>
			
			<br>
									<div class="mb-3">
										<label for="exampleFormControlInput1" class="form-label">Criminal
											Photo:</label> <input type="file" id="exampleFormControlInput1"
											 name="image"  required>
									</div>
	<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("image", request)%></div>


<%-- <div class="container">
								<p>
									<label for="exampleInputPassword1">Role:</label>
								<div class="form-group">
									<select class="custom-select" name=role>
										<%
											Connection conn = JDBCDataSource.getConnection();
											String sql = "SELECT * FROM role";
											PreparedStatement ps = conn.prepareStatement(sql);
											ResultSet rs = ps.executeQuery();
											while (rs.next()) {
										%>

										<option value="--------Select--------"></option>
										<option value="<%=rs.getLong(1)%>"><%=rs.getString(2)%></option>

										<%
											}
										%>
									</select>

									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("role", request)%></div>
								</div>

 --%>

									<br> <input type="submit" class="btn btn-primary"
										name="operation" value="<%=CriminalCtl.OP_SAVE%>">
								</fieldset>
							</form>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

	</form>
	<br>
	
</body>
</html>