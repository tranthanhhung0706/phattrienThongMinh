package com.example.demo4.service;

import java.util.List;

import com.example.demo4.entity.Phieudat;

public interface PhieuDatService {
   void save(Phieudat pd);
   List<Phieudat> getLastPD();
   List<Phieudat> getPDByKH(String makh);
}
