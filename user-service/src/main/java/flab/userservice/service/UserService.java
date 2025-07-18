package flab.userservice.service;


import flab.userservice.dto.UserSignupRequest;
import flab.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import flab.userservice.entity.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 생성
    public Boolean create(UserSignupRequest user) {
        return userRepository.save(user);
    }

    // 전체 목록 조회
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // ID로 단건 조회
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // 이메일로 조회
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // 존재 여부 확인
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // 회원 삭제
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
