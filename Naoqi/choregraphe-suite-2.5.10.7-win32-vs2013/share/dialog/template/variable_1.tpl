


  var get%variableshort%  = function(id) 
  {

    session.service("ALMemory").then(function (memory) 
    {
     
        console.log("memory")
        memory.getData("%variable%").then(function (result) 
        {
		  console.log("result" + result);
	      $scope.$apply(function () 
          {
			$scope.%variableshort%  = result;
          });
         
         
        }, function (error) 
            {
                console.log("An error occurred: " + error);
            });
    })
  }




session.service("ALMemory").then(function (ALMemory) {
  ALMemory.subscriber("%variable%").then(function (subscriber) {

    subscriber.signal.connect(function (state) {
        
     console.log("yop")
     $scope.$apply(function () {
		get%variableshort%();

        });
   
     
    });
  });
});
