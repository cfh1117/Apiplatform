<html ang="zh-CN">
<head>
<#assign contextPath="${request.contextPath}"/>
</head>
<body>
<input name="apiId" value="${apiId}" type="hidden">

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>接口预览</title>
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta http-equiv="Cache-Control" content="no-transform">
    <link rel="stylesheet" href="${contextPath}/css/index.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/pagination.css">
    <link rel="stylesheet" type="text/css" href="${contextPath}/css/alert.css">


        	<div class="right-interlist right-interpre">
        		<div class="nav-interpre">
	        		<ul>
						<li><a href="${contextPath}/api/toApiView?apiId=${apiId}">预览</a></li>
						<li class="active"><a href="${contextPath}/api/toApiEdit?apiId=${apiId}">编辑</a></li>
						<li><a href="${contextPath}/api/toApiRun?apiId=${apiId}">运行</a></li>
					</ul>
				</div>
        		<form name="apiEditForm">
        		<input name="id" value="${apiId}" type="hidden">
        		<div class="del-interpre del-interedit">
 					<div class="basicinfo-interpre basicset-interedit">
						<div class="comtit-interpre notop">基本设置</div>
						<div class="setlist-interedit">
							<div class="line-interedit line-com">
								<label><span>*</span>接口名称：</label>
								<div class="ipt-interedit">
									<input name="name" value="${apiEditVO.name}" placeholder="接口名称" type="text">
								</div>
							</div>
							<div class="line-interedit line-com">
								<label><span>*</span>选择分类：</label>
								<div class="ipt-interedit">
									<select name="apiClassificationId" id="">
										<#if apiClassifications?? && (apiClassifications?size>0)>
										<#list apiClassifications as apiClassification>
											<option value="${apiClassification.id}" <#if apiClassification.id=='${apiEditVO.apiClassificationId}'>selected="selected"</#if>>${apiClassification.name}</option>
										</#list>
										</#if>
									</select>
								</div>
							</div>
							<div class="line-interedit line-com">
								<label><span>*</span>请求方法<i class="icon-doubt"></i>：</label>
								<div class="ipt-interedit">
									<select name="method" id="" class="reqtype-interedit" style="width:15%;margin-right:0;">
										<option value="get" <#if apiEditVO.method=='get'>selected="selected"</#if>>GET</option>
										<option value="post" <#if apiEditVO.method=='post'>selected="selected"</#if>>POST</option>
										<option value="put" <#if apiEditVO.method=='put'>selected="selected"</#if>>PUT</option>
										<option value="delete" <#if apiEditVO.method=='delete'>selected="selected"</#if>>DELETE</option>
										<option value="head" <#if apiEditVO.method=='head'>selected="selected"</#if>>HEAD</option>
										<option value="option" <#if apiEditVO.method=='option'>selected="selected"</#if>>OPTION</option>
										<option value="patch" <#if apiEditVO.method=='patch'>selected="selected"</#if>>PATCH</option>
									</select>
								</div>
							</div>
							<div class="line-interedit line-com">
								<label><span>*</span>接口地址<i class="icon-doubt"></i>：</label>
								<div class="ipt-interedit">
									<input placeholder="/path" name="url" value="${apiEditVO.url}" type="text">
								</div>
							</div>
						</div>
 					</div>
 					<div class="basicinfo-interpre reqparamsset-interedit">
 						<div class="comtit-interpre">请求参数设置</div>
 						<div class="reqplist-interedit">
							<div class="reqpnav-interedit comnav-interedit">
								<ul class="clearfix inline-block">
									<li class="active"><a href="javascript:void(0);">Query</a></li>
									<li><a href="javascript:void(0);">Body</a></li>
									<li class="header"><a href="javascript:void(0);">Headers</a></li>
								</ul>
							</div>
							<div class="reqpdel-interedit comdel-interedit clearfix">
								<div class="reqpcom-interredit active" id="reqpQuery">
									<div class="reqpline-interedit linebox-interedit">
										<a href="javascript:void(0)" class="btn-com">添加Query参数</a>
										<#if (apiEditVO.queryParams??) && (apiEditVO.queryParams?size>0)>
										<#list apiEditVO.queryParams as queryParam>
										<div class="line-interedit line-com">
											<input name="queryParams[${queryParam_index}].id" value="${queryParam.id}" type="hidden">
											<input name="queryParams[${queryParam_index}].apiId" value="${queryParam.apiId}" type="hidden">
											<input name="queryParams[${queryParam_index}].type" value="${queryParam.type}" type="hidden">
											<input placeholder="name" name="queryParams[${queryParam_index}].name" value="${queryParam.name}" style="width:18%" type="text">
											<select name="queryParams[${queryParam_index}].paramType" id="" style="width:12%">
												<option value="string">string</option>
												<option value="int">int</option>
											</select>
											<textarea name="queryParams[${queryParam_index}].exampleData" id="" value="" placeholder="参数示例" style="width:20%">${queryParam.exampleData}</textarea>
											<textarea name="queryParams[${queryParam_index}].description" id="" value="" placeholder="备注" style="width:25%">${queryParam.description}</textarea>
											<i class="icon icon-delete f-l"></i>
										</div>
										</#list>
										</#if>
									</div>
								</div>
								<div class="reqpcom-interredit" id="reqpBody">
									<div class="radiobox-interedit">
										<#if (apiEditVO.bodyRawParams??) && (apiEditVO.bodyRawParams?size>0)>
											<input value="form" type="radio" name="pt">form
											<input value="raw" type="radio"  checked="" name="pt">raw
										<#else>
											<input value="form" type="radio"  checked="" name="pt">form
											<input value="raw" type="radio"  name="pt">raw
										</#if>
									</div>
									<div class="reqpline-interedit linebox-interedit">
										<a href="javascript:void(0);" class="btn-com" <#if (apiEditVO.bodyRawParams??) && (apiEditVO.bodyRawParams?size>0)>style="display:none"</#if>>添加body参数</a>
										<#if (apiEditVO.bodyParams??) && (apiEditVO.bodyParams?size>0)>
										<#list apiEditVO.bodyParams as bodyParam>
										<div class="line-interedit line-com">
											<input name="bodyParams[${bodyParam_index}].id" value="${bodyParam.id}" type="hidden">
											<input name="bodyParams[${bodyParam_index}].apiId" value="${bodyParam.apiId}" type="hidden">
											<input name="bodyParams[${bodyParam_index}].type" value="${bodyParam.type}" type="hidden">
											<input placeholder="name" name="bodyParams[${bodyParam_index}].name" value="${bodyParam.name}" style="width:18%" type="text">
											<select name="bodyParams[${bodyParam_index}].paramType" id="" style="width:12%">
												<option value="string">string</option>
												<option value="int">int</option>
											</select>
											<textarea name="bodyParams[${bodyParam_index}].exampleData" id="" value="" placeholder="参数示例" style="width:20%">${bodyParam.exampleData}</textarea>
											<textarea name="bodyParams[${bodyParam_index}].description" id="" value="" placeholder="备注" style="width:25%">${bodyParam.description}</textarea>
											<i class="icon icon-delete f-l"></i>
										</div>
										</#list>
										</#if>
									</div>
									<#if (apiEditVO.bodyRawParams??) && (apiEditVO.bodyRawParams?size>0)>
									<#list apiEditVO.bodyRawParams as bodyRawParam>
									<div class="basicinfo-interpre reqparamsset-interedit">
				 						<div class="reqplist-interedit">
				 							<textarea name="bodyRawParams[0].exampleData" id="" class="remark-interedit">${bodyRawParam.exampleData!'{}'}</textarea>
				 							<input name="bodyRawParams[0].type" value="4" type="hidden"/>
				 						</div>
 									</div>
 									</#list>
									</#if>
								</div>
								<div class="reqpcom-interredit" id="reqpHeaders">
									<div class="reqpline-interedit linebox-interedit">
										<a href="javascript:void(0)" class="btn-com">添加Header参数</a>
										<#if (apiEditVO.headerParams??) && (apiEditVO.headerParams?size>0)>
										<#list apiEditVO.headerParams as headerParam>
										<div class="line-interedit line-com">
											<input name="headerParams[${headerParam_index}].id" value="${headerParam.id}" type="hidden">
											<input name="headerParams[${headerParam_index}].apiId" value="${headerParam.apiId}" type="hidden">
											<input name="headerParams[${headerParam_index}].type" value="${headerParam.type}" type="hidden">
											<input placeholder="参数名称" name="headerParams[${headerParam_index}].name" value="${headerParam.name}" style="width:20%" type="text">
											<select name="headerParams[${headerParam_index}].paramType" id="" style="width:12%">
												<option value="string">string</option>
												<option value="int">int</option>
											</select>
											<textarea name="headerParams[${headerParam_index}].exampleData" id="" value="" placeholder="参数示例" style="width:20%">${headerParam.exampleData}</textarea>
											<textarea name="headerParams[${headerParam_index}].description" id="" value="" placeholder="备注" style="width:29%">${headerParam.description}</textarea>
											<i class="icon icon-delete f-l"></i>
										</div>
										</#list>
										</#if>
									</div>
								</div>
							</div>
 						</div>
 					</div>
 					<div class="basicinfo-interpre reqparamsset-interedit">
 						<div class="comtit-interpre">备 注</div>
 						<div class="reqplist-interedit">
 							<textarea name="description" id="" class="remark-interedit">${apiEditVO.description!''}</textarea>
 						</div>
 					</div>
        		</div>
        		<div class="comfirm-interedit">
					<a href="javascript:void(0)" class="btn-com">保存</a>
        		</div>
        		</form>
        	</div>
     <script type="text/javascript" src="${contextPath}/js/jquery-1.11.3.js"></script>  
     <script type="text/javascript" src="${contextPath}/js/jquery.pagination.js"></script>
     <script type="text/javascript" src="${contextPath}/js/alert.js"></script>
     <script type="text/javascript" src="${contextPath}/js/base.js"></script>
     <script type="text/javascript" src="${contextPath}/js/apiEdit.js"></script>
     <script type="text/javascript" src="${contextPath}/js/common.js"></script>

</body></html>