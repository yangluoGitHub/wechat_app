var inited = false;
var selectDimDivJObj;
var userAppSelDivJObj;
var url = new RegExp("[a-zA-z]+://[^\s]*");

function completeCon(obj){
	var menuKey = $.trim(obj.value);
	var wechatId = $("#accIdField").val();
	var cto = $("#"+obj.id);
	//alert(obj.id);
	if(menuKey.length < 2){
		return false;
	}
	DWREngine.setAsync(false);
	weMenuService.qryWechatFuc(menuKey, wechatId, function(data){
		var jsonArray = JSON.parse(data);
		cto.autocomplete(jsonArray,{
			minChars:1,
			max:10,
			cacheLength:1,
			matchContains:true,
			formatItem:function(jsonArray,i,max){
				//return jsonArray.value+"|"+jsonArray.label;
				return jsonArray.label;
			},
			formatResult:function(jsonArray){
//				return jsonArray.label+"|"+jsonArray.value;
				return jsonArray.label;
			}
		});
	});
	DWREngine.setAsync(true);
	
}
function blurFunc(obj){
	var menuKey = $.trim(obj.value);
	var wechatId = $("#accIdField").val();
	if (menuKey.length == 0) return;
	if (!url.test(obj.value)){
		DWREngine.setAsync(false);
		weMenuService.isExitFunctionName(menuKey, wechatId, function(data){
			//alert(data);
			if (data == false) {
				obj.value='';
			}
		});
		DWREngine.setAsync(true);
	}
}

var editMenuItem = function(el) {
	var isRoot = arguments.length > 1 && arguments[1] === true;
	var wrapper = $(el).parent().parent();
	var state = wrapper.attr('state');
	if (!state || state == 'display') {
		$('span', wrapper).hide();
		$('input', wrapper).each(function(idx, el) {
			$(this).show();
			$(this).val($('span', $(this).parent()).attr('title'));
//			$(this).val($('span', $(this).parent()).html());
		});
		wrapper.attr('state', 'edit');
	} else {
		var hasErr = false;
		$('input', wrapper).each(
				function() {
					if ($(this).parent().css('display') == 'none')
						return;
					var val = $.trim($(this).val());
					var isNameField = $(this).attr('name') == 'menuName_i';
					var hasChild = false;
					var next = null;
					if (isRoot) {
						while (!hasChild && (next = wrapper.next()).length) {
							if (next.attr('name') == 'rootMenu')
								break;
							hasChild = true;
						}
						if ((hasChild && ((isNameField && val.length > 5) || (isNameField && !val)))
								|| (!hasChild && (!val || (isNameField && val.length > 5) || (!isNameField && val.length > 66)))) {
							$(this).css('border', '2px solid red');
							hasErr = true;
							return false;
						}
					} else {
						if (!val || (isNameField && val.length > 12) || (!isNameField && val.length > 66)) {
							$(this).css('border', '2px solid red');
							hasErr = true;
							return false;
						}
					}
					$(this).css('border', '1px solid #e3e3e3');
				});
		if (hasErr)
			return false;
		$('input', wrapper).hide();
		$('span', wrapper).each(function(idx, el) {
			$(this).show();
			var val = $('input', $(this).parent()).val();
			
//			var arry = val.split("|");
//			//处理
//		//	alert(arry[0]);
//			if(arry.length > 1){
//				$(this).attr('title', arry[1]).html(arry[0] > 10 ? arry[0].substring(0, 10) + '...' : arry[0]);
//			}else{
				$(this).attr('title', val).html(val.length > 10 ? val.substring(0, 10) + '...' : val);
//			}
		});
		if (!isRoot) {
			if ($('dd', wrapper.parent()).length >= 0)
				$('span[name=menuKey]', $('dt', wrapper.parent())).parent().hide();
		}
		wrapper.attr('state', 'display');
		preview();
	}
};

var delMenuItem = function(el) {
	XIDialog.confirm({
		title : '提示',
		msg : '确认删除？',
		confirm : function(userConfirm) {
			if (userConfirm) {
				var ddEl = $(el).parent().parent();
				var dtEl = $('dt', ddEl.parent());
				var dlEl = ddEl.parent();
				ddEl.remove();
				if ($('dd', dlEl).length == 0) {
					$('span[name=menuKey]', dtEl).parent().show();
					$('.CM-cta01', dtEl).trigger('click');
				}
				preview();
			}
		}
	});
};

var delRootMenu = function(el) {
	XIDialog.confirm({
		title : '提示',
		msg : '确认删除？这将一并删除所有子菜单',
		confirm : function(userConfirm) {
			if (userConfirm) {
				el = $(el).parent().parent();
				var els = [];
				els.push(el);
				while ((tr = el.next()).length) {
					if (tr.attr('name') == 'rootMenu')
						break;
					els.push(tr);
					el = el.next();
				}
				for ( var i = 0; i < els.length; i++)
					els[i].remove();
				preview();
			}
		}
	});
};

var addMenuItem = function(el) {
	var tr = $(el).parent().parent();
	var target = null, insertBefore = true, count = 0;
	while ((target = tr.next()).length) {
		if (target.attr('name') == 'rootMenu')
			break;
		tr = tr.next();
		count++;
	}
	if (!target || !target.length) {
		target = tr.parent();
		insertBefore = false;
	}
	if (count == 5) {
		XIDialog.alert('子菜单数量不能超过5个');
		return false;
	}
	var item = $('<tr>').attr('name', 'menuItem');
	if (insertBefore)
		target.before(item);
	else
		target.append(item);
	item
			.html('<td width="111" height="30" align="center" valign="middle" class="CMOP-cttd01 CM-cl03"><div><span name="menuName"></span><input name="menuName_i" value="" style="display:none;width:100px;border:1px solid #f8f8f8;"/></div></td>'
					+ '<td width="161" height="30" align="center" valign="middle" class="CMOP-cttd01 CM-cl03"><div><span name="menuKey"></span><input id="'+count+'" name="menuKey_i" value="" onkeyup="completeCon(this)" onblur ="blurFunc(this)" style="display:none;width:150px;border:1px solid #f8f8f8;"/></div></td>'
					+ '<td width="124" height="30" align="right" valign="middle" class="CMOP-cttd03"><a href="#c4" class="CM-cta05" style="margin-right:15px"></a> <a href="#c4" class="CM-cta04"></a></td>');
	$('.CM-cta04', item).click(function() {
		if (window.blurTimeout)
			clearTimeout(window.blurTimeout);
		editMenuItem(this);
	});
	$('.CM-cta04', item).trigger('click');
	$('input', item).keyup(function() {
		if ($.trim(this.value))
			this.style.border = '1px solid #e3e3e3';
	}).blur(function(e) {
		var self = this;
		window.blurTimeout = setTimeout(function() {
			editMenuItem($('.CM-cta04', $(self).parent().parent().parent()).get(0));
		}, 100);
	}).focus(function(e) {
		if (window.blurTimeout)
			clearTimeout(window.blurTimeout);
	});
	$('.CM-cta05', item).click(function() {
		delMenuItem(this);
	});
	preview();
};

$(document)
		.ready(
				function() {
					$('#addRootMenuBtn')
							.click(
									function() {
										var countNum= 10+$('tr[name=rootMenu]', $('#jsweb_c4')).length; 
										if ($('tr[name=rootMenu]', $('#jsweb_c4')).length == 3) {
											XIDialog.alert('菜单数量不能超过3个！');
											return false;
										}
//										alert(countNum);
										var dl = $('<tr></tr>').attr('name', 'rootMenu');
										dl
												.html('<td width="111" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01"><div><span name="menuName"></span><input name="menuName_i" value="" style="display:none;width:100px;"/></div></td>'
														+ '<td width="161" height="30" align="center" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd01"><div><span name="menuKey"></span><input id="'+countNum+'" name="menuKey_i" value="" onkeyup="completeCon(this)" onblur ="blurFunc(this)" style="display:none;width:150px;"/></div></td>'
														+ '<td width="124" height="30" align="right" valign="middle" bgcolor="#f8f8f8" class="CMOP-cttd03"> <a href="#c4" class="CM-cta03" style="margin-right:15px"></a> <a href="#c4" class="CM-cta02"></a> <a href="#c4" class="CM-cta01"></a></td>');
										$('#menuContainer').append(dl);
										
										$('.CM-cta01', dl).click(function(e) {
											if (window.blurTimeout)
												clearTimeout(window.blurTimeout);
											editMenuItem(this, true);
										}).trigger('click');
										$('.CM-cta02', dl).click(function() {
											addMenuItem(this);
										});
										$('.CM-cta03', dl).click(function() {
											delRootMenu(this);
										});
										$('input', dl).keyup(function() {
											if ($.trim(this.value))
												this.style.border = '1px solid #e3e3e3';
										}).blur(function(e) {
											var btn = $('.CM-cta01', $(this).parent().parent().parent()).get(0);
											window.blurTimeout = setTimeout(function() {
												editMenuItem(btn, true);
											}, 100);
										}).focus(function(e) {
											if (window.blurTimeout)
												clearTimeout(window.blurTimeout);
										});
									});
					$('.CM-cta04').click(function() {
						if (window.blurTimeout)
							clearTimeout(window.blurTimeout);
						editMenuItem(this);
					});
					$('.CM-cta05').click(function() {
						delMenuItem(this);
					});
					$('.CM-cta01').click(function() {
						if (window.blurTimeout)
							clearTimeout(window.blurTimeout);
						editMenuItem(this, true);
					});
					$('.CM-cta02').click(function() {
						addMenuItem(this);
					});
					$('.CM-cta03').click(function() {
						delRootMenu(this);
					});
					$('input[name$=_i]').keyup(function() {
						if ($.trim(this.value))
							this.style.border = '1px solid #e3e3e3';
					}).blur(function() {
						var self = this;
						var funcName = $.trim(self.value);
						var wechatId = $("#accIdField").val();
						var isMenuKey = $(self).attr('name') == 'menuKey_i';
						if(isMenuKey){//验证业务功能是否存在
							if (funcName.length == 0) return;
							if (!url.test(self.value)){
								DWREngine.setAsync(false);
								weMenuService.isExitFunctionName(funcName, wechatId, function(data){
									//alert(data);
									if (data == false ) {
										self.value='';
									}
								});
								DWREngine.setAsync(true);
							}
						}
						
						window.blurTimeout = setTimeout(function() {
							var tr = $(self).parent().parent().parent();
//							if (tr.attr('state') == 'display')
//								return false;
							var isRoot = tr.attr('name') == 'rootMenu';
							if (isRoot)
								$('.CM-cta01', tr).trigger('click');
							else
								$('.CM-cta04', tr).trigger('click');
						}, 100);
						
					}).focus(function(e) {
						if (window.blurTimeout)
							clearTimeout(window.blurTimeout);
					});
					$('#saveMenuBtn').click(function() {
						// var accIdEl = $('#accIdField');
						// if (!accIdEl.length) {
						// alert('请先使用基本设置中的URL和Token成功关联您的微信公众平台账号。');
						// return false;
						// }
						var dls = $('tr[name=rootMenu]', '#menuContainer');
						var errMsg = '';
						var data = [];
						dls.each(function(idx, dl) {
							dl = $(dl);
							if (dl.attr('state') == 'edit')
								if (editMenuItem($('.CM-cta01', dl), true) === false) {
									errMsg = '您的菜单尚未正确编辑完成，请检查。';
									return false;
								}
							var rootMenu = {};
							var type =url.test($('span[name=menuKey]', dl).attr('title'))?'view':'click';
							
							//rootMenu.type = url.test($('span[name=menuKey]', dl).attr('title'))?'view':'click';
							rootMenu.type=type;
							rootMenu.name = $('span[name=menuName]', dl).attr('title');
							if(type == 'click'){
							rootMenu.key = $('span[name=menuKey]', dl).attr('title');
							}else{
							rootMenu.url = 	$('span[name=menuKey]', dl).attr('title');
							}
							var tr = null, sub_button = [];
							while ((tr = dl.next()).length) {
								if (tr.attr('name') == 'rootMenu')
									break;
								if (tr.attr('state') == 'edit')
									if (editMenuItem($('.CM-cta04', tr)) === false) {
										errMsg = '您的菜单尚未正确编辑完成，请检查。';
										return false;
									}
								var sub = {};
								var style2=url.test($('span[name=menuKey]', tr).attr('title'))?'view':'click';
								sub.type = style2;
								sub.name = $('span[name=menuName]', tr).attr('title');
								if(style2 =="click"){
								sub.key = $('span[name=menuKey]', tr).attr('title');
								}else{
								sub.url = $('span[name=menuKey]', tr).attr('title');	
								}
								
								sub_button.push(sub);
								dl = dl.next();
							}
							if (sub_button.length) {
								delete rootMenu.key;
								delete rootMenu.type;
								delete rootMenu.url;
								rootMenu.sub_button = sub_button;
							} else {
								if (!rootMenu.key ) {
									if(!rootMenu.url){
									errMsg = '没有子节点的主菜单必须填写问题或者URL!';
									$('.CM-cta01', dl).trigger('click');
									return false;
									}
								}
								
							}
							data.push(rootMenu);
						});
						if (errMsg) {
							XIDialog.alert(errMsg);
							return false;
						}
						var postBody = data.length ? $.toJSON(data) : '';
//						alert(postBody);
//						alert(encodeURI(postBody));
//						postBody=encodeURI(encodeURI(postBody));
						postBody = encodeURI(postBody);
						$.ajax({
							url : 'weMenu.do?action=saveMenuBtn',
							type : 'POST',
							data : {
								id : $('#accIdField').val(),
								data : postBody
							},
							success : function(respTxt) {
								if (respTxt == 'noacc')
									XIDialog.alert('请先使用基本设置中的URL和Token成功关联您的微信公众平台账号。');
								else if (respTxt == 'error')
									XIDialog.alert('保存失败，请稍后再试！');
								else
									XIDialog.alert('保存成功！');
							}
						});
						return false;
					});
					$('#deployMenuBtn').click(function() {
						//var ret = window.confirm('确认发布菜单？');
						XIDialog.confirm({
							title : '提示',
							msg : '确认发布菜单？',
							confirm : function(userConfirm) {
								if (userConfirm) {
									$.ajax({
										url : 'weMenu.do?action=deployMenuBtn',
										type : 'POST',
										data : {
											id : $('#accIdField').val()
										},
										success : function(respTxt) {
											if (respTxt == 'no_data')
												XIDialog.alert('您尚未编辑菜单内容。');
											else if (respTxt == 'error')
												XIDialog.alert('发布失败，请稍后再试！');
											else
												XIDialog.alert('发布成功！');
										}
									});
								}
							}
						});
						return false;
					});
					$('span[name^=menu]', $('#menuContainer')).each(function() {
						var val = $(this).html();
						if (val.length > 10)
							$(this).html(val.substring(0, 10) + '...');
					});
					if (!window.noMenuData)
						preview();
					// $('#addRootMenuBtn').trigger('click');
					// else

					$('#remainLimit').html(defaultRemainLimit);
					$('#remainLimitDiv').show();
					currentFoucsTextareaJObj = $('#welcntextarea')[0];
					$(currentFoucsTextareaJObj).val('');
					renderBar(currentFoucsTextareaJObj, 'text');
				});

function apply(el) {
	$.ajax({
		url : 'weixin!apply.action',
		type : 'GET',
		success : function() {
			var html = '<div class="ASK-cn1">您的申请已提交审核，请耐心等待！<br/>如需咨询，请拨打客服电话：021-39518970</div>';
			$(el).parent().parent().html(html);
		}
	});
}

function preview() {
	$('#previewRootItems').html('');
	$('#previewCont').hide();
	var dls = $('tr[name=rootMenu]', '#menuContainer');
	dls.each(function(idx, dl) {
		dl = $(dl);
		var rootMenu = $('<li></li>').html('<h6>' + $('span[name=menuName]', $(this)).html() + '</h6><p></p>');
		$('#previewRootItems').append(rootMenu);
		rootMenu.click(function() {
			$('#previewSubItems').html('');
			var tr = null, subMenus = [];
			var dl0 = dl;
			while ((tr = dl0.next()).length) {
				if (tr.attr('name') == 'rootMenu')
					break;
				subMenus.push(tr);
				dl0 = dl0.next();
			}
			if (subMenus.length == 0) {
				$('#previewCont').hide();
			} else {
				subMenus = $(subMenus);
				subMenus.each(function() {
					var val = $('span[name=menuName]', $(this)).attr('title');
					var displayVal = val;
					if (displayVal.length > 5)
						displayVal = displayVal.substring(0, 5) + '..';
					var subMenu = $('<li></li>').html('<h6>' + displayVal + '</h6><p></p>').attr('title', val);
					$('#previewSubItems').append(subMenu);
				});
				var left = idx * 90 + 50 + 'px';
				var left0 = $('#previewCont').css('left');
				if (left == left0 && $('#previewCont').css('display') != 'none')
					$('#previewCont').hide();
				else {
					$('#previewCont').css('left', left);
					$('#previewCont').show();
				}
			}
		});
	});
}

function bind() {
	var accIdEl = $('#accIdField');
	// if (!accIdEl.length) {
	// alert('请先使用基本设置中的URL和Token成功关联您的微信公众平台账号。');
	// return false;
	// }
	var appIdEl = $('#appIdField');
	var appSecretEl = $('#appSecretField');
	var appIdVal = $.trim(appIdEl.val());
	var appSecretVal = $.trim(appSecretEl.val());
	if (!appIdVal || appIdVal == '请输入内容' || !appSecretVal || appSecretVal == '请输入内容') {
		XIDialog.alert('请完整填写开发者信息！');
		return false;
	}
	$.ajax({
		url : 'weixin!bind.action',
		type : 'POST',
		data : {
			accId : accIdEl.val(),
			appId : appIdVal,
			appSecret : appSecretVal
		},
		success : function(respTxt) {
			if ('noacc' == respTxt)
				XIDialog.alert('请先使用基本设置中的URL和Token成功关联您的微信公众平台账号。');
			else if ('fail' == respTxt) {
				XIDialog.alert('开发者账号绑定失败，请检查您的信息或稍候再试！');
				return false;
			} else {
				var accId = respTxt.replace(/\"/g, '');
				accIdEl.val(accId);
				XIDialog.alert('绑定开发者账号成功！');
				setTimeout(function() {
					window.location.reload();
				}, 500);
			}
		}
	});
}

//function switchTab(el) {
//	$('li', $(el).parent()).removeClass('hover');
//	$(el).addClass('hover');
//	$('div[id^=jsweb]').hide();
//	$('#jsweb_' + el.id).show();
//	var loc = window.location.href;
//	loc = loc.replace(/#c[1-4]/, '').replace(/#/, '');
//	window.location.href = loc + '#' + el.id;
//	document.body.scrollTop = 0;
//}

// //////////
var currentFoucsTextareaJObj;
var defaultRemainLimit = 600;
var currentWelType = 'text';

function loadAnswerByType(id, type, currentFoucsTextareaJObj) {
	var url;
	if (type == 'imgtxtmsg') {
		url = "img-msg!getImgmsg.action?imgmsgId=";
	} else if (type == 'imgmsg') {
		url = "imageItem.jsp?imageId=";
	} else if (type == 'musicmsg') {
		url = "audioItem.jsp?audioId=";
	} else if (type == 'videomsg') {
		url = "videoItem.jsp?videoId=";
	}
	if (currentFoucsTextareaJObj.next('iframe').length > 0) {
		currentFoucsTextareaJObj.next('iframe').remove();
	}
	var iframeJObj = $('<iframe/>').attr('src', url + id).attr('style', 'border: 0 none;height: 100%;width: 100%;');
	currentFoucsTextareaJObj.after(iframeJObj);
	currentFoucsTextareaJObj.hide();
	renderBar(currentFoucsTextareaJObj, type);
	iframeJObj.load(function() {
		iframeJObj[0].contentWindow.document.onclick = function() {
			renderBar(currentFoucsTextareaJObj, type);
		}
	});
}

function tLp6Click() {
	showUserAppSelDiv();
}

function tLp6ClickCb(btnJObj) {
	var appId = $(btnJObj).attr('appId');
	var appObj = appMap[appId];
	// $(currentFoucsTextareaJObj).parent().children('input[type=hidden]').val('service');
	loadAnswerByAppType(appId, appObj['name'], appObj['appArgs'], $(currentFoucsTextareaJObj));
}

function loadAnswerByAppType(appId, appName, appArgs, currentFoucsTextareaJObj) {
	$(currentFoucsTextareaJObj).val(appId);
	currentWelType = 'service';
	if (currentFoucsTextareaJObj.next('iframe').length > 0) {
		currentFoucsTextareaJObj.next('iframe').remove();
	}
	if (currentFoucsTextareaJObj.next('div').length > 0) {
		currentFoucsTextareaJObj.next('div').remove();
	}
	var divContentJObj = $('<div/>').attr('style',
			'overflow-y:auto;border: 0 none;font-family: tahoma,Arial,Helvetica,sans-serif;font-size: 12px;height: 58px;').attr('appName', appName);
	currentFoucsTextareaJObj.after(divContentJObj);
	var divJObj = $('<div/>').attr('style', 'border: 0 none;font-family: tahoma,Arial,Helvetica,sans-serif;font-size: 12px;height: 22px;').css(
			'padding-left', $(currentFoucsTextareaJObj).css('text-indent'));
	divJObj.html('/调用 ' + appName);
	divContentJObj.append(divJObj);
	if (appArgs) {
		for ( var i = 0; i < appArgs.length; i++) {
			var appArg = appArgs[i];
			var divJObj = $('<div/>').attr('name', 'args').attr('argsName', appArg['name']);
			var inputJObj = $('<input/>').attr('type', 'text').css('width', '180px').attr('style',
					'border-left: 0px;border-right: 0px;border-top: 0ex;border-bottom: 1px dashed #CCCCCC;width: 230px;').attr('maxLength', '20');
			if (appArg['value'] && appArg['value'].length > 0) {
				inputJObj.val(appArg['value']);
			} else if (appArg['defaultVal'] && appArg['defaultVal'].length > 0) {
				inputJObj.val(appArg['defaultVal']);
			}
			var argTitle = appArg['name'];
			var read_app = appMap[appId];
			if (read_app && read_app['appArgs']) {
				argTitle = read_app['appArgs'][i]['title'] ? read_app['appArgs'][i]['title'] : appArg['name'];
			}
			divJObj.append(argTitle + "=").append(inputJObj);
			if (appArg['optional'] && appArg['optional'] == '1') {
				divJObj.append('<font color="red">*</font>');
				divJObj.attr('optional', '1');
			}
			divContentJObj.append(divJObj);
		}
	}
	currentFoucsTextareaJObj.hide();
	// answerDivClick(currentFoucsTextareaJObj);
	renderBar(currentFoucsTextareaJObj, 'service');
	divContentJObj.bind('click', function() {
		// answerDivClick(currentFoucsTextareaJObj);
		renderBar(currentFoucsTextareaJObj, 'service');
	});
}

function tLp1Click() {
	WSC_ShowDialog("img-msg!list.action", "图文消息");
}
function tLp1ClickCb(imgId) {
	$(currentFoucsTextareaJObj).val(imgId);
	currentWelType = 'imgtxtmsg';
	loadAnswerByType(imgId, 'imgtxtmsg', $(currentFoucsTextareaJObj));
	$('#remainLimitDiv').hide();
}

function tLp2Click() {
	WSC_ShowDialog("video!list.action", "视频");
}
function tLp2ClickCb(imgId) {
	$(currentFoucsTextareaJObj).val(imgId);
	currentWelType = 'videomsg';
	loadAnswerByType(imgId, 'videomsg', $(currentFoucsTextareaJObj));
	$('#remainLimitDiv').hide();
}
function tLp3Click() {
	WSC_ShowDialog("audio!list.action", "语音");
}
function tLp3ClickCb(imgId) {
	$(currentFoucsTextareaJObj).val(imgId);
	currentWelType = 'musicmsg';
	loadAnswerByType(imgId, 'musicmsg', $(currentFoucsTextareaJObj));
	$('#remainLimitDiv').hide();
}
function tLp4Click() {
	WSC_ShowDialog("image!list.action", "图片");
}
function tLp4ClickCb(imgId) {
	$(currentFoucsTextareaJObj).val(imgId);
	currentWelType = 'imgmsg';
	loadAnswerByType(imgId, 'imgmsg', $(currentFoucsTextareaJObj));
	$('#remainLimitDiv').hide();
}
function tLp5Click(content) {
	currentWelType = 'text';
	if (content) {
		$(currentFoucsTextareaJObj).val(content);
	} else {
		$(currentFoucsTextareaJObj).val('');
	}
	checkTextLength();
	$('#remainLimitDiv').show();
	if ($(currentFoucsTextareaJObj).next('iframe').length > 0) {
		$(currentFoucsTextareaJObj).next('iframe').remove();
	}
	$(currentFoucsTextareaJObj).show();
	renderBar(currentFoucsTextareaJObj, 'text');
}

// 初始化加载欢迎语信息
function loadContent(type, content, tags, appName, appArgs) {
	if (!type || type == 'text') {
		tLp5Click(content);
	} else if (type == 'imgtxtmsg') {
		tLp1ClickCb(content);
	} else if (type == 'imgmsg') {
		tLp4ClickCb(content);
	} else if (type == 'musicmsg') {
		tLp3ClickCb(content);
	} else if (type == 'videomsg') {
		tLp2ClickCb(content);
	} else if (type == 'service') {
		loadAnswerByAppType(content, appName, appArgs, $('#welcntextarea'));
	}
}

function loadWelcomeMsg(welcomeObj) {
	$('#welIdField').val(welcomeObj['id']);
	var type = welcomeObj['feature'];
	var dimAnswer = welcomeObj['dimAnswer'];
	loadContent(type, dimAnswer['answer'], dimAnswer['tags'], dimAnswer['appName'], dimAnswer['args']);
}
// function loadWelcomeMsg(type, content) {
// if (type == 'text') {
// tLp5Click(content);
// } else if (type == 'imgmsg') {
// tLp4ClickCb(content);
// } else if (type == 'musicmsg') {
// tLp3ClickCb(content);
// } else if (type == 'imgtxtmsg') {
// tLp1ClickCb(content);
// } else if (type == 'videomsg') {
// tLp2ClickCb(content);
// }else if(type=='service'){
// textareaJObj.val(answer);
// toggleBar(barJObj,5);
// hiddenInputJObj.val('service');
// loadAnswerByAppType(answer,appName,appArgs,textareaJObj,unique);
// }
// }

function renderBar(textareaDomObj, type) {
	var textareaJObj = $(textareaDomObj);
	if (type) {
		var typeBar = $('div[class="CMOP-weltop03"]');
		if (type == 'text') {
			typeBar.find('a[class="weltop-link01"]').attr('class', 'weltop-link01Click');
			typeBar.find('a[class="weltop-link02Click"]').attr('class', 'weltop-link02');
			typeBar.find('a[class="weltop-link03Click"]').attr('class', 'weltop-link03');
			typeBar.find('a[class="weltop-link04Click"]').attr('class', 'weltop-link04');
			typeBar.find('a[class="weltop-link05Click"]').attr('class', 'weltop-link05');
			typeBar.find('a[class="weltop-link06Click"]').attr('class', 'weltop-link06');
		} else if (type == 'imgmsg') {
			typeBar.find('a[class="weltop-link01Click"]').attr('class', 'weltop-link01');
			typeBar.find('a[class="weltop-link02Click"]').attr('class', 'weltop-link02');
			typeBar.find('a[class="weltop-link03Click"]').attr('class', 'weltop-link03');
			typeBar.find('a[class="weltop-link04"]').attr('class', 'weltop-link04Click');
			typeBar.find('a[class="weltop-link05Click"]').attr('class', 'weltop-link05');
			typeBar.find('a[class="weltop-link06Click"]').attr('class', 'weltop-link06');
		} else if (type == 'musicmsg') {
			typeBar.find('a[class="weltop-link01Click"]').attr('class', 'weltop-link01');
			typeBar.find('a[class="weltop-link02Click"]').attr('class', 'weltop-link02');
			typeBar.find('a[class="weltop-link03"]').attr('class', 'weltop-link03Click');
			typeBar.find('a[class="weltop-link04Click"]').attr('class', 'weltop-link04');
			typeBar.find('a[class="weltop-link05Click"]').attr('class', 'weltop-link05');
			typeBar.find('a[class="weltop-link06Click"]').attr('class', 'weltop-link06');
		} else if (type == 'imgtxtmsg') {
			typeBar.find('a[class="weltop-link01Click"]').attr('class', 'weltop-link01');
			typeBar.find('a[class="weltop-link02"]').attr('class', 'weltop-link02Click');
			typeBar.find('a[class="weltop-link03Click"]').attr('class', 'weltop-link03');
			typeBar.find('a[class="weltop-link04Click"]').attr('class', 'weltop-link04');
			typeBar.find('a[class="weltop-link05Click"]').attr('class', 'weltop-link05');
			typeBar.find('a[class="weltop-link06Click"]').attr('class', 'weltop-link06');
		} else if (type == 'videomsg') {
			typeBar.find('a[class="weltop-link01Click"]').attr('class', 'weltop-link01');
			typeBar.find('a[class="weltop-link02Click"]').attr('class', 'weltop-link02');
			typeBar.find('a[class="weltop-link03Click"]').attr('class', 'weltop-link03');
			typeBar.find('a[class="weltop-link04Click"]').attr('class', 'weltop-link04');
			typeBar.find('a[class="weltop-link05"]').attr('class', 'weltop-link05Click');
			typeBar.find('a[class="weltop-link06Click"]').attr('class', 'weltop-link06');
		} else if (type == 'service') {
			typeBar.find('a[class="weltop-link01Click"]').attr('class', 'weltop-link01');
			typeBar.find('a[class="weltop-link02Click"]').attr('class', 'weltop-link02');
			typeBar.find('a[class="weltop-link03Click"]').attr('class', 'weltop-link03');
			typeBar.find('a[class="weltop-link04Click"]').attr('class', 'weltop-link04');
			typeBar.find('a[class="weltop-link05Click"]').attr('class', 'weltop-link05');
			typeBar.find('a[class="weltop-link06"]').attr('class', 'weltop-link06Click');
		}
	}
}

// TODO
function getDatas() {
	var isValid = true;
	var errorMsg;
	var cJObj = $('div[class="CMOP-welcontent"]');
	// 验证
	var cType = currentWelType;
	var answer = cJObj.find('textarea').val();

	if (!answer) {
		isValid = false;
		errorMsg = '您尚未正确编辑内容！';
	}
	var dm;
	if (!cType || cType == 'text') {
		dm = {
			'answer' : answer
		};
	} else if (cType == 'imgtxtmsg') {
		dm = {
			'answer' : answer,
			'type' : cType
		};
	} else if (cType == 'imgmsg') {
		dm = {
			'answer' : answer,
			'type' : cType
		};
	} else if (cType == 'musicmsg') {
		dm = {
			'answer' : answer,
			'type' : cType
		};
	} else if (cType == 'videomsg') {
		dm = {
			'answer' : answer,
			'type' : cType
		};
	} else if (cType == 'service') {
		var serviceContainDivJObj = cJObj.find('textarea').next('div');
		var argsJObjs = serviceContainDivJObj.children('div[name="args"]');
		var appArgsFlag = true;
		var appArgsArray = new Array();
		for ( var ai = 0; ai < argsJObjs.length; ai++) {
			var argsJObj = $(argsJObjs[ai]);
			var argsName = argsJObj.attr('argsName');
			var argsOptional = argsJObj.attr('optional');
			var inputJObj = argsJObj.children('input');
			var argsVal = inputJObj.val();
			if (argsOptional == '1') {
				if (!argsVal || argsVal.length == 0) {
					appArgsFlag = false;
					break;
				}
			} else {
				argsOptional = '0';
				if (!argsVal || argsVal.length == 0) {
					argsVal = "";
				}
			}
			appArgsArray.push({
				"name" : argsName,
				"value" : argsVal,
				"optional" : argsOptional
			});
		}
		if (!appArgsFlag) {
			isValid = false;
			errorMsg = '必填的调用参数不能为空！';
		}
		dm = {
			'answer' : answer,
			'type' : cType,
			'appName' : serviceContainDivJObj.attr('appName'),
			'args' : appArgsArray
		};
		if (cJObj.attr('unique') == '1') {
			dm['unique'] = 1;
		}

		var app = appMap[answer];
		if (app) {
			var appService = app['service'];
			if (appService && appService != '') {
				dm['service'] = appService;
			}
			var appType = app['type'];
			if (appType && appType - 0 > 0) {
				dm['exCall'] = app['url'];
			}
		}

	}
	//
	if (!isValid) {
		XIDialog.alert(errorMsg);
		return;
	}
	// 提交
	return dm;
}

function getWelcomeMsg() {
	var dm = getDatas();
	if (!dm) {
		return;
	}
	return [ currentWelType, dm ];
}

function checkTextLength() {
	var textareaJObj = $(currentFoucsTextareaJObj);
	if (textareaJObj) {
		var inputText = textareaJObj.val();
		if (inputText.length > defaultRemainLimit) {
			$('#remainLimit').html(0);
			textareaJObj.val(inputText.substring(0, 600));
		} else {
			$('#remainLimit').html(defaultRemainLimit - inputText.length);
		}
	}
}

function saveWelcome() {
	var welInfo = getWelcomeMsg();
	if (!welInfo)
		return false;
	var welcomeEntitys = {};
	welcomeEntitys['dimAnswer'] = welInfo[1];
	welcomeEntitys['type'] = welInfo[0];
	var welIdEl = $('#welIdField');
	welcomeEntitys['welId'] = welIdEl.val();
	$.ajax({
		url : 'weixin!welcome.action',
		type : 'POST',
		data : {
			"welcomeEntity" : $.toJSON(welcomeEntitys)
		},
		success : function(respTxt0) {
			if ('fail' == respTxt0) {
				XIDialog.alert('开发者账号绑定失败，请检查您的信息或稍候再试！');
				return false;
			} else {
				var welId = respTxt0.replace(/\"/g, '');
				welIdEl.val(welId);
				XIDialog.alert('保存成功！');
			}
		}
	});
}

// app
var classification_loaded_flag = false;

function classification_load() {
	if (!classification_loaded_flag) {
		classification_loaded_flag = true;
		categorys_load();
		for ( var i = 0; i < 6; i++) {
			appByCategory_load(i);
		}
	}
}

function categorys_load() {
	$.ajax({
		url : 'store!getAllCategorysAndCustom.do',
		type : "POST",
		async : false,
		success : function(msg) {
			var categorys = eval("(" + msg + ")");
			for ( var i = 0; i < categorys.length; i++) {
				$('#tab' + (i + 1)).html(categorys[i]["name"]);
				$('#tab' + (i + 1)).attr("cid", categorys[i]["id"]);
			}
		},
		error : function(xhr, status, e) {

		}
	});
}

function showUserAppSelDiv() {
	classification_load();
	$.blockUI({
		message : $("#userAppSelDiv"),
		css : {
			width : 0,
			border : 0,
			background : "none",
			left : '20%',
			top : '20%'
		}
	});
}

function closeUserAppSelDiv() {
	$.unblockUI();
}

var appMap = {};

function appByCategory_load(type, pn) {
	var tabSelected = $('#tab' + type);
	var categoryId = tabSelected.attr('cid');
	var params = {
		'platform' : 'weixin'
	};
	if (categoryId) {
		params["categoryId"] = categoryId;
	}
	if (pn) {
		params["pg.pn"] = pn;
	}
	$.ajax({
		url : 'store!getAppsByCategoryIdAndUid.do',
		type : "POST",
		async : true,
		data : params,
		success : function(msg) {
			var pg = eval("(" + msg + ")");
			// console.log(type,pg);
			var apps = pg.data;
			if (apps && apps.length > 0) {
				var ulJObj = $('#content' + type).find('ul');
				ulJObj.empty();
				for ( var i = 0; i < apps.length; i++) {
					var app = apps[i];

					var liJObj = $('<li/>').attr('class', 'liline01');
					var iconDivJObj = $('<div/>').attr('class', 'Apply_icon01 f_l');
					var aInAppDivJObj = $('<a/>').attr('href', '#');
					var imgInAInAppDivJObj = $('<img/>').attr('src', "../apppic?" + app['icon']).attr('width', '60').attr('height', '60');
					aInAppDivJObj.append(imgInAInAppDivJObj);
					iconDivJObj.append(aInAppDivJObj);
					liJObj.append(iconDivJObj);

					var introDivJObj = $('<div/>').attr('class', 'Apply_intro f_l');
					var hInIntroDivJObj = $('<h2/>');
					var aInHInIntroDivJObj = $('<a/>').attr('href', '#').html(app['name']);
					hInIntroDivJObj.append(aInHInIntroDivJObj);
					introDivJObj.append(hInIntroDivJObj);
					var pInIntroDivJObj = $('<p/>').html(app['description']);
					introDivJObj.append(pInIntroDivJObj);
					// <p class="CM-cl04">有效期：2013年10月20日</p>
					var p2InIntroDivJObj = $('<p/>').html('有效期：待定').attr('class', 'CM-cl04');
					introDivJObj.append(p2InIntroDivJObj);

					liJObj.append(introDivJObj);

					var rightDivJObj = $('<div/>').attr('class', 'Apply_right f_r');
					var pInRightDivJObj = $('<p/>');
					var aInPInRightDivJObj = $('<a/>').css('font-size', '12px').attr('class', 'wx-button02').attr('href', '#').html('确　定').attr(
							'appId', app['id']);
					appMap[app['id']] = app;
					aInPInRightDivJObj.bind('click', function() {
						tLp6ClickCb(this);
						$.unblockUI();
						return false;
					});
					pInRightDivJObj.append(aInPInRightDivJObj);
					rightDivJObj.append(pInRightDivJObj);
					liJObj.append(rightDivJObj);

					ulJObj.append(liJObj);
				}
			}
			// 分页
			pgControl(type, pg);
		},
		error : function(xhr, status, e) {

		}
	});
}

function oneTabClick(type) {
	for ( var i = 0; i < 6; i++) {
		if (i == type) {
			$('#tab' + i).removeAttr('href').css('color', '');
			$('#content' + i).show();
		} else {
			$('#tab' + i).attr('href', '#').css('color', '#1280D9');
			$('#content' + i).hide();
		}
	}
}

function tab0_click() {
	oneTabClick(0);
}

function tab1_click() {
	oneTabClick(1);
}

function tab2_click() {
	oneTabClick(2);
}

function tab3_click() {
	oneTabClick(3);
}

function tab4_click() {
	oneTabClick(4);
}

function tab5_click() {
	oneTabClick(5);
}

$(document).ready(function() {
	var loc = window.location.href;
	var currentPageId = null;
	if (loc.indexOf('#c1') > 0)
		currentPageId = '#c1';
	else if (loc.indexOf('#c2') > 0)
		currentPageId = '#c2';
	else if (loc.indexOf('#c3') > 0)
		currentPageId = '#c3';
	else if (loc.indexOf('#c4') > 0)
		currentPageId = '#c4';
//	if (currentPageId)
//		switchTab($(currentPageId).get(0));

	$('input[name^=app]', $('#jsweb_c2')).each(function(idx, el) {
		el = $(el);
		if (!el.val())
			el.val('请输入内容');
		el.blur(function() {
			if (!$.trim($(this).val()))
				$(this).val('请输入内容');
		});
		el.focus(function() {
			if ($.trim($(this).val()) == '请输入内容')
				$(this).val('');
		});
	});

	classification_load();

	$('#tab0').bind('click', function() {
		tab0_click();
		return false;
	});
	$('#tab1').bind('click', function() {
		tab1_click();
		return false;
	});
	$('#tab2').bind('click', function() {
		tab2_click();
		return false;
	});
	$('#tab3').bind('click', function() {
		tab3_click();
		return false;
	});
	$('#tab4').bind('click', function() {
		tab4_click();
		return false;
	});
	$('#tab5').bind('click', function() {
		tab5_click();
		return false;
	});
});
