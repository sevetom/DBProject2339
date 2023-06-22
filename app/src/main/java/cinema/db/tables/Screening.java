package cinema.db.tables;

public class Screening {
    private final int seatsRemaining;
    private final int turnId;
    private final int roomId;
    private final String movieTitle;

    public Screening(int seatsRemaining, int turnId, int roomId, String movieTitle) {
        this.seatsRemaining = seatsRemaining;
        this.turnId = turnId;
        this.roomId = roomId;
        this.movieTitle = movieTitle;
    }

    public int getSeatsRemaining() {
        return seatsRemaining;
    }

    public int getTurnId() {
        return turnId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }
}
