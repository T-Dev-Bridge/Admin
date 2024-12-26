package com.bridge.admin.service;

import com.bridge.admin.repository.rdb.Article;
import lombok.extern.slf4j.Slf4j;
import org.bridge.base.repository.rdb.CrdRepository;
import org.bridge.base.service.CrudService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Qualifier("articleService")
public class ArticleService extends CrudService<Article, ArticleDto, Long> {

    @Override
    public Class<ArticleDto> getDtoClazz() {
        return ArticleDto.class;
    }

    public ArticleService(CrdRepository<Article, Long> repository) {
        super(repository);
    }


    @Override
    @Qualifier("articleRepository")
    public void setRepository(CrdRepository repository) {
        this.repository = repository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        this.searchFields = new String[]{"title", "content"};

        this.numberFields = new String[]{"id", "activeYn"};

        super.init();
    }
}
