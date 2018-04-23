<%--
  Created by IntelliJ IDEA.
  User: lcy
  Date: 17/1/3
  Time: 下午4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/common/common_home.jsp" %>
<html>
<head>
    <title>新增帐号</title>
</head>
<body>

<script src="${ctx}/page_resource/homeBack/account/js/accountAdd.js"></script>
<script type="text/javascript">
    accountUpdate.businessId = "${accountUpdate.businessId}";
    accountUpdate.type = "${accountUpdate.type}";
</script>
<style>
    .errorSheetFormLabel{
        color: red;
    }
    /*覆盖jquery validation error css*/
    .errorSheetFormLabel label{
        display:inherit;
        color: red;
    }

</style>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        添加帐号
    </h1>


    <div class="row">

        <div class="span9">

            <div class="widget">

                <div class="widget-header">
                    <h3>基本信息</h3>
                </div> <!-- /widget-header -->

                <div class="widget-content">



                    <div class="tabbable">
                        <br />

                        <div class="tab-content">
                            <div class="tab-pane active">
                                <form id="accountForm" class="form-horizontal" method="post" />
                                    <fieldset>
                                        <div class="control-group">
                                            <label class="control-label" for="accountTitle">标题</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" name="title" id="accountTitle" value="${accountUpdate.title}"/>
                                                <span class="errorSheetFormLabel" ></span>
                                                <input type="hidden"  name="businessId" value="${accountUpdate.businessId}" />
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <div class="control-group">
                                            <label class="control-label" for="typeAccount" >类型</label>
                                            <div class="controls">
                                                <select name="type" id="typeAccount" class="input-xlarge" >
                                                    <option value="-1">请选择</option>
                                                    <option value="1">网站</option>
                                                    <option value="2">服务器</option>
                                                    <option value="0">其他</option>
                                                </select>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <div class="control-group">
                                            <label class="control-label" for="account">帐号</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" name="account" id="account" value="${accountUpdate.account}"/>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->
                                        <div class="control-group">
                                            <label class="control-label" for="passwd">密码</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" name="passwd" id="passwd" value="${accountUpdate.passwd}"/>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->
                                        <div class="control-group">
                                            <label class="control-label" for="description">描述</label>
                                            <div class="controls">
                                                <textarea class="input-xlarge"  name="description" id="description" rows="4"></textarea>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <div class="control-group">
                                            <label class="control-label" for="url">url</label>
                                            <div class="controls">
                                                <input type="text" class="input-xlarge" id="url" name="url" value="${accountUpdate.url}" />
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <br />


                                        <div class="form-actions">
                                            <button type="submit" class="btn btn-primary" >Save</button>
                                            <!--<button class="btn">Cancel</button>-->
                                        </div> <!-- /form-actions -->
                                    </fieldset>
                                </form>
                            </div>
                        </div>
                    </div>
                </div> <!-- /widget-content -->

            </div> <!-- /widget -->

        </div> <!-- /span9 -->




</div> <!-- /row -->

</div> <!-- /span9 -->

</body>
</html>
