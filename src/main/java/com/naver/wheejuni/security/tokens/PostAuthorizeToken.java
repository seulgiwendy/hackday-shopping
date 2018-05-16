package com.naver.wheejuni.security.tokens;

import com.naver.wheejuni.domain.Account;
import com.naver.wheejuni.security.AccountContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class PostAuthorizeToken extends UsernamePasswordAuthenticationToken {

    private PostAuthorizeToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public PostAuthorizeToken(Account account, List<SimpleGrantedAuthority> authorities) {
        this(account, account.getPassword(), authorities);
    }

    public PostAuthorizeToken(AccountContext context) {
        this(context, context.getPassword(), context.getAuthorities());
    }

    public Account getAccount() {
        return (Account)super.getPrincipal();
    }

    public AccountContext getContext() {
        return (AccountContext)super.getPrincipal();
    }
}
