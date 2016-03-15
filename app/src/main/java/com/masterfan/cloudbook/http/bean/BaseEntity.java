package com.masterfan.cloudbook.http.bean;

import org.xutils.db.annotation.Column;

public class BaseEntity {
	/***/
	@Column(name = "_id", isId = true)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
