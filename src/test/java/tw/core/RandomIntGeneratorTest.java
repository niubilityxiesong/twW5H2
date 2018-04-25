package tw.core;


import org.junit.Test;
import tw.core.generator.RandomIntGenerator;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * 在RandomIntGeneratorTest文件中完成RandomIntGenerator中对应的单元测试
 */
public class RandomIntGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void text_generateNums_should_return_IllegalArgumentException() throws Exception{

        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        randomIntGenerator.generateNums(4,9);
    }

    @Test
    public void text_generateNums_should_return_randomarray() throws Exception{

        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();

        assertThat(randomIntGenerator.generateNums(9,4).length()).isEqualTo(7);
    }
}