<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Home</title>
		<jsp:include page="layout/head.jsp"></jsp:include>
		<style type="text/css">
			.div-radius {
				border:1px solid #a1a1a1;
				padding:10px 40px; 
				border-radius:10px;
			}
		</style>
  	</head>
<body>
	<jsp:include page="layout/menu.jsp"></jsp:include>
    <div class="container">
    	<div class="jumbotron">
		     <div style="clear: both; margin-top: 20px;"></div>
		     <c:if test="${error}">
		     	<p style="color: red;">Registry fail!!! Please contact with Administrator!</p>
		     </c:if>
		     <form role="form" action="<c:url value="/${urlPattern}/registerAction"/>">
			  <div class="form-group">
			    <label for="username">Username</label>
			    <input type="text" required="required" class="form-control" id="username" name="username" placeholder="Enter usernmae">
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <input type="password" required="required" class="form-control" id="password" name="password">
			  </div>
			  <div class="form-group">
			    <label for="fullName">Full Name</label>
			    <input type="text" required="required" class="form-control" id="fullName" name="fullName" placeholder="Enter your name">
			  </div>
			  <button type="submit" class="btn btn-default">Register</button>
			</form>
		</div>
    </div>
	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
