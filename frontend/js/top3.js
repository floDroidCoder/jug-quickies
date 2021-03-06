/** @jsx React.DOM */
var Session = React.createClass({
  render: function() {
  	var dateClass = 'session-date ' + (this.props.session.open ? 'open' : 'closed');

    return (
      <div className="session row">
      	<div className="col-xs-3 col-sm-2 like">
      		{this.props.session.nbLike} <span className="glyphicon glyphicon-heart-empty"/>
      	</div>
      	<div className="col-xs-9 col-sm-10 session-text">
        	<p>
        		<a href={this.props.session.url}>{this.props.session.name}</a>&nbsp;
        	 	by <a href={this.props.session.author.url} className="authorLink">{this.props.session.author.name}</a>
        	 </p>
        	<p className={dateClass}>
        		<span className="glyphicon glyphicon-calendar"></span>&nbsp;{this.props.session.date}
        	</p>
        	<p className="location">
        		<span className="glyphicon glyphicon-tree-conifer"></span>&nbsp;{this.props.session.location}
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
  			<Session session={session}></Session>
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
