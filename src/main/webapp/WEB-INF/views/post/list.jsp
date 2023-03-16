<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
         <!-- Masonry scripts-->
    <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
    <title>${post.title}</title>
	
	
	<link rel="stylesheet" href="/main/css/common.css">
<script src="/main/js/jquery.js"></script>
<script src="/main/js/jquery.bxslider.min.js"></script>
<script src="/main/js/isotope.pkgd.min.js"></script>
<script src="/main/js/common.js"></script>
<script src="/main/js/main.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="/main/css/jquery.bxslider.min.css">
    
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

	<h1>${loc}</h1>

    <div class="container py-5 mt-5">
        <div class="row row-cols-1 row-cols-md-3 g-4"  data-masonry='{"percentPosition": true,  "itemSelector": ".col" }'>
            
            <c:forEach var="p" items="${postList}">
	            <c:if test="${p.loc == loc}">
		            <div class="col">
		                <div class="card bg-secondary">
		                	<div class="card-body text-white">${p.title}</div>
		                	<a href="/post/list/${p.pid}"><img src="${p.img}" class="card-img-top" /></a>
		                    <div class="card-body text-white">${p.content}</div>
		                </div>
		            </div>
	            </c:if>
			</c:forEach>
        </div>
    </div>


	<!-- Masonry layout-->
    <script>
        var $grid = document.querySelector('.row');
        var msnry = new Masonry($grid, {
            itemSelector: '.col',
            percentPosition: true
        });

        var $images = $grid.querySelectorAll('.card img');
        $images.forEach(function (el) {
            el.addEventListener('load', function () {
                console.log("Image is loaded: " + el.getAttribute("src"));
                msnry.layout();
            });
        });
    </script>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj"
        crossorigin="anonymous"></script>
    <!-- Masonry scripts-->
    <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
</body>

</html>