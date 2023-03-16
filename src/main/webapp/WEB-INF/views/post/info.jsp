<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>여행지 서울</title>
<meta charset="EUC-KR">
	<link rel="stylesheet" href="/main/css/common.css">
<script src="/main/js/jquery.js"></script>
<script src="/main/js/jquery.bxslider.min.js"></script>
<script src="/main/js/isotope.pkgd.min.js"></script>
<script src="/main/js/common.js"></script>
<script src="/main/js/main.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="/main/css/jquery.bxslider.min.css">
<link rel="stylesheet" href="/post/css/info.css">
</head>
</head>
<body>
	<!--헤더 영역-->
	<div id="header-wrap">
		<header class="header-inner">
			<h1>
				<a href="/main"><img src="/main/images/daehan.png" width="200px" height="50px" alt="">
				<img src="/main/images/korea.png" width="80px" height="80px" alt="">
				<img src="/main/images/bang.png" width="200px" height="50px" alt=""></a>
			</h1>
			<button>
				<span class="blind">메뉴 열기</span> <span></span> <span></span> <span></span>
			</button>
			<ul class="util-menu">
			
			<c:set var="s" value="${sessionScope.sessionid }" />	
			
			<c:choose>
				<c:when test="${empty s}">
				<li><a href="/login">로그인</a></li></c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${s eq 'admin'}">
						<li><a>[관리자]</a></li>
						<li><a href="/logout">로그아웃</a></li></c:when>
						<c:otherwise>
						<li><a>${s}님 환영합니다</a></li>
						<li><a href="/logout">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>

			
				
				<li><a href="/join">회원가입</a></li>
				<li><a href="/board/list">게시판</a></li>
				<li><a href="#">마이페이지</a></li>
				<li><a href="#">고객센터</a></li>
			</ul>
			<nav id="gnb">
				<h2 class="blind">메인메뉴</h2>
				<ul>
					<li class="m1"><a href="/post/list?loc=seoul">서울</a>
						<ul>
							<li><a href="#">강북</a></li>
							<li><a href="#">강남</a></li>
							<li><a href="#">강서</a></li>
							<li><a href="#">강동</a></li>
						</ul></li>
					<li class="m2"><a href="/post/list?loc=busan">부산</a>
						<ul>
							<li><a href="#">중구</a></li>
							<li><a href="#">동구</a></li>
							<li><a href="#">서구</a></li>
							<li><a href="#">남구</a></li>
							<li><a href="#">북구</a></li>
						</ul></li>
					<li class="m3"><a href="/post/list?loc=jeju">제주</a>
						<ul>
							<li><a href="#">제주시</a></li>
							<li><a href="#">서귀포</a></li>
						</ul></li>
					<li class="m4 no-sub"><a href="#">공지사항</a></li>
				</ul>
			</nav>
			<button>
				<span class="blind">메뉴닫기</span> <span></span> <span></span>
			</button>
		</header>
	</div>

	 
	<div id="contents">
		<div class='titleType1'>
			<div class="tit"><h2>${post.title}</h2></div>
			<div class="area_address"><span class="address">${post.address}</span></div>
		</div>
		<div class="course_detail">
			<div class="wrap_contView">
				<div class="area_txtView">${post.content}</div>
			</div>
		</div>
		
		<div id="map" style="width:100%; height:500px;"></div>
	</div>
	<div id="map" style="width:100%; height:500px;"></div>

	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a8f261db701c3d43d7424b62afca4d55&libraries=services"></script>
	<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = {
	        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };  
	
	// 지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	// 주소로 좌표를 검색합니다
	geocoder.addressSearch('${post.address}', function(result, status) {
	
	    // 정상적으로 검색이 완료됐으면 
	     if (status === kakao.maps.services.Status.OK) {
	
	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	        // 결과값으로 받은 위치를 마커로 표시합니다
	        var marker = new kakao.maps.Marker({
	            map: map,
	            position: coords
	        });
	
	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        var infowindow = new kakao.maps.InfoWindow({
	            content: '<div style="width:150px;text-align:center;padding:6px 0;">${post.title}</div>'
	        });
	        infowindow.open(map, marker);
	
	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	        map.setCenter(coords);
	 	   } 
		});    
	</script>
</body>
</html>