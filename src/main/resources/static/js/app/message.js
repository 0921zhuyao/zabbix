var message = new Object();

message.pageSize = 10;
message.pageNum = 1;
function initNotice(date){
    message.listUrl=date.listUrl;
    message.infoUrl=date.infoUrl;
    message.htmlTextDom=date.htmlTextDom;
    message.search();
}
message.noticeDetail = noticeDetail;
message.listatail = listDatail;

function noticeDetail(id) {
    window.location=message.infoUrl+"/"+id;
}

message.search = searchCnd;
message.getParentParam = getParentParam;

mui("body").on('tap', '.messageDetail', function(event){
    mui.openWindow({
        url: message.infoUrl+"/"+$(this).attr("messageId"),
        id:"detail-info"
    })
});

function getParentParam() {
    searchCnd();
}

function searchCnd(){
    var data="";
    if(message.pageSize){
        data += "&pageSize=" + message.pageSize;
    }
    if(message.pageNum){
        data += "&pageNum=" + message.pageNum;
    }
    listDatail(data);
}
message.searchNextPage=function(){
    message.pageNum++;
    getParentParam();
}

function listDatail(data){
    $.ajax({
        url:message.listUrl,
        data:data,
        type:"post",
        dataType:"json",
        success: function(data){
            var list = data.list;
            var htmlStr="";
            if(list.length > 0 ){
                for(var i=0;i<list.length;i++){
                    var n = list[i];
                    htmlStr += "<div class=\"info_list\" id='"+n.id+"'>" +
                        " <img src=\"/images/t03.png\">" ;
                        if(n.status == 0){
                            htmlStr +=  "<div id=\"badge\">1</div>";
                        }
                        htmlStr +=" <div class=\"list_left messageDetail\" messageId = '" + n.id + "'\">" +
                        " <p class=\"list_title\">"+ n.title+"</p>" +
                        "<span>" + fmtDate(n.crtTime) + "</span>"+
                        " <p class=\"omg list_address\">" + n.content + "</p>" +
                        " </div>" +
                        " </div>"
                }
            }else{
                if(message.pageNum==1){
                    message.htmlTextDom.html("<li class=\"info_list\">" +
                        " <p class=\"list_title\" style='font-size: 15px; font-weight: 600; letter-spacing: 1px;color: #8f8f94;width: 100%;text-align: center;'>暂无数据</p>" +
                        " </li>");
                }
                mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
            }
            if(message.pageNum==1){
                message.htmlTextDom.html(htmlStr);
                if(message.pageNum == data.totalPage){
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                }
            }else{
                message.htmlTextDom.append(htmlStr);
                if(message.pageNum == data.totalPage){
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                }
            }
        }
    })
}

function fmtDate(obj){
    var date =  new Date(obj);
    var y = 1900+date.getYear();
    var m = "0"+(date.getMonth()+1);
    var d = "0"+date.getDate();
    return y+"-"+m.substring(m.length-2,m.length)+"-"+d.substring(d.length-2,d.length);
}
