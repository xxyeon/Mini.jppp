package miniJppp.miniProj.controller;

import lombok.RequiredArgsConstructor;
import miniJppp.miniProj.DTO.UserInfo;
import miniJppp.miniProj.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MemberService memberService;

    @PostMapping("/update-info")
    public String updateUserInfo(UserInfo userInfo) {

        memberService.updateUserInfo(userInfo);

        return "redirect:/main/profile";
    }
}
