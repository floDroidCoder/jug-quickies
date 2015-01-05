/** @jsx React.DOM */
var SignIn = React.createClass({
	render: function() {
		return (
		  	<div className="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		  	  <div className="modal-dialog">
		  	    <div className="modal-content">
		  	      <div className="modal-header">
		  	        <button type="button" className="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span className="sr-only">Close</span></button>
		  	        <h4 className="modal-title" id="myModalLabel">Sign In</h4>
		  	      </div>
		  	      <div className="modal-body">
		  	        <div className="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					  <div className="panel panel-default">
					    <div className="panel-heading" role="tab" id="headingOne">
					      <h4 className="panel-title">
					        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					          Use my existing account
					        </a>
					      </h4>
					    </div>
					    <div id="collapseOne" className="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					      <div className="panel-body">
					        <form action="/j_security_check">
								<form className="form-horizontal" role="form">
								  <div className="form-group">
								    <label for="inputEmail3" className="col-sm-2 control-label">Email</label>
								    <div className="col-sm-10">
								      <input type="email" className="form-control" id="inputEmail3" placeholder="Email" />
								    </div>
								  </div>
								  <div className="form-group">
								    <label for="inputPassword3" className="col-sm-2 control-label">Password</label>
								    <div className="col-sm-10">
								      <input type="password" className="form-control" id="inputPassword3" placeholder="Password" />
								    </div>
								  </div>
								  <div className="form-group">
								    <div className="col-sm-offset-2 col-sm-10">
								      <div className="checkbox">
								        <label>
								          <input type="checkbox" /> Remember me
								        </label>
								      </div>
								    </div>
								  </div>
								</form>
					        </form>
					      </div>
					    </div>
					  </div>
					  <div className="panel panel-default">
					    <div className="panel-heading" role="tab" id="headingTwo">
					      <h4 className="panel-title">
					        <a className="collapsed" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
					          Create an account
					        </a>
					      </h4>
					    </div>
					    <div id="collapseTwo" className="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
					      <div className="panel-body">
					        <form className="form-horizontal" role="form">
								  <div className="form-group">
								    <label for="inputEmail3" className="col-sm-2 control-label">Email</label>
								    <div className="col-sm-10">
								      <input type="email" className="form-control" id="inputEmail3" placeholder="Email" />
								    </div>
								  </div>
								  <div className="form-group">
								    <label for="inputPassword3" className="col-sm-2 control-label">Password</label>
								    <div className="col-sm-10">
								      <input type="password" className="form-control" id="inputPassword3" placeholder="Password" />
								    </div>
								  </div>
								  <div className="form-group">
								    <label for="inputPassword4" className="col-sm-2 control-label">Confirm Password</label>
								    <div className="col-sm-10">
								      <input type="password" className="form-control" id="inputPassword4" placeholder="Password" />
								    </div>
								  </div>
								  <div className="form-group">
								    <div className="col-sm-offset-2 col-sm-10">
								      <div className="checkbox">
								        <label>
								          <input type="checkbox" /> Remember me
								        </label>
								      </div>
								    </div>
								  </div>
								</form>
					      </div>
					    </div>
					  </div>
					</div>
		  	      </div>
		  	      <div className="modal-footer">
		  	        <button type="button" className="btn btn-default" data-dismiss="modal">Close</button>
		  	        <button type="button" className="btn btn-primary">Sign in</button>
		  	      </div>
		  	    </div>
		  	  </div>
		  	</div>
		);
	}
});