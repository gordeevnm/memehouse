function memeTemplate(meme) {
	var tags = '';
	if (meme.tags) {
		for (var i = 0; i < meme.tags.length; i++) {
			tags += '<li><a href="/?tags=' + meme.tags[i] + '">' + meme.tags[i] + '</a></li>';
		}
	}
	
	var imgOrText;
	
	if (meme.pictureId) {
		imgOrText = '<a class="meme-image" href="/memes/' + meme.id + '"><img src="' + meme.pictureId + '"></a>'
	} else {
		imgOrText = '<a class="meme-text" href="/memes/' + meme.id + '">' + meme.description + '"</a>'
	}
	
	return '' +
		  '<div class="meme-wrapper">' +
		  '  <div class="meme">' +
		  imgOrText +
		  '    <div class="meme-title"><a href="/memes/' + meme.id + '">' + meme.name + '</a></div>' +
		  '    <div class="meme-tags">' +
		  '      <ul>' +
		  tags +
		  '      </ul>' +
		  '    </div>' +
		  '  </div>' +
		  '</div>';
}

function simpleSearch() {
	var query = $('#simple-search-query').val();
	$.ajax("/api/memes/search/simple?query=" + encodeURIComponent(query))
		  .done(function (data) {
			  var resultsContainer = $("#search-results");
			  resultsContainer.html('');
			  data.forEach(function (item) {
				  resultsContainer.append(memeTemplate(item));
			  });
		  })
		  .fail(function (data) {
			  show('error', JSON.stringify(data));
		  });
}

function show(type, data) {
	var opts = {
		addclass: "stack-bottomright"
	};
	switch (type) {
		case 'error':
			opts.title = "Oh No";
			opts.text = data;
			opts.type = "error";
			break;
		case 'success':
			opts.title = "Good News Everyone";
			opts.text = data;
			opts.type = "success";
			break;
	}
	new PNotify(opts);
}