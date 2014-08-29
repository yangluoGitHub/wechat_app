//禁止右键
//document.oncontextmenu=function(){return false;}
/*引入常量关联数组资源文件*/
document.write('<script type="text/javascript" src="../scripts/constantMap.js"><\/script>');

/*jQuery插件，简单功能*/
/*
 * rowHilight();
 * onlyNumber();
 * onlyPositiveInteger();
 * timePattern();
 * replaceConstantMap(mapName);
 * fillOptions(mapName);
 * lineNo2Name();
 * userNo2Name();
 * orgNo2Name();
 * showOrgSelector(imgId, noId, nameId)
 * checkContent({type: 1, name: "", reg: 正则, msg: 消息});
 */
if (typeof(jQuery) != 'undefined') {    //如果页面引入了jquery.js
	
(function($){ 
	/**
	 * 表格除第一行外，鼠标停留行高亮，离开移除高亮
	 */
	$.fn.rowHilight = function(){
		this.each(function(){
			if ($(this).is('table')) {    //如果是table
				$(this).find('tr:gt(0)')
			 	.mouseenter(function(){
					$(this).children().addClass("onmouseover");
				})	 
			 	.mouseleave(function(){
					$(this).children().removeClass("onmouseover");   	 
			 	});
			}
		});
		return this;    //支持链式操作
	};
	/**
	 * 只能输入数字(包括数字，小数点，开头负号)
	 */
	$.fn.onlyNumber = function(){
		this.each(function(){
			if ($(this).is(':text')) {    //如果是text
				$(this)
				.keypress(function(){
					//if (event.keyCode < 48 || event.keyCode > 57){
					//	event.returnValue = false;
					//}
					if(event.keyCode >= 48 && event.keyCode <= 57){ 
						event.returnValue = true;
					} else if (window.event.keyCode == 46 ) {    //小数点
						var flag = $(this).val().indexOf(".") != -1;   //已经存在小数点
						if(flag) {
							window.event.keyCode = 0; 
						}
					} else if (window.event.keyCode == 45 ) {    //负号
						var indexOfMinus = $(this).val().indexOf("-");
						if(indexOfMinus != -1) {    //存在
							window.event.keyCode = 0 ;
						}else if (indexOfMinus == -1 && $(this).val().length >= 1) {    //不存在，但输入框内已经有值
							$(this).val('');
						}
					}
				})
				.bind("beforepaste",function(){
					clipboardData.setData("text",clipboardData.getData("text").replace(/[^\d-.]/g,""));
				})
				.blur(function(){
					$(this).val($(this).val().replace(/[^\d-.]/g,''));
				});
			}
		});
		return this;
	};
	/**
	 * 只能输入正整数
	 */
	$.fn.onlyPositiveInteger = function(){
		this.each(function(){
			if ($(this).is(':text')){
				$(this)
				.keyup(function(){
					$(this).val($(this).val().replace(/[^\d]/g,""));
				})
				.bind("beforepaste",function(){
					clipboardData.setData("text",clipboardData.getData("text").replace(/[^\d]/g,""));
				})
				.blur(function(){
					var tempVal = parseInt($(this).val(),10);
					if(isNaN(tempVal)==false&&tempVal>0){$(this).val(tempVal);}
					else{$(this).val("");}
				});
			}
		});
		return this;
	};
	/**
	 * @param {name:'am'}, 无参数默认 time='all'
	 * text,支持时间格式检测，hh:mm，并将 h:m: 格式的时间补为 0h:0m:00 格式
	 * 时间格式为 hhmm ，补为 hh:mm:00 
	 */
	$.fn.timePattern = function(opts){
		var defaults = {time: 'all'};
		var opts = $.extend({},$.fn.timePattern.defaults, opts);
		
		this.each(function(){
			if ($(this).is(':text')) {
				$(this)
				.keyup(function(){
					$(this).val($(this).val().replace(/[^\d:]/g,''));
				})
				.bind("beforepaste", function(){
					clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d:]/g,''));
				})
				.attr("maxlength",5)
				.blur(function(){
					var value = $(this).val();
					if (value.length == 0) return;
					if(!/\b[0-9]{1,2}:[0-9]{1,2}\b/.test(value)) {
						if (/\b[0-9]{4}\b/.test($(this).val())) {
							var sHour = value.substring(0, 2);
							var sMinute = value.substring(2);
						} else {
				    		alert("时间格式：hh:mm");
				    		$(this).val("");
				    		$(this).focus();
				    		return false;
						}
					} else {
						var timeArr = $(this).val().split(":");
						var sHour = pad(timeArr[0],2);
						var sMinute = pad(timeArr[1], 2);
					}
					var sSecond = "00";
					var hour = parseInt(sHour, 10), minute = parseInt(sMinute, 10);
					if (opts.time == "am") { 
						flag = hour>=0 && hour<=11 && minute>=0 && minute<60;
						var sTimeMin = '00:00', sTimeMax = '11:59';
					} 
					else if (opts.time == "pm"){
						flag = hour>=12 && hour<=23 && minute>=0 && minute<60; 
						var sTimeMin = '12:00', sTimeMax = '23:59';
					}
					else { 
						flag = hour>=0 && hour<=23 && minute>=0 && minute<60; 
						var sTimeMin = '00:00', sTimeMax = '23:59';
					}
					if (!flag) {
						alert("时间超出范围: " + sTimeMin + "--" + sTimeMax );
						$(this).val("");
			    		$(this).focus();
					} else {
						$(this).val(sHour + ":" + sMinute + ":" + sSecond);
					}
				});
			}
		});
		return this;
	};
	/**
	 * 使用constantMap.js的资源进行替换 
	 */
	$.fn.replaceConstantMap = function(mapName){
		this.each(function(){
			if($.trim($(this).html())==null || $.trim($(this).html())=="undefined" || $.trim($(this).html())==""){
				$(this).val(mapName[$.trim($(this).val())]);
			} else {
				$(this).html(mapName[$.trim($(this).html())]);
			}
		});
		return this;
	};
	/**
	 * 向select添加options,使用constantMap.js的资源
	 */
	$.fn.fillOptions = function(mapName, arr){
		var arrType = typeof arr;
		var isArrArray = $.isArray(arr);
		
		this.each(function(){
			if ($(this).is('select')) {
				var oSelect = $(this);
				$.each(mapName, function(key, value){
				    //如果arr是undefined(即没有传入这个参数)，或者这个Key在传入的Array中
					if ( arrType=='undefined' || (isArrArray && $.inArray(key, arr)!=-1)) {
						$(oSelect).append(
							$("<option></option>").val(key).text(value)	
						);
					}
				});
			}
		});
		
		return this;
	};
	/**
	 * 线路ID替换成名称
	 */
	$.fn.lineNo2Name = function(){
		this.each(function(){
			DWREngine.setAsync(false);
			var thisTd = $(this);
			var id = $.trim(thisTd.html());
			addnotesLineService.qryNameById(id, function(data){
				thisTd.html(data);
			});
			DWREngine.setAsync(true);
		});
		return this;
	};
	/**
	 * 用户ID替换成名称
	 */
	$.fn.userNo2Name = function(){
		this.each(function(){
	 		DWREngine.setAsync(false);
	 		var opTd = $(this);
	 		var no = $.trim(opTd.html());
	 		userService.qryUserNameById(no, function(data){
	 			opTd.html(data);
	 		});
	 		DWREngine.setAsync(true);
	 	});
		return this;
	};
	/**
	 * 银行机构ID替换成名称
	 */
	$.fn.orgNo2Name = function(){
		this.each(function(){
	 		DWREngine.setAsync(false);
	 		var thisTd = $(this);
	 		var no = $.trim(thisTd.html());
	 		orgService.getNameByNo(no, function(data){
	 			thisTd.html(data);
	 		});
	 		DWREngine.setAsync(true);
	 	});
		return this;
	};
	
	$.fn.showOrgSelector = function(imgId, noId, nameId){
		this.each(function(){
			if($(this).is('a')) {
				$(this).one('click', function(){
					showOrgSelector(imgId, noId, nameId);
					$("#orgFrame").hide();
				}).click(function(){
					$("#orgFrame").toggle('fast');
				});
			}
		});
		return this;
	};
	
	/**
	 * 提交表单时验证用，不支持链式操作
	 */
	$.fn.checkContent = function(opts){
		//reg是检验用正则，msg是提示语，name是需要检验的表单部分的名字
		var defaults = {type: 1, name: ""};
		var opts = $.extend({}, defaults, opts);
		var flag = true;
		
		if (opts.type = 1) {
			opts.reg = /^[0-9a-zA-Z]*$/, opts.msg = "字母和数字";
		} else if (opts.type = 2) {
			opts.reg = /^[0-9]*$/, opts.msg = "数字";
		} else if (opts.type = 3) {
			opts.reg = /^[a-zA-Z]*$/, opts.msg = "字母";
		}
		
		this.each(function(){
			var value = $(this).val();
			if (!opts.reg.test(value)) {
				alert(opts.name + "必须是" + opts.msg);
				$(this).select();
				flag = false;
				return false;
			}
		});
		return flag;
	};
	
})(jQuery);

}

//判定字符串是否为空值
function isNull(str)
{ 
  if (removeSpace(str)==""){
    return true;
  }
  return false;
}

function phoneCheck(obj)
{
    strRef = "0123456789()+-";
	strTmp = (obj.value == undefined ? obj : obj.value);
	str = "";
	for(i=0;i<strTmp.length;i++){
		if(strTmp.charAt(i) != ' '){
			str += strTmp.charAt(i);
		}
	}
	for (i=0;i<str.length;i++) {
		tempChar= str.substring(i,i+1);
		if (strRef.indexOf(tempChar,0)==-1) {
			return false; 
		}  
	}
	return true;
}

function mobileCheck(obj)
{
    strRef = "0123456789";
	strTmp = (obj.value == undefined ? obj : obj.value);
	str = "";
	for(i=0;i<strTmp.length;i++){
		if(strTmp.charAt(i) != ' '){
			str += strTmp.charAt(i);
		}
	}
	for (i=0;i<str.length;i++) {
		tempChar= str.substring(i,i+1);
		if (strRef.indexOf(tempChar,0)==-1) {
			return false; 
		}  
	}
	return true;
}

function isvalidemail(s)
	{
		var reg1 = new RegExp('^[a-zA-Z0-9][a-zA-Z0-9@._-]{3,}[a-zA-Z]$');
		var reg2 = new RegExp('[@.]{2}');
		
		if (s.search(reg1) == -1
				|| s.indexOf('@') == -1
				|| s.lastIndexOf('.') < s.lastIndexOf('@')
				|| s.lastIndexOf('@') != s.indexOf('@')
				|| s.search(reg2) != -1)
			return false;
		
		return true;
	}
	
//判定字符串是否为整数

function isInteger(str)
{ 
  if(isNaN(parseInt(str,10))){
    return false;
  }
  if (str.toLowerCase() != str.toUpperCase()){
    return false;
  }
  else{
     if (str.indexOf(".") != -1){
       return false;
     }
	 return true;
  }
}

// 判断字符串是否为正整数
function isPositiveInteger(str){
	if(isInteger(str)&&parseInt(str,10)>0){return true;}
	else{return false;}
}

//判定字符串是否为浮点数

function isFloat(str)
{
  if(isNaN(parseFloat(str))){
    return false;
  }
  if (str.toLowerCase() != str.toUpperCase()){
    return false;
  }
  return true;
}



//返回字符串字节长度（1个汉字占2个字节）,如果传入的不是string,返回-1


function getByteLength(sString)
{
  if (typeof sString !== "string") {
	return -1;
  }
  var sStr,iCount,i,strTemp ; 

  iCount = 0 ;
  
  sStr = sString.split("");
  
  for (i = 0 ; i < sStr.length ; i ++){
  
    strTemp = escape(sStr[i]); 
    
    if (strTemp.indexOf("%u",0) == -1) {   // 表示是汉字
      iCount = iCount + 1 ;
    } 
    else {
      iCount = iCount + 2 ;
    }
  }
  return iCount ;
}



//除去字符串前后的空格

function removeSpace(str)
{
	trimLeft = /^\s+/;
	trimRight = /\s+$/;
	return str == null ? "" : str.toString().replace(trimLeft,"").replace(trimRight,"");
}

//参数1 - 要进行处理的字符串
//参数2 - 要替代的字符串
//参数3 - 替代字符串

function replaceString(str1,str2,str3)
{
  var returnStr = str1;
  var pos;
  var len;
  
  len = str2.length;  
  pos = returnStr.indexOf(str2);
  
  while(pos >= 0){
	returnStr=returnStr.substring(0,pos) + str3 + returnStr.substring(pos+len,returnStr.length);
	pos = returnStr.indexOf(str2);
  }
  
  return returnStr;
}


//打开日期选择器

var dateElement;

function openDatePicker(target){
	   
      dateElement = target;
      var a = dateElement.value;
      if(a != ""){
      	if(a.length != 10){
      		alert(document.getElementById('script.common.dateFormat').value+'：yyyy-mm-dd');
      		return;
      	}
      	else{
          var initYear = parseFloat(a.substring(0,4));
          var initMonth = parseFloat(a.substring(5,7));
          var initDate = parseFloat(a.substring(8,10));
          
          if(initYear > 2015 || initYear < 2005){
          	alert(document.getElementById('script.common.yearLimit').value);
          	return;
          }
          if(initMonth > 12 || initMonth < 1){
          	alert(document.getElementById('script.common.monthLimit').value);
          	return;
          }
          if(initDate > 31 || initDate < 1){
          	alert(document.getElementById('script.common.DateLimit').value);
          	return;
          }
      	}
      }
      
      var width = "210";
      var height = "250";
      var top=(window.screen.height-height)/2;
      var left=(window.screen.width-width)/2;
      var dateppicker = window.open("/project/pages/datePicker.jsp","datePicker","scrollbars=no,toolbar=no,location=no,direction=no,resizeable=no,width="+width+",height="+height+",top="+top+",left="+left);
      dateppicker.focus();
}

//设置日期元素的值 

function setDate(date){
   dateElement.value = date; 
}

//获取日期元素的值

function getDate(){
   return dateElement.value; 
}


//打开窗口居屏幕中央

function openWindow(url,title,width,height){
    var top=(window.screen.height-height)/2;
    var left=(window.screen.width-width)/2;
    var openWin = window.open(url,title,"scrollbars=yes,toolbar=no,location=no,direction=no,resizeable=no,width="+width+",height="+height+",top="+top+",left="+left);
    openWin.focus();
}

//打开模态对话框居屏幕中央

function openModalWindow(url,width,height){
    return window.showModalDialog(url,'',"center:yes;help:no;status:no;dialogWidth="+width+"px;dialogHeight="+height+"px");
}


/**
 * 功能介绍：全选表单formObj内的所有checkBox
 * 参数说明：formObj：表单对象，checkedObj:全选 checkBox按钮
 */
function checkedAll(formObj,checkedObj)
{
	if(checkedObj.checked==true)
	{
		for(var i=0;i<formObj.elements.length;i++)
		{
			if(formObj.elements[i].type=="checkbox")
			{
				formObj.elements[i].checked=true;
			}
		}
	}
	else
	{
		for(var i=0;i<formObj.elements.length;i++)
		{
			if(formObj.elements[i].type=="checkbox")
			{
				formObj.elements[i].checked=false;
			}
		}
	}
}
/**
 * 功能介绍：全选表单formObj内的所有checkBox
 * 参数说明：formObj：表单对象
 */
function selectAllInForm(formObj)
{
	for(var i=0;i<formObj.elements.length;i++)
	{
		if(formObj.elements[i].type=="checkbox")
		{
			formObj.elements[i].checked=true;
		}
	}
}
/**
 * 功能介绍：反选表单formObj内的所有checkBox
 * 参数说明：formObj：表单对象
 */
function reverseInForm(formObj)
{
	for(var i=0;i<formObj.elements.length;i++)
	{
		if(formObj.elements[i].type=="checkbox")
		{
			formObj.elements[i].checked=!formObj.elements[i].checked;
		}
	}
}
/**
 * 功能介绍：全不选表单formObj内的所有checkBox
 * 参数说明：formObj：表单对象
 */
function selectNoneInForm(formObj)
{
	for(var i=0;i<formObj.elements.length;i++)
	{
		if(formObj.elements[i].type=="checkbox")
		{
			formObj.elements[i].checked=false;
		}
	}
}
/**
 * 功能介绍：判断表单formObj中是否有checkBox按钮被选中，有则确认后提交表单 
 * 参数说明：formObj：表单对象
 */
function isSelect(formObj)
{
	var isSelect=0;
	for(var i=0;i<formObj.elements.length;i++)
	{
		if(formObj.elements[i].type=="checkbox"&&formObj.elements[i].checked==true)
		{
			isSelect=1;
			break;
		}
	}
	if(isSelect==0)
	{ 
		alert(document.getElementById('script.common.notSelect').value);
		return false;
	}
	return true;
}

//删除select控件中的全部内容
function deleteSelectAll(obj)
{
	for(i=obj.length-1;i>=0;i--)
	{
		obj.remove(i);
	}
}


//检验IP格式是否符标准格式
//参数1 - 要进行处理的对象
function checkip(obj){
	var scount=0; 
	var ip = obj.value; 
	var iplength = ip.length; 
	var Letters = "1234567890."; 
	for (i=0; i < iplength; i++) 
  	{ 
   		var CheckChar = obj.value.charAt(i); 
  		if (Letters.indexOf(CheckChar) == -1) 
   		{ 
  			alert (document.getElementById('script.common.ipAddressError').value+"\".\","+document.getElementById('script.common.invalidCharacter').value); 
 
   			obj.focus(); 
   			return false; 
   		} 
  	} 

	for (var i = 0;i<iplength;i++){ 
  		(ip.substr(i,1)==".")?scount++:scount; 
	}	
		if(scount!=3) 
		{ 
  			alert (document.getElementById('script.common.ipAddressError').value+"\".\","+document.getElementById('script.common.invalidCharacter').value); 
    		obj.focus(); 
 			return false; 
		} 
	
	first = ip.indexOf("."); 
	last = ip.lastIndexOf("."); 
	str1 = ip.substring(0,first); 
	subip = ip.substring(0,last); 
	sublength = subip.length; 
	second = subip.lastIndexOf("."); 
	str2 = subip.substring(first+1,second); 
	str3 = subip.substring(second+1,sublength); 
	str4 = ip.substring(last+1,iplength); 

	if (str1=="" || str2=="" ||str3== "" ||str4 == "") 
	{
		alert(document.getElementById('script.common.ipError').value); 
  		obj.focus(); 
		return false; 
	} 
	if(str1.length>3||str2.lenght>3||str3.length>3||str4.length>3)
	{
	    alert (document.getElementById('script.common.ipLimit').value); 
   		obj.focus(); 
    	return false;
	}
	if (str1<= 0 || str1 >255) 
	{
		alert (document.getElementById('script.common.ipLimit').value); 
   		obj.focus(); 
    	return false; 
	} 
	else if (str2< 0 || str2 >255) 
	{	
		alert (document.getElementById('script.common.ipLimit').value); 
 		obj.focus(); 
		return false; 
	} 
	else if (str3< 0 || str3 >255) 
	{
		alert (document.getElementById('script.common.ipLimit').value); 
   		obj.focus(); 
		return false; 
	} 
	else if (str4<= 0 || str4 >255) 
	{
		alert (document.getElementById('script.common.ipLimit').value); 
	 	obj.focus(); 
		return false; 
	}
	return true;
}

//计算字符串长度，若超常则返回提示信息
//参数1 - 要进行处理的字符串对象
//参数2 - 字符串规定长度

function checktext(obj,arg){
	var checkchar = new String(obj.value);
	var len = checkchar.length;
	var num = 0;
	checkchar=0;
	for(var i=0;i<len;i++){
		char = obj.value.charAt(i);
		tmp = escape(char); 
    	if (tmp.indexOf("%u",0) == -1) {   
      		num = num + 1 ;
    	} 
    	else {
     		 num = num + 2 ;
     	}
     }
//		re=/[\u4E00-\u9FA5]/g; 
//		if(re.test(char)){
//			if(char.match(re).length == 1){
//				num+=2;
//			}
//		}
//		else {
//			num+=1;
//		}
			 
//	}

	//判断是否超长
 	if(num > arg){
 	alert(document.getElementById('script.common.inputLengthLimit').value + arg + document.getElementById('script.common.no'));
 	obj.focus();
 	return false;
 	}
 	return true;
}
function checktextLen(obj,arg){
	var checkchar = new String(obj.value);
	var len = checkchar.length;
	var num = 0;
	checkchar=0;
	for(var i=0;i<len;i++){
		char = obj.value.charAt(i);
		tmp = escape(char); 
    	if (tmp.indexOf("%u",0) == -1) {   
      		num = num + 1 ;
    	} 
    	else {
     		 num = num + 2 ;
     	}
     } 
	//判断是否超长
 	if(num > arg){
 	return false;
 	}
 	return true;
}
//检验E-MAIL输入是否符合标准格式
//参数1 - 待检验的对象
function validEmail(obj){
   reEmail=/\w+\@\w+\.\w+/gi ;//正则表式如果符合要求就通过
   var isOk=reEmail.test(obj.value);
   if(!isOk){
       alert(document.getElementById('System.fault_email_format').value);
//     obj.value='';
   	   obj.focus();
	   obj.select();
	   return false;
   }
   	   return true;
}

//判断是否有中文
function checkChinese(value)
{
	var str,Num = 0;
	for (var i=0;i<value.length;i++)
	{
		str = value.substring(i,i+1);
		if (str<="~")  //判断是否双字节
			continue;
		else
		{
			return true;
		}
	}
	return false;
}


//返回字符串的长度（1个汉字代表2单位长度）
function getLength(sString)
{
  var sStr,iCount,i,strTemp ; 

  iCount = 0 ;
  
  sStr = sString.split("");
  
  for (i = 0 ; i < sStr.length ; i ++){
  
    strTemp = escape(sStr[i]); 
    
    if (strTemp.indexOf("%u",0) == -1) {   // 表示是汉字
      iCount = iCount + 1 ;
    } 
    else {
      iCount = iCount + 2 ;
    }
  }
  return iCount ;
}

//无提示关闭窗口
function CloseWindow() 
{ 
 var ua=navigator.userAgent; 
 var ie=navigator.appName=="Microsoft Internet Explorer"?true:false; 
 if(ie) 
 { 
      var IEversion=parseFloat(ua.substring(ua.indexOf("MSIE ")+5,ua.indexOf(";",ua.indexOf("MSIE ")))); 
  if(IEversion< 5.5) 
  { 
   var str  = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">'; 
       str += '<param name="Command" value="Close"></object>'; 
       document.body.insertAdjacentHTML("beforeEnd", str); 
       document.all.noTipClose.Click(); 
  } 
      else 
  { 
       window.opener =null; 
       window.close(); 
      } 
   } 
 else 
 { 
  window.close(); 
   } 
}

function hideMe(obj) {
	obj.style.display = 'none';
}

function showMe(obj) {
	obj.style.display = 'block';
}

/*@author copied, since 2011-11-1*/
/**
 * 前位补0，n是需要补到的位数
 */
function pad(num, n) {
    var len = num.toString().length;
    while(len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}
function unPad(num, n) {
	var tempString = num.toString();
	var len = num.toString().length;
	while(len > n){
		if (tempString.charAt(0) == "0") {
			tempString = tempString.substring(1);
			len--;
		} else {
			break;
		}
	}
	return tempString;
}
/**
 * 获取YYYY-MM-DD格式的当天日期
 * @returns {String}
 */
function getDateStr(){
	var date = new Date();
	var dateString = date.getFullYear() + "-" + pad((date.getMonth()+1),2) + "-" + pad(date.getDate(),2);
	return dateString;
} 

/**
 * 获取系统时间hh:MM:ss
 * @returns {String}
 */
function getSysTime(){
	var date = new Date();
	var time = pad(date.getHours(),2) + ":" + pad((date.getMinutes()+1),2) + ":" + pad(date.getSeconds(),2);
	return time;
}

/**
 * 获取YYYY-MM格式的上一个月份
 * @returns {String}
 */
function getPreviousMonth(){
	var sysD = new Date();
	var sysY = sysD.getFullYear();
	var sysM = sysD.getMonth();
	var preY = sysM==0 ? sysY-1 : sysY;
	var preM = sysM==0 ? 11 : sysM-1;
	var dateString = preY + "-" + pad((preM+1),2);
	return dateString;
}

/**
 * 是否满足：startDate<=endDate，满足返回true
 * @param startDate(YYYY-MM-DD)
 * @param endDate(YYYY-MM-DD)
 * @returns {Boolean}
 */
function check2Date(startDate, endDate){
	if (typeof startDate != 'string' || typeof endDate != 'string') {
		return false;
	}
	if (startDate.length * endDate.length != 0 && startDate > endDate) {
		return false;
	} else {
		return true;
	}
}

/**
 * 获得时间间隔天数
 * @param startDate YYYY-MM-DD
 * @param endDate YYYY-MM-DD
 * @returns {Number} 
 */
function getSubDayNum(startDate, endDate){
	var start = new Date(parseInt(startDate.substring(0,4), 10),parseInt(startDate.substring(5,7), 10)-1,parseInt(startDate.substring(8,10), 10));
	var end = new Date(parseInt(endDate.substring(0,4), 10),parseInt(endDate.substring(5,7), 10)-1,parseInt(endDate.substring(8,10), 10));
	var subDayNum = (end.getTime()-start.getTime())/(24*3600*1000);
	return subDayNum;
}

/**
 * 
 * @param yearMonthDay
 * @returns {Date}
 */
function getDateFromStr(yearMonthDay) {
	if (typeof yearMonthDay != 'string') {
		return null;
	}
	var year = parseInt(yearMonthDay.substring(0,4), 10);
	var month = parseInt(yearMonthDay.substring(5,7), 10);
	var day = parseInt(yearMonthDay.substring(8), 10);
	
	var aDate = new Date();
	aDate.setFullYear(year, month - 1, day);
	return aDate;
}

/**
 * 将小时和分钟组合成：HH:MM
 * @param hour
 * @param minute
 * @returns {String}
 */
function getTimeHHMM(hour, minute){
	if(getByteLength(hour)==1){
		hour = "0"+hour;
	}
	if(getByteLength(minute)==1){
		minute = "0"+minute;
	}
	return hour+":"+minute;
}

/**
 * 从格式为HH:MM或HH:MM:SS的时间string里解析出小时
 * @param timeStr
 * @returns
 */
function getTimeHour(timeStr){
	var hour  = timeStr.substring(0,2);
	hour = hour.substring(0,1)=="0"?hour.substring(1,2):hour;
	return hour;
}

/**
 * 从格式为HH:MM或HH:MM:SS的时间string里解析出分钟
 * @param timeStr
 * @returns
 */
function getTimeMinute(timeStr){
	var minute  = timeStr.substring(3,5);
	minute = minute.substring(0,1)=="0"?minute.substring(1,2):minute;
	return minute;
}

/**
 * 格式化
 */
function format(str, step, splitor){
	str = str.toString();
	var len = str.length;
	if(len > step) {
		var l1 = len%step;
		var l2 = Math.floor(len/step);
		var arr = [];
		var first = str.substr(0, l1);
		if(first != '') {
			arr.push(first);
		};
		for(var i=0; i<l2 ; i++) {
			arr.push(str.substr(l1 + i*step, step));                                   
		};
		str = arr.join(splitor);
	};
	return str;
}

/**
 * 获取时间 
 * @param baseTime 基本时间
 * @param chgHour
 * @param chgMinute
 * @param chgSecond
 * @returns "hh:mm:ss"
 */
function getFixedTime(baseTime, chgHour, chgMinute, chgSecond){
	var second = parseInt(baseTime.substr(6,2),10);
	var minute = parseInt(baseTime.substr(3,2),10);
	var hour = parseInt(baseTime.substr(0,2),10);
	//reset second
	second = second + chgSecond;
	if(second<0){
		minute = minute - Math.floor((60-second)/60) ;
		second = 60-(0-second)%60;
	}
	if(second>59){
		minute = minute + Math.floor(second/60);
		second = second%60;
	}
	//reset minute
	minute = minute + chgMinute;
	if(minute<0){
		hour = hour - Math.floor((60-minute)/60);
		minute = 60-(0-minute)%60;
	}
	if(minute>59){
		hour = hour + Math.floor(minute/60);
		minute = minute%60;
	}
	//reset hour
	hour = hour + chgHour;
	if(hour<0){
		hour = 24 - (0-hour)%24;
	}
	if(hour>23){
		hour = hour%24;
	}
	//return
	return pad(hour,2)+":"+pad(minute,2)+":"+pad(second,2);
}

