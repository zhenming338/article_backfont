package org.river.article.controller;

import jakarta.annotation.Resource;
import org.river.article.common.Page;
import org.river.article.common.Result;
import org.river.article.pojo.dto.AddArticleDto;
import org.river.article.pojo.dto.GetArticleCardDto;
import org.river.article.pojo.dto.GetArticleListPageDto;
import org.river.article.pojo.entity.Channel;
import org.river.article.pojo.vo.ArticleCardVo;
import org.river.article.pojo.vo.ArticleContentVo;
import org.river.article.pojo.vo.ArticlePageVO;
import org.river.article.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @DeleteMapping("/deleteMyArticle")
    public Result<Object> deleteMyArticleByArticleId(@RequestParam int articleId){
        articleService.deleteMyArticleByArticleId(articleId);
        return Result.success();
    }

    @PostMapping("/getArticleCardList")
    public Result<List<ArticleCardVo>> getArticleCardByArticleCardVo(@RequestBody GetArticleCardDto getArticleCardDto){
        System.out.println(getArticleCardDto);
        List<ArticleCardVo> articleCardVoList=articleService.getArticleCard(getArticleCardDto);
        return Result.success(articleCardVoList);
    }

    @GetMapping("/getArticleContent")
    public Result<ArticleContentVo> getMethodName(@RequestParam int articleId) {
        ArticleContentVo articleContentVo = articleService.getArticleContentByArticleId(articleId);
        return Result.success(articleContentVo);
    }
    
}
