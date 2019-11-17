package com.example.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.example.domain.Mission;
import com.example.domain.MissionStatus;
import com.example.domain.MissionType;
import com.example.domain.PageBean;
import com.example.domain.ReceiveMissionStatus;
import com.example.domain.User;
import com.example.service.MissionService;
import com.example.service.UserService;
import com.example.utils.MyEncoderByMd5;


/**
 * 
 * @author XRDMM
 *
 */
public class MyBatisSpringTest {

	
	
	@Test
	public void testOrder() throws NoSuchAlgorithmException, IOException{
		//
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		MissionService missionService = ac.getBean(MissionService.class);
		
		//根据类型查询接口测试		
//		PageBean<Mission> missionPage=missionService.queryType(MissionType.全部,"",1);
//		List<Mission> missions=missionPage.getList();
//		for(Mission mission:missions)
//			System.out.println("\nSeriver任务类型查询："+mission.getMissionNo());
		
		//接取任务接口测试
		Mission mission=new Mission();
		mission.setMissionNo(12);
		mission.setuName("201710098001");
		missionService.receiveMission(mission);
		missionService.addReceive(mission);
		System.out.println("\nSeriver任务接取：");
		
		//查询个人发布还未被接取的任务接口
//		String uName="201710098005";
//		PageBean<Mission> pageBean=missionService.querySelf(uName,MissionStatus.已被接取,1);
//		List<Mission> missions=pageBean.getList();
//		for(Mission mission:missions)
//			System.out.println("\nSeriver查询个人发布还未被接取的任务："+mission.getMissionNo()+" : "+mission.getMissionTitle());
		
		//查询个人接取的任务接口
//		String uName="201710098005";
//		PageBean<Mission> pageBean=missionService.querySelfNOrYReceive(uName, ReceiveMissionStatus.已接取, 1);
//		List<Mission> missions=pageBean.getList();
//		for(Mission mission:missions)
//			System.out.println("\nSeriver查询个人接取的任务："+mission.getMissionNo()+" : "+mission.getMissionTitle());
		
		//完成任务
//		Mission mission=new Mission();
//		mission.setMissionNo(2);
//		mission.setuName("201710098005");
//		missionService.completeMission(mission);
		
		//关键字搜索
//		String word="急";
//		List<Mission> missions=missionService.titleKeyWord(MissionType.寻物, word);
//		missions.addAll(missionService.detailKeyWord(MissionType.寻物, word));
//		String string=JSON.toJSON(missions).toString();
//		System.out.println("test\n"+string);
		
		//确认完成
//		int missionNo=1;
//		int i=missionService.missionProfile(missionNo);
//		System.out.println("test\n"+i);
		
		
//		File f=new File("D:\\javaweb\\2121.png");
//		FileInputStream inputStream=new FileInputStream(f);
//		MultipartFile file=new MockMultipartFile("file", f.getName(),"text/plain",IOUtils.toByteArray(inputStream));
//		String filePath="D:\\javaweb\\pic";
//		String originalFileName=file.getOriginalFilename();
//		System.out.println("o:"+originalFileName);
//		String newFileName=UUID.randomUUID()+originalFileName;
//		System.out.println("o:"+newFileName);
		
		
		//删除任务
//		missionService.deleteMission(21);
		
		//发布任务
//		Mission mission=new Mission();
//		mission.setMissionTitle("测试标题");
//		mission.setMissionDetail("测试详情");
//		mission.setCount(1);
//		mission.setMissionType(MissionType.寻物);
//		mission.setuName("201710098029");
//		missionService.addMission(mission);
		
		//任务详情
//		missionService.query(2);
	}
	
	MyEncoderByMd5 md5=new MyEncoderByMd5();
	
	//@Test
	public void testLogin() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService userService = ac.getBean(UserService.class);
		User user = new User();
		user.setuName("123450");
		user.setuPassword("123456");
		user.setName("啾啾");
		user.setAcademy("计算机工程学院");
		user.setGrade("四班");
		user.setMajor("软件");
		user.setTel("15810000000");
		userService.addUser(user);
		//userService.login(user);
		//String uName="12345";
		//userService.queryUser(uName);
	}
}
