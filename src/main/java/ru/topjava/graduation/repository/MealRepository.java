package ru.topjava.graduation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Integer> {

    List<Meal> findAllByRestaurantIdAndDate(Integer restaurantId, LocalDate date);

    @Override
    @Transactional
    Meal save(Meal item);

    @Override
    @Transactional
    void deleteById(Integer id);
}