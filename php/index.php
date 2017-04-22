<!DOCTYPE html>
<html>
<head lang="en">
  <meta charset="UTF-8">
  <title>桂电成绩和课表查询</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="../assets/i/logo.png">
  <link rel="stylesheet" href="../assets/css/amazeui.min.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
    .footer{
 position: static;
bottom: 20px;
left: 0;
text-align: center;
width: 100%;
height: auto;
font-size: 15px;
color: #1E90FF;
      }
  </style>
  <script>
    function to_register(){
        window.location.href = "http://www.chensihang.com/register.html"
    }
    </script>
</head>
<body>
<div class="header">
  <div class="am-sans-serif">
    <h1>桂电本科教务查分</h1>
  </div>
  <hr />
</div>
<div class="am-sans-serif">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
    <br>
    <br>
    
    <ul class="am-nav am-nav-tabs">
        <li class="am-active"><a href="#">教务查分</a></li>
    </ul>
    
    <br /><br />
    
    <div class="am-panel am-panel-primary">
        <div class="am-panel-hd">教务处成绩查询</div>
        <div class="am-panel-bd">
            <form method="post" class="am-form" action="jwc.php">
                <label for="id">学号:</label>
                <input type="text" name="zjh" id="email" value="">
                <br>
                <label for="password">密码:</label>
                <input type="password" name="mm" id="password" value="">
                <br>
                <div class="am-form-group am-sans-serif">
                    <label for="doc-select-1">查询内容</label>
                    <select id="doc-select-1" name="scorechecktype">
                        <option value="kebiao">本学期课表查询</option>
                        <option value="chenji">所有成绩查询</option>
                    </select>
                    <span class="am-form-caret"></span>
                </div>
                <br />
                <div class="am-cf">
                    <input type="submit" name="login_submit" value="点我查分^_^" class="am-btn am-btn-primary am-center">
                </div>
            </form>
        </div>
    </div>

    
      
      
      
    <hr>
  </div>
</div>
    <!-- 多说评论框 start -->
	<div class="ds-thread" data-thread-key="123" data-title="桂电成绩和课表查询" data-url="http://guete.sinaapp.com/"></div>
<!-- 多说评论框 end -->
<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
<script type="text/javascript">
var duoshuoQuery = {short_name:"guet"};
	(function() {
		var ds = document.createElement('script');
		ds.type = 'text/javascript';ds.async = true;
		ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
		ds.charset = 'UTF-8';
		(document.getElementsByTagName('head')[0] 
		 || document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
	</script>

<h3 class="am-u-lg-6 am-u-md-8 am-u-sm-centered am-sans-serif">P.S.   此成绩查询系统仅针对桂林电子科技大学^_^</h3>
    <div class="footer">Copyright © 2015 | Powered By Yiwen</div>
</body>
</html>
