package miniJppp.miniProj.DTO;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Getter
@Builder
@AllArgsConstructor
public class MemberDto {
    public MemberDto() {
    }

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private String profileImgUrl;
}
