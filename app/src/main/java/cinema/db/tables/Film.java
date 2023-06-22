package cinema.db.tables;

import java.util.Calendar;
import java.util.Date;

public class Film {
    private final String title;
    private final String genre;
    private final Date releaseDate;
    private final String showReleaseString;

    public Film(String title, String genre, Date releaseDate) {
        this.title = title;
        this.genre = genre;
        this.releaseDate = releaseDate;
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(releaseDate);
        this.showReleaseString = ""+calendar.get(Calendar.YEAR);
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getShowReleaseString() {
        return showReleaseString;
    }
}
