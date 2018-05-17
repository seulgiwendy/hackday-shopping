package com.naver.wheejuni.domain;

import com.google.common.collect.Lists;
import com.naver.wheejuni.dto.security.UserJoinRequest;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = "articles")
@ToString(exclude = "articles")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private long id;

    @Column(name = "LOGIN_ID")
    private String userId;

    @Column(name = "ACCOUNT_NAME")
    private String name;

    private String password;

    @OneToMany(mappedBy = "account")
    private List<Article> articles = Lists.newArrayList();

    @ElementCollection(targetClass = UserGroups.class, fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<UserGroups> userGroups;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    private Account(String name, String userId, String password, Set<UserGroups> userGroups, UserRole role) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.userGroups = userGroups;
        this.role = role;
    }

    public boolean isCorrectPassword(String password, PasswordEncoder encoder) {
        return encoder.matches(password, this.password);
    }

    public static Account fromRequest(UserJoinRequest req, PasswordEncoder passwordEncoder) {
        return new Account(req.getName(), req.getId(), passwordEncoder.encode(req.getPassword()), UserGroups.findMatchingGroups(req.getGroups()), UserRole.USER);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public Set<UserGroups> getUserGroups() {
        return userGroups;
    }

    public UserRole getRole() {
        return role;
    }
}
