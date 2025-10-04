package com.gok.food_map.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.district.service.DistrictService;
import com.gok.food_map.district.vo.DistrictGetVO;
import com.gok.food_map.user.dto.*;
import com.gok.food_map.user.service.MemberLevelService;
import com.gok.food_map.user.service.UserService;
import com.gok.food_map.user.vo.LevelGetListVO;
import com.gok.food_map.user.vo.UserGetListVO;
import com.gok.food_map.user.vo.UserLoginVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class UserController {

    private final MemberLevelService memberLevelService;
    private final UserService userService;
    private final DistrictService districtService;

    @PostMapping("/login")
    public UserLoginVO login(@RequestBody UserLoginDto dto, HttpServletRequest request) {
        return userService.userLogin(dto,request);
    }
    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDto dto){
        userService.UserRegister(dto);
    }
    //获取列表
    @PostMapping("/getList")
    public IPage<UserGetListVO> getList(@RequestBody UserGetListDTO dto) {
        return userService.getList(dto);
    }
    //初始化
    @PostMapping("/initLevel")
    public List<LevelGetListVO> initLevel() {
        return memberLevelService.getList();
    }

    @RequestMapping("/initDistricts")
    public List<DistrictGetVO> initDistricts() {
        return districtService.getByParent(null);
    }
    //新增
    @PostMapping("/add")
    public void add(@RequestBody UserSaveDTO dto) {
        userService.add(dto);
    }
    //导出
    @RequestMapping("/export")
    public void export( String code, String createTime, String current, String name, String size  , HttpServletResponse response) {
        UserGetListDTO dto = new UserGetListDTO(current,size,code,name,createTime);
        userService.export(dto, response);
    }

    //编辑
    @PostMapping("/edit")
    public void edit(@RequestBody UserSaveDTO dto) {
        userService.edit(dto);
    }
    //会员修改
    @PostMapping("/levelChange")
    public void levelChange(@RequestBody UserLevelChangeDTO dto) {
        userService.LevelUpdate(dto);
    }

    //删除
    @PostMapping("/remove")
    public void remove(@RequestBody UserRemoveDTO dto) {
        userService.remove(dto);
    }
}
