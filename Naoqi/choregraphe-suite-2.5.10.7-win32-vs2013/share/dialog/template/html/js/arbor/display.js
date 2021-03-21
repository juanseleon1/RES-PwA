(function(){

 
  

  $(document).ready(function(){

   
	var sys = arbor.ParticleSystem(1000, 400,1);
	sys.parameters({gravity:true});

	sys.renderer = Renderer("#viewport") ;
	var animals = sys.addNode('Animals',{'color':'red','shape':'dot','label':'Animals'});
	var machin = sys.addNode('machin',{'color':'blue','shape':'dot','label':'machin'});
	var cat = sys.addNode('cat',{'color':'blue','shape':'dot','label':'cat'});

	sys.addEdge(animals, cat);
	sys.addEdge(animals, animals);
	sys.addEdge(machin, cat);

//	setInterval(function () {sys.addNode('aze',{'color':'blue','shape':'dot','label':'aze'});}, 3000);


  })
  
  
  
  
  
  
  
	
})()
