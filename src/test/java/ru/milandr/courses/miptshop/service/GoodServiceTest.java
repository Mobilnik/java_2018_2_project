package ru.milandr.courses.miptshop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.daos.GoodDao;
import ru.milandr.courses.miptshop.dtos.GoodDto;
import ru.milandr.courses.miptshop.entities.Good;
import ru.milandr.courses.miptshop.services.GoodService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodServiceTest {
    @Mock
    private GoodDao goodDao;

    private GoodService goodService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        goodService = new GoodService(goodDao);
    }

    @Test(expected = ValidationException.class)
    public void nullGetTest() throws ValidationException {
        goodService.get(null);
    }


    @Test(expected = ValidationException.class)
    public void wrongIdGetTest() throws ValidationException {
        given(goodDao.findOne(1L)).willReturn(null);
        goodService.get(1L);
    }

    @Test
    public void getTest() throws ValidationException {
        Good good = new Good(1L, "Milk", new byte[500], new BigDecimal(28.3));
        given(goodDao.findOne(1L)).willReturn(good);

        GoodDto actualGoodDto = goodService.get(1L);
        GoodDto expectedOrderDto = new GoodDto(1L, "Milk", new byte[500], new BigDecimal(28.3));
        assertThat(actualGoodDto).isEqualTo(expectedOrderDto);
    }

    @Test(expected = ValidationException.class)
    public void nullCreateTest() throws ValidationException {
        goodService.create(null);
    }

    @Test(expected = ValidationException.class)
    public void existingIdCreateTest() throws ValidationException {
        goodService.create(new GoodDto(1L, null, null, null));
    }

    @Test
    public void createTest() throws ValidationException {
        GoodDto goodDto = new GoodDto(null, "Milk", new byte[500], new BigDecimal(28.3));

        Good actualGood = goodService.create(goodDto);
        Good expectedGood = new Good(null, "Milk", new byte[500], new BigDecimal(28.3));
        assertThat(actualGood).isEqualTo(expectedGood);
    }

    @Test(expected = ValidationException.class)
    public void nullUpdateTest() throws ValidationException {
        goodService.update(null);
    }

    @Test(expected = ValidationException.class)
    public void nullIdUpdateTest() throws ValidationException {
        goodService.update(new GoodDto(null, "Milk", new byte[500], new BigDecimal(28.3)));
    }

    @Test(expected = ValidationException.class)
    public void noSuchGoodUpdateTest() throws ValidationException {
        given(goodDao.findOne(1L)).willReturn(null);
        goodService.update(new GoodDto(1L, "Milk", new byte[500], new BigDecimal(28.3)));
    }

    @Test
    public void updateTest() throws ValidationException {
        GoodDto goodDto = new GoodDto(1L, "Milk", new byte[500], new BigDecimal(28.3));

        Good good = new Good(1L, "DaoMilk", new byte[500], new BigDecimal(25.0));
        given(goodDao.findOne(1L)).willReturn(good);


        Good actualUpdatedGood = goodService.update(goodDto);
        Good expectedUpdatedGood = new Good(1L, "Milk", new byte[500], new BigDecimal(28.3));
        assertThat(actualUpdatedGood).isEqualTo(expectedUpdatedGood);
    }

    @Test(expected = ValidationException.class)
    public void nullDeleteTest() throws ValidationException {
        goodService.delete(null);
    }
}
