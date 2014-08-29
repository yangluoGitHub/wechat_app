/* 
 * 功能介绍：添加select的下拉元素；有重复值时不添加
 * 参数说明：obj：select对象；value：元素值；text：元素文本
 * author: hpshen
 */
 
function addNode(obj,value,text){
	var len=obj.length;
	var isExit=false;
	for(var j=0;j<len;j++){  
   	    if(value==obj.options[j].value){
            isExit=true;
             break;
        }
    }
    if(!isExit)
    obj.options.add(new Option(text,value));
}

/* 
 * 功能介绍：将源select对象中选中的元素添加到到目的select对象中。
 * 参数说明：srcObj：源select对象；desObj：目的select对象；alertMessage：报警文本，保留扩展
 * author: hpshen
 */
function addChildNodes(srcObj,desObj,alertMessage){
    
    var desLen=desObj.length;
    var srcLen=srcObj.length; 
    for(var i=0;i<srcLen;i++){
        var srcOpt=srcObj.options[i];
        if(srcOpt.selected){
           //如果选择提示栏，则不做任何添加
           var isContinue=false;
		   if(srcOpt.value==0){
			   isContinue=true;
			   continue;
		   }
            //如果已经选择了该项，则不再添加
           var desLen=desObj.length;
            
           for(var j=0;j<desLen;j++){
               if(srcOpt.value==desObj.options[j].value){
                   isContinue=true;
                   continue;
               }
           }
           if(isContinue==true){
               continue;  
           }
           else{
           	desObj.options.add(new Option(srcOpt.text,srcOpt.value));
           }
        }
    }
}


/* 
 * 功能介绍：移除select中选中的元素
 * 参数说明：obj：select对象；
 * author: hpshen
 */
 
function removeChildNodes(obj){
    var len=obj.length;
    for(var i=len-1;i>=0;i--){        
        if(obj.options[i].selected){
            obj.remove(i);
        }
    }
}

/* 
 * 功能介绍：清除select的下拉元素；
 * 参数说明：obj：select对象；
 * author: hpshen
 */
 
function clearListObj(obj){
    var len=obj.length;
    for(var i=0;i<len;i++) obj.remove(0);
}

/* 
 * 功能介绍：全选select的下拉元素
 * 参数说明：obj：select对象；
 * author: hpshen
 */
 
function selectAll(obj){
    var len=obj.length;
    for(var i=0;i<len;i++){
        obj.options[i].selected=true;
    }
}
