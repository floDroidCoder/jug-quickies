/** @jsx React.DOM */
var Session = React.createClass({
  render: function() {
  	var dateClass = 'session-date ' + (this.props.session.open ? 'open' : 'closed');
    var user =  this.props.session.presenterName ? this.props.session.presenterName: this.props.session.email;
    var imgSrc = 'http://www.gravatar.com/avatar/' + this.props.session.presenterMD5;
    return (
      <div className="session row text-left">
      	<div className="col-xs-3 col-sm-2 like">
      		{this.props.session.nbVote} <span className="glyphicon glyphicon-heart-empty"/>
      	</div>
      	<div className="col-xs-6 col-sm-8 session-text">
        	<p>
        		<a href={'/quicky/' + this.props.session.id }>{this.props.session.title}</a>&nbsp;
        	 	by <a href={'/user/' + this.props.session.presenterId} className="authorLink">{user}</a>
        	 </p>
        	<p className={dateClass}>
        		<span className="glyphicon glyphicon-calendar"></span>&nbsp;{new Date(this.props.session.submissionDate).format('dd-mm-yyyy HH:MM')}
        	</p>
        	<p className="location">
        		<i className="fa fa-at"></i>&nbsp;{this.props.session.location}
        	</p>
        	<p className="usergroup">
	    		<i className="fa fa-group"></i>&nbsp;{this.props.session.usergroup}
	    	</p>
        </div>
        <div className="col-xs-3 col-sm-2 avatar">
            <img src={imgSrc} />
          </div>
      </div>
    );
  }
});

var SessionList = React.createClass({
  getInitialState: function() {
	    return {data: []};
  },
  loadData: function() {
	  $.ajax({
	      url: this.props.url,
	      dataType: 'json',
	      success: function(data) {
	        this.setState({data: data});
	      }.bind(this),
	      error: function(xhr, status, err) {
	        console.error(this.props.url, status, err.toString());
	      }.bind(this)
	    });
  },
  componentWillReceiveProps: function(nextProps) {
	  $.ajax({
	      url: nextProps.url,
	      dataType: 'json',
	      success: function(data) {
	        this.setState({data: data});
	      }.bind(this),
	      error: function(xhr, status, err) {
	        console.error(this.props.url, status, err.toString());
	      }.bind(this)
	  });
  },
  componentDidMount: function() { 
	this.loadData();
	setInterval(this.loadData, this.props.pollInterval);
  },
  render: function() {
  	var sessionNodes = this.state.data.map(function(session) {
  		return (
  			<Session key={session.id} session={session}></Session>
  		);
  	});
  	var noQuicky = (<div>No Quicky Yet</div>);
    return (
    <div className="col-sm-12 col-md-6">
	       <h1 className="text-center">{this.props.name}</h1>
	       <div className="session-list text-center">
		        {sessionNodes.length > 0 ? sessionNodes : noQuicky}
	      </div>
    </div>
    );
  }
});

var SessionFilter = React.createClass({
	getInitialState: function() {
	    return {usergroup: 'all', groupList: []};
	},
	changeUserGroup: function(usergroup) {
		this.setState({usergroup: usergroup});
	},
	loadGroups: function() {
		$.ajax({
	      url: '/usergroups',
	      dataType: 'json',
	      success: function(data) {
	        this.setState({groupList: data});
	      }.bind(this),
	      error: function(xhr, status, err) {
	        console.error(this.props.url, status, err.toString());
	      }.bind(this)
	    });
	},
	attachEvents: function() {
		var component = this;
		$(document).on('click', '#filters button', function() {
			$('#filters button').removeClass('btn-selected');
			$(this).addClass('btn-selected');
			component.changeUserGroup($(this).val());
		});
	},
	componentDidMount: function() {
		this.loadGroups();
		this.attachEvents();
	},
	render: function() {
		var baseUGUrl = this.props.baseUrl + this.state.usergroup;
		var urlTopPast = baseUGUrl + '/top-past';
		var urlTopFutur = baseUGUrl + '/top-futur';
		var urlAllPast = baseUGUrl + '/past';
		var urlAllFutur = baseUGUrl + '/futur';
		
		var groups = this.state.groupList.map(function(usergroup) {
		   return (
			   <div className="btn-group" role="group">
			   		<button type="button" className="btn btn-default" value={usergroup.name}>{usergroup.name}</button>
			   </div>
		  );
		});
		
		return (
				<div className="row" id="filters">
					<div className="btn-group btn-group-justified" role="group" aria-label="...">
					  <div className="btn-group" role="group">
					    <button type="button" className="btn btn-default btn-selected" value="ALL">All</button>
					  </div>
					  {groups}
					</div>
					<div className="row">
						<SessionList name="Top Past Quickies" url={urlTopPast} pollInterval="30000"/>
					  	<SessionList name="Top Futur Quickies" url={urlTopFutur} pollInterval="30000"/>
				  	</div>
				  	<div className="row">
					  	<SessionList name="All Past Quickies" url={urlAllPast} pollInterval="30000"/>
					  	<SessionList name="All Futur Quickies" url={urlAllFutur} pollInterval="30000"/>
				  	</div>
				</div>
		);
	}
});
