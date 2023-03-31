<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>


<title>User Profile</title>
<spring:url value="/resources/js/bootstrap.bundle.min.js"
    var="bootstrapjs" />
<spring:url value="/resources/js/jquery-1.11.1.min.js" var="jqueryjs" />
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapcss" />
<script src="${jqueryjs}"></script>
<script src="${bootstrapjs}"></script>
<script src="resources/js/manageAddress.js"></script>
<link href="${bootstrapcss}" rel="stylesheet" />

<html>
<head>
<title>Product Description</title>
<body>
    <jsp:include page="navbar.jsp"/>
    
        
    <style>
.parent {
    position: relative;
}

.over-img {
    position: absolute;
    top: 20%;
    left: 20%;
}
</style>
<br>
    <h3 align="left">${product.name}</h3>
    <br>
    <table style='margin-top:10;margin-left:70;margin-right:0;'>
        <tr>
            <td><c:choose>
                    <c:when test="${product.quantity > '0'}">
                        <img src="${product.image_link} " width=200 height=300
                            alt="${product.name} Image" title="${product.name} Image" />
                        <c:url var="addToCart" value="/addtocart"></c:url>

                        <form action="${addToCart}" method="POST">
                            <br>
                            <fieldset style="width: 223px;">

                                <legend>Select Quantity</legend>
                                <p>
                                    <label>Quantity </label> <select name="quantity">

                                        <c:forEach var="i" begin="1" end="${product.quantity}">
                                            <option value="${i}">${i}</option>
                                        </c:forEach>
                                    </select>
                                </p>
                            </fieldset>

                            <input type="hidden" name="prod_name" value="${product.name}">
                            <input type="hidden" name="prod_id" value="${product.id}">

                            <button type="submit"
                                style="width: 269px; color: Orange; height: 51px; font: bold 24px Arial;">Add
                                to Cart</button>
                        </form>

                    </c:when>

                    <c:otherwise>
                        <div class="parent">
                            <img src="${product.image_link} " width=200 height=300
                                alt="${product.name} Image" title="${product.name} Image" /> <img
                                src="https://www.freeiconspng.com/uploads/sold-out-png-33.png"
                                width=200 height=200 class="over-img" />
                        </div>
                        <br>
                        <br>
                        <button type="submit"
                            style="width: 269px; color: Red; height: 51px; font: bold 24px Arial;">Out
                            of Stock</button>

                        <br />
                    </c:otherwise>
                </c:choose></td>
            <td>
                <h4 >Product Specification:</h4>

                <table>
                    <tr align="left">
                        <th>Price:</th>
                        <td>Rs. ${product.price}</td>
                    </tr>
                    <tr>
                    <td>
                    <br><br><br>
                    </td></tr>
                    <tr align="left">
                        <th>Category  :</th>
                        <td>${product.category}</td>
                    </tr>
                    <tr>
                    <td>
                    <br><br><br>
                    </td></tr>
                    <tr align="left">
                        
                        <th>
                        <p style="overflow:auto; width: 100px; height:80px;" align="justify">
                        Description:
                        </p>
                        </th>
                 
                        <td>
                        <p style="overflow:auto; width: 400px; height:80px;" align="justify">
                                     ${product.description}
                                     
                        </p>
                        </td>
                        
                    </tr>
                </table>
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            </td>
        </tr>
    </table>


</body>
</html>