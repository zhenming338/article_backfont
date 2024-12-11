package org.river.article.controller;

import jakarta.annotation.Resource;
import org.river.article.common.Page;
import org.river.article.common.Result;
import org.river.article.pojo.dto.AddArticleDto;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Article;
import org.river.article.pojo.entity.Channel;
import org.river.article.pojo.vo.ArticlePageVO;
import org.river.article.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Resource
    ArticleService articleService;


    @GetMapping("/getChannelList")
    public Result<List<Channel>> getChannelList(){
        List<Channel> channelList=articleService.getChannelList();
        return Result.success(channelList);
    }

    @PostMapping("/addArticle")
    public Result<Object> addArticle(@RequestBody  AddArticleDto addArticleDto){
        System.out.println(addArticleDto);

        articleService.addArticle(addArticleDto);
        return Result.success();
    }

    @PostMapping("/getArticlePage")
    public Result<Page<ArticlePageVO>> getArticleList(@RequestBody GetArticleListPageDto getArticleListPageDto){
        System.out.println(getArticleListPageDto);
        Page<ArticlePageVO> articlePage=articleService.getArticlePage(getArticleListPageDto);
        return Result.success(articlePage);
    }

}
