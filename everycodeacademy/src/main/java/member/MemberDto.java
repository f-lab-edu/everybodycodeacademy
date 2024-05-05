package member;

import java.time.LocalDateTime;

public class MemberDto {

    private Long id;
    private String email;
    private LocalDateTime date;

    // 생성자
    public MemberDto () {
    }

    public MemberDto (Long id, String email, LocalDateTime date) {
        this.id = id;
        this.email = email;
        this.date = date;
    }

    // 게터와 세터 메소드
    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public LocalDateTime getDate () {
        return date;
    }

    public void setDate (LocalDateTime date) {
        this.date = date;
    }

    // toString() 메소드
    @Override
    public String toString () {
        return "MemberDto{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", date=" + date +
            '}';
    }
}
