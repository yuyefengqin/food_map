package com.gok.food_map.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.user.dto.UserGetListDTO;
import com.gok.food_map.user.dto.UserSaveDTO;
import com.gok.food_map.user.service.UserService;
import com.gok.food_map.user.vo.UserGetListVO;
import com.gok.food_map.user.vo.UserRemoveDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class UserController {

    private final UserService userService;

    //获取列表
    @PostMapping("/getList")
    public IPage<UserGetListVO> getList(@RequestBody UserGetListDTO dto) {

        return userService.getList(dto);
    }

    //新增
    @PostMapping("/add")
    public void add(@RequestBody UserSaveDTO dto) {

        userService.add(dto);
    }

    //编辑
    @PostMapping("/edit")
    public void edit(@RequestBody UserSaveDTO dto) {

        userService.edit(dto);
    }

    //删除
    @PostMapping("/remove")
    public void remove(@RequestBody UserRemoveDTO dto) {

        userService.remove(dto);
    }
}
