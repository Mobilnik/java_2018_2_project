package ru.milandr.courses.miptshop.services;

import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.daos.GoodDao;
import ru.milandr.courses.miptshop.dtos.GoodDto;
import ru.milandr.courses.miptshop.entities.Good;

@Service
public class GoodService {

    private GoodDao goodDao;

    public GoodService(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public GoodDto getGood(Long goodId) {
        Good good = goodDao.findOne(goodId);
        return buildGoodDtoFromGood(good);
    }

    private GoodDto buildGoodDtoFromGood(Good good) {
        GoodDto goodDto = new GoodDto();
        goodDto.setId(good.getId());
        goodDto.setName(good.getName());
        goodDto.setPhoto(good.getPhoto());
        goodDto.setPrice(good.getPrice());

        return goodDto;
    }
}