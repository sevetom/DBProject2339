package cinema.db.tables;

public class Room {
    private final int roomId;
    private final int seats;
    private final String CF;

    public Room(int numeroSala, int numeroPosti, String codiceFiscale) {
        this.roomId = numeroSala;
        this.seats = numeroPosti;
        this.CF = codiceFiscale;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getSeats() {
        return seats;
    }

    public String getCF() {
        return CF;
    }
}
