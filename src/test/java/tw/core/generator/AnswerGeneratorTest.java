package tw.core.generator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tw.core.Answer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.sun.javaws.JnlpxArgs.verify;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * 在AnswerGeneratorTest文件中完成AnswerGenerator中对应的单元测试
 */
public class AnswerGeneratorTest {

    RandomIntGenerator randomIntGenerator;
    AnswerGenerator answerGenerator;

    @Mock
    RandomIntGenerator mockrandom;

    @Before
    public void setup() throws Exception{

        randomIntGenerator = new RandomIntGenerator();
        MockitoAnnotations.initMocks(this);
        answerGenerator = new AnswerGenerator(mockrandom);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_return_IllegalArgumentException() throws Exception{

        randomIntGenerator.generateNums(4,9);
    }

    @Test
    public void should_return_length_of_randomIntGenerator() throws Exception{

        assertThat(randomIntGenerator.generateNums(9,4).length()).isEqualTo(7);
    }

    @Test
    public void should_return_array_of_answer() throws Exception{

        when(mockrandom.generateNums(9,4)).thenReturn("1 2 3 4");

        Answer answer = answerGenerator.generate();

        assertThat(answer.toString()).isEqualTo("1 2 3 4");

    }
}

