package mini.jppp.miniProj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WordController {

    @GetMapping("/learn")
    public String learn() {
        return "learn.html";
    }
}
