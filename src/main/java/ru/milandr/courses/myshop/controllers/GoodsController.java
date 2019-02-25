package ru.milandr.courses.myshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milandr.courses.myshop.entities.User;
import ru.milandr.courses.myshop.services.GoodService;

@RestController
@RequestMapping("good")
public class GoodsController {

    private GoodService goodService;

    public GoodsController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("{goodId}")
    public User getUser(@PathVariable Long goodId) {
        goodService.findGood(goodId);
        return null;
    }
}
