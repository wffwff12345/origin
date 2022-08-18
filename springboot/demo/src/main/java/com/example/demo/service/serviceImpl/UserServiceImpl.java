package com.example.demo.service.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Enum.TEnum;
import com.example.demo.commontool.ResponseResult;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
  
    @Override
    public ResponseResult<?> getByname(UserDto dto) {
        LambdaQueryWrapper<User> query= new LambdaQueryWrapper<>();
        query.eq(User::getName, dto.getName());
        User user=this.getOne(query);
        if(user==null){
            return ResponseResult.ErrorResult(TEnum.USERISNOTEXIST);
        }
        return ResponseResult.okResult(user);
    }

    @Override
    public ResponseResult<?> login(LoginDto dto) {
        LambdaQueryWrapper<User> query=new LambdaQueryWrapper<>();
        query.eq(User::getName, dto.getName());
        User user=this.getOne(query);
        if(user==null){
            return ResponseResult.ErrorResult(TEnum.USERISNOTEXIST);
        }
        if(user.getStatus()==0){
            return ResponseResult.ErrorResult(TEnum.USERISNOPOWER);
        }
        String pswmd5=dto.getPassword();
        pswmd5=DigestUtils.md5DigestAsHex(pswmd5.getBytes());
        if(!pswmd5.equals(user.getPassword()))
        {
           return ResponseResult.ErrorResult(TEnum.LOGINGERROR);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("token", user.getId());
        return ResponseResult.okResult(result, TEnum.LOGINSUCCEED);
    }
    
}
