<%@page import="com.sp.crime.Ctl.CrimeCtl"%>
<%@page import="com.sp.crime.Utility.DataUtility"%>
<%@page import="com.sp.crime.Utility.ServletUtility"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<style>
textarea {
	margin-left: 10px;
	padding: 30px;
}
</style>
<meta charset="ISO-8859-1">
<title>CrimeADD</title>
</head>
<body>
	<%@include file="Header.jsp"%>
	<br>
	<h3 align="center" style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h3>
	<form action="<%=OCRView.CRIME_CTL%>" method="post">

		<jsp:useBean id="bean" scope="request"
			class="com.sp.crime.Bean.CrimeBean" />

		<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
			type="hidden" name="createdBy" value="<%=bean.getCreatedby()%>">
		<input type="hidden" name="modifiedBy"
			value="<%=bean.getModifiedby()%>"> <input type="hidden"
			name="createdDatetime" value="<%=bean.getCreatedatetime()%>">
		<input type="hidden" name="modifiedDateTime"
			value="<%=bean.getModifieddatetime()%>">


		
		<table class="table table-striped">
			<tbody align="center">
				<tr>
					<td colspan="1">
						<form class="well form-horizontal">
							<fieldset>
							<h2>Add Crime</h2>
								<div class="col-md-2">
									<label for="form_message">Crime Name:</label> <input type="text"
										name="crime_name" style="width: 115%"
										value="<%=DataUtility.getStringData(bean.getCrime_name())%>"
										placeholder="Enter Crime Name">
</div>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("crime_name", request)%></div>
									<br>
<div class="col-md-2">
									<label for="form_message">Description:</label>
									<textarea name="description" class="form-Center"
										placeholder="Write your Description" cols="30" rows="3"
										value="<%=DataUtility.getStringData(bean.getDescription())%>"></textarea>
									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("description", request)%></div>
</div>

									<input type="submit" class="btn btn-primary" name="operation"
										value="<%=CrimeCtl.OP_SAVE%>">
							</fieldset>
						</form>
					</td>
				</tr>
			</tbody>
		</table>

	</form>

	<br>
	

</body>
</html>