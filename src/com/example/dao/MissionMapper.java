package com.example.dao;

import java.util.List;
/**
 * 
 * @author XRDMM
 *
 */

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.apache.ibatis.annotations.Param;

import com.example.domain.Mission;
import com.example.domain.MissionStatus;
import com.example.domain.MissionType;
import com.example.domain.Picture;
import com.example.domain.ReceiveMissionStatus;
public interface MissionMapper {

	/**
	 * 添加订单
	 * @param order
	 */
	public int addMission(Mission mission);
	/**
	 * 查询该任务是否由此用户发布
	 * @param mission
	 * @return
	 */
	public int countExist(Mission mission);
	
	
	/**
	 * 已修改
	 * 查询个人发布未被接取的任务/查询个人发布已被接取的任务/查询个人发布已被完成的任务
	 * @param uName
	 * @return
	 */
	public List<Mission> querySelf(@Param("uName")String uName,@Param("missionStatus")String missionStatus,
											 @Param("starRow")int starRow,@Param("pageSize")int pageSize);
	
	/**
	 * 查询个人已接取的任务/查询个人已完成的任务
	 * @param receive
	 * @return
	 */
	public List<Mission> querySelfNOrYReceive(@Param("uName")String uName,@Param("receiveMissionStatus")String receiveMissionStatus,
			 								  @Param("starRow")int starRow,@Param("pageSize")int pageSize);
	
	/**
	 * 查询他人订单
	 * @return
	 */
	public Mission query(@Param("missionNo")int missionNo);
	
	
	/**
	 * 已修改
	 * 根据类型查询任务
	 * 
	 * @return
	 */
	public List<Mission> queryType(@Param("missionType")String missionType,
								   @Param("word")String word,
								   @Param("starRow")int starRow,@Param("pageSize")int pageSize);
	
	
	/**
	 * 已修改
	 * 接收任务
	 * @param mission
	 */
	public int receiveMission(Mission mission);
	/**
	 * 查看mission表中count的数量
	 * @param mission
	 * @return
	 */
	public int count(Mission mission);
	
	/**
	 * 查询receive表中的count
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
	 * 已修改
	 * 完成订单
	 * @param mission
	 */
	public int completeMission(Mission mission);
	/**
	 * 完成订单
	 * @param mission
	 */
	public int completeReceive(Mission mission);
	/**
	 * 查看receive已完成的总数
	 * @param missionNo
	 * @return
	 */
	public int countComplete(@Param("missionNo")int missionNo);
	
	/**
	 * 标题关键字查询
	 * @param missionType
	 * @param word
	 * @return
	 */
	public List<Mission> titleKeyWord(@Param("missionType")String missionType,@Param("missionTitle")String word);
	
	/**
	 * 详细关键字查询
	 * @param missionType
	 * @param word
	 * @return
	 */
	public List<Mission> detailKeyWord(@Param("missionType")String missionType,@Param("missionDetail")String word);
	
	/**
	 * 发布者确认任务已被完成(修改任务状态)
	 * @param missionNo
	 * @param uName
	 */
	public int missionProfile(@Param("missionNo")int missionNo);
	/**
	 * 修改接取者任务状态为已被确认
	 * @param missionNo
	 * @return
	 */
	public int  receiveProfile(@Param("missionNo")int missionNo);
	
	/**
	 * 查询未被接取任务的总数
	 * @return
	 */
	public int countMission(@Param("missionType")String missionType,@Param("word")String word);

	/**
	 * 根据任务状态查询任务数
	 * @param missionStatus
	 * @return
	 */
	public int countMissionStatus(@Param("missionStatus")String missionStatus);
	
	/**
	 * 上传图片
	 * @param picture
	 */
	public int addPic(Picture picture);
	/**
	 * 查询图片
	 * @param missionNo
	 * @return
	 */
	public List<Picture> queryPic(@Param("missionNo")int missionNo);
	
	/**
	 * 删除任务
	 * @param missionNo
	 * @return
	 */
	public int deleteMission(@Param("missionNo")int missionNo);
	
	/**
	 * 删除图片
	 * @param missionNo
	 */
	public void deletePicture(@Param("missionNo")int missionNo);
}
