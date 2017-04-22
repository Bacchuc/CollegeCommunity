package com.yzd.collegecommunity.modeal;

import java.io.Serializable;
import java.util.Date;

public class GU implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private Goods collect_goods;
	private User collect_goods_user;
	private Date collectDate;
	
	public GU() {
	}

	public GU(Long id, Goods collect_goods, User collect_goods_user, Date collectDate) {
		super();
		this.id = id;
		this.collect_goods = collect_goods;
		this.collect_goods_user = collect_goods_user;
		this.collectDate = collectDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Goods getCollect_goods() {
		return collect_goods;
	}

	public void setCollect_goods(Goods collect_goods) {
		this.collect_goods = collect_goods;
	}
	public Date getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	public User getCollect_goods_user() {
		return collect_goods_user;
	}

	public void setCollect_goods_user(User collect_goods_user) {
		this.collect_goods_user = collect_goods_user;
	}
	
}
