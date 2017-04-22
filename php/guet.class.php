<?php
include_once('simple_html_dom.php');
class guet {
private $username;//用户名
private $password;//密码
function __construct($username,$password){
$this->username=$username;
$this->password=$password;

}
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
        // $a=1;
         } else{
        return false;
        //$a=0;
               }
    }

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
    function getdata($url1)
    {
        $cookie=$this->getcookie();
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url1);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_COOKIE, $cookie);
        $res1 = curl_exec($ch);
        curl_close($ch);
        return $res1;
    }
    function getdata1($url2,$post_fields1)
    {
       $cookie=$this->getcookie();
       $url3= $url2;
       $ch = curl_init();
       curl_setopt($ch, CURLOPT_URL, $url3);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_COOKIE, $cookie);
       curl_setopt($ch, CURLOPT_POSTFIELDS, $post_fields1);
       $table = curl_exec($ch);
       curl_close($ch);
        return $table;
    }
    function getinfo()//学生信息
    {
        $url='http://bkjw.guet.edu.cn/student/Info.asp';
        $info=$this->getdata($url);
        return $info;
    }
    function getkebiao()//课表
    {
       $cookie=$this->getcookie();
       $post_fields1='term=2015-2016_1';
       $url1='http://bkjw.guet.edu.cn/student/coursetable.asp';
       $ch = curl_init();
       curl_setopt($ch, CURLOPT_URL, $url1);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_COOKIE, $cookie);
       curl_setopt($ch, CURLOPT_POSTFIELDS, $post_fields1);
       $table = curl_exec($ch);
       curl_close($ch);
        //return $table;
        $contents = mb_convert_encoding($table, "utf8", "gb2312");
    $htmlparser = str_get_html($contents);
    $body_array = $htmlparser->find('table[border=1]');
        // $htmlparser->clear();
    return $body_array[0];
    }
    function getchengji()//成绩
    {
       $cookie=$this->getcookie();
       $post_fields1='lwBtnquery=��ѯ&lwPageSize=1000';
       $url1='http://bkjw.guet.edu.cn/student/Score.asp';
       $ch = curl_init();
       curl_setopt($ch, CURLOPT_URL, $url1);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_COOKIE, $cookie);
       curl_setopt($ch, CURLOPT_POSTFIELDS, $post_fields1);
       $table = curl_exec($ch);
       curl_close($ch);
       $contents = mb_convert_encoding($table, "utf8", "gb2312");
       $htmlparser = str_get_html($contents);
       $body_array = $htmlparser->find('table[border=1]');
        // $htmlparser->clear();
       return $body_array[0];
    }
    function getnotpass()//不及格
    {
        $cookie=$this->getcookie();
        $url1='http://bkjw.guet.edu.cn/student/notpass.asp';
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url1);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_COOKIE, $cookie);
        $res1 = curl_exec($ch);
        curl_close($ch);
        return $res1;
    }
    function getname()
    {
    $b=$this->getinfo();
    $name_contents = mb_convert_encoding($b, "utf8", "gb2312");
    $htmlparser1 = str_get_html($name_contents);
    $name_array = $htmlparser1->find('p');
    $name_array1 = array();
    for($i = 0; $i < count($name_array); $i ++)
    {
        $temp = $name_array[$i]->plaintext;
        $name_array1[$i] = $temp;
    }
        
    $htmlparser1->clear();
        // echo $name_array1[0];//学号
        // echo $name_array1[1];//姓名
        // echo $name_array1[2];//班级
       // echo $name_array1[3];//年级 学期
   return $str1 = substr($name_array1[1],7);
    }
    
}
?>