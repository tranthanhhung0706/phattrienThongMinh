package com.example.demo4.converter;


import org.springframework.stereotype.Component;

import com.example.demo4.dto.MathangDTO;
import com.example.demo4.entity.Banggia;
import com.example.demo4.entity.Hinhanhmh;
import com.example.demo4.entity.Mathang;
@Component
public class Mathangconvert {
    public MathangDTO toDTO(Mathang entity,Banggia enity2,Hinhanhmh enity3) {
    	MathangDTO dto=new MathangDTO();
    	dto.setMamh(entity.getMamh());
    	dto.setTenmh(entity.getTenmh());
        dto.setGia(enity2.getGiamoi());
        dto.setHinhanh(enity3.getDuongdan());
        return dto;
    }
//    public MathangDTO toDTOHA(Mathang entity,Banggia enity2,Hinhanhmh enity3) {
//    	MathangDTO dto=new MathangDTO();
//    	dto.setMamh(entity.getMamh());
//    	dto.setTenmh(entity.getTenmh());
//        dto.setGia(enity2.getGiamoi());
//        dto.setHinhanh(enity3.getDuongdan());
//        return dto;
//    }
}
