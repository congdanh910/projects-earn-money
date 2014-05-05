<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
	<head>
		<jsp:include page="layout/head.jsp"></jsp:include>
  	</head>
<body>
<c:if test="${not empty rates}">
	<div class="input select rating-f">
	 	<p style="float: left;margin-right: 10px;">
	    	Count : ${rates['1']} votes
	    </p>
	    <select id="rating1" name="rating">
	        <option value="1" selected="selected">1</option>
	        <option value="2">2</option>
	        <option value="3">3</option>
	        <option value="4">4</option>
	        <option value="5">5</option>
	    </select>
	</div>
	<div style="clear: both;"></div>
	<div class="input select rating-f">
		<p style="float: left;margin-right: 10px;">
	    	Count : ${rates['2']} votes
	    </p>  
	    <select id="rating2" name="rating">
	        <option value="1">1</option>
	        <option value="2" selected="selected">2</option>
	        <option value="3">3</option>
	        <option value="4">4</option>
	        <option value="5">5</option>
	    </select>
	</div>
	<div style="clear: both;"></div>
	<div class="input select rating-f">
		<p style="float: left;margin-right: 10px;">
	    	Count : ${rates['3']} votes
	    </p>   
	    <select id="rating3" name="rating">
	        <option value="1">1</option>
	        <option value="2">2</option>
	        <option value="3" selected="selected">3</option>
	        <option value="4">4</option>
	        <option value="5">5</option>
	    </select>
	</div>
	<div style="clear: both;"></div>
	<div class="input select rating-f">
		<p style="float: left;margin-right: 10px;">
	    	Count : ${rates['4']} votes
	    </p>       
	    <select id="rating4" name="rating">
	        <option value="1">1</option>
	        <option value="2">2</option>
	        <option value="3">3</option>
	        <option value="4" selected="selected">4</option>
	        <option value="5">5</option>
	    </select>
	</div>
	<div style="clear: both;"></div>
	<div class="input select rating-f">
		<p style="float: left;margin-right: 10px;">
	    	Count : ${rates['5']} votes
	    </p> 
	    <select id="rating5" name="rating">
	        <option value="1">1</option>
	        <option value="2">2</option>
	        <option value="3">3</option>
	        <option value="4">4</option>
	        <option value="5" selected="selected">5</option>
	    </select>
	</div>
</c:if>
<jsp:include page="layout/footer.jsp"></jsp:include>
<script type="text/javascript">
    $(function () {            
		$('#rating1').barrating({ showSelectedRating:false });			
		$('#rating2').barrating({ showSelectedRating:false });
		$('#rating3').barrating({ showSelectedRating:false });
		$('#rating4').barrating({ showSelectedRating:false });
		$('#rating5').barrating({ showSelectedRating:false });
    });
</script>
</body>
</html>