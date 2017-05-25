# 桂林电子科技大学本科教务课表查询

### 有两个版本，php版和Android客户端版，均实现教务系统模拟登陆，课表查询及其他相关查询
***
#### 1.php语言编写（[php版](php "php")）
模拟登陆，核心其实在如何获取cookie及用所获取cookie post相关数据或者去get请求
*  通过跳转地址判断账号密码是否正确
```php
 function checkid()//判断账号密码是否正确
    {
        $username=$this->username;
        $password=$this->password;
        $login_url='http://bkjw.guet.edu.cn/student/public/login.asp?mCode=000703';
        $post_fields='username='.$username.'&passwd='.$password.'&login= ǡ ¼';
        $ch = curl_init($login_url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HEADER, true);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $post_fields);
        $header = curl_exec($ch);
        curl_close($ch);
        //echo $header;
        $needle ='menu.asp?menu=mnall.asp';
        $tmparray = explode($needle,$header);
        if(count($tmparray)>1){
         return true;
        //echo $a=1;
         } else{
        return false;
        //echo$a=0;
               }
    }
```

* 通过正则表达式获取cookie
  
```php
    function getcookie()//获取cookie
    {
        $username=$this->username;
        $password=$this->password;
        $login_url='http://bkjw.guet.edu.cn/student/public/login.asp?mCode=000703';
        $post_fields='username='.$username.'&passwd='.$password.'&login=�ǡ�¼';
        $ch = curl_init($login_url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HEADER, true);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $post_fields);
        $res = curl_exec($ch);
        curl_close($ch);
        preg_match_all("/Set-Cookie: (.*?)=(.*?);/", $res, $match);
        $cookie = '';
        for ($i=0; $i < count($match[ 0 ]); $i++){
        $cookie .= ($match[ 1 ][ $i ] . '=' . $match[ 2 ][ $i ] . '; ');
    }
        return $cookie;
    }
```
***
#### 2.java语言编写（[Android客户端](GUET "GUET")）
##### 下载地址：点击下载[客户端](Android "Android")

   

  