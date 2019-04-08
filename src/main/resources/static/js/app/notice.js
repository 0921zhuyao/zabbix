var notice = new Object();

notice.pageSize = 10;
notice.pageNum = 1;
function initNotice(date){
    notice.listUrl=date.listUrl;
    notice.infoUrl=date.infoUrl;
    notice.htmlTextDom=date.htmlTextDom;
    notice.search();
}
notice.noticeDetail = noticeDetail;
notice.listatail = listDatail;

function noticeDetail(id) {
    window.location=notice.infoUrl+"/"+id;
}

notice.search = searchCnd;
notice.getParentParam = getParentParam;

mui("body").on('tap', '.noticeDetail', function(event){
    mui.openWindow({
        url: notice.infoUrl+"/"+$(this).attr("noticeId"),
        id:"detail-info"
    })
});


function getParentParam() {
    searchCnd();
}

function searchCnd(){
    var data="";
    if(notice.pageSize){
        data += "&pageSize=" + notice.pageSize;
    }
    if(notice.pageNum){
        data += "&pageNum=" + notice.pageNum;
    }
    listDatail(data);
}
notice.searchNextPage=function(){
    notice.pageNum++;
    getParentParam();
}

function listDatail(data){
    $.ajax({
        url:notice.listUrl,
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
                        " <img src=\"/images/t03.png\">" +
                        " <div class=\"list_left noticeDetail\" noticeId = '" + n.id + "'\">" +
                        " <p class=\"list_title\">"+ n.title+"</p>" +
                        "<span>" + fmtDate(n.crtTime) + "</span>"+
                        " <p class=\"omg list_address\">" + n.content + "</p>" +
                        " </div>" +
                        " </div>"
                }
            }else{
                if(notice.pageNum==1){
                    notice.htmlTextDom.html("<li class=\"info_list\">" +
                        " <p class=\"list_title\" style='font-size: 15px; font-weight: 600; letter-spacing: 1px;color: #8f8f94;width: 100%;text-align: center;'>暂无数据</p>" +
                        " </li>");
                }
                mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
            }
            if(notice.pageNum==1){
                notice.htmlTextDom.html(htmlStr);
                if(notice.pageNum == data.totalPage){
                    mui('#pullrefresh').pullRefresh().endPullupToRefresh((true)); //参数为true代表没有更多数据了。
                }
            }else{
                notice.htmlTextDom.append(htmlStr);
                if(notice.pageNum == data.totalPage){
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
