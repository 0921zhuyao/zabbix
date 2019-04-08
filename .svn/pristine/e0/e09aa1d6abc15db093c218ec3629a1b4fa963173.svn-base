function getSize(){

		var oHtml=document.documentElement;
		var viewWidth=oHtml.clientWidth;

		if(viewWidth<320){
			oHtml.style.fontSize='20px';
		}else if(viewWidth>720){
			oHtml.style.fontSize='45px';
		}else{
			oHtml.style.fontSize=viewWidth/16+'px';
		}
    }

    getSize();
    

    window.onresize=function(){

    	getSize();

    }