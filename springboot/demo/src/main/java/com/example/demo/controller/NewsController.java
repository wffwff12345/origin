package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commontool.ResponseResult;
import com.example.demo.dto.NewsDto;
import com.example.demo.dto.SearchNewsDto;
import com.example.demo.service.NewsService;

@RequestMapping("/news")
@RestController
public class NewsController {
    @Autowired
    NewsService service;
    @PostMapping("/list")
    public ResponseResult<?> list(@RequestBody SearchNewsDto dto){
        ResponseResult<?> result=service.list(dto);
        return result;
    }
    @PostMapping("/news")
    public ResponseResult<?> news(@RequestBody SearchNewsDto dto){
        ResponseResult<?> result=service.news(dto);
        return result;
    }
    @PostMapping("/insert")
    public ResponseResult<?> insert(@RequestBody NewsDto dto){
        ResponseResult<?> result=service.insertnew(dto);
        return result;
    }
    @GetMapping("/get/{id}")
    public ResponseResult<?> getOne(@PathVariable("id") Integer id){
        return service.getByid(id);
    }
    @GetMapping("/getnew/{id}")
    public ResponseResult<?> getNew(@PathVariable("id") Integer id){
        return service.get(id);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseResult<?> delete(@PathVariable("id") Integer id){
        return service.delete(id);
    }
}
