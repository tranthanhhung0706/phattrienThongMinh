package com.example.demo4.service;

import java.util.List;

import com.example.demo4.entity.Ctpd;

public interface ChitTietPDService {
   void save(Ctpd ctpd);
   List<Ctpd> getctPD(String mapd);
   List<Ctpd> getctPD1(String makh);
}
