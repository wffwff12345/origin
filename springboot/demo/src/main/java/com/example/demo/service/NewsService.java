package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.commontool.ResponseResult;
import com.example.demo.dto.NewsDto;
import com.example.demo.dto.SearchNewsDto;
import com.example.demo.entity.News;

public interface NewsService extends IService<News> {
    ResponseResult<?> list(SearchNewsDto dto);
    ResponseResult<?> insertnew(NewsDto dto);
    ResponseResult<?> getByid(Integer id);
    ResponseResult<?> delete(Integer id);
    ResponseResult<?> news(SearchNewsDto dto);
    ResponseResult<?> get(Integer id);

}
