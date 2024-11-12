
package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private UserBO userBO;
	
	/**
	 * <b>아이디 중복 확인(JPA)</b><br><br>
	 * 
	 * user 테이블에서 loginId로 아이디 중복 확인 (JPA)
	 * 
	 * @param loginId signUp.html 에서 form태그로 받음.
	 * @return 
	 * 	사용 가능 => {"code":200, "is_duplicate_id":false}<br>
	 * 	아이디 중복 => {"code":200, "is_duplicate_id":true}<br>
	 * 	에러 {"code":500, "error_message":"에러 이유"} - 에러 구분하는 로직 아직 없음.<br>
	 */
	@GetMapping("/is-duplicate-id")
	public Map<String, Object> isDuplicateId(
			@RequestParam("loginId") String loginId) {
		Map<String, Object> result = new HashMap<>();
		if (userBO.getUserEntity(loginId) != null) {
			result.put("code", 200);
			result.put("is_duplicate_id", true);
		} else {
			result.put("code", 200);
			result.put("is_duplicate_id", false);
		}
		
		// TODO 조회 실패 시 에러 콜백해주는 로직 구현
		
		
		
		return result;
		
	}
	
	/**
	 * <b>회원가입(JPA)</b><br><br>
	 * 아이디, 비밀번호, 이름, 이메일을 받아 insert하고 <br>
	 * 성공 여부를 json으로 반환한다.<br>
	 * @param loginId 아이디
	 * @param password 비밀번호
	 * @param name 이름
	 * @param email 이메일
	 * @return 성공 시 {"code":200, "result":"성공"}, 실패 시 {"code":500, "error_message":"에러 이유"}
	 */
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId, 
			@RequestParam("password") String password, 
			@RequestParam("name") String name, 
			@RequestParam("email") String email) {
		
		// 암호화
		String hashedPassword = EncryptUtils.md5(password);
		
		// db insert
		UserEntity user = userBO.addUser(loginId, hashedPassword, name, email);
		
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("error_message", "뭔가 문제 발생");
		}
			
		
		return result;
	}
	
	
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest request) {
		
		// hashing
		String hashedPassword = EncryptUtils.md5(password);
		
		UserEntity user = userBO.getUserEntityByLoginIdPassword(loginId, hashedPassword);
		
		Map<String, Object> result = new HashMap<>();
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 300);
			result.put("error_message", "존재하지 않는 사용자입니다.");
		}
		
		return result;
		
		
	}
	
}
