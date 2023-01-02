package com.sollyj.dto;

public class MemberDTO {
	private String mid;
	private String mpw;
	private String mname;
	private String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public MemberDTO() {
		super();
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getMpw() {
		return mpw;
	}

	public void setMpw(String mpw) {
		this.mpw = mpw;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	@Override
	public String toString() {
		return "MemberDTO{" +
			"mid='" + mid + '\'' +
			", mpw='" + mpw + '\'' +
			", mname='" + mname + '\'' +
			'}';
	}
}