<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<%@ include file="list.jsp"%>
</head>
<div class="ibox-title">
	<label style="font-size: 18px;" class="form-group">&nbsp;&nbsp;&nbsp;&nbsp;设备预览</label>
</div>
<div class="ibox-content">
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<form role="form" class="form-inline">
		<label style="font-size: 14px;" class="form-group">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客户名称
			：</label>
		<div class="form-group">
			<select class="form-control" name="" style="width: 200px">
				<option>选项 1</option>
				<option>选项 2</option>
				<option>选项 3</option>
				<option>选项 4</option>
			</select>
		</div>
		<div class="form-group">
			<label style="font-size: 14px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;设备名称：</label>
			<input type="password" placeholder="请输入设备名称"
				id="exampleInputPassword2" class="form-control">
		</div>
		<div class="form-group">
			<button class="btn btn-default dropdown-toggle "
				style="font-size: 14px;" type="button">
				<i class="fa fa-search"></i>&nbsp;&nbsp;检索
			</button>
		</div>
		<div>
			<br>
		</div>
		<div class="text-left">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button"
				onclick="javascript:window.location.href='device_add.html'"
				style="font-size: 14px;" class="btn btn-default dropdown-toggle">
				<i class="fa fa-plus"></i>&nbsp;&nbsp;添加设备
			</button>
		</div>
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">
					<table class="table table-striped table-bordered table-hover dataTables-example">
						<thead>
							<tr>
								<th></th>
								<th>姓名</th>
								<th>地点</th>
								<th>薪水</th>
								<th>入职时间</th>
								<th>办公地点</th>
								<th>编号</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>


				</div>
			</div>

		</div>
	</form>
</div>


<input type="hidden" id="ctx" value="${ctx}" />



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">新增</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<input type="text" class="form-control" id="name" placeholder="姓名">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="position"
						placeholder="位置">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="salary"
						placeholder="薪资">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="start_date"
						placeholder="时间" data-date-format="yyyy/mm/dd">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="office"
						placeholder="工作地点">
				</div>
				<div class="form-group">
					<input type="text" class="form-control" id="extn" placeholder="编号">
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-info" id="initData">添加模拟数据</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" id="save">保存</button>
			</div>
		</div>
	</div>
</div>

<!-- Page-Level Scripts -->
<script>
	var table;
	var editFlag = false;
	var ctx = document.getElementById('ctx').value;
	$(function() {
		debugger;
		//$('#start_date').datetimepicker();

		var tpl = $("#tpl").html();
		//预编译模板
		//var template = Handlebars.compile(tpl);

		table = $('.dataTables-example')
				.DataTable(
						{
							searching : false, //去掉搜索框方法一：百度上的方法，但是我用这没管用
			                sDom : '"top"i',   //去掉搜索框方法二：这种方法可以，动态获取数据时会引起错误
			                bFilter: false,    //去掉搜索框方法三：这种方法可以
							"bAutoWidth": false, //自适应宽度
							ajax : {
								url : ctx + "/web/deviceList"							
							},
							"order" : [ [ 1, 'asc' ] ],// dt默认是第一列升序排列 这里第一列为序号列，所以设置为不排序，并把默认的排序列设置到后面
							"serverSide" : true,
							"columns" : [
									{
										"data" : "seq"
									}, //因为要加行号，所以要多一列，不然会把第一列覆盖 
									{
										"data" : "name"
									},
									{
										"data" : "position"
									},
									{
										"data" : "salary"
									},
									{
										"data" : "start_date"
									},
									{
										"data" : "office"
									},
									{
										"data" : "extn"
									},
									{
										"data" : "name",
										"render" : function(data, type, full,
												callback) {
											/*return ('<a class="btn btn-info"    data-toggle="modal" data-target="#modal-addOrUpdate" onclick="details(\''
													+ data
													+ '\');" >禁用</a><a class="btn btn-info"    data-toggle="modal" data-target="#modal-addOrUpdate" onclick="details(\''
													+ data + '\');" >删除</a>');*/
											
													return ('<div class="btn-group"> ' +
			                                '<button data-toggle="dropdown" class="btn btn-default dropdown-toggle " style="font-size:14px;" >操作 &nbsp;&nbsp;<span class="caret"></span>' +
			                                '</button>' +
			                                '<ul class="dropdown-menu">' +
			                                    '<li><a href="buttons.html#">查看</a>' +
			                                    '</li>' +
			                                    '<li><a href="buttons.html#" >修改</a>' +
			                                    '</li>' +
			                                    '<li><a href="buttons.html#">删除</a>' +
			                                    '</li>' +			                                    
			                                '</ul>' +
			                            '</div>');
											
										}

									} ],
							"columnDefs" : [ {
								"searchable" : false,
								"orderable" : false,
								"targets" : [ 1 ],
								"visible" : false
							//"targets": [0.-1]
							}

							/*,
							{
							    "targets": 7,
							    "render": function (a, b, c, d) {
							        var context =
							        {
							            func: [
							                {"name": "修改", "fn": "edit(\'" + c.name + "\',\'" + c.position + "\',\'" + c.salary + "\',\'" + c.start_date + "\',\'" + c.office + "\',\'" + c.extn + "\')", "type": "primary"},
							                {"name": "删除", "fn": "del(\'" + c.name + "\')", "type": "danger"}
							            ]
							        };
							        var html = template(context);
							        return html;
							    }
							}*/

							],
							/*"language": {
							    "lengthMenu": "每页_MENU_ 条记录",
							    "zeroRecords": "没有找到记录",
							    "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
							    "infoEmpty": "无记录",
							    "search": "搜索：",
							    "infoFiltered": "(从 _MAX_ 条记录过滤)",
							    "paginate": {
							        "previous": "111上一页",
							        "next": "下一页"
							    }
							},
							"dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>" +
							        "t" +
							        "<'row'<'col-xs-6'i><'col-xs-6'p>>",*/
							"initComplete" : function() {
								$("#mytool")
										.append(
												'<button id="datainit" type="button" class="btn btn-primary btn-sm">增加基础数据</button>&nbsp');
								$("#mytool")
										.append(
												'<button type="button" class="btn btn-default btn-sm" data-toggle="modal" data-target="#myModal">添加</button>');
								$("#datainit").on("click", initData);
							}

						});

		//添加序号
		//不管是排序，还是分页，还是搜索最后都会重画，这里监听draw事件即可
		/*table.on('draw.dt',function() {
		            table.column(0, {
		                search: 'applied',
		                order: 'applied'
		            }).nodes().each(function(cell, i) {
		                //i 从0开始，所以这里先加1
		                i = i+1;
		                //服务器模式下获取分页信息
		                var page = table.page.info();
		                //当前第几页，从0开始
		                var pageno = page.page;
		                //每页数据
		                var length = page.length;
		                //行号等于 页数*每页数据长度+行号
		                var columnIndex = (i+pageno*length);
		                cell.innerHTML = columnIndex;
		            });
		        }).draw();*/

		$("#save").click(add);

		//$("#initData").click(initSingleData);

	});

	/**
	 * 初始化基础数据
	 */
	/*function initData() {
		var flag = confirm("本功能将添加数据到数据库，你确定要添加么？");
		if (flag) {
			$.get("/objects.txt", function(data) {
				var jsondata = JSON.parse(data);
				$(jsondata.data).each(function(index, obj) {
					ajax(obj);
				});
			});
		}
	}*/

	/**
	 * 初始化基础数据
	 */
	function initSingleData() {
		$("#name").val("http://datatables.club");
		$("#position").val("成都");
		$("#salary").val("10000");
		$("#start_date").val("2016/7/10");
		$("#office").val("java");
		$("#extn").val("341");
	}

	/**
	 * 清除
	 */
	function clear() {
		$("#name").val("").attr("disabled", false);
		$("#position").val("");
		$("#salary").val("");
		$("#start_date").val("");
		$("#office").val("");
		$("#extn").val("");
		editFlag = false;
	}

	/**
	 * 添加数据
	 **/
	function add() {
		var addJson = {
			"name" : $("#name").val(),
			"position" : $("#position").val(),
			"salary" : $("#salary").val(),
			"start_date" : $("#start_date").val(),
			"office" : $("#office").val(),
			"extn" : $("#extn").val()
		};

		ajax(addJson);
	}

	/**
	 *编辑方法
	 **/
	function edit(name, position, salary, start_date, office, extn) {
		console.log(name);
		editFlag = true;
		$("#myModalLabel").text("修改");
		$("#name").val(name).attr("disabled", true);
		$("#position").val(position);
		$("#salary").val(salary);
		$("#start_date").val(start_date);
		$("#office").val(office);
		$("#extn").val(extn);
		$("#myModal").modal("show");
	}

	function ajax(obj) {
		var url = "/add.jsp";
		if (editFlag) {
			url = "/edit.jsp";
		}
		$.ajax({
			url : url,
			data : {
				"name" : obj.name,
				"position" : obj.position,
				"salary" : obj.salary,
				"start_date" : obj.start_date,
				"office" : obj.office,
				"extn" : obj.extn
			},
			success : function(data) {
				table.ajax.reload();
				$("#myModal").modal("hide");
				$("#myModalLabel").text("新增");
				clear();
				console.log("结果" + data);
			}
		});
	}

	function details(data) {
		alert(data);
	}

	/**
	 * 删除数据
	 * @param name
	 */
	function del(name) {
		$.ajax({
			url : "/del.jsp",
			data : {
				"name" : name
			},
			success : function(data) {
				table.ajax.reload();
				console.log("删除成功" + data);
			}
		});
	}
</script>



</body>
</html>