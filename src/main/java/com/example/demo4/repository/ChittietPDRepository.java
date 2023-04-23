package com.example.demo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo4.entity.Ctpd;
import com.example.demo4.entity.CtpdId;
import com.example.demo4.entity.Phieudat;

public interface ChittietPDRepository extends JpaRepository<Ctpd, CtpdId> {
	@Query(value = "CALL getctPD(:mapd);", nativeQuery = true)
	public List<Ctpd> getctPD(@Param("mapd") String mapd);
	
	@Query(value = "CALL getctPD1(:makh);", nativeQuery = true)
	public List<Ctpd> getctPD1(@Param("makh") String makh);
}
