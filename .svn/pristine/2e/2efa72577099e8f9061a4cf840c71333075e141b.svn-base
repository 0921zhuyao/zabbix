var enterprise=new Object();
enterprise.pageSize=10;
enterprise.pageNum=1;
function initEnterprise(date){
    enterprise.listUrl=date.listUrl;
    enterprise.searchDom=date.searchDom;
    enterprise.infoUrl=date.infoUrl;
    enterprise.htmlTextDom=date.htmlTextDom;
    enterprise.taskPlanId=date.taskPlanId;
    enterprise.search();
}
enterprise.search=searchCnd;
enterprise.getParentParam=getParentParam;

mui("body").on('tap', '.qyjcxxDetail', function(event){
    if($(this).attr("type")==4){
        parent.window.location.href="/app/companyChecktable/selfJudge/"+"/"+$(this).attr("co_id");
    }else{
        parent.window.location.href=enterprise.infoUrl+"/"+$(this).attr("co_id");
    }
});
// 为确认按钮绑定click事件
mui("body").on('tap','.sure',function(e) {
    var dom=this;
    $.post("/app/taskplan/updateStatus",{id:$(this).parent().attr("de_id")},function(data){
        if(data.code==0){
            $(dom).prev().removeClass("dingji").addClass("finish").html("定级中");
            mui.toast("确认成功");
        }else{
            mui.toast("确认失败");
        }
    });
    //阻止事件穿透
    e.stopPropagation();

})
function getParentParam() {
    enterprise.value=parent.document.getElementById("searchText").value;
    enterprise.industry_code=parent.document.getElementById("showUserPicker1Val").value;
    enterprise.subordinate_streets=parent.document.getElementById("showUserPicker2Val").value;
    enterprise.scale_situation=parent.document.getElementById("showUserPicker3Val").value;
    enterprise.enterprise_type=parent.document.getElementById("showUserPicker4Val").value;
    searchCnd();
}

function searchCnd(){
    var data="";
    if(enterprise.pageSize){
        data+="&pageSize="+enterprise.pageSize;
    }
    if(enterprise.pageNum){
        data+="&pageNum="+enterprise.pageNum;
    }
    if(enterprise.value){
        data+="&qymc="+enterprise.value;
    }
    if(enterprise.industry_code){
        data+="&hylb="+enterprise.industry_code;
    }
    if(enterprise.subordinate_streets){
        data+="&orgId="+enterprise.subordinate_streets;
    }
    if(enterprise.scale_situation){
        data+="&gmqk="+enterprise.scale_situation;
    }
    if(enterprise.taskPlanId && enterprise.taskPlanId!=-1){
        data+="&taskPlanId="+enterprise.taskPlanId;
    }
    if(enterprise.enterprise_type){
        data+="&qygm="+enterprise.enterprise_type;
    }
    if(data.charAt(0)=="&"){
        data = data.substring(0);
    }
    listDatail(data);
}
enterprise.searchNextPage=function(){
    enterprise.pageNum++;
    getParentParam();
}
function listDatail(data){
    if(!enterprise.htmlTextDom){
        return;
    }
    $.ajax({
        url:enterprise.listUrl,
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
                        htmlStr+="<div class=\"info_list qyjcxxDetail\" co_id='"+es.companyId+"' de_id='"+es.taskDetailId+"' type='"+es.checktableStatus+"'>";
                        if(es.initLevel == null)
                            htmlStr+= "<span class=\"level\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;未定级</span>";
                        else{
                            htmlStr+= "<span class=\"level\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + es.initLevel + " 级</span>";
                        }
                        htmlStr+="<img src=\"/images/pic1.png\" alt=\"\">" +
                                 "<div class=\"list_left\">" +
                                 "<p class=\"list_title\"> "+es.scjydwqc+"</p></div>" ;
                            if(es.checktableStatus==0){
                                htmlStr+= "<button class=\"no-finish\">未开始</button>";
                            }
                            if(es.checktableStatus==1){
                                htmlStr+= "<button class=\"wait\">自评中</button>";
                            }
                            if(es.checktableStatus==2){
                                htmlStr+= "<button class=\"fucha\">复查中</button><div class=\"dot\"></div>";

                            }
                            if(es.checktableStatus==3){
                                htmlStr+= "<button class=\"dingji\">定级中</button>";
                                htmlStr+= "<button class=\"sure\">确认</button>";
                            }
                            if(es.checktableStatus==4){
                                htmlStr+= "<button class=\"finish\">已完成</button>";
                            }
                            if(es.checktableStatus==5){
                                htmlStr+= "<button class=\"zuofei\">已作废</button>";
                            }
                        htmlStr+="</div>";
                    }
                }
                if(enterprise.pageNum==1){
                    //重置上拉滚动条
                    mui('#pullrefresh').pullRefresh().scrollTo(0, 0, 0);
                    //重置启用上下拉的操作
                    mui("#pullrefresh").pullRefresh().setStopped(false)
                    mui('#pullrefresh').pullRefresh().enablePullupToRefresh();
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((false)); //参数为true代表没有更多数据了。
                    enterprise.htmlTextDom.html(htmlStr);
                    if(enterprise.pageNum == data.totalPage){
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                    }
                }else{
                    enterprise.htmlTextDom.append(htmlStr);
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((false)); //参数为true代表没有更多数据了。
                    if(enterprise.pageNum == data.totalPage){
                        mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                    }
                }
            }else{
                if(enterprise.pageNum==1){
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
