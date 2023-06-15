package tn.wissalyermani.msarticle.services;

import dto.ArticleDto;
import dto.StockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import tn.wissalyermani.msarticle.client.StockClient;
import tn.wissalyermani.msarticle.entities.Article;
import tn.wissalyermani.msarticle.mappers.ArticleMapper;
import tn.wissalyermani.msarticle.repositories.ArticleRepository;

import javax.validation.constraints.AssertTrue;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements IArticleService {
    private final ArticleRepository articleRepository;
    private final StockClient stockClient;

    @Override
    public Article addArticle(Article article) {
        article.setCreatedAt(LocalDate.now());
        return articleRepository.save(article);
    }

    @Override
    public Article updateArticle(Map<Object, Object> fields, Long id) {
        Optional<Article> article = articleRepository.findById(id);
        Assert.isTrue(article.isPresent(), "There's no article for this ID!");
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Article.class, key.toString());
            field.setAccessible(true);
            ReflectionUtils.setField(field, article.get(), value);
        });
        return articleRepository.save(article.get());
    }

    @Override
    public void removeArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }

    @Override
    public ArticleDto findArticleById(Long articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        Assert.isTrue(article.isPresent(), "There's no article for this ID!");

        StockDto stockDto = stockClient.getStockById(article.get().getIdStock());

        return ArticleMapper.mapToDto(article.get(), stockDto);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

}
