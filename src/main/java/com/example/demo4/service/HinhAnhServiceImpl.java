package com.example.demo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo4.entity.Hinhanhmh;
import com.example.demo4.repository.HinhAnhRepository;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {
  @Autowired
  private HinhAnhRepository hinhAnhRepository;

@Override
public List<Hinhanhmh> getHinhAnhAll() {
	// TODO Auto-generated method stub
	return hinhAnhRepository.findAll();
}

@Override
public Hinhanhmh getHinhAnhByMH(String mamh) {
	// TODO Auto-generated method stub
	return hinhAnhRepository.getHinhAnhByMH(mamh);
}
}
