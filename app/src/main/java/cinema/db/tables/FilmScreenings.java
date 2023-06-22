package cinema.db.tables;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class FilmScreenings {
    private final String title;
    private final String genre;
    private final Date releaseDate;
    private final int showReleaseDate;
    private final int seatsRemaining;
    private final int roomId;
    private final Date screeningDate;
    private final String showScreeningDate;
    private final Time screeningTime;
    private final int turnId;

    public FilmScreenings(final String title, final String genre, final Date releaseDate, final int seatsRemaining, final int roomId, final Date screeningDate, final Time screeningTime, final int turnId) {
        this.title = title;
        this.genre = genre;
        this.seatsRemaining = seatsRemaining;
        this.roomId = roomId;
        this.screeningTime = screeningTime;
        this.turnId = turnId;
        this.releaseDate = releaseDate;
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate);
        this.screeningDate = screeningDate;
        this.showReleaseDate = calendar.get(Calendar.YEAR);
        calendar.setTime(screeningDate);
        this.showScreeningDate = "" + calendar.get(Calendar.DAY_OF_MONTH) +"/"+ 
                calendar.get(Calendar.MONTH) +"/"+ calendar.get(Calendar.YEAR);
    }
    
    public String getTitle() {
        return this.title;
    }

    public String getGenre() {
        return this.genre;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public int getShowReleaseDate() {
        return this.showReleaseDate;
    }

    public int getSeatsRemaining() {
        return this.seatsRemaining;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public Date getScreeningDate() {
        return this.screeningDate;
    }

    public String getShowScreeningDate() {
        return this.showScreeningDate;
    }

    public Time getScreeningTime() {
        return this.screeningTime;
    }

    public int getTurnId() {
        return this.turnId;
    }
}
