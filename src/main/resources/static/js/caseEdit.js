//参数填写的折叠效果
$('.paramcom-interrun').on('click','.icon-arrow',function(){
	$(this).toggleClass('active');
	if($(this).hasClass('active')){
		$(this).parent().siblings().addClass('active');
	}else{
		$(this).parent().siblings().removeClass('active');
	}
});
//设置环境下拉框
$('.iptenvir-interrun').click(function(){
	$(this).toggleClass('active');
	if($(this).hasClass('active')){
		$(this).siblings().addClass('active');
	}else{
		$(this).siblings().removeClass('active');
	}
});
$(document).click(function(e){
	if(!$(e.target).closest('.iptenvir-interrun').length){
		$('.envirlist-interrun').removeClass('active');
	}
});
//添加环境列表
$('body').last().on('click','.toplist-layer .icon-add',function(){
	$('.envirlist-layer ul').last().prepend('<li>新环境<i class="icon icon-delete"></i></li>')
});
//删除环境列表
$('body').last().on('click','.envirlist-layer .icon-delete',function(){
	$(this).parent().remove();
})
//高级参数设置展示更多
$('body').on('click','.morelist-layer',function(){
	$(this).addClass('active').siblings().addClass('active');
})
//高级参数设置-添加方法的整列
$('body').on('click','.paramslist-layer ul li',function(){
	$(this).addClass('active').siblings().removeClass('active');
	var text = $('.pcAlert').last().find('.paramslist-layer ul li.active span');
	var htmlArr = [];
	$.each(text,function(i,v){
		htmlArr.push($(v).html());
	});
	var index = $(this).parents('.paramslist-layer').index();
	var length = $(this).parents('.paramslist-layer').siblings().length;
	var length2 = $('.pcAlert').last().find('.paramsdel-layer .paramslist-layer').length;
	$('.psmodexp-layer').last().html('{{ '+htmlArr.join(' | ')+' }}');
	if(index != length )return;
	if(index == 1 && length ==1 && length2 != 2 && $(this).parents('.paramscom-layer').length)return;
	$('.paramsdel-layer').last().append($('.paramslist-layer').first().clone());
})

$(function(){
	//环境设置弹框
	$('.envirlist-interrun').on('click','#btnEnvirSet',function(){
		var _this = this;
		var dialog = jqueryAlert({
		    'style'   : 'pc',
		    'title'   : '环境设置',
		    'content' :  $('#envirSetLayer'),
		    'modal'   : true,
		    'contentTextAlign' : 'left',
		    'width'   : '800px',
		    'animateType' : 'linear',
		    'buttons' :{
		    }
		})
	});
	//高级参数设置弹框
	$('.paramline-interrun').on('click','.edit-interrun',function(){
		var _this = this;
		var dialog = jqueryAlert({
		    'style'   : 'pc',
		    'title'   : '高级参数设置',
		    'content' :  $('#paramSetLayer'),
		    'modal'   : true,
		    'contentTextAlign' : 'left',
		    'width'   : '1050px',
		    'animateType' : 'linear',
		    'buttons' :{
		    	'取消' : function(){
		            dialog.close(); 
		        },
		        '插入':function(){	
		        	var rule = $('.psmodexp-layer').last().html();
		        	var value = $(_this).siblings('input').val();
		        	$(_this).siblings('input').val(rule+value);
		        	dialog.close(); 
		        }
		    }
		})
	});
	$("#btnUApi").click(function(){
		$.ajax({
			url:lemon.config.global.rootUrl+"/suite/updateCase",
			data:$("[name='caseForm']").serialize(),
			type:'post',
			dataType:'json',
			success:function(ret){
				if(ret!=null){
					alert(ret.message);
					if(ret.isSuccess=="1"){
						window.parent.location.reload();
					}
				}
			}
		});
	});
	$('#addAssParams').validate({
	    onFocus: function() {
	      this.parent().addClass('active');
	      return false;
	    },
	    onBlur: function() {
	      var $parent = this.parent();
	      var _status = parseInt(this.attr('data-status'));
	      $parent.removeClass('active');
	      if (!_status) {
	        $parent.addClass('error');
	      }
	      return false;
	    }
  	});
	
	$(".btn-send").click(function(){
		$.ajax({
			url:lemon.config.global.rootUrl+"/api/runOnCaseEditPage",
			data:$("[name='caseForm']").serialize(),
			type:'post',
			dataType:'json',
			success:function(ret){
				if(ret.isSuccess=="1"){
					$("[name='responseHeader']").html("<pre>"+ret.data.response.responseHeader+"</pre>");
					$("[name='responseData']").html("<pre>"+ret.data.response.responseBody+"</pre>");
				}else{
					alert(ret.message);
				}
			}
		});
	});
	
	$(".restit-interrun").find(".titnav-interrun").click(function(){
		var index = $(this).index();
		if(index==0){
			//显示
			$(".reslist-interrun").show();
			//隐藏
			$(".testlist-interrun").hide();
		}else if(index==1){
			//显示
			$(".testlist-interrun").show();
			//隐藏
			$(".reslist-interrun").hide();
			//找到表中配置的断言规则,用于数据回显
			var url = lemon.config.global.rootUrl+"/suite/findCaseTestRule";
			var caseId = $("[name='caseId']").val();
			$.post(url,{"caseId":caseId},function(ret){
				if(ret.isSuccess=="1"){
					if(ret.data!==null){
						//数据回填到页面
						$("[name='caseTestRule.expression']").val(ret.data.expression);
						$("[name='caseTestRule.expected']").val(ret.data.expected);
					}
				}else{
					alert(ret.message);
				}
			},'json');
		}
	});
});