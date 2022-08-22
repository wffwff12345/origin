package com.example.demo.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.alibaba.cloud.context.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Enum.TEnum;
import com.example.demo.commontool.ResponseResult;
import com.example.demo.dto.ApLogindto;
import com.example.demo.dto.ApRegisterDto;
import com.example.demo.entity.AppUser;
import com.example.demo.mapper.AppUserMapper;
import com.example.demo.service.AppUserService;
@Service
public class AppUserImpl extends ServiceImpl<AppUserMapper,AppUser> implements AppUserService{

    @Override
    public ResponseResult<?> register(ApRegisterDto dto) {
        if(!StringUtils.isEmpty(dto.getName())&&!StringUtils.isEmpty(dto.getPassword())&&!StringUtils.isEmpty(dto.getPhone())){
            LambdaQueryWrapper<AppUser> query=new LambdaQueryWrapper<>();
            query.eq(AppUser::getName, dto.getName());
            AppUser result=this.getOne(query);
            if(result==null){
                AppUser user=new AppUser();
                user.setName(dto.getName());
                String password=DigestUtils.md5DigestAsHex(dto.getPassword().getBytes());
                user.setPassword(password);
                user.setPhone(dto.getPhone());
                user.setStatus(0);
                user.setDate(new Date());
                this.save(user);
                return ResponseResult.okResult(user, TEnum.RRGISTRESUCESS);
            }
            return ResponseResult.ErrorResult(TEnum.USERISEXIST);
        }
        return ResponseResult.ErrorResult(TEnum.ERROR);
    }

    @Override
    public ResponseResult<?> login(ApLogindto dto) {
        if(!StringUtils.isEmpty(dto.getName())&&!StringUtils.isEmpty(dto.getPassword())){
            LambdaQueryWrapper<AppUser> query=new LambdaQueryWrapper<>();
            query.eq(AppUser::getName, dto.getName());
            AppUser user=this.getOne(query);
            if(user==null){
                return ResponseResult.ErrorResult(TEnum.USERISNOTEXIST);
            }
            if(user.getStatus()==0){
                return  ResponseResult.ErrorResult(TEnum.USERISNOPOWER);
            }
            String password=dto.getPassword();
            password=DigestUtils.md5DigestAsHex(password.getBytes());
            if(password.equals(user.getPassword())){
                Map<String,Object> result= new HashMap<>(); 
                result.put("user", user);
                result.put("token",user.getId());
                return ResponseResult.okResult(result, TEnum.LOGINSUCCEED);
            }else{
                return ResponseResult.ErrorResult(TEnum.LOGINGERROR);
            }
        }
        return ResponseResult.ErrorResult(TEnum.LOGINISEMPTY);
    }
    
}
