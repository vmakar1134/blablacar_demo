package com.makar.blablacar.service.impl;

import com.makar.blablacar.domain.Rate;
import com.makar.blablacar.domain.User;
import com.makar.blablacar.service.RateService;
import com.makar.blablacar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final UserService userService;

    private final String RATE_RESOURCE_URL = "http://localhost:8080/rest/rates";

    @Override
    @Transactional
    public Rate getUpdatedRate(Long userId) {
        Rate rate = retrieveRate(userId);
        User user = userService.get(userId);
        user.setRate(rate);
        return rate;
    }

    private Rate retrieveRate(Long userId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(RATE_RESOURCE_URL + userId, Rate.class);
    }
}
