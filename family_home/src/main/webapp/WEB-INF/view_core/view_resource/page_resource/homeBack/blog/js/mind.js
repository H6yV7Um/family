/**
 * Created by lcy on 17/10/4.
 */
/* 提交json */
var mindParam = {};

angular.module('kityminderDemo', ['kityminderEditor'])
    .config(function (configProvider) {
        configProvider.set('imageUpload', family_ns.contextPath + '/blog/updateMindPicture');
    })
    .controller('MainController', function($scope) {
        $scope.initEditor = function(editor, minder) {
            window.editor = editor;
            window.minder = minder;
        };
    });

$(function(){
    family_ns.queryAjax({
        type: "post",
        url: family_ns.contextPath + "/blog/queryOneMinderInfo/" + mindParam.businessId,
        data: {},
        success: function (result) {
            if (result.success) {
                var jsonObj = JSON.parse(result.result.bdescmd);
                if(result.result.bdescmd)
                    editor.minder.importJson(jsonObj);
            }
        }
    });
    if(1 == mindParam.owns || "1" == mindParam.owns){
        $('#mindBtn').show();
    }
});



function sub(){
    var editorJson = editor.minder.exportJson();
    var editorJsonString = JSON.stringify(editorJson);
    var formData = {};
    formData.businessId = mindParam.businessId;
    formData.bdesc = editorJsonString;
    var url = family_ns.contextPath + "/blog/blogAddMinder";
    family_ns.operateConfirmByAjax(function(){
        family_ns.submitAjax({
            type:"post",
            url:url,
            data: formData,
            success:function(result){
                if(result.success)
                    family_ns.operatorAlertSuccess(family_ns.getSucMessage.call(this,result.message));
                else
                    family_ns.operatorAlertError(family_ns.getErrMessage.call(this,result.message));
            }
        });
    });
}