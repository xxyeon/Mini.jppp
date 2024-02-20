package miniJppp.miniProj.controller;

import lombok.RequiredArgsConstructor;
import miniJppp.miniProj.DTO.LearnDto;
import miniJppp.miniProj.DTO.ReviewDto;
import miniJppp.miniProj.service.InventoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AjaxController {

    private final InventoryService inventoryService;

    @PostMapping("/save-learn-data")
    public String save_data(@RequestBody LearnDto learnDto) {

        inventoryService.saveLearnData(learnDto);
        return "success";
    }

    @PostMapping("/save-review-data")
    public String saveReviewData(@RequestBody ReviewDto reviewDto) {
        inventoryService.saveReviewData(reviewDto);
        return "success";
    }

    @PostMapping("/delete-review-data")
    public String deleteReviewData(@RequestBody ReviewDto reviewDto) {
        inventoryService.deleteReviewData(reviewDto);
        return "success";
    }

}
