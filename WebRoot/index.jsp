<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="zh-ch" class="screen-desktop-wide device-desktop">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="renderer" content="webkit">
<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">

<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
<title>游客虚拟体验系统</title>
<meta name="description" content="游客虚拟体验系统">

<link rel="stylesheet" href="./vesv_files/zui.min.css">
<link rel="stylesheet" href="./vesv_files/zui-theme.css">
<link rel="stylesheet" href="./vesv_files/redefine.css">


<script src="./vesv_files/hm.js"></script>
<script src="./vesv_files/hm(1).js"></script>
<script language="JavaScript" type="text/javascript" src="./vesv_files/jquery-1.9.1.js"></script>
<script language="JavaScript" type="text/javascript" src="./vesv_files/datetimepicker.js"></script>
<style id="style-1-cropbar-clipper">
.en-markup-crop-options {
    top: 18px !important;
    left: 50% !important;
    margin-left: -100px !important;
    width: 200px !important;
    border: 2px rgba(255,255,255,.38) solid !important;
    border-radius: 4px !important;
}
/* !important 用于在IE浏览器和IE以外的浏览器设置不同的样式，因为IE浏览器不能理解!important，而IE以外的浏览器可以，所以只要在后面加!important的样式在IE浏览器中不会起作用 */
.en-markup-crop-options div div:first-of-type {
    margin-left: 0px !important;
}
</style>
</head>

<body>
<header>
<style>
  @media screen and (max-width: 767px) {
    .mobile_nav_hide{
      display: none;
    }
  }
  /*@media 规则使你有能力在相同的样式列表中，使用不同的样式规则来针对不同的媒介（即例如：同一段内容再手机设备和电脑设备上现实的样式不同等）*/
</style>
<nav class="navbar navbar-default header_wrap" role="navigation" style="background-color:#4caf50">
  <div class="container">
    <div class="navbar-header">
      <!-- 移动设备上的导航切换按钮 -->
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse-example">
        <span class="sr-only">切换导航</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    <div class="collapse navbar-collapse navbar-collapse-example ">
	     <ul class="nav navbar-nav">
			    <li class="active"><a href="${pageContext.request.contextPath}"><strong>首页</strong></a></li>
			    <li><a href="#"><strong>发布</strong></a></li>
		        <li><a href="${pageContext.request.contextPath}/login.jsp"><strong>登录</strong></a></li>
		        <li><a href="${pageContext.request.contextPath}/regist.jsp"><strong>注册</strong></a></li>
	     </ul>

   		<!--  <ul class="nav navbar-nav navbar-right mobile_nav_hide">
	  		 <li>
			  <button style="margin-top:10px" type="button" class="btn btn-primary" onclick="javascript:window.location.href=&#39;/add/pic&#39;">发布</button>
			 </li>
       			 <li><a href="http://www.cnquanjing.com/passport/">登录</a></li>
			 <li><a href="http://www.cnquanjing.com/passport/register">注册</a></li>			
		 </ul> -->
    </div>
  </div>
</nav>
</header>

<style>
	body{
		background-color:#FFFFFF;
	}
	.inline.hidden-xs {
  		display: inline !important;
  	}
	.chosen_wrap{
	}
	.img_list{
		margin-bottom: 10px;
		cursor: pointer;
	}
	.title_cover{
		height: 30px;
		background-color: rgba(138,137,137,0.5);
		position: absolute;
		bottom: 0px;
		width: 100%;
		color: #fff;
		line-height: 30px;
		font-size: 14px;
		padding-left: 10px;
	}
	.more{
		margin-top: 10px;
		text-align: right;
		display: block;
	}
	.of_hide{
		display: block;
	    overflow: hidden;
	    text-overflow: ellipsis;
	}
	.padding0{
		padding: 0;
	}
	.cards .card .card-content span{
		width: 100%;
		height: 20px;
	}
	.content_nav{
		/*margin-top: 18px;*/
	}
	.content_nav .col-md-2 div{
		height: 60px;
		line-height: 60px;
		text-align: center;
		font-size: 23px;
		border-radius: 8px;
		cursor: pointer;
		color:#fff;
	}
	.content_nav .col-xs-4{
		margin-top: 10px;
	}
	.kf_block{
		background-color: #EA644A;
	}
	.gj_block{
		background-color: #F1A325;
	}
	.ly_block{
		background-color: #38B03F;
	}
	.jz_block{
		background-color: #03B8CF;
	}
	.jy_block{
		background-color: #BD7B46;
	}
	.xw_block{
		background-color: #8666B8;
	}
</style>
<script>
	$(function(){
		$("#top_frame").load(function() {
			console.log($(this).contents().find("body").height());
			$(this).height($(window).height()*0.7);
		})
	})
</script>
<div class="container">
   <div id="myNiceCarousel" class="carousel slide" data-ride="carousel">
  <!-- 圆点指示器 -->
  <ol class="carousel-indicators">
    <li data-target="#myNiceCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myNiceCarousel" data-slide-to="1"></li>
    <li data-target="#myNiceCarousel" data-slide-to="2"></li>
  </ol>

  <!-- 轮播项目 -->
  <div class="carousel-inner">
    <div class="item active">
      <img alt="First slide" src="http://zui.sexy/docs/img/slide1.jpg">
      <div class="carousel-caption">
        <h3>我是第一张幻灯片</h3>
      </div>
    </div>
    <div class="item">
      <img alt="Second slide" src="http://zui.sexy/docs/img/slide2.jpg">
      <div class="carousel-caption">
        <h3>我是第二张幻灯片</h3>
      </div>
    </div>
    <div class="item">
      <img alt="Third slide" src="http://zui.sexy/docs/img/slide3.jpg">
      <div class="carousel-caption">
        <h3>我是第三张幻灯片</h3>
        <p>last picture~</p>
      </div>
    </div>
  </div>

  <!-- 项目切换按钮 -->
  <a class="left carousel-control" href="#myNiceCarousel" data-slide="prev">
    <span class="icon icon-chevron-left"></span>
  </a>
  <a class="right carousel-control" href="#myNiceCarousel" data-slide="next">
    <span class="icon icon-chevron-right"></span>
  </a>
</div>


	<div class="row">
		<div class="col-xs-12">
			<h2 class="text-muted">景点欣赏<a href="http://www.cnquanjing.com/pictures"><small class="text-muted  pull-right more">更多&gt;&gt;</small></a></h2>
		</div>
	</div>
	<!--一个卡片列表行-->
	<div class="row">
		<div class="cards" style="margin:0;">
			<!--卡片列表循环-->
			 <div class="col-md-3 col-sm-3 col-xs-6 col-lg-3">
			   <div class="card" href="###">
			     <a target="_blank" href="${pageContext.request.contextPath}/panorama/qj_1.html"><img src="./vesv_files/thumb(8).jpg" alt="广东城市夜景"></a>
			     <div class="card-heading">
			     	<div class="col-md-9 col-xs-8 of_hide padding0">
			     		<strong class="text-primary">广东城市夜景</strong> 
			     	</div>
			     	<div class="col-md-3 col-xs-4 of_hide padding0">
			     		<div class="pull-right text-danger">
			     		<i class="icon-heart-empty"></i>
			     		 6469</div>
			     	</div>
			     </div>
			     <div class="card-content text-muted">
			     <span class="of_hide"></span>
			     </div>
			   </div>
			 </div>
			 <div class="col-md-3 col-sm-3 col-xs-6 col-lg-3">
			   <div class="card" href="###">
			     <a target="_blank" href="http://www.cnquanjing.com/tour/d37c0657e6082e0b"><img src="./vesv_files/thumb(9).jpg" alt="坊子区九龙涧生态科普教育基地VR全景"></a>
			     <div class="card-heading">
			     	<div class="col-md-9 col-xs-8 of_hide padding0">
			     		<strong class="text-primary">九龙涧自然风景区</strong> 
			     	</div>
			     	<div class="col-md-3 col-xs-4 of_hide padding0">
			     		<div class="pull-right text-danger"><i class="icon-heart-empty"></i> 1775</div>
			     	</div>
			     </div>
			     <div class="card-content text-muted">
			     <span class="of_hide">九龙涧自然风景区区域面积530多公顷。其中：林木覆盖面积200多公顷，水域面积40多公顷，耕地面积290多公顷。自然风景区为低山丘陵地形，山丘最高处海拔78.40米，最低处海拔30.60米，最大高差差近50米。地层分布为中生界侏罗系地层。 九龙涧自然风景区历史悠久，曾经一度辉煌的斟鄩故国就在位于九龙涧自然风景区南5公里的丁村一带。我国历史上的许多著名人物曾经在此隐居，所以以前古迹甚多，有仙姑洞、龙王庙、姜太公钓鱼亭、晏婴祠、公冶长赏鸟亭、苏东坡赏月亭、刘伯温饮酒亭、郑板桥竹林碑等，可惜这些古迹皆毁于不同朝代的战乱。</span>
			     </div>
			   </div>
			 </div>
			 <div class="col-md-3 col-sm-3 col-xs-6 col-lg-3">
			   <div class="card" href="###">
			     <a target="_blank" href="http://www.cnquanjing.com/tour/0e6c5c6ead8d62c7"><img src="./vesv_files/thumb(12).jpg" alt="深圳北站夜景"></a>
			     <div class="card-heading">
			     	<div class="col-md-9 col-xs-8 of_hide padding0">
			     		<strong class="text-primary">深圳北站夜景</strong> 
			     	</div>
			     	<div class="col-md-3 col-xs-4 of_hide padding0">
			     		<div class="pull-right text-danger"><i class="icon-heart-empty"></i> 2345</div>
			     	</div>
			     </div>
			     <div class="card-content text-muted">
			     <span class="of_hide">深圳北站是深圳目前唯一的特等站，站址在广东省深圳市宝安区龙华新区民治街道，占地240万平方米。深圳北站是深圳铁路“四主四辅”客运格局（四主”为深圳北站、深圳站、深圳机场东站（规划）、深圳坪山站，“四辅”为福田站、西丽站（规划）、深圳东站、平湖站。）最为核心的车站，也是深圳当前建设占地面积最大、接驳功能最为齐全的特大型综合铁路枢纽，成为我国铁路新型房站的标志性工程，是深圳市规模最大，设备技术最先进，客流量最大的火车站。</span>
			     </div>
			   </div>
			 </div>
			<div class="col-md-3 col-sm-3 col-xs-6 col-lg-3">
			   <div class="card" href="###">
			     <a target="_blank" href="http://www.cnquanjing.com/tour/417e556d3e25050c"><img src="./vesv_files/thumb(13).jpg" alt="襄阳小武当真武山"></a>
			     <div class="card-heading">
			     	<div class="col-md-9 col-xs-8 of_hide padding0">
			     		<strong class="text-primary">襄阳小武当真武山</strong> 
			     	</div>
			     	<div class="col-md-3 col-xs-4 of_hide padding0">
			     		<div class="pull-right text-danger"><i class="icon-heart-empty"></i> 2603</div>
			     	</div>
			     </div>
			     <div class="card-content text-muted">
			     <span class="of_hide"></span>
			     </div>
			   </div>
			 </div>
			 <div class="col-md-3 col-sm-3 col-xs-6 col-lg-3">
			   <div class="card" href="###">
			     <a target="_blank" href="http://www.cnquanjing.com/tour/b8a90ec5147f6925"><img src="./vesv_files/thumb(16).jpg" alt="襄阳新农村典范白云人家"></a>
			     <div class="card-heading">
			     	<div class="col-md-9 col-xs-8 of_hide padding0">
			     		<strong class="text-primary">襄阳白云人家</strong> 
			     	</div>
			     	<div class="col-md-3 col-xs-4 of_hide padding0">
			     		<div class="pull-right text-danger"><i class="icon-heart-empty"></i> 8194</div>
			     	</div>
			     </div>
			     <div class="card-content text-muted">
			     <span class="of_hide"></span>
			     </div>
			   </div>
			 </div>
			 <div class="col-md-3 col-sm-3 col-xs-6 col-lg-3">
			   <div class="card" href="###">
			     <a target="_blank" href="http://www.cnquanjing.com/tour/97d6e47bd48ed22b"><img src="./vesv_files/thumb(17).jpg" alt="北京国家体育场—鸟巢"></a>
			     <div class="card-heading">
			     	<div class="col-md-9 col-xs-8 of_hide padding0">
			     		<strong class="text-primary">北京国家体育场—鸟巢</strong> 
			     	</div>
			     	<div class="col-md-3 col-xs-4 of_hide padding0">
			     		<div class="pull-right text-danger"><i class="icon-heart-empty"></i> 2439</div>
			     	</div>
			     </div>
			     <div class="card-content text-muted">
			     <span class="of_hide">国家体育场—鸟巢位于北京奥林匹克公园中心区南部，为2008年北京奥运会的主体育场。工程总占地面积21公顷，场内观众坐席约为91000个。举行了奥运会、残奥会开闭幕式、田径比赛及足球比赛决赛。奥运会后成为北京市民参与体育活动及享受体育娱乐的大型专业场所，并成为地标性的体育建筑和奥运遗产。体育场由雅克·赫尔佐格、德梅隆、艾未未以及李兴刚等设计，由北京城建集团负责施工。体育场的形态如同孕育生命的“巢”和摇篮，寄托着人类对未来的希望。设计者们对这个场馆没有做任何多余的处理，把结构暴露在外，因而自然形成了建筑的外观。</span>
			     </div>
			   </div>
			 </div>
			 <div class="col-md-3 col-sm-3 col-xs-6 col-lg-3">
			   <div class="card" href="###">
			     <a target="_blank" href="http://www.cnquanjing.com/tour/fd35f4bf3fb651f6"><img src="./vesv_files/thumb(18).jpg" alt="菏泽曹州牡丹园"></a>
			     <div class="card-heading">
			     	<div class="col-md-9 col-xs-8 of_hide padding0">
			     		<strong class="text-primary">菏泽牡丹园</strong> 
			     	</div>
			     	<div class="col-md-3 col-xs-4 of_hide padding0">
			     		<div class="pull-right text-danger"><i class="icon-heart-empty"></i> 11575</div>
			     	</div>
			     </div>
			     <div class="card-content text-muted">
			     <span class="of_hide">牡丹园位于菏泽市人民北路，东临中国林展馆，是世界上面积最大的牡丹观赏园。它是以明清以来赵氏园、桑篱园、桂陵园等十几处牡丹园林为基础，于1982年修建。该园占地近2000亩，1100多个品种，其中百年以上株龄的古牡丹一百余株。</span>
			     </div>
			   </div>
			 </div>
			 <div class="col-md-3 col-sm-3 col-xs-6 col-lg-3">
			   <div class="card" href="###">
			     <a target="_blank" href="http://www.cnquanjing.com/tour/4375afe07f63cbf5"><img src="./vesv_files/thumb(19).jpg" alt="深圳欢乐谷VR全景"></a>
			     <div class="card-heading">
			     	<div class="col-md-9 col-xs-8 of_hide padding0">
			     		<strong class="text-primary">深圳欢乐谷</strong> 
			     	</div>
			     	<div class="col-md-3 col-xs-4 of_hide padding0">
			     		<div class="pull-right text-danger"><i class="icon-heart-empty"></i> 108436</div>
			     	</div>
			     </div>
			     <div class="card-content text-muted">
			     <span class="of_hide">深圳欢乐谷是华侨城集团新一代大型主题乐园，首批国家AAAAA级旅游景区，占地面积35万平方米，总投资20亿元人民币，是一座融参与性、观赏性、娱乐性、趣味性于一体的中国现代主题乐园。1998年开业以来，深圳欢乐谷经过五期的滚动发展，已成为国内投资规模最大、设施最先进的现代乐园。十四年来，深圳欢乐谷共接待海内外游客3000多万人，入园人数连续8年居国内第一，并连续四年荣膺亚太十大主题公园，成为中国主题公园行业的领跑者。</span>
			     </div>
			   </div>
			 </div>
				         
			<!--卡片列表循环结束-->	
		</div>
	</div>
	<!--一个卡片列表行结束-->
</div>


<style type="text/css">
.footer {
        height: 75px;
        font-size: 12px;
        clear: both;
        display: none
}
.footer ul {
	height: 75px;
	line-height: 75px;
}
.footer-logo {
	height: 75px;
	line-height: 75px;
}

.cooperative p{
	margin: 0;
}
.cooperative p:nth-child(1) {
	font-size: 12px;
}
.cooperative p:nth-child(2) {
	font-size: 9px;
}

.ab{
	position: absolute;
	bottom: 0;
}
@media (max-width:470px) {
.footer{ position: relative;height: 94px;}
.footer ul { margin-left: 5px;width: 200px;}
.footer ul li a { padding: 5px 2px;}
.footer-logo>a>img{ vertical-align: 14px;}
.footer-nav>li{ line-height: 50px;}
.cooperative>p>a>img{ width: 61px}
.cooperative{ width:100%;position: absolute;top: 8px;right: 7px;}
.hz{ text-align: center;}
.bq{ text-align: center;}

}
</style>
<footer class="footer" style="background-color:#4caf50">
	 <div class="center-block" style="width:320px;">
	   <P class="bq"></P>
	   <br/><br/>
	   <p class="bq">Copyright©2017 游客虚拟体验系统 All Rights Reserved </p>
	 </div>
</footer>
<script language="JavaScript" type="text/javascript" src="./vesv_files/jquery.form.js"></script>
<script language="JavaScript" type="text/javascript" src="./vesv_files/bootbox.js"></script> 
<script language="JavaScript" type="text/javascript" src="./vesv_files/pager.js"></script> 
<script language="JavaScript" type="text/javascript" src="./vesv_files/common.js"></script>
<script language="JavaScript" type="text/javascript" src="./vesv_files/zui.js"></script>
<script>
	var f_resize_time;
	window.onload = function (){ 
		f_resize_time = setTimeout(resizeFooter,1000);
		// $(window).bind("resize",function(){
		// 	$("footer").hide();
		// 	if(f_resize_time) clearTimeout(f_resize_time);
		// 	f_resize_time = setTimeout(resizeFooter,100);
		// })
		$(document).bind("resize",function(){
			$("footer").hide();
			if(f_resize_time) clearTimeout(f_resize_time);
			f_resize_time = setTimeout(resizeFooter,100);
		})
	} 
	function resizeFooter(){
		if ($(window).height()>$(document.body).height()) {
			$("footer").addClass("ab").show();
		}else{
			$("footer").removeClass("ab").show();
		}
	}
</script>
</body>
</html>