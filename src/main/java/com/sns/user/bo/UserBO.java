package com.sns.user.bo;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.sns.user.domain.UserSimpleDTO;
import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // JPA
public class UserBO {
	
	private final UserRepository userRepository;
	
	/**
	 * <b>아이디로 사용자 조회 (JPA)</b><br>
	 * 
	 * @param loginId 아이디
	 * @return 결과가 있으면 {@code UserEntity}, 없으면 {@code null}
	 */
	public UserEntity getUserEntity(String loginId) {
		return userRepository.findByLoginId(loginId).orElse(null);
	}
	
	
	/**
	 * 로그인 시 아이디, 비밀번호로 user 데이터 가져오기(JPA)<br>
	 * 
	 * @param loginId
	 * @param password
	 * @return
	 */
	public UserEntity getUserEntityByLoginIdPassword (String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password).orElse(null);
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
	
	@Deprecated
	// input: userId
	// output: UserEntity
	public UserEntity getUserEntityById(int id) {
		return userRepository.findById(id).orElse(null);
	}
	
	
	// UserEntity에서 loginId, imgPath, name만 가지고 온 DTO
	public UserSimpleDTO getUserSimpleById(int id) {
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		UserSimpleDTO userSimpleDTO = UserSimpleDTO.builder()
				.id(userEntity.getId())
				.loginId(userEntity.getLoginId())
				.imgPath(userEntity.getImgPath())
				.name(userEntity.getName())
				.build();
			
		return userSimpleDTO;
	}
	
}
