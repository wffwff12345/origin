package com.example.demo.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.cloud.context.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.Enum.TEnum;
import com.example.demo.commontool.PageResponseResult;
import com.example.demo.commontool.People;
import com.example.demo.commontool.ResponseResult;
import com.example.demo.commontool.WmThreadLocal;
import com.example.demo.dto.NewsDto;
import com.example.demo.dto.SearchNewsDto;
import com.example.demo.entity.News;
import com.example.demo.mapper.NewsMapper;
import com.example.demo.service.NewsService;
import com.example.demo.service.UserService;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
    @Autowired
    UserService service;

    private String getImageFromList(List<String> images) {
        String result = null;
        if (images.size() > 0) {
            result = String.join(",", images);
        }
        return result;
    }

    @Override
    public ResponseResult<?> list(SearchNewsDto dto) {
        People people = WmThreadLocal.getUser();
        if (people == null) {
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        IPage<News> page = new Page<>(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<News> query = new LambdaQueryWrapper<>();
        query.eq(News::getUserId, people.getId());
        if (!StringUtils.isEmpty(dto.getTitle())) {
            query.like(News::getTitle, dto.getTitle());
        }
        // if(dto.getChannel().equals("首页")){
        // dto.setChannel("");
        // }
        if (!StringUtils.isEmpty(dto.getChannel())) {

            query.eq(News::getChannel, dto.getChannel());
        }
        IPage<News> result = this.page(page, query);
        long total = result.getTotal();
        List<News> data = result.getRecords();
        return new PageResponseResult<>(dto.getSize(), dto.getPage(), total, data);
    }

    @Override
    public ResponseResult<?> insertnew(NewsDto dto) {
        People people = WmThreadLocal.getUser();
        if (people == null) {
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        News news = new News();
        if (dto.getId() != null) {
            news.setId(dto.getId());
        }
        news.setTitle(dto.getTitle());
        news.setChannel(dto.getChannel());
        news.setContent(dto.getContent().toString());
        news.setCreateTime(dto.getCreateTime());
        news.setAuthor("tom");
        String picture = getImageFromList(dto.getCover());
        news.setCover(picture);
        news.setType(dto.getType());
        news.setUserId(people.getId());
        this.saveOrUpdate(news);
        return ResponseResult.okResult(news);
    }

    @Override
    public ResponseResult<?> getByid(Integer id) {
        People people = WmThreadLocal.getUser();
        if (people == null) {
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        LambdaQueryWrapper<News> query = new LambdaQueryWrapper<>();
        query.eq(News::getUserId, people.getId());
        query.eq(News::getId, id);
        News news = this.getOne(query);
        if (news == null) {
            return ResponseResult.ErrorResult(TEnum.USERISNOTEXIST);
        }
        return ResponseResult.okResult(news);
    }

    @Override
    public ResponseResult<?> delete(Integer id) {
        People people = WmThreadLocal.getUser();
        if (people == null) {
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        LambdaQueryWrapper<News> query = new LambdaQueryWrapper<>();
        query.eq(News::getUserId, people.getId());
        query.eq(News::getId, id);
        News news = this.getOne(query);
        if (news == null) {
            return ResponseResult.ErrorResult(TEnum.ERROR);
        }
        LambdaQueryWrapper<News> query2 = new LambdaQueryWrapper<>();
        query2.eq(News::getUserId, people.getId());
        query2.eq(News::getId, id);
        this.remove(query2);
        return ResponseResult.okResult(news);
    }

    @Override
    public ResponseResult<?> news(SearchNewsDto dto) {
        People people = WmThreadLocal.getUser();
        if (people == null) {
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        IPage<News> page = new Page<>(dto.getPage(), dto.getSize());
        LambdaQueryWrapper<News> query = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(dto.getTitle())) {
            query.like(News::getTitle, dto.getTitle());
        }
        // if(dto.getChannel().equals("首页")){
        // dto.setChannel("");
        // }
        if (!StringUtils.isEmpty(dto.getChannel())) {

            query.eq(News::getChannel, dto.getChannel());
        }
        IPage<News> result = this.page(page, query);
        long total = result.getTotal();
        List<News> data = result.getRecords();
        return new PageResponseResult<>(dto.getSize(), dto.getPage(), total, data);
    }

    @Override
    public ResponseResult<?> get(Integer id) {
        People people = WmThreadLocal.getUser();
        if (people == null) {
            return ResponseResult.ErrorResult(TEnum.NEEDLOGIN);
        }
        LambdaQueryWrapper<News> query = new LambdaQueryWrapper<>();
        query.eq(News::getId, id);
        News news = this.getOne(query);
        if (news == null) {
            return ResponseResult.ErrorResult(TEnum.USERISNOTEXIST);
        }
        return ResponseResult.okResult(news);
    }

}
