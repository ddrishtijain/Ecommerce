<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Order Placed</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>

<jsp:include page="navbar.jsp"/>

<body>

	<div class="container">

		<link rel="stylesheet"
			href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
			integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
			crossorigin="anonymous">
		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
			integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
			crossorigin="anonymous"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
			integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
			crossorigin="anonymous"></script>
		<script
			src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
			integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
			crossorigin="anonymous"></script>

		<!-- <h3 align="center">Your Order has been successfully placed.Further details will be shared with you shortly.</h3>
<h2><div style="text-align:center"><a href="products">Add More Items to Cart</a></h2></div>
</div> -->



		
		

	<div align="center"><br><br><br>
		<c:if test="${not empty success}">
		<h2><p style="color:green">${success}</p></h2>
		</c:if>
	</div><br><br><br>
	
		<div align="center"> <br><br><br>
		<c:if test="${not empty failure}">
		<h2><p style="color:red">${failure}</p></h2>
		</c:if>
	</div>
	<div align="center">
		<h2>
				Redirecting to Products Catalog
			</h2>
			<script type="text/JavaScript">
				setTimeout(
						"location.href = '${pageContext.request.contextPath}/products';",
						3000);
			</script></div>

</body>
</html>