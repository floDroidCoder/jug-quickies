/** @jsx React.DOM */
var CarouselItem = React.createClass({
	render: function() {
		var className = 'item ' + (this.props.active ? 'active' : '');
		return (
			<div className={className}>
	  		    <img src={this.props.src} alt={this.props.alt} />
	  		</div>
	  	);
	}
});
var Carousel = React.createClass({
  render: function() {
  	var items = this.props.data.map(function(img, index) {
  		return (
  			<CarouselItem src={img.src} alt={img.alt} active={index==0}></CarouselItem>
  		);
  	});
  	var href= '#'+this.props.id;
  	return ( 
  		<div className="col-xs-12 col-md-6 col-md-offset-3">
			<div id={this.props.id} className="carousel slide" data-ride="carousel">
			  <div className="carousel-inner" role="listbox">
			    {items}
			  </div>
			  <a className="left carousel-control" href={href} role="button" data-slide="prev">
			    <span className="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			    <span className="sr-only">Previous</span>
			  </a>
			  <a className="right carousel-control" href={href} role="button" data-slide="next">
			    <span className="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			    <span className="sr-only">Next</span>
			  </a>
			</div>
		</div>
	);
  }
});
