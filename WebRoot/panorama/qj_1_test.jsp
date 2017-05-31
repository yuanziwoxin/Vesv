<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>panorama</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
	    <link rel="stylesheet" type="text/css" href="./../vr_lib/style1.3.27.6.css">
	    <meta name="renderer" content="webkit">
		<meta http-equiv="x-ua-compatible" content="IE=edge,chrome=1">
	    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
	    <meta name="apple-mobile-web-app-capable" content="yes">
	    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	    <meta content="no-cache, no-store, must-revalidate" http-equiv="Cache-Control">
	    <meta content="no-cache" http-equiv="Pragma">
	    <meta content="0" http-equiv="Expires">
	    
	    <link rel="stylesheet" href="./../vr_lib/redefine.css">
	    <link rel="stylesheet" href="./../vr_lib/alivideo.css">
	    
	   	<script language="JavaScript" type="text/javascript" src="./../vr_lib/uhweb.js"></script> 
	    <script language="JavaScript" type="text/javascript" src="./../vr_lib/vrshow.js"></script> 
	    <script src="./../vr_lib/jweixin-1.0.0.js"></script>

	    <script language="JavaScript" type="text/javascript" src="./../js/jquery.js"></script>
	    <link href="./../vr_lib/zui.min.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/zui-theme.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/fileinput.min.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/bootstrap-switch.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/chosen.min.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/zui.datatable.min.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/response.css" type="text/css" rel="stylesheet">
	    
	    

	    <style>
	        @-ms-viewport { width:device-width; }
	        @media only screen and (min-device-width:800px) { html { overflow:hidden; } }
	        html { height:100%; }
	        body { height:100%; overflow:hidden; margin:0; padding:0; font-family:microsoft yahei, Helvetica, sans-serif;  background-color:#000000; }
	    </style>
	    <style id="style-1-cropbar-clipper">
	    .en-markup-crop-options {
	        top: 18px !important;
	        left: 50% !important;
	        margin-left: -100px !important;
	        width: 200px !important;
	        border: 2px rgba(255,255,255,.38) solid !important;
	        border-radius: 4px !important;
	    }
	    .en-markup-crop-options div div:first-of-type {
	        margin-left: 0px !important;
	    }
	    </style>



		<style>
			body {
				background-color: #000000;
				margin: 0;
				cursor: move;
				overflow: hidden;
			}

			a {
				color: #ffffff;
			}

			#info {
				position: absolute;
				width: 100%;
				color: #ffffff;
				padding: 5px;
				font-family: Monospace;
				font-size: 13px;
				font-weight: bold;
				text-align: center;
				z-index: -1;
			}

/*(1)z-index 属性设置元素的堆叠顺序。拥有更高堆叠顺序的元素总是会处于堆叠顺序较低的元素的前面。
(2)元素可拥有负的 z-index 属性值。
(3)z-index 仅能在定位元素上奏效!（例如 position:absolute;）
(4)该属性设置一个定位元素沿z轴的位置，z轴定义为垂直延伸到显示区的轴。
   如果为正数，则离用户更近，为负数则表示离用户更远。
(5)z-index 可用于将在一个元素放置于另一元素之后。
*/
		</style>
		<script type="text/javascript" src="http://www.imooc.com/data/jquery-1.8.2.min.js"></script>
	</head>
	<body>
	<div id="content">
        <div class="vrshow_container_2_min">	
  			<div class="img_desc_container_min" id="shareDiv">
			   <img src="./../static/images/skin1/vr-btn-share.png" data-toggle="modal" data-target="#shareModal"/>
			   <!--  <img src="./../static/images/skin1/vr-btn-share.png" onclick="shareTest()"/> -->
			    <div class="img_desc_min">分享</div>
			</div>

			<div class="img_desc_container_min" id="profileDiv">
			    <img src="./../static/images/skin1/vr-btn-desc.png" data-toggle="modal" data-target="#profileModal">
			    <div class="img_desc_min">简介</div>
			</div>
		</div>

			
			
			<!-- <script src="./../vr_lib/comment.js"></script> -->
                                               
			
		
      
     <!--  模态框 -->
        <!-- 分享网页 -->
          <!-- 
           <div class="modal" id="shareModal" data-backdrop="static" data-keyboard="false" style="z-index:2002">
    <div class="modal-dialog" style="height:350px;">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
            <span style="color: #353535;">用手机扫描二维码分享给你的好友</span>
        </div>
        <div class="modal-body">
            <div class="center-block" style="text-align: center">
                <img id="qrcode" src="./2017春节广东航拍720_files/loading.gif" width="226px" height="226px">
            </div>
        </div>
    </div>
</div> -->
	
	  </div>
	  
	  <div class="modal" id="shareModal" tabindex="-1" role="dialog" aria-labelledby="shareModalLabel" aria-hidden="true">
				<div class="modal-dialog" >
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="shareModalTitle">
								美好总是从分享开始
							</h4>
						</div>
						<div class="modal-body">
							<div id="share-2"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div> 
		  <!--  <script type="text/javascript">
	           function shareModal()
	           {
	        	   //alert('hek');
	        	   $('#shareModal').modal("show");
	           }
	           shareModal();
           </script> -->
          <!--  
           <script type="text/javascript">
          	function shareTest()
          	{
          	  document.getElementById('shareModal').style.display='block';
          	}
          </script> -->
           
	       	<!-- 网页分享到主流社交平台-->
	       	<script src="http://apps.bdimg.com/libs/jquery/1.8.2/jquery.js"></script>
		    <link rel="stylesheet" href="./../share/dist/css/share.min.css">
			<script src="./../share/dist/js/jquery.share.min.js"></script>
	        <script>
			$('#share-1').share();
			$('#share-2').share({sites: ['qzone', 'qq', 'weibo','wechat']});
			$('#share-3').share();
			$('#share-4').share();
			</script>
		<!-- 分享网页 -->

		<!-- 简介 -->
        	<div class="modal fade" id="profileModal" tabindex="-1" role="dialog" aria-labelledby="profileModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								&times;
							</button>
							<h4 class="modal-title" id="profileTitle">
								广东城市风光
							</h4>
						</div>
						<div class="modal-body">
							<p>城市是实现人民美好生活向往的重要载体。改革开放以来，广东实现了城市化的超常规发展，用30多年的时间走过了许多发达国家更长时间才走完的城市化历程。这30多年的广东城市发展就犹如变奏曲一般，既有一以贯之的主题、节奏，又有各个地方的特色装饰、自由发挥，昂扬向上，引人入胜。</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default" data-dismiss="modal">关闭
							</button>
						</div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal -->
			</div>
	        
	  
	</body>
</html>
