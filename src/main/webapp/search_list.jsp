<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>search_list</title>

<!--  Bootstrap  3.x  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>
	<table class="table">
		<tr class="warning">
			<th>상호</th>
			<th>주소(지번/도로명)</th>
			<th>전화</th>
			<th>거리(m)</th>
			<th>위치(위도/경도)</th>
		</tr>
		
		<!-- for(KakaoLocalVo vo : list) -->
		<c:forEach var="vo" items="${ list }">
			<tr>
				<td><a href="${ vo.place_url }" target="_blank">${ vo.place_name }</a></td> <!-- _blank -> 새창띄우기 -->
				<td>
					지번 : ${ vo.address_name }<br>
					도로명 : ${ vo.road_address_name }
				</td>
				<td>${ vo.phone }</td>
				<td>${ vo.distance }</td>
				<td>
					위도 : ${ vo.y }<br>
					경도 : ${ vo.x }
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>