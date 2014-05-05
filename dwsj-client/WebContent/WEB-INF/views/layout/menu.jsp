<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="urlPattern" value="dwsj" scope="session"/>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href='<c:url value="/${urlPattern}/home"/>'>DWSJ</a>
      </div>
      <div class="navbar-collapse collapse">
         <ul class="nav navbar-nav">
           <li class="active"><a href='<c:url value="/${urlPattern}/home"/>'>Home</a></li>
           <c:if test="${not empty user}">
           		<li><a href="<c:url value="/${urlPattern}/myPlace"/>">My Places</a></li>
           </c:if>
         </ul>
       <c:if test="${login == null or login == false}">
         	<div class="navbar-collapse collapse">
			  <form class="navbar-form navbar-right" role="form" action="<c:url value="/${urlPattern}/login"/>" method="post">
				<div class="form-group">
				  <input type="text" placeholder="Username" class="form-control" name="username">
				</div>
				<div class="form-group">
				  <input type="password" placeholder="Password" class="form-control" name="password">
				</div>
				<button type="submit" class="btn btn-success">Sign in</button>
				<button type="button" class="btn btn-success" onclick="window.location.href = '<c:url value="/${urlPattern}/register"/>';">Register</button>
			  </form>
			</div>
		</c:if>
		<c:if test="${login == true}">
			<ul class="nav navbar-nav navbar-right">
	            <li><a href="<c:url value="/${urlPattern}/logout"/>">Logout</a></li>
	         </ul>
		</c:if>
      </div>
    </div>
</div>