package ru.topjava.graduation.web.vote;

import org.junit.Assert;
import org.junit.Test;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.web.AbstractControllerTest;

import static ru.topjava.graduation.data.UserTestData.*;
import static ru.topjava.graduation.data.VoteTestData.*;

public class VoteControllerTest extends AbstractControllerTest {

    public VoteControllerTest() {
        super(REST_3_VOTES_URL);
    }

    @Test
    public void getAllForUnauthorized() throws Exception {
        expectUnauthorized(perform(doGet()));
    }

    @Test
    public void createUserVote() throws Exception {
        perform(doPost().basicAuth(USER_2));
        getVotes(USER_2, REST_3_VOTES + 1, REST_3_VOTES_URL);
    }

    @Test
    public void getAllForUser() throws Exception {
        getVotes(USER, REST_1_VOTES, REST_1_VOTES_URL);
    }

    @Test
    public void getAllForAdmin() throws Exception {
        getVotes(ADMIN, REST_2_VOTES, REST_2_VOTES_URL);
    }

    private void getVotes(User user, Integer votes, String url) throws Exception {
        Integer expectedVotes = Integer.parseInt(perform(doGet(url).basicAuth(user))
                .andReturn()
                .getResponse()
                .getContentAsString());
        Assert.assertEquals(expectedVotes, votes);
    }
}