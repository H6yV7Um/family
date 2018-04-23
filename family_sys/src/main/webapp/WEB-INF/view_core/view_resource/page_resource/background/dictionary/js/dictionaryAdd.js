/**
 *  yangxuenan  
 *  数据字典添加修改
 */
$(function(){
	initTypeSelect();
	 $("#dictionarySubmit").focus();
	 var validate = $("#dictionaryAddForm").validate({
	        debug: true, //调试模式取消submit的默认提交功能
	        focusInvalid: false, //当为false时，验证无效时，没有焦点响应
	        onkeyup: false,
	        submitHandler: function(form){   //表单提交句柄,为一回调函数，带一个参数：form
	        	dictionarySubmit.call(this)
	        },

	        rules:{
	            id:{
	                required:true,

	            },
	            pid:{
	                required:true,
	            },
	            type:{
	                required:true,
	            },
	            name:{
	                required:true,
	               
	            },
	            value:{
	                required:true,
	                
	            },
	            flag:{
	            	required:true,
	            },
	            remarks:{
	            	required:true,
	            }
	        },
	        messages:{
	            id:{
	                required:"必填",
	                remote:"当前id已存在,不能使用,请更换"
	            },
	            pid:{
	                required:"必填",
	                remote:"当前pid已存在,不能使用,请更换"
	            },
	            type:{
	                required:"必填",
	            },
	            name:{
	                required:"必填",
	                
	            },
	            value:{
	                required: "不能为空",
	                
	            },
	            flag:{
	            	required: "不能为空",
	            }
	            
	        },
	        errorPlacement: function(error, element) {
	            error.appendTo( element.parent("div").parent("div").next("div").find("p") );
	        },
	        invalidHandler: function(errorMap,errorList){       // 如果表单验证不通过，将会触发这个函数
	           
	        },
	        //invalidHandler  表单提交时  错误验证回调 showErrors  每一次错误验证 回调
	        showErrors:function(errorMap,errorList) {   //每一次错误验证
	          
	            this.defaultShowErrors();
	        }
	    });
	});

function initTypeSelect(){
	$.ajax({
		
		url : family_ns.contextPath + "/dictionary/adminLog/selectAllDictionaryType",
		success : function(data){
			
			var list = data.result;
			for(var i = 0;i < list.length ; i++){
				$('#selector1').append('<option value="'+list[i].type+'">'+list[i].type+'</option>');
				
			}
		}
	})
}

function dictionarySubmit(){
	var url = family_ns.contextPath + "/dictionary/adminLog/dictionaryAdd";
	var data = {};
	
	var dictionaryId = $('#id').val();
	if (dictionaryId && "" != dictionaryId) {
		url = family_ns.contextPath + "/dictionary/adminLog/dictionaryUpdate";
	}
	//增加or修改操作
	data = family_ns.serializeObject($("#dictionaryAddForm"));

	family_ns.submitAjax({
		type : 'POST',
		url : url,
		data : data,
		dataType : 'json',
		success : function(result) {
			//解锁提交
			window.location.href=family_ns.contextPath +"/view/background/dictionary"
			family_ns.operatorAlertSuccess(family_ns.sucMessage);
			
		}
		
	});
}