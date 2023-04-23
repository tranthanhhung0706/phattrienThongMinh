package com.example.demo4.dto;

import com.example.demo4.entity.Chatlieu;
import com.example.demo4.entity.Loaikh;
import com.example.demo4.entity.Loaimh;
import com.example.demo4.entity.Nhanhieu;

public class MathangDTO {
	private String mamh;
	private String tenmh;
	private String mota;
	private int trangthai;
	private Integer cachlam;
	private Integer gia;
	private Integer quantity;
	private String hinhanh;
	
	public MathangDTO() {
		super();
	}
	
	public MathangDTO(String mamh, String tenmh, String mota, int trangthai, Integer cachlam, Integer gia,
			Integer quantity, String hinhanh) {
		super();
		this.mamh = mamh;
		this.tenmh = tenmh;
		this.mota = mota;
		this.trangthai = trangthai;
		this.cachlam = cachlam;
		this.gia = gia;
		this.quantity = quantity;
		this.hinhanh = hinhanh;
	}

	public String getHinhanh() {
		return hinhanh;
	}
	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getMamh() {
		return mamh;
	}
	public void setMamh(String mamh) {
		this.mamh = mamh;
	}
	public String getTenmh() {
		return tenmh;
	}
	public void setTenmh(String tenmh) {
		this.tenmh = tenmh;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public int getTrangthai() {
		return trangthai;
	}
	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	public Integer getCachlam() {
		return cachlam;
	}
	public void setCachlam(Integer cachlam) {
		this.cachlam = cachlam;
	}
	public Integer getGia() {
		return gia;
	}
	public void setGia(Integer gia) {
		this.gia = gia;
	}
	
}
