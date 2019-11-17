package com.example.domain;

public class Receiver {

	private int id;//id号
	private int missionNo;//任务号
	private String uName;//用户名
	private ReceiveMissionStatus receiveMissionStatus;//接取任务状态
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMissionNo() {
		return missionNo;
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
	public ReceiveMissionStatus getReceiveMissionStatus() {
		return receiveMissionStatus;
	}
	public void setReceiveMissionStatus(ReceiveMissionStatus receiveMissionStatus) {
		this.receiveMissionStatus = receiveMissionStatus;
	}
	
	
}
