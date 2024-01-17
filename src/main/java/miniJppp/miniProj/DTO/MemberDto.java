package miniJppp.miniProj.DTO;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class MemberDto {
    public MemberDto() {
    }

    private Long id;
    private String name;
    private LocalDateTime createAt;
}
