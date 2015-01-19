var bannerData = [
	{src:'img/ads_banner/kitten_0.gif', alt:'kitten_0'},
	{src:'img/ads_banner/kitten_1.gif', alt:'kitten_1'},
	{src:'img/ads_banner/kitten_2.gif', alt:'kitten_2'},
	{src:'img/ads_banner/kitten_3.gif', alt:'kitten_3'}
];
React.render(
  <div>
  	<Carousel data={bannerData} id="carousel-kitten"/>
  	<SessionFilter baseUrl="/quickies/"/>
  </div>,
  document.getElementById('content')
);