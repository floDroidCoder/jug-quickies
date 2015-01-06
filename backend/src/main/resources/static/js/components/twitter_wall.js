/*var config = {
  "id": '543076616558370818',
  "domId": 'twitter',
  "maxTweets": 20,
  "enableLinks": true,
  "showUser": true,
  "showTime": true,
  "dateFunction": dateFormatter,
  "customCallback": handleTweets,
  "showRetweet": false
};

function dateFormatter(date) {
  return date.toTimeString();
}

function handleTweets(tweets){
    var x = tweets.length;
    var n = 0;
    var element = document.getElementById('twitter');
    var html = '<div class="row">';
    while(n < x) {
      html += '<div class="col-xs-12 col-sm-6 col-md-4 twitWrapper">' + tweets[n] + '</div>';
      n++;
    }
    html += '</div>';
    element.innerHTML = html;
}

twitterFetcher.fetch(config);*/

var TwitterWall = {
		_dateFormatter : function(date) {
			return date.toTimeString();
		},
		_handleTweets : function(tweets) {
			var x = tweets.length;
		    var n = 0;
		    var element = document.getElementById(TwitterWall._config["domId"]);
		    var html = '<div class="row">';
		    while(n < x) {
		      html += '<div class="col-xs-12 col-sm-6 col-md-4 twitWrapper">' + tweets[n] + '</div>';
		      n++;
		    }
		    html += '</div>';
		    element.innerHTML = html;
		},
		_config : {
			  "id": '543076616558370818',
			  "domId": 'twitter',
			  "maxTweets": 20,
			  "enableLinks": true,
			  "showUser": true,
			  "showTime": true,
			  "showRetweet": false
		},
		fetch: function() {
			debugger;
			this._config["dateFunction"] = this._dateFormatter;
			this._config["customCallback"] = this._handleTweets;
			
			twitterFetcher.fetch(this._config);
		}
}