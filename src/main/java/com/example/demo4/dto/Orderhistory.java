package com.example.demo4.dto;

import java.util.List;

public class Orderhistory {
    private String mapd;
    private List<Item> items;
    private String date;
    
	public Orderhistory() {
		super();
	}
	public Orderhistory(String mapd, List<Item> items, String date) {
		super();
		this.mapd = mapd;
		this.items = items;
		this.date = date;
	}
	public String getMapd() {
		return mapd;
	}
	public void setMapd(String mapd) {
		this.mapd = mapd;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
    
}
