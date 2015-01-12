var listOpenData =  [ 
	{key:1, 
		name:"Spring boot : from zero to hero", date:"01.01.2015 19:00", location:'Hepia Salle 106 - Genève', url:"/sessions/1/detail", author : {name:'Florian Genaudet', id:0, url:'/users/0/detail'}, open: true, nbLike:10},
	{key:2, 
		name:"Elastic search", date:"02.01.2015 19:00", location:'Hepia Salle 106 - Genève', url:"/sessions/2/detail", author : {name:'Florian Genaudet', id:0, url:'/users/0/detail'}, open: true, nbLike:20},
	{key:3, 
		name:"Java 8 Hands-on", date:"03.01.2015 19:00", location:'Hepia Salle 106 - Genève', url:"/sessions/3/detail", author : {name:'Florian Genaudet', id:0, url:'/users/0/detail'}, open: true, nbLike:30}
];
var listClosedData =  [ 
	{key:1, 
		name:"Chaton etc...", date:"01.01.2015 19:00", location:'Hepia Salle 106 - Genève', url:"/sessions/1/detail", author : {name:'Florian Genaudet', id:0, url:'/users/0/detail'}, open: false, nbLike:10},
	{key:2, 
		name:"Chaton etc...", date:"02.01.2015 19:00", location:'Hepia Salle 106 - Genève', url:"/sessions/2/detail", author : {name:'Florian Genaudet', id:0, url:'/users/0/detail'}, open: false, nbLike:20},
	{key:3, 
		name:"Chaton etc...", date:"03.01.2015 19:00", location:'Hepia Salle 106 - Genève', url:"/sessions/3/detail", author : {name:'Florian Genaudet', id:0, url:'/users/0/detail'}, open: false, nbLike:30}
];
var bannerData = [
	{src:'img/ads_banner/kitten_0.gif', alt:'kitten_0'},
	{src:'img/ads_banner/kitten_1.gif', alt:'kitten_1'},
	{src:'img/ads_banner/kitten_2.gif', alt:'kitten_2'},
	{src:'img/ads_banner/kitten_3.gif', alt:'kitten_3'}
];
React.render(
  <div>
  	<Carousel data={bannerData} id="carousel-kitten"/>
  	<SessionList data={listOpenData} name="Next Sessions" url="/quickies"/>
  	<SessionList data={listClosedData} name="Past Sessions" url="/quickies"/>
  </div>,
  document.getElementById('content')
);