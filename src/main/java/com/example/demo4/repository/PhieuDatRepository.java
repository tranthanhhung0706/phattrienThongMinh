package com.example.demo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo4.entity.Mathang;
import com.example.demo4.entity.Phieudat;

public interface PhieuDatRepository extends JpaRepository<Phieudat, String> {
	@Query(value = "CALL getLastPD();",nativeQuery = true)
	public List<Phieudat> getLastPD();
	@Query(value = "CALL getPDByKH(:makh);", nativeQuery = true)
	public List<Phieudat> getPDByKH(@Param("makh") String makh);
}
