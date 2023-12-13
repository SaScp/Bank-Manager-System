package ru.alex.bank_managersystem.validator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.alex.bank_managersystem.util.validator.CardValidator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;



public class CardValidatorTest {

    private CardValidator cardValidator;
    @BeforeEach
    void setUp() {
        cardValidator = new CardValidator();
    }


    public static Collection numbers() {
        return Arrays.asList(new Object[][]{
                        {"2202206158342886", true},
                        {"2202206158342068", true},
                        {"2202206158343397", true},
                        {"2202206158342456", true},
                        {"2202206158847462", true},
                        {"2202206158918446", true},
                        {"2202206158918289", true},
                        {"2202206158918131", true},
                        {"220220611740826", false},
                        {"4028829001530228", false},
                        {"3898639671059818", true}
                }
        );
    }

    @ParameterizedTest
    @MethodSource("numbers")
    public void isCardCorrectValidatorTest(String card,  Boolean isOk) {
        cardValidator.supports(CardValidator.class);
        Assert.assertEquals(isOk, cardValidator.isCardCorrectTest(card));
    }
    @ParameterizedTest
    @MethodSource("numbers")
    public void isLunaTestValidatorTest(String card,  Boolean isOk) {
        cardValidator.supports(CardValidator.class);
        Assert.assertEquals(isOk, cardValidator.isLunaTest(card));
    }
}
