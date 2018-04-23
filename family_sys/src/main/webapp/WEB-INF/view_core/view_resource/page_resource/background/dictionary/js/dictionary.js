/**
 * yangxuenan
 * 数据字典
 */
family_ns.page.sort = "business_id desc";
//获取要编辑的id  更改的id
var dictionaryId = "";
var dicPage = 1;
$(function() {
	 initTableGrid.call(this,1);
});

function searchDic(){
	initTableGrid.call(this,1);
}

function  initTableGrid(page){
	var data={};
	family_ns.page.page = page;
	data= family_ns.page ;
	data.name=$("#name").val();
	family_ns.queryAjax({
		type: "POST",
		url: family_ns.contextPath + "/dictionary/queryDictionary",
		data: data ,
		success : function(data) {
			var rows = data.rows;
			 if(!rows)
	                return;
			var resStr = "";
			for ( var i = 0; i < rows.length; i++) {
                resStr += "<tr>";
                resStr += "<td scope='row' id='"+rows[i].businessId +"'>"
                        +"<input type='radio' name='dictionary'/>&nbsp;&nbsp;"
                    "</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].name)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].value)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].type)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].create_date)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].username)+"</td>";
                if(rows[i].flag == 1){
                	resStr += "<td>"+stringToBlank.call(this,"启用")+"</td>";
                }else if(rows[i].flag == 0){
                	resStr += "<td>"+stringToBlank.call(this,"停用")+"</td>";
                }
                
                resStr += "</tr>";
            }
            $("#dictionaryTableTbody").html("");
            $("#dictionaryTableTbody").append(resStr);
            initPagination.call(this,'pagination-dictionary',data.pages,data.pageNu,"initTableGrid");
			
		}
	})
	
}
/**
 * add dictionary
 */
function addDictionary(){

    window.location.href = family_ns.contextPath + "/view/background/dictionaryAdd";
}
/***
 * delete dictionary
 */
function deleteDictionary(){
	
    var dictionary = $("#dictionaryTableTbody td input[name='dictionary']:checked");
    if(0 == dictionary.length){
        var swalAlert = {"title":"温馨提示","text":"请选中一条数据,进行操作!","timer":2000};
        family_ns.operatorAlertError.call(this,swalAlert);
        return;
    }else{
    	dictionaryId = dictionary.parent().attr("id");
    	 var message = {};

         message.text = "您确认您的操作?(删除角色,拥有该角色的人员将失去对应的权限)";

    	family_ns.operateConfirmByAjax(function(){

            family_ns.submitAjax({
                type: "post",
                url: family_ns.contextPath + "/dictionary/adminLog/dictionaryDel?id=" + dictionaryId,
                dataType: "json",
                success: function (data) {
                	if(data.success){
                		family_ns.operatorAlertSuccess(family_ns.sucMessage);
            			initTableGrid(family_ns.page.page);
                	}else {
                		family_ns.operatorAlertError(family_ns.errMessage);
                	}
                	
                }
            });
        }, message);
    	}	 

    }
/**
 * 修改数据字典
 */
    function updateDictionary(){
        var dictionary = $("#dictionaryTableTbody td input[name='dictionary']:checked");
        if(0 == dictionary.length){
            var swalAlert = {"title":"温馨提示","text":"请选中一条数据,进行操作!","timer":2000};
            family_ns.operatorAlertError.call(this,swalAlert);
            return;
        }else{
        	dictionaryId = dictionary.parent().attr("id");
            window.location.href=family_ns.contextPath+"/dictionary/toBg/toDictionaryUpdatePage/" + dictionaryId;
        }
    }

