package com.example.demo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo4.entity.Ctpd;
import com.example.demo4.repository.ChittietPDRepository;

@Service
public class ChiTietPDServiceImpl implements ChitTietPDService {
   @Autowired
   private ChittietPDRepository chittietPDRepository;

@Override
public void save(Ctpd ctpd) {
	// TODO Auto-generated method stub
	chittietPDRepository.save(ctpd);
   }

@Override
public List<Ctpd> getctPD(String mapd) {
	// TODO Auto-generated method stub
	return chittietPDRepository.getctPD(mapd);
}

@Override
public List<Ctpd> getctPD1(String makh) {
	// TODO Auto-generated method stub
	return chittietPDRepository.getctPD1(makh);
}
   
}
