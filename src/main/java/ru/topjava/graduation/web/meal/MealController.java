package ru.topjava.graduation.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.graduation.model.Meal;
import ru.topjava.graduation.service.MealService;
import ru.topjava.graduation.web.Controller;

import java.util.List;

import static ru.topjava.graduation.web.Controller.JSON_TYPE;
import static ru.topjava.graduation.web.Controller.MEALS_URL;

@RestController
@RequestMapping(value = MEALS_URL, produces = JSON_TYPE)
public class MealController implements Controller {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService mealService;

    @GetMapping
    public List<Meal> getAll(@PathVariable Integer restaurantId) {
        log.info("get meals for a restaurant {}", restaurantId);
        return mealService.getAll(restaurantId);
    }
}