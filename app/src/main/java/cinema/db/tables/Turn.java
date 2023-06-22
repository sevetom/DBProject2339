package cinema.db.tables;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Turn {
    private final int turnId;
    private final Date date;
    private final String showDate;
    private final Time startingTime;
    private final Time endingTime;
    private final String CF;

    public Turn(int turnId, Date date, Time startingTime, Time endingTime, String CF) {
        this.turnId = turnId;
        this.date = date;
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        this.showDate = "" + cal.get(Calendar.DAY_OF_MONTH) +"/"+ cal.get(Calendar.MONTH) +"/"+ cal.get(Calendar.YEAR);
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.CF = CF;
    }

    public int getTurnId() {
        return turnId;
    }

    public Date getDate() {
        return date;
    }

    public String getShowDate() {
        return showDate;
    }

    public Time getStartingTime() {
        return startingTime;
    }

    public Time getEndingTime() {
        return endingTime;
    }

    public String getCF() {
        return CF;
    }
}
