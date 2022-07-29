package com.example.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper mapper;
	
	 @Autowired
	 private PasswordEncoder encoder;
	
	//ユーザ登録
	@Override
	public void signup(MUser user) {
		user.setDepartmentId(1);//部署
		user.setRole("ROLE_GENERAL");//ロール
		
		//パスワード
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
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
		//パスワード暗号化
		
		String encryptPassword = encoder.encode(password);
		mapper.updateOne(userId,encryptPassword,userName);
		//int i=1/0;
	}

	@Override
	public void deleteUserOne(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		int count = mapper.deleteOne(userId);
		
	}
	//ログインユーザ取得
	@Override
	public MUser getLoginUser(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return mapper.findLoginUser(userId);
	}

}
