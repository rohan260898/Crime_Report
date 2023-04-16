<%@page import="com.sp.crime.Bean.CrimnalBean"%>
<%@page import="com.sp.crime.Utility.ServletUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.sp.crime.Ctl.CriminalListCtl"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Criminal List</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>

	<%
		UserBean bean2 = (UserBean) session.getAttribute("user");
	%>
	<h2 align="center" style="color: green">Criminal List</h2>
	

		<table class="table table-striped">
			<tr>

				<th scope="col">ID</th>
				<th scope="col">Image</th>
				<th scope="col">Criminal_Address</th>
				<th scope="col">Gender</th>
				<th scope="col">Crime_Time</th>
				<th scope="col">PoliceStationName</th>
				<th scope="col">State</th>
				<th scope="col">Action</th>
			</tr>
			<%
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					CrimnalBean bean = (CrimnalBean) it.next();
			%>
						<tr>

				<th scope="row"><%=index++%></th>
				<td><img alt=""
					src="/OnlineCrimeReport/image?id=<%=bean.getId()%>" height="200px;"
					width="200px;" class="shadow-1-strong rounded-circle"
					alt="avatar 1">

					<h5 class="mb-10">
						<span class="badge bg-success"><%=bean.getCrimnal_name()%></span>
					</h5></td>
				<td><%=bean.getCrimnal_address()%></td>
				<td><%=bean.getGender()%></td>
				<td><%=bean.getCrime_time()%></td>
				<td><%=bean.getPolicestationname()%></td>
				<td><%=bean.getState()%></td>
				<td><a class="btn btn-danger"
					href="/OnlineCrimeReport/criminallistctl?id=<%=bean.getId()%>">Delete</a></td>
						<%
				}
			%>
			</tbody>
		</table>




	</form>
	
</body>
</html>