package com.naver.wheejuni.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@Slf4j
public class UserGroupsTest {

    private UserGroups testGroup = UserGroups.C_GROUP;
    private Set<String> queries = Sets.newHashSet();

    @Before
    public void setQueries() {
        Flux.just("A_GROUP", "C_GROUP").subscribe(s -> this.queries.add(s));
    }

    @Test
    public void groups_namesDisplay() {
        Arrays.stream(UserGroups.values()).forEach(g -> log.debug(g.name()));
        assertTrue(UserGroups.A_GROUP.name().equalsIgnoreCase("A_GROUP"));
    }

    @Test
    public void groups_serializeTest() throws JsonProcessingException{
        assertThat(new ObjectMapper().writeValueAsString(this.testGroup), is("{\"symbol\":\"C그룹\",\"description\":\"공지사항 수정 안해도 처음부터 잘 써지는 C그룹\",\"type\":\"C_GROUP\"}"));
    }

    @Test
    public void groups_findByString() {
        assertThat(UserGroups.findMatchingGroups(this.queries).size(), is(2));
    }

}