package ru.topjava.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.service.RestaurantService;
import ru.topjava.graduation.web.Controller;

import java.util.List;

import static ru.topjava.graduation.web.Controller.JSON_TYPE;
import static ru.topjava.graduation.web.Controller.RESTAURANTS_URL;

@RestController
@RequestMapping(value = RESTAURANTS_URL, produces = JSON_TYPE)
public class RestaurantController implements Controller {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> getAll() {
        log.info("get all the restaurants");
        return restaurantService.getAll();
    }
}