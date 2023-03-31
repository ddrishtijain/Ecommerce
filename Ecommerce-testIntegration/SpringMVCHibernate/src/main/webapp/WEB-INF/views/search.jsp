
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<spring:url value="/resources/js/bootstrap.bundle.min.js" var="bootstrapjs" />
<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryjs" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />


<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Products</title>
<style type="text/css">
.tg {
    border-collapse: collapse;
    border-spacing: 0;
    border-color: #ccc;
}

.tg td {
    font-family: Arial, sans-serif;
    font-size: 14px;
    padding: 10px 5px;
    border-style: solid;
    border-width: 1px;
    overflow: hidden;
    word-break: normal;
    border-color: #ccc;
    color: #333;
    background-color: #fff;
}

.tg th {
    font-family: Arial, sans-serif;
    font-size: 14px;
    font-weight: normal;
    padding: 10px 5px;
    border-style: solid;
    border-width: 1px;
    overflow: hidden;
    word-break: normal;
    border-color: #ccc;
    color: #333;
    background-color: #f0f0f0;
}

.tg .tg-4eph {
    background-color: #f9f9f9
}
.parent{ 
position: relative; }
 .over-img
 { position: absolute;
    top: 20%;
    left: 20%; }
    
</style>

<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<script src="resources/js/manageAddress.js"></script>
<link href="${bootstrapcss}" rel="stylesheet" />

</head>
<body style='margin-top:10;margin-left:70;margin-right:0;'>
    <jsp:include page="navbar.jsp"/>
    <br> <br> <br> <br>
	<div>
<form action="search" method="post">
    <table style="width: 50%">
        <tr>
            <td style="width: 130px; ">Search Products</td>
            <td style="width: 135px; "><input type="search" name="pattern" class="tg"/></td>
            <td><input type="submit" value="Search" class="tg"/></td>
        </tr>
    </table>
    
</form>
</div>


	<h2>Products List</h2>
	
	
	<c:if test="${results.size() > 0}">
		<table style='margin-top:10;margin-left:200;margin-right:0;'>

			<c:forEach items="${results}" var="product">

				<td>
					<table>
                            <tr align="left" >
                                <td style="overflow:auto; width: 250px; ">
                                    <c:if test="${product.quantity > '0'}" >
                                        <a href="<c:url value='/product_detail/${product.id}' />"><img
                                                src="${product.image_link} " width=200 height=200
                                                style="border: 2px black dashed; border-radius: 10px; padding: 5px;"
                                                alt="${product.name} Image" title="${product.name} Image">
                                        </a>
                                    </c:if>
                                    <c:if test="${product.quantity == '0'}" >
                                        <a href="<c:url value='/product_detail/${product.id}' />">
                                        <div class="parent">
                                        <img src="${product.image_link} " width=200 height=200
                                                style="border: 2px black dashed; border-radius: 10px; padding: 5px;"
                                                alt="${product.name} Image" title="${product.name} Image" />
                                         <img src="https://www.freeiconspng.com/uploads/sold-out-png-33.png" width=100 height=100 class="over-img" />
                                         </div>
                                        </a>
                                    </c:if>
                                </td>
                            </tr>
                        <tr align="left" >
                            
                                <td >
                                <p style="overflow:auto; width: 200px; height:42px;" align="justify">
                                    <a href="<c:url value='/product_detail/${product.id}' />">
                                    
                                    ${product.name}</a>
                                    </p>
                                </td>
                                
                            </tr>
                            <tr>
                                <td>
                                <p style="overflow:auto; width: 200px; height:20px;" align="center">
                                Rs. <c:out value="${product.price}" />
                                </p>
                                </td>
                                
                            </tr>
                        </table>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${results.size() == 0}">
		<h2 align="center">Sorry no products found</h2>
	</c:if>


</body>
</html>