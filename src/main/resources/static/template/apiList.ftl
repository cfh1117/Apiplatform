<html ang="zh-CN">
<#assign contextPath="${request.contextPath}"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<meta name="description" content="">
<meta name="keywords" content="">
<meta http-equiv="Cache-Control" content="no-transform">
<link rel="stylesheet" type="text/css" href="${contextPath}/css/index.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/css/pagination.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/css/alert.css">
</head>
<body>
	<input name="projectId" value="${projectId!''}" type="hidden">
	<input name="apiClassificationId" value="${apiClassificationId!''}" type="hidden">
	<div class="right-interlist">
		<div class="desc-interlist">
			<div class="desctop-interlist">
				<div class="desctit-interlist f-l">
					全部接口共<span>(4)</span>个
				</div>
				<a href="javascript:;" class="btn-addinter btn-com f-r">添加接口</a>
			</div>
			<div class="remark-interlist">
				<input class="input-com" value="公共分类" type="text">
				<div class="btnbox-interlist">
					<a href="javascript:;" class="btn-edit"></a> <a href="javascript:;"
						class="btn-sure"></a> <a href="javascript:;" class="btn-cancel"></a>
				</div>
			</div>
		</div>
		<div class="data-intrelist">
			<div class="datatit-interlist" id="interDataTit">
				<ul class="clearfix">
					<li>接口名称</li>
					<li>接口路径</li>
					<li>接口分类</li>
					<li>状态</li>
				</ul>
			</div>
			<div class="datatxt-interlist" id="interData">
				<ul>
					
				</ul>
			</div>
		</div>
		<div class="box-pagination">
			<div class="inline-block">
				<div class="pagination m-style" id="pagination">
					<span class="active">1</span><a href="javascript:;" data-page="2">2</a><a
						href="javascript:;" class="next">&gt;</a><input class="jump-ipt"
						type="text"><a href="javascript:;" class="jump-btn">跳转</a>
				</div>
			</div>
		</div>
	</div>
	<!-- 添加接口弹框 -->
	<form id="addForm">
		<div class="dialog-addinter">
			<div class="line-addinter form_control clearfix">
				<label>接口分类：</label> <select name="apiClassificationId">

				</select>
			</div>
			<div class="line-addinter form_control clearfix">
				<label><span>*</span>接口名称：</label> <input placeholder="接口名称"
					class="required" data-valid="isNonEmpty" data-error="内容不能为空"
					name="name" type="text"> <span class="valid_message"></span>
			</div>
			<div class="line-addinter form_control clearfix">
				<label><span>*</span>接口路径：</label> <select
					class="seltype-addinter required" name="method"
					data-valid="isNonEmpty" data-error="内容不能为空">
					<option value="get">GET</option>
					<option value="post">POST</option>
					<option value="put">PUT</option>
					<option value="delete">DELETE</option>
					<option value="head">HEAD</option>
					<option value="option">OPTION</option>
					<option value="patch">PATCH</option>
				</select> <span class="valid_message"></span> <input
					class="path-addinter required" placeholder="/path"
					name="url" data-valid="isNonEmpty" data-error="内容不能为空"
					type="text"> <span class="valid_message"></span>
			</div>
			<div class="remark-addinter">
				<span>注：</span>详细的接口数据可以在编辑页面中添加
			</div>
		</div>
	</form>
	<script type="text/javascript" src="${contextPath}/js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="${contextPath}/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="${contextPath}/js/alert.js"></script>
	<script type="text/javascript" src="${contextPath}/js/base.js"></script>
	<script type="text/javascript" src="${contextPath}/js/jquery-validate.js"></script>
	<script type="text/javascript" src="${contextPath}/js/common.js"></script>
	<script type="text/javascript" src="${contextPath}/js/apiList.js"></script>


</body>
</html>