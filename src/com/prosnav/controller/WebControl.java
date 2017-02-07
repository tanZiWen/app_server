
//package com.prosnav.controller;
//
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import net.sf.json.JSONObject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.google.gson.Gson;
//import com.prosnav.model.test.User;
//import com.prosnav.service.impl.TestService;
//
///**
// * 客户端控制层
// * */
//
//@Controller
//@RequestMapping("/web")
//public class WebControl {
//
//	private static Logger log = LoggerFactory.getLogger(WebControl.class);
//
//	@Resource
//	private TestService testService;
//
//	@RequestMapping(value = "/testPostJson", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//	public ResponseEntity<String> testPostJson(@RequestBody String json)
//			throws Exception {
//		JSONObject jsons = JSONObject.fromObject(json);
//		log.info(json);
//
//		return new ResponseEntity<String>(json, HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/testGetString", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ResponseEntity<String> testGetString(@RequestParam String username)
//			throws Exception {
//		log.info(username);
//
//		return new ResponseEntity<String>("啊啊啊啊", HttpStatus.OK);
//	}
//
//	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ModelAndView test(HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("test");
//
//		return mv;
//	}
//
//	@RequestMapping(value = "/upload", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ModelAndView upload(HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView();
////		String content = "hellow 中国";
////		InputStream ips = new ByteArrayInputStream(content.getBytes("UTF-8"));
////		String url = "http://192.168.44.129:8081/fileupload/html"; 
////		log.info(url);
////		System.out.println(FileUploadUtil.upload(url, "text.txt",ips));
//		
//		testService.upload();
//		mv.setViewName("test");
//		return mv;
//	}
//
//	/**
//	 * 地图呈现
//	 * */
//	@RequestMapping(value = "/map", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ModelAndView reportPosition(HttpServletRequest request)
//			throws Exception {
//		ModelAndView mv = new ModelAndView();
//		// HttpSession s=request.getSession();
//		// System.out.println("当前用户session值为："+s.getId());
//		// springPropertyResourceReader.init();
//
//		mv.setViewName("map/map");
//
//		String longitude = "121.4736999111";
//		String latitude = "31.2303950509";
//
//		// System.out.println(new MapUtils().getAddress(
//		// springPropertyResourceReader.getProperty("MAP_KEY"), longitude,
//		// latitude));
//
//		return mv;
//	}
//
//	/**
//	 * 地图反查经纬度及查询地址
//	 * */
//	@RequestMapping(value = "/findMap", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ModelAndView find(HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		// HttpSession session=request.getSession();
//		// String id=session.getId();
//		// if(StringUtils.isNotBlank(id)){
//		testService.test();
//		// String uName=new
//		// String(request.getParameter("userName").getBytes("ISO-8859-1"),
//		// "UTF-8");;
//		// System.out.println("姓名："+uName+"session为："+id);
//		// mv.addObject("name", uName);
//		// mv.addObject("sid", id);
//		// mv.setViewName("success");
//		// }else{
//		// mv.addObject("name", "null");
//		// mv.addObject("sid", "null");
//		// mv.setViewName("success");
//		// }
//
//		mv.setViewName("map/map_save");
//		return mv;
//	}
//
//	/**
//	 * 地图反查经纬度及查询地址
//	 * */
//	@RequestMapping(value = "/saveMap", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ModelAndView save(HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		// HttpSession session=request.getSession();
//		// String id=session.getId();
//		// if(StringUtils.isNotBlank(id)){
//		// testService.test2();
//		// String uName=new
//		// String(request.getParameter("userName").getBytes("ISO-8859-1"),
//		// "UTF-8");;
//		// System.out.println("姓名："+uName+"session为："+id);
//		// mv.addObject("name", uName);
//		// mv.addObject("sid", id);
//		// mv.setViewName("success");
//		// }else{
//		// mv.addObject("name", "null");
//		// mv.addObject("sid", "null");
//		// mv.setViewName("success");
//		// }
//
//		testService.save();
//
//		mv.setViewName("map/map_save");
//		return mv;
//	}
//
//	/**
//	 * 地图反查经纬度及查询地址
//	 * */
//	@RequestMapping(value = "/main", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ModelAndView main(HttpServletRequest request) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("index");
//		return mv;
//	}
//
//	/**
//	 * 设备列表
//	 * */
//	@RequestMapping(value = "/deviceListSkip", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ModelAndView deviceListSkip(HttpServletRequest request)
//			throws Exception {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("device_list");
//		return mv;
//	}
//
//	/**
//	 * 设备列表
//	 * */
//	@RequestMapping(value = "/deviceList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
//	public ModelAndView deviceList(HttpServletRequest request,
//			HttpServletResponse response) throws Exception {
//		// ModelAndView mv=new ModelAndView();
//		// mv.setViewName("device_list");
//		// return mv;
//
//		System.out.println("=====================================");
//		ResultSet rs = null;
//		Statement stmt = null;
//		String table = "user";
//
//		// 获取请求次数
//		String draw = "0";
//		draw = request.getParameter("draw");
//		// 数据起始位置
//		String start = request.getParameter("start");
//		// 数据长度
//		String length = request.getParameter("length");
//
//		// 总记录数
//		String recordsTotal = "0";
//
//		// 过滤后记录数
//		String recordsFiltered = "";
//
//		// 定义列名
//		String[] cols = { "seq", "name", "position", "salary", "start_date",
//				"office", "extn" };
//		// 获取客户端需要那一列排序
//		String orderColumn = "0";
//		orderColumn = request.getParameter("order[0][column]");
//		orderColumn = cols[Integer.parseInt(orderColumn)];
//		// 获取排序方式 默认为asc
//		String orderDir = "asc";
//		orderDir = request.getParameter("order[0][dir]");
//
//		// 获取用户过滤框里的字符
//		String searchValue = request.getParameter("search[value]");
//
//		List<String> sArray = new ArrayList<String>();
//		if (!searchValue.equals("")) {
//			sArray.add(" name like '%" + searchValue + "%'");
//			sArray.add(" position like '%" + searchValue + "%'");
//			sArray.add(" salary like '%" + searchValue + "%'");
//			sArray.add(" start_date like '%" + searchValue + "%'");
//			sArray.add(" office like '%" + searchValue + "%'");
//			sArray.add(" extn like '%" + searchValue + "%'");
//		}
//
//		String individualSearch = "";
//		if (sArray.size() == 1) {
//			individualSearch = sArray.get(0);
//		} else if (sArray.size() > 1) {
//			for (int i = 0; i < sArray.size() - 1; i++) {
//				individualSearch += sArray.get(i) + " or ";
//			}
//			individualSearch += sArray.get(sArray.size() - 1);
//		}
//
//		try {
//
//			List<User> users = new ArrayList<User>();
//
//			int count = 1;
//			recordsTotal = "200";
//			for (int i = 0; i < 10; i++) {
//				users.add(new User(String.valueOf(Integer.parseInt(start)
//						+ count), String.valueOf(i), String.valueOf(i), "33",
//						"44", "55", "66"));
//				count++;
//			}
//			if (searchValue != "") {
//				recordsFiltered = "200";
//			} else {
//				recordsFiltered = recordsTotal;
//			}
//
//			Map<Object, Object> info = new HashMap<Object, Object>();
//			info.put("data", users);
//			info.put("recordsTotal", recordsTotal);
//			info.put("recordsFiltered", recordsFiltered);
//			info.put("draw", draw);
//			String json = new Gson().toJson(info);
//			response.getWriter().write(json);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//		return null;
//	}
//
//}
