<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/post/css/list.css">
<link rel="stylesheet" href="/main/css/common.css">
<script src="/main/js/jquery.js"></script>
<script src="/main/js/jquery.bxslider.min.js"></script>
<script src="/main/js/isotope.pkgd.min.js"></script>
<script src="/main/js/common.js"></script>
<script src="/main/js/main.js"></script>
<link rel="stylesheet" href="/main/css/jquery.bxslider.min.css">
<link rel="stylesheet" href="/post/css/info.css">
<title>여행지 서울</title>
</head>
<body>
	<header id="common_header">
	    <img id="logo" src="/images/logo.png" width="165" height="58">
	    <div id="menu">
	        <a href="#"><b>Home</b></a>
	        <a href="/tour/list/area?tarea=seoul">Seoul</a>
	        <a href="/tour/list/area?tarea=tokyo">Tokyo</a>
	        <a href="/tour/list/area?tarea=paris">Paris</a>
	    </div>
	</header>
	<div id="contents">
		<div class = "tit_cont"><h2>#서울 ${post.tag}</h2></div>
		<div class="total_check">
		   <strong>총<span>55</span>건</strong>
		   <div class="btn_txt">
		      <button type="button">최신순</button>
		      <button type="button">인기순</button>
		      <button type="button">상세조회</button>
		   </div>
		</div>
		<c:forEach var="p" items="${postList}">
		<ul class="list_thumType">
	      <li class = "bdr_nor">
	      <div class="photo">
	         <a href="/post/list/${p.pid}"><img src= ${p.img} width ="140px" height ="94px"></a> 
	      </div>
	      <div class="area_txt">
		      <div class="tit">${p.title}</div>
		      <p>${p.content}</p>
		      <div class="ar_tag">#1박2일 #2박3일 #당일코스 #추천코스</div>
	      </div>
	      </li>
		</ul>
		</c:forEach>
	</div>
</body>
</html>