package com.example.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Mission;
import com.example.domain.MissionStatus;
import com.example.domain.MissionType;
import com.example.domain.PageBean;
import com.example.domain.Picture;
import com.example.domain.ReceiveMissionStatus;
/**
 * 
 * @author XRDMM
 *
 */
public interface MissionService {

	/**
	 * 添加订单
	 * @param mission
	 */
	public int addMission(Mission mission);
	
	/**
	 * 查询个人发布还未被接取的任务
	 * @param uName
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public PageBean<Mission> querySelf(String uName,MissionStatus missionStatus,int starRow) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	/**
	 * 查询个人接收订单
	 * @param receiver
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public PageBean<Mission> querySelfNOrYReceive(String uName,ReceiveMissionStatus receiveMissionStatus,int starRow) throws NoSuchAlgorithmException, UnsupportedEncodingException;
	
	/**
	 * 查询他人订单
	 * @return
	 */
	public Mission query(int missionNo);
	
	/**
	 * 已修改
	 * 查询他人类型订单
	 * @return
	 */
	public PageBean<Mission> queryType(MissionType missionType,String word,int starRow);
	
	
	//接取任务
	/**
	 * 已修改
	 * 接收订单
	 * @param mission
	 */
	public int receiveMission(Mission mission);
	/**
	 * 接取任务前，查看任务人数是否足够
	 * @param mission
	 * @return
	 */
	public int count(Mission mission);
	/**
	 * 接取任务前，查询receive表中的count
	 * @param mission
	 * @return
	 */
	public int countReceive(Mission mission);	
	/**
	 * 为receive表添加数据
	 */
	public int addReceive(Mission mission);
	/**
	 * 查询该用户是否接过该任务
	 * @param mission
	 * @return
	 */
	public int countID(Mission mission);
	
	/**
	 * 完成订单
	 * @param order
	 */
	public int completeMission(Mission mission);
	
//	/**
//	 * 标题关键字查询
//	 * @param missionType
//	 * @param word
//	 * @return
//	 */
//	public List<Mission> titleKeyWord(MissionType missionType,String word);
//	/**
//	 * 详细关键字查询
//	 * @param missionType
//	 * @param word
//	 * @return
//	 */
//	public List<Mission>detailKeyWord(MissionType missionType,String word);
	/**
	 * 确认完成任务
	 * @param missionNo
	 */
	public int missionProfile(int missionNo);
	/**
	 * 添加图片
	 * @param picture
	 */
	public int addPic(int missionNo,MultipartFile[] multipartFiles);
	/**
	 * 查询图片
	 * @param missionNo
	 * @return
	 */
	public List<Picture> queryPic(int missionNo);
	
	/**
	 * 删除任务
	 * @param missionNo
	 * @return
	 */
	public int deleteMission(int missionNo);
	
}
