<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet"
	href="http://cache.amap.com/lbs/static/main1119.css" />
<script type="text/javascript"
	src="http://webapi.amap.com/maps?v=1.3&key=2b1128cb282fdc6f7925ad9959dc6fdf&plugin=AMap.Autocomplete"></script>
<script type="text/javascript"
	src="http://cache.amap.com/lbs/static/addToolbar.js"></script>

</head>
<body>
<body>
	<div id="container"></div>
	<div id="myPageTop">
		<table>
			<tr>
				<td><label>设备所属地址搜索：</label></td>
				<td class="column2"><label>获取经纬度：</label></td>
			</tr>
			<tr>
				<td><input type="text" placeholder="请输入设备所在地址" id="tipinput">
				</td>
				<td class="column2"><input type="text" placeholder="获取的经纬度"
					id="lnglat"></td>
			</tr>
		</table>
	</div>
	<script type="text/javascript">
		//var mask = [];
		var markers = [];
		var map = new AMap.Map("container", {
			resizeEnable : true,
			zoom : 9
		});
		//为地图注册click事件获取鼠标点击出的经纬度坐标
		var clickEventListener = map.on('click', function(e) {
			document.getElementById("lnglat").value = e.lnglat.getLng() + ','
					+ e.lnglat.getLat()
			var lnglatXY = [ e.lnglat.getLng(), e.lnglat.getLat() ]; //已知点坐标
			debugger;
			//mask.push(lnglatXY);
			if (map.map.Cb.length != 0) {
				map.remove(markers);
			}

			var marker = new AMap.Marker({ //加点
				map : map,
				position : lnglatXY
			});

			markers.push(marker);

		});
		var auto = new AMap.Autocomplete({
			input : "tipinput"
		});
		AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
		function select(e) {
			if (e.poi && e.poi.location) {
				map.setZoom(15);
				map.setCenter(e.poi.location);
			}
		}
	</script>
</body>
</html>