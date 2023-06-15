package tn.wissalyermani.msarticle.controllers;

import dto.ArticleDto;
import dto.StockDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.wissalyermani.msarticle.entities.Article;
import tn.wissalyermani.msarticle.services.IArticleService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("article")
public class ArticleController {
    private final IArticleService articleService;

    @PatchMapping("{id}")
    Article updateArticle(
            @RequestBody Map<Object, Object> fields,
            @PathVariable Long id
    ) {
        return articleService.updateArticle(fields, id);
    }

    @PostMapping("/add")
    public Article addArticle(@RequestBody Article c) {
        return articleService.addArticle(c);
    }

    @GetMapping("/find/{id}")
    public ArticleDto findArticleById(@PathVariable Long id) {
        return articleService.findArticleById(id);
    }

}
