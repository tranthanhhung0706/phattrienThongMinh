package com.example.demo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo4.entity.Phieudat;
import com.example.demo4.repository.PhieuDatRepository;

@Service
public class PhieuDarServiceImpl implements PhieuDatService{
     @Autowired
     private PhieuDatRepository phieuDatRepository;
	@Override
	public void save(Phieudat pd) {
		// TODO Auto-generated method stub
		phieuDatRepository.save(pd);
	}
	@Override
	public List<Phieudat> getLastPD() {
		// TODO Auto-generated method stub
		return phieuDatRepository.getLastPD();
	}
	@Override
	public List<Phieudat> getPDByKH(String makh) {
		// TODO Auto-generated method stub
		return phieuDatRepository.getPDByKH(makh);
	}

}
