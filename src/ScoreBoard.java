import java.util.HashMap;

public class ScoreBoard {

    HashMap<String, Integer> theGames;
    private HashMap<Integer, BoardItem> theBoard;
    private int gameCount;

    public ScoreBoard() {
        theBoard = new HashMap<Integer, BoardItem>();
        theGames = new HashMap<String, Integer>();

    }

    public synchronized void startMatch(String homeTeam, String awayTeam) {

        gameCount++;
        BoardItem boardItem = new BoardItem(homeTeam, awayTeam, gameCount);
        theBoard.put(gameCount, boardItem);
        theGames.put(homeTeam, gameCount);

    }


    public synchronized void finishMatch(String homeTeam) throws Exception {
        if (theGames.get(homeTeam) == null) {
            throw new Exception("Error trying to finish game. Home Team " + homeTeam + " not found");
        }
        theBoard.remove(theGames.get(homeTeam));
    }

    public int getNumberRunningGames() {
        return theBoard.size();
    }

}
