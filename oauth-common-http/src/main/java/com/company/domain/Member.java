package com.company.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * Entity - 会员
 * @author umeox
 */
@Entity
@Table(name = "ux_member")
@JsonIgnoreProperties(value = {"roleList", "hibernateLazyInitializer", "hibernateLazyInitializer", "handler", "fieldHandler"})
public class Member implements Serializable{

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = true)
	private String username;// 帐号

	private String name;// 名称（昵称或者真实姓名，不同系统不同定义）

	private String password; // 密码;
	private String salt;// 加密密码的盐

	private byte state;// 用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 ,
	// 1:正常状态,2：用户被锁定.

	public Member(Member member){
		super();
		this.username = member.getUsername();
		this.password = member.getPassword();
		this.roleList = member.getRoleList();
	}

	public Member() {

	}
	@ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER) // 立即从数据库中进行加载数据
	@JoinTable(name = "ux_member_role", joinColumns = { @JoinColumn(name = "memberId") }, inverseJoinColumns = {
			@JoinColumn(name = "roleId") })
	private List<SysRole> roleList;// 一个用户具有多个角色



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public List<SysRole> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}

	/**
	 * 密码盐.
	 *
	 * @return
	 */
	public String getCredentialsSalt() {
		return this.username + this.salt;
	}

	@Override
	public String toString() {
		return "Member{" +
				"username='" + username + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", state=" + state +
				", roleList=" + roleList +
				'}';
	}
}
