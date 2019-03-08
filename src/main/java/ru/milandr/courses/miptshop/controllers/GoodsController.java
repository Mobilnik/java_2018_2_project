package ru.milandr.courses.miptshop.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.milandr.courses.miptshop.dtos.GoodDto;
import ru.milandr.courses.miptshop.services.GoodService;

@RestController
@RequestMapping("good")
public class GoodsController {

    private GoodService goodService;

    public GoodsController(GoodService goodService) {
        this.goodService = goodService;
    }

    @GetMapping("/{goodId}")
    public GoodDto getGood(@PathVariable Long goodId) {
        return goodService.findGood(goodId);
    }
}
