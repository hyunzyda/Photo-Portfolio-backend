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
    <title>Bootstrap 5 with Masonry!</title>
    
    
</head>

<body>

    <div class="container py-5 mt-5">
        <h1>Bootstrap 5 with Masonry</h1>
        <div class="row row-cols-1 row-cols-md-3 g-4"  data-masonry='{"percentPosition": true,  "itemSelector": ".col" }'>

            <div class="col">
                <div class="card bg-primary">
                    <div class="card-body text-white">
                        <p style="height:200px;">This a text block A</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card bg-secondary">
                    <img src="https://kontext.tech/api/flex/medias/obj-2272" class="card-img-top" />
                    <div class="card-body text-white">This is block B with image</div>
                </div>
            </div>
            <div class="col">
                <div class="card bg-secondary">
                	<img src="${post.img}" class="card-img-top" />
                    <div class="card-body text-white">This a text block B</div>
                </div>
            </div>
            <div class="col">
                <div class="card bg-success">
                    <div class="card-body text-white">
                        <p style="height:100px;">This a text block C</p>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="card bg-info">
                    <div class="card-body text-white">This a text block D</div>
                </div>
            </div>
            
            
            <c:forEach var="p" items="${postList}">
            <div class="col">
                <div class="card bg-secondary">
                	<img src="${p.img}" class="card-img-top" />
                    <div class="card-body text-white">${p.content}</div>
                </div>
            </div>
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