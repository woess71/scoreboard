import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ScoreBoardTest {

    ScoreBoard myBoard;
    ArrayList<String[]> teams;


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
                e.printStackTrace();
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
            e.printStackTrace();
            Assertions.fail();
        }
        Assertions.assertEquals(myBoard.getNumberRunningGames(), 5);
    }

    @Test
    void testUpdateGame() {
        try {
            testAddGames();
        } catch( Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
        myBoard.updateScore("Liverpool", 0, 1);
        myBoard.updateScore("Derby", 0, 1);
        myBoard.updateScore("Barcelona", 0, 1);
        myBoard.updateScore("Roma", 1, 0);
        myBoard.updateScore("Dortmund", 1, 0);
        myBoard.updateScore("Celtic", 1, 0);

        teams.forEach( team -> {
            try {
                int score = myBoard.getGameScore(team[0]);
                Assertions.assertEquals(score, 1);
            } catch (Exception e) {
                e.printStackTrace();
                Assertions.fail();
            }
        });
    }

    @Test
    void testScoreBoardSorting() {
        // todo
    }

}
