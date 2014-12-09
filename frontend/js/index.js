// TO DELETE
var data = [ 
	{key:1, name:"Chaton etc...", date:"01.01.2015", url:"/sessions/1/detail", open: true},
	{key:2, name:"Chaton etc...", date:"01.01.2015", url:"/sessions/2/detail", open: true},
	{key:3, name:"Chaton etc...", date:"01.01.2015", url:"/sessions/3/detail", open: true}
];

var Session = React.createClass({
  render: function() {
  	var divClass = 'col-xs-4 ' + this.props.open;
    return (
      <div className="session row">
      	<div className="col-xs-4 open">
      		10 <span className="glyphicon glyphicon-heart"/>
      	</div>
        <a className="col-xs-4" href={this.props.url}>{this.props.name}</a> 
        <p className="col-xs-4 session-date">on {this.props.date}</p>
      </div>
    );
  }
});

var SessionList = React.createClass({
  render: function() {
  	var sessionNodes = this.props.data.map(function(session) {
  		return (
  			<Session name={session.name} date={session.date} url={session.url} open={session.open}></Session>
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