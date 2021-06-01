package com.example.demo.Entity;

import java.io.Serializable;


public class SinhVien implements Serializable{
	
	private String maso;
	private String hoten;
	private String ngaysinh;
	private String gioitinh;
	private String email;
	public String getMaso() {
		return maso;
	}
	public void setMaso(String maso) {
		this.maso = maso;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public String getGioitinh() {
		return gioitinh;
	}
	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public SinhVien(String maso, String hoten, String ngaysinh, String gioitinh, String email) {
		super();
		this.maso = maso;
		this.hoten = hoten;
		this.ngaysinh = ngaysinh;
		this.gioitinh = gioitinh;
		this.email = email;
	}
	public SinhVien() {
		super();
		this.maso = "";
		this.hoten = "";
		this.ngaysinh = "";
		this.gioitinh = "";
		this.email = "";
	}
	@Override
	public String toString() {
		return "SinhVien [maso=" + maso + ", hoten=" + hoten + ", ngaysinh=" + ngaysinh + ", gioitinh=" + gioitinh
				+ ", email=" + email + "]";
	}
	
	
}
