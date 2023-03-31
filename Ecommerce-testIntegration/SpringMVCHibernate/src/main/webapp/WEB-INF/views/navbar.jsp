<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
<nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">
    <img src="<c:url value="/resources/img/salesforce.svg" />" width="50" height="30" class="d-inline-block align-top" alt="">
    FutureForce.com
  </a>
  <div class="collapse navbar-collapse justify-content-end" id="navbarSupportedContent">
    <ul class="navbar-nav">
    	<!-- For customer -->
    <c:if test="${sessionScope.role == 'customer'}">
      <li class="nav-item <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/products'}">active</c:if>">
        <a class="nav-link" href="/Ecommerce/products">Product Catalogue</a>      
      </li>
      <li class="nav-item <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/viewcart'}">active</c:if>">
        <a class="nav-link" href="/Ecommerce/viewcart">Shopping Cart</a>      
      </li>
    </c:if>      
    
    <!-- For vendor/manager -->
    <c:if test="${sessionScope.role == 'manager'}">
      <li class="nav-item <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/inventoryHome'}">active</c:if>">
        <a class="nav-link" href="/Ecommerce/inventoryHome">Inventory</a>      
      </li>
    </c:if>
    
    <!-- For Delivery Exec -->
    <c:if test="${sessionScope.role == 'delivery'}">
      <li class="nav-item <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/delivery/${sessionScope.userID}'}">active</c:if>">
        <a class="nav-link" href="/Ecommerce/delivery/${sessionScope.userID}">Your Deliveries</a>      
      </li>
    </c:if>
    
    <!-- For Delivery Admin -->
    <c:if test="${sessionScope.role == 'da'}">
      <li class="nav-item <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/deliveryAdmin'}">active</c:if>">
        <a class="nav-link" href="/Ecommerce/deliveryAdmin">Manage Deliveries</a>      
      </li>
    </c:if>
    
    <c:if test="${sessionScope.userID==null }" >
    	<li class="nav-item <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/login'}">active</c:if>">
        	<a class="nav-link" href="/Ecommerce/login">Login</a>      
      	</li>
      	<li class="nav-item <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/signup'}">active</c:if>">
        	<a class="nav-link" href="/Ecommerce/signup">Signup</a>      
      	</li>
    
    </c:if>
    
    <c:if test="${sessionScope.userID!=null }" >
    
      <li class="nav-item dropdown <c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/profile'}">active</c:if>">
        <a  class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Hello ${sessionScope.firstName} ! 
        </a>
        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="/Ecommerce/profile">Profile</a>          
          <c:if test="${sessionScope.role == 'customer'}">
          	<c:if test="${requestScope['javax.servlet.forward.request_uri'] == '/Ecommerce/profile'}" >
        		<a class="dropdown-item" href="#yourOrders">Your Orders</a>
        		<a class="dropdown-item" href="#manageAddresses">Manage Addresses</a>        		
          	</c:if>	
          </c:if>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="/Ecommerce/logout">Logout</a>
          
        </div>
      </li>
      </c:if>
    </ul>
    
  </div>
</nav>
</div>

<div style="margin-bottom:2cm;"></div>




