package ru.milandr.courses.miptshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.dtos.GoodDto;
import ru.milandr.courses.miptshop.services.GoodService;

@RequiredArgsConstructor
@RestController
@RequestMapping("good")
public class GoodsController {

    private final GoodService goodService;

    @GetMapping(value = "/{goodId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GoodDto> getGood(@PathVariable Long goodId) throws ValidationException {
        return ResponseEntity.ok(goodService.get(goodId));
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGood(@RequestBody GoodDto goodDto) throws ValidationException {
        goodService.create(goodDto);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateGood(@RequestBody GoodDto goodDto) throws ValidationException {
        goodService.update(goodDto);
    }

    @DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGood(@RequestBody GoodDto goodDto) throws ValidationException {
        goodService.delete(goodDto.getId());
    }
}
