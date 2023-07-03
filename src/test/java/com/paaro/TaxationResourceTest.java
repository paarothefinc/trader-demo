package com.paaro;

import com.paaro.traderdemo.dto.BetDto;
import com.paaro.traderdemo.dto.TaxationDto;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TaxationResourceTest {

    @Test
    public void testGetTaxationGeneralRate() {
        final BetDto betDto = new BetDto(1L, BigDecimal.valueOf(2000, 2), BigDecimal.valueOf(300, 2));

        final Response response = given().contentType(MediaType.APPLICATION_JSON).body(betDto).when().post("/taxation");
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

        final TaxationDto taxation = response.getBody().as(TaxationDto.class);

        Assertions.assertEquals(BigDecimal.valueOf(5400L, 2), taxation.getPossibleReturnAmount());
        Assertions.assertEquals(BigDecimal.valueOf(6000L, 2), taxation.getPossibleReturnAmountBefTax());
        Assertions.assertEquals(BigDecimal.valueOf(600L, 2), taxation.getPossibleReturnAmountAfterTax());
        Assertions.assertEquals(BigDecimal.valueOf(10L, 2), taxation.getTaxRate());
        Assertions.assertEquals(BigDecimal.valueOf(600L, 2), taxation.getTaxAmount());
    }

    @Test
    public void testGetTaxationGeneralAmount() {
        final BetDto betDto = new BetDto(2L, BigDecimal.valueOf(2000, 2), BigDecimal.valueOf(300, 2));

        final Response response = given().contentType(MediaType.APPLICATION_JSON).body(betDto).when().post("/taxation");
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

        final TaxationDto taxation = response.getBody().as(TaxationDto.class);

        Assertions.assertEquals(BigDecimal.valueOf(5800L, 2), taxation.getPossibleReturnAmount());
        Assertions.assertEquals(BigDecimal.valueOf(6000L, 2), taxation.getPossibleReturnAmountBefTax());
        Assertions.assertEquals(BigDecimal.valueOf(200L, 2), taxation.getPossibleReturnAmountAfterTax());
        Assertions.assertEquals(BigDecimal.valueOf(3333L, 5), taxation.getTaxRate());
        Assertions.assertEquals(BigDecimal.valueOf(200L, 2), taxation.getTaxAmount());
    }

    @Test
    public void testGetTaxationWinningsRate() {
        final BetDto betDto = new BetDto(3L, BigDecimal.valueOf(2000, 2), BigDecimal.valueOf(300, 2));

        final Response response = given().contentType(MediaType.APPLICATION_JSON).body(betDto).when().post("/taxation");
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

        final TaxationDto taxation = response.getBody().as(TaxationDto.class);

        Assertions.assertEquals(BigDecimal.valueOf(5600L, 2), taxation.getPossibleReturnAmount());
        Assertions.assertEquals(BigDecimal.valueOf(6000L, 2), taxation.getPossibleReturnAmountBefTax());
        Assertions.assertEquals(BigDecimal.valueOf(400L, 2), taxation.getPossibleReturnAmountAfterTax());
        Assertions.assertEquals(BigDecimal.valueOf(10L, 2), taxation.getTaxRate());
        Assertions.assertEquals(BigDecimal.valueOf(400L, 2), taxation.getTaxAmount());
    }

    @Test
    public void testGetTaxationWinningsAmount() {
        final BetDto betDto = new BetDto(4L, BigDecimal.valueOf(2000, 2), BigDecimal.valueOf(300, 2));

        final Response response = given().contentType(MediaType.APPLICATION_JSON).body(betDto).when().post("/taxation");
        response.then().assertThat().statusCode(HttpStatus.SC_OK);

        final TaxationDto taxation = response.getBody().as(TaxationDto.class);

        Assertions.assertEquals(BigDecimal.valueOf(5900L, 2), taxation.getPossibleReturnAmount());
        Assertions.assertEquals(BigDecimal.valueOf(6000L, 2), taxation.getPossibleReturnAmountBefTax());
        Assertions.assertEquals(BigDecimal.valueOf(100L, 2), taxation.getPossibleReturnAmountAfterTax());
        Assertions.assertEquals(BigDecimal.valueOf(1666L, 5), taxation.getTaxRate());
        Assertions.assertEquals(BigDecimal.valueOf(100L, 2), taxation.getTaxAmount());
    }
}
