package ru.topjava.graduation.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.model.Meal;
import ru.topjava.graduation.service.MealService;
import ru.topjava.graduation.web.Controller;

import javax.validation.Valid;
import java.net.URI;

import static ru.topjava.graduation.web.Controller.ADMIN_MEALS_URL;
import static ru.topjava.graduation.web.Controller.JSON_TYPE;

@RestController
@RequestMapping(value = ADMIN_MEALS_URL, produces = JSON_TYPE)
public class AdminMealController implements Controller {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService mealService;

    @GetMapping("{id}")
    public Meal get(@PathVariable Integer id) {
        log.info("get meal with id {}", id);
        return mealService.get(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        log.info("delete meal with id {}", id);
        mealService.delete(id);
    }

    @PostMapping(consumes = JSON_TYPE)
    public ResponseEntity<Meal> create(@Valid @RequestBody Meal meal,
                                       @PathVariable Integer restaurantId) {
        Meal created = mealService.create(meal, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(MEALS_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}