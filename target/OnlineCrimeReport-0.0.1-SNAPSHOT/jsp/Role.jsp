<%@page import="com.sp.crime.Ctl.RoleCtl"%>
<%@page import="com.sp.crime.Utility.DataUtility"%>
<%@page import="com.sp.crime.Utility.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Role Add</title>
</head>
<body>
<%@include file="Header.jsp"%>
<br>
<h3 align="center"  style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h3>
<form action="<%=OCRView.ROLE_CTL%>" method="post">
<jsp:useBean id="bean" scope="request"
						class="com.sp.crime.Bean.RoleBean" />

					<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
						type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
					<input type="hidden" name="modifiedBy"
						value="<%=bean.getModifiedby()%>"> <input type="hidden"
						name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
					<input type="hidden" name="modifiedDateTime"
						value="<%=bean.getModifieddatetime()%>">


  <div class="container">
  <h2 align="center"  style="background-color: blue;">Add Role</h2>
  <br>
  
  <h6 style="margin-left: 450px;">Role Name  :</h6>  <input type ="text" name ="rolename" value=
  "<%=DataUtility.getStringData(bean.getRolename())%>" placeholder="Enter Role Name"style="margin-left: 550px;">
   <div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("rolename", request)%></div>
					</div>
					<br>
					
  <input type="submit" class="btn btn-primary" name="operation" value="<%=RoleCtl.OP_SAVE%>" style="margin-left: 770px;">
</form>

<br>


</body>
</html>