package com.gok.food_map.file.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gok.food_map.file.dto.FileUploadDTO;
import com.gok.food_map.file.entity.MFile;
import com.gok.food_map.file.mapper.MFileMapper;
import com.gok.food_map.file.vo.FileUploadVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
* @author hhww7
* @description 针对表【m_file(文件)】的数据库操作Service实现
* @createDate 2024-10-08 20:08:43
*/
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class FileService extends ServiceImpl<MFileMapper, MFile> {

    private static final String DIR = "D:/file/";

    private final FileService self;

    private final MFileMapper mFileMapper;

    //获取
    public MFile get(Long id) {
        return mFileMapper.selectById(id);
    }

    //上传
    @Transactional
    public FileUploadVO upload(MultipartFile multipartFile) {
        //插入数据库
        MFile mFile = new MFile(null, multipartFile.getOriginalFilename(), false, null);
        Long id = DefaultIdentifierGenerator.getInstance().nextId(mFile);
        String dynamicPath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
        //创建文件夹
        File dir = new File(DIR + dynamicPath);
        dir.mkdirs();
        mFile.setId(id);
        mFile.setPath(dynamicPath + id);
        mFileMapper.insert(mFile);
        //保存到本地
        try (InputStream inputStream = multipartFile.getInputStream();
             OutputStream outputStream = new FileOutputStream(new File(dir, id.toString()))) {
            StreamUtils.copy(inputStream, outputStream);
        } catch (IOException e) { throw new RuntimeException(e); }
        return new FileUploadVO(id.toString());
    }

    //下载
    public void download(Long id, HttpServletResponse response) {
        MFile file = mFileMapper.selectById(id);
        if (file == null) {
//            throw new RuntimeException("文件不存在");
            System.out.println("文件不存在");
        }
//        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
//        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), StandardCharsets.UTF_8));
//        try (InputStream inputStream = new FileInputStream(DIR + file.getPath())) {
//            OutputStream outputStream = response.getOutputStream();
//            StreamUtils.copy(inputStream, outputStream);
//        } catch (IOException e) { throw new RuntimeException(e); }
    }

    //删除
    @Transactional
    public void remove(Long id) {
        MFile file = mFileMapper.selectById(id);
        mFileMapper.deleteById(id);
        if (file != null) { new File(DIR + file.getPath()).delete(); }
    }

    //有效
    public void enable(Long id) {
        MFile file = mFileMapper.selectById(id);
        if (file != null) {
            file.setEnable(true);
            mFileMapper.updateById(file);
        }
    }

    //定时删除无效文件数据
    @Scheduled(cron = "0 0 1 * * ?") //每天凌晨1点执行
    public void removeInValid() {
        LambdaQueryWrapper<MFile> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MFile::getEnable, false);
        List<MFile> mFiles = mFileMapper.selectList(wrapper);
        //要使用self调用remove，防止事务失效
        mFiles.forEach(f -> self.remove(f.getId()));
    }
}




