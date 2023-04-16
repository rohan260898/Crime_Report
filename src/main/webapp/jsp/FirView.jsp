<%@page import="com.sp.crime.Ctl.FirCtl"%>
<%@page import="com.sp.crime.Utility.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript">
	$('.datepicker').datepicker();
</script>
<meta charset="ISO-8859-1">
<title>FIR View</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<h6 style="color: red;"><%=ServletUtility.getErrorMessage(request)%></h6>
	<h6 style="color: green;"><%=ServletUtility.getSuccessMessage(request)%></h6>
	<div class="container">
		<form action="<%=OCRView.FIR_CTL%>" method="post">

			<div class="row">
				<div class="col-2"></div>
				<div class="col-8">
					<table class="table table-striped">
						<tbody>
							<tr>
								<td colspan="1">
									<form class="well form-horizontal">
										<fieldset>

											<jsp:useBean id="bean" scope="request"
												class="com.sp.crime.Bean.FirBean" />

											<input type="hidden" name="id" value="<%=bean.getId()%>">
											<input type="hidden" name="createdBy"
												value="<%=bean.getCreatedby()%>"> <input
												type="hidden" name="modifiedBy"
												value="<%=bean.getModifiedby()%>"> <input
												type="hidden" name="createdDatetime"
												value="<%=bean.getCreatedatetime()%>"> <input
												type="hidden" name="modifiedDateTime"
												value="<%=bean.getModifieddatetime()%>">

											<div class="form-body">
												<div class="row">
													<div class="form-holder">
														<div class="form-content">
															<div class="form-items">
																<h3>Report Register</h3>
																<p>Fill in the data below.</p>
																<form class="requires-validation" novalidate>

																	<div class="col-md-12">
																		<label for="form_message">Name:</label> <input
																			class="form-control" type="text" name="name"
																			placeholder="Full Name"
																			value="<%=DataUtility.getStringData(bean.getName())%>">
																	</div>
																	<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></div>
															</div>
															<div class="col-md-12">
																<label for="form_message">Father Name:</label> <input
																	class="form-control" type="text" name="father_name"
																	placeholder="Father's Name"
																	value="<%=DataUtility.getStringData(bean.getFather_name())%>">
															</div>
															<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("father_name", request)%></div>
														</div>

														<div class="col-md-12">
															<label for="form_message">Mother Name:</label> <input
																class="form-control" type="text" name="mother_name"
																placeholder="Mother's Name"
																value="<%=DataUtility.getStringData(bean.getMother_name())%>">
														</div>
														<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("mother_name", request)%></div>
													</div>
													

														<div class="col-md-12">
															<div class="form-group">
																<label for="form_message">Address:</label>
																<textarea id="form_message" name="address"
																	class="form-control" placeholder="Write your Address"
																	rows="2"></textarea>
															</div>
														</div>
														<br>
														<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("address", request)%></div>
													</div>
													<div class="col-md-12">
														<label for="form_message">MobileNo:</label> <input
															class="form-control" type="text" name="mobileno"
															placeholder="MobileNo"
															value="<%=DataUtility.getStringData(bean.getMobileno())%>">
													</div>
													<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("mobileno", request)%></div>
												</div>
											
							<label for="form_message">Date:</label>

											<div class="form-group">
												<input type="text" class="form-control"
													id="exampleInputEmail1" aria-describedby="emailHelp"
													name="date" id="datepicker" data-provide="datepicker"
													value="<%=DataUtility.getStringData(bean.getDate())%>"
													placeholder="date Enter Here"> <font color="red"><%=ServletUtility.getErrorMessage("date", request)%></font>
											</div>

											<div class="col-md-12">

												<div class="form-group">
													<label for="form_message">Information:</label>
													<textarea id="form_message" name="information"
														class="form-control" placeholder="Write your Information"
														rows="4"></textarea>
												</div>
											</div>
											<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("infotmation", request)%></div>

			


							<%
								HashMap<String, String> map = new HashMap<String, String>();
								map.put("Male", "Male");
								map.put("Female", "Female");
							%>

							<div class="mb-3" style="">
								<label class="form-label">Gender"</label>
								<%=HTMLUtility.getList("gender", String.valueOf(bean.getGender()), map)%>
								<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></div>
							</div>



							
									<label for="exampleInputPassword1">Crime:</label>
								<div class="form-group">
									<select class="custom-select" name=crime  style="width: 100%; height: 40px;">
										<%
											Connection con = JDBCDataSource.getConnection();
											String sql = "SELECT * FROM crimeinfo";
											PreparedStatement ps = con.prepareStatement(sql);
											ResultSet rs = ps.executeQuery();
											while (rs.next()) {
										%>

										<option value="--------Select--------"></option>
										<option value="<%=rs.getLong(1)%>"><%=rs.getString(2)%></option>

										<%
											}
										%>
									</select>

									<div class="form-text" style="color: red"><%=ServletUtility.getErrorMessage("crime", request)%></div>
								</div>


							<br> <input type="submit" class="btn btn-primary"
								name="operation" value="<%=FirCtl.OP_SAVE%>">
							&nbsp;&nbsp;<input type="submit" class="btn btn-primary"
								name="operation" value="<%=FirCtl.OP_RESET%>">

					</fieldset>
					</form>
					</td>
					</tr>
					</tbody>
					</table>
					</div>
					
				<div class="col-2"></div>
			</div>
		</form>

		<br>
	</div>

</body>
</html>