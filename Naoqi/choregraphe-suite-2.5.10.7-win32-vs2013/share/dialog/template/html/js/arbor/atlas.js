(function(){

var selectedNode = []
  
var sys = arbor.ParticleSystem(400, 1000,.5);
// remove
  var raiseEvent  = function(event, value) 
  {
    session.service("ALMemory").then(function (memory) 
    {
             memory.raiseEvent(event,value).then(function (result) 
        {
			
        });
         
        }, function (error) 
            {
                console.log("An error occurred: " + error);
            });
    }

function select(node)
{
    console.log("select")
    if (selectedNode.length==0)
    {
        raiseEvent("select1",node.data.label)
    }
    
    selectedNode.push(node)
    console.log(selectedNode);
    if (selectedNode.length==2)
    {
        sys.addEdge(selectedNode[0], selectedNode[1]);
        raiseEvent("select2",node.data.label)
    }
    
}
    
function unselect(node)
{
    
    console.log("unselect")

      for(var i = selectedNode.length; i--;) {
          if(selectedNode[i].data.label === node.data.label) {
              selectedNode.splice(i, 1);
              console.log("item removed")
          }
      }
    console.log(selectedNode);
  
}
  

// knowledge
var session = new QiSession();

//var arr;

  $(document).ready(function(){
    var used = {};

   

	sys.parameters({gravity:true});

	sys.renderer = Renderer("#viewport",select,unselect) ;
	//var sky = sys.addNode('test',{'color':'red','shape':'dot','label':'test'});
	//var est = sys.addNode('de',{'color':'yellow','shape':'dot','label':'de'});
	//var bleu = sys.addNode('base',{'color':'blue','shape':'dot','label':'base'});
    

	//sys.addEdge(sky, est);
	//sys.addEdge(est, bleu);
    //arr = [sky, est, bleu]
    
    

//	setInterval(function () {sys.addNode('aze',{'color':'blue','shape':'dot','label':'aze'});}, 3000);


      
      
      
  
function onKnowledge(domain, subject, predicat ,object )
{
  
  if (predicat == "CurrentEncounterInfo")
      return;
    
    if (predicat == "UserCreationInfo")
      return;
    
  if (predicat == "FirstEncounterInfo")
      return;
    
if (predicat == "LastEncounterInfo")
      return;
  console.log("knowledge added")
  if( subject in used )
  {
      used[subject] = used[subject]+1
  }
  else
  {
        used[subject] = 1
  }
    
  if( subject+predicat+object in used )
  {
      used[subject+predicat+object] = used[subject+predicat+object]+1
  }
  else
  {
        used[subject+predicat+object] = 1
  }
    
  if( object in used )
  {
      used[object] = used[object]+1
  }
  else
  {
        used[object] = 1
  }
    // do something

  
  if (subject==predicat)
  {
    var s = sys.addNode(subject,{'color':'blue','shape':'dot','label':subject});    
    return;
  }

  var s = sys.addNode(subject,{'color':'red','shape':'dot','label':subject});
  var p = sys.addNode(subject+predicat+object,{'color':'green','shape':'dot','label':predicat});
  var o = sys.addNode(object,{'color':'blue','shape':'dot','label':object});
	
  sys.addEdge(s, p);
  sys.addEdge(p, o);
  //sys.pruneNode(o)
}
      
      

function removeKnowledge(domain, subject, predicat ,object )
{

  if( subject in used )
  {
      used[subject] = used[subject]-1
  }
    
  if( subject+predicat+object in used )
  {
      used[subject+predicat+object] = used[subject+predicat+object]-1
  }
    
  if( object in used )
  {
      used[object] = used[object]-1
  }
    
  var s = sys.getNode(subject)
  var p = sys.getNode(subject+predicat+object)
  var o = sys.getNode(object)
  
  if( subject in used )
  {
      if (used[subject] <=0)
      {
        used[subject] = 0
        sys.pruneNode(s)
      }
  }
  
  if( subject+predicat+object in used )
  {
      if (used[subject+predicat+object] <=0)
      {
        used[subject+predicat+object] = 0
        sys.pruneNode(p)
      }
  }
    
      if( object in used )
  {
      if (used[object] <=0)
      {
        used[object] = 0
        sys.pruneNode(o)
      }
  }
    
  
}
  
    
var signalLink;
var learning;
// naoqi 2
    

// add
session.service("ALKnowledge").then(function (l) {
  learning = l;
  learning.knowledgeAdded.connect(onKnowledge).then(function (link) {
    signalLink = link;
  }, function (error) {
    console.log("An error occurred: " + error);
  });
});
      
// remove
  var get  = function(id) 
  {
    session.service("ALMemory").then(function (memory) 
    {
     
        console.log("memory")
        memory.getData(id).then(function (result) 
        {
		  
	              console.log(result)
                  removeKnowledge(result[0],result[1], result[2], result[3] )
			
        });
         
        }, function (error) 
            {
                console.log("An error occurred: " + error);
            });
    }


  
  
session.service("ALKnowledge").then(function (l) {
  learning = l;
  learning.knowledgeRemoved.connect(removeKnowledge).then(function (link) {
    signalLink = link;
  }, function (error) {
    console.log("An error occurred: " + error);
  });
});
      


      
      // end remove
      
  // start init
      


var initialKnowledge  = function() 
  {

    session.service("ALKnowledge").then(function (knowledge) 
    {
     
        knowledge.queryTriplet("com.aldebaran.learning","*","*","*").then(function (result) 
        {
		  
            for (var i in result) {
                    
                    console.log(result[i]);
                    if (result[i][2]!="rangeType")
                    {
                        onKnowledge(result[i][0],result[i][1],result[i][2],result[i][3])
                    }
                }
	      
         
         
        }, function (error) 
            {
                console.log("An error occurred: " + error);
            });
    })
  }

initialKnowledge()
      
// end init
      
      
  })
  
  	
})()


    
