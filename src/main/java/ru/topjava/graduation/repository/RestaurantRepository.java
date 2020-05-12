package ru.topjava.graduation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.topjava.graduation.model.Restaurant;

import java.util.List;

@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    List<Restaurant> findAll();

    @Override
    @Transactional
    Restaurant save(Restaurant item);

    @Override
    @Transactional
    void deleteById(Integer id);
}