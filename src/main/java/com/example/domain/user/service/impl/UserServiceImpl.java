package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;
	
	//ユーザ登録
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);//部署
		user.setRole("ROLE_GENERAL");//ロール
		mapper.insertOne(user);
	}
	
	//ユーザ取得
	@Override
	public List<MUser> getUsers(MUser user) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.findMany(user);
	}

	@Override
	public MUser getUserOne(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.findOne(userId);
	}

	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		mapper.updateOne(userId,password,userName);
		//int i=1/0;
	}

	@Override
	public void deleteUserOne(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		int count = mapper.deleteOne(userId);
		
	}
}
