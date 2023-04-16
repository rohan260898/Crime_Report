<%@page import="com.sp.crime.Ctl.OCRView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
</head>
<body>
<%@include file="Header.jsp"%>

<div id="carouselExampleFade" class="carousel slide carousel-fade" data-bs-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="<%=OCRView.APP_CONTEXT%>/images/010.jpg" class="d-block w-100" alt="..."  width="500" 
     height="700">
    </div>
    <div class="carousel-item">
      <img src="<%=OCRView.APP_CONTEXT%>/images/david-von-diemar-jM6Y2nhsAtk-unsplash.jpg" class="d-block w-100" alt="..."  width="500" 
     height="700">
    </div>
   
  </div>

</div>
</body>
</html>