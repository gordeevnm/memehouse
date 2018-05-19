window.addEventListener("load", initInterface);

function initInterface(e) {
	var searchToggler = document.querySelector(".search-box--toggler");

	searchToggler.addEventListener("click", function (e) {
		document.querySelector(".search-box--full").classList.toggle("hidden");
	});
}