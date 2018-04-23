/**
 * movie js
 * @author lcy
 * @Date    6-15
 */

family_ns.page.sort = "is_top desc";
$(function(){
    initTableGrid.call(this,1);
    initClassify.call(this);
    initSwiper.call(this);

});
/**
 * 每页3个
 */
var bookRows = 12;
var bookCurrentPage = 1;
var bookCountPages = 0 ;

/*classify*/
var bookClassify;


/**
 * 初始化table
 */
function initTableGrid(){
    var data={};
    bookCountPages = bookCountPages > 0 ? bookCountPages : 0;
    data.page = bookCurrentPage;
    data.rows = bookRows;
    data.flag = 1;
    data.delFlag = 0;
    data.sort = " is_top desc";
    /*book classify*/
    if(bookClassify)
        data.classify = bookClassify;
    else
        data.isTop = 1;
    family_ns.queryAjax({
        type: 'POST',

        url: family_ns.contextPath + "/book/queryBooksAnon" ,

        data: data ,

        dataType: 'json',

        success: function(result) {
            var rows = result.rows;
            if (!rows || 0 == rows.length) {
                $("#books").html("");
                return;
            }
            bookCountPages = result.pages;
            var resStr = "";
            for(var i = 0 ; i < rows.length ; i ++){
                if(i % 3 == 0)
                    resStr += "<div class='row'>";

                resStr +=   "<div class='col s4'>";
                resStr +=       "<div class='card hoverable'>";
                resStr +=           "<div class='card-image'>";
                resStr +=               "<img src='" + family_ns.webResourceServer + rows[i].bookCover  + "'>";
                resStr +=               "<span class='card-title'></span>";
                resStr +=               "<a class='btn-floating halfway-fab waves-effect waves-light amber' href='###' onclick='browseBook(\"" + rows[i].businessId + "\")'>" +
                                            "<i class='material-icons'>local_library</i></a>";
                resStr +=           "</div>";
                resStr +=           "<div class='card-content'>";
                resStr +=               "<p>" + rows[i].name + "&nbsp;&nbsp;[" + rows[i].code + "]</p>";
                resStr +=           "</div>";
                resStr +=       "</div>"
                resStr +=   "</div>";

                if((i+1) % 3 == 0)
                    resStr += "</div>";
            }
            resStr += "</div>";

            $("#bookList").html(resStr);

            /*分页 page book pageEffect*/
            var pageStr = "";
            /*第一页*/
            if(bookCurrentPage  - 1 > 0)
                //pageStr +=     "<li class='previous'><a href='###' onclick='preBlog()'><span aria-hidden='true'>&larr;</span> Previous</a></li>";
                pageStr += "<li class='waves-effect'><a href='#!' onclick='preBook()'><span aria-hidden='true'>←</span>Previous</a></li>";
            /*最后一页*/
            if(bookCountPages - bookCurrentPage > 0)
                pageStr += "<li class='waves-effect'><a href='#!' onclick='nextBook()' >Next<span aria-hidden='true'>→</span></a></li>";
            $("#pageEffect").html(pageStr);

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

        url: family_ns.contextPath + "/book/staticClassify",

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
 * 移动端不显示
 */
function initSwiper(){
    var options = {
        direction: 'horizontal',
        loop: true

    };
    if (window.innerWidth <= 768) {
        document.getElementsByClassName("swiper-button-black")[0].style.display='none';
        document.getElementsByClassName("swiper-button-black")[1].style.display='none';
    }else{
        options["navigation"] = {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        }
    };
    var mySwiper = new Swiper('.swiper-container',options);
}
/**
 * 刷新 classify
 */
function refreshClassify(index,name) {
    $("#bookTitle").html(name);
    bookClassify = index;
    initTableGrid.call(this);
    $('body,html').animate({scrollTop:0},1000);//有动画
}


/**
 * 浏览 book
 */
function browseBook(id){
    window.open(family_ns.contextPath + "/book/browseBook/" + id);
}
/**
 * 向前
 */
function preBook(){
    bookCurrentPage --;
    initTableGrid.call(this);
}
/**
 * 向后
 */
function nextBook(){
    bookCurrentPage ++;
    initTableGrid.call(this);
}
