var answer=new Object();
answer.setInitParam=function setInitParam(data){
    answer.type=data.type;
}
$(function() {
	var selfResult = $('#selfResult').val();
    var selfWrongFlag = 0;
    var selfRightFlag = 0;
    var reviewResult = $('#reviewResult').val();
    var reviewWrongFlag = 0;
    var reviewRightFlag = 0;
    //自评初始化
    if('' != selfResult && selfResult == 0){
    	$(".self-wrong").css({
            "background": "url(\"/images/wrong1.png\") no-repeat",
            "backgroundSize": "30px 30px"
        });
    	selfWrongFlag = 1;
    }else if(selfResult == 1){
    	$(".self-right").css({
            "background": "url(\"/images/right1.png\") no-repeat",
            "backgroundSize": "30px 30px"
        });
    	selfRightFlag = 1;
    }
    //街道初始化
    if('' != reviewResult && reviewResult == 0){
    	$(".review-wrong").css({
            "background": "url(\"/images/wrong1.png\") no-repeat",
            "backgroundSize": "30px 30px"
        });
    	reviewRightFlag = 1;
    }else if(reviewResult == 1){
    	$(".review-right").css({
            "background": "url(\"/images/right1.png\") no-repeat",
            "backgroundSize": "30px 30px"
        });
    	selfRightFlag = 1;
    }

    /****************************************************企业******************************************************/
    $(".self-wrong").click(function() {
        if (selfRightFlag == 1) {
            $(".self-right").css({
                "background": "url(\"/images/right.png\") no-repeat",
                "backgroundSize": "30px 30px"
            });
            selfRightFlag = 0;
            $('#selfResult').val("");
        };
        if (selfWrongFlag == 0) {
            $(this).css({
                "background": "url(\"/images/wrong1.png\") no-repeat",
                "backgroundSize": "30px 30px"
            });
            selfWrongFlag = 1;
            $('#selfResult').val(0);
        } else {
            $(this).css({
                "background": "url(\"/images/wrong.png\") no-repeat",
                "backgroundSize": "30px 30px"
            });
            selfWrongFlag = 0
            $('#selfResult').val("");
        }

    });
    $(".self-right").click(function() {
        if (selfWrongFlag == 1) {
            $(".self-wrong").css({
                "background": "url(\"/images/wrong.png\") no-repeat",
                "backgroundSize": "30px 30px"
            });
            selfWrongFlag = 0;
            $('#selfResult').val("");
        };
        if (selfRightFlag == 0) {
            $(this).css({
                "background": "url(\"/images/right1.png\") no-repeat",
                "backgroundSize": "30px 30px"
            });
            selfRightFlag = 1;
            $('#selfResult').val(1);
        } else {
            $(this).css({
                "background": "url(\"/images/right.png\") no-repeat",
                "backgroundSize": "30px 30px"
            });
            selfRightFlag = 0;
            $('#selfResult').val("");
        }
    })
    
});
$(".mui-popup-backdrop").click(function() {
	$(".mui-popup-backdrop").hide();
	$(".mui-popup").hide()
});
$(".cancel").click(function(){
	$(".mui-popup-backdrop").hide();
	$(".mui-popup").hide()
});  

$('.ff').click(function(){
	dialogAlert(M.dialog1, ff);
});

$('.yj').click(function(){
	var str = '';
	for(var i=0; i < regArray.length; i++){
		str += regArray[i].content;
	}

	dialogAlert(M.dialog2,str);
});

function dialogAlert(dialog, content){
    // 判断是否已存在，如果已存在则直接显示
    if(dialog){
        return dialog.show();
    }
    dialog = jqueryAlert({
        'content' : content,
        'modal'   : true,
        'contentTextAlign' : 'left',
        'width'   : '350px',
        'height' : '484px',
        'margin-top' : '-280.5px',
        'animateType' : 'linear',
        'buttons' :{
            '关闭' : function(){
                dialog.close();
            }
        }
    })
}
