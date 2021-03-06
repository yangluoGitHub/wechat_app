<%@ page language="java"%>
<%@page import="com.weili.wechat.common.Resource"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.weili.wechat.common.GetResource"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<% 
   Resource resource =  new Resource("zh_CN");
   if( session.getAttribute("locale") != null )
   resource = (Resource)GetResource.getOneResource(application,session.getAttribute("locale").toString());
%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <title>见性</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/style.css">
        
        <!-- Javascript -->
        <script type="text/javascript" src="assets/js/jquery-1.8.2.min.js"></script>
        <script type="text/javascript" src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/js/jquery.backstretch.min.js"></script>

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>

        <div class="header">
            <div class="container">
                <div class="row">
                    
                </div>
            </div>
        </div>

        <div class="register-container container">
            <div class="row">
                
                <div class="register span6">
                    <form action="register.do?action=register" method="post">
                        <h2><span class="red"><strong>${message}</strong></span></h2>
                        
                       
                    </form>
                </div>
            </div>
        </div>
        

<script language="javascript">
jQuery(document).ready(function() {

    /*
        Background slideshow
    */
    alert('${message}');
    $.backstretch([
      "assets/img/backgrounds/1.jpg"
    , "assets/img/backgrounds/2.jpg"
    , "assets/img/backgrounds/3.jpg"
    ], {duration: 3000, fade: 750});

    /*
        Tooltips
    */
    $('.links a.home').tooltip();
    $('.links a.blog').tooltip();

    /*
        Form validation
    */
    $('.register form').submit(function(){
        $(this).find("label[for='username']").html('Username');
        $(this).find("label[for='password']").html('Password');
        ////
        
        var username = $(this).find('input#username').val();
        var password = $(this).find('input#password').val();
        
        if(username == '') {
            $(this).find("label[for='username']").append("<span style='display:none' class='red'> - Please enter a valid username.</span>");
            $(this).find("label[for='username'] span").fadeIn('medium');
            return false;
        }
        
        if(password == '') {
            $(this).find("label[for='password']").append("<span style='display:none' class='red'> - Please enter a valid password.</span>");
            $(this).find("label[for='password'] span").fadeIn('medium');
            return false;
        }
    });


});
</script>


</body>
</html>