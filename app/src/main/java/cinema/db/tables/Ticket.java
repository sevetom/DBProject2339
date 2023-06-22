package cinema.db.tables;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Ticket {
    private final int ticketCode;
    private final float price;
    private final int seatNumber;
    private final String movieTitle;
    private final Date date;
    private final String showDate;
    private final Time time;
    private final int roomNumber;
    private final String email;
    private final int reservationCode;
    private final String CF;

    public Ticket(int ticketCode, float price, int seatNumber, String movieTitle, Date date, Time time, int roomNumber, String email, int reservationCode, String CF) {
        this.ticketCode = ticketCode;
        this.price = price;
        this.seatNumber = seatNumber;
        this.movieTitle = movieTitle;
        this.date = date;
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.showDate = calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR);
        this.time = time;
        this.roomNumber = roomNumber;
        this.email = email;
        this.reservationCode = reservationCode;
        this.CF = CF;
    }

    public int getTicketCode() {
        return ticketCode;
    }

    public float getPrice() {
        return price;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Date getDate() {
        return date;
    }

    public String getShowDate() {
        return showDate;
    }

    public Time getTime() {
        return time;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getReservationCode() {
        return reservationCode;
    }

    public String getCF() {
        return CF;
    }
}
