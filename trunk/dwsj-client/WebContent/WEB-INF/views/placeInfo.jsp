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
	<div style="clear: both; margin-top: 20px;"></div>
    <div class="container">
    	<div class="jumbotron">
    		<ul class="media-list">
    			<li>	
				     <div class='notifications top-left'></div>
			     </li>
				<li class="media">
					<c:if test="${not empty place}">
			        <div class="media-body">
				          <h4 class="media-heading">${place.name}</h4>
				          <p>${place.description}</p>
				          <div style="background-color: blue; height: 1px"></div>
				          <c:if test="${not empty images }">
				          	<c:forEach var="image" items="${images}">
						          <div class="media" style="margin-left: 25px;">
						            <a class="pull-left" href="#">
						              <img class="media-object" src="${image.url}" style="width: 80px; height: 80px;">
						            </a>
						            <div class="media-body">
						              <h4 class="media-heading"></h4>
						              ${image.information}
						              <div style="float: right;padding-top: 10px;padding-right: 16px;">
						              	<a href="#" onclick="showDialog(${image.id});">0 Comments</a> ||
						              	<a href="#">0 Rates</a>
						              </div>
						            </div>
						          </div>
					         </c:forEach>
				          </c:if>
				          <div style="clear: both; margin-top: 10px;"></div>
				          <div class="media" style="margin-left: 25px;">
					          	<form action="<c:url value="/${urlPattern}/addImage"/>" enctype="multipart/form-data" method="post">
					          		<input type="hidden" name="placeId" value="${place.id}" />
							        <textarea required="required" <c:if test="${empty user}">readonly="readonly" placeholder="Please login to add information" </c:if> placeholder="Information here ....." class="form-control" rows="5" name="information"></textarea>
							        <div style="clear: both; margin-top: 10px;"></div>
							        <input type="file" name="placeImage" accept="image/gif, image/jpeg, image/png"/>
							        <div style="clear: both; margin-top: 10px;"></div>
							        <input type="submit" class="btn btn-default" value="Upload" <c:if test="${empty user}">readonly="readonly"</c:if> />
							    </form>
				          </div>
			         </div>
			       </c:if>
			       <c:if test="${empty place}">
			       	<h2>Place is not found!!!</h2>
			       </c:if>
			     </li>
			</ul>
		</div>
    </div>
    
    
    <div class="modal fade" id="commentDialog">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">Comments</h4>
	      </div>
	      <div class="modal-body">
	        <div id="commentBody"></div>
	        <div id="commentDiv">
	        	<form action="" method="get" id="commentForm">
	          		<input type="hidden" name="imageId" id="imageId" value="" />
			        <textarea required="required" <c:if test="${empty user}">readonly="readonly" placeholder="Please login to write comment" </c:if> placeholder="Comment here ....." class="form-control" rows="5" name="commentData"></textarea>
			        <div style="clear: both; margin-top: 10px;"></div>
			        <input type="button" class="btn btn-default" value="Comment" <c:if test="${empty user}">readonly="readonly"</c:if> onclick="commentAction();"/>
			    </form>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
	<jsp:include page="layout/footer.jsp"></jsp:include>
	<c:if test="${notify == true}">
		<script type="text/javascript">
		    jQuery('.top-left').notify({
		       message: { text: 'Add successful!!!' }
		      }).show();
	    </script>
   </c:if>
   <c:if test="${notify == false and redirect > 0}">
		<script type="text/javascript">
		    jQuery('.top-left').notify({
		       message: { text: 'Add fail! Please contact with Administrator' },
		       type: "danger"
		      }).show();
	    </script>
   </c:if>
   <script type="text/javascript">
   		function showDialog(_id){
   			jQuery.ajax({
   			        url : '<c:url value="/${urlPattern}/commentOfImage"/>?imageId=' + _id,
   			        type: 'GET',
   			     	success: function(data) {
   			     		jQuery("#commentBody").html(data);
   			    	}
   			    });
   			jQuery('#imageId').val(_id);
   			jQuery('#commentDialog').modal('show');
   		}  		
   </script>
   
   <script type="text/javascript">
   		function commentAction(){
   			jQuery.ajax({
   			        url : '<c:url value="/${urlPattern}/commentOnImage"/>',
   			        type: 'GET',
   			     	data: jQuery('#commentForm').serialize(),
   			     	success: function(data) {
   			     		var parseJson = jQuery.parseJSON(data);
   			     		if(parseJson.status == "1"){
	   			     		jQuery.ajax({
	   		   			        url : '<c:url value="/${urlPattern}/commentOfImage"/>?imageId=' + parseJson.imageId,
	   		   			        type: 'GET',
	   		   			     	success: function(data) {
	   		   			     		jQuery("#commentBody").html(data);
	   		   			    	}
	   		   			    });
   			     		} else {
   			     			alert("Comment fail! Please contact with Administrator!");
   			     		}
   			    	}
   			    });
   		}
   </script>
</body>
</html>
