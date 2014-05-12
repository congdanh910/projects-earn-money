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
	   			<li>	
				     <div class='notifications top-left'></div>
			     </li>
				<c:if test="${not empty places}">
					<c:forEach var="place" items="${places}">
						<li class="media">
							<div class="media div-radius" style="margin-left: 25px;">
					            <a class="pull-left" href="#">
					              <img class="media-object" src="${place.placeImage}" style="width: 80px; height: 80px;">
					            </a>
					            <div class="media-body">
					              <h4 class="media-heading"><a href='<c:url value="/${urlPattern}/placeInfo"/>?placeId=${place.id}'>${place.name}</a></h4>
							        ${place.description}
					            </div>
				            </div>
				 		</li>				
					</c:forEach>
					<div style="clear: both; margin-top: 10px;"></div>
					<div style="background-color: blue; height: 1px"></div>
				</c:if> 
			</ul>
			<div style="clear: both; margin-top: 10px;"></div>
		      <div class="media" style="margin-left: 25px;">
		          	<form action="<c:url value="/${urlPattern}/addPlace"/>" enctype="multipart/form-data" method="post">
			            <input required="required" type="text" class="form-control" name="placeName" value="" placeholder="Short description of place here......">
		          		<div style="clear: both; margin-top: 10px;"></div>
				        <textarea required="required" <c:if test="${empty user}">readonly="readonly" placeholder="Please login to add place information" </c:if> 
				        	placeholder="Place information here ....." class="form-control" rows="5" name="placeInformation"></textarea>
				        <div style="clear: both; margin-top: 10px;"></div>
				        <input type="file" name="placeImage" accept="image/gif, image/jpeg, image/png" required="required"/>
				        <div style="clear: both; margin-top: 10px;"></div>
				        <input type="submit" class="btn btn-default" value="Add" <c:if test="${empty user}">disabled="disabled"</c:if> />
				    </form>
		   	</div>
		</div>
    </div>
	<jsp:include page="layout/footer.jsp"></jsp:include>
	<!-- 	Notify -->
	<c:if test="${notify == true}">
		<script type="text/javascript">
		    jQuery('.top-left').notify({
		       message: { text: 'Add successful!!!' }
		      }).show();
	    </script>
   </c:if>
   <script type="text/javascript">
		function activeMenu(){
			jQuery("#menuMyPlaces").addClass("active");
		}
		window.onload = activeMenu;
	</script>   
</body>
</html>
