var upLoad=new Object();
//alert弹框
var M = {
}
function alertInfo(data) {
    if (M.dialog1) {
        return M.dialog1.show();
    }
    M.dialog1 = jqueryAlert({
        'content': data.msg || "功能暂未开放",
        'closeTime': data.time || 1500
    });
}
upLoad.alertInfo=alertInfo;

//文件删除
function removeFile(thisData) {
    var btnArray = ['否', '是'];
    mui.confirm('文件删除后不可恢复，是否删除？', '提示', btnArray, function(e) {
        if (e.index == 1) {
            if($(thisData).prev().attr("fileId")){
                $.ajax({
                    type: "post",
                    url: "/app/companyChecktableFiles/del",
                    data: {"ids": $(thisData).prev().attr("fileId")},
                    dataType: "json",
                    success: function (data) {
                        if (data.code == 0) {
                            var dom=$(thisData).parent();
                            dom.remove();
                        } else {
                            mui.alert("上传失败");
                        }
                    }
                })
            }else{
                var dom=$(thisData).parent();
                dom.remove();
            }
        }
    })

};
//input file 文件发生变化时
function afterChangeFile(data){
    var htmlTemp="<div class=\"img-box\"> <label> <a href=\"javascript:;\" class=\"file\">" +
        "<input type=\"file\" name=\"file\"  onchange=\"afterChangeFile(this)\">" +
        "</a><span class=\"upload-text\">上传文件</span>"+
        "<span class=\"upload-info\">（文件大小不得超过10M</span></label></div>";
    var fileObj = data.files[0];
    var fileVal=$(data).val();
    var fileType=fileVal.substring(fileVal.lastIndexOf("."));
    if(fileObj.size>1024*1024*10){
        errormsgInfo(data,"文件过大");
        return false;
    };
    var fileTypes = [".jpg", ".png", ".jpeg",".txt",""];
    var imagesTypes = [".jpg", ".png", ".jpeg"];
    // if(fileTypes.indexOf(fileType)==-1){
    //     errormsgInfo(data,"文件格式不正确");
    //     return false;
    // }
    $("body").mLoading();
    var dom=$(data).parents(".img-box");
    upLoad.ajaxUpLoad({
        url: "/module/companyChecktableFiles/addFile",
    },fileObj,function(msg){
        //新增一个上传框
        dom.after(htmlTemp);
        if(imagesTypes.indexOf(fileType)!=-1){
            var reader = new FileReader();
            reader.readAsDataURL(fileObj);
            reader.onload = function (e) {
                dom.html("<img src="+this.result+" fileId="+msg+"> <div class=\"removeFile\" onclick='removeFile(this)'></div>");
            };
        }else{
            dom.html("<img src='http:\\../images/wenjian.jpg' fileId="+msg+"> <div class=\"removeFile\" onclick='removeFile(this)'></div>");
        }
    });
    $(data).parent().css("background","url(/images/right1.png) no-repeat ").css("background-size","30px 30px");
    $(data).parent().next().html("文件已选择");
    $("body").mLoading("hide");
}
//文件不符合条件提示
function errormsgInfo(data,msg){
    $(data).parent().css("background","url(/images/wrong1.png) no-repeat ").css("background-size","30px 30px");
    $(data).val("");
    $(data).parent().next().html(msg);
}

upLoad.ajaxUpLoad=ajaxUpLoad;
//上传文件的后台接口
function ajaxUpLoad(data,fileObj,fun){
    var formFile = new FormData();
    if(fileObj){
        formFile.append("file", fileObj); //加入文件对象
    }
    if(data){
        for(var item in data.param){//加入其它参数
            formFile.append(item,data.param[item]);
        }
    }
//上传进度监听方法
/*    var xhrOnProgress = function(fun) {
        xhrOnProgress.onprogress = fun; //绑定监听
        //使用闭包实现监听绑
        return function() {
            //通过$.ajaxSettings.xhr();获得XMLHttpRequest对象
            var xhr = $.ajaxSettings.xhr();
            //判断监听函数是否为函数
            if(typeof xhrOnProgress.onprogress !== 'function')
                return xhr;
            //如果有监听函数并且xhr对象支持绑定时就把监听函数绑定上去
            if(xhrOnProgress.onprogress && xhr.upload) {
                xhr.upload.onprogress = xhrOnProgress.onprogress;
            }
            return xhr;
        }
    };*/
    $.ajax({
        type: "post",
        url: data.url,//这里加上你自己的上传地址
        processData: false,//关闭jquery对文件的预处理
        data: formFile,
        dataType: "json",
        contentType: false, //必须
        cache: false,//上传文件无需缓存
        /*xhr:xhrOnProgress(function(e){
            var percent=e.loaded / e.total;//计算百分比
            console.log("-----"+percent)
        }),*/
        success: function(data) {
            if(data.code == 0){
                // $("#tempFileId").val($("#tempFileId").val()+data.msg+",")
                if(fun){
                    fun(data.msg);
                }
            }else{
                mui.alert("上传失败");
                $("#submit-btn").attr("disabled",false);
                return false;
            }
        }
    });
}