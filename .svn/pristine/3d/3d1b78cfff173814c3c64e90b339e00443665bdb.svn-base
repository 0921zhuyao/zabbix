var plan=new Object();
plan.pageSize=10;
plan.pageNum=1;
function initEnterprise(date){
    plan.listUrl=date.listUrl;
    plan.infoUrl=date.infoUrl;
    plan.htmlTextDom=date.htmlTextDom;
    plan.search();
}
plan.search=searchCnd;
plan.searchNextPage=function(){
    plan.pageNum++;
    plan.search();
}
function searchCnd(){
    var data="";
    if(plan.pageSize){
        data+="&pageSize="+plan.pageSize;
    }
    if(plan.pageNum){
        data+="&pageNum="+plan.pageNum;
    }
    if(data.charAt(0)=="&"){
        data = data.substring(0);
    }
    listDatail(data);
}

mui("body").on('tap', '.planDetail', function(event){
    parent.window.location.href=plan.infoUrl+"/"+$(this).attr("id")+"?planName="+$(this).attr("planName");
});

function listDatail(data){
    if(!plan.htmlTextDom){
        return;
    }
    $.ajax({
        url:plan.listUrl,
        data:data,
        type:"post",
        dataType:"json",
        success: function(data){
            if(data.status=="success"){
                var list=data.result;
                var htmlStr="";
                if(list.length>0){
                    for(var i=0;i<list.length;i++){
                        var es=list[i];
                        if(es.scjydz==null ||es.scjydz==''){
                            es.scjydz="未录入";
                        }
                        htmlStr+="<li class='planDetail' id='"+es.planId+"' planName='"+es.planName+"'>\n" +
                            "                    <div class=\"list-title\">\n" +
                            "                        <span>"+(i+1)+"、</span>\n" +
                            "                        <span>"+es.planName+"</span>\n" ;
                        if(es.planStatus==0){
                            htmlStr+="<button class=\"no-finish\">未审核</button>\n";
                        }
                        if(es.planStatus==1){
                            htmlStr+="<button class=\"finish\">已确认</button>\n";
                        }
                        if(es.planStatus==2){
                            htmlStr+="<button class=\"no-finish\">未通过</button>\n";
                        }
                        htmlStr+="</div>" +
                            "<div class=\"list-msg\">" +
                            "<div class=\"msg-left\">" +
                            "<p class=\"list_address omg\">自评时间:"+es.planStartTime+"</p>" +
                            "<p class=\"list_de omg\">" +
                            "<span>自评总企业数: "+es.detailCount+"家&nbsp;|</span>" +
                            "<span>&nbsp;已自评:"+es.finishCheck+"家&nbsp;|</span>" +
                            "<span>&nbsp;未自评: "+(es.detailCount-es.finishCheck)+"家 </span></p></div></div></li>";
                    }
                }
                if(plan.pageNum==1){
                    //重置上拉滚动条
                    mui('#pullrefresh').pullRefresh().scrollTo(0, 0, 0);
                    //重置启用上下拉的操作
                    mui("#pullrefresh").pullRefresh().setStopped(false)
                    mui('#pullrefresh').pullRefresh().enablePullupToRefresh();
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((false)); //参数为true代表没有更多数据了。
                    plan.htmlTextDom.html(htmlStr);
                    if(plan.pageNum == data.totalPage){
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                    }
                }else{
                    plan.htmlTextDom.append(htmlStr);
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((false)); //参数为true代表没有更多数据了。
                    if(plan.pageNum == data.totalPage){
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                    }
                }
            }else{
                if(plan.pageNum==1){
                    //重置上拉滚动条
                    mui('#pullrefresh').pullRefresh().scrollTo(0, 0, 0);
                    enterprise.htmlTextDom.html("<li class=\"info_list\">" +
                        " <p class=\"list_title\" style='font-size: 15px; font-weight: 600; letter-spacing: 1px;color: #8f8f94;width: 100%;text-align: center;'>暂无数据</p>" +
                        " </li>");
                    //没有数据的时候禁用上下拉，保证不出现没有更多数据的文字
                    mui("#pullrefresh").pullRefresh().setStopped(true)
                    mui('#pullrefresh').pullRefresh().disablePullupToRefresh();
                }
                mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
            }
        }
    })
}