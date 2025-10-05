package com.gok.food_map.user.service;

import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gok.food_map.district.mapper.MAddressMapper;
import com.gok.food_map.exception.ServiceException;
import com.gok.food_map.file.service.FileService;
import com.gok.food_map.user.dto.*;
import com.gok.food_map.user.entity.MUser;
import com.gok.food_map.user.entity.MemberLevel;
import com.gok.food_map.user.mapper.MUserMapper;
import com.gok.food_map.user.vo.LevelGetListVO;
import com.gok.food_map.user.vo.UserGetListVO;
import com.gok.food_map.user.vo.UserLoginVO;
import com.gok.food_map.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
* @author hhww7
* @description 针对表【m_user(用户)】的数据库操作Service实现
* @createDate 2024-10-08 20:00:48
*/
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class UserService  {

    private static final String PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\W]{6,18}$";//密码

    private final FileService fileService;


    private final MUserMapper mUserMapper;
    private final MAddressMapper mAddressMapper;

    private final MemberLevelService memberLevelService;

    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute("token");
    }
    public UserLoginVO userLogin(UserLoginDto dto, HttpServletRequest request) {
        LambdaQueryWrapper<MUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(dto.getCode() != null, MUser::getCode, dto.getCode());
        MUser mUser = mUserMapper.selectOne(wrapper);
        if (mUser == null) {
            request.getRequestDispatcher("/error/accountError");
            return null;
        }
        if(!mUser.getPassword().equals(dto.getPassword())) {
            request.getRequestDispatcher("/error/passwordError");
            return null;
        }
        JSONObject createJsonp = JSONUtil.createObj().put("id", mUser.getId());
        assert createJsonp != null;
        String token = TokenUtil.createToken(createJsonp);
        request.setAttribute("Authorization", token);
        request.getSession().setAttribute("token", token);
        UserLoginVO vo = new UserLoginVO();
        BeanUtils.copyProperties(mUser, vo);
        vo.setId(mUser.getId().toString());
        vo.setAvatar((mUser.getAvatar() != null) ? mUser.getAvatar().toString() : "");
        vo.setCity((mUser.getCity() != null) ? mUser.getCity().toString() : "");
        vo.setCreateTime(mUser.getCreateTime().toString());
        System.out.println(request.getSession().getAttribute("token").toString());
        return vo;
    }
    public void UserRegister(UserRegisterDto dto){
        MUser saveUser = new MUser();
        if (!dto.getCode().isEmpty() && !dto.getPassword().isEmpty()) {
            saveUser.setCode(dto.getCode());
            saveUser.setPassword(dto.getPassword());
            saveUser.setCreateTime(LocalDateTime.now());
            saveUser.setLevelId(1);
            saveUser.setValid(true);
            mUserMapper.insert(saveUser);
            System.out.println("save success");
        }else {
            System.out.println("save fail");
        }

    }

    //新增
    @Transactional
    public void add(UserSaveDTO dto) {
        checkSave(dto, true);
        MUser mUser = new MUser();
        if (!dto.getCreateTime().isEmpty()){
            LocalDateTime time = null;
            try {
                time = new SimpleDateFormat("yy-MM-dd").parse(dto.getCreateTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                mUser.setCreateTime(time);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        BeanUtils.copyProperties(dto, mUser);
        mUserMapper.insert(mUser); //保存用户数据
        fileService.enable(mUser.getAvatar());//生效用户头像
    }
    //初始化
    public List<LevelGetListVO> init() {
        List<MemberLevel> levels = memberLevelService.findAll();
        List<LevelGetListVO> res = new ArrayList<>();
        BeanUtils.copyProperties(res, levels);
        for (MemberLevel level : levels) {
            LevelGetListVO vo = new LevelGetListVO();
            BeanUtils.copyProperties(level, vo);
            res.add(vo);
        }
        return res;
    }

    //编辑
    @Transactional
    public void edit(UserSaveDTO dto) {
        checkSave(dto, false);
        MUser mUser = mUserMapper.selectById(dto.getId());
        if (dto.getAvatar() == null) { //删除原有照片
            fileService.remove(mUser.getAvatar());//删除旧头像
        } else {
            if (!dto.getAvatar().equals(mUser.getAvatar())) { //替换照片
                fileService.remove(mUser.getAvatar());//删除旧头像
                fileService.enable(dto.getAvatar());//生效用户头像
            }
        }
        if (!dto.getCreateTime().isEmpty()){
            LocalDateTime time = null;
            try {
                time = new SimpleDateFormat("yy-MM-dd").parse(dto.getCreateTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                mUser.setCreateTime(time);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        BeanUtils.copyProperties(dto, mUser);
        mUser.setUpdateTime(LocalDateTime.now());
        mUserMapper.updateById(mUser); //更新用户数据
    }

    //会员修改
    @Transactional
    public void LevelUpdate(UserLevelChangeDTO dto){
        MUser mUser = mUserMapper.selectById(dto.getId());
        mUser.setUpdateTime(LocalDateTime.now());
        String level = dto.getNewLevel();
        Boolean valid = Boolean.valueOf(dto.getNewValid());
        BeanUtils.copyProperties(dto, mUser);
        mUser.setLevelId(Integer.valueOf(level));
        mUser.setUpdateTime(LocalDateTime.now());
        mUser.setValid(valid);
        mUserMapper.updateById(mUser); //更新用户数据
    }

    //获取列表
    public IPage<UserGetListVO> getList(UserGetListDTO dto) {
        IPage<MUser> page = new Page<>(dto.getCurrent() == null ? 1 : Integer.parseInt(dto.getCurrent()), dto.getSize() == null ? 20 : Integer.parseInt(dto.getSize()));
        LambdaQueryWrapper<MUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(dto.getCode()), MUser::getCode, dto.getCode());
        wrapper.like(StringUtils.hasText(dto.getName()), MUser::getName, dto.getName());
        LocalDateTime time = null;
        if (!dto.getCreateTime().isEmpty()) {
            try {
                time = new SimpleDateFormat("yy-MM-dd").parse(dto.getCreateTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                wrapper.ge(dto.getCreateTime() != null, MUser::getCreateTime, time);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        page = mUserMapper.selectPage(page, wrapper);
        IPage<UserGetListVO> res = new Page<>();
        BeanUtils.copyProperties(page, res);
        List<UserGetListVO> record = page.getRecords().stream().map(mUser -> {
            UserGetListVO vo = new UserGetListVO();
            BeanUtils.copyProperties(mUser, vo);
            vo.setAvatar(mUser.getAvatar() == null ? null : mUser.getAvatar().toString());
            vo.setCity(mUser.getCity() == null ? null : mUser.getCity().toString());
            //将LocalDateTime格式类型转为LocalDate再变String
            vo.setId(mUser.getId().toString());
            vo.setCreateTime(mUser.getCreateTime() == null ? null : LocalDate.of(mUser.getCreateTime().getYear(),mUser.getCreateTime().getMonth(),mUser.getCreateTime().getDayOfMonth()).toString());
            return vo;
        }).toList();
        res.setRecords(record);
        return res;
    }

    //删除
    @Transactional
    public void remove(UserRemoveDTO dto) {
        MUser mUser = mUserMapper.selectById(dto.getId());
        if (mUser != null) {
            mUserMapper.deleteById(dto.getId());
            fileService.remove(mUser.getAvatar());
        }
    }
    public void export(UserGetListDTO dto, HttpServletResponse response) {
        //todo
        File file= new File("./users.txt");
        String fileName= file.getName();
        LambdaQueryWrapper<MUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(dto.getCode()), MUser::getCode, dto.getCode());
        wrapper.like(StringUtils.hasText(dto.getName()), MUser::getName, dto.getName());
        LocalDateTime time = null;
        if (!dto.getCreateTime().isEmpty()) {
            try {
                time = new SimpleDateFormat("yy-MM-dd").parse(dto.getCreateTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                wrapper.ge(dto.getCreateTime() != null, MUser::getCreateTime, time);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        wrapper.orderByDesc(MUser::getCreateTime);
        List<MUser> users =  mUserMapper.selectList(wrapper);
        if (users==null){
            throw new RuntimeException("没有用户");
        }
        try(BufferedWriter os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
//            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
//            os.writeObject(users);
            for (MUser user : users) {
                os.write(user.toString());
                os.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //设置content-type: application/octet-stream（告知浏览器这是二进制文件）
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        //设置content-disposition（attachment用附加方式下载，filename指定文件名【中文名正确解析需要进行编码】）
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        //复制文件
        try(InputStream inputStream= new FileInputStream(file)) {
            OutputStream outputStream= response.getOutputStream();
            StreamUtils.copy(inputStream, outputStream);
        } catch(Exception e) {
            throw new RuntimeException("文件下载失败");
        }


    }


    //校验
    private void checkSave(UserSaveDTO dto, boolean isAdd) {

        //id校验
        Long id = dto.getId();
        MUser mUser = null;
        if (isAdd) {
            dto.setId(null);//新增时id设为null，交给雪花算法生成
        } else {
            //编辑时用户必须存在
            mUser = mUserMapper.selectById(id);
            if (mUser == null) {
                throw new RuntimeException("用户不存在");
            }
        }
        //code校验
        String code = dto.getCode();
        if (isAdd) {
            //新增时与已有用户code不能重复
            LambdaQueryWrapper<MUser> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(MUser::getCode, code);
            MUser byCode = mUserMapper.selectOne(wrapper);
            if (byCode != null) {
                throw new RuntimeException("code已经存在");
            }
        } else {
            dto.setCode(mUser.getCode());//编辑时，code不能修改，直接设置为原始值
        }
        //姓名校验
        String name = dto.getName();
        if (!StringUtils.hasText(name) || name.length() > 20) {
            throw new RuntimeException("姓名参数错误");//姓名为空或者长度大于20
        }
        //密码校验
        String password = dto.getPassword();
        if (isAdd) {
            if (!StringUtils.hasText(password) || !Pattern.matches(PASSWORD, password)) {
                //密码为空或者密码格式不正确（请输入包含英文和数字，在6-18位之间）
                throw new RuntimeException("密码格式不正确");
            }
        } else {
            dto.setPassword(mUser.getPassword());//编辑时不许可修改密码，只能重置密码
        }
        //头像校验
        Long avatar = dto.getAvatar();
        if (avatar != null && fileService.get(avatar) == null) {
            throw new RuntimeException("头像不存在");//有设置头像文件，但是文件不存在
        }
        //性别校验
        String sex = dto.getGender();
        if (!StringUtils.hasText(sex) || !List.of("男", "女").contains(sex)) {
            throw new RuntimeException("性别输入有误");
        }
        //会员等级校验
        Integer levelId = dto.getLevelId();
        if (levelId == null) {
            throw new RuntimeException("会员等级异常");
        }

        //创建时间

        //有效校验
        if (isAdd) {
            dto.setValid(true);
        }
    }

    public UserLoginVO getUserInfo(HttpServletRequest request) {
        String token = request.getSession().getAttribute("token").toString();
        Map<String, String> map = TokenUtil.getToken(token);
        MUser user = mUserMapper.selectById(Long.valueOf(map.get("id")));
        if (user == null) {
            ServiceException.build("无效会话或数据不存在");
        }
        UserLoginVO vo = new UserLoginVO();
        BeanUtils.copyProperties(user, vo);
        vo.setId(user.getId().toString());
        vo.setAvatar(user.getAvatar() == null ? null : user.getAvatar().toString());
        vo.setCity(user.getCity() == null ? null : user.getCity().toString());
        vo.setCreateTime(user.getCreateTime() == null ? null : user.getCreateTime().toString());
        return vo;
    }
}




