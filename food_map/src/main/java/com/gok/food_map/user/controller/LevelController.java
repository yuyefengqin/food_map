package com.gok.food_map.user.controller;

import com.gok.food_map.user.dto.LevelSaveDTO;
import com.gok.food_map.user.entity.MemberLevel;
import com.gok.food_map.user.service.MemberLevelService;
import com.gok.food_map.user.vo.LevelGetListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Lazy)
@RequestMapping("/level")
public class LevelController {
    private final MemberLevelService service;
    @PostMapping("/getList")
    public List<LevelGetListVO> getList(){
        return service.getList();
    }

    @PostMapping("/edit")
    public void editLevel(@RequestBody LevelSaveDTO dto) {
        service.edit(dto);
    }
    @PostMapping("/add")
    public void addLevel(@RequestBody LevelSaveDTO dto) {
        service.add(dto);
    }
    @PostMapping("/remove")
    public void removeLevel(@RequestBody int id) {
        service.delete(id);
    }
}
