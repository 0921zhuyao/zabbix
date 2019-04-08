var enterprise=new Object();
enterprise.pageSize=10;
enterprise.pageNum=1;
function initEnterprise(date){
    enterprise.listUrl=date.listUrl;
    enterprise.searchDom=date.searchDom;
    enterprise.infoUrl=date.infoUrl;
    enterprise.htmlTextDom=date.htmlTextDom;
    enterprise.search();
}
enterprise.search=searchCnd;
enterprise.getParentParam=getParentParam;

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
                        htmlStr+="<div class=\"content-line\">" +
                                 "<li class=\"info_list\" id='"+es.companyId+"'>"+
                                 "<img src='/images/pic1.png'>" +
                                 "<div class=\"list_left\">" +
                                 "<p class=\"list_title\">"+es.scjydwqc+"</p>" +
                                 "<span class='checkSpan'>不符合项:&nbsp;"+es.noPassNumber+"&nbsp;个&nbsp&nbsp|</span>" +
                                 "<span class='checkSpan'>行政执法:&nbsp;"+es.xzzfNumber+"&nbsp;个</span>";
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
                        " <p class=\"list_title no-more\" style=''>暂无数据</p>" +
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

$(document).ready(function() {
    mui("body").on('tap', '.info_list', function(event){
        var c_id=$(this).attr("id");
        parent.window.location.href='/app/companyChecktable/initList/-1/'+c_id;
    });
});