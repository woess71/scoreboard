public class BoardItem {

    private String homeTeam;
    private String awayTeam;
    private int homeScore = 0;
    private int awayScore = 0;
    private StringBuffer homeTotal;
    private StringBuffer awayTotal;

    private Integer gameCounter;


    public BoardItem(String homeTeam, String awayTeam, Integer gameCounter) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTotal = new StringBuffer(homeTeam).append(" 0");
        this.awayTotal = new StringBuffer(awayTeam).append(" 0");
        this.gameCounter = gameCounter;

    }

    public void updateScore(int homeScore, int awayScore) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        homeTotal = new StringBuffer(homeTeam).append(" ").append(homeScore);
        awayTotal = new StringBuffer(awayTeam).append(" ").append(awayScore);
    }

    public int getScoreTotal() {
        return homeScore + awayScore;
    }

    public String getDisplaySummary() {
        return homeTotal + " - " + awayTotal;
    }


    public Integer getGameCounter() {
        return gameCounter;
    }

}
