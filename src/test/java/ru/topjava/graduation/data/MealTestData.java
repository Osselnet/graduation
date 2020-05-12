package ru.topjava.graduation.data;

import ru.topjava.graduation.TestMatchers;
import ru.topjava.graduation.model.Meal;

import java.util.List;

import static ru.topjava.graduation.data.RestaurantTestData.*;

public class MealTestData {
    private static final Integer START_SEQ = 100000;

    public static final Integer MEAL_1_ID = START_SEQ + 6;
    public static final Integer MEAL_2_ID = START_SEQ + 7;
    public static final Integer MEAL_3_ID = START_SEQ + 8;
    public static final Integer MEAL_4_ID = START_SEQ + 9;
    public static final Integer MEAL_5_ID = START_SEQ + 10;

    public static final Meal MEAL_1 = new Meal(MEAL_1_ID, "ChickenBurger set", 300);
    public static final Meal MEAL_2 = new Meal(MEAL_2_ID, "CheeseBurger set", 400);
    public static final Meal MEAL_3 = new Meal(MEAL_3_ID, "FishBurger set", 500);

    public static final Meal MEAL_4 = new Meal(MEAL_4_ID, "BigMac set", 300);
    public static final Meal MEAL_5 = new Meal(MEAL_5_ID, "Happy Meal set", 400);

    public static final List<Meal> RESTAURANT_1_MEALS = List.of(MEAL_1, MEAL_2, MEAL_3);
    public static final List<Meal> RESTAURANT_2_MEALS = List.of(MEAL_4, MEAL_5);

    public static final String REST_1_MEALS_URL = "/restaurants/" + REST_1_ID + "/meals";
    public static final String REST_2_MEALS_URL = "/restaurants/" + REST_2_ID + "/meals";
    public static final String REST_3_MEALS_URL = "/restaurants/" + REST_3_ID + "/meals";

    public static final String ADMIN_REST_1_MEALS_URL = "/admin" + REST_1_MEALS_URL;

    public static TestMatchers<Meal> MEAL_MATCHERS = TestMatchers.useFieldsComparator(Meal.class, "restaurant");

    public static Meal getNewMeal() {
        return new Meal("Big Mexican Burger", 199);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal();
        updated.setId(MEAL_1_ID);
        updated.setName("Today's new burger");
        updated.setPrice(200);
        updated.setRestaurant(RESTAURANT_1);
        return updated;
    }
}