package miniJppp.miniProj.controller;

import lombok.RequiredArgsConstructor;
import miniJppp.miniProj.DTO.LearnDto;
import miniJppp.miniProj.service.InventoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
public class AjaxController {

    private final InventoryService inventoryService;

    @ResponseBody
    @PostMapping("/save-learn-data")
    public String save_data(@RequestBody LearnDto learnDto) {

        inventoryService.saveLearnData(learnDto);
        return "success";
    }
}
