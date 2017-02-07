<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<!-- <script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=2b1128cb282fdc6f7925ad9959dc6fdf&plugin=AMap.Geocoder"></script> -->
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
<script
	src="http://webapi.amap.com/maps?v=1.3&key=2b1128cb282fdc6f7925ad9959dc6fdf"></script>

<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<style type="text/css">
.info {
	border: solid 1px silver;
}

div.info-top {
	position: relative;
	background: none repeat scroll 0 0 #F9F9F9;
	border-bottom: 1px solid #CCC;
	border-radius: 5px 5px 0 0;
}

div.info-top div {
	display: inline-block;
	color: #333333;
	font-size: 14px;
	font-weight: bold;
	line-height: 31px;
	padding: 0 10px;
}

div.info-top img {
	position: absolute;
	top: 10px;
	right: 10px;
	transition-duration: 0.25s;
}

div.info-top img:hover {
	box-shadow: 0px 0px 5px #000;
}

div.info-middle {
	font-size: 12px;
	padding: 6px;
	line-height: 20px;
}

div.info-bottom {
	height: 0px;
	width: 100%;
	clear: both;
	text-align: center;
}

div.info-bottom img {
	position: relative;
	z-index: 104;
}

span {
	margin-left: 5px;
	font-size: 11px;
}

.info-middle img {
	float: left;
	margin-right: 6px;
}
</style>

<%@ include file="../top.jsp"%> 
</head>
<body>
	<div id="container"></div>

	<div id="myPageTop">
		<table>
			<tr>
				<td><label>请输入设备关键字：</label></td>
			</tr>
			<tr>
				<td><input class="form-control" id="tipinput"/></td><td>
				<input type="button"  style="width:50px;" class="btn btn-sm btn-info" value="搜索" onclick="bon2();"></td>				
			</tr>
		</table>
	</div>

	<script type="text/javascript">
		var map2;
		var map;

		function bon2() {
			//实例化信息窗体
			var title = '设备Xccccc', content = [];
			//content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>地址：北京市朝阳区阜通东大街6号院3号楼东北8.3公里");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			//content.push("<a href='http://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");
			//dataInit(title, content);

			map.panTo([ 120.722827,31.175324 ]);
			dataInit(title, content).open(map, map2.getPosition());
		}

		map = new AMap.Map("container", {
			resizeEnable : true,
			zoom : 9
		});

		var map1 = marker = new AMap.Marker({
			icon : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
			position : [ 121.4736999111, 31.2303950509 ]
		});
		marker.setMap(map);

		map2 = marker = new AMap.Marker({
			icon : "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
			position : [ 120.722827,31.175324 ]
		});
		marker.setMap(map);

		AMap.event.addListener(map1, 'click', function() {
			//实例化信息窗体
			var title = '设备Xccccc', content = [];
			//content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>地址：北京市朝阳区阜通东大街6号院3号楼东北8.3公里");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			//content.push("<a href='http://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");
			//dataInit(title, content);

			dataInit(title, content).open(map, map1.getPosition());

		});

		AMap.event.addListener(map2, 'click', function() {
			//实例化信息窗体
			var title = '设备Xccccc', content = [];
			//content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>地址：北京市朝阳区阜通东大街6号院3号楼东北8.3公里");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			content.push("电话：010-64733333");
			//content.push("<a href='http://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");
			//dataInit(title, content);

			dataInit(title, content).open(map, map2.getPosition());
		});

		function dataInit(title, content) {
			//实例化信息窗体
			//var title = '设备Xccccc',
			//var content = [];
			//content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>地址：北京市朝阳区阜通东大街6号院3号楼东北8.3公里");
			//content.push("电话：010-64733333");
			//content.push("电话：010-64733333");
			//content.push("电话：010-64733333");
			//content.push("电话：010-64733333");
			//content.push("电话：010-64733333");
			//content.push("<a href='http://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");
			debugger;
			var infoWindow = new AMap.InfoWindow({
				isCustom : true, //使用自定义窗体
				content : createInfoWindow(title, content.join("<br/>")),
				offset : new AMap.Pixel(16, -45)
			});

			return infoWindow;
		}

		//构建自定义信息窗体
		function createInfoWindow(title, content) {
			var info = document.createElement("div");
			info.className = "info";

			//可以通过下面的方式修改自定义窗体的宽高
			//info.style.width = "400px";
			// 定义顶部标题
			var top = document.createElement("div");
			var titleD = document.createElement("div");
			var closeX = document.createElement("img");
			top.className = "info-top";
			titleD.innerHTML = title;
			closeX.src = "http://webapi.amap.com/images/close2.gif";
			closeX.onclick = closeInfoWindow;

			top.appendChild(titleD);
			top.appendChild(closeX);
			info.appendChild(top);

			// 定义中部内容
			var middle = document.createElement("div");
			middle.className = "info-middle";
			middle.style.backgroundColor = 'white';
			middle.innerHTML = content;
			info.appendChild(middle);

			// 定义底部内容
			var bottom = document.createElement("div");
			bottom.className = "info-bottom";
			bottom.style.position = 'relative';
			bottom.style.top = '0px';
			bottom.style.margin = '0 auto';
			var sharp = document.createElement("img");
			sharp.src = "http://webapi.amap.com/images/sharp.png";
			bottom.appendChild(sharp);
			info.appendChild(bottom);
			return info;
		}

		//关闭信息窗体
		function closeInfoWindow() {
			map.clearInfoWindow();
		}
	</script>
</body>
</html>

