/**
 * account js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "assess_flag asc,start_date desc";

/**
 * constant
 * @type {string}
 */
var timeSheetParam = {};
var tbodyId = "timeSheetTbody";
var tbodyCheck = "timeSheetCheck";

// 模态框
var assessMessageLayer ;
var exportLayer;

var vagueQuery;



$(function(){
    $(".dateClass").datetimepicker({
        minView: "month",
        format : "yyyy-mm-dd",
        autoclose : true,
        todayBtn : true,
        language : 'zh-CN',
        pickDate: true,
        pickTime: false,
        //hourStep: 1,
        //minuteStep: 1,
        inputMask: true
    });
    initTableGrid.call(this,1);
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
    data.type = 1;
    if(vagueQuery){
        data.createUser = $("#createBy").val();
    }
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/timesheet/queryTimesheets" ,

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
                    //已通过
                    if("0" != rows[i].assessFlag )
                        resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'></td>";
                    else
                        resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                            + "<input type='checkbox' name='" + tbodyCheck + "' class='checkbox'/></td>";

                    resStr += "<td>"+stringToBlank.call(this,rows[i].title)+"</td>";
                    resStr += "<td>"+family_ns.formatterStrByLength(stringToBlank.call(this,rows[i].content),20,true)+"</td>";
                    resStr += "<td>"+stringToBlank.call(this,rows[i].createUser)+"</td>";
                    resStr += "<td>"+stringToBlank.call(this,rows[i].start)+"</td>";
                    resStr += "<td>"+stringToBlank.call(this,rows[i].end)+"</td>";
                    resStr += "<td>";
                    //通过
                    if("2" == rows[i].assessFlag ){
                        resStr += "<span style='color:#4eaa4c;'  >已通过</span>";
                    }
                    //待审核
                    else if("0" == rows[i].assessFlag && timeSheetParam.assessAuthor){
                        resStr += "<button class='btn btn-info' onclick='assessPass(\"" + rows[i].businessId + "\")' type='button'>通过</button>&nbsp;&nbsp;";
                        resStr += "<button class='btn btn-danger' onclick='assessReject(\"" + rows[i].businessId + "\")' type='button'>打回</button>";
                    }
                    //已打回
                    else if("1" == rows[i].assessFlag ){
                        resStr += "<span style='color:#dd514c;'  >已打回</span>";
                    }
                    else{
                        resStr += "";
                    }
                    resStr += "</td>";

                resStr += "</tr>";
            }
            $("#" + tbodyId).html("");
            $("#" + tbodyId).append(resStr);
            initPagination.call(this,'paginationTimeSheet',result.pages,result.pageNu,"initTableGrid");
        },error:function (result) {
            //console.info(result);
        }
    });
}

/***
 * batch assess
 */
function batchAssess(){
    //选中行
    if(!family_ns.isCommonTableSelectedRow.call(this,tbodyId,tbodyCheck))
        return;
    var businessIds = family_ns.getCommonTableSelectedRowBusinessId.call(this,tbodyId,tbodyCheck);

    assessMessageLayer = layer.msg('批量审核', {
        time: 0, //不自动关闭
        zIndex:1111,
        btn: ['通过', '打回','取消'],
        yes: function(index){
            //通过
            assessPass.call(this,businessIds);
        },btn2: function(index){
            //打回
            assessReject.call(this,businessIds);
            return false;
        },btn3: function(index){

        }
    });
}
/**
 * 导出工时
 */
function exportTimesheet(){
    exportLayer = layer.open({
        type: 1,
        title: '导出信息',
        fix: false,
        //maxmin: true,
        shadeClose: false,
        zIndex:1111,
        area: ['40%', '28%'],
        content: $("#exportInfo"),
        success: function(layero, index){

        },
        end: function(){
            cleanExportTimeSheet.call(this);
        }
    });
}
/**
 * 工时日历
 */
function calendarTimesheet(){
    window.location.href = family_ns.contextPath + "/view/homeBack/calendarTimeSheet";
}
// 清空 export
function cleanExportTimeSheet(){
    $("#startDate").val("");
    $("#endDate").val("");
}

/**
 * 确认导出数据
 */
function sureExportTs(){
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    if(!startDate || "" == startDate || !endDate || "" == endDate){
        var mess = family_ns.getErrMessage("请选择开始,结束时间!");
        family_ns.operatorAlertError(mess);
    }
    else
        window.open(family_ns.contextPath + "/timesheet/timesheetExport?startQuery=" + startDate
        + "&endQuery=" + endDate);
}
/**
 * search account
 */
function searchTimesheet(){
    vagueQuery = true;
    initTableGrid.call(this,1);
}
/**
 * 通过
 */
function assessPass(businessId){
    var data = {};
    data.businessId = businessId;
    data.assessFlag = "2";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/timesheet/timesheetAssess",
            data: data,
            success: function (result) {
                if (result.success)
                    family_ns.successJumpSelf.call(this,timeSheetParam.urlSelf,timeSheetParam.modelId);
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });
}
/**
 * 打回
 */
function assessReject(businessId){
    var data = {};
    data.businessId = businessId;
    data.assessFlag = "1";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/timesheet/timesheetAssess",
            data: data,
            success: function (result) {
                if (result.success)
                    family_ns.successJumpSelf.call(this,timeSheetParam.urlSelf,timeSheetParam.modelId);
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });
}