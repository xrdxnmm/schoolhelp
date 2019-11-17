package com.example.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.example.domain.Mission;
import com.example.domain.MissionStatus;
import com.example.domain.MissionType;
import com.example.domain.PageBean;
import com.example.domain.Picture;
import com.example.domain.ReceiveMissionStatus;
import com.example.service.MissionService;
import com.example.utils.FormatConversion;
import net.sf.json.JSONObject;

/**
 * 
 * @author XRDMM
 *
 */
@Controller
@RequestMapping("missionController")
public class MissionController {
	
	FormatConversion formatConversion=new FormatConversion();
	
	@Autowired
	private MissionService missionService;
	
	
	
	@ResponseBody
	@RequestMapping(value = "addMission",produces = {"application/json;charset=UTF-8"})
	public String addMission(Mission mission) {
		System.out.println("添加订单："+mission.getuName()+" "+mission.getMissionTitle());
		
		int result=missionService.addMission(mission);
		Map<String, String> map=new HashMap<String,String>();
		if(result>0) {
			map.put("message","success");
			map.put("status","200");
		}else {
			map.put("message","faile");
			map.put("status","201");
		}
		String string=JSONObject.fromObject(map).toString();
		System.out.println("string："+string);
		return string;
		
	}
	
	/**
	 * 已修改
	 * 分页查询个人发布未被接取的任务/查询个人发布已被接取的任务/查询个人发布已被完成的任务
	 * @param uName
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */	
	@ResponseBody
	@RequestMapping(value = "querySelf",produces = {"application/json;charset=UTF-8"})
	public String querySelf(String uName,MissionStatus missionStatus,
							@RequestParam(value="pageNo",defaultValue="1")int pageNo) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		System.out.println("查询个人发布订单");
		PageBean<Mission> mission=missionService.querySelf(uName,missionStatus,pageNo);
		String string = JSON.toJSON(mission).toString();
		System.out.println("string1:"+string);
		return string;
	}
	
	/**
	 * 已修改
	 * 分页查询个人已接取的任务/查询个人已完成的任务
	 * @param receive
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */	
	@ResponseBody
	@RequestMapping(value = "querySelfNOrYReceive",produces = {"application/json;charset=UTF-8"})
	public String querySelfNOrYReceive(String uName,ReceiveMissionStatus receiveMissionStatus,
									   @RequestParam(value="pageNo",defaultValue="1")int pageNo) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		System.out.println("1查询个人接收订单");
		System.out.println("Seriver:"+uName+" | "+receiveMissionStatus+" | "+pageNo);
		PageBean<Mission> pageBean=missionService.querySelfNOrYReceive(uName,receiveMissionStatus,pageNo);
		String string = JSON.toJSON(pageBean).toString();
		System.out.println("string1:"+string);
		return string;
	}
	
	@ResponseBody
	@RequestMapping(value = "query",produces = {"application/json;charset=UTF-8"})
	public String query(int missionNo){
		
		//List<Map<String,Object>> listmap=new ArrayList<Map<String, Object>>();
		Mission mission=missionService.query(missionNo);
		
		//Map<String, Object>map=new HashMap<String, Object>();
		String string = JSON.toJSON(mission).toString();
		System.out.println("string1:"+string);
		return string;
		
	}
	
	/**
	 * 已修改
	 * 接取任务
	 * @param mission
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "receiveMission",produces = {"application/json;charset=UTF-8"})
	public String receiveMission(Mission mission) {
		Map<String, String> map=new HashMap<String, String>();
		System.out.println("接受订单1:"+mission.getMissionNo());
			int result=missionService.receiveMission(mission);
			System.out.println(mission.getMissionNo()+"接收人："+mission.getuName());			
		if(result>0) {	
			map.put("message", "success");
			map.put("status", "200");			
		}
		else {
			map.put("message", "false");
			map.put("status", "201");
						
		}
		String string=JSONObject.fromObject(map).toString();
		System.out.println("string："+string);
		return string;
		
	}
	
	
	/**
	 * 已修改
	 * 根据类型查询任务
	 * @param missionType
	 * @return string
	 */
	@ResponseBody
	@RequestMapping(value = "queryType",produces = {"application/json;charset=UTF-8"})
	public String queryType(@RequestParam(value="missionType",defaultValue="全部")MissionType missionType,
							String word,
							@RequestParam(value="pageNo",defaultValue="1")int pageNo) {
		System.out.println("missionType:"+missionType);
		PageBean<Mission> list=missionService.queryType(missionType,word,pageNo);
		String string=JSON.toJSON(list).toString();
		System.out.println("queryType:"+string);
		return string;				
	}
	/**
	 * 已修改
	 * 完成任务
	 * @param mission
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "completeMission",produces = {"application/json;charset=UTF-8"})
	public String completeMission(Mission mission) {
		Map<String,String> map=new HashMap<String, String>();
		System.out.println("完成任务:"+mission.getMissionNo());
		int result=missionService.completeMission(mission);
		System.out.println("控制层："+mission.getMissionNo()+"接收人："+mission.getuName());		
		if(result>0) {
			map.put("message", "success");
			map.put("status", "200");
		}else {
			map.put("message", "faile");
			map.put("status", "201");
		}			
		String string=JSONObject.fromObject(map).toString();
		System.out.println("string："+string);
		return string;
		
	}
//	@ResponseBody	
//	@RequestMapping(value = "keyWord",produces = {"application/json;charset=UTF-8"})
//	public String keyWord(MissionType missionType,String word) {
//		System.out.println("控制层："+missionType.toString());
//		List<Mission> missions=missionService.titleKeyWord(missionType, word);
//		missions.addAll(missionService.detailKeyWord(missionType, word));
//		String string=JSON.toJSON(missions).toString();
//		return string;
//	}
	
	@ResponseBody
	@RequestMapping(value = "missionProfile",produces = {"application/json;charset=UTF-8"})
	public String missionProfile(int missionNo) {
		Map<String, String> map=new HashMap<String, String>();
		System.out.println("控制层：确认完成任务："+missionNo);
		int result=missionService.missionProfile(missionNo);
		if(result<0) {
			map.put("message", "success");
			map.put("status", "200");
		}else {
			map.put("message", "faile");
			map.put("status", "201");
		}
		String string=JSONObject.fromObject(map).toString();
		System.out.println("string:"+string);
		return string;
	}	
	
	@ResponseBody
	@RequestMapping(value = "addPic",produces = {"application/json;charset=UTF-8"})
	public String addPic(int missionNo,MultipartFile[] multipartFiles,HttpServletRequest request) {
		System.out.println("控制层:添加图片");
//		String path=request.getSession().getServletContext().getRealPath("/images");
//		System.out.println("路径："+path);
		int result=missionService.addPic(missionNo,multipartFiles);
		Map<String,String> map=new HashMap<String,String>();
		if(result>0) {
			map.put("message", "success");
			map.put("status", "200");
		}
		else {
			map.put("message", "faile");
			map.put("status", "201");
		}
		String string=JSONObject.fromObject(map).toString();
		return string;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "queryPic",produces = {"application/json;charset=UTF-8"})
	public List<Picture> queryPic(int missionNo){
		List<Picture> pictures=missionService.queryPic(missionNo);
		String string=JSON.toJSON(pictures).toString();
		System.out.println("queryPic:"+string);
		return pictures;
	}
	
	/**
	 * 删除任务
	 * @param missionNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteMission",produces = {"application/json;charset=UTF-8"})
	public String deleteMission(int missionNo) {
		int result=missionService.deleteMission(missionNo);
		Map<String,String>map=new HashMap<String, String>();
		if (result>0) {
			map.put("message", "success");
			map.put("status", "200");
		}else {
			map.put("message", "faile");
			map.put("status", "201");
		}
		String string=JSONObject.fromObject(map).toString();
		System.out.println("string:"+string);
		return string;
	}

}
