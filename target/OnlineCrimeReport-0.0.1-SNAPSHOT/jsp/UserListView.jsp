<%@page import="com.sp.crime.Ctl.UserRegisterCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.sp.crime.Utility.ServletUtility"%>
<%@page import="com.sp.crime.Utility.HTMLUtility"%>
<%@page import="com.sp.crime.Ctl.UserListCtl"%>
<%@page import="com.sp.crime.Model.UserModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UserList</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
	<h2 align="center">User List</h2>
	

		<table class="table table-striped">
			<tr >
				
				<th scope="col" style="color: blue">ID</th>
				<th scope="col"  style="color: blue">FirstName</th>
				<th scope="col"style="color: blue">LastName</th>
				<th scope="col"style="color: blue">Email</th>
				<th scope="col"style="color: blue">Gender</th>
				<th scope="col"style="color: blue">RoleName</th>
				<th scope="col"style="color: blue">Action</th>
					<th scope="col"></th>
			</tr>
			<%
			
				int index = 1;
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				while (it.hasNext()) {
					UserBean bean = (UserBean) it.next();
			%>
			<tr>
				
				<th scope="row" style="color: blue"><%=index++%></th>
				<td><%=bean.getFirstName()%></td>
				<td><%=bean.getLastName()%></td>
				<td><%=bean.getEmail()%></td>
				<td><%=bean.getPhoneNo()%></td>
				<td><%=bean.getGender()%></td>
				<td><a class="btn btn-info"
					href="<%=OCRView.USER_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
				<td><a class="btn btn-danger" href="<%=OCRView.USER_LIST_CTL%>?id=<%=bean.getId()%>">Delete</a></td>
			</tr>
			<%
				}
			%>
			</tbody>
		</table>

		
	</form>
</body>

</html>