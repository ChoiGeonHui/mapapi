package com.mapapi.food.service;

import com.mapapi.food.repository.FoodShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodShopService {

    private final FoodShopRepository foodShopRepository;

}
