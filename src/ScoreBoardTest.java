import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ScoreBoardTest {

    ScoreBoard myBoard;
    ArrayList<String[]> teams;

    final String SCOREBOARD_DISPLAY = "Celtic 3 - Rangers 0" + "\n" +
            "Roma 2 - Milano 1" + "\n" +
            "Barcelona 1 - Napoli 2" + "\n" +
            "Liverpool 2 - Man Utd. 1" + "\n" +
            "Dortmund 1 - Paris 0" + "\n" +
            "Derby 0 - Coventry 1" + "\n" +
            "Chelsea 0 - Villa 0" + "\n";

    @BeforeEach
    void setUp() {

        myBoard = new ScoreBoard();
        teams = new ArrayList<>();
        teams.add(new String[]{"Liverpool", "Man Utd."});
        teams.add(new String[]{"Derby", "Coventry"});
        teams.add(new String[]{"Barcelona", "Napoli"});
        teams.add(new String[]{"Roma", "Milano"});
        teams.add(new String[]{"Dortmund", "Paris"});
        teams.add(new String[]{"Celtic", "Rangers"});
        teams.add(new String[]{"Chelsea", "Villa"});

    }

    @AfterEach
    void tearDown() {
        myBoard = null;
        teams = null;
    }


    @Test
    void testAddGames() {
        teams.forEach(teamPair -> {
            try {
                myBoard.startMatch(teamPair[0], teamPair[1]);
            } catch (Exception e) {
                Assertions.fail();
            }
        });
        Assertions.assertEquals(myBoard.getNumberRunningGames(), 7);
    }


    @Test
    void testRemoveGame() {
        try {
            testAddGames();
            myBoard.finishMatch(teams.getFirst()[0]);
            myBoard.finishMatch(teams.getLast()[0]);
        } catch (Exception e) {
            Assertions.fail();
        }
        Assertions.assertEquals(myBoard.getNumberRunningGames(), 5);
    }

    @Test
    void testUpdateGame() {
        try {
            testAddGames();
            myBoard.updateScore("Liverpool", 0, 1);
            myBoard.updateScore("Derby", 0, 1);
            myBoard.updateScore("Barcelona", 0, 1);
            myBoard.updateScore("Roma", 1, 0);
            myBoard.updateScore("Dortmund", 1, 0);
            myBoard.updateScore("Celtic", 1, 0);
            myBoard.updateScore("Chelsea", 0, 1);

        } catch( Exception e) {
            Assertions.fail();
        }

        teams.forEach( team -> {
            try {
                int score = myBoard.getGameScore(team[0]);
                Assertions.assertEquals(score, 1);
            } catch (Exception e) {
                Assertions.fail();
            }
        });
    }

    @Test
    void testScoreBoardSorting() {
        try {
            testAddGames();

            myBoard.updateScore("Liverpool", 0, 1);
            myBoard.updateScore("Derby", 0, 1);
            myBoard.updateScore("Barcelona", 0, 1);
            myBoard.updateScore("Roma", 1, 0);
            myBoard.updateScore("Dortmund", 1, 0);
            myBoard.updateScore("Celtic", 1, 0);
            myBoard.updateScore("Liverpool", 1, 1);
            myBoard.updateScore("Liverpool", 2, 1);
            myBoard.updateScore("Barcelona", 1, 1);
            myBoard.updateScore("Barcelona", 1, 2);
            myBoard.updateScore("Celtic", 2, 0);
            myBoard.updateScore("Celtic", 3, 0);
            myBoard.updateScore("Roma", 1, 1);
            myBoard.updateScore("Roma", 2, 1);
            String scoreBoardDisplay =  myBoard.getBoardForDisplay();
            Assertions.assertEquals(scoreBoardDisplay, SCOREBOARD_DISPLAY);
        } catch( Exception e) {
            Assertions.fail();
        }
    }

}
