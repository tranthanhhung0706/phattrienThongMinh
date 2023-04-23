package com.example.demo4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo4.entity.Chatlieu;
import com.example.demo4.repository.ChatLieuRepository;

@Service
public class ChatLieuServiceImpl implements ChatLieuService{
  @Autowired
  private ChatLieuRepository chatLieuRepository;
	@Override
	public List<Chatlieu> getAllChatLieu() {
		// TODO Auto-generated method stub
		return chatLieuRepository.findAll();
	}

}
