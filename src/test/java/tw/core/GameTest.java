package tw.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.core.model.GuessResult;

import static com.google.inject.matcher.Matchers.any;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * 在GameTest文件中完成Game中对应的单元测试
 */


public class GameTest {

    Answer inputanswer;


    @Mock
    AnswerGenerator mockanswer;

    @Before
    public void setup() throws Exception{

        inputanswer = Answer.createAnswer("1 7 8 2");
        MockitoAnnotations.initMocks(this);
        when(mockanswer.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));

    }

    @Test
    public void test_GuessResult_should_return_1A1B() throws Exception{

        Game game = new Game(mockanswer);
        GuessResult result = game.guess(inputanswer);

        assertThat(result.getResult()).isEqualTo("1A1B");
    }

    @Test
    public void text_CheckStatus_should_return_continue() throws Exception{

        Game game = new Game(mockanswer);
        GuessResult result = game.guess(inputanswer);

        game.guess(inputanswer);
        assertThat(game.checkStatus()).isEqualTo("continue");

    }

    @Test
    public void text_CheckStatus_should_return_fail() throws Exception{

        Game game = new Game(mockanswer);
        GuessResult result = game.guess(inputanswer);

        game.guess(inputanswer);
        game.guess(inputanswer);
        game.guess(inputanswer);
        game.guess(inputanswer);
        game.guess(inputanswer);
        game.guess(inputanswer);
        game.guess(inputanswer);
        assertThat(game.checkStatus()).isEqualTo("fail");

    }

    @Test
    public void test_checkCorrectGuessResult_should_return_true() throws Exception{

        Game game = new Game(mockanswer);
        game.guess(Answer.createAnswer("1 2 3 4"));

        assertThat(game.checkStatus()).isEqualTo("success");
    }
}
