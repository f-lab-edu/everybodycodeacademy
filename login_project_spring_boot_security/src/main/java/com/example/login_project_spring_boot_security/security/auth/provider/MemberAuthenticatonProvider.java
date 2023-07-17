package com.example.login_project_spring_boot_security.security.auth.provider;

import com.example.login_project_spring_boot_security.member.entity.Member;
import com.example.login_project_spring_boot_security.security.MemberPrincipalDetailService;

import com.example.login_project_spring_boot_security.security.MemberPrincipalDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
// AuthenticationProvider 를 구현한 클래스
public class MemberAuthenticatonProvider implements AuthenticationProvider {

  @Autowired private MemberPrincipalDetailService memberPrincipalDetailService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName(); // 사용자가 입력한 id
    String password = (String) authentication.getCredentials(); // 사용자가 입력한 password

    // 생성해둔 MemberPrioncipalDetailService 에서 loadUserByUsername 메소드를 호출하여 사용자 정보를 가져온다.
    MemberPrincipalDetails memberPrincipalDetails =
        (MemberPrincipalDetails) memberPrincipalDetailService.loadUserByUsername(username);

    // db에 저장된 password
    String dbPassword = memberPrincipalDetails.getPassword();
    // 암호화 방식 (BCryptPasswordEncoder)를 사용하여 비밀번호를 비교한다.
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    if (!passwordEncoder.matches(password, dbPassword)) {
      System.out.println("[사용자] 비밀번호가 일치하지 않습니다.");
      throw new BadCredentialsException("[사용자] 아이디 또는 비밀번호가 일치하지 않습니다.");
    }

    Member member = memberPrincipalDetails.getMember();
    if (member == null || "N".equals(member.getIsUsed())) {
      System.out.println("[사용자] 사용할 수 없는 계정입니다.");
      throw new BadCredentialsException("[사용자] 사용할 수 없는 계정입니다.");
    }

    return new UsernamePasswordAuthenticationToken(
        memberPrincipalDetails, null, memberPrincipalDetails.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
