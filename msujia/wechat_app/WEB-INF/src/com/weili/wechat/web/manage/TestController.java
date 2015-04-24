package com.weili.wechat.web.manage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Scope("prototype")
@Controller
@RequestMapping("/products")
public class TestController{
  
 /**
  * ≤‚ ‘∑Ω∑®,HelloWord
  * @param request
  * @param response
  * @return
  * @throws Exception
  */
 @RequestMapping(value="/list",method=RequestMethod.GET)
    public String getProducts(HttpServletRequest request,HttpServletResponse response) throws Exception {
   
        request.setAttribute("name", "helloWord");
         
        return "products/list";
         
    }
}