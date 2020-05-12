package ru.topjava.graduation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.topjava.graduation.model.Restaurant;
import ru.topjava.graduation.repository.RestaurantRepository;
import ru.topjava.graduation.util.exception.NotFoundException;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant get(Integer restaurantId) {
        return getOrThrowException(restaurantId);
    }

    public void delete(Integer restaurantId) {
        getOrThrowException(restaurantId);
        restaurantRepository.deleteById(restaurantId);
    }

    private Restaurant getOrThrowException(Integer restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(NotFoundException::new);
    }
}