package ru.topjava.graduation.data;

import ru.topjava.graduation.TestMatchers;
import ru.topjava.graduation.model.Vote;

import static ru.topjava.graduation.data.RestaurantTestData.*;

public class VoteTestData {

    public static final Integer REST_1_VOTES = 3;
    public static final Integer REST_2_VOTES = 3;
    public static final Integer REST_3_VOTES = 3;

    public static final String REST_1_VOTES_URL = "/restaurants/" + REST_1_ID + "/votes";
    public static final String REST_2_VOTES_URL = "/restaurants/" + REST_2_ID + "/votes";
    public static final String REST_3_VOTES_URL = "/restaurants/" + REST_3_ID + "/votes";

    public static TestMatchers<Vote> VOTE_MATCHERS = TestMatchers.useFieldsComparator(Vote.class);
}