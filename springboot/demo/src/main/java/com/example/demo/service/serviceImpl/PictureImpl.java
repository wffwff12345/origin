package com.example.demo.service.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Enum.TEnum;
import com.example.demo.commontool.OssService;
import com.example.demo.commontool.PageResponseResult;
import com.example.demo.commontool.People;
import com.example.demo.commontool.ResponseResult;
import com.example.demo.commontool.WmThreadLocal;
import com.example.demo.dto.PictureDto;
import com.example.demo.entity.Picture;
import com.example.demo.mapper.PictureMapper;
import com.example.demo.service.PictureService;
@Service
public class PictureImpl extends ServiceImpl<PictureMapper,Picture> implements PictureService {
    @Autowired
    private OssService ossService;
    @Override
    public ResponseResult<?> upload(MultipartFile file)  {
        People people = WmThreadLocal.getUser();
        if(people==null){
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        System.out.println(file);
        try {
            String url = ossService.upLoad(file);
            Picture picture=new Picture();
            picture.setUrl(url);
            picture.setCreateTime(new Date());
            picture.setStatus(1);
            picture.setType(1);
            picture.setUserId(people.getId());
            this.save(picture);
            return ResponseResult.okResult(picture);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseResult.ErrorResult(TEnum.ERROR);
        }
       
    }
    @Override
    public ResponseResult<?> list(PictureDto dto) {
        People people = WmThreadLocal.getUser();
        if(people==null){
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        LambdaQueryWrapper<Picture> query=new LambdaQueryWrapper<>();
        query.eq(Picture::getUserId, people.getId());
        query.eq(Picture::getStatus,dto.getStatus());
        query.eq(Picture::getStatus,dto.getStatus());
        query.orderByAsc(Picture::getCreateTime);
        IPage<Picture> page=new Page<>(dto.getPage(), dto.getSize());
        IPage<Picture> result=this.page(page, query);
        long total= result.getTotal();
        List<Picture> data=result.getRecords();
        return new PageResponseResult<> (dto.getSize(), dto.getPage(), total, data);
    }
    @Override
    public ResponseResult<?> collect(Integer id, Integer status) {
        People people = WmThreadLocal.getUser();
        if(people==null){
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        LambdaUpdateWrapper<Picture> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(Picture::getUserId, people.getId());
        wrapper.eq(Picture::getId, id);
        wrapper.set(Picture::getStatus, status);
        boolean result=this.update(wrapper);
        Picture picture=this.getById(id);
        if(result){
            return ResponseResult.okResult(picture);
        }
        return ResponseResult.ErrorResult(TEnum.ERROR);
    }
    @Override
    public ResponseResult<?> delete(Integer id) {
        People people = WmThreadLocal.getUser();
        if(people==null){
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        LambdaQueryWrapper<Picture> query=new LambdaQueryWrapper<>();
        query.eq(Picture::getUserId, people.getId());
        query.eq(Picture::getId, id);
        Picture picture = this.getOne(query);
        if(picture==null){
            return ResponseResult.ErrorResult(TEnum.ERROR);
        }
        LambdaQueryWrapper<Picture> query2=new LambdaQueryWrapper<>();
        query2.eq(Picture::getUserId, people.getId());
        query2.eq(Picture::getId, id);
        this.remove(query2);
        return ResponseResult.okResult(picture);
    }
}
