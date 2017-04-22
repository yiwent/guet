<!DOCTYPE html>
<html>
<body
<?php
$login_url='http://ruku.cc/index/index/action/token/aef7afef431f4948'; 
        $ch = curl_init($login_url);
        curl_setopt($ch, CURLOPT_POST, false);
        curl_setopt($ch, CURLOPT_HEADER, true);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//curl_setopt($ch, CURLOPT_POSTFIELDS, $post_fields);
        $header = curl_exec($ch);
        curl_close($ch);
echo $header;
preg_match_all("/Set-Cookie: (.*?)=(.*?);/", $header, $match);
//echo $match;

    $cookie = '';
    for ($i=0; $i < count($match[ 0 ]); $i++){
        $cookie .= ($match[ 1 ][ $i ] . '=' . $match[ 2 ][ $i ] . '; ');}
//echo $cookie;
       $post_fields='phone=13977543112&token=aef7afef431f4948&tag=WEIWEI';
       $url='http://ruku.cc/index/index/action/token/aef7afef431f4948';
//*   $ch = curl_init();
//    curl_setopt($ch, CURLOPT_URL, $url);
//    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
//       curl_setopt($ch, CURLOPT_POST, true);
//       curl_setopt($ch, CURLOPT_COOKIE, $cookie);
//       curl_setopt($ch, CURLOPT_POSTFIELDS, $post_fields);
//       $table = curl_exec($ch);      curl_close($ch);
echo $table=vcurl($url,$post_fields,$cookie,$cookiejar,$url);
function vcurl($url, $post = '', $cookie = '', $cookiejar = '', $referer = ''){ 
$tmpInfo = ''; 
$cookiepath = getcwd().'./'.$cookiejar; 
$curl = curl_init(); 
curl_setopt($curl, CURLOPT_URL, $url); 
curl_setopt($curl, CURLOPT_USERAGENT, $_SERVER['HTTP_USER_AGENT']); 
if($referer) { 
curl_setopt($curl, CURLOPT_REFERER, $referer); 
} else { 
curl_setopt($curl, CURLOPT_AUTOREFERER, 1); 
} 
if($post) { 
curl_setopt($curl, CURLOPT_POST, 1); 
curl_setopt($curl, CURLOPT_POSTFIELDS, $post); 
} 
if($cookie) { 
curl_setopt($curl, CURLOPT_COOKIE, $cookie); 
} 
if($cookiejar) { 
curl_setopt($curl, CURLOPT_COOKIEJAR, $cookiepath); 
curl_setopt($curl, CURLOPT_COOKIEFILE, $cookiepath); 
} 
//curl_setopt($curl, CURLOPT_FOLLOWLOCATION, 1); 
curl_setopt($curl, CURLOPT_TIMEOUT, 100); 
curl_setopt($curl, CURLOPT_HEADER, 0); 
curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1); 
$tmpInfo = curl_exec($curl); 
if (curl_errno($curl)) { 
echo '<pre><b>错误:</b><br />'.curl_error($curl); 
} 
curl_close($curl); 
return $tmpInfo; 
} 
     
?>
</body>
</html>