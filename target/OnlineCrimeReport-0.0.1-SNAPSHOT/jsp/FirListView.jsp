<%@page import="com.sp.crime.Bean.FirBean"%>
<%@page import="com.sp.crime.Ctl.FirListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.sp.crime.Utility.ServletUtility"%>
<%@page import="com.sp.crime.Utility.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FIR List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>

	<%
	UserBean bean2 = (UserBean) session.getAttribute("user");
	%>
	<jsp:useBean id="bean1" scope="request"
		class="com.sp.crime.Bean.RoleBean" />
	<input type="hidden" name="id" value="<%=bean1.getId()%>">
	<h2 align="center" style="color: green">Report List</h2>


		<table class="table table-striped" style="background-color: pink;">
			<tr>

				<th scope="col">ID</th>
				<th scope="col">Name</th>
				<th scope="col">FatherName</th>
				<th scope="col">MotherName</th>
				<th scope="col">Address</th>
				<th scope="col">MobileNo</th>
			<!-- 	<th scope="col">Email</th> -->
				<th scope="col">Date</th>
				<th scope="col">Information</th>
				<th scope="col">Gender</th>
				<th scope="col">Crime</th>

			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					FirBean bean = (FirBean) it.next();
			%>
			<tr style="background-color: white;">


				<th scope="row"><%=index++%></th>
				<td><%=bean.getName()%></td>
				<td><%=bean.getFather_name()%></td>
				<td><%=bean.getMother_name()%></td>
				<td><%=bean.getAddress()%></td>
				<td><%=bean.getMobileno()%></td>
			<%-- 	<td><%=bean.getEmail()%></td> --%>
				<td><%=bean.getDate()%></td>
				<td><%=bean.getInformation()%></td>
				<td><%=bean.getGender()%></td>
				<td><%=bean.getCrime()%></td>
				<%
					}
				%>
			
			</tbody>
		</table>

	</form>
	
</body>
</html>