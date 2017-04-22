
            

<?php

include_once('simple_html_dom.php');
include'guet.class.php';
//header("content-Type: text/html; charset=Utf-8"); 
$no='1100820211';
 $a=new guet($no,$no);
$b=$a->getchengji();
echo $b;
 
?>