package cinema.db.tables;

public class Ticket {
    private final int ticketCode;
    private final float price;
    private final int seatNumber;
    private final int roomId;
    private final int turn;
    private final String movieTitle;
    private final String CF;
    private final String email;

    public Ticket(int ticketCode, float price, int seatNumber, int roomId, final int turn, String movieTitle, final String CF, final String email) {
        this.ticketCode = ticketCode;
        this.price = price;
        this.seatNumber = seatNumber;
        this.movieTitle = movieTitle;
        this.roomId = roomId;
        this.turn = turn;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public String getCF() {
        return CF;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getTurn() {
        return turn;
    }
}
