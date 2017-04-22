<?php

function randmobile()
{
  $numberPlace = array(30,31,32,33,34,35,36,37,38,39,50,51,58,59,89);
  $mobile = 1;
  $mobile .= $numberPlace[rand(0,count($numberPlace)-1)];
  $mobile .= str_pad(rand(0,99999999),8,0,STR_PAD_LEFT);
  $result = $mobile;
  return $result;
}
$aa= randmobile();
echo $aa;
 
?>