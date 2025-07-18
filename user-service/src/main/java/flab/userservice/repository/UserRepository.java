package flab.userservice.repository;

import flab.userservice.dto.UserSignupRequest;
import flab.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //회원가입
    Boolean save(UserSignupRequest user);

    // username 중복 확인
    boolean existsByUsername(String username);

    // email 중복 확인
    boolean existsByEmail(String email);

    // email로 사용자 찾기
    Optional<User> findByEmail(String email);

    // 소셜 로그인 사용자 찾기 (KAKAO, GOOGLE 등)
    Optional<User> findByLoginProviderAndEmail(String loginProvider, String email);

    // providerId로 사용자 찾기 (KAKAO 고유 ID 등)
    Optional<User> findByLoginProviderAndNickname(String loginProvider, String nickname);

    // 필요시: email + providerId 조합
    Optional<User> findByLoginProviderAndEmailAndNickname(String loginProvider, String email, String nickname);
}
