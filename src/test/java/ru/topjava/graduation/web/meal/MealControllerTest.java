package ru.topjava.graduation.web.meal;

import org.junit.Test;
import ru.topjava.graduation.web.AbstractControllerTest;

import static ru.topjava.graduation.data.MealTestData.*;
import static ru.topjava.graduation.data.UserTestData.ADMIN;
import static ru.topjava.graduation.data.UserTestData.USER;

public class MealControllerTest extends AbstractControllerTest {

    public MealControllerTest() {
        super(REST_1_MEALS_URL);
    }

    @Test
    public void createNotAllowed() throws Exception {
        expectNotAllowed(perform(doPost().jsonBody(getNewMeal()).basicAuth(ADMIN)));
    }

    @Test
    public void deleteNotAllowed() throws Exception {
        expectNotAllowed(perform(doDelete().basicAuth(ADMIN)));
    }

    @Test
    public void getAllForUnauthorized() throws Exception {
        expectUnauthorized(perform(doGet(REST_3_MEALS_URL)));
    }

    @Test
    public void getAllForUser() throws Exception {
        getAllEntities(perform(doGet(REST_1_MEALS_URL).basicAuth(USER)), RESTAURANT_1_MEALS, MEAL_MATCHERS);
    }

    @Test
    public void getAllForAdmin() throws Exception {
        getAllEntities(perform(doGet(REST_2_MEALS_URL).basicAuth(ADMIN)), RESTAURANT_2_MEALS, MEAL_MATCHERS);
    }
}