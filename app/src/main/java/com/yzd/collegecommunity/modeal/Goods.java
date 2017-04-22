package com.yzd.collegecommunity.modeal;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Goods implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;//编号
	private Date publisurDate;//发布时间
	private String description;//商品描述
	private String goods_photo;//商品图片
	private Double price;//商品价格
	private String state;//商品状态
	private User sell_user;//卖家
	private	User buy_user;//买家
	private Integer collectTimes;//收藏次数
	
	private Set<GU> goods_users=new HashSet<GU>();
	
	public Goods() {
		
	}

	public Goods(Long id, Date publisurDate, String description, String goods_photo, Double price, String state,
			User sell_user, User buy_user, Integer collectTimes, Set<GU> goods_users) {
		super();
		this.id = id;
		this.publisurDate = publisurDate;
		this.description = description;
		this.goods_photo = goods_photo;
		this.price = price;
		this.state = state;
		this.sell_user = sell_user;
		this.buy_user = buy_user;
		this.collectTimes = collectTimes;
		this.goods_users = goods_users;
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

	public String getGoods_photo() {
		return goods_photo;
	}

	public void setGoods_photo(String goods_photo) {
		this.goods_photo = goods_photo;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getSell_user() {
		return sell_user;
	}
	
	public void setSell_user(User sell_user) {
		this.sell_user = sell_user;
	}

	public User getBuy_user() {
		return buy_user;
	}

	public void setBuy_user(User buy_user) {
		this.buy_user = buy_user;
	}

	public Integer getCollectTimes() {
		return collectTimes;
	}

	public void setCollectTimes(Integer collectTimes) {
		this.collectTimes = collectTimes;
	}

	public Set<GU> getGoods_users() {
		return goods_users;
	}

	public void setGoods_users(Set<GU> goods_users) {
		this.goods_users = goods_users;
	}
	
}
