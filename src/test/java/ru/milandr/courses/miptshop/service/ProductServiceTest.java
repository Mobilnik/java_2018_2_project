package ru.milandr.courses.miptshop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.milandr.courses.miptshop.common.utils.ValidationException;
import ru.milandr.courses.miptshop.daos.ProductDao;
import ru.milandr.courses.miptshop.dtos.ProductDto;
import ru.milandr.courses.miptshop.entities.Product;
import ru.milandr.courses.miptshop.services.ProductService;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Mock
    private ProductDao productDao;

    private ProductService productService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductService(productDao);
    }

    @Test(expected = ValidationException.class)
    public void nullGetTest() throws ValidationException {
        productService.get(null);
    }


    @Test(expected = ValidationException.class)
    public void wrongIdGetTest() throws ValidationException {
        given(productDao.findOne(1L)).willReturn(null);
        productService.get(1L);
    }

    @Test
    public void getTest() throws ValidationException {
        Product product = new Product(1L, "Milk", new byte[500], new BigDecimal(28.3));
        given(productDao.findOne(1L)).willReturn(product);

        ProductDto actualProductDto = productService.get(1L);
        ProductDto expectedOrderDto = new ProductDto(1L, "Milk", new byte[500], new BigDecimal(28.3));
        assertThat(actualProductDto).isEqualTo(expectedOrderDto);
    }

    @Test(expected = ValidationException.class)
    public void nullCreateTest() throws ValidationException {
        productService.create(null);
    }

    @Test(expected = ValidationException.class)
    public void existingIdCreateTest() throws ValidationException {
        productService.create(new ProductDto(1L, null, null, null));
    }

    @Test
    public void createTest() throws ValidationException {
        ProductDto productDto = new ProductDto(null, "Milk", new byte[500], new BigDecimal(28.3));

        Product actualProduct = productService.create(productDto);
        Product expectedProduct = new Product(null, "Milk", new byte[500], new BigDecimal(28.3));
        assertThat(actualProduct).isEqualTo(expectedProduct);
    }

    @Test(expected = ValidationException.class)
    public void nullUpdateTest() throws ValidationException {
        productService.update(null);
    }

    @Test(expected = ValidationException.class)
    public void nullIdUpdateTest() throws ValidationException {
        productService.update(new ProductDto(null, "Milk", new byte[500], new BigDecimal(28.3)));
    }

    @Test(expected = ValidationException.class)
    public void noSuchProductTest() throws ValidationException {
        given(productDao.findOne(1L)).willReturn(null);
        productService.update(new ProductDto(1L, "Milk", new byte[500], new BigDecimal(28.3)));
    }

    @Test
    public void updateTest() throws ValidationException {
        ProductDto productDto = new ProductDto(1L, "Milk", new byte[500], new BigDecimal(28.3));

        Product product = new Product(1L, "DaoMilk", new byte[500], new BigDecimal(25.0));
        given(productDao.findOne(1L)).willReturn(product);


        Product actualUpdatedProduct = productService.update(productDto);
        Product expectedUpdatedProduct = new Product(1L, "Milk", new byte[500], new BigDecimal(28.3));
        assertThat(actualUpdatedProduct).isEqualTo(expectedUpdatedProduct);
    }

    @Test(expected = ValidationException.class)
    public void nullDeleteTest() throws ValidationException {
        productService.delete(null);
    }
}
