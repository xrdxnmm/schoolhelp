package com.example.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.MissionMapper;
import com.example.domain.Mission;
import com.example.domain.MissionStatus;
import com.example.domain.MissionType;
import com.example.domain.PageBean;
import com.example.domain.Picture;
import com.example.domain.ReceiveMissionStatus;
import com.example.service.MissionService;
import com.example.utils.MyEncoderByMd5;
/**
 * 
 * @author XRDMM
 *
 */

@Service("missionService")
@Transactional
public class MissionServiceImpl implements MissionService{
	
	@Autowired
	MissionMapper missionMapper;
	final static int PAGESIZE=10; 
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public int addMission(Mission mission) {
		System.out.println("Seriver:添加mission:"+mission.getuName());
		int result=missionMapper.addMission(mission);
		System.out.println("Seriver:result:"+result);
		return result;
	}
	
	/**
	 * 已修改
	 * 分页查询个人发布未被接取的任务/查询个人发布已被接取的任务/查询个人发布已被完成的任务
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public PageBean<Mission> querySelf(String uName,MissionStatus missionStatus,int pageNo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println("Seriver:查询个人发布订单");
		System.out.println("Seriver:"+uName+" | "+missionStatus+" | "+pageNo);
		List<Mission> list=missionMapper.querySelf(uName,missionStatus.toString(),(pageNo-1)*PAGESIZE,PAGESIZE);
		for(Mission mission:list) {
			mission.setCountReceive(missionMapper.countReceive(mission));
			System.out.println("Seriver:"+mission.getMissionNo()+" | "+mission.getCount());
		}
		PageBean<Mission> pageBean=new PageBean<Mission>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(PAGESIZE);
		int count=missionMapper.countMissionStatus(missionStatus.toString());
		pageBean.setTotalCount(count);
		if(count%PAGESIZE==0) {
			pageBean.setTotalPage(count/PAGESIZE);
		}else {
			pageBean.setTotalPage(count/PAGESIZE+1);
		}	
		pageBean.setList(list);
		return pageBean;
	}
	
	/**
	 * 已修改
	 * 分页查询个人接取的任务/查询个人完成的任务
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public PageBean<Mission> querySelfNOrYReceive(String uName,ReceiveMissionStatus receiveMissionStatus,int pageNo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println("Seriver:查询个人接收订单:"+receiveMissionStatus);
		
		List<Mission> list=missionMapper.querySelfNOrYReceive(uName,receiveMissionStatus.toString(),(pageNo-1)*PAGESIZE,PAGESIZE);
		for(Mission mission:list) {
			mission.setCountReceive(missionMapper.countReceive(mission));
			System.out.println("Seriver:"+mission.getMissionNo()+" | "+mission.getCount());
		}
		PageBean<Mission> pageBean=new PageBean<Mission>();
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(PAGESIZE);
		int count=missionMapper.countMissionStatus(receiveMissionStatus.toString());
		pageBean.setTotalCount(count);
		if (count%PAGESIZE==0) {
			pageBean.setTotalPage(count/PAGESIZE);
		}
		else {
			pageBean.setTotalPage(count/PAGESIZE+1);
		}
		pageBean.setList(list);
		for(Mission mission:list)
			System.out.println("Seriver根据任务状态查询个人接取任务："+mission.getMissionNo());
		
		return pageBean;
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public Mission query(int missionNo) {
		System.out.println("Seriver:查询任务详情");
		Mission mission=missionMapper.query(missionNo);
		mission.setCountReceive(missionMapper.countReceive(mission));
		System.out.println("Seriver根据任务状态查询个人接取任务："+mission.getMissionTitle()+" | "+mission.getCountReceive());
		return mission;
	}

	/**
	 * 已修改
	 * 分页根据类型查询任务
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public PageBean<Mission> queryType(MissionType missionType,String word,int pageNo) {
		System.out.println("Seriver:根据任务类型查询");
		PageBean<Mission> pageBean=new PageBean<Mission>();
		System.out.println("当前页："+pageNo);
		pageBean.setPageNo(pageNo);
		pageBean.setPageSize(PAGESIZE);
		int countMission=missionMapper.countMission(missionType.toString(),word);
		System.out.println("size2:"+missionMapper.countMission(missionType.toString(),word));
		pageBean.setTotalCount(countMission);		
		if ((countMission%PAGESIZE)==0) {
			pageBean.setTotalPage(countMission/PAGESIZE);	
		}
		else {
			pageBean.setTotalPage(countMission/PAGESIZE+1);	
		}
		List<Mission> list=missionMapper.queryType(missionType.toString(),word,(pageNo-1)*PAGESIZE,PAGESIZE);
				
		System.out.println("Seriver类型："+missionType+" 关键字:"+word);
		for(Mission mission:list) {
			mission.setCountReceive(missionMapper.countReceive(mission));
			System.out.println("Seriver类型筛选："+mission.getMissionNo()+" | "+mission.getCountReceive());	
		}

		pageBean.setList(list);
		return pageBean;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 已修改,判断mission表中的count和receive表中的count一样
	 * 则改为已被接取
	 * 接取任务
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public int receiveMission(Mission mission) {	
		int result=0;
		int count=missionMapper.countReceive(mission);
		if (missionMapper.count(mission)>count
				&& (missionMapper.countID(mission))==0
				&&missionMapper.countExist(mission)==0) {
			result=missionMapper.addReceive(mission);
			if(result>0&&count==0)
				missionMapper.receiveMission(mission);
			System.out.println("Seriver：接收任务:"+"任务号:"+mission.getMissionNo());
		}
		return result;
			
	}
	/**
	 * 接取任务前查询count
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public int count(Mission mission) {
		int count=missionMapper.count(mission);
		System.out.println("Seriver:count="+count);
		return count;
	}	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public int countReceive(Mission mission) {
		int count =missionMapper.countReceive(mission);
		System.out.println("Seriver:countReceive="+count);
		return count;
	}
	/**
	 * 为receive表添加数据
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public int addReceive(Mission mission) {
		int result=missionMapper.addReceive(mission);
		System.out.println("Seriver:添加receive表数据");
		return result;
	} 
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public int countID(Mission mission) {
		int count=missionMapper.countID(mission);
		System.out.println("Seriver:是否接取过该订单："+count);
		return count;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 已修改
	 * 修改任务状态 已完成|已被完成
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
	public int completeMission(Mission mission) {
		int result=missionMapper.completeReceive(mission);
		System.out.println("Seriver：修改receive的状态"+mission.getMissionNo());
		//判断mission表中count是否等于receive表中已完成的任务数量，是则修改mission的状态
		if(result!=0) {
			if(missionMapper.count(mission)==missionMapper.countComplete(mission.getMissionNo())) {
				int i=missionMapper.completeMission(mission);
				System.out.println("Seriver：修改mission的状态"+mission.getMissionNo());
				System.out.println("Seriver：完成任务:"+"result:"+i);
				return i;
			}
			
		}
		System.out.println("Seriver：完成任务:"+"任务号:"+mission.getMissionNo());
		System.out.println("Seriver：完成任务:"+"result:"+result);
		return result;
			
		
	}
	
//	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)	
//	public List<Mission> titleKeyWord(MissionType missionType,String word){
//		List<Mission> missions=missionMapper.titleKeyWord(missionType.toString(), word);
//		System.out.println("Seriver:标题关键字"+word);
//		for(Mission mission:missions) {
//			System.out.println("\n标题查询结果:"+mission.getMissionTitle());
//		}
//		return missions;
//	}
//	
//	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)	
//	public List<Mission> detailKeyWord(MissionType missionType,String word){
//		List<Mission> missions=missionMapper.detailKeyWord(missionType.toString(), word);
//		System.out.println("Seriver:详细关键字"+word);
//		for(Mission mission:missions) {
//			System.out.println("\n详细查询结果:"+mission.getMissionDetail());
//		}
//		return missions;
//	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)	
	public int missionProfile(int missionNo) {
		int result=missionMapper.missionProfile(missionNo);
		if(result>0)
			result=missionMapper.receiveProfile(missionNo);
		System.out.println("Seriver：result:"+result);
		System.out.println("Seriver：修改mission的状态"+missionNo);
		return result;
	}
	
	private final static String filePath="D:\\javaweb\\pic";
	public int addPic(int missionNo,MultipartFile[] multipartFiles) {
		String originalFileName;
		String newFileName;
		int result=0;
		for(MultipartFile file:multipartFiles) {
			originalFileName=file.getOriginalFilename();
			newFileName=UUID.randomUUID()+originalFileName;
			File targetFile=new File(filePath,newFileName);
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Picture picture=new Picture();
			picture.setPicPath(newFileName);
			picture.setMissionNo(missionNo);
			System.out.println("seriver:"+newFileName+" | "+missionNo);
			result=missionMapper.addPic(picture);
		//	picture.setMissionNo(missionNo);
		}
		return result;
	}
	
	public List<Picture> queryPic(int missionNo){
		List<Picture> pictures=missionMapper.queryPic(missionNo);
		for(Picture picture:pictures)
			System.out.println("Seriver:pic:"+picture.getMissionNo()+" | "+picture.getPicPath());
		return pictures;
	}
	
	/**
	 * 删除任务
	 * @param missionNo
	 * @return
	 */
	public int deleteMission(int missionNo) {
		System.out.println("Seriver:missionNo:"+missionNo);
		int result=missionMapper.deleteMission(missionNo);
		if(result>0)
			missionMapper.deletePicture(missionNo);
		System.out.println("result:"+result);
		return result;
	}
	

}
