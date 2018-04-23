<%--
  Created by IntelliJ IDEA.
  User: lcy
  Date: 17/2/18
  Time: 上午10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" href="${ctx}/common/ico/family_home.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="${ctx}/common/ui/swiper/css/swiper.min.css">
    <style>
        img{
            max-width: 100%;
        }
    </style>
</head>
<body>

<div class="swiper-container">
    <div class="swiper-wrapper" id="wrappers">
        <div class="swiper-slide" align="center"><img style="height: 100vh;" src="${ctx}/common/images/1.png" alt=""></div>
    </div>
</div>
<!-- js -->
<script src="${ctx}/common/js/jQuery/jquery-1.11.1.min.js"></script>

<script src="${ctx}/common/ui/swiper/js/swiper.min.js"></script>
<!-- cyUtil -->
<script src="${ctx}/common/js/util/cyUtil.js"></script>
<script>
    family_ns.contextPath = "${ctx}";
</script>

<script>
    var books = "${requestScope.book}";
    var booksArray;
    if("[]" == books){
        booksArray = [];
    }
    else
        booksArray = books.split(','); //由JSON字符串转换为JSON对象

    $(function(){

        var swiper = "";

        for(var i = 0 ; i < booksArray.length ; i++){
            var bookKey = booksArray[i].substring(booksArray[i].indexOf("/"),booksArray[i].lastIndexOf("png")+3);
            swiper += "<div class='swiper-slide' align='center'><img style='height: 100vh;' src='" + family_ns.webResourceServer  + bookKey + "' alt=''></div>";
        }

        $("#wrappers").html(swiper);

        var mySwiper = new Swiper ('.swiper-container', {
            direction: 'horizontal',
            loop: true

        });

    });
</script>

</body>
</html>
