var jsonDate=null;
function getDate(dictUrl,dic){
    $.ajax({
        url:"/system/dict/data/JsonDate/"+dic,
        type:"get",
        async: false,
        dataType:"json",
        success: function(data){
            if(data.length>0){
                for(var i=0;i<data.length;i++){
                    if(data[i].children){
                        data[i].children.unshift({text:"全部",value:""});
                        for(var j=0;j<data[i].children.length;j++){
                            if(data[i].children[j].children){
                                data[i].children[j].children.unshift({text:"全部",value:""});
                            }
                        }
                    }
                }
                data.unshift({text:"全部",value:""});
                jsonDate = JSON.stringify(data);

            }
        }
    })
}
function JsonDate(date){
    getDate(date.dictUrl,date.dic)
    return JSON.parse(jsonDate);
}