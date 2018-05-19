<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Memehouse</title>
	<link rel="stylesheet" href="/css/main.css">
	<script src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/pnotify.custom.min.js"></script>
	<link rel="stylesheet" href="/css/pnotify.custom.min.css">
	<script src="/js/saveMeme.js"></script>
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
		<button onclick="saveMeme()" class="edit-button">Сохранить</button>
	</header>
	<section class="main">
		<div class="edit-form">
			<#if meme.pictureId??>
				<img class="meme-full-image" src="${meme.pictureId!}">
			</#if>
			<div class="meme-full-info">
				<label>img</label>
				<input type="text" value="${meme.pictureId!}" id="meme-picture-id">
				<input type="text" hidden style="display: none" value="${meme.id}" id="meme-id">
				<h2>${meme.name}</h2>
				<label>Название</label>
				<input type="text" value="${meme.name}" id="meme-name">
				<label>Описание</label>
				<textarea style="font-size: 17px" id="meme-description">${meme.description}</textarea>
				<textarea id="meme-tags" rows="3" style="max-height: 60px;"><#list meme.tags as tag>${tag}<#if tag_has_next>, </#if></#list>
				</textarea>
			</div>
		</div>
	</section>
	<footer></footer>
</main>
</body>
</html>