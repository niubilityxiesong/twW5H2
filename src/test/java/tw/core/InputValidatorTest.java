package tw.core;

import org.junit.Before;
import org.junit.Test;
import tw.validator.InputValidator;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 在InputValidatorTest文件中完成InputValidator中对应的单元测试
 */
public class InputValidatorTest {

    private String legalarray;
    private String illegalarray;
    private String illegalarray2;

    @Before
    public void setup() throws Exception{

        legalarray = "1 2 3 4";
        illegalarray = "1 2 3";
        illegalarray2 = "1 2 3 44";
    }

    @Test
    public void test_InputValidator_should_return_true() throws Exception{

        InputValidator inputValidator = new InputValidator();
        assertThat(inputValidator.validate(legalarray)).isTrue();
    }

    @Test
    public void test_InputValidator_should_return_fail() throws Exception{

        InputValidator inputValidator = new InputValidator();
        assertThat(inputValidator.validate(illegalarray)).isFalse();
        assertThat(inputValidator.validate(illegalarray2)).isFalse();
    }
}
