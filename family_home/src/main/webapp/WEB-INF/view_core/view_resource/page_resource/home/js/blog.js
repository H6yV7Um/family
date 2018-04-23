/**
 * movie js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "read_times desc";
$(function(){
    initTableGrid.call(this,1);
    initClassify.call(this);
    initRTopic.call(this);
});
/**
 * 每页3个
 */
var blogRows = 3;
var blogCurrentPage = 1;
var blogCountPages = 0 ;

/*classify*/
var blogClassify;

/*rtopic*/
var blogRtopic;
/**
 * 初始化table
 */
function initTableGrid(){
    var data={};
    blogCountPages = blogCountPages>0 ? blogCountPages : 0;
    data.page = blogCurrentPage;
    data.rows = blogRows;
    data.flag = 1;
    data.delFlag = 0;
    data.sort = " is_top desc";
    /*blog classify*/
    if(blogClassify)
        data.classify = blogClassify;
    /*blog rtopic*/
    else if(blogRtopic)
        data.rtopic = blogRtopic;
    else
        data.isTop = 1;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/blog/queryBlogsAnon" ,

        data: data ,

        dataType: 'json',

        success: function(result) {
            var rows = result.rows;
            if (!rows || 0 == rows.length) {
                $("#blogs").html("");
                return;
            }
            blogCountPages = result.pages;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                resStr += "<div class='blog'>";
                resStr +=   "<div class='media'>";
                resStr +=       "<div class='media-left'>";
                resStr +=           "<a href='#'>";
                resStr +=               "<img class='media-object about-left' src='" + family_ns.contextPath + "/common/images/home/user.jpg' alt=''>";
                resStr +=           "</a>";
                resStr +=           "<div class='col-lg-4'>";
                resStr +=               "<label>" + rows[i].blogUseRealName+ "</label>";
                resStr +=           "</div>";
                resStr +=        "</div>";
                resStr +=        "<div class='media-body'>";
                resStr +=           "<h4 class='media-heading'>" + family_ns.formatterStrByLength.call(this,rows[i].title,20,true) + "</h4>";
                resStr +=               "<div class='card-content'>";
                //md
                if(0 == rows[i].type || "0" == rows[i].type){
                    var desc = rows[i].bdesc.substr(0,200);
                    desc = family_ns.html_encode.call(this,desc);
                    resStr +=
                        desc;
                }
                resStr +=               "</div>";
                resStr +=           "<div class='card-footer'>";
                resStr +=               "<h6 class='footer-tip'><a href='###' onclick='browseBlog.call(this,\""+
                    rows[i].businessId
                    +"\",\""+
                    rows[i].type
                    + "\")' class='read_more'>阅读原文>></a></h6>";
                resStr +=           "</div>";
                resStr +=         "</div>";
                resStr +=   "</div>";
                resStr += "</div>";
            }
            resStr += "<nav aria-label='' id='blogPage'>";
            resStr +=   "<ul class='pager'>";
            /*第一页*/
            if(blogCurrentPage  - 1 > 0)
                resStr +=     "<li class='previous'><a href='###' onclick='preBlog()'><span aria-hidden='true'>&larr;</span> Previous</a></li>";
            /*最后一页*/
            if(blogCountPages - blogCurrentPage > 0)
                resStr +=     "<li class='next'><a href='###' onclick='nextBlog()'>Next <span aria-hidden='true'>&rarr;</span></a></li>";
            resStr +=   "</ul>";
            resStr += "</nav>";
            $("#blogs").html(resStr);
        },error:function (result) {
            console.info(result);
        }
    });
}
/**
 * 初始化分类
 */
function initClassify(){
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/blog/staticClassify",

        data: {},

        dataType: 'json',

        success: function (result) {
            if (result.success) {
                var classifyList = result.result;
                /*遍历*/
                for(var i = 0 ; i < classifyList.length ; i++){
                    var clVar = classifyList[i];
                    $("#classify_" + clVar.classify).html("  (" + clVar.count + ")");
                    //$("#classify_" + clVar.classify).parent();
                }

                $("span[id*='classify_']").each(function(index,element){
                    // count 0 , 取消 click
                    if($(this).html() == "")
                        $(this).parent().removeAttr("onclick");
                });

            }
        }
    });
}

/**
 * 刷新 classify
 */
function refreshClassify(index,name) {
    $("#classifyName").html(name);
    blogClassify = index;
    blogRtopic = undefined;
    initTableGrid.call(this);
    $('body,html').animate({scrollTop:0},1000);//有动画
}
/**
 * 刷新 rtopic
 * @param businessId
 * @param rtopic
 */
function refreshRtopic(businessId,rtopic){
    $("#classifyName").html(rtopic);
    blogClassify = undefined;
    blogRtopic = businessId;
    initTableGrid.call(this);
    $('body,html').animate({scrollTop:0},1000);//有动画
}


/**
 * 浏览 blog
 */
function browseBlog(id,type){
    if("0" == type || 0 == type)
        window.open(family_ns.contextPath + "/blog/browseBlog/"+id,'newwindow','');
    else
        window.open(family_ns.contextPath + "/blog/toHg/toMindUpdatePage/"+id,'newwindow','');
}
/**
 * 向前
 */
function preBlog(){
    blogCurrentPage --;
    initTableGrid.call(this);
}
/**
 * 向后
 */
function nextBlog(){
    blogCurrentPage ++;
    initTableGrid.call(this);
}
/**
 * 初始化Rtopic
 */
function initRTopic(){
    var data={};
    data.page = 1;
    data.rows = 6;
    data.flag = 1;
    data.delFlag = 0;
    data.sort = " create_date desc";

    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/rtopic/queryRTopicsAnno" ,

        data: data ,

        dataType: 'json',

        success: function(result) {
            var rows = result.rows;
            if (!rows || 0 == rows.length) {
                $("#rtopicList").html("");
                return;
            }
            //遍历
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++) {
                if(i%2 == 0){
                    resStr += "<div class=row'>";
                    resStr +=   "<div class='col-md-6'>";
                    if(rows[i].blogCount == 0){
                        resStr += "<button class='btn btn-default btn-trigger'  style='border: 1px solid #eee'>" + family_ns.formatterStrByLength.call(this,rows[i].title,10,true) + "</button>";
                    }
                    else
                        resStr += "<button class='btn btn-default btn-trigger' onclick='refreshRtopic(\"" + rows[i].businessId + "\",\"" + rows[i].title + "\")' style='border: 1px solid #eee'>" + family_ns.formatterStrByLength.call(this,rows[i].title,10,true) + "(" + rows[i].blogCount + ")</button>";
                    resStr +=   "</div>";
                }
                else{
                    resStr +=   "<div class='col-md-6'>";
                    if(rows[i].blogCount == 0){
                        resStr += "<button class='btn btn-default btn-trigger'  style='border: 1px solid #eee'>" + family_ns.formatterStrByLength.call(this,rows[i].title,10,true) + "</button>";
                    }
                    else
                        resStr += "<button class='btn btn-default btn-trigger' onclick='refreshRtopic(\"" + rows[i].businessId + "\",\"" + rows[i].title + "\")' style='border: 1px solid #eee'>" + family_ns.formatterStrByLength.call(this,rows[i].title,10,true) + "(" + rows[i].blogCount + ")</button>";
                    resStr +=   "</div>";
                    resStr += "</div>";
                }
            }
            if(rows.length%2 != 0)
                resStr += "</div>";
            $("#rtopicList").html(resStr);
        },error:function (result) {
            console.info(result);
        }
    });
}