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
	    
	    <!-- <script language="JavaScript" type="text/javascript" src="./../vr_lib/uhweb.js"></script> 
	    <script language="JavaScript" type="text/javascript" src="./../vr_lib/vrshow.js"></script> 
	    <script src="./../vr_lib/jweixin-1.0.0.js"></script>  -->

	    <script language="JavaScript" type="text/javascript" src="./../js/jquery.js"></script>
	    <link href="./../vr_lib/zui.min.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/zui-theme.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/fileinput.min.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/bootstrap-switch.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/chosen.min.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/zui.datatable.min.css" type="text/css" rel="stylesheet">
	    <link href="./../vr_lib/response.css" type="text/css" rel="stylesheet">
	    
	     <script language="JavaScript" type="text/javascript" src="./../js/zui.min.js"></script>

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

		/**********总体布局**************/
		*{margin:0; padding:0; border:0;}
		/*body{background-color:rgba(0,0,0,0.4);}*/
		#content{position:absolute; width:100%; height:100%; margin:0px auto; border:0px solid #FFF;}
		#content:hover #tags img{opacity:1;}

		/**********图片主要显示区**************/
		#player{position:absolute; width:100%; height:100%; top:0; left:0;}
		#player img{display:inline-block; position:absolute; float:left; width:100%; height:100%; top:0;}

		/**********图片显示标签**************/
		#tags{position:absolute; left:30px; bottom:90px;z-index: 1}
		#tags img{display:inline-block; width:120px; height:100px; float:left; margin-right:10px; transition:1s; opacity:0.5;border: 1px solid #ffffff;}
		#tags img:hover{transform:scale(1.1); cursor:pointer;}
		</style>
		<script type="text/javascript" src="http://www.imooc.com/data/jquery-1.8.2.min.js"></script>
		<script type="text/javascript">
		    $(function(){
				var same=0;					//用于避免触碰到相同标签式是发生切换			
				var interval=5000;			//图片切换时间
				var timer;					//计时器
				
				/*********显示和隐藏图片************/
				$("#player img").hide();
				$("#player img").eq(0).show();
				$("#tags img").click(function(){
					var tags=$("#tags img").index(this);
					if(same!=tags){
						$("#player img").fadeOut();
						$("#player img").eq(tags).fadeIn(500);	
					}
					same=tags;
				})
				
				/*********自动播放图片***********/
				function autoplay(){
					same++;
					if(same>=$("#tags img").length){
						same=0;	
					}
					$("#player img").fadeOut();
					$("#player img").eq(same).fadeIn(500);	
				}
				
				/**********播放图片切换**************/
				function play(){
					timer=setInterval(autoplay,interval);	
				}
				
				/**********停止图片切换**************/
				function stop(){
					clearInterval(timer);	
				}
				
				/*****鼠标悬浮或移出时播放或停止切换图片***/
				$("#content").hover(function(){
					stop();
				},
				function(){
					play();	
				})
				
				play();
			})
		</script>


	</head>
	<body>
	<div id="content">
	  <!--主显示区-->
    	<div id="player">
		<script src="./../js/three.js"></script>
		<script src="./../js/CSS3DRenderer.js"></script>

		<script>
			//定义相机、场景、渲染器，这是是3D场景形成的三大要素。
			var camera, scene, renderer;
			//定义几何体，材质，以及几何体加材质之后形成的网格
			var geometry, material, mesh;
			//生成三维向量（0,0,0），相机的目标点,即位于坐标的原点
			var target = new THREE.Vector3();
			//lon 即longitude的缩写，表示经度，竖着的（有东经和西经的说法），lat 即latitude的缩写，表示纬度，横着的（有南纬和北纬的说法）；该经纬度表示相机的聚焦点，初始状态在前面。
			var lon = 90, lat = 0;
			//也是相机的聚焦点，上面的是角度，此处转化为弧度制。
			var phi = 0, theta = 0;
			//移动端用户输入的坐标x,y
			var touchX, touchY;

			init();
			animate();

			function init() {
				//相机默认的位置在坐标系的原点位置

				//Three.js的相机表示是THREE.Camera,它是相机的基类，其子类有两种相机，分别是正（交）投影相机THREE.OrthographicCamera和透视投影摄像机THREE.PerpectiveCamera.所谓正投影的特点是：远近高低的比例都相同，而透视投影有一个基本点，就是远处的物体比近处的小（也就是通常说的“近大远小”），而透视投影更符合我们的视觉。第一个属性是指视角，第二个属性是宽高比，第三个是近处的裁剪面的距离，第四个是远处的裁剪面的距离。比第三个属性值小的部分和比第四个属性值大的部分都不会渲染。
				camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 1, 1000 );
				// 创建场景
				scene = new THREE.Scene();

				/** 
				* 这个sides对应的是六张图位于立体坐标轴内的位置，里面的position又包含x,y,三个轴
 				* 然后ratation是三个轴上的变换
 				*/ 
 				//右手坐标系，z轴正向指向观察者，即相机。下面是使六个面拼接成立方体，分别对应
				var sides = [
					{
						//右手坐标系，且以人站在原点向z轴正方向看的参考系为参考，以下几个面可以分别对应：
						//左面
						url: './../textures/qj_1_1/posx.jpg',
						position: [ -512, 0, 0 ],
						rotation: [ 0, Math.PI / 2, 0 ]
					},
					{
						//右面
						url: './../textures/qj_1_1/negx.jpg',
						position: [ 512, 0, 0 ],
						rotation: [ 0, -Math.PI / 2, 0 ]
					},
					{
						//上面
						url: './../textures/qj_1_1/posy.jpg',
						position: [ 0,  512, 0 ],
						rotation: [ Math.PI / 2, 0, Math.PI ]
					},
					{
						//下面
						url: './../textures/qj_1_1/negy.jpg',
						position: [ 0, -512, 0 ],
						rotation: [ - Math.PI / 2, 0, Math.PI ]
					},
					{
						//前面
						url: './../textures/qj_1_1/posz.jpg',
						position: [ 0, 0,  512 ],
						rotation: [ 0, Math.PI, 0 ]
					},
					{
						//后面
						url: './../textures/qj_1_1/negz.jpg',
						position: [ 0, 0, -512 ],
						rotation: [ 0, 0, 0 ]
					}
				];
				//将六个面的图片添加到场景中
				for ( var i = 0; i < sides.length; i ++ ) {

					var side = sides[ i ];

					var element = document.createElement( 'img' );
					element.width = 1026; // 多余的2个像素用来弥补差距
					element.src = side.url;
					//CSS3DObject是object3D拓展而得到的方法。
					var object = new THREE.CSS3DObject( element );
					object.position.fromArray( side.position );
					object.rotation.fromArray( side.rotation );
					scene.add( object );

				}
				//创建渲染器，这里的CSS3DRenderer方法也是拓展出来的方法
				renderer = new THREE.CSS3DRenderer();
				renderer.setSize( window.innerWidth, window.innerHeight );
				document.body.appendChild( renderer.domElement );

				//添加鼠标，手势，窗口事件

				document.addEventListener( 'mousedown', onDocumentMouseDown, false );
				document.addEventListener( 'wheel', onDocumentMouseWheel, false );

				document.addEventListener( 'touchstart', onDocumentTouchStart, false );
				document.addEventListener( 'touchmove', onDocumentTouchMove, false );

				window.addEventListener( 'resize', onWindowResize, false );

			}

			function onWindowResize() {
				//窗口缩放的时候，使得场景也跟随着一起缩放
				camera.aspect = window.innerWidth / window.innerHeight;
				camera.updateProjectionMatrix();

				renderer.setSize( window.innerWidth, window.innerHeight );

			}

			function onDocumentMouseDown( event ) {

				event.preventDefault();
				//保证监听拖拽事件
				document.addEventListener( 'mousemove', onDocumentMouseMove, false );
				document.addEventListener( 'mouseup', onDocumentMouseUp, false );

			}

			function onDocumentMouseMove( event ) {
				//鼠标移动的距离为：currenEvent.movementX=currentEvent.screenX-previousEvent.screenX
				var movementX = event.movementX || event.mozMovementX || event.webkitMovementX || 0;
				var movementY = event.movementY || event.mozMovementY || event.webkitMovementY || 0;
				//???
				lon -= movementX * 0.1;
				lat += movementY * 0.1;

			}

			function onDocumentMouseUp( event ) {
				//当鼠标在上面时即不再在面上拖拽时，则移除刚刚设置的监听事件；
				document.removeEventListener( 'mousemove', onDocumentMouseMove );
				document.removeEventListener( 'mouseup', onDocumentMouseUp );

			}

			function onDocumentMouseWheel( event ) {
				//相机的视觉随着鼠标滚动的距离拉近或远离
				camera.fov += event.deltaY * 0.05;
				camera.updateProjectionMatrix();

			}

			function onDocumentTouchStart( event ) {

				event.preventDefault();
				//移动端没有movement，但可以直接用touchX,touchY去计算移动的距离。
				var touch = event.touches[ 0 ];

				touchX = touch.screenX;
				touchY = touch.screenY;

			}

			function onDocumentTouchMove( event ) {

				event.preventDefault();

				var touch = event.touches[ 0 ];

				lon -= ( touch.screenX - touchX ) * 0.1;
				lat += ( touch.screenY - touchY ) * 0.1;

				touchX = touch.screenX;
				touchY = touch.screenY;

			}
			//开启动画
			function animate() {

				requestAnimationFrame( animate );

				lon +=  0.1;
				lat = Math.max( - 85, Math.min( 85, lat ) );
				//角度转化为弧度制
				phi = THREE.Math.degToRad( 90 - lat );
				theta = THREE.Math.degToRad( lon );
				//在球坐标系中算出相机的聚集点的坐标。
				target.x = Math.sin( phi ) * Math.cos( theta );
				target.y = Math.cos( phi );
				target.z = Math.sin( phi ) * Math.sin( theta );

				camera.lookAt( target );

				renderer.render( scene, camera );

			}

		</script>
		</div>
<!--主显示区-->
<!--显示标签-->
        <div id="tags">
            <img src="./../tags/1/thumb_1_1.jpg">
            <img src="./../tags/1/thumb_1_2.jpg">
            <img src="./../tags/1/thumb_1_13.jpg">
            <img src="./../tags/1/thumb_1_14.jpg">
            <img src="./../tags/1/thumb_1_19.jpg">
            <img src="./../tags/1/thumb_1_20.jpg">
            <img src="./../tags/1/thumb_1_9.jpg">
        </div>

		<div class="vrshow_container_1_min">
			<!--全屏显示-->
		    <div class="btn_fullscreen" onclick="fullScreen(document.documentElement)" title="" style=""></div>
		    <div class="btn_vrmode" onclick="showWebVR()" style=""></div>
			<div id="musicBtn" class="btn_bgmusic" style="">
				<audio id="music" src="./../music/Alphaville - Forever Young.mp3" autoplay="autoplay" loop="loop">你的浏览器不支持audio标签。</audio> 
		   	</div>
		    <script> 
		      $("#musicBtn").click(function(){ 
		          var music = document.getElementById("music"); 
		          if(music.paused){ 
		              music.play(); 
		              $("#musicBtn").attr("class","btn_bgmusic"); 
		          }else{ 
		              music.pause(); 
		              $("#musicBtn").attr("class","btn_bgmusic_off"); 
		          } 
		      }); 
		    </script>
		</div>
		

        <div class="vrshow_container_2_min">	
  			<div class="img_desc_container_min" id="shareDiv">
			   <img src="./../static/images/skin1/vr-btn-share.png" data-toggle="modal" data-target="#shareModal"/>
			    <div class="img_desc_min">分享</div>
			</div>

			<div class="img_desc_container_min" id="profileDiv">
			    <img src="./../static/images/skin1/vr-btn-desc.png" data-toggle="modal" data-target="#profileModal">
			    <div class="img_desc_min">简介</div>
			</div>
			<div class="img_desc_container_min" id="praiseDiv">
			   <img id="btnpraise" src="./../static/images/skin1/vr-btn-good.png" onclick="addPraise(this)">
			   <div class="img_desc_min" id="praisedNum">0</div>
			</div>

			<script>
			    function addPraise(obj)
			    {
			    	$(obj).attr('src','./../static/images/skin1/vr-btn-good-click.png');
			    }
			</script>
			<style>
				.vrshow_comment {
				    position: absolute;
				    bottom: 10%;
				    left: 50%;
				    margin-left: -200px;
				    width: 400px;
				    min-height: 100px;
				    background-color: rgba(51, 51, 51, 0.8);
				    z-index: 4300;
				    color: #fff;
				    border-radius: 5px;
				    display: none;
				}
				@media screen and (max-width: 767px) {
				    .vrshow_comment {
				        width: 250px;
				        margin-left: -125px;
				    }

				}
			 </style>
			<div class="img_desc_container_min" id="comment_div">
			    <img src="./../static/images/skin1/vr-btn-comment.png" onclick="addComment()">
			    <div class="img_desc_min">说一说</div>
			</div>
			<!-- <script src="./../vr_lib/comment.js"></script> -->
                                               
			
		</div>
        <div class="vrshow_container_3_min">
                <div class="img_desc_container_min scene-choose-width" style="">
                    <img src="./../static/images/skin1/vr-btn-scene.png" onclick="switchThumbs()">
                    <div class="img_desc_min">场景选择</div>
                </div>
        </div>

    <!-- 全屏显示和退出全屏 -->
    <script type="text/javascript">
		function fullScreen(element) {
		  // 判断各种浏览器，找到正确的方法
		  var requestMethod = element.requestFullScreen || //W3C
		  element.webkitRequestFullScreen ||  //Chrome等
		  element.mozRequestFullScreen || //FireFox
		  element.msRequestFullScreen; //IE11
		  if (requestMethod) {
		    requestMethod.call(element);
		  }
		  else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
		    var wscript = new ActiveXObject("WScript.Shell");
		    if (wscript !== null) {
		      wscript.SendKeys("{F11}");
		    }
		  }
		}
		//退出全屏 判断浏览器种类
		function exitFull() {
		  // 判断各种浏览器，找到正确的方法
		  var exitMethod = document.exitFullscreen || //W3C
		  document.mozCancelFullScreen ||  //Chrome等
		  document.webkitExitFullscreen || //FireFox
		  document.webkitExitFullscreen; //IE11
		  if (exitMethod) {
		    exitMethod.call(document);
		  }
		  else if (typeof window.ActiveXObject !== "undefined") {//for Internet Explorer
		    var wscript = new ActiveXObject("WScript.Shell");
		    if (wscript !== null) {
		      wscript.SendKeys("{F11}");
		    }
		  }
		}
		</script>
    	<!-- 全屏显示和退出全屏 -->


     <!--  模态框 -->
        <!-- 分享网页 -->
        	 <div class="modal fade" id="shareModal" tabindex="-1" role="dialog" aria-labelledby="shareModalLabel" aria-hidden="true">
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
	        <!-- 简介数据获取-->
	        <script type="text/javascript"></script>
		    
		<!-- 简介 -->

		<!-- 说一说 -->
		<div class="vrshow_comment" id="addcomment">
	        <div class="row">
	            <div class="col-md-12">
	                <h4 style="margin-left:10px">评论</h4>
	            </div>
	        </div>
	        <div class="row">
	            <div class="col-md-12" style="padding-left:20px;padding-right:20px">
	                <textarea id="usercomm" class="form-control" rows="3" placeholder="说点什么吧！最多不要超过25个字哦" maxlength="15"></textarea>
	            </div>
	        </div>
	        <div class="row">
	            <div class="col-md-12 text-right" style="padding:10px 20px">
	                <button class="btn btn-primary" type="button" id="doComm">发表</button>
	                <button class="btn" type="button" onclick="cancelComment()">取消</button>
	            </div>
	        </div>
	    </div>

	    <script type="text/javascript">
	    	function addComment()
	    	{
	    		if($(".vrshow_comment").css('display')=='none'){
	    			$(".vrshow_comment").show();
		    		$("#usercomm").focus();
	    		}else{
	    			$(".vrshow_comment").hide();
	    		}
	    		
	    	}
	    	function cancelComment()
	    	{
	    		$(".vrshow_comment").hide();
	    	}
	    </script>
    <!-- 说一说 -->

	<!--  模态框 -->

	<!-- 切换场景 -->
	<script type="text/javascript">
		function switchThumbs()
		{
			if ($('#tags').css('display')=='block') {
				$('#tags').hide();
			}
			else{
				$('#tags').show();
			}
		}

	</script>

	<!-- 切换场景 -->
	  </div>
	</body>
</html>
