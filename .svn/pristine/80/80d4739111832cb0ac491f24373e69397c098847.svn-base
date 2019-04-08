var dynamic = new Object();
dynamic.pageSize=10;
dynamic.pageNum=1;
function listDatail(){
    $.ajax({
        url:"/app/companyChecktable/query",
        data:{companyId :dynamic.companyId ,dynamicType : dynamic.type,pageSize : 10,pageNum : dynamic.pageNum,dynamicStatus: dynamic.status,content : dynamic.content},
        type:"post",
        dataType:"json",
        success: function(data){
            if(data.status=="success"){
                var list=data.result;
                var htmlStr="";
                if(list.length>0){
                    for(var i=0;i<list.length;i++){
                        var es=list[i];
                        htmlStr+="<div data-value='"+es.content+"' data-dynamic='"+es.dynamicsId+"' class=\"content info_list qyjcxxDetail\" co_id='"+es.checkTableId+"'>" +
                            "<div class=\"Nonstandard_list\">" +
                            "<p class=\"omg\">"+es.content+"</p>";
                        if(es.dynamicStatus==0){
                            htmlStr+= "<button class=\"state3\">待处理</button></div>";
                        }
                        if(es.dynamicStatus==1){
                            htmlStr+= "<button class=\"state1\">待审核</button></div>";
                        }
                        if(es.dynamicStatus==3){
                            htmlStr+= "<button class=\"state2\">驳回</button></div>";

                        }
                        htmlStr+="</div>";
                    }
                }
                if(dynamic.pageNum==1){
                    //重置上拉滚动条
                    mui('#pullrefresh').pullRefresh().scrollTo(0, 0, 0);
                    //重置启用上下拉的操作
                    mui("#pullrefresh").pullRefresh().setStopped(false)
                    mui('#pullrefresh').pullRefresh().enablePullupToRefresh();
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((false)); //参数为true代表没有更多数据了。
                    dynamic.htmlTextDom.html(htmlStr);
                    if(dynamic.pageNum == data.totalPage){
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                    }
                }else{
                    dynamic.htmlTextDom.append(htmlStr);
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((false)); //参数为true代表没有更多数据了。
                    if(dynamic.pageNum == data.totalPage){
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                    }
                }
            }else{
                if(dynamic.pageNum==1){
                    //重置上拉滚动条
                    mui('#pullrefresh').pullRefresh().scrollTo(0, 0, 0);
                    dynamic.htmlTextDom.html("<li class=\"Nonstandard_list\">" +
                        " <p class=\"list_title\" style='font-size: 15px; font-weight: 600; letter-spacing: 1px;color: #8f8f94;width: 100%;text-align: center;'>暂无数据</p></li>");
                    //没有数据的时候禁用上下拉，保证不出现没有更多数据的文字
                    mui("#pullrefresh").pullRefresh().setStopped(true)
                    mui('#pullrefresh').pullRefresh().disablePullupToRefresh();
                }
                mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
            }
        }
    })
}
//获取父页面参数
function getCnd(){
    dynamic.type = parent.document.getElementById("showTypePicker1Val").value;
    dynamic.status = parent.document.getElementById("showStatusPicker2Val").value;
    dynamic.content = parent.document.getElementById("searchText").value;
};
//查询详情页面
mui("body").on('tap', '.qyjcxxDetail', function(event){
    parent.window.location.href="/app/companyChecktable/answereDynamic/"+$(this).attr("co_id") +"/"+$(this).attr("data-dynamic");
});
//下拉加载数据
dynamic.searchNextPage=function(){
    dynamic.pageNum++;
    listDatail();
}
//初始化条件
function initDynamic(date){
    dynamic.listUrl=date.listUrl;
    dynamic.searchDom=date.searchDom;
    dynamic.infoUrl=date.infoUrl;
    dynamic.htmlTextDom=date.htmlTextDom;
    dynamic.companyId=date.companyId;
}
//下拉加载数据
dynamic.searchNextPage=function(){
    dynamic.pageNum++;
    listDatail();
}