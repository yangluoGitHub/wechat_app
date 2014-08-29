/* 
 * ���ܽ��ܣ����select������Ԫ�أ����ظ�ֵʱ�����
 * ����˵����obj��select����value��Ԫ��ֵ��text��Ԫ���ı�
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
 * ���ܽ��ܣ���Դselect������ѡ�е�Ԫ����ӵ���Ŀ��select�����С�
 * ����˵����srcObj��Դselect����desObj��Ŀ��select����alertMessage�������ı���������չ
 * author: hpshen
 */
function addChildNodes(srcObj,desObj,alertMessage){
    
    var desLen=desObj.length;
    var srcLen=srcObj.length; 
    for(var i=0;i<srcLen;i++){
        var srcOpt=srcObj.options[i];
        if(srcOpt.selected){
           //���ѡ����ʾ���������κ����
           var isContinue=false;
		   if(srcOpt.value==0){
			   isContinue=true;
			   continue;
		   }
            //����Ѿ�ѡ���˸���������
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
 * ���ܽ��ܣ��Ƴ�select��ѡ�е�Ԫ��
 * ����˵����obj��select����
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
 * ���ܽ��ܣ����select������Ԫ�أ�
 * ����˵����obj��select����
 * author: hpshen
 */
 
function clearListObj(obj){
    var len=obj.length;
    for(var i=0;i<len;i++) obj.remove(0);
}

/* 
 * ���ܽ��ܣ�ȫѡselect������Ԫ��
 * ����˵����obj��select����
 * author: hpshen
 */
 
function selectAll(obj){
    var len=obj.length;
    for(var i=0;i<len;i++){
        obj.options[i].selected=true;
    }
}
