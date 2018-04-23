<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ attribute name="content" type="java.lang.String" required="true" description="消息内容"%>
<%@ attribute name="type" type="java.lang.String" description="消息类型：info、success、warning、error、loading"%>
<!-- <script type="text/javascript">top.$.jBox.closeTip();</script>-->
<!--必须添加c标签库,不添加 因嵌套  不会报错 直接跳过-->
<c:if test="${not empty content}">
	<!-- 
	<c:if test="${not empty type}"><c:set var="ctype" value="${type}"/></c:if><c:if test="${empty type}"><c:set var="ctype" value="${fn:indexOf(content,'失败') eq -1 ? 'success' : 'error'}"/></c:if>
	<div id="messageBox" class="alert alert-${ctype} hide"><button data-dismiss="alert" class="close">×</button>${content}</div> 
	<script type="text/javascript">if(!top.$.jBox.tip.mess){top.$.jBox.tip.mess=1;top.$.jBox.tip("${content}","${ctype}",{persistent:true,opacity:0});$("#messageBox").show();}</script>
	 -->
	 
	<c:if test="${not empty type}"><c:set var="ctype" value="${type}"/></c:if><c:if test="${empty type}"><c:set var="ctype" value="${fn:indexOf(content,'失败') eq -1 ? 'success' : 'error'}"/></c:if>
	<script type="text/javascript">
	    $(document).ready(function() {
	    	var i = -1;
	        var toastCount = 0;
	        var $toastlast;
	            var shortCutFunction = "${ctype}";
	            var msg = "${content}";
	            var title = '';
	            var toastIndex = toastCount++;
	            
	            toastr.options = {
				  "closeButton": false,
				  "debug": false,
				  "progressBar": true,
				  "positionClass": "toast-top-center",
				  "onclick": null,
				  "showDuration": "400",
				  "hideDuration": "1000",
				  "timeOut": "1500",
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "fadeIn",
				  "hideMethod": "fadeOut"
				}
	            var $toast = toastr[shortCutFunction](msg, title); // Wire up an event handler to a button in the toast, if it exists
	            $toastlast = $toast;
	            if ($toast.find('#okBtn').length) {
	                $toast.delegate('#okBtn', 'click', function () {
	                    alert('you clicked me. i was toast #' + toastIndex + '. goodbye!');
	                    $toast.remove();
	                });
	            }
	            if ($toast.find('#surpriseBtn').length) {
	                $toast.delegate('#surpriseBtn', 'click', function () {
	                    alert('Surprise! you clicked me. i was toast #' + toastIndex + '. You could perform an action here.');
	                });
	            }
	        
	        function getLastToast(){
	            return $toastlast;
	        }
	        $('#clearlasttoast').click(function () {
	            toastr.clear(getLastToast());
	        });
	        $('#cleartoasts').click(function () {
	            toastr.clear();
	        });
	    })
	</script>
</c:if>