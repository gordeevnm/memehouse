<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Memehouse</title>
	<link rel="stylesheet" href="/css/main.css">
	<script src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/pnotify.custom.min.js"></script>
	<link rel="stylesheet" href="/css/pnotify.custom.min.css">
	<style>
		.meme-full-image {
			max-height: 400px;
			max-width: 600px;
			display: block;
			margin: auto;
		}

		.meme-full-info {
			max-width: 500px;
			display: block;
			margin: auto;
		}
	</style>
</head>
<body>
<main>
	<header>
		<a href="/" class="logo">memehouse</a>
		<button onclick="location.href='/memes/${meme.id}/edit'" class="edit-button">Редактировать</button>
	</header>
	<section class="main">
		<div class="edit-form">
			<#if meme.pictureId??>
				<img class="meme-full-image" src="${meme.pictureId}">
			</#if>
			<div class="meme-full-info">
				<h2>${meme.name}</h2>
				<label style="font-size: 17px">${meme.description}</label>
				<div class="meme-tags">
					<ul>
					<#list meme.tags as tag>
						<li><a href="/?tags=${tag}">${tag}</a></li>
					</#list>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<footer></footer>
</main>
</body>
</html>