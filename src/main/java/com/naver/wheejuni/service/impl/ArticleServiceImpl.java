package com.naver.wheejuni.service.impl;

import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.domain.repositories.jpa.ArticleRepository;
import com.naver.wheejuni.dto.ArticleUpdateRequest;
import com.naver.wheejuni.dto.NewArticleDto;
import com.naver.wheejuni.dto.UpdateRequestTypes;
import com.naver.wheejuni.exceptions.domain.NoArticleException;
import com.naver.wheejuni.service.specification.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Override
    public void saveNewArticle(NewArticleDto dto) {
        repository.save(Article.fromDto(dto));
    }

    @Override
    @Transactional
    public Optional<Article> updateArticle(ArticleUpdateRequest request) {
        if(request.getUpdateRequestTypes() == UpdateRequestTypes.DELETE) {
            repository.deleteById(request.getId());
            return Optional.empty();
        }

        return Optional.of(repository.findById(request.getId()).orElseThrow(() -> new NoArticleException("ID에 맞는 게시물이 없습니다.")).updateArticle(request));
    }

    @Override
    public List<Article> getByTargetGroups(Set<UserGroups> targetGroups) {
        return repository.findByUserGroupsIn(targetGroups);
    }

    @Override
    public Page<Article> getByTargetGroupsPaged(Set<UserGroups> targetGroups, Pageable pageable) {
        return null;
    }
}
