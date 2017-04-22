<?php session_start(); ?>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>成绩结果</title>
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
    </style>
    <script>
    function to_register(){
        window.location.href = "./register.php"
    }
    </script>
</head>
<body>
    <div class="header">
        <div class="am-sans-serif">
            <h1>成绩结果</h1>
        </div>
        <hr />
    </div>
    <div class="am-sans-serif">
        <div class="am-u-lg-8 am-u-md-8 am-u-sm-centered">
            <br>
            <br>
            
            <ul class="am-nav am-nav-tabs">
     
                <li><a href="/index.php">成绩查询</a></li>
                <li class="am-active"><a href="#">查询结果</a></li>
            </ul>
            
            <br /><br />
            

<?php

include_once('simple_html_dom.php');
include'guet.class.php';
/*set the option of the curl function */
$zjh = $_POST['zjh'];
$mm = $_POST['mm'];

$scorechecktype = $_POST['scorechecktype'];

//$cookie = getCookie($zjh, $mm);
$a=new guet($zjh,$mm);
if(!$a->checkid())
{
        echo "<script>alert('输入账号或者密码错误！');</script>";
        echo "<script>window.location.href='/index.php'</script>";
        exit;
}
else
{
    if($scorechecktype == 'kebiao')
    {
     echo '<div class="am-sans-serif">';
     echo $a->getkebiao();
     echo '</div>';
     echo '</body></html>';;
    }
else
  {
    echo '<div class="am-sans-serif">';
   echo $a->getchengji();
    echo '</div>';
   echo '</body></html>';
  }
}

//unlink($cookie);
?>