package com.example.domain;

import java.io.Serializable;

public class UploadMsg implements Serializable{
    private String per;//���ϴ��ļ��İٷֱ�
    private String speed;//�ļ��ϴ����ٶ�
    private String remainTime;//�ϴ���ʣ��ʱ��
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
