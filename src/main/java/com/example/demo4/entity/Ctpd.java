package com.example.demo4.entity;
// Generated Dec 14, 2022, 9:49:56 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Ctpd generated by hbm2java
 */
@Entity
@Table(name = "ctpd", catalog = "thoitrang")
public class Ctpd implements java.io.Serializable {

	private CtpdId id;
	private Mathang mathang;
	private Phieudat phieudat;
	private int soluong;

	public Ctpd() {
	}

	public Ctpd(CtpdId id, Mathang mathang, Phieudat phieudat, int soluong) {
		this.id = id;
		this.mathang = mathang;
		this.phieudat = phieudat;
		this.soluong = soluong;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "mapd", column = @Column(name = "MAPD", nullable = false, length = 10)),
			@AttributeOverride(name = "mamh", column = @Column(name = "MAMH", nullable = false, length = 10)) })
	public CtpdId getId() {
		return this.id;
	}

	public void setId(CtpdId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAMH", nullable = false, insertable = false, updatable = false)
	public Mathang getMathang() {
		return this.mathang;
	}

	public void setMathang(Mathang mathang) {
		this.mathang = mathang;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAPD", nullable = false, insertable = false, updatable = false)
	public Phieudat getPhieudat() {
		return this.phieudat;
	}

	public void setPhieudat(Phieudat phieudat) {
		this.phieudat = phieudat;
	}

	@Column(name = "SOLUONG", nullable = false)
	public int getSoluong() {
		return this.soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

}
