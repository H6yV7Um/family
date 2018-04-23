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
    <title>帐号信息</title>
</head>
<body>
<style type="text/css">
    label{
        display: inherit;
    }
</style>
<script src="${ctx}/page_resource/homeBack/account/js/accountInfo.js"></script>

<script type="text/javascript">
    accountParam.modelId = "${param.modelId}";
    accountParam.urlSelf = "/view/homeBack/index";
</script>
<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        帐号信息
    </h1>


    <div class="row">

        <div class="span9">

            <div class="widget">

                <div class="widget-header">
                    <h3>修改密码</h3>
                </div> <!-- /widget-header -->

                <div class="widget-content">



                    <div class="tabbable">
                        <br />

                        <div class="tab-content">
                            <div class="tab-pane active">
                                <form id="accountForm" class="form-horizontal" method="post" />
                                <fieldset>

                                    <div class="control-group">
                                        <label class="control-label" for="accountLastPass">原始密码</label>
                                        <div class="controls">
                                            <input type="password" class="input-xlarge" name="accountLastPass" id="accountLastPass" value=""/>
                                            <span class="errorSheetFormLabel" ></span>
                                        </div> <!-- /controls -->
                                    </div> <!-- /control-group -->

                                    <div class="control-group">
                                        <label class="control-label" for="accountPass">最新密码</label>
                                        <div class="controls">
                                            <input type="password" class="input-xlarge" name="accountPass" id="accountPass" value=""/>
                                            <span class="errorSheetFormLabel" ></span>
                                        </div> <!-- /controls -->
                                    </div> <!-- /control-group -->



                                    <div class="control-group">
                                        <label class="control-label" for="accountRepeatPass">重复密码</label>
                                        <div class="controls">
                                            <input type="password" class="input-xlarge" name="accountRepeatPass" id="accountRepeatPass" value=""/>
                                            <span class="errorSheetFormLabel" ></span>
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
