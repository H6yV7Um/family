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
    <title>新增电影</title>
</head>
<body>
<!--home star-->
<link rel="stylesheet" href="${ctx}/common/css/homeBack/homeStar.css">

<script src="${ctx}/page_resource/homeBack/movie/js/startScore.js"></script>
<script src="${ctx}/page_resource/homeBack/movie/js/movieAdd.js"></script>
<script type="text/javascript">
    movieUpdate.movieTheatre = "${movieUpdate.movieTheatre}";
    movieUpdate.star = "${movieUpdate.star}";
    movieUpdate.businessId = "${movieUpdate.businessId}";
    movieUpdate.modelId = "${param.modelId}";
</script>

<div class="span9">

    <h1 class="page-title">
        <i class="icon-th-large"></i>
        添加电影
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
                                <form id="movieForm" class="form-horizontal" />
                                    <fieldset>
                                        <!--
                                        <div class="control-group">
                                            <label class="control-label" for="username">Username</label>
                                            <div class="controls">
                                                <input type="text" class="input-medium disabled" id="username" value="goideate" disabled="" />
                                                <p class="help-block">Your username is for logging in and cannot be changed.</p>
                                            </div>
                                        </div>  -->


                                        <div class="control-group">
                                            <label class="control-label" for="movieName">电影名称</label>
                                            <div class="controls">
                                                <input type="text" class="input-large" name="name" id="movieName" value="${movieUpdate.name}"/>
                                                <input type="hidden" class="input-large" name="businessId" value="${movieUpdate.businessId}" />
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <div class="control-group">
                                            <label class="control-label" for="movieTheatre" >电&nbsp;&nbsp;影&nbsp;&nbsp;院</label>
                                            <div class="controls">
                                                <select name="movieTheatre" id="movieTheatre">
                                                    <option value="-1">请选择</option>

                                                </select>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <div class="control-group">
                                            <label class="control-label" for="movieDate">电影时间</label>
                                            <div class="controls">
                                                <input type="text" name="movieDate"  class="datetimeClass" id="movieDate" value="${movieUpdate.formatterMovieDate}">
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <div class="control-group">
                                            <label class="control-label" for="price">影票价格</label>
                                            <div class="controls">
                                                <input type="text" class="input-large" name="price" id="price" value="${movieUpdate.price}"/>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <div class="control-group">
                                            <label class="control-label" for="location">电影座位</label>
                                            <div class="controls">
                                                <input type="text" class="input-large" name="location" id="location" value="${movieUpdate.location}"/>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->



                                        <div class="control-group">
                                            <label class="control-label" for="movieInterval">电影时长</label>
                                            <div class="controls">
                                                <input type="text" class="input-large" id="movieInterval" name="movieInterval" value="${movieUpdate.movieInterval}" />
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <div class="control-group">
                                            <label class="control-label" for="flyCount">观影人数</label>
                                            <div class="controls">
                                                <input type="text" class="input-large" name="flyCount" id="flyCount" value="${movieUpdate.flyCount}"/>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <div class="control-group">
                                            <label class="control-label" for="remarks">电影备注</label>
                                            <div class="controls">
                                                <input type="text" class="input-large" name="remarks" id="remarks" value="${movieUpdate.remarks}"/>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->

                                        <div class="control-group">
                                            <label class="control-label" >电影影评</label>
                                            <div class="controls">
                                                <div id="startone"  class="block clearfix" >
                                                    <div class="star_score"></div>
                                                    <p style="float:left;">您的评分：<span class="fenshu" id="fenshuYp"></span> 分</p>
                                                    <div class="attitude"></div>
                                                </div>
                                            </div> <!-- /controls -->
                                        </div> <!-- /control-group -->


                                        <br />


                                        <div class="form-actions">
                                            <button type="button" class="btn btn-primary" onclick="saveMovie()">Save</button>
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

<%@ include file="/WEB-INF/common/pagination_footer.jsp"%>
</body>
</html>
