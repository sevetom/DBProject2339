package cinema.db.tables;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class ReservedTicket {
    private final int ticketCode;
    private final float price;
    private final int seatNumber;
    private final String filmTitle;
    private final Date date;
    private final String showDate;
    private final Time time;
    private final int roomNumber;
    private final String email;
    private final int reservationCode;
    private final String CF;

    public ReservedTicket(final int ticketCode, final float price, final int seatNumber, final String filmTitle, final Date date, final Time time, final int roomNumber, final String email, final int reservationCode, final String CF) {
        this.ticketCode = ticketCode;
        this.price = price;
        this.seatNumber = seatNumber;
        this.filmTitle = filmTitle;
        this.date = date;
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        this.showDate = "" + cal.get(Calendar.DAY_OF_MONTH) +"/"+ cal.get(Calendar.MONTH) +"/"+ cal.get(Calendar.YEAR);
        this.time = time;
        this.roomNumber = roomNumber;
        this.email = email;
        this.reservationCode = reservationCode;
        this.CF = CF;
    }

    public int getTicketCode() {
        return this.ticketCode;
    }

    public float getPrice() {
        return this.price;
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

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public int getReservationCode() {
        return this.reservationCode;
    }

    public String getCF() {
        return this.CF;
    }
}
