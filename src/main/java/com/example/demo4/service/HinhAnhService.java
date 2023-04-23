package com.example.demo4.service;

import java.util.List;

import com.example.demo4.entity.Hinhanhmh;

public interface HinhAnhService {
    List<Hinhanhmh> getHinhAnhAll();
    Hinhanhmh getHinhAnhByMH(String mamh);
}
