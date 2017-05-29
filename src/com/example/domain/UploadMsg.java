package com.example.domain;

import java.io.Serializable;

public class UploadMsg implements Serializable{
    private String per;//已上传文件的百分比
    private String speed;//文件上传的速度
    private String remainTime;//上传的剩余时间
	public String getPer() {
		return per;
	}
	public void setPer(String per) {
		this.per = per;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}
	@Override
	public String toString() {
		return "{per:" + per + ", remainTime:" + remainTime
				+ ", speed:" + speed+"}";
	}
}
