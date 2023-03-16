<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title> Trip | HOME </title>
<link rel="stylesheet" href="/main/css/common.css">
<script src="/main/js/jquery.js"></script>
<script src="/main/js/jquery.bxslider.min.js"></script>
<script src="/main/js/isotope.pkgd.min.js"></script>
<script src="/main/js/common.js"></script>
<script src="/main/js/main.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="/main/css/jquery.bxslider.min.css">
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

	<!--//헤더 영역-->
	<!--콘테이너 영역-->
	<div id="container">
		<div id="gallery_wrap">
			<ul class="slide_gallery">
				<li><img class="img1" src="/main/images/seoul.jpg" alt="사진1"></li>
				<li><img class="img1" src="/main/images/seoul1.jpg" alt="사진2"></li>
				<li><img class="img1" src="/main/images/busan.jpg" alt="사진3"></li>
				<li><img class="img1" src="/main/images/busan1.jpg" alt="사진4"></li>
				<li><img class="img1" src="/main/images/jeju.jpg" alt="사진5"></li>
				<li><img class="img1" src="/main/images/jeju1.jpg" alt="사진6"></li>
			</ul>
			<ul class="slider-pager">
				<li><a href="#" data-slide-index="0"><img class="img2"
						src="/main/images/seoul.jpg"></a></li>
				<li><a href="#" data-slide-index="1"><img class="img2"
						src="/main/images/seoul1.jpg"></a></li>
				<li><a href="#" data-slide-index="2"><img class="img2"
						src="/main/images/busan.jpg"></a></li>
				<li><a href="#" data-slide-index="3"><img class="img2"
						src="/main/images/busan1.jpg"></a></li>
				<li><a href="#" data-slide-index="4"><img class="img2"
						src="/main/images/jeju.jpg"></a></li>
				<li><a href="#" data-slide-index="5"><img class="img2"
						src="/main/images/jeju1.jpg"></a></li>
			</ul>
		</div>
		<article id="trip_box">
			<div class="trip_box">
				<h4 class="place">서울</h4>
				<img class="small_pic" src="/main/images/box_img/namsan.webp" alt="">
				<div class="word">용산</div>
				<div class="small_word">남산 타워</div>
				<br> <img class="small_pic" src="/main/images/box_img/namdaemoon.jpg" alt="">
				<div class="word">중구</div>
				<div class="small_word">남대문</div>

			</div>

			<div class="trip_box">
				<h4 class="place">부산</h4>
				<img class="small_pic" src="/main/images/box_img/hae.jfif" alt="">
				<div class="word">해운대</div>
				<div class="small_word">해운대 해수욕장</div>
				<br> <img class="small_pic" src="/main/images/box_img/lottehotel.jpg" alt="">
				<div class="word">서면</div>
				<div class="small_word">롯데 호텔</div>
			</div>

			<div class="trip_box">
				<h4 class="place">제주</h4>
				<img class="small_pic" src="/main/images/box_img/newstar.jpg" alt="">
				<div class="word">애월</div>
				<div class="small_word">새별오름</div>
				<br> <img class="small_pic" src="/main/images/box_img/dragon.jpg" alt="">
				<div class="word">안덕</div>
				<div class="small_word">용머리 해안</div>
			</div>

			<div id="clear"></div>
			<div class="trip_box">
				<h4 class="place">서울</h4>
				<img class="small_pic" src="/main/images/box_img/tower.jfif" alt="">
				<div class="word">강남</div>
				<div class="small_word">릇데 타워</div>
				<br> <img class="small_pic" src="/main/images/box_img/palace.jpg" alt="">
				<div class="word">종로</div>
				<div class="small_word">경복궁</div>
			</div>

			<div class="trip_box">
				<h4 class="place">부산</h4>
				<img class="small_pic" src="/main/images/box_img/bridge.jfif" alt="">
				<div class="word">수영</div>
				<div class="small_word">광안리</div>
				<br> <img class="small_pic" src="/main/images/box_img/zul.webp" alt="">
				<div class="word">기장</div>
				<div class="small_word">해동 용궁사</div>
			</div>

			<div class="trip_box">
				<h4 class="place">제주</h4>
				<img class="small_pic" src="/main/images/box_img/hanla.webp" alt="">
				<div class="word">토평</div>
				<div class="small_word">한라산</div>
				<br> <img class="small_pic" src="/main/images/box_img/gold.webp" alt="">
				<div class="word">한림</div>
				<div class="small_word">금능 해수욕장</div>
			</div>


		</article>
		<div class="line"></div>
		<div id="banner">
			<article id="customer">
				<img class="title" src="/main/images/customer.png" width="150" height="30">
				<img class="more" src="/main/images/more.png" width="30" height="10">
				<img class="phone" src="/main/images/call.png" width="220" height="95">
			</article>
			<article id="notice">
				<img class="title" src="/main/images/gong.png" width="150" height="30">
				<img class="more" src="/main/images/more.png" width="30" height="10">
				<div class="clear"></div>
				<ul>
					<li>
						<div class="subject">새해 복 많이 받으세요.</div>
						<div class="date">2023-01-01</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="subject">새해 복 많이 받으세요.</div>
						<div class="date">2022-01-01</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="subject">새해 복 많이 받으세요.</div>
						<div class="date">2021-01-01</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="subject">새해 복 많이 받으세요.</div>
						<div class="date">2020-01-01</div>
						<div class="clear"></div>
					</li>
					<li>
						<div class="subject">새해 복 많이 받으세요.</div>
						<div class="date">2019-01-01</div>
						<div class="clear"></div>
					</li>
				</ul>
			</article>

			<article id="faq">
				<img id="jejubanner" src="/main/images/jejubanner.jpg" onclick="javascript_:window.open('/main/images/fireposter.jpg', 'new', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no, width=550,height=700')"> 
				<img id="tour" src="/main/images/tour.jpg" onclick="javascript_:window.open('/main/images/tripposter.jpg', 'new', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no, width=450,height=600')"> 
				<img id="gamja" src="/main/images/gamja.jpg" onclick="javascript_:window.open('/main/images/gamja.jpg', 'new', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no, width=400,height=450')">
				<img id="loopy" src="/main/images/gif.gif" onclick="javascript_:window.open('https://www.youtube.com/embed/AYgsiY2TpXc', 'new', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no')">
			</article>
			<div class="clear"></div>
		</div>
	</div>

	<!--//콘테이너 영역-->
	<!--푸터 영역-->
	<div id="footer-wrap">
		<footer id="footer">

			<article id="address">
				<ul id="footer_menu">
					<li>개인정보 취급방침 | 이용 안내 | 이메일 무단수집 거부</li>
					<li>서민금융119서비스 | 보험범죄 신고 | 전자 민원 접수 | 보험약관 안내 | 금융사기신고센터</li>
				</ul>
				<ul id="company_info">
					<li>해적왕이될 사나이(주)</li>
					<li>대표이사 : 루피</li>
					<li>주소 : 써니호</li>
					<li>전화 : (02)123-1234, FAX : (02)134-4747</li>
					<li id="copyright">Copyright by 뽀로로 All Rights Reserved.</li>
				</ul>
			</article>
			<article id="link">
				<h3>고객센터 1577-1577</h3>
				<select>
					<option>네이버</option>
					<option>다음</option>
					<option>구글</option>
				</select>
				<div id="icons">
					<img src="/main/images/naver.png" width="20" height="20"> <img
						src="/main/images/daum.png" width="20" height="20"> <img
						src="/main/images/google.png" width="20" height="20">
				</div>
			</article>
		</footer>
	</div>
	<!--//푸터 영역-->
</body>
</html>
