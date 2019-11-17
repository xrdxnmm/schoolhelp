package com.example.domain;

import java.sql.Date;
/**
 * 
 * @author XRDMM
 *
 */
public class Mission {

	private int missionNo;//订单号
	private String missionTitle;
	private String uName;//用户名
	private String missionDetail;//订单详情	
	private MissionType missionType;//订单类型	
	private Date startTime;//开始时间
	private Date endTime;//结束时间
	private MissionStatus missionStatus;//̬发布者订单状态
	private int count;	//规定人数
	
	private int countReceive;//临时存储
	//private String receiver;//接收订单者	
	//private receiveMissionStatus receiveOrderStatus;//接收者订单状态״̬

	
	
	public int getMissionNo() {
		return missionNo;
	}

	public int getCountReceive() {
		return countReceive;
	}

	public void setCountReceive(int countReceive) {
		this.countReceive = countReceive;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getMissionTitle() {
		return missionTitle;
	}

	public void setMissionTitle(String missionTitle) {
		this.missionTitle = missionTitle;
	}

	public void setMissionNo(int missionNo) {
		this.missionNo = missionNo;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getMissionDetail() {
		return missionDetail;
	}

	public void setMissionDetail(String missionDetail) {
		this.missionDetail = missionDetail;
	}

	public MissionType getMissionType() {
		return missionType;
	}

	public void setMissionType(MissionType missionType) {
		this.missionType = missionType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public MissionStatus getMissionStatus() {
		return missionStatus;
	}

	public void setMissionStatus(MissionStatus missionStatus) {
		this.missionStatus = missionStatus;
	}
	
	

	
}
