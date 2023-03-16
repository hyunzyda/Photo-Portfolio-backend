<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=a8f261db701c3d43d7424b62afca4d55&libraries=services"></script>

<style>
	#first {
		padding: 10px 0px;
		margin: 10px;
		width : 250px;
		border: 1px solid;
	}
	#third {
		padding: 10px 0px;
		margin: 10px;

	}
	#fourth {
		padding: 10px 0px;
		margin: 10px;

	}
	#fifth {
		padding: 10px 0px;
		margin: 10px;

	}
	#db{
		margin: 10px;
		padding: 10px 0px;

	}
    .in-line{
    	  display: inline-block;
  margin: 0;
    }
		/* IE */
		select::-ms-expand { 
			display: none;
		}
		.select {
			
		  -o-appearance: none;
		  -webkit-appearance: none;
		  -moz-appearance: none;
		  appearance: none;	
		  background: #BAD1E6;	  
		}
		.select option {
		  background: #BAD1E6;
		  color: #fff;

			}
</style>
<title>여행지 정보</title>
</head>
<body>
	<div id="first"><h2>여행지 정보 추가</h2></div>
	<div id="fifth"><button class="btn btn-outline-info mb-3" type="button"><a href="/post/list"> 리스트 불러오기 </button></a></div>
	<div id="fifth"><button class="btn btn-outline-info mb-3" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal" > 여행지 등록 </button></div>
		
	<div id="db"><button class="btn btn-outline-info mb-3" type="button" data-bs-toggle="collapse" data-bs-target="#addForm" aria-expanded="false" aria-controls="addForm"> DB 체크 </button>
		<div class="collapse" id="addForm">
			
	<div id="third">
		<table class="table table-dark table-striped">
			<tr>
				<th>id</th>
				<th>주소</th>
				<th>제목</th>
				<th>내용</th>
				<th>이미지</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="p" items="${postList}">
				<tr onclick="location.href='/post/${t.pid}';" class="text-decoration-none" style="cursor: pointer;">
					<td>${p.pid}</td>
					<td>${p.address}</td>
					<td>${p.title}</td>
					<td>${p.content}</td>
					<td>${p.img}</td>
					<td><a href="/post/delete/${p.pid}"><span class="badge bg-secondary"> &times; </span></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	
	
	<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" style="background-color: skyblue">
  <div class="modal-dialog modal-lg modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">관광지 소개</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      
      
			<form method="post" action="/post/add" enctype="multipart/form-data">
				<label class="form-label" >제목</label>
				<input type="text" name="title" class="form-control"/>
				
				<label class="form-label">내용</label>
				<textarea cols="50" rows="5" name="content" class="form-control"></textarea>
				
				<label class="form-label">이미지</label>
				<input type="file" name="file"  class="form-control" />
				
				<label class="form-label">주소</label>	
				<br>
			
					
					<input class="in-line" type="text" name="address" id="address"  placeholder="주소" class="form-control">
					
					<input type="button" onclick="sample5_execDaumPostcode()" name="address" style="background: #BAD1E6" value="주소 검색"><br>

					 <label>지역 선택</label></br>
					<select name="loc" id="loc" class="select">
					 
					  <option disabled selected>지역 선택 🍊</option>
					  <option value="seoul">서울</option>
					  <option value="busan">부산</option>
					  <option value="jeju">제주</option>
					</select>
					
				<button type="submit" class="btn btn-success mt-3" style="display: block; margin: auto; background: #BAD1E6" >등록</button>
			</form>
		</div>
	</div>
	
<script>
   function sample5_execDaumPostcode() {
       new daum.Postcode({
           oncomplete: function(data) {
               var addr = data.address; // 최종 주소 변수

               // 주소 정보를 해당 필드에 넣는다.
               document.getElementById("address").value = addr;
		}
	}).open();
   }
</script>
</div>
</div>
</div>
</div>

</body>
</html>