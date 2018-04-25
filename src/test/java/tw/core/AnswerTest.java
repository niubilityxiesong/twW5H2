package tw.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.model.Record;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * 在AnswerTest文件中完成Answer中对应的单元测试
 */
public class AnswerTest {

    private String usearray = "1 2 3 4";

    @Mock
    Answer mockanswer;

    @Before
    public void steup(){

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_creatAnswer() throws Exception{

        Answer answer = new Answer();

        String result = answer.createAnswer(usearray).toString();

        assertThat(result).isEqualTo("1 2 3 4");
    }

    @Test(expected = OutOfRangeAnswerException.class)
    public void test_validata() throws Exception{

        Answer answer = Answer.createAnswer("1 2 3 44");

        answer.validate();
    }

    @Test
    public void test_check_should_return_11() throws Exception{

        Answer actualanswer = Answer.createAnswer("1 2 3 4");
        Answer inputanswer = Answer.createAnswer("1 6 7 2");
        Record value = actualanswer.check(inputanswer);
        int[] rightvalue = {1,1};
        assertThat(value.getValue()).isEqualTo(rightvalue);
    }
}