package tw.controllers;

import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tw.GuessNumberModule;
import tw.commands.GuessInputCommand;
import tw.commands.InputCommand;
import tw.core.Answer;
import tw.core.Game;
import tw.core.exception.OutOfRangeAnswerException;
import tw.core.generator.AnswerGenerator;
import tw.core.generator.RandomIntGenerator;
import tw.views.GameView;

import static com.google.inject.Guice.createInjector;
import static junit.framework.TestCase.assertEquals;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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
        assertTrue(output.contains("Guess History:"));
        assertTrue(output.contains("Guess Result:"));
        assertTrue(output.contains("Game Status:"));
    }

    @Mock
    AnswerGenerator answerGenerator;
    @Mock
    Game game;
    @Mock
    GameView gameView;
    @Mock
    InputCommand inputCommand;

    @Before
    public void setup() throws Exception{

        MockitoAnnotations.initMocks(this);
        when(answerGenerator.generate()).thenReturn(Answer.createAnswer("1 2 3 4"));
        game = new Game(answerGenerator);
    }



    @Test
    public void showGameStatus_should_return_true() throws Exception{
        GameController gameController = new GameController(game, gameView);
        Answer answer = Answer.createAnswer("1 2 3 4");
        when(inputCommand.input()).thenReturn(answer);

        gameController.play(inputCommand);
        verify(gameView).showGameStatus("success");

    }

    @Test
    public void showGameStatus_should_return_false() throws Exception{

        GameController gameController = new GameController(game, gameView);
        Answer answer1 = Answer.createAnswer("1 2 3 5");
        Answer answer2 = Answer.createAnswer("1 4 3 5");
        Answer answer3 = Answer.createAnswer("1 6 3 5");
        Answer answer4 = Answer.createAnswer("1 7 3 5");
        Answer answer5 = Answer.createAnswer("1 8 3 5");
        Answer answer6 = Answer.createAnswer("1 2 3 7");

        when(inputCommand.input()).thenReturn(answer1).thenReturn(answer2).thenReturn(answer3).thenReturn(answer4)
        .thenReturn(answer5).thenReturn(answer6);

        gameController.play(inputCommand);
        verify(gameView).showGameStatus("fail");
        verify(gameView, times(6)).showGuessResult(any());

    }

}