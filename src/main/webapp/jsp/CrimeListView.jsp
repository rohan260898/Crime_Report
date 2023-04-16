<%@page import="com.sp.crime.Utility.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.sp.crime.Bean.CrimeBean"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Crime List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
	<h2 align="center"  style="color: green;">Crime List</h2>
	<br>

	<h4 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h4>
	<h4 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h4>

	<form action="<%=OCRView.CRIME_LIST_CTL%>" method="post">


		<br>

		<table class="table table-striped"  style="background-color: beige;">
			<tr>

				<th scope="col">Crime Name:--</th>
				<th scope="col">Description</th>
				<th scope="col"></th>
			</tr>
			<%
				int index = 1;
				CrimeBean bean = null;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {

					bean = (CrimeBean) it.next();
			%>
			<tr>
				<th scope="row"><font color="red">*</font><%=bean.getCrime_name()%></th>
				<th scope="row"><%=bean.getDescription()%></th>
			</tr>
			<%
				}
			%>
			</tbody>
		</table>
	</form>
	<br>
	
</body>
</html>