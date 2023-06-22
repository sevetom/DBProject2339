package cinema.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import cinema.db.tables.Film;
import cinema.db.tables.FilmScreenings;
import cinema.db.tables.Room;
import cinema.db.tables.Screening;
import cinema.db.tables.Snack;
import cinema.db.tables.Ticket;
import cinema.db.tables.ReservedTicket;
import cinema.db.tables.Turn;
import cinema.db.tables.User;
import cinema.db.tables.Worker;
import cinema.utils.Utils;

/**
 * This class contains the logic of the application.
 * It's used to communicate with the database.
 */
public class Logic {
    final private Connection connection;
    public Logic(final Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    public Set<FilmScreenings> getFilmScreenings() {
        final String query = "SELECT F.Titolo, F.Genere, F.DataUscita, P.PostiRimanenti, P.NumeroSala, T.Data, T.OraInizio, T.CodiceTurno" 
                + " FROM film F, proiezione P, turno T"
                + " WHERE F.Titolo = P.Titolo"
                + " AND P.CodiceTurno = T.CodiceTurno";
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final Set<FilmScreenings> films = new HashSet<>();
            while (result.next()) {
                final String Title = result.getString("Titolo");
                final String Genre = result.getString("Genere");
                final Date ReleaseDate = Utils.sqlDateToDate(result.getDate("DataUscita"));
                final int SeatsRemaining = result.getInt("PostiRimanenti");
                final int RoomId = result.getInt("NumeroSala");
                final Date ScreeningDate = Utils.sqlDateToDate(result.getDate("Data"));
                final Time ScreeningTime = result.getTime("OraInizio");
                final int TurnId = result.getInt("CodiceTurno");
                final FilmScreenings film = new FilmScreenings(Title, Genre, ReleaseDate, SeatsRemaining, RoomId, ScreeningDate, ScreeningTime, TurnId);
                films.add(film);
            }
            return films;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<Film> getFilms() {
        final String query = "SELECT * FROM film;";
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final Set<Film> films = new HashSet<>();
            while (result.next()) {
                final String Title = result.getString("Titolo");
                final String Genre = result.getString("Genere");
                final Date ReleaseDate = Utils.sqlDateToDate(result.getDate("DataUscita"));
                final Film film = new Film(Title, Genre, ReleaseDate);
                films.add(film);
            }
            return films;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<Ticket> getTickets() {
        final String query = "SELECT * FROM biglietto;";
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final Set<Ticket> tickets = new HashSet<>();
            while (result.next()) {
                final int TicketCode = result.getInt("CodiceBiglietto");
                final float Price = result.getFloat("Prezzo");
                final int SeatNumber = result.getInt("NumeroPosto");
                final String MovieTitle = result.getString("TitoloFilm");
                final Date ScreeningDate = Utils.sqlDateToDate(result.getDate("Data"));
                final Time ScreeningTime = result.getTime("Orario");
                final int RoomNumber = result.getInt("NumeroSala");
                final String Email = result.getString("Email");
                final int ReservationCode = result.getInt("CodicePrenotazione");
                final String CF = result.getString("CodiceFiscale");
                final Ticket ticket = new Ticket(TicketCode, Price, SeatNumber, MovieTitle, ScreeningDate, ScreeningTime, RoomNumber, Email, ReservationCode, CF);
                tickets.add(ticket);
            }
            return tickets;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<Snack> getSnacks() {
        final String query = "SELECT * FROM alimento;";
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final Set<Snack> snacks = new HashSet<>();
            while (result.next()) {
                final int SellId = result.getInt("CodiceVendita");
                final String Type = result.getString("Tipo");
                final float Price = result.getFloat("Prezzo");
                final String Brand = result.getString("Marca");
                final String CF = result.getString("CodiceFiscale");
                final Snack snack = new Snack(SellId, Type, Price, Brand, CF);
                snacks.add(snack);
            }
            return snacks;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<Worker> getWorkers() {
        final String query = "SELECT * FROM dipendente;";
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final Set<Worker> workers = new HashSet<>();
            while (result.next()) {
                final String CF = result.getString("CodiceFiscale");
                final String Role = result.getString("Ruolo");
                final String Name = result.getString("Nome");
                final String Surname = result.getString("Cognome");
                final String PW = result.getString("PW");
                final String City = result.getString("Citta");
                final String Street = result.getString("Via");
                final int HouseNumber = result.getInt("NumCivico");
                final int Intern = result.getInt("Interno");
                final Worker worker = new Worker(CF, Role, Name, Surname, PW, City, Street, HouseNumber, Intern);
                workers.add(worker);
            }
            return workers;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<User> getUsers() {
        final String query = "SELECT * FROM abbonato;";
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final Set<User> users = new HashSet<>();
            while (result.next()) {
                final String Email = result.getString("Email");
                final Date birth = Utils.sqlDateToDate(result.getDate("DataNascita"));
                final int cap = result.getInt("CAP");
                final String cardName = result.getString("NomePropCarta");
                final long cardNumber = result.getLong("NumeroCarta");
                final Date cardExpire = Utils.sqlDateToDate(result.getDate("DataScadenzaCarta"));
                final int cvc = result.getInt("CVC");
                final String Name = result.getString("Nome");
                final String Surname = result.getString("Cognome");
                final String PW = result.getString("PW");
                final String City = result.getString("Citta");
                final String Street = result.getString("Via");
                final int HouseNumber = result.getInt("NumCivico");
                final int Intern = result.getInt("Interno");
                final User user = new User(Email, birth, cap, cardName, cardNumber, cardExpire, cvc, Name, 
                        Surname, PW, City, Street, HouseNumber, Intern);
                users.add(user);
            }
            return users;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<Room> getRooms() {
        final String query = "SELECT * FROM sala;";
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final Set<Room> rooms = new HashSet<>();
            while (result.next()) {
                final int RoomNumber = result.getInt("NumeroSala");
                final int Seats = result.getInt("NumeroPosti");
                final String cf = result.getString("CodiceFiscale");
                final Room room = new Room(RoomNumber, Seats, cf);
                rooms.add(room);
            }
            return rooms;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<Turn> getTurns() {
        final String query = "SELECT * FROM turno;";
        try (final Statement statement = this.connection.createStatement()) {
            final ResultSet result = statement.executeQuery(query);
            final Set<Turn> turns = new HashSet<>();
            while (result.next()) {
                final int TurnId = result.getInt("CodiceTurno");
                final Date Date = Utils.sqlDateToDate(result.getDate("Data"));
                final Time StartTime = result.getTime("OraInizio");
                final Time EndTime = result.getTime("OraFine");
                final String CF = result.getString("CodiceFiscale");
                final Turn turn = new Turn(TurnId, Date, StartTime, EndTime, CF);
                turns.add(turn);
            }
            return turns;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Worker checkWorkerLogin(final String username, final String password) {
        final String query = "SELECT * FROM dipendente WHERE CodiceFiscale = ? AND PW = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final String CF = result.getString("CodiceFiscale");
                final String role = result.getString("Ruolo");
                final String name = result.getString("Nome");
                final String surname = result.getString("Cognome");
                final String pw= result.getString("PW");
                final String city = result.getString("Citta");
                final String street = result.getString("Via");
                final int houseNumber = result.getInt("NumCivico");
                final int intern = result.getInt("Interno");
                final Worker worker = new Worker(CF, role, name, surname, pw, city, street, houseNumber, intern);
                return worker;
            }
            return null;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public User checkUserLogin(final String username, final String password) {
        final String query = "SELECT * FROM abbonato WHERE Email = ? AND PW = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            final ResultSet result = statement.executeQuery();
            if (result.next()) {
                final String email = result.getString("Email");
                final Date birth = Utils.sqlDateToDate(result.getDate("DataNascita"));
                final int cap = result.getInt("CAP");
                final String cardName = result.getString("NomePropCarta");
                final long cardNumber = result.getLong("NumeroCarta");
                final Date cardExpire = Utils.sqlDateToDate(result.getDate("DataScadenzaCarta"));
                final int cardCvc = result.getInt("CVC");
                final String name = result.getString("Nome");
                final String surname = result.getString("Cognome");
                final String pw= result.getString("PW");
                final String city = result.getString("Citta");
                final String street = result.getString("Via");
                final int houseNumber = result.getInt("NumCivico");
                final int intern = result.getInt("Interno");
                final User user = new User(email, birth, cap, cardName, cardNumber, cardExpire, cardCvc, name, surname, pw, city, street, houseNumber, intern);
                return user;
            }
            return null;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insertUser(final String email, final Date birth, final int cap, final String cardName, 
            final long cardNumber, final Date cardExpire, final int cardCvc, final String name, 
            final String surname, final String pw, final String city, final String street, final int houseNumber, final int intern) {
        final String query = "INSERT INTO abbonato (Email, DataNascita, CAP, NomePropCarta, NumeroCarta, DataScadenzaCarta, CVC, Nome, Cognome, PW, Citta, Via, NumCivico, Interno)" 
                + " VALUES (?, ? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setDate(2, Utils.dateToSqlDate(birth));
            statement.setInt(3, cap);
            statement.setString(4, cardName);
            statement.setLong(5, cardNumber);
            statement.setDate(6, Utils.dateToSqlDate(cardExpire));
            statement.setInt(7, cardCvc);
            statement.setString(8, name);
            statement.setString(9, surname);
            statement.setString(10, pw);
            statement.setString(11, city);
            statement.setString(12, street);
            statement.setInt(13, houseNumber);
            statement.setInt(14, intern);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insertWorker(final String CF, final String role, final String Name, 
            final String Surname, final String password, final String city, 
            final String street, final int houseNumber, final int intern) {
        final String query = "INSERT INTO dipendente (CodiceFiscale, Ruolo, Nome, Cognome, PW, Citta, Via, NumCivico, Interno)" 
                + " VALUES (?, ? , ? , ?, ?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, CF);
            statement.setString(2, role);
            statement.setString(3, Name);
            statement.setString(4, Surname);
            statement.setString(5, password);
            statement.setString(6, city);
            statement.setString(7, street);
            statement.setInt(8, houseNumber);
            statement.setInt(9, intern);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insertFilm(final String title, final String genre, final Date releaseDate) {
        final String query = "INSERT INTO film (Titolo, Genere, DataUscita)" 
                + " VALUES (?, ? , ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setString(2, genre);
            statement.setDate(3, Utils.dateToSqlDate(releaseDate));
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insertTurn(final Date date, final LocalTime startTime, final LocalTime endFilm, final String cf) {
        final String query = "INSERT INTO turno (CodiceTurno, Data, OraInizio, OraFine, CodiceFiscale)" 
                + " VALUES (?, ? , ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            // takes the highest number bewtween turns and adds 1
            final int turnId = this.getTurns().stream().mapToInt(t -> t.getTurnId()).max().orElse(0) + 1;
            statement.setInt(1, turnId);
            statement.setDate(2, Utils.dateToSqlDate(date));
            statement.setTime(3, Time.valueOf(startTime));
            statement.setTime(4, Time.valueOf(endFilm));
            statement.setString(5, cf);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insertRoom(final int seats, final String cf) {
        final String query = "INSERT INTO sala (NumeroSala, NumeroPosti, CodiceFiscale)" 
                + " VALUES (?, ? , ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            // takes the highest number bewtween rooms and adds 1
            final int roomId = this.getRooms().stream().mapToInt(r -> r.getRoomId()).max().orElse(0) + 1;
            statement.setInt(1, roomId);
            statement.setInt(2, seats);
            statement.setString(3, cf);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insertScreening(final int turnId, final int roomId, final String filmTitle) {
        final String query = "INSERT INTO proiezione (PostiRimanenti, CodiceTurno, NumeroSala, TitoloFilm)" 
                + " VALUES (?, ? , ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final int seats = this.getRooms().stream().filter(r -> r.getRoomId() == roomId).mapToInt(r -> r.getSeats()).findFirst().orElse(0);
            statement.setInt(1, seats);
            statement.setInt(2, turnId);
            statement.setInt(3, roomId);
            statement.setString(4, filmTitle);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<ReservedTicket> getTicketsReserved(final User loggedUser) {
        final String query = "SELECT CodiceBiglietto, Prezzo, NumeroPosto, TitoloFilm, Data, Orario, NumeroSala, Email, CodicePrenotazione, CodiceFiscale " 
                + "FROM biglietto WHERE Email = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, loggedUser.getEmail());
            final ResultSet result = statement.executeQuery();
            final Set<ReservedTicket> tickets = new HashSet<>();
            while (result.next()) {
                final int ticketId = result.getInt("CodiceBiglietto");
                final float price = result.getFloat("Prezzo");
                final int seatNumber = result.getInt("NumeroPosto");
                final String filmTitle = result.getString("TitoloFilm");
                final Date screeningDate = Utils.sqlDateToDate(result.getDate("Data"));
                final Time screeningTime = result.getTime("Orario");
                final int roomNumber = result.getInt("NumeroSala");
                final String userEmail = result.getString("Email");
                final int reservationId = result.getInt("CodicePrenotazione");
                final String userCF = result.getString("CodiceFiscale");
                final ReservedTicket ticket = new ReservedTicket(ticketId, price, seatNumber, filmTitle, screeningDate, screeningTime, roomNumber, userEmail, reservationId, userCF);
                tickets.add(ticket);
            }
            return tickets;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean insertReservedTicket(final User loggedUser, final int turnId, final String filmTitle, final int room) {
        final Screening screening = this.getScreening(turnId, filmTitle, room);
        final Turn turn = this.getTurn(turnId);
        if (screening == null || turn == null) {
            return false;
        }
        final String query = "INSERT INTO biglietto (CodiceBiglietto, Prezzo, NumeroPosto, TitoloFilm, Data, Orario, NumeroSala, Email, CodicePrenotazione, CodiceFiscale)" 
                + " VALUES (?, ? , ? , ?, ?, ?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final String query2 = "SELECT MAX(CodiceBiglietto) FROM biglietto;";
            final PreparedStatement statement2 = this.connection.prepareStatement(query2);
            final ResultSet result = statement2.executeQuery();
            int ticketId = 0;
            if (result.next()) {
                ticketId = result.getInt(1) + 1;
            }
            statement.setInt(1, ticketId);
            statement.setFloat(2, Float.parseFloat("8.00"));
            statement.setInt(3, screening.getSeatsRemaining());
            final String query3 = "UPDATE proiezione SET PostiRimanenti = ? WHERE CodiceTurno = ? AND NumeroSala = ? AND Titolo = ?;";
            final PreparedStatement statement3 = this.connection.prepareStatement(query3);
            statement3.setInt(1, screening.getSeatsRemaining() - 1);
            statement3.setInt(2, turnId);
            statement3.setInt(3, room);
            statement3.setString(4, filmTitle);
            statement3.executeUpdate();
            statement.setString(4, filmTitle);
            statement.setDate(5, Utils.dateToSqlDate(turn.getDate()));
            statement.setTime(6, turn.getStartingTime());
            statement.setInt(7, room);
            statement.setString(8, loggedUser.getEmail());
            final String query4 = "SELECT MAX(CodicePrenotazione) FROM biglietto;";
            final PreparedStatement statement4 = this.connection.prepareStatement(query4);
            final ResultSet result2 = statement4.executeQuery();
            int reservationId = 0;
            if (result2.next()) {
                reservationId = result2.getInt(1) + 1;
            }
            statement.setInt(9, reservationId);
            statement.setString(10, null);
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean insertFreeTicket(final Worker loggedWorker, final float price, final String filmTitle, 
            final int room, final int turnId) {
        final Screening screening = this.getScreening(turnId, filmTitle, room);
        final Turn turn = this.getTurn(turnId);
        if (screening == null || turn == null) {
            return false;
        }
        final String query = "INSERT INTO biglietto (CodiceBiglietto, Prezzo, NumeroPosto, TitoloFilm, Data, Orario, NumeroSala, Email, CodicePrenotazione, CodiceFiscale)" 
                + " VALUES (?, ? , ? , ?, ?, ?, ?, ?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final String query2 = "SELECT MAX(CodiceBiglietto) FROM biglietto;";
            final PreparedStatement statement2 = this.connection.prepareStatement(query2);
            final ResultSet result = statement2.executeQuery();
            int ticketId = 0;
            if (result.next()) {
                ticketId = result.getInt(1) + 1;
            }
            statement.setInt(1, ticketId);
            statement.setFloat(2, price);
            statement.setInt(3, screening.getSeatsRemaining());
            final String query3 = "UPDATE proiezione SET PostiRimanenti = ? WHERE CodiceTurno = ? AND NumeroSala = ? AND Titolo = ?;";
            final PreparedStatement statement3 = this.connection.prepareStatement(query3);
            statement3.setInt(1, screening.getSeatsRemaining() - 1);
            statement3.setInt(2, turnId);
            statement3.setInt(3, room);
            statement3.setString(4, filmTitle);
            statement3.executeUpdate();
            statement.setString(4, filmTitle);
            statement.setDate(5, Utils.dateToSqlDate(turn.getDate()));
            statement.setTime(6, turn.getStartingTime());
            statement.setInt(7, room);
            statement.setString(8, null);
            statement.setInt(9, 0);
            statement.setString(10, loggedWorker.getCF());
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }  
    }

    public Turn getTurn(final int turnId) {
        final String query = "SELECT CodiceTurno, Data, OraInizio, OraFine, CodiceFiscale " 
                + "FROM turno WHERE CodiceTurno = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, turnId);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                final int turnId2 = result.getInt("CodiceTurno");
                final Date screeningDate = Utils.sqlDateToDate(result.getDate("Data"));
                final Time screeningTime = result.getTime("OraInizio");
                final Time screeningTimeEnd = result.getTime("OraFine");
                final String userCF = result.getString("CodiceFiscale");
                final Turn turn = new Turn(turnId2, screeningDate, screeningTime, screeningTimeEnd, userCF);
                return turn;
            }
            return null;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<Turn> getWorkerTurns(final Worker loggedWorker) {
        final String query = "SELECT CodiceTurno, Data, OraInizio, OraFine, CodiceFiscale " 
                + "FROM turno WHERE CodiceFiscale = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, loggedWorker.getCF());
            final ResultSet result = statement.executeQuery();
            final Set<Turn> turns = new HashSet<>();
            while (result.next()) {
                final int turnId = result.getInt("CodiceTurno");
                final Date screeningDate = Utils.sqlDateToDate(result.getDate("Data"));
                final Time screeningTime = result.getTime("OraInizio");
                final Time screeningTimeEnd = result.getTime("OraFine");
                final String userCF = result.getString("CodiceFiscale");
                final Turn turn = new Turn(turnId, screeningDate, screeningTime, screeningTimeEnd, userCF);
                turns.add(turn);
            }
            return turns;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Screening getScreening(final int turnId, final String filmTitle, final int room) {
        final String query = "SELECT PostiRimanenti, CodiceTurno, NumeroSala, Titolo " 
                + "FROM proiezione WHERE CodiceTurno = ? AND NumeroSala = ? AND Titolo = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, turnId);
            statement.setInt(2, room);
            statement.setString(3, filmTitle);
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                final int remainingSeats = result.getInt("PostiRimanenti");
                final int turnId2 = result.getInt("CodiceTurno");
                final int roomNumber = result.getInt("NumeroSala");
                final String filmTitle2 = result.getString("Titolo");
                final Screening screening = new Screening(remainingSeats, turnId2, roomNumber, filmTitle2);
                return screening;
            }
            return null;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Set<Room> getWorkerRooms(final Worker loggedWorker) {
        final String query = "SELECT NumeroSala, NumeroPosti, CodiceFiscale " 
                + "FROM sala WHERE CodiceFiscale = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, loggedWorker.getCF());
            final ResultSet result = statement.executeQuery();
            final Set<Room> rooms = new HashSet<>();
            while (result.next()) {
                final int roomNumber = result.getInt("NumeroSala");
                final int totalSeats = result.getInt("NumeroPosti");
                final String userCF = result.getString("CodiceFiscale");
                final Room room = new Room(roomNumber, totalSeats, userCF);
                rooms.add(room);
            }
            return rooms;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void insertSnack(final String type, final float price, final String brand, final Worker loggedWorker) {
        final String query = "INSERT INTO snack (CodiceVendita, Tipo, Prezzo, Marca, CodiceFiscale) VALUES (?, ?, ?);";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            final String query2 = "SELECT MAX(CodiceVendita) FROM snack;";
            final PreparedStatement statement2 = this.connection.prepareStatement(query2);
            final ResultSet result = statement2.executeQuery();
            int saleId = 0;
            if (result.next()) {
                saleId = result.getInt(1) + 1;
            }
            statement.setInt(1, saleId);
            statement.setString(2, type);
            statement.setFloat(3, price);
            statement.setString(4, brand);
            statement.setString(5, loggedWorker.getCF());
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean removeUser(final String email) {
        final String query = "DELETE FROM abbonato WHERE Email = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean removeWorker(final String cf) {
        final String query = "DELETE FROM dipendente WHERE CodiceFiscale = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, cf);
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean removeFilm(final String title) {
        final String query = "DELETE FROM film WHERE Titolo = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.executeUpdate();
            final String query2 = "DELETE FROM proiezione WHERE Titolo = ?;";
            final PreparedStatement statement2 = this.connection.prepareStatement(query2);
            statement2.setString(1, title);
            statement2.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean removeScreening(final String title, final int room, final int turn) {
        final String query = "DELETE FROM proiezione WHERE Titolo = ? AND NumeroSala = ? AND CodiceTurno = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, title);
            statement.setInt(2, room);
            statement.setInt(3, turn);
            statement.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean removeTurn(final int turnId) {
        final String query = "DELETE FROM turno WHERE CodiceTurno = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, turnId);
            statement.executeUpdate();
            // removes the screening associated with the turn
            final String query2 = "DELETE FROM proiezione WHERE CodiceTurno = ?;";
            final PreparedStatement statement2 = this.connection.prepareStatement(query2);
            statement2.setInt(1, turnId);
            statement2.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Boolean removeRoom(final int roomNumber) {
        final String query = "DELETE FROM sala WHERE NumeroSala = ?;";
        try (final PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, roomNumber);
            statement.executeUpdate();
            final String query2 = "DELETE FROM proiezione WHERE NumeroSala = ?;";
            final PreparedStatement statement2 = this.connection.prepareStatement(query2);
            statement2.setInt(1, roomNumber);
            statement2.executeUpdate();
            return true;
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
