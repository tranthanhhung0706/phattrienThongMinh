package com.example.demo4.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo4.entity.Hinhanhmh;
import com.example.demo4.entity.Mathang;

public interface HinhAnhRepository extends JpaRepository<Hinhanhmh,String>{
//	@Query("SELECT ha FROM Hinhanhmh ha WHERE ha.maha = ?1")
//	public Hinhanhmh getHinhAnhByMH(String mamh);
	@Query(value = "CALL get_HAMH(:mamh);", nativeQuery = true)
	public Hinhanhmh getHinhAnhByMH(@Param("mamh") String mamh);
}
