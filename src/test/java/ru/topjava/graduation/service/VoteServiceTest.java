package ru.topjava.graduation.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.util.exception.VoteDenyException;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.topjava.graduation.data.RestaurantTestData.*;
import static ru.topjava.graduation.data.UserTestData.USER;
import static ru.topjava.graduation.data.UserTestData.USER_2;
import static ru.topjava.graduation.data.VoteTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class VoteServiceTest {

    @Autowired
    private VoteService voteService;

    @Test
    public void getVotes() {
        Assert.assertEquals(voteService.getVotesCount(REST_1_ID), REST_1_VOTES);
        Assert.assertEquals(voteService.getVotesCount(REST_2_ID), REST_2_VOTES);
        Assert.assertEquals(voteService.getVotesCount(REST_3_ID), REST_3_VOTES);
    }

    @Test
    public void setVote() {
        Vote newVote = new Vote(USER, RESTAURANT_1);
        Vote created = voteService.create(USER, REST_1_ID, LocalTime.of(10, 0));
        newVote.setId(created.getId());
        VOTE_MATCHERS.assertMatch(newVote, created);
        Assert.assertEquals((int) voteService.getVotesCount(REST_1_ID), REST_1_VOTES + 1);
    }

    @Test
    public void updateVote() {
        voteService.create(USER, REST_1_ID, LocalTime.of(10, 0));
        Assert.assertEquals((int) voteService.getVotesCount(REST_1_ID), REST_1_VOTES + 1);
        voteService.create(USER, REST_2_ID, LocalTime.of(10, 30));
        Assert.assertEquals(voteService.getVotesCount(REST_1_ID), REST_1_VOTES);
        Assert.assertEquals((int) voteService.getVotesCount(REST_2_ID), REST_2_VOTES + 1);
    }

    @Test
    public void denyVoting() {
        voteService.create(USER_2, REST_2_ID, LocalTime.of(10, 0));
        assertThrows(VoteDenyException.class, () -> voteService.create(USER_2, REST_3_ID, LocalTime.of(12, 0)));
    }
}