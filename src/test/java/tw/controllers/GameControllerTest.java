package tw.controllers;

import org.junit.Before;
import org.junit.Test;
import tw.commands.GuessInputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.views.GameView;

import static junit.framework.TestCase.assertEquals;
import static org.fest.assertions.api.Assertions.assertThat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;


/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    private String systemOut() {

        return outContent.toString();
    }

    @Test
    public void testbegingame() throws Exception {

        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
        Game game = new Game(answerGenerator);
        GameView gameView = new GameView();

        GameController gameController = new GameController(game, gameView);
        gameController.beginGame();

        assertThat(systemOut()).isEqualTo("------Guess Number Game, You have 6 chances to guess!  ------\n");
    }



}