package ru.milandr.courses.miptshop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.daos.GoodDao;
import ru.milandr.courses.miptshop.dtos.GoodDto;
import ru.milandr.courses.miptshop.entities.Good;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNotNull;
import static ru.milandr.courses.miptshop.common.utils.ValidationUtils.validateIsNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodService {

    private final GoodDao goodDao;

    public GoodDto get(Long goodId) throws ValidationException {
        validateIsNotNull(goodId, "No Good id provided");

        Good good = goodDao.findOne(goodId);
        validateIsNotNull(good, "No Good with id " + goodId);
        return buildGoodDtoFromGood(good);
    }

    public List<GoodDto> getAll() {
        return goodDao.findAllBy().stream()
                 .map(this::buildGoodDtoFromGood)
                 .collect(Collectors.toList());
    }

    private GoodDto buildGoodDtoFromGood(Good good) {
        GoodDto goodDto = new GoodDto();
        goodDto.setId(good.getId());
        goodDto.setName(good.getName());
        goodDto.setPhoto(good.getPhoto());
        goodDto.setPrice(good.getPrice());

        return goodDto;
    }

    public Good create(GoodDto goodDto) throws ValidationException {
        validateIsNotNull(goodDto, "No Good DTO provided");
        validateIsNull(goodDto.getId(), "Can not create a good with existing id");

        Good good = buildGoodFromGoodDto(goodDto);
        goodDao.save(good);
        return good;
    }

    private Good buildGoodFromGoodDto(GoodDto goodDto) {
        return new Good(null,
                goodDto.getName(),
                goodDto.getPhoto(),
                goodDto.getPrice());
    }

    public Good update(GoodDto goodDto) throws ValidationException {
        validateIsNotNull(goodDto, "No Good DTO provided");
        validateIsNotNull(goodDto.getId(), "Can not update a good without id");

        Good good = goodDao.findOne(goodDto.getId());
        validateIsNotNull(good, "No Good with id " + goodDto.getId());

        good.setName(goodDto.getName());
        good.setPhoto(goodDto.getPhoto());
        good.setPrice(goodDto.getPrice());

        goodDao.save(good);
        return good;
    }

    public void delete(Long goodId) throws ValidationException {
        validateIsNotNull(goodId, "No Good ID provided");
        goodDao.delete(goodId);
    }
}