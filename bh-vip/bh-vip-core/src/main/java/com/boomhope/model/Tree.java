package com.boomhope.model;


/**
 * 前端树对象模型（使用场景：角色中的大标题和子标题及生成树）
 * @version 1.0  2017-03-09
 * @author zy
 *
 */
public class Tree {
	
	private String id;
	
	private String parentId;
	
	private String name;
	
	private boolean checked;
	
	private String menuPath;

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
