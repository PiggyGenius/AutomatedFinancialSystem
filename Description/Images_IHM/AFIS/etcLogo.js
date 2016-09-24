function etcSendRequest(aRequest,aId,aURL,aForm) {
//	if(!JSON) return true;
	// switch UL with a loading div
	$("#"+aId).html('<div class="loading"></div>');
	if(aForm !== undefined) aRequest += (aRequest ? "&" : "") + $("#"+aForm).serialize();
//	alert(aRequest);
  $.post(aURL, aRequest, function(data) {
    etcHandleResponse(data);
  });
    return false;
}

function etcHandleResponse(data) {
    var response = data;
    if(!(/##([\S\s]+)##/.test(response))) response = RegExp.$1;  //[\s\S]* matches all
//    alert(response);
    var update = new Array();
    var updatearray = response.split('#');
    for(i in updatearray)
      if(updatearray[i].indexOf('|') != -1) {
          update = updatearray[i].split('|');
          div2show = update[0];//.replace(/<!--.*-->|/g,"").replace(/^\s+|\s+$|/g,"");
          text = update[1];//.replace(/<!--.*-->|/g,"").replace(/^\s+|\s+$|/g,"");
//    alert(div2show+' ----------- '+text);
          $("#"+div2show).html(text); //.val(text);
      }
}
/*
function handleJSON() {
  if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
    var response = JSON.parse(xmlhttp.responseText);
//    alert(response);
    for(update in response.updatearray) changeText( update.div, update.text );
  }
}
function changeText( div2show, text ) {
    // Detect Browser
    if(!div2show) return;
    var IE = (document.all) ? 1 : 0;
    var DOM = 0, viewer = null; 
    if (parseInt(navigator.appVersion) >=5) {DOM=1};

    // Grab the content from the requested "div" and show it in the "container"
    if (DOM) {
        viewer = document.getElementById(div2show);
    }  else if(IE) {
        viewer = document.all[div2show];
    }
        if(viewer) {
          if(typeof viewer.value != 'undefined') viewer.value = text;
          else viewer.innerHTML = text;
        }
}
*/


if(logoCanvas = document.getElementById("logoCanvas")) {
  function rotateLogo(aCanvas, aAngle) {
    if(aCanvas.getContext) {
                    // Initaliase a 2-dimensional drawing context
       var context = aCanvas.getContext('2d');
       var cTime = new Date();
       var rad = (typeof aAngle=='undefined' ? cTime.getMinutes()/60 : aAngle);
	context.fillStyle = 'rgba(255,255,255,0.25)';
       context.fillRect(0,0,aCanvas.width,aCanvas.height);
                    //Rotate picture
       if(typeof aAngle=='undefined') context.setTransform(1, 0, 0, 1, 0, 0);
       context.translate(aCanvas.width/2,aCanvas.height/2);
       context.rotate(2*Math.PI*rad);
       context.translate(-aCanvas.width/2,-aCanvas.height/2);
                    //Load the image object in JS, then apply to canvas onload                    
       var myImage = new Image();
       myImage.onload = function() {
         context.drawImage(myImage,(aCanvas.width-myImage.width)/2,(aCanvas.height-myImage.height)/2);
       }
       myImage.src = document.getElementById("logo").src;
     }
  }
  function tictac(aURL) {
    $.get(aURL, function(data) {
      if($('#tictac').html()<data) {
        $('#tictac').html(data);
        $('#tictac').addClass('emphcolor');
        if($('#etc_events')) {
          $('#etc_events_clone').load('index.php?id=127 #etc_events', function() { 
            if($('#etc_events_clone').html() != $('#etc_events_container').html()) {$('#etc_events_container').fadeOut('fast').html($('#etc_events_clone').html()).fadeIn('slow');
            $("#flot").addClass("alert");}
          })
        }
      }
    });
  }
rotateLogo(logoCanvas);
setInterval("rotateLogo(logoCanvas)",60000);
setInterval("tictac('tictac.php')",60000);
}
