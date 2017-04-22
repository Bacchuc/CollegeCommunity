package com.yzd.collegecommunity.modeal;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Task implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//编号
	private Date publisurDate;//发布时间
	private String description;//任务描述
	private Double pay;//酬谢金额
	private String state;//任务状态
	private User publish_user;//发布者
	private	User accept_user;//接受者
	private String pic;//任务图片
	private Integer collectTimes;//收藏次数

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	private Set<TU> task_users=new HashSet<TU>();
	
	//构造函数
	public Task() {
		
	}

	public Task(Long id, Date publisurDate, String description, Double pay, String state, User publish_user,
			User accept_user, Integer collectTimes, Set<TU> task_users) {
		super();
		this.id = id;
		this.publisurDate = publisurDate;
		this.description = description;
		this.pay = pay;
		this.state = state;
		this.publish_user = publish_user;
		this.accept_user = accept_user;
		this.collectTimes = collectTimes;
		this.task_users = task_users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPublisurDate() {
		return publisurDate;
	}

	public void setPublisurDate(Date publisurDate) {
		this.publisurDate = publisurDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getPublish_user() {
		return publish_user;
	}

	public void setPublish_user(User publish_user) {
		this.publish_user = publish_user;
	}
	
	public User getAccept_user() {
		return accept_user;
	}

	public void setAccept_user(User accept_user) {
		this.accept_user = accept_user;
	}

	public Set<TU> getTask_users() {
		return task_users;
	}

	public void setTask_users(Set<TU> task_users) {
		this.task_users = task_users;
	}

	public Integer getCollectTimes() {
		return collectTimes;
	}

	public void setCollectTimes(Integer collectTimes) {
		this.collectTimes = collectTimes;
	}

}
