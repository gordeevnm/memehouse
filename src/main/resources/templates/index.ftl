<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Memehouse</title>
	<script src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/pnotify.custom.min.js"></script>
	<link rel="stylesheet" href="/css/pnotify.custom.min.css">

	<link rel="stylesheet" href="/css/main.css">
	<script src="/js/interface.js"></script>
	<script src="/js/simpleSearch.js"></script>
</head>
<body>
<main>
	<header>
		<a href="/" class="logo">memehouse</a>
	</header>
	<section class="main">
		<div class="search-container">
			<div class="search-box">
				<div class="search-box--quick">
					<input autofocus id="simple-search-query" type="text" ="simpleSearch()">
					<button onclick="simpleSearch()" id="simple-search-button" type="button">Искать</button>
				</div>
				<span class="search-box--toggler">
					Расширенный поиск
				</span>
				<div class="search-box--full hidden">
					<label>smth</label>
					<input type="text">
					<button class="btn">искать</button>
				</div>
			</div>
			<div class="search-results" id="search-results">
			</div>
		</div>
	</section>
	<footer></footer>
</main>
<script>
	$("#simple-search-query").keyup(function(event) {
		if (event.keyCode === 13) {
			$("#simple-search-button").click();
		}
	});
</script>
</body>
</html>