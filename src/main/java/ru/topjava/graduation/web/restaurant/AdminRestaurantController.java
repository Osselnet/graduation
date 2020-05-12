package ru.topjava.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.service.RestaurantService;

import javax.validation.Valid;
import java.net.URI;

import static ru.topjava.graduation.web.Controller.*;

@RestController
@RequestMapping(value = ADMIN_RESTAURANTS_URL, produces = JSON_TYPE)
public class AdminRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/{restaurantId}")
    public Restaurant get(@PathVariable Integer restaurantId) {
        log.info("get restaurant with id {}", restaurantId);
        return restaurantService.get(restaurantId);
    }

    @PostMapping(consumes = JSON_TYPE)
    public ResponseEntity<Restaurant> create(@Valid @RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RESTAURANTS_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{restaurantId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer restaurantId) {
        log.info("delete restaurant with id {}", restaurantId);
        restaurantService.delete(restaurantId);
    }
}