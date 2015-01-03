 _.templateSettings = {
		    interpolate: /\<\@\=(.+?)\@\>/gim,
		    evaluate: /\<\@([\s\S]+?)\@\>/gim,
		    escape: /\<\@\-(.+?)\@\>/gim
		};