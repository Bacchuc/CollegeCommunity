package com.yzd.collegecommunity.modeal;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;			//编号
	private String username;	//用户名
	private String password;	//密码
	private String gender;		//性别
	private String telephone;	//手机号
	private Integer publishTimes; //发布次数
	private Integer acceptTimes; //接收次数
	private Integer finishTimes;//完成次数
	private Integer integrate; //积分
	private String path;//用户头像
	private String mail;//用户邮箱
	
	private Set<TU> task_users=new HashSet<TU>();
	private Set<GU> goods_users=new HashSet<GU>();
	//构造函数
	public User() {
	
	}


	public User(Long id, String username, String password, String gender, String telephone, Integer publishTimes,
			Integer acceptTimes, Integer finishTimes, Integer integrate, String path, String mail, Set<TU> task_users,
			Set<GU> goods_users) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.telephone = telephone;
		this.publishTimes = publishTimes;
		this.acceptTimes = acceptTimes;
		this.finishTimes = finishTimes;
		this.integrate = integrate;
		this.path = path;
		this.mail = mail;
		this.task_users = task_users;
		this.goods_users = goods_users;
	}

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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Set<TU> getTask_users() {
		return task_users;
	}

	public void setTask_users(Set<TU> task_users) {
		this.task_users = task_users;
	}

	public Integer getPublishTimes() {
		return publishTimes;
	}

	public void setPublishTimes(Integer publishTimes) {
		this.publishTimes = publishTimes;
	}

	public Integer getAcceptTimes() {
		return acceptTimes;
	}

	public void setAcceptTimes(Integer acceptTimes) {
		this.acceptTimes = acceptTimes;
	}
	public Integer getIntegrate() {
		return integrate;
	}

	public void setIntegrate(Integer integrate) {
		this.integrate = integrate;
	}

	public Integer getFinishTimes() {
		return finishTimes;
	}

	public void setFinishTimes(Integer finishTimes) {
		this.finishTimes = finishTimes;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Set<GU> getGoods_users() {
		return goods_users;
	}

	public void setGoods_users(Set<GU> goods_users) {
		this.goods_users = goods_users;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}
}

