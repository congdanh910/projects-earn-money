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
	   		<form id="searchForm" class="navbar-form" action='<c:url value="/${urlPattern}/search"/>' method="post">
		        <div class="form-group" style="display:inline;">
		          <div class="input-group">
		            <input type="text" class="form-control" name="search" value="${search}">
		            <span class="input-group-addon"><span class=""><a href="#" onclick="searchSubmit();">Search</a></span></span>
		          </div>
		        </div>
		     </form>
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
	<script type="text/javascript">
		function searchSubmit(){
			jQuery('#searchForm').submit();
		}
	</script>
	<script type="text/javascript">
		function activeMenu(){
			jQuery("#menuHome").addClass("active");
		}
		window.onload = activeMenu;
	</script>
</body>
</html>
