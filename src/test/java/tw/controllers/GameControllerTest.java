package tw.controllers;

import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import tw.GuessNumberModule;
import tw.commands.GuessInputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.views.GameView;

import static com.google.inject.Guice.createInjector;
import static junit.framework.TestCase.assertEquals;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.*;


/**
 * 在GameControllerTest文件中完成GameController中对应的单元测试
 */
public class GameControllerTest {

    private ByteArrayOutputStream outContent;

    private void Inputnum(String input){
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private String Output(){
        return outContent.toString();
    }

    @Test
    public void testbegingame() throws Exception {

        RandomIntGenerator randomIntGenerator = new RandomIntGenerator();
        AnswerGenerator answerGenerator = new AnswerGenerator(randomIntGenerator);
        Game game = new Game(answerGenerator);
        GameView gameView = new GameView();

        GameController gameController = new GameController(game, gameView);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //gameController.beginGame();
        //assertTrue( Output().endsWith("------Guess Number Game, You have 6 chances to guess!  ------\n" ));


        String input = "1 2 3 4\n" + "1 2 3 5\n" + "1 2 6 5\n" + "7 2 3 5\n" + "1 8 3 5\n" + "1 2 9 5\n" + "0 2 3 5\n";
        Inputnum(input);
        gameController.play(new GuessInputCommand());
        String output = Output();

        assertTrue(output.startsWith("------Please input your answer as x x x x , x <10 ------\n"));
        assertTrue(output.contains("Gue1ss History:"));
        assertTrue(output.contains("Gu2ess Result:"));
        assertTrue(output.contains("Ga3me Status:"));
    }


}