var bannerData = [
	{src:'img/ads_banner/kitten_0.gif', alt:'kitten_0'},
	{src:'img/ads_banner/kitten_1.gif', alt:'kitten_1'},
	{src:'img/ads_banner/kitten_2.gif', alt:'kitten_2'},
	{src:'img/ads_banner/kitten_3.gif', alt:'kitten_3'}
];
React.render(
  <div>
  	<Carousel data={bannerData} id="carousel-kitten"/>
  	<SessionList name="Next Sessions" url="/quickies" pollInterval="30000"/>
  	<SessionList name="Past Sessions" url="/quickies" pollInterval="30000"/>
  </div>,
  document.getElementById('content')
);