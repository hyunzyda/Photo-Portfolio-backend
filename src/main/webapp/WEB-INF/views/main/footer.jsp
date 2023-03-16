<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> Trip | HOME </title>
<link rel="stylesheet" href="css/common.css">
<script src="js/jquery.js"></script>
<script src="js/jquery.bxslider.min.js"></script>
<script src="js/isotope.pkgd.min.js"></script>
<script src="js/common.js"></script>
<script src="js/main.js"></script>
<link rel="stylesheet" href="css/jquery.bxslider.min.css">
</head>
<script>
	$(function() {
		$(".slide_gallery").bxSlider({
			auto : true,
			autoControls : false,
			pagerCustom : ".slider-pager"
		});
	});
</script>
</head>
<body>
	<!--헤더 영역-->
	<div id="header-wrap">
		<header class="header-inner">
			<h1>
				<img src="images/daehan.png" width="200px" height="50px" alt="">
				<img src="images/korea.png" width="80px" height="80px" alt="">
				<img src="images/bang.png" width="200px" height="50px" alt="">
			</h1>
			<button>
				<span class="blind">메뉴 열기</span> <span></span> <span></span> <span></span>
			</button>
			<ul class="util-menu">
				<li><a href="#">로그인</a></li>
				<li><a href="#">회원가입</a></li>
				<li><a href="#">게시판</a></li>
				<li><a href="#">마이페이지</a></li>
				<li><a href="#">고객센터</a></li>
			</ul>
			<nav id="gnb">
				<h2 class="blind">메인메뉴</h2>
				<ul>
					<li class="m1"><a href="#">서울</a>
						<ul>
							<li><a href="#">강북</a></li>
							<li><a href="#">강남</a></li>
							<li><a href="#">강서</a></li>
							<li><a href="#">강동</a></li>
						</ul></li>
					<li class="m2"><a href="#">부산</a>
						<ul>
							<li><a href="#">중구</a></li>
							<li><a href="#">동구</a></li>
							<li><a href="#">서구</a></li>
							<li><a href="#">남구</a></li>
							<li><a href="#">북구</a></li>
						</ul></li>
					<li class="m3"><a href="#">제주</a>
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

	<!--//헤더 영역-->
	<!--콘테이너 영역-->
	<div id="container">
		<div id="gallery_wrap">
			<ul class="slide_gallery">
				<li><img class="img1" src="images/seoul.jpg" alt="사진1"></li>
				<li><img class="img1" src="images/seoul1.jpg" alt="사진2"></li>
				<li><img class="img1" src="images/busan.jpg" alt="사진3"></li>
				<li><img class="img1" src="images/busan1.jpg" alt="사진4"></li>
				<li><img class="img1" src="images/jeju.jpg" alt="사진5"></li>
				<li><img class="img1" src="images/jeju1.jpg" alt="사진6"></li>
			</ul>
			<ul class="slider-pager">
				<li><a href="#" data-slide-index="0"><img class="img2"
						src="images/seoul.jpg"></a></li>
				<li><a href="#" data-slide-index="1"><img class="img2"
						src="images/seoul1.jpg"></a></li>
				<li><a href="#" data-slide-index="2"><img class="img2"
						src="images/busan.jpg"></a></li>
				<li><a href="#" data-slide-index="3"><img class="img2"
						src="images/busan1.jpg"></a></li>
				<li><a href="#" data-slide-index="4"><img class="img2"
						src="images/jeju.jpg"></a></li>
				<li><a href="#" data-slide-index="5"><img class="img2"
						src="images/jeju1.jpg"></a></li>
			</ul>
		</div>
		<article id="trip_box">
			<div class="trip_box">
				<h4 class="place">서울</h4>
				<img class="small_pic" src="images/box_img/namsan.webp" alt="">
				<div class="word">용산</div>
				<div class="small_word">남산 타워</div>
				<br> <img class="small_pic" src="images/box_img/namdaemoon.jpg" alt="">
				<div class="word">중구</div>
				<div class="small_word">남대문</div>

			</div>

			<div class="trip_box">
				<h4 class="place">부산</h4>
				<img class="small_pic" src="images/box_img/hae.jfif" alt="">
				<div class="word">해운대</div>
				<div class="small_word">해운대 해수욕장</div>
				<br> <img class="small_pic" src="images/box_img/lottehotel.jpg" alt="">
				<div class="word">서면</div>
				<div class="small_word">롯데 호텔</div>
			</div>

			<div class="trip_box">
				<h4 class="place">제주</h4>
				<img class="small_pic" src="images/box_img/newstar.jpg" alt="">
				<div class="word">애월</div>
				<div class="small_word">새별오름</div>
				<br> <img class="small_pic" src="images/box_img/dragon.jpg" alt="">
				<div class="word">안덕</div>
				<div class="small_word">용머리 해안</div>
			</div>

			<div id="clear"></div>
			<div class="trip_box">
				<h4 class="place">서울</h4>
				<img class="small_pic" src="images/box_img/tower.jfif" alt="">
				<div class="word">강남</div>
				<div class="small_word">릇데 타워</div>
				<br> <img class="small_pic" src="images/box_img/palace.jpg" alt="">
				<div class="word">종로</div>
				<div class="small_word">경복궁</div>
			</div>

			<div class="trip_box">
				<h4 class="place">부산</h4>
				<img class="small_pic" src="images/box_img/bridge.jfif" alt="">
				<div class="word">수영</div>
				<div class="small_word">광안리</div>
				<br> <img class="small_pic" src="images/box_img/zul.webp" alt="">
				<div class="word">기장</div>
				<div class="small_word">해동 용궁사</div>
			</div>

			<div class="trip_box">
				<h4 class="place">제주</h4>
				<img class="small_pic" src="images/box_img/hanla.webp" alt="">
				<div class="word">토평</div>
				<div class="small_word">한라산</div>
				<br> <img class="small_pic" src="images/box_img/gold.webp" alt="">
				<div class="word">한림</div>
				<div class="small_word">금능 해수욕장</div>
			</div>

	</div>

	<!--//콘테이너 영역-->
	<!--푸터 영역-->
	<!--//푸터 영역-->
</body>
</html>
</html>