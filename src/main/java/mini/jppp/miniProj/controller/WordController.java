package mini.jppp.miniProj.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class WordController {

    @GetMapping
    public String mian() {
        return "main/ww_main.html";
    }

    @GetMapping("/learn")
    public String learn() {
        return"main/ww_learn.html";
    }

    @GetMapping("/inventory")
    public String inventory() {
        return"main/inventory.html";
    }


}
