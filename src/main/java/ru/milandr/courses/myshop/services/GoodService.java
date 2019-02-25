package ru.milandr.courses.myshop.services;

import org.springframework.stereotype.Service;
import ru.milandr.courses.myshop.daos.GoodDao;
import ru.milandr.courses.myshop.entities.Good;

@Service
public class GoodService {

    private GoodDao goodDao;

    public GoodService(GoodDao goodDao) {
        this.goodDao = goodDao;
    }

    public Good findGood(Long goodId) {
        Good good = goodDao.findOne(goodId);
        System.out.println(good.toString());
        return good;
    }
}
