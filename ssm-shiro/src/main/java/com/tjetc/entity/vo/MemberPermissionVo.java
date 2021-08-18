package com.tjetc.entity.vo;

import java.util.List;

public class MemberPermissionVo {
	private Long memberId;
	private String memberUsername;
	private Boolean memberRoot;
	private List<String> permissionNames;
	
	public Long getMemberId() {
		return memberId;
	}
	
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	
	public String getMemberUsername() {
		return memberUsername;
	}
	
	public void setMemberUsername(String memberUsername) {
		this.memberUsername = memberUsername;
	}
	
	public Boolean getMemberRoot() {
		return memberRoot;
	}
	
	public void setMemberRoot(Boolean memberRoot) {
		this.memberRoot = memberRoot;
	}
	
	public List<String> getPermissionNames() {
		return permissionNames;
	}
	
	public void setPermissionNames(List<String> permissionNames) {
		this.permissionNames = permissionNames;
	}
}
