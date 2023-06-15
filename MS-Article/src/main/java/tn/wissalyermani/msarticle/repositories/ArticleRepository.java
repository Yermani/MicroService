package tn.wissalyermani.msarticle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import tn.wissalyermani.msarticle.entities.Article;

import javax.persistence.Id;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
