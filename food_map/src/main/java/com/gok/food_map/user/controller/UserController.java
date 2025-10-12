package com.gok.food_map.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gok.food_map.annotation.Auth;
import com.gok.food_map.district.service.DistrictService;
import com.gok.food_map.district.vo.DistrictGetVO;
import com.gok.food_map.user.dto.*;
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

    private final UserService userService;
    private final DistrictService districtService;
    @Auth
    @PostMapping("/adminLogin")
    public UserLoginVO adminLogin(@RequestBody UserLoginDto dto,HttpServletRequest request) {
         return userService.adminLogin(dto,request);
    }

    @PostMapping("/login")
    public UserLoginVO login(@RequestBody UserLoginDto dto, HttpServletRequest request) {
        return userService.userLogin(dto,request);
    }
    @PostMapping("/register")
    public void register(@RequestBody UserRegisterDto dto){
        userService.UserRegister(dto);
    }
    //获取列表
    @Auth
    @PostMapping("/getList")
    public IPage<UserGetListVO> getList(@RequestBody UserGetListDTO dto) {
        return userService.getList(dto);
    }
    //初始化
    @Auth
    @PostMapping("/initLevel")
    public List<LevelGetListVO> initLevel() {
        return userService.init();
    }
    @Auth
    @RequestMapping("/initDistricts")
    public List<DistrictGetVO> initDistricts() {
        return districtService.getByParent(null);
    }
    //新增
    @Auth
    @PostMapping("/add")
    public void add(@RequestBody UserSaveDTO dto) {
        userService.add(dto);
    }
    //导出
    @Auth
    @RequestMapping("/export")
    public void export( String code, String createTime, String current, String name, String size  , HttpServletResponse response) {
        UserGetListDTO dto = new UserGetListDTO(current,size,code,name,createTime);
        userService.export(dto, response);
    }

    //编辑
    @Auth
    @PostMapping("/edit")
    public void edit(@RequestBody UserSaveDTO dto) {
        userService.edit(dto);
    }
    //会员修改
    @Auth
    @PostMapping("/levelChange")
    public void levelChange(@RequestBody UserLevelChangeDTO dto) {
        userService.LevelUpdate(dto);
    }

    //删除
    @Auth
    @PostMapping("/remove")
    public void remove(@RequestBody UserRemoveDTO dto) {
        userService.remove(dto);
    }
    @Auth
    @PostMapping("/getUserInfo")
    public UserLoginVO getUserInfo(HttpServletRequest request) {
        return userService.getUserInfo(request);
    }
    @Auth
    @PostMapping("/editPassword")
    public void editPassword( @RequestBody UserEditPasswordDto dto,HttpServletRequest request) {
        userService.editPassword(dto,request);
    }
    @Auth
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        userService.logout(request);
    }
    @PostMapping("/autoLogin")
    public UserLoginVO autoLogin(HttpServletRequest request) {
        return userService.autoLogin(request);
    }
}
