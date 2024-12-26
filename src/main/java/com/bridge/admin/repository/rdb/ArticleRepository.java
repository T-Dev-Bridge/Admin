package com.bridge.admin.repository.rdb;

import org.bridge.base.repository.rdb.CrudRepository;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("articleRepository")
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
