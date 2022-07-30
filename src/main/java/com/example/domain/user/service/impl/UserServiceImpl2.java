package com.example.domain.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.repository.UserRepository;

@Service
@Primary
public class UserServiceImpl2 implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	 @Autowired
	 private PasswordEncoder encoder;
	
	//ユーザ登録
	@Transactional
	@Override
	public void signup(MUser user) {
		
		//存在チェック
		boolean exists = repository.existsById(user.getUserId());
		if(exists) {
			throw new DataAccessException("ユーザが既に存在") {};
		}
		
		user.setDepartmentId(1);//部署
		user.setRole("ROLE_GENERAL");//ロール
		
		//パスワード
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		//insert
		repository.save(user);
	}
	
	//ユーザ取得
	@Override
	public List<MUser> getUsers(MUser user) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}
	//ユーザ取得(1件)
	@Override
	public MUser getUserOne(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		return user;
	}

	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		// TODO 自動生成されたメソッド・スタブ
		//パスワード暗号化
		
//		String encryptPassword = encoder.encode(password);
//		mapper.updateOne(userId,encryptPassword,userName);
		//int i=1/0;
	}

	@Transactional
	@Override
	public void deleteUserOne(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteById(userId);
		
	}
	//ログインユーザ取得
	@Override
	public MUser getLoginUser(String userId) {
		// TODO 自動生成されたメソッド・スタブ
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		return user;
	}

}
