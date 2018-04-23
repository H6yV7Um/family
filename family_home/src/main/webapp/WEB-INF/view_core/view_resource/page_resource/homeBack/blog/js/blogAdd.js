/**
 * Created by cy on 16/6/22.
 */
var testEditor;

var blogUpdate = {};

$(function(){

    initBlogView.call(this);

    initBlogLabels.call(this);

    testEditor = editormd("test-editormd", {
        width   : "100%",
        height  : 680,
        syncScrolling : "single",
        saveHTMLToTextarea:true,
        emoji : true,
        taskList : true,
        tocm          : true,         // Using [TOCM]
        tex : true,                   // 开启科学公式TeX语言支持，默认关闭
        flowChart : true,             // 开启流程图支持，默认关闭
        sequenceDiagram : true,       // 开启时序/序列图支持，默认关闭,
        path : family_ns.contextPath+'/common/ui/mdeditor/',
        pluginPath : family_ns.contextPath + "/common/ui/mdeditor/plugins/",
        imageUpload : true,
        imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL : family_ns.contextPath+"/blog/updatePicture"
        /*
        上传的后台只需要返回一个 JSON 数据，结构如下：
         {
             success : 0 | 1,           // 0 表示上传失败，1 表示上传成功
                 message : "提示的信息，上传成功或上传失败及错误信息等。",
             url     : "图片地址"        // 上传成功时才返回
         }
        */
    });



    $("#blogTitle").focus();

    //修改
    if(blogUpdate.businessId){
        initialUpdateInfo.call(this);
    }
});
/**
 * 初始化view
 */
function initBlogView(){
    $("#homeBackLeftMenu").hide();
}
/***
 * 初始化修改操作
 */
function initialUpdateInfo(){
    //testEditor.getMarkdown();       // 获取 Markdown 源码
    //testEditor.getHTML();           // 获取 Textarea 保存的 HTML 源码
    //testEditor.getPreviewedHTML();  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea
    $("#classifyLables").val(blogUpdate.classify);
}

/**
 * 保存 博客
 */
function saveBlog(){
    var formData = getBlogInfo.call(this);
    var url = family_ns.contextPath + "/blog/blogAdd";
    if(blogUpdate.businessId)
        url = family_ns.contextPath + "/blog/blogUpdate";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type:"post",
            url:url,
            data: formData,
            success:function(result){
                if(result.success){
                    window.location.href = family_ns.contextPath + "/view/homeBack/blog?mf=1&modelId="
                        + blogUpdate.modelId;
                }
                else
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.messgae));
            }
        })
    })
}


/**
 * 获取blog 信息
**/
function getBlogInfo(){
    var param = {};
    //param.businessId = "";
    //console.info(testEditor.getHTML());
    if(blogUpdate.businessId)
        param.businessId = blogUpdate.businessId;
    param.title = $("#blogTitle").val();
    param.type = '0';
    param.bdesc = testEditor.getMarkdown();
    param.bdescHtml = testEditor.getPreviewedHTML();
    param.bkeys = $("#blogBkeys").val();
    param.classify = $("#classifyLables").val();
    var blogLabelId = $("#blogLabels").val();
    if("-1" != blogLabelId)
        param.blogLabelId = blogLabelId;
    return param;
}


/**
 * 初始化labelButton
 */
function initBlogLabels(){
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
                    if(blogUpdate.blogLabelId == res.businessId)
                        labelsOption += "<option value='" + res.businessId+ "' selected='selected'>" + res.title + "</option>";
                    labelsOption += "<option value='" + res.businessId+ "'>" + res.title + "</option>";

                }
                $("#blogLabels").append(labelsOption);
            }
        }
    });
}