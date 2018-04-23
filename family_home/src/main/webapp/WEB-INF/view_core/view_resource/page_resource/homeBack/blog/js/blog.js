/**
 * movie js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "update_date desc,is_top desc";
var vagueQuery;
var blogParam = {};
// tbody
var tbodyId = "blogTableTbody";
var tbodyCheck = "blogCheck";

/**
 * layer
 */
var blogLabelInfo ;
var mindInfo;
//是否可以编辑
var isEditAble = true;
var editAbleBusinessId = "";

var blogLabelTableName = "blogLabelTbody";

$(function(){

    initTableGrid.call(this,1);

    initBlogLabelButtons.call(this);

    var validate = $("#mindForm").validate(
        {
            debug : true, //调试模式取消submit的默认提交功能
            //errorClass: "label.error", //默认为错误的样式类为：error
            focusInvalid : false, //当为false时，验证无效时，没有焦点响应
            onkeyup : false,
            submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
                saveMinderBlog.call(this);
            },

            rules : {
                title : {
                    required : true
                }
            },
            messages : {
                title : {
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
    //缺省是查询全部
    data.blogOwn = "1";
    if(vagueQuery){
        data.title = $("#blogName").val();
        data.blogOwn = $("input[name='blogOwn']:checked").val();
    }
    data.blogLabelId = blogParam.blogLabelId;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/blog/queryBlogs" ,

        data: data ,

        dataType: 'json',

        success: function(result){
            var rows = result.rows;
            //数据判断
            if(!family_ns.clearTableData.call(this,tbodyId,rows))
                return;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                resStr += "<tr class='unread checked' >";
                resStr += "<td class='hidden-xs' id='"+rows[i].businessId+"'>"
                            + "<input type='radio' name='" + tbodyCheck + "' class='checkbox'/></td>";

                resStr += "<td ondblclick='browseBlog.call(this,\""+
                    rows[i].businessId
                    +"\",\""+
                    rows[i].type
                    +"\")'>"+family_ns.formatterStrByLength.call(this,rows[i].title,15,true)+"</td>";
                resStr += "<td>"+family_ns.formatterStrByLength.call(this,rows[i].bkeys,20,true)+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].createDate)+"</td>";
                resStr += "<td>"+formatterBlogClassify.call(this,rows[i].classify)+"</td>";
                resStr += "<td>"+formatterBlogType.call(this,rows[i].type)+"</td>";
                //resStr += "<td>"+stringToBlank.call(this,rows[i].readTimes)+"</td>";

                /*****operation start*******/
                resStr += "<td>";
                if(rows[i].own && "1" == rows[i].own){
                    if(rows[i].isPublic && "1" == rows[i].isPublic)
                        resStr += "<input type='checkbox' class='isPulicBlog'  name='pub_"+rows[i].businessId+"'  checked>";
                    else
                        resStr += "<input type='checkbox' class='isPulicBlog' name='pub_"+rows[i].businessId+"' >";
                }
                //审批权限
                if(blogParam.manageAuthor){
                    if(rows[i].isTop && "1" == rows[i].isTop)
                        resStr += "<input type='checkbox' class='isTj' name='tj_"+rows[i].businessId+"' checked>";
                    else
                        resStr += "<input type='checkbox' class='isTj' name='tj_"+rows[i].businessId+"' >";
                }
                /*****operation  end*******/
                resStr += "</td>";
                resStr += "</tr>";
            }
            $("#blogTableTbody").html(resStr);
            if(1 === result.pageNu){
                initPagination.call(this,'pagination-blog',result.pages,result.pageNu,"initTableGrid");
            }
            initSwitch.call(this);
        }
    });
}

var isSure = false;

/**
 * 初始化switch
 */
function initSwitch(){
    /*初始化public check*/
    $(".isPulicBlog").bootstrapSwitch({
        'onText':'public',
        'offText':'private',
        'size':'normal',
        'onColor':'info',
        "offColor":'warning',
        onSwitchChange: function(event, state) {
            var businessId = $($(this).parents('tr').children().get(0)).attr('id');
            if(!isSure)
                publicBlog.call(this,businessId,state);
            else{
                isSure = false;
                return true;
            }

            return false;
        }
    });
    /*初始化tj check*/
    $(".isTj").bootstrapSwitch({
        'onText':'推荐',
        'offText':'未推荐',
        'onColor':'info',
        'size':'normal',
        "offColor":'warning',
        onSwitchChange: function(event, state) {
            var businessId = $($(this).parents('tr').children().get(0)).attr('id');
            if (!isSure)
                topBlog.call(this, businessId, state);
            else {
                isSure = false;
                return true;
            }
            return false;
        }
    });
}
/**
 * 转换格式 类型
 * @param type
 * @returns {*}
 */
function formatterBlogType(type){
    if(0 == type || "0" == type)
        return "md";
    else if(1 == type || "1" == type)
        return "mind";
    else
        return "";
}


/**
 * 转换格式 分类
 * @param classify
 * @returns {*}
 *
 * 0 综合 1 移动开发 2 架构 3 云计算/大数据 4 互联网 5 运维 6 数据库 7 前端 8 编程语言 9 研发管理
 */
function formatterBlogClassify(classify){
    if(0 == classify || "0" == classify)
        return "综合";
    else if(1 == classify || "1" == classify)
        return "移动开发";
    else if(2 == classify || "2" == classify)
        return "架构";
    else if(3 == classify || "3" == classify)
        return "云计算/大数据";
    else if(4 == classify || "4" == classify)
        return "互联网";
    else if(5 == classify || "5" == classify)
        return "运维";
    else if(6 == classify || "6" == classify)
        return "数据库";
    else if(7 == classify || "7" == classify)
        return "前端";
    else if(8 == classify || "8" == classify)
        return "编程语言";
    else if(9 == classify || "9" == classify)
        return "研发管理";
    else
        return "";
}


/**
 * 初始化labelButton
 */
function initBlogLabelButtons(){
    var data = {};
    data.flag = 1;
    data.delFlag = 0;
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/blog/queryBlogLabels",
        data: data,
        success: function (result) {
            var btns = "";
            if(result.success && result.result.length > 0){
                var resultBtn = result.result;
                for(var i =0; i<resultBtn.length;i++){
                    var res = resultBtn[i];
                    btns += "<button class='btn btn-success' type='button' id='btnType"+res.businessId
                         +"'    vType='"+res.businessId
                         +"' style='margin: 1%'>";

                    if(res.countBlog && res.countBlog > 0){
                        btns += res.title + "&nbsp;&nbsp;" + "<span class='badge' style='background-color: #da4f49'>";
                        btns += res.countBlog;
                    }else{
                        btns += res.title + "<span class='badge' style='display: none;background-color: #da4f49'>";
                    }
                    btns += "</span>";
                    btns += "</button>";
                }
                $("#labelStaticBtns").html(btns);

                initBlogLabelBtn.call(this);
            }
        }
    });
}
/**
 * blogLabelId button
 */
function initBlogLabelBtn() {
    $("#labelStaticBtns button").on("click",function (event) {
        var button = $(this)[0];
        //gold
        if(!$("#"+button.id).hasClass("selectedTypeBtn")){
            $("#labelStaticBtns button").removeClass("selectedTypeBtn");
            $("#"+button.id).addClass("selectedTypeBtn");
            blogParam.blogLabelId = $("#"+button.id).attr("vType");
            searchBlog.call(this);
        }else{
            $("#"+button.id).removeClass("selectedTypeBtn");
            blogParam.blogLabelId = "";
            searchBlog.call(this);
        }

    });
}

/**
 * search blog
 */
function searchBlog(){
    vagueQuery = true;
    initTableGrid(1);
}
/**
 * add md
 */
function addBlog(){
    window.location.href = family_ns.contextPath + "/view/homeBack/blogAdd";
}
/**
 * add minder
 */
function addMinder(){
    mindInfo = layer.open({
        type: 1,
        title: 'mind 信息',
        fix: false,
        //maxmin: true,
        shadeClose: false,
        zIndex: 1111,
        area: ['45%', '50%'],
        content: $("#mindInfo"),
        success: function(layero, index){
            initBlogLabels.call(this);
        },
        end: function () {
            clearMinder.call(this);
        },
        btn: ['确定'],
        yes: function(index, layero){
            $('#mindForm').submit();
        }
    });
}
/**
 * 清空 minder
 */
function clearMinder(){
    $('#resetMinder').click();
    $('#minderId').val("");
    $("#blogLabels").html("");
    $("#blogLabels").append("<option value='-1'>请选择</option>");
    $('#classifyLables').val("0");
}
/**
 * 提交minder
 */
function saveMinderBlog(){
    var formData = family_ns.serializeObject($("#mindForm"));

    var url = family_ns.contextPath + "/blog/blogAdd";
    if($("#minderId").val())
        url = family_ns.contextPath + "/blog/blogUpdate";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type:"post",
            url:url,
            data: formData,
            success:function(result){
                if(result.success){
                    //layer.close(mindInfo);
                    if(!$("#minderId").val())
                        window.open(family_ns.contextPath+"/blog/toHg/toMindUpdatePage/" + result.result.businessId,'newwindow','');
                    family_ns.successJumpSelf.call(this,blogParam.urlSelf,blogParam.modelId);
                }
                else
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
            }
        });
    });

}
function initBlogLabels(blogLabelId){
    var data = {};
    data.flag = 1;
    data.delFlag = 0;
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/blog/queryBlogLabels",
        data: data,
        success: function (result) {
            var btns = "";
            if(result.success && result.result.length > 0){
                var resultBtn = result.result;
                var labelsOption = "";
                for(var i =0; i<resultBtn.length;i++){
                    var res = resultBtn[i];
                    if(blogLabelId && blogLabelId == res.businessId)
                        labelsOption += "<option value='" + res.businessId+ "' selected='selected'>" + res.title + "</option>";
                    else
                        labelsOption += "<option value='" + res.businessId+ "'>" + res.title + "</option>";

                }
                $("#blogLabels").append(labelsOption);
            }
        }
    });
}
/**
 * 修改
 */
function updateBlog(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'blogTableTbody','blogCheck'))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,'blogTableTbody','blogCheck');
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/blog/queryOne/" + businessId,
        data: {},
        success: function (result) {
            if(result.success){
                var type = result.result.type;
                /* md */
                if(0 == type || "0" == type)
                    window.location.href=family_ns.contextPath+"/blog/toHg/toBlogUpdatePage/" + businessId;
                /* mind */
                else if(1 == type || "1" == type){
                    mindInfo = layer.open({
                        type: 1,
                        title: 'mind 信息',
                        fix: false,
                        //maxmin: true,
                        shadeClose: false,
                        zIndex: 1111,
                        area: ['45%', '50%'],
                        content: $("#mindInfo"),
                        success: function(layero, index){
                            initBlogLabels.call(this,result.result.blogLabelId);
                            $("#blogTitle").val(result.result.title);
                            $("#minderId").val(businessId);
                            $("#blogBkeys").val(result.result.bkeys);
                            $("#classifyLables").val(result.result.classify);
                        },
                        end: function () {
                            clearMinder.call(this);
                        },
                        btn: ['修改'],
                        yes: function(index, layero){
                            $('#mindForm').submit();
                        }
                    });
                }else{
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }

            }
        }
    });



}

/***
 * delete movie
 */
function deleteBlog(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'blogTableTbody','blogCheck'))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,'blogTableTbody','blogCheck');
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/blog/blogDelete/" + businessId,
            data: "",
            success: function (result) {
                if (result.success) {
                    family_ns.successJumpSelf.call(this,blogParam.urlSelf,blogParam.modelId);
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });


}
//浏览博客
function browseBlog(id,type){
    if("0" == type || 0 == type)
        window.open(family_ns.contextPath + "/blog/browseBlog/"+id,'newwindow','');
    else
        window.open(family_ns.contextPath + "/blog/toHg/toMindUpdatePage/"+id,'newwindow','');

}

/**
 * 推荐 1
 * 取消推荐 0
 * @param id
 */
function topBlog(id,state){

    state = (state)?'1':'0';
    var data = {};
    data.businessId = id;
    data.isTop = state;
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/blog/topBlog",
            data: data,
            success: function (result) {
                if (result.success) {
                    isSure = true;
                    $("input[name='tj_" + id + "']").bootstrapSwitch('toggleState');
                    family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));

                }
            }
        });
    });
}
/**
 * 公开 1
 * 取消推荐 0
 * @param id
 */
function publicBlog(id,state){
    state = (state)?'1':'0';
    var data = {};
    data.businessId = id;
    data.isPublic = state;
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/blog/publicBlog",
            data: data,
            success: function (result) {
                if (result.success) {
                    isSure = true;
                    $("input[name='pub_" + id + "']").bootstrapSwitch('toggleState');
                    family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });
}
/**
 * 转换星星
 * @param star
 */
function strFormatter(star){
    var starStr = "";
    if(!star || 0 === star)
        return starStr;

    for(var i = 0 ; i < star ; i++){
        starStr += "<i class='fa fa-star icon-state-warning'></i>";
    }
    return starStr;
}

/**
 * blog label 维护
 */
function blogLabelMana(){
    var data = {};
    data.flag = 1;
    data.delFlag = 0;
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/blog/queryBlogLabels",
        data: data,
        success: function (result) {
            if (result.success) {
                blogLabelInfo = layer.open({
                    type: 1,
                    title: '标签管理',
                    fix: false,
                    //maxmin: true,
                    shadeClose: false,
                    zIndex: 1111,
                    area: ['50%', '68%'],
                    content: $("#blogLabelInfo"),
                    success: function(layero, index){
                        initBlogLabel.call(this,result.result);
                    },
                    end: function () {
                        clearBlogLabel.call(this);
                        window.location.href = family_ns.contextPath + "/view/homeBack/blog?mf=2";
                    }
                });
            }
        }
    });
}
/**
 * 初始化blogLabel
 */
function initBlogLabel(result){
    if(!result || result.length == 0)
        return ;
    for(var k = 0; k < result.length ; k ++){
        var row = conversionBlogLabelRow.call(this,result[k]);
        $("#"+blogLabelTableName).append(row);
    }
}
function conversionBlogLabelRow(param){
    var row = "";
    row += "<tr id='"+ param.businessId +"'>";
    row += "<td style='width: 220px;'>"+ param.title;
    row += "</td>";
    row += "<td style='width: 95px;'>" ;
    row += "<button type='button' class='btn btn-default' aria-label='Left Align' style='width: 33px;' onclick='minusLabel(\""+
        param.businessId +"\")'>";
    row += "<span class='icon-minus' style='margin-left: 3%' ></span>";
    row += "</button>&nbsp;&nbsp;";
    row += "<button type='button' class='btn btn-default' aria-label='Left Align' style='width: 33px;' onclick='editLabel(\""+
        param.businessId +"\")'>";
    row += "<span class='icon-edit' style='margin-left: 3%' ></span>";
    row += "</button>";
    row += "</td>";
    row += "<td style='width: 120px;'>"+ param.countBlog;
    row += "</td>";
    row += "</tr>"
    return row;
}


function clearBlogLabel(){
    $("#labelTitle").val("");
    $("#"+blogLabelTableName).html("");
}



/**
 * add label
 */
function addLabel(){
    var server = {};
    server.title = $("#labelTitle").val();
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/blog/blogLabelAdd" ,
            data: server,
            success: function (result) {
                if (result.success) {
                    family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                    var row = conversionBlogLabelRow.call(this,result.result);
                    $("#"+blogLabelTableName).append(row);
                    clearBlogLabelInput.call(this);
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });
}
/**
 * minus label
 */
function minusLabel(businessId){
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/blog/blogLabelDelete/" + businessId,
            data: "",
            success: function (result) {
                if (result.success) {
                    family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                    $("#" + blogLabelTableName+ " tr[id='"+businessId+"']").remove();
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });
}
/**
 * 编辑 label
 */

function editLabel(businessId) {
    //编辑
    if(isEditAble){
        isEditAble = false;
        editAbleBusinessId = businessId;
        var title = $("#" + blogLabelTableName+ " tr[id='"+businessId+"'] td:first").html();
        $("#" + blogLabelTableName+ " tr[id='"+businessId+"'] td:first").html("<input value='"+title+"'/>");
    }
    //结束编辑
    else{
        if(businessId != editAbleBusinessId){
            family_ns.operatorAlertError(family_ns.getErrMessage("只能同时编辑一个标签"));
            return;
        }
        var title = $("#" + blogLabelTableName+ " tr[id='"+businessId+"'] td:first input").val();
        var param ={};
        param.title = title;
        param.businessId = businessId;
        family_ns.operateConfirmByAjax(function(){
            family_ns.submitAjax({
                type: "post",
                url: family_ns.contextPath + "/blog/blogLabelUpdate",
                data: param,
                success: function (result) {
                    if (result.success) {
                        family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                        isEditAble = true;
                        $("#" + blogLabelTableName+ " tr[id='"+businessId+"'] td:first").html(title);
                    }
                    else {
                        family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                    }
                }
            });
        });
    }
}

function clearBlogLabelInput(){
    $("#labelTitle").val("");
}

