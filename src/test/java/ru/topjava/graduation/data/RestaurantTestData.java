package ru.topjava.graduation.data;

import ru.topjava.graduation.TestMatchers;
import ru.topjava.graduation.model.Restaurant;

import java.util.List;

public class RestaurantTestData {
    private static final Integer START_SEQ = 100000;

    public static final Integer REST_1_ID = START_SEQ + 3;
    public static final Integer REST_2_ID = START_SEQ + 4;
    public static final Integer REST_3_ID = START_SEQ + 5;

    public static final Restaurant RESTAURANT_1 = new Restaurant(REST_1_ID, "Burger King", "ул.Садовая 1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(REST_2_ID, "McDonalds", "ул.Центральная 5");
    public static final Restaurant RESTAURANT_3 = new Restaurant(REST_3_ID, "KFC", "ул.Пушкина 10");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_3);

    public static TestMatchers<Restaurant> RESTAURANTS_MATCHERS = TestMatchers.useFieldsComparator(Restaurant.class, "meals");

    public static Restaurant getNewRestaurant() {
        return new Restaurant("Пиццерия", "пр.Академиков 15");
    }

    public static Restaurant getUpdatedRestaurant() {
        Restaurant updated = new Restaurant();
        updated.setId(REST_1_ID);
        updated.setName("NEW NAME");
        updated.setAddress("NEW ADDRESS");
        return updated;
    }
}