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
	   		<ul class="media-list">
				<c:if test="${not empty places}">
					<c:forEach var="place" items="${places}">
						<li class="media">
						    <div class="media">
						      	<div class="media-body div-radius">
						        	<h4 class="media-heading"><a href='<c:url value="/${urlPattern}/placeInfo"/>?placeId=${place.id}'>${place.name}</a></h4>
						        	${place.description}
						      	</div>
				   			</div>
				 		</li>					
					</c:forEach>
				</c:if> 
			</ul>
		</div>
    </div>
	<jsp:include page="layout/footer.jsp"></jsp:include>
</body>
</html>
