function saveMeme() {
	var id = $("#meme-id").val();
	var meme = {
		name: $("#meme-name").val(),
		description: $("#meme-description").val(),
		tags: $("#meme-tags").val().replace(/\s+/g, ' ').trim().split(/\s*,\s*/),
		pictureId: $("#meme-picture-id").val()
	};
	$.ajax({
		method: 'PUT',
		url: '/api/memes/' + id,
		data: JSON.stringify(meme),
		headers: {
			"Content-Type": "application/json"
		}
	})
		  .done(function (data) {
			  location.href = '/memes/' + id;
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