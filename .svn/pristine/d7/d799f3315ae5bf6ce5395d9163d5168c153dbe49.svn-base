var answer=new Object();
answer.setInitParam=function setInitParam(data){
    answer.type=data.type;
    //初始化答题展示页面
    if(answer.type==1){
        $("#qydt").parent().parent().addClass("showAnswer");
    }else if(answer.type==0){
        $("#jdfp").parent().parent().addClass("showAnswer");
    }
}
$(function() {
    var selfResult = $('#selfResult').val();
    var reviewResult = $('#reviewResult').val();
    var selfWrongFlag = 0;
    var selfRightFlag = 0;
    var reviewWrongFlag = 0;
    var reviewRightFlag = 0;
    //自评初始化
    if('' != selfResult && selfResult == 0){
        $(".self-wrong").css({"backgroundColor": "#d72822",color: 'white',border: '1px solid rgb(215, 40, 34)'});
        selfWrongFlag = 1;
    }else if(selfResult == 1){
        $(".self-right").css({"backgroundColor": "#10AD83",color: 'white',border: '1px solid rgb(16, 173, 131)'});
        selfRightFlag = 1;
    }
    //复评初始化
    if('' != reviewResult && reviewResult == 0){
        $(".review-wrong").css({"backgroundColor": "#d72822",color: 'white',border: '1px solid rgb(215, 40, 34)'});
        reviewWrongFlag = 1;
    }else if(reviewResult == 1){
        $(".review-right").css({"backgroundColor": "#10AD83",color: 'white',border: '1px solid rgb(16, 173, 131)'});
        reviewRightFlag = 1;
    }
    /****************************************************自评******************************************************/
    if(answer.type=='1'){
        $("#reviewRemark").attr("disabled","disabled");
        $("#file2").attr("disabled","disabled");
        $(".self-wrong").click(function() {
            if (selfRightFlag == 1) {
                $(".self-right").css({"backgroundColor": "#f8f8fb",color: '#5b5b5b',border: '1px solid #9aa0aa'});
                selfRightFlag = 0;
                $('#selfResult').val("");
            };
            if (selfWrongFlag == 0) {
                $(this).css({"backgroundColor": "#d72822",color: 'white',border: '1px solid rgb(215, 40, 34)'});
                selfWrongFlag = 1;
                $('#selfResult').val(0);
            } else {
                $(this).css({"backgroundColor": "#f8f8fb",color: '#5b5b5b',border: '1px solid #9aa0aa'});
                selfWrongFlag = 0
                $('#selfResult').val("");
            }

        });
        $(".self-right").click(function() {
            if (selfWrongFlag == 1) {
                $(".self-wrong").css({"backgroundColor": "#f8f8fb",color: '#5b5b5b',border: '1px solid #9aa0aa'});
                selfWrongFlag = 0;
                $('#selfResult').val("");
            };
            if (selfRightFlag == 0) {
                $(this).css({"backgroundColor": "#10AD83",color: 'white',border: '1px solid rgb(16, 173, 131)'});
                selfRightFlag = 1;
                $('#selfResult').val(1);
            } else {
                $(this).css({"backgroundColor": "#f8f8fb",color: '#5b5b5b',border: '1px solid #9aa0aa'});
                selfRightFlag = 0;
                $('#selfResult').val("");
            }
        });
    }
    /****************************************************复评******************************************************/
    if(answer.type=='0'){
        $("#selfRemark").attr("disabled","disabled");
        $("#file1").attr("disabled","disabled");
        $(".review-wrong").click(function() {
            if (reviewRightFlag == 1) {
                $(".review-right").css({"backgroundColor": "#f8f8fb",color: '#5b5b5b',border: '1px solid #9aa0aa'});
                reviewRightFlag = 0;
                $('#reviewResult').val("");
            };
            if (reviewWrongFlag == 0) {
                $(this).css({"backgroundColor": "#d72822",color: 'white',border: '1px solid rgb(215, 40, 34)'});
                reviewWrongFlag = 1;
                $('#reviewResult').val(0);
            } else {
                $(this).css({"backgroundColor": "#f8f8fb",color: '#5b5b5b',border: '1px solid #9aa0aa'});
                reviewWrongFlag = 0
                $('#reviewResult').val("");
            }

        });
        $(".review-right").click(function() {
            if (reviewWrongFlag == 1) {
                $(".review-wrong").css({"backgroundColor": "#f8f8fb",color: '#5b5b5b',border: '1px solid #9aa0aa'});
                reviewWrongFlag = 0;
                $('#reviewResult').val("");
            };
            if (reviewRightFlag == 0) {
                $(this).css({"backgroundColor": "#10AD83",color: 'white',border: '1px solid rgb(16, 173, 131)'});
                reviewRightFlag = 1;
                $('#reviewResult').val(1);
            } else {
                $(this).css({"backgroundColor": "#f8f8fb",color: '#5b5b5b',border: '1px solid #9aa0aa'});
                reviewRightFlag = 0;
                $('#reviewResult').val("");
            }
        });
    }
    $(".mui-popup-backdrop").click(function() {
        $(".mui-popup-backdrop").hide();
        $(".mui-popup").hide()
    });
    $(".cancel").click(function(){
        $(".mui-popup-backdrop").hide();
        $(".mui-popup").hide()
    });
});
function tishi(msg,fun){
    var btnArray = ['取消', '确定'];
    mui.confirm( msg,'提示', btnArray, function(e) {
        if (e.index == 1) {
            //重新登录，有了就加上
            fun();
        }
    })
}
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
//跳过
$('#next-btn').click(function(){
    if (!totalNum) {
        tishi('您的登录已过期，是否重新登录？');
        return false;
    }
    if(questions[nowNum]){
        var mask = mui.createMask(function(){return false});//callback为用户点击蒙版时自动执行的回调；
        mask.show();//显示遮罩
        history.replaceState('a',null,"/app/companyChecktable/answereCheckTable/"+questions[nowNum]+"/"+companyId+"/"+(nowNum+1));
        window.location.reload();
    }else{
        tishi("已经是最后一题了,是否返回目录",function(){goback();});
    }
});

//数据提交
$('#submit-btn').click(function(){
    if (!totalNum) {
        tishi('您的登录已过期，是否重新登录？');
        return false;
    }
    submit();
});

function toInsertOrUpdate(){
    if(questions[nowNum]){
        var mask = mui.createMask(function(){return false});//callback为用户点击蒙版时自动执行的回调；
        mask.show();//显示遮罩
        history.replaceState('a',null,"/app/companyChecktable/answereCheckTable/"+questions[nowNum]+"/"+companyId+"/"+(nowNum+1));
        window.location.reload();
    }else{
        tishi("已经是最后一题了,是否返回目录",function(){goback();});
    }
}
//提交数据，根据不同用户调用不同接口
function submit(fileObj){
    $("#submit-btn").attr("disabled","disabled");
    var data;
    if(answer.type == 1){
        if(taskStatus == 0 || taskStatus == 1){
            data = $('#companyCheckTableForm1').serializeJSON();
            data.tempFileId=$("#tempFileId").val();
            if(!data.selfResult){
                $("#submit-btn").attr("disabled",false);
                mui.alert("您还未选择");
                return false;
            }
            $("body").mLoading();
            upLoad.ajaxUpLoad({
                url: "/app/companyChecktable/add",
                param:data
            },fileObj,function(msg){
                if(msg==-1){
                    $("#submit-btn").attr("disabled",false);
                    return false;
                }
                toInsertOrUpdate();
            });
            $("body").mLoading("hide");
        }else{
            mui.alert("当前状态无法进行修改")
        }
    }else if(answer.type==0){
        if(taskStatus == 2){
            data = $('#companyCheckTableForm2').serializeJSON();
            data.tempFileId=$("#tempFileId").val();
            //data.push({"tempFileId":$("#tempFileId").val()});
            if(!data.reviewResult){
                $("#submit-btn").attr("disabled",false);
                mui.alert("您还未选择");
                return false;
            }
            $("body").mLoading();
            upLoad.ajaxUpLoad({
                url: "/app/companyChecktable/add",
                param:data
            },fileObj,function(msg){
                if(msg==-1){
                    $("#submit-btn").attr("disabled",false);
                    return false;
                }
                toInsertOrUpdate();
            });
            $("body").mLoading("hide");
        }else{
            mui.alert("当前状态无法进行修改")
        }
    }
}

$('.checkTableType').click(function(){
    dialogAlert(M.dialog1, checkTableType);
});

$('.yj').click(function(){
    var str = '';
    for(var i=0; i < regArray.length; i++){
        str += regArray[i].content;
    }

    dialogAlert(M.dialog2,str);
});
//返回
function goback(){
    var mask = mui.createMask(function(){return false});//callback为用户点击蒙版时自动执行的回调；
    mask.show();//显示遮罩
    var checkTableNameId=$("input[name='checkTableNameId']");
    history.replaceState('a',null,"/app/companyChecktable/detail/"+(checkTableNameId[0].value || checkTableNameId[1].value)+"/"+companyId);
    window.location.reload();
}