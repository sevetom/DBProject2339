package cinema.db.tables;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class ReservedTicket {
    private final int ticketCode;
    private final int seatNumber;
    private final String filmTitle;
    private final int roomId;
    private final Date date;
    private final String showDate;
    private final Time time;

    public ReservedTicket(final int ticketCode, final String filmTitle, final int seatNumber, final int roomId, final Date date, final Time time) {
        this.ticketCode = ticketCode;
        this.seatNumber = seatNumber;
        this.filmTitle = filmTitle;
        this.roomId = roomId;
        this.date = date;
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        this.showDate = "" + cal.get(Calendar.DAY_OF_MONTH) +"/"+ cal.get(Calendar.MONTH) +"/"+ cal.get(Calendar.YEAR);
        this.time = time;
    }

    public int getTicketCode() {
        return this.ticketCode;
    }

    public int getSeatNumber() {
        return this.seatNumber;
    }

    public String getFilmTitle() {
        return this.filmTitle;
    }

    public Date getDate() {
        return this.date;
    }

    public String getShowDate() {
        return this.showDate;
    }

    public Time getTime() {
        return this.time;
    }

    public int getRoomId() {
        return this.roomId;
    }
}
