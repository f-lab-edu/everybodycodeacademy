import React, { useState } from 'react';
import axios from 'axios';
  // 폼 제출 핸들러
  function SignupForm() {
    const [formData, setFormData] = useState({
      name: '',
      email: '',
      password: '',
      passwordConfirm: '',
    });

    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData({
        ...formData,
        [name]: value,
      });
    };

    const handleSubmit = async (e) => {
      e.preventDefault();

      if (formData.password !== formData.passwordConfirm) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
      }

      try {
        // axios를 사용하여 회원가입 요청을 보냅니다.
        const response = await axios.post('http://localhost:8080/member/signin', {
          name: formData.name,
          email: formData.email,
          password: formData.password,
        });

        // 요청이 성공적으로 처리됐다면,
        alert('회원가입 성공!');
      } catch (error) {
        // 요청이 실패했다면,
        alert('회원가입 실패.');
      }
    };

  return (
      <form onSubmit={handleSubmit}>
        <div>
          <label>이름:</label>
          <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              required
          />
        </div>
        <div>
          <label>이메일:</label>
          <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              required
          />
        </div>
        <div>
          <label>비밀번호:</label>
          <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
          />
          <label>비밀번호 확인:</label>
          <input
              type="password"
              name="passwordConfirm"
              value={formData.passwordConfirm}
              onChange={handleChange}
              required
          />
        </div>
        <button type="submit">회원 가입</button>
      </form>
  );
}

export default SignupForm;
