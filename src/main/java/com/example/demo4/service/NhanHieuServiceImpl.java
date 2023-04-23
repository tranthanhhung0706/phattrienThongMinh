package com.example.demo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo4.entity.Nhanhieu;
import com.example.demo4.repository.NhanHieuRepository;

@Service
public class NhanHieuServiceImpl implements NhanHieuService {
   @Autowired 
   private NhanHieuRepository nhanHieuRepository;
	@Override
	public List<Nhanhieu> getAllNhanHieu() {
		// TODO Auto-generated method stub
		return nhanHieuRepository.findAll();
	}

}
