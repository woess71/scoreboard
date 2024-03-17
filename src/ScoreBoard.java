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

    public int getGameScore( String homeTeam) throws Exception {
        if( theGames.get(homeTeam) == null) {
            throw new Exception("Error trying to get Game Score. Home Team " + homeTeam + " not found");
        }
        BoardItem item = theBoard.get( theGames.get(homeTeam) );
        return item.getScoreTotal();
    }

    public synchronized void updateScore( String homeTeam, int homeScore, int awayScore) throws Exception {
        if( ! theGames.containsKey(homeTeam) ) {
            throw new Exception("Error updating score. Home Team " + homeTeam + " not found");
        }
        Integer counter = theGames.get(homeTeam);
        BoardItem item = theBoard.get(counter);
        item.updateScore(homeScore, awayScore);
    }

}
