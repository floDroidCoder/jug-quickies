/** @jsx React.DOM */
var Session = React.createClass({
  render: function() {
  	var dateClass = 'session-date ' + (this.props.open ? 'open' : 'closed');
    return (
      <div className="session row">
      	<div className="col-xs-4 like">
      		{this.props.nbLike} <span className="glyphicon glyphicon-heart"/>
      	</div>
      	<div className="col-xs-8">
        	<p>
        		<a href={this.props.url}>{this.props.name}</a>
        	 	by {this.props.author}
        	 </p>
        	<p className={dateClass}>
        		<span className="glyphicon glyphicon-time"></span>{this.props.date}
        	</p>
        </div>
      </div>
    );
  }
});

var SessionList = React.createClass({
  render: function() {
  	var sessionNodes = this.props.data.map(function(session) {
  		return (
  			<Session name={session.name} 
  				date={session.date} 
  				url={session.url} 
  				open={session.open}
  				nbLike={session.nbLike}
  				author={session.author}></Session>
  		);
  	});
    return (
    <div className="col-sm-12 col-md-6">
	       <h1 className="text-center">{this.props.name}</h1>
	       <div className="session-list">
	        	{sessionNodes}
	      </div>
    </div>
    );
  }
});
