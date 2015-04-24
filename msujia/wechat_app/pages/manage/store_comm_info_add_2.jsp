<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>
<SCRIPT   LANGUAGE="JavaScript"> 
   var   flag=false; 
  function   DrawImage(){ 
  var ImgD = document.getElementById("mainimage");
  alert("ok");
        var image=new   Image(); 
      
  	//image.src=ImgD.src; 
  	image.src = "c:\fakepath\3558735.jpg";
  	alert(ImgD.src);
  
        if(image.width>0   &&   image.height>0){ 
          flag=true; 
    if(image.width>300 || image.height>200)
    {
            ImgD.width=image.width/2;     
            ImgD.height=image.height/2; 
            ImgD.alt=image.width+"×"+image.height; 
   }
   else
   {
   ImgD.width=image.width;     
            ImgD.height=image.height; 
            ImgD.alt=image.width+"×"+image.height; 
   }
          } 
        }   
  function  mainChange(Value){ 
  alert(Value);
  flag=false; 
  document.all.pictureLink.alt=""; 
  document.all.pictureLink.src=Value; 
  document.all.maindiv.style.display='block'; 
  init();
  }
  
  //将imageDest图片的大小按比例缩放,适合显示在宽W和高H的区域内 
	function ResizeImage(imageDest, W, H) {
		//显示框宽度W,高度H 
		var image = new Image();
		image.src = imageDest.src;
		alert(image.src);
		if (image.width > 0 && image.height > 0) {
			//比较纵横比 
			if (image.width / image.height >= W / H)//相对显示框：宽>高 
			{
				if (image.width > W) //宽度大于显示框宽度W，应压缩高度 
				{
					imageDest.width = W;
					imageDest.height = (image.height * W) / image.width;
				} else //宽度少于或等于显示框宽度W，图片完全显示 
				{
					imageDest.width = image.width;
					imageDest.height = image.height;
				}
			} else//同理 
			{
				if (image.height > H) {
					imageDest.height = H;
					imageDest.width = (image.width * H) / image.height;
				} else {
					imageDest.width = image.width;
					imageDest.height = image.height;
				}
			}
		}
	}
	
	//将页面内所有指定id的图片按比例缩放 
	function RsizeAllImageById(id, W, H) {
		var imgs = document.getElementsByTagName("img");
		alert(imgs);
		for ( var i = 0; i < imgs.length; i++) {
			if (imgs[i].id == id) {
				ResizeImage(imgs[i], W, H);
			}
		}
	}
	function init() { 
	alert("ok");
		RsizeAllImageById("pictureLink", 150, 113); 
	} 
  
   </SCRIPT> 
  <TABLE   border="0"> 
      <TBODY> 
  <tr>   
                  <td   height="22"   colspan="5"> 
  top:<input   type="file"   name="up1"   style="width:300"   class="textbox"   onChange="javascript:mainChange(this.value);"> 
          </td> 
              </tr> 
      </TBODY> 
  </TABLE> 
      <table height="78"   border="0"   cellpadding="0"   cellspacing="0"> 
      <tr> 
              <td  ><div   id="maindiv" style="display:none">
     <img id=pictureLink name="pictureLink" width="150" height="113" border="0"  src="" />
     </div></td> 
  
          </tr> 
      </table>
<body>
</body>
</html>