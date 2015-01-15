/** @jsx React.DOM */
var TwitterWall = React.createClass({
	_dateFormatter : function(date) {
		return date.toTimeString();
	},
	_handleTweets : function(tweets) {
		this.tweets = tweets;
		
		var x = tweets.length;
	    var n = 0;
	    var element = document.getElementById(this.props.domId);
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
		  "maxTweets": 20,
		  "enableLinks": true,
		  "showUser": true,
		  "showTime": true,
		  "showRetweet": false
	},
	_fetch: function() {
		this._config["domId"] = this.props.domId;
		this._config["dateFunction"] = this._dateFormatter;
		this._config["customCallback"] = this._handleTweets;
		
		twitterFetcher.fetch(this._config);
	},
	componentDidMount: function() {
		this._fetch();
		setInterval(this._fetch, this.props.pollInterval);
	},
	render: function() {
		return (<div></div>);
	}
});