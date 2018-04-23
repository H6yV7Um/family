<%--
  Created by family
  User: cy
  Date: 16/6/16
  Time: 上午11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" ></c:set>

<script src="${ctx}/common/js/jQuery/jquery.twbsPagination.js"></script>
<script>
    $(document).ready(function () {

    });
    /**
     * 初始化分页按钮
     * @param total 总页数
     * @param pageNu  当前页数
     */
    function initPagination(pagination,pages,pageNu,twicePagination){
        if(0 == pages)
            pages = 1;
        $('#'+pagination).empty();
        $('#'+pagination).removeData("twbs-pagination");
        $('#'+pagination).unbind("page");
        $('#'+pagination).twbsPagination({
            totalPages: pages,
            startPage: pageNu,
            visiblePages: 13,
            onPageClick: function (event, page) {
                if(twicePagination == null){
                	initTableGrid.call(this,page);
                }else{
                	eval(twicePagination).call(this,page);
                }
                
            }
        });
    }
</script>
