/**
 * Created by cy on 16/6/24.
 */
var blogEditorView;
var markdownId;
var markdown;
$(function(){
    initBlogMdiew.call(this);
});


/*
* 初始化 blog view
* */
function initBlogMdiew(){
    $.post(family_ns.contextPath + "/blog/queryBlogById/"+markdownId,null,function(result){
        if(family_ns.noAuthorizcationByAjax.call(this,result)){
            return;
        }
        blogEditorView = editormd.markdownToHTML("blog-editormd-view", {
            markdown        : result.bcontent ,//+ "\r\n" + $("#append-test").text(),
            //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启
            htmlDecode      : "style,script,iframe",  // you can filter tags decode
            //toc             : false,
            tocm            : true,    // Using [TOCM]
            //tocContainer    : "#custom-toc-container", // 自定义 ToC 容器层
            //gfm             : false,
            //tocDropdown     : true,
            // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签
            emoji           : true,
            taskList        : true,
            tex             : true,  // 默认不解析
            flowChart       : true,  // 默认不解析
            sequenceDiagram : true,  // 默认不解析
        });
        $("#b_title").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.title);
        $("#b_desc").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.bdesc);
        $("#b_createDate").html("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.createDate);

    },'json');

}

/***
 * 确认提交 blog操作
 */
function operateConfirmBlog(formId){
    if(family_ns.confirm){
        family_ns.confirm = false ;
        return true;
    }
    swal({
        title: "您确定要提交吗？",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnConfirm: false
    }, function () {
        family_ns.confirm = true;
        //testEditor.getMarkdown();       // 获取 Markdown 源码
        //testEditor.getHTML();           // 获取 Textarea 保存的 HTML 源码
        //testEditor.getPreviewedHTML();  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea
        $("#"+formId).submit();
    });
    return false;

}