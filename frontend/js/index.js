// TO DELETE
var data = [ 
	{key:1, name:"Chaton etc...", date:"01.01.2015 19:00", url:"/sessions/1/detail", author:'Florian', open: true, nbLike:10},
	{key:2, name:"Chaton etc...", date:"02.01.2015 19:00", url:"/sessions/2/detail", author:'Florian', open: true, nbLike:20},
	{key:3, name:"Chaton etc...", date:"03.01.2015 19:00", url:"/sessions/3/detail", author:'Florian', open: true, nbLike:30}
];

var Session = React.createClass({
  render: function() {
  	var divClass = 'col-xs-4 ' + this.props.open;
    return (
      <div className="session row">
      	<div className="col-xs-4 open like">
      		{this.props.nbLike} <span className="glyphicon glyphicon-heart"/>
      	</div>
      	<div className="col-xs-8">
        	<p>
        		<a href={this.props.url}>{this.props.name}</a>
        	 	by {this.props.author}
        	 </p>
        	<p className="session-date">
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
React.render(
  <div>
  	<SessionList data={data} name="Open Sessions"/>
  	<SessionList data={data} name="Past Sessions"/>
  </div>,
  document.getElementById('content')
);