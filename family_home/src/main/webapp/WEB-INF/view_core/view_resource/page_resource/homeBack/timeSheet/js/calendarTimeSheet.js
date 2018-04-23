/**
 * init
 */
var sheetInfo; //sheet layer modal
$(document).ready(function() {
    $(".datetimeClass").datetimepicker({
        format : "yyyy-mm-dd hh:ii",
        autoclose : true,
        todayBtn : true,
        language : 'zh-CN',
        pickDate: true,
        pickTime: true,
        hourStep: 1,
        minuteStep: 1,
        inputMask: true
    });
    /*//全天任务  开始时间格式化
    $('#startDate').datetimepicker().on('changeDate', function(ev){
        console.info(ev.date);
        var allDay = $("input[name='allDay']:checked").val();
        if("true" == allDay){
            var startDate = $("#startDate").val();
            $('#startDate').val(startDate.substring(0,10)+" 00:00");
            return false;
        }
    });
    //全天任务  结束时间格式化
    $('#endDate').datetimepicker().on('changeDate', function(ev){
        var allDay = $("input[name='allDay']:checked").val();
        if("true" == allDay){
            var endDate = $("#endDate").val();
            $('#endDate').val(endDate.substring(0,10)+" 23:59");
            return false;
        }
    });*/
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        selectable: true,
        selectHelper: true,
        //选中天->添加
        dayClick: function(start, end) {

        },
        //选中事件->修改
        eventClick: function(calEvent, jsEvent, view) {
            openReadTimeSheet.call(this,calEvent,jsEvent);
        },
        editable: false,
        eventLimit: true, // allow "more" link when too many events
        events: function(start,end,timezone, callback) {
            var type = $("input[name='type']:checked").val();
            family_ns.queryAjax({
                type:"post",
                url:family_ns.contextPath+"/timesheet/queryManagerTimesheetList",
                data: {
                    start:start.format("YYYY-MM-DD HH:mm"),
                    end:end.format("YYYY-MM-DD HH:mm"),
                    flag : 1,
                    delFlag : 0,
                    type : type
                },
                success:function(result){
                    if(result.success){
                        callback(result.result);
                    }
                    else
                        family_ns.operatorAlertError();
                }
            });
        },
        eventResize : function(event, delta, revertFunc, jsEvent, ui, view){
            if(event.assessFlag == "2"){
                revertFunc.call(this);
                var mess = family_ns.getErrMessage("工时已通过,不能修改!");
                family_ns.operatorAlertError(mess);
            }else
                resizeAndDropTimeSheet.call(this,event,revertFunc);
            //revertFunc.call(this);
        },
        eventDrop: function(event, delta, revertFunc, jsEvent, ui, view) {
            if(event.assessFlag == "2"){
                revertFunc.call(this);
                var mess = family_ns.getErrMessage("工时已通过,不能修改!");
                family_ns.operatorAlertError(mess);
            }else
                resizeAndDropTimeSheet.call(this,event,revertFunc);
        },
        eventMouseover:function(event, jsEvent, view ){
            //layer.tips('只想提示地精准些','.fc-content');
            if("" != event.content && "" != event.content.trim()){
                $("#sheetInfoFloatContent").html(event.content);
                $("#sheetInfoFloat").css('backgroundColor',event.color);
                $("#sheetInfoFloat").show();
                $("#sheetInfoFloat").css("left",jsEvent.clientX);
                $("#sheetInfoFloat").css("top",jsEvent.clientY);
            }


        },
        eventMouseout:function( event, jsEvent, view ){
            $("#sheetInfoFloat").hide();
            $("#sheetInfoFloatContent").html("");
        }
    });

});

/**
 * 打开只读的 timeSheet
 * @param calEvent
 * @param jsEvent
 */
function openReadTimeSheet(calEvent,jsEvent){
    initRtopic.call(this,calEvent,true);
    initBlogTableGrid.call(this,1,calEvent,true);
    sheetInfo = layer.open({
        type: 1,
        title: '查看工时信息',
        fix: false,
        //maxmin: true,
        shadeClose: false,
        zIndex:1111,
        area: ['80%', '70%'],
        content: $("#sheetInfo"),
        success: function(layero, index){
            //console.log(layero, index);
            initTimeSheet.call(this,calEvent,true);
        },
        end: function(){
            //layer.tips('Hi', '#about', {tips: 1})
            cleanTimeSheet.call(this);
        }
    });
}

/**
 * updateEvent 只接受原始对象
 * @param source
 * @param calEvent
 */
function copyPropFromTimeSheet (source,calEvent){

    for(p in source){
        calEvent[p]= source[p];
    }
    return calEvent;
}
/**
 *
 * @param source
 * @param calEvent
 * @returns {*}
 */
function copyPropFromEvent(source,calEvent) {
    source.content = calEvent.content;
    source.start = calEvent.start.format("YYYY-MM-DD HH:mm");
    if(calEvent.end)
        source.end = calEvent.end.format("YYYY-MM-DD HH:mm");
    source.allDay = calEvent.allDay;
    source.businessId = calEvent.businessId;
    return source;
}
/**
 * 初始化 timeSheet
 */
function initTimeSheet(calEvent,readOnly){
    $("#workBusinessId").val(calEvent.businessId);
    var title = calEvent.title.substr(calEvent.title.indexOf(":") + 2);
    $("#workTitle").val(title);
    $("#workContent").val(calEvent.content);
    //init allDay
    $("input[name='allDay'][value='false']").removeAttr('checked');
    $("input[name='allDay'][value='true']").removeAttr('checked');
    $("input[name='allDay'][value='"+ calEvent.allDay +"']").prop('checked','true');
    //init type
    $("input[name='type'][value='0']").removeAttr('checked');
    $("input[name='type'][value='1']").removeAttr('checked');
    $("input[name='type'][value='2']").removeAttr('checked');
    $("input[name='type'][value='"+ calEvent.type +"']").prop('checked','true');

    if(calEvent.start)
        $("#startDate").val(calEvent.start.format("YYYY-MM-DD HH:mm"));
        //$("#startDate").val(calEvent.start._i.substring(0,16));
    if(calEvent.end)
        $("#endDate").val(calEvent.end.format("YYYY-MM-DD HH:mm"));
        //$("#endDate").val(calEvent.end._i.substring(0,16));
    //只读模式

    if(readOnly){
        $("#workTitle").attr("disabled","disabled");
        $("#workContent").attr("disabled","disabled");
        $("input[name='allDay']").attr("disabled","disabled");
        $("#startDate").attr("disabled","disabled");
        $("#endDate").attr("disabled","disabled");
        $("input[name='type']").attr("disabled","disabled");
    }
}
/**
 * clear timesheet
 */
function cleanTimeSheet(){

    $("#workTitle").removeAttr("disabled","disabled");
    $("#workContent").removeAttr("disabled","disabled");
    $("input[name='allDay']").removeAttr("disabled","disabled");
    $("#startDate").removeAttr("disabled","disabled");
    $("#endDate").removeAttr("disabled","disabled");
    $("#rtopics").removeAttr("disabled","disabled");

    $("#workBusinessId").val("");
    //错误提示清空
    $(".errorSheetFormLabel").each(function(){
        $(this).html("");
    });
    $("#resetMySheet").click();
    $("input[name='allDay'][value='false']").prop('checked','true');
    $("input[name='type'][value='2']").prop('checked','true');

    $("#rtopics").html('');
    $("#rtopics").append("<option value='-1'>请选择</option>");

    $("#" + blogTbodyId ).html("");
}
/**
 * timeSheet 验证
 * @returns {boolean}
 */
function timeSheetCheck(){
    var title =  $("#workTitle").val();
    var startDate = $("#startDate").val();
    var endDate = $("#endDate").val();
    var allDay = $("input[name='allDay']:checked").val();

    /*title*/
    if("" == title || "" == title.trim()){
        $("#workTitle + span").html("&nbsp;*标题必填");
        return false;
    }else{
        $("#workTitle + span").html("");
    }
    /*startDate*/
    if("" == startDate || "" == startDate.trim()){
        $("#startDate + span").html("&nbsp;*开始时间必填");
        return false;
    }else{
        $("#startDate + span").html("");
    }
    /*endDate*/
    if("false" == allDay && ("" == endDate || "" == endDate.trim())){
        $("#endDate + span").html("&nbsp;*非全天任务,结束时间必填");
        return false;
    }else{
        $("#endDate + span").html("");
    }
    if("" != endDate && "" != endDate.trim() && startDate >= endDate){
        $("#endDate + span").html("&nbsp;*结束时间应大于开始时间");
        return false;
    }
    //00:00
    if("true" == allDay){
        var minStart = startDate.substr(11,5);
        if("00:00" != minStart){
            $("#startDate + span").html("&nbsp;*全天任务,请将开始时间设为当前天的00:00");
            return false;
        }
        if("" != endDate && "" != endDate.trim()){
            var minEnd = endDate.substr(11,5);
            if("00:00" != minEnd){
                $("#endDate + span").html("&nbsp;*全天任务,请将结束时间设为下一天的00:00");
                return false;
            }
        }
    }
    return true;
}


/**
 * 初始化table
 */
var blogTbodyId = "blogTableTbody";
var blogTbodyCheck = "blogCheck";
function initBlogTableGrid(page,calEvent,readOnly){
    var data={};
    family_ns.page.page = page;
    family_ns.page.sort = "checked desc";
    data= family_ns.page ;
    data.flag = 1;
    data.delFlag = 0;
    if(calEvent)
        data.sheetId = calEvent.businessId;
    else
        data.sheetId = "";
    family_ns.queryAjax({
        type: 'POST',
        url: family_ns.contextPath + "/blog/querySheetBlogs" ,
        data: data ,
        dataType: 'json',
        success: function(result){
            var rows = result.rows;
            //数据判断
            if(!family_ns.clearTableData.call(this,blogTbodyId,rows))
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                resStr += "<tr class='unread checked' ondblclick='browseBlog.call(this,\""+
                    rows[i].businessId
                    +"\",\""+
                    rows[i].type
                    +"\")'>";
                if("1" == rows[i].checked)
                    resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                        + "<input type='radio' name='" + blogTbodyCheck + "' checked='checked' class='checkbox'/></td>";
                else if(readOnly)
                    resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                        + "</td>";
                else
                    resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                        + "<input type='radio' name='" + blogTbodyCheck + "' class='checkbox'/></td>";

                resStr += "<td>"+family_ns.formatterStrByLength(rows[i].title,15,true)+"</td>";
                resStr += "<td>"+family_ns.formatterStrByLength(rows[i].bkeys,10,true)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].createDate)+"</td>";

                resStr += "</tr>";
            }
            $("#" + blogTbodyId ).html(resStr);
            if(1 === result.pageNu){
                initPagination.call(this,'pagination-blog',result.pages,result.pageNu,"initBlogTableGrid");
            }
        }
    });
}

/*初始化 topics*/
function initRtopic(calEvent,readOnly){
    var data={};
    data.flag = 1;
    data.delFlag = 0;

    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/rtopic/queryMyRTopicsByPagination",

        data: data,

        dataType: 'json',

        success: function (result) {
            var flag = false; //匹配上
            if(result.total > 0){
                var options = "";
                for(var i = 0 ; i < result.rows.length; i ++){
                    var businessId = result.rows[i].businessId;
                    if(calEvent && calEvent.topicId == businessId)
                        flag = true;
                    options += "<option value='" + businessId + "'>";
                    options += result.rows[i].title;
                    options += "</option>"
                }
                $("#rtopics").append(options);
                if(flag)
                    $("#rtopics").val(calEvent.topicId);
                //$("#movieTheatre option[value='" + movieUpdate.movieTheatre +"']").prop("selected","selected");
            }
            if(readOnly)
                $("#rtopics").attr("disabled","disabled");
        },error:function (result) {
            console.info(result);
        }
    });
}


//浏览博客
function browseBlog(id,type){
    if("0" == type || 0 == type)
        window.open(family_ns.contextPath + "/blog/browseBlog/"+id,'newwindow','');
    else
        window.open(family_ns.contextPath + "/blog/toHg/toMindUpdatePage/"+id,'newwindow','');

}