package miniJppp.miniProj.DTO;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import miniJppp.miniProj.entity.Inventory;
import miniJppp.miniProj.entity.Learn;
import miniJppp.miniProj.entity.Word;
import miniJppp.miniProj.repository.LearnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LearnDto {


    private String wordId;
    private boolean learn;
    private String nickname;


}
