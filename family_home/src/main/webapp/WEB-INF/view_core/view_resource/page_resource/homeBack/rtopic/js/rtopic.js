/**
 * movie js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "update_date desc";
var vagueQuery;
var rtopicParam = {};
// tbody
var tbodyId = "rtopicTableTbody";
var tbodyCheck = "rtopicCheck";

var userBody = "userTableTbody";
var userBodyCheck = "userBodyCheck";

/**
 * 监控 check 选中
 */
var currentSelectUsers = "";    //当前选中的users
var currentSelectUsersId = "";  //当前选中的user ids


/**
 * layer
 */
var rtopicInfo ;
//是否可以编辑
var isEditAble = true;
var editAbleBusinessId = "";

var rtopicLabelTableName = "rtopicLabelTbody";

$(function(){
    initTableGrid.call(this,1);

    $(".datetimeClass").datetimepicker({
        format : "yyyy-mm-dd",
        autoclose : true,
        todayBtn : true,
        language : 'zh-CN',
        pickDate: true,
        minView: "month",//设置只显示到月份
        inputMask: true
    });

    var validate = $("#rtopicForm").validate(
        {
            debug : true, //调试模式取消submit的默认提交功能
            //errorClass: "label.error", //默认为错误的样式类为：error
            focusInvalid : false, //当为false时，验证无效时，没有焦点响应
            onkeyup : false,
            submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
                saveRtopic.call(this);
            },

            rules : {
                title : {
                    required : true
                },
                startDate:{
                    required : true
                },
                endDate:{
                    required : true
                }
            },
            messages : {
                title : {
                    required : "*必填"
                },
                startDate:{
                    required : "*必填"
                },
                endDate:{
                    required : "*必填"
                }
            },
            errorPlacement : function(error, element) {
                error.appendTo(element.next(
                    "span"));
            },
            invalidHandler : function(errorMap, errorList) { // 如果表单验证不通过，将会触发这个函数

            },
            //invalidHandler  表单提交时  错误验证回调 showErrors  每一次错误验证 回调
            showErrors : function(errorMap, errorList) { //每一次错误验证
                //缺省默认错误
                this.defaultShowErrors();

            }
        });
});


/**
 * 初始化table
 */

function initTableGrid(page){
    var data={};
    family_ns.page.page = page;
    data= family_ns.page ;
    data.flag = 1;
    data.delFlag = 0;
    if(vagueQuery){
        data.title = $("#rtopicName").val();
    }
    data.rtopicLabelId = rtopicParam.rtopicLabelId;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/rtopic/queryRTopicsByPagination" ,

        data: data ,

        dataType: 'json',

        success: function(result){
            var rows = result.rows;
            //数据判断
            if(!family_ns.clearTableData.call(this,tbodyId,rows))
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                resStr += "<tr class='unread checked'>";
                resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                            + "<input type='radio' name='" + tbodyCheck + "' class='checkbox'/></td>";

                resStr += "<td>"+family_ns.formatterStrByLength(rows[i].title,20,true)+"</td>";
                resStr += "<td>"+family_ns.formatterStrByLength(rows[i].description,20,true)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].startDate)
                    + " ~ " +  stringToBlank.call(this,rows[i].endDate) +"</td>";
                resStr += "<td>"+formatterRtype.call(this,rows[i].type)+"</td>";

                resStr += "<td>"+stringToBlank.call(this,rows[i].users)+"</td>";
                resStr += "<td><button class='btn btn-primary' onclick='rtopicDetail(\"" + rows[i].businessId + "\")' type='button'>milestone</button></td>";

                resStr += "</tr>";
            }
            $("#rtopicTableTbody").html(resStr);
            if(1 === result.pageNu){
                initPagination.call(this,'pagination-rtopic',result.pages,result.pageNu,"initTableGrid");
            }
        }
    });
}

/**
 * search rtopic
 */
function searchRtopic(){
    vagueQuery = true;
    initTableGrid(1);
}



//类型转换
function formatterRtype(type){

    if("0" == type)
        return "前端";
    else if("1" == type)
        return "后端";
    else if("2" == type)
        return "运维";
    else if("3" == type)
        return "管理";
    else if("4" == type)
        return "策划";
    else
        return "";
}
/***
 * delete movie
 */
function deleteRtopic(){
    if(!family_ns.isCommonTableSelectedRow.call(this,tbodyId,tbodyCheck))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,tbodyId,tbodyCheck);
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/rtopic/rtopicDelete/" + businessId,
            data: "",
            success: function (result) {
                if (result.success) {
                    family_ns.successJumpSelf.call(this,rtopicParam.urlSelf,rtopicParam.modelId);
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });


}

//浏览博客
function browseRtopic(id){

    window.open(family_ns.contextPath + "/rtopic/browseRtopic/"+id,'newwindow','');
}

/**
 * add rtopic
 */
function addRtopic(){

    var server = {};
    server.title = $("#labelTitle").val();

    rtopicInfo = layer.open({
        type: 1,
        title: '课题信息',
        fix: false,
        //maxmin: true,
        shadeClose: false,
        zIndex:1111,
        area: ['60%', '60%'],
        content: $("#rtopicInfo"),
        success: function(layero, index){
            initTopicUser.call(this);
        },
        end: function(){
            clearRtopicInfo.call(this);
        },
        btn: ['增加'],
        yes: function(index, layero){
            $('#rtopicForm').submit();
        }
    });


}
/**
 * 修改
 */
function updateRtopic(){
    if(!family_ns.isCommonTableSelectedRow.call(this,tbodyId,tbodyCheck))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,tbodyId,tbodyCheck);

    var param = {};
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/rtopic/queryOneById/" + businessId,
        data: param,
        success: function (result) {
            if (result.success) {
                rtopicInfo = layer.open({
                    type: 1,
                    title: '课题信息',
                    fix: false,
                    //maxmin: true,
                    shadeClose: false,
                    zIndex:1111,
                    area: ['60%', '60%'],
                    content: $("#rtopicInfo"),
                    success: function(layero, index){
                        initTopicUser.call(this,businessId);
                        $("#rtopicBusinessId").val(businessId);
                        $("#workTitle").val(result.result.title);
                        $("#startDate").val(result.result.startDate);
                        $("#endDate").val(result.result.endDate);
                        $("#workContent").text(result.result.description);
                        $("#type").val(result.result.type);
                    },
                    end: function(){
                        clearRtopicInfo.call(this);
                    },
                    btn: ['确认'],
                    yes: function(index, layero){
                        $('#rtopicForm').submit();
                    }
                });
            }
        }
    });
}
/**
 * 提交 rtopic
 */
function saveRtopic(){
    var formData = family_ns.serializeObject($("#rtopicForm"));
    formData.rtopicUsersAdd = currentSelectUsersId;

    var url = family_ns.contextPath + "/rtopic/rtopicAdd";
    if($("#rtopicBusinessId").val())
        url = family_ns.contextPath + "/rtopic/rtopicUpdate";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type:"post",
            url:url,
            data: formData,
            success:function(result){
                if(result.success){
                    window.location.href = family_ns.contextPath + "/view/homeBack/rtopic?mf=1&modelId="
                        + rtopicParam.modelId;
                }
                else
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
            }
        });
    });

}

/**
 * clear rtopic 信息
 */
function clearRtopicInfo(){
    $("#resetRtopicInfo").click();
    $("#rtopicBusinessId").val("");
    $("#workContent").val('');
    $("#"+userBody).html("");
    $("#topicUsers").html("");
    currentSelectUsers = "";
    currentSelectUsersId = "";
}
/**
 * 跳转
 * @param businessId
 */
function rtopicDetail(businessId){
    window.location.href=family_ns.contextPath+"/view/homeBack/rtopicMileStone?milestoneId=" + businessId
        + "&modelId=" + rtopicParam.modelId;
}

/**
 * 展示 user 列表
 */

function initTopicUser(businessId){

    var data={};
    data.flag = 1;
    data.delFlag = 0;
    data.sort = ' checked desc ';
    if(businessId)
        data.businessId = businessId;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/rtopic/queryUsersByRTopics",

        data: data,

        dataType: 'json',

        success: function (result) {
            var rows = result.rows;
            //数据判断
            if (!family_ns.clearTableData.call(this, userBody, rows))
                return;
            var resStr = "";
            for (var i = 0; i < rows.length; i++) {
                resStr += "<tr class='unread checked' style='cursor: pointer' ondblclick='browseBlog.call(this,\"" +
                    rows[i].businessId
                    + "\")'>";
                if ("1" == rows[i].checked) {
                    resStr += "<td class='hidden-xs' id='" + rows[i].businessId + "'>"
                        + "<input type='checkbox' name='" + userBodyCheck + "' checked='checked' value='" + rows[i].businessId + "' class='checkbox' onchange='checkedUser(\"" + rows[i].businessId + "\")'/></td>";
                    currentSelectUsersId += rows[i].businessId + ",";
                    currentSelectUsers += rows[i].name + ",";
                }
                else
                    resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                        + "<input type='checkbox' name='" + userBodyCheck + "' value='" + rows[i].businessId + "' onchange='checkedUser(\"" + rows[i].businessId + "\")' class='checkbox'/></td>";

                resStr += "<td class='' id='tdName_"+rows[i].businessId+"' >"+stringToBlank.call(this,rows[i].name)+"</td>";
                resStr += "<td class=''  >"+stringToBlank.call(this,rows[i].no)+"</td>";
                resStr += "</tr>";

            }
            $("#" + userBody).html(resStr);
            if(currentSelectUsers.length > 0){
                currentSelectUsers = currentSelectUsers.substring(0,currentSelectUsers.length-1);
            }
            $("#topicUsers").html(currentSelectUsers);
        }
    });
}

function checkedUser(businessId){
    currentSelectUsers = "";
    currentSelectUsersId = "";
    $(":input[name='"+ userBodyCheck +"']:checked").each(function(){
        currentSelectUsersId += $(this).val() + ",";
        currentSelectUsers += $(this).parent().next().html() + ",";
    });
    if(currentSelectUsers.length > 0){
        currentSelectUsers = currentSelectUsers.substring(0,currentSelectUsers.length-1);
    }
    $("#topicUsers").html(currentSelectUsers);
}