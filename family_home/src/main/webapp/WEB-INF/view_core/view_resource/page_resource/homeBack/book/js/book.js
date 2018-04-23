/**
 * movie js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "update_date desc,is_top desc";
var vagueQuery;
var bookParam = {};
// tbody
var tbodyId = "bookTableTbody";
var tbodyCheck = "bookCheck";

/**
 * layer
 */
var bookInfo ;
$(function(){

    initTableGrid.call(this,1);

    var validate = $("#bookForm").validate(
        {
            debug : true, //调试模式取消submit的默认提交功能
            //errorClass: "label.error", //默认为错误的样式类为：error
            focusInvalid : false, //当为false时，验证无效时，没有焦点响应
            onkeyup : false,
            submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
                saveBook.call(this);
            },

            rules : {
                name : {
                    required : true,
                    rangelength:[5,40]
                },
                code : {
                    required : true,
                    rangelength:[8,8],
                    remote: {
                        url: family_ns.contextPath + "/book/queryCodeValidate",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            code: function () {
                                return $("#bookCode").val();
                            },
                            businessId: function () {
                                return $("#bookId").val();
                            },
                            delFlag : "0",
                            page: 1,
                            rows: 10
                        }  //远程地址只能输出 "true" 或 "false"，不能有其他输出。
                    }
                }
            },
            messages : {
                name : {
                    required : "*必填",
                    rangelength: "{0} 到 {1} 之间的字符"

                },
                code : {
                    required : "*必填",
                    rangelength: "请输入{0}个字符",
                    remote : "当前编号已存在,不能使用,请更换"
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



    $('#file_upload_first').uploadifive({
        'auto'             : true,
        //'checkScript'      : 'check-exists.php',
        'formData'         : {
        },
        'fileType'     : 'application/pdf',
        'fileSizeLimit' : "400MB",
        'multi' : false,
        'queueID'          : 'queue_first',
        'uploadScript'     : family_ns.contextPath + '/book/uploadBook',
        'onUploadComplete' : function(file, data) {
            //file.name  originName
            var result = eval('('+data+')');
            if(result.success){
                //family_ns.webResourceServer
                $("#bookUrl").val(result.result);
            }
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
    data.bookOwn = "1";
    if(vagueQuery){
        data.title = $("#bookName").val();
        data.bookOwn = $("input[name='bookOwn']:checked").val();
    }
    data.bookLabelId = bookParam.bookLabelId;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/book/queryFBooksByPagination" ,

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

                resStr += "<td ondblclick='browseBook.call(this,\""+
                    rows[i].businessId
                    +"\",\""+
                    rows[i].type
                    +"\")'>"+family_ns.formatterStrByLength.call(this,rows[i].name,15,true)+"</td>";
                resStr += "<td>"+rows[i].code+"</td>";
                resStr += "<td>"+stringToBlank.call(this,rows[i].createDate)+"</td>";
                resStr += "<td>"+formatterBookClassify.call(this,rows[i].classify)+"</td>";
                resStr += "<td>"+formatterBookCase.call(this,rows[i].bookCase)+"</td>";

                /*****operation start*******/
                resStr += "<td>";
                if(rows[i].own && "1" == rows[i].own){
                    if(rows[i].isPublic && "1" == rows[i].isPublic)
                        resStr += "<input type='checkbox' class='isPulicBook' name='pub_"+rows[i].businessId+"'  checked>";
                    else
                        resStr += "<input type='checkbox' class='isPulicBook' name='pub_"+rows[i].businessId+"' >";
                }
                //审批权限
                if(bookParam.manageAuthor){
                    if(rows[i].isTop && "1" == rows[i].isTop)
                        resStr += "<input type='checkbox' class='isTj' name='tj_"+rows[i].businessId+"' checked>";
                    else
                        resStr += "<input type='checkbox' class='isTj' name='tj_"+rows[i].businessId+"' >";
                }
                /*****operation  end*******/
                resStr += "</td>";
                resStr += "</tr>";
            }
            $("#" + tbodyId).html(resStr);
            if(1 === result.pageNu){
                initPagination.call(this,'pagination-book',result.pages,result.pageNu,"initTableGrid");
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
    $(".isPulicBook").bootstrapSwitch({
        'onText':'public',
        'offText':'private',
        'size':'normal',
        'onColor':'info',
        "offColor":'warning',
        onSwitchChange: function(event, state) {
            var businessId = $($(this).parents('tr').children().get(0)).attr('id');
            if(!isSure)
                publicBook.call(this,businessId,state);
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
                topBook.call(this, businessId, state);
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
function formatterBookCase(bookCase){
    if(0 == bookCase || "0" == bookCase)
        return "初级";
    else if(1 == bookCase || "1" == bookCase)
        return "中级";
    else if(2 == bookCase || "2" == bookCase)
        return "高级";
    else if(3 == bookCase || "3" == bookCase)
        return "资深级";
    else if(4 == bookCase || "4" == bookCase)
        return "专家级";
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
function formatterBookClassify(classify){
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
 * search book
 */
function searchBook(){
    vagueQuery = true;
    initTableGrid(1);
}
/**
 * add book
 */
function addBook(){
    bookInfo = layer.open({
        type: 1,
        title: '书籍信息',
        fix: false,
        //maxmin: true,
        shadeClose: false,
        zIndex: 1111,
        area: ['45%', '58%'],
        content: $("#bookInfo"),
        success: function(layero, index){
        },
        end: function () {
            clearBook.call(this);
        },
        btn: ['确定'],
        yes: function(index, layero){
            $('#bookForm').submit();
        }
    });
}
/**
 * 清空 minder
 */
function clearBook(){
    $('#resetBook').click();
    $('#minderId').val("");
    $("#bookLabels").html("");
    $("#bookLabels").append("<option value='-1'>请选择</option>");
    $('#classifyLables').val("0");
}
/**
 * 提交minder
 */
function saveBook(){
    var formData = family_ns.serializeObject($("#bookForm"));
    var url = family_ns.contextPath + "/book/bookAdd";
    if($("#bookId").val())
        url = family_ns.contextPath + "/book/bookUpdate";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type:"post",
            url:url,
            data: formData,
            success:function(result){
                if(result.success){
                    family_ns.successJumpSelf.call(this,bookParam.urlSelf,bookParam.modelId);
                }
                else
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
            }
        });
    });

}
function initBookLabels(bookLabelId){
    var data = {};
    data.flag = 1;
    data.delFlag = 0;
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/book/queryBookLabels",
        data: data,
        success: function (result) {
            var btns = "";
            if(result.success && result.result.length > 0){
                var resultBtn = result.result;
                var labelsOption = "";
                for(var i =0; i<resultBtn.length;i++){
                    var res = resultBtn[i];
                    if(bookLabelId && bookLabelId == res.businessId)
                        labelsOption += "<option value='" + res.businessId+ "' selected='selected'>" + res.title + "</option>";
                    else
                        labelsOption += "<option value='" + res.businessId+ "'>" + res.title + "</option>";

                }
                $("#bookLabels").append(labelsOption);
            }
        }
    });
}
/**
 * 修改
 */
function updateBook(){
    if(!family_ns.isCommonTableSelectedRow.call(this,'bookTableTbody','bookCheck'))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,'bookTableTbody','bookCheck');
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/book/queryOne/" + businessId,
        data: {},
        success: function (result) {
            if(result.success){
                var type = result.result.type;
                /* md */
                if(0 == type || "0" == type)
                    window.location.href=family_ns.contextPath+"/book/toHg/toBookUpdatePage/" + businessId;
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
                            initBookLabels.call(this,result.result.bookLabelId);
                            $("#bookTitle").val(result.result.title);
                            $("#minderId").val(businessId);
                            $("#bookBkeys").val(result.result.bkeys);
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
function deleteBook(){
    if(!family_ns.isCommonTableSelectedRow.call(this,tbodyId,tbodyCheck))
        return;
    var businessId = family_ns.getCommonTableSelectedRowBusinessId.call(this,tbodyId,tbodyCheck);
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/book/bookDelete/" + businessId,
            data: "",
            success: function (result) {
                if (result.success) {
                    family_ns.successJumpSelf.call(this,bookParam.urlSelf,bookParam.modelId);
                }
                else {
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
                }
            }
        });
    });


}
//浏览博客
function browseBook(id,type){
    if("0" == type || 0 == type)
        window.open(family_ns.contextPath + "/book/browseBook/"+id,'newwindow','');
    else
        window.open(family_ns.contextPath + "/book/toHg/toMindUpdatePage/"+id,'newwindow','');

}

/**
 * 推荐 1
 * 取消推荐 0
 * @param id
 */
function topBook(id,state){

    state = (state)?'1':'0';
    var data = {};
    data.businessId = id;
    data.isTop = state;
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/book/topBook",
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
function publicBook(id,state){
    state = (state)?'1':'0';
    var data = {};
    data.businessId = id;
    data.isPublic = state;
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type: "post",
            url: family_ns.contextPath + "/book/publicBook",
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
