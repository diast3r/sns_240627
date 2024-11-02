package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service // JPA
public class UserBO {
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * <b>아이디로 사용자 조회 (JPA)</b><br><br>
	 * 
	 * @param loginId 아이디
	 * @return 결과가 있으면 {@code UserEntity}, 없으면 {@code null}
	 */
	public UserEntity getUserEntity(String loginId) {
		return userRepository.findByLoginId(loginId).orElse(null);
	}
	
	/**
	 * <b>회원가입 (JPA)</b><br><br>
	 * 
	 * @param loginId 아이디
	 * @param password 비밀번호(암호화)
	 * @param name 이름
	 * @param email 이메일
	 * @return 성공하면 추가된 UserEntity 예외 시 null
	 * @throws OptimisticLockingFailureException - 낙관적 락 충돌(내가 수정하려는 동안 이미 데이터가 수정됨.)
	 * 
	 */
	public UserEntity addUser(
			String loginId, String password, 
			String name, String email) {
		try {
			return userRepository.save(UserEntity.builder()
					.loginId(loginId)
					.password(password)
					.name(name)
					.email(email)
					.build());
		} catch(Exception e) {
			return null;
		}
	}
	
}
