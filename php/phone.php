<?php
function phone($phone)
{
 $login_url='http://ruku.cc/index/index/action/token/aef7afef431f4948'; 
        $ch = curl_init($login_url);
// curl_setopt($ch, CURLOPT_POST, false);
        curl_setopt($ch, CURLOPT_HEADER, true);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//curl_setopt($ch, CURLOPT_POSTFIELDS, $post_fields);
        $header = curl_exec($ch);
        curl_close($ch);
//echo $header;
preg_match_all("/Set-Cookie: (.*?)=(.*?);/", $header, $match);
// echo $match;
    $cookie = '';
    for ($i=0; $i < count($match[ 0 ]); $i++){
        $cookie .= ($match[ 1 ][ $i ] . '=' . $match[ 2 ][ $i ] . '; ');}
       $post_fields='phone='.$phone.'&token=aef7afef431f4948&tag=WEIWEI';
       $url1='http://ruku.cc/Index/Api/call.html';
       $ch1 = curl_init();
       curl_setopt($ch1, CURLOPT_URL, $url1);
       curl_setopt($ch1, CURLOPT_RETURNTRANSFER, true);
       curl_setopt($ch1, CURLOPT_POST, true);
       curl_setopt($ch1, CURLOPT_COOKIE, $cookie);
       curl_setopt($ch1, CURLOPT_POSTFIELDS, $post_fields);
       $table = curl_exec($ch1);
       curl_close($ch1);
    //echo  $table;
    return true;

}
echo phone('13977543115');

?>