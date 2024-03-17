import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Objects;

public class ScoreBoard {

    HashMap<String, Integer> theGames;
    private HashMap<Integer, BoardItem> theBoard;
    private int gameCount;

    public ScoreBoard() {
        theBoard = new HashMap<>();
        theGames = new HashMap<>();

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
        theGames.remove(homeTeam);
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

    public String getBoardForDisplay() {
        StringBuilder builder = new StringBuilder();
        ArrayList<BoardItem> items =  new ArrayList<> (theBoard.values() );

        items.sort(new BoardItemComparator());
        items.forEach( boardItem ->
                builder.append(boardItem.getDisplaySummary()).append("\n")
        );
        return builder.toString();
    }


    static class BoardItemComparator implements Comparator<BoardItem> {

        // Compares boardItems corresponding to following rule :
        // Highest total score first
        // If total score is equal then order the most recent added game first.

        @Override
        public int compare(BoardItem o1, BoardItem o2) {
            int currentValue =  Integer.compare(o1.getScoreTotal(), o2.getScoreTotal()) * -1;

            if(currentValue == 0) {
                if( o1.getGameCounter() > o2.getGameCounter() ) {
                    return -1;
                } else if(Objects.equals(o1.getGameCounter(), o2.getGameCounter())) {
                    return 0;
                }
                return 1;
            }
            return currentValue;
        }
    }

}
