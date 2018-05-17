package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.domain.Account;
import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.dto.article.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleService {

    Article saveNewArticle(NewArticleDto dto);

    Article saveNewArticle(NewArticleDto dto, Account account);

    Optional<Article> updateArticle(ArticleUpdateRequest request);

    List<Article> getByTargetGroups(Set<UserGroups> targetGroups);

    SingleArticle getByArticleId(long id);

    SingleArticle getByArticleIdEditable(long id, Account account);

    PagedArticles getPagedArticle(ArticleListRequest request, Set<UserGroups> userGroups);
}
