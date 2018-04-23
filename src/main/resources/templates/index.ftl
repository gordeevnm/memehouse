<!doctype html>
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	<meta charset="UTF-8">
	<meta name="viewport"
	      content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">
	<title>Memehouse</title>
	<#include "common/common_imports.ftl">
	<script src="/js/fullscreen_image.js"></script>
	<script>
		$(document).ready(function () {
			$('#extended-search-head').on('click', function () {
				$('#extended-search-params').collapse('toggle')
			});
			$('#extended-search-params')
				  .on('show.bs.collapse', function () {
					  console.log('show.bs.collapse');
					  $('#extended-search-head-icon').text('-');
				  })
				  .on('hide.bs.collapse', function () {
					  console.log('hide.bs.collapse');
					  $('#extended-search-head-icon').text('+');
				  });
		})
	</script>
</head>
<body>
<#include "common/navbar.ftl">
<div class="container">
	<form>
		<div class="form-row">
			<div class="col-8">
				<input type="text"
				       class="form-control"
				       placeholder="Recipient's username"
				       aria-label="Recipient's username"
				       aria-describedby="basic-addon2">
			</div>
			<div class="col-1">
				<button class="btn btn-outline-secondary" type="button">Button</button>
			</div>
		</div>
		<div class="form-row"
		     style="height: 30px;"
		     id="extended-search-head">
			<div class="col-8">
				Дополнительные параметры поиска
			</div>
			<div class="col-1" id="extended-search-head-icon">
				+
			</div>
		</div>
		<div id="extended-search-params" class="collapse">
			<div class="form-row">
				<div class="col">
					<input type="text"
					       class="form-control"
					       placeholder="Recipient's username"
					       aria-label="Recipient's username"
					       aria-describedby="basic-addon2">
				</div>
				<div class="col">
					<button class="btn btn-outline-secondary" type="button">Button</button>
				</div>
			</div>
			<div class="form-row">
				<div class="col">
					<input type="text"
					       class="form-control"
					       placeholder="Recipient's username"
					       aria-label="Recipient's username"
					       aria-describedby="basic-addon2">
				</div>
				<div class="col">
					<button class="btn btn-outline-secondary" type="button">Button</button>
				</div>
			</div>
			<div class="form-row">
				<div class="col">
					<input type="text"
					       class="form-control"
					       placeholder="Recipient's username"
					       aria-label="Recipient's username"
					       aria-describedby="basic-addon2">
				</div>
				<div class="col">
					<button class="btn btn-outline-secondary" type="button">Button</button>
				</div>
			</div>
			<div class="form-row">
				<div class="col">
					<input type="text"
					       class="form-control"
					       placeholder="Recipient's username"
					       aria-label="Recipient's username"
					       aria-describedby="basic-addon2">
				</div>
				<div class="col">
					<button class="btn btn-outline-secondary" type="button">Button</button>
				</div>
			</div>
		</div>
	</form>
</div>
<div class="container">
	<div class="card"
	     style="width: 18rem; margin: 10px">
		<img class="card-img-top meme-card-image"
		     src="https://cs8.pikabu.ru/images/big_size_comm/2017-08_6/1503760968124220552.jpg"
		     onclick="fullscreenImage('https://cs8.pikabu.ru/images/big_size_comm/2017-08_6/1503760968124220552.jpg')"
		     alt="Card image cap">
		<div class="card-body">
			<h5 class="card-title">Card title</h5>
			<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's
				content.</p>
			<a href="#" class="btn btn-primary">Go somewhere</a>
		</div>
	</div>
</div>
</body>
</html>