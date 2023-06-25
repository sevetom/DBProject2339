/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
package cinema.graphics;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import cinema.db.tables.Film;
import cinema.db.tables.FilmScreenings;
import cinema.db.tables.ReservedTicket;
import cinema.db.tables.Room;
import cinema.db.tables.Snack;
import cinema.db.tables.Ticket;
import cinema.db.tables.Turn;
import cinema.db.tables.User;
import cinema.db.tables.Worker;
import cinema.model.Logic;
import cinema.utils.Utils;

public class View extends javax.swing.JFrame {

    private final Logic logic;
    private final Map<String, JTextField> loginFields = new HashMap<>();
    private final Map<String, JTextField> signInUserFields = new HashMap<>();
    private final Map<String, JTextField> signInWorkerFields = new HashMap<>();
    private final Map<String, JTextField> resTicketFields = new HashMap<>();
    private final Map<String, JTextField> sellFields = new HashMap<>();
    private final Map<String, JTextField> barFields = new HashMap<>();
    private final Map<String, JTextField> insFilmFields = new HashMap<>();
    private final Map<String, JTextField> insTurnFields = new HashMap<>();
    private final Map<String, JTextField> insRoomFields = new HashMap<>();
    private final Map<String, JTextField> insProFields = new HashMap<>();
    private final Map<String, JTextField> chRoomFields = new HashMap<>();
    private final Map<String, JTextField> chTurnFields = new HashMap<>();
    private Worker loggedWorker;
    private User loggedUser;

    /**
     * Creates new form View
     */
    public View(final Logic logic) {
        super("Cinema");
        this.initComponents();
        this.initLook();
        this.initSets();
        this.logic = logic;
        this.loadScreenings();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initSets() {
        this.loginFields.put("username", this.usernameField);
        this.loginFields.put( "password", this.passwordField);

        this.roleButtonGroup.add(this.roleSellButton);
        this.roleButtonGroup.add(this.roleCleanButton);

        this.signInUserFields.put("email", this.emailField);
        this.signInUserFields.put("name", this.nameField);
        this.signInUserFields.put("surname", this.surnameField);
        this.signInUserFields.put("birthD", this.birthFieldD);
        this.signInUserFields.put("birthM", this.birthFieldM);
        this.signInUserFields.put("birthY", this.birthFieldY);
        this.signInUserFields.put("city", this.cityField);
        this.signInUserFields.put("address", this.addressField);
        this.signInUserFields.put("civic", this.civicField);
        this.signInUserFields.put("intern", this.internField);
        this.signInUserFields.put("cap", this.capField);
        this.signInUserFields.put("cardName", this.cardNameField);
        this.signInUserFields.put("cardNumber", this.cardNumField);
        this.signInUserFields.put("cardExpD", this.cardExpFieldD);
        this.signInUserFields.put("cardExpM", this.cardExpFieldM);
        this.signInUserFields.put("cardExpY", this.cardExpFieldY);
        this.signInUserFields.put("cardCvc", this.cardCvcField);
        this.signInUserFields.put("password", this.pwSignInField);

        this.signInWorkerFields.put("CF", this.cfField);
        this.signInWorkerFields.put("name", this.nameField);
        this.signInWorkerFields.put("surname", this.surnameField);
        this.signInWorkerFields.put("city", this.cityField);
        this.signInWorkerFields.put("address", this.addressField);
        this.signInWorkerFields.put("civic", this.civicField);
        this.signInWorkerFields.put("intern", this.internField);
        this.signInWorkerFields.put("password", this.pwSignInField);

        this.resTicketFields.put("turn", this.resTurnField);
        this.resTicketFields.put("film", this.resTitleField);
        this.resTicketFields.put("room", this.resRoomField);

        this.sellFields.put("price", this.sellPriceField);
        this.sellFields.put("film", this.filmSellField);
        this.sellFields.put("room", this.roomSellField);
        this.sellFields.put("turn", this.sellTurnField);

        this.barFields.put("type", this.snackTypeField);
        this.barFields.put("price", this.snackPriceField);
        this.barFields.put("brand", this.BrandSnackField);

        this.insFilmFields.put("title", this.insFiTitleField);
        this.insFilmFields.put("genre", this.insFiGenreField);
        this.insFilmFields.put("releaseD", this.insFiReleaseFieldD);
        this.insFilmFields.put("releaseM", this.insFiReleaseFieldM);
        this.insFilmFields.put("releaseY", this.insFiReleaseFieldY);

        this.insTurnFields.put("dateD", this.insTurDateFieldD);
        this.insTurnFields.put("dateM", this.insTurDateFieldM);
        this.insTurnFields.put("dateY", this.insTurDateFieldY);
        this.insTurnFields.put("timeStart", this.insTurTimeStartField);
        this.insTurnFields.put("timeEnd", this.insTurTimeEndField);
        this.insTurnFields.put("cf", this.insTurCfField);

        this.insRoomFields.put("seats", this.insRomSeatsField);
        this.insRoomFields.put("cf", this.insRomCfField);

        this.insProFields.put("film", this.insProTitleField);
        this.insProFields.put("room", this.insProRomField);
        this.insProFields.put("turn", this.insProTurField);

        this.chRoomFields.put("id", this.changeRoomField);
        this.chRoomFields.put("cf", this.chRoomCfField);

        this.chTurnFields.put("id", this.changeTurnField);
        this.chTurnFields.put("cf", this.chTurnCfField);
    }

    private void loadScreenings() {
        for (FilmScreenings f : this.logic.getFilmScreenings()) {
            final String[] row = {f.getTitle(), f.getGenre(), String.valueOf(f.getShowReleaseDate()), 
                    String.valueOf(f.getSeatsRemaining()), String.valueOf(f.getRoomId()), f.getShowScreeningDate(), 
                    String.valueOf(f.getScreeningTime()), String.valueOf(f.getTurnId())};
            ((javax.swing.table.DefaultTableModel) this.filmsTable.getModel()).addRow(row);
        }
    }

    private void loadFilms() {
        for (Film f : this.logic.getFilms()) {
            final String[] row = {f.getTitle(), f.getGenre(), f.getShowReleaseString()};
            ((javax.swing.table.DefaultTableModel) this.filmsAdminTable.getModel()).addRow(row);
        }
    }

    private void loadTicketsReserved() {
        for (ReservedTicket t : this.logic.getTicketsReserved(this.loggedUser)) {
            final String[] row = {String.valueOf(t.getTicketCode()), t.getFilmTitle(), 
                    String.valueOf(t.getSeatNumber()), String.valueOf(t.getRoomId()), 
                    t.getShowDate(), String.valueOf(t.getTime())};
            ((javax.swing.table.DefaultTableModel) this.reservedTable.getModel()).addRow(row);
        }
    }

    private void loadWorkerTurns() {
        for (Turn t : this.logic.getWorkerTurns(this.loggedWorker)) {
            final String[] row = {String.valueOf(t.getTurnId()), t.getShowDate(), 
            String.valueOf(t.getStartingTime()), String.valueOf(t.getEndingTime())};
            ((javax.swing.table.DefaultTableModel) this.workerTurnsTable.getModel()).addRow(row);
        }
    }

    private void loadWorkerRooms() {
        for (Room r : this.logic.getWorkerRooms(this.loggedWorker)) {
            final String[] row = {String.valueOf(r.getRoomId()), String.valueOf(r.getSeats())};
            ((javax.swing.table.DefaultTableModel) this.cleanTable.getModel()).addRow(row);
        }
    }

    private void loadTickets() {
        for (Ticket t : this.logic.getTickets()) {
            final String[] row = {String.valueOf(t.getTicketCode()), String.valueOf(t.getPrice()), 
                    String.valueOf(t.getSeatNumber()), t.getMovieTitle(), 
                    String.valueOf(t.getRoomId()), String.valueOf(t.getTurn()),  
                    t.getCF(), t.getEmail()};
            ((javax.swing.table.DefaultTableModel) this.ticketsTable.getModel()).addRow(row);
        }
    }

    private void loadSnacks() {
        for (Snack s : this.logic.getSnacks()) {
            final String[] row = {String.valueOf(s.getSellId()), s.getType(), String.valueOf(s.getPrice()), 
                    s.getBrand(), s.getCF()};
            ((javax.swing.table.DefaultTableModel) this.barTable.getModel()).addRow(row);
        }
    }

    private void loadWorkers() {
        for (Worker w : this.logic.getWorkers()) {
            if (w.getCF().equals("admin")) {
                continue;
            }
            final String[] row = {w.getCF(), w.getRole(), w.getName(), w.getSurname(), 
                    w.getCity(), w.getStreet(), String.valueOf(w.getHouseNumber()), String.valueOf(w.getIntern())};
            ((javax.swing.table.DefaultTableModel) this.workersTable.getModel()).addRow(row);
        }
    }

    private void loadUsers() {
        for (User u : this.logic.getUsers()) {
            final String[] row = {u.getEmail(), u.getName(), u.getSurname(), u.getCity(), u.getStreet(), 
                    String.valueOf(u.getHouseNumber()), String.valueOf(u.getIntern())};
            ((javax.swing.table.DefaultTableModel) this.usersTable.getModel()).addRow(row);
        }
    }

    private void loadRooms() {
        for (Room r : this.logic.getRooms()) {
            final String[] row = {String.valueOf(r.getRoomId()), String.valueOf(r.getSeats()), String.valueOf(r.getCF())};
            ((javax.swing.table.DefaultTableModel) this.roomsTable.getModel()).addRow(row);
        }
    }

    private void loadTurns() {
        for (Turn t : this.logic.getTurns()) {
            final String[] row = {String.valueOf(t.getTurnId()), t.getShowDate(), 
            String.valueOf(t.getStartingTime()), String.valueOf(t.getEndingTime()), String.valueOf(t.getCF())};
            ((javax.swing.table.DefaultTableModel) this.turnsTable.getModel()).addRow(row);
        }
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        for (JTextField f : this.loginFields.values()) {
            if (f.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        final Boolean isWorker = this.loginCheckBox.isSelected();
        if (isWorker) {
            this.loggedWorker = this.logic.checkWorkerLogin(this.loginFields.get("username").getText(), this.loginFields.get("password").getText());
            if (this.loggedWorker == null) {
                JOptionPane.showMessageDialog(this, "Username o password errati", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (this.loggedWorker.getCF().equals("admin")) {
                this.MainMenu.setComponentAt(1, this.adminArea);
                this.loadTickets();
                this.loadWorkers();
                this.loadSnacks();
                this.loadUsers();
                this.loadRooms();
                this.loadTurns();
                this.loadFilms();
            } else {
                this.MainMenu.setComponentAt(1, this.workerArea);
                if (this.loggedWorker.getRole().equals("pulizie")) {
                    this.loadWorkerRooms();
                    this.workerPane.remove(this.workerPane.indexOfTab("Registra Vendita"));
                } else {
                    this.workerPane.remove(this.workerPane.indexOfTab("Sale da pulire"));
                }
                this.loadWorkerTurns();
            }
            this.MainMenu.setTitleAt(1, this.loggedWorker.getRole());
        } else {
            this.loggedUser = this.logic.checkUserLogin(this.loginFields.get("username").getText(), this.loginFields.get("password").getText());
            if (this.loggedUser == null) {
                JOptionPane.showMessageDialog(this, "Username o password errati", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.MainMenu.setComponentAt(1, this.userArea);
            this.MainMenu.setTitleAt(1, this.loggedUser.getName() + " " + this.loggedUser.getSurname());
            this.loadTicketsReserved();
        }
        this.repaint();
    }//GEN-LAST:event_loginButtonActionPerformed

    private void workerSignButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workerSignButtonActionPerformed
        final boolean activated = this.workerSignButton.isSelected();
        this.cfField.setEnabled(activated);
        this.roleSellButton.setEnabled(activated);
        this.roleCleanButton.setEnabled(activated);
        this.birthFieldD.setEnabled(!activated);
        this.birthFieldM.setEnabled(!activated);
        this.birthFieldY.setEnabled(!activated);
        this.emailField.setEnabled(!activated);
        this.capField.setEnabled(!activated);
        this.cardNameField.setEnabled(!activated);
        this.cardNumField.setEnabled(!activated);
        this.cardExpFieldD.setEnabled(!activated);
        this.cardExpFieldM.setEnabled(!activated);
        this.cardExpFieldY.setEnabled(!activated);
        this.cardCvcField.setEnabled(!activated);
    }//GEN-LAST:event_workerSignButtonActionPerformed

    private void signButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signButtonActionPerformed
        if (this.signInUserFields.get("password").getText().equals("0000")) {
            JOptionPane.showMessageDialog(this, "Password non valida", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (this.workerSignButton.isSelected()) {
            for (JTextField f : this.signInWorkerFields.values()) {
                if (f.getText().isEmpty() && f != this.internField) {
                    JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            this.logic.insertWorker(this.signInWorkerFields.get("CF").getText(), this.roleCleanButton.isSelected() ? "pulizie" : "vendite", 
                    this.signInWorkerFields.get("name").getText(), this.signInWorkerFields.get("surname").getText(), 
                    this.signInWorkerFields.get("password").getText(), this.signInWorkerFields.get("city").getText(),
                    this.signInWorkerFields.get("address").getText(), Integer.parseInt(this.signInWorkerFields.get("civic").getText()), 
                    Integer.parseInt(this.signInWorkerFields.get("intern").getText()));
            JOptionPane.showMessageDialog(this, "Registrazione effettuata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            for (JTextField f : this.signInUserFields.values()) {
                if (f.getText().isEmpty() && f != this.internField) {
                    JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            this.logic.insertUser(this.signInUserFields.get("email").getText(), 
                    Utils.buildDate(Integer.parseInt(this.signInUserFields.get("birthD").getText()), 
                    Integer.parseInt(this.signInUserFields.get("birthM").getText()), Integer.parseInt(this.signInUserFields.get("birthY").getText())).get(),
                    Integer.parseInt(this.signInUserFields.get("cap").getText()), this.signInUserFields.get("cardName").getText(), 
                    Long.parseLong(this.signInUserFields.get("cardNumber").getText()), 
                    Utils.buildDate(Integer.parseInt(this.signInUserFields.get("cardExpD").getText()),
                    Integer.parseInt(this.signInUserFields.get("cardExpM").getText()), Integer.parseInt(this.signInUserFields.get("cardExpY").getText())).get(),
                    Integer.parseInt(this.signInUserFields.get("cardCvc").getText()), this.signInUserFields.get("name").getText(), 
                    this.signInUserFields.get("surname").getText(), this.signInUserFields.get("password").getText(),  
                    this.signInUserFields.get("city").getText(), this.signInUserFields.get("address").getText(), 
                    Integer.parseInt(this.signInUserFields.get("civic").getText()), 
                    this.signInUserFields.get("intern").getText().equals("") ? 0 : Integer.parseInt(this.signInUserFields.get("intern").getText()));
            JOptionPane.showMessageDialog(this, "Registrazione effettuata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        }
        for (JTextField f : this.signInUserFields.values()) {
            f.setText("");
        }
        for (JTextField f : this.signInWorkerFields.values()) {
            f.setText("");
        }
    }//GEN-LAST:event_signButtonActionPerformed

    private void resButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resButtonActionPerformed
        for (JTextField f : this.resTicketFields.values()) {
            if (f.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        final int turnId = Integer.parseInt(this.resTicketFields.get("turn").getText());
        final String filmTitle = this.resTicketFields.get("film").getText();
        final int room = Integer.parseInt(this.resTicketFields.get("room").getText());
        if (!this.logic.insertReservedTicket(this.loggedUser, turnId, filmTitle, room)) {
            JOptionPane.showMessageDialog(this, "Errore durante la prenotazione", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Prenotazione effettuata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        this.resTicketFields.values().forEach((f) -> {
            f.setText("");
        });
        ((javax.swing.table.DefaultTableModel) this.reservedTable.getModel()).setRowCount(0);
        this.loadTicketsReserved();
        this.repaint(room, room, room, turnId, room);
    }//GEN-LAST:event_resButtonActionPerformed

    private void sellTickButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellTickButtonActionPerformed
        for (JTextField f : this.sellFields.values()) {
            if (f.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (!this.logic.insertFreeTicket(this.loggedWorker, Float.parseFloat(this.sellFields.get("price").getText()), 
                this.sellFields.get("film").getText(), 
                Integer.parseInt(this.sellFields.get("room").getText()), 
                Integer.parseInt(this.sellFields.get("turn").getText()))) {
            JOptionPane.showMessageDialog(this, "Errore durante la vendita", "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "Vendita registrata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        this.sellFields.values().forEach((f) -> {
            f.setText("");
        });
        this.loadWorkerTurns();
        this.loadWorkerRooms();
    }//GEN-LAST:event_sellTickButtonActionPerformed

    private void sellSnackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellSnackButtonActionPerformed
        for (JTextField f : this.barFields.values()) {
            if (f.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        this.logic.insertSnack(this.barFields.get("type").getText(), 
                Float.parseFloat(this.barFields.get("price").getText()), this.barFields.get("brand").getText(), 
                this.loggedWorker);
        JOptionPane.showMessageDialog(this, "Vendita registrata con successo", "Successo", JOptionPane.INFORMATION_MESSAGE);
        this.barFields.values().forEach((f) -> {
            f.setText("");
        });
    }//GEN-LAST:event_sellSnackButtonActionPerformed

    private void insFiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insFiButtonActionPerformed
        for (JTextField f : this.insFilmFields.values()) {
            if (f.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi");
                return;
            }
        }
        this.logic.insertFilm(this.insFilmFields.get("title").getText(), this.insFilmFields.get("genre").getText(),
            Utils.buildDate(Integer.parseInt(this.insFilmFields.get("releaseD").getText()),
                Integer.parseInt(this.insFilmFields.get("releaseM").getText()),
                Integer.parseInt(this.insFilmFields.get("releaseY").getText())).get());
        JOptionPane.showMessageDialog(this, "Film inserito correttamente");
        for (JTextField f : this.insFilmFields.values()) {
            f.setText("");
        }
        this.loadFilms();
        this.repaint();
    }//GEN-LAST:event_insFiButtonActionPerformed

    private void insTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insTurnButtonActionPerformed
        for (JTextField f : this.insTurnFields.values()) {
            if (f.getText().isEmpty() && f.getName() != "cf") {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi");
                return;
            }
        }
        this.logic.insertTurn(Utils.buildDate(Integer.parseInt(this.insTurnFields.get("dateD").getText()),
            Integer.parseInt(this.insTurnFields.get("dateM").getText()),
            Integer.parseInt(this.insTurnFields.get("dateY").getText())).get(),
        LocalTime.parse(this.insTurnFields.get("timeStart").getText()),
        LocalTime.parse(this.insTurnFields.get("timeEnd").getText()),
        this.insTurnFields.get("cf").getText().equals("Codice Fiscale (opzionale)") ? null :
        this.insTurnFields.get("cf").getText());
        JOptionPane.showMessageDialog(this, "Turno inserito correttamente");
        for (JTextField f : this.insTurnFields.values()) {
            f.setText("");
        }
        for (JTextField f : this.chRoomFields.values()) {
            f.setText("");
        }
        this.loadTurns();
        this.repaint();
    }//GEN-LAST:event_insTurnButtonActionPerformed

    private void chRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chRoomButtonActionPerformed
        for (JTextField f : this.chRoomFields.values()) {
            if (f.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi");
                return;
            }
        }
        if (this.logic.changeRoomCf(Integer.parseInt(this.chRoomFields.get("id").getText()), 
                this.chRoomFields.get("cf").getText())) {
            JOptionPane.showMessageDialog(this, "Sala cambiata correttamente");
        } else {
            JOptionPane.showMessageDialog(this, "Errore durante il cambio sala", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        for (JTextField f : this.chRoomFields.values()) {
            f.setText("");
        }
        this.loadRooms();
        this.repaint();
    }//GEN-LAST:event_chRoomButtonActionPerformed

    private void chTurnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chTurnButtonActionPerformed
        for (JTextField f : this.chTurnFields.values()) {
            if (f.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi");
                return;
            }
        }
        if (this.logic.changeTurnCf(Integer.parseInt(this.chTurnFields.get("id").getText()), 
                this.chRoomFields.get("cf").getText())) {
            JOptionPane.showMessageDialog(this, "Turno cambiata correttamente");
        } else {
            JOptionPane.showMessageDialog(this, "Errore durante il cambio turno", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        this.loadTurns();
        this.repaint();
    }//GEN-LAST:event_chTurnButtonActionPerformed

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        workerArea = new javax.swing.JPanel();
        workerPane = new javax.swing.JTabbedPane();
        turnsPane = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        workerTurnsTable = new javax.swing.JTable();
        sellPanel = new javax.swing.JPanel();
        ticketSellLable = new javax.swing.JLabel();
        snackSellLable = new javax.swing.JLabel();
        snackTypeLable = new javax.swing.JLabel();
        snackPriceLable = new javax.swing.JLabel();
        brandSnackLable = new javax.swing.JLabel();
        sellPriceLable = new javax.swing.JLabel();
        filmSellLable = new javax.swing.JLabel();
        roomSellLable = new javax.swing.JLabel();
        sellPriceField = new javax.swing.JTextField();
        filmSellField = new javax.swing.JTextField();
        roomSellField = new javax.swing.JTextField();
        snackTypeField = new javax.swing.JTextField();
        snackPriceField = new javax.swing.JTextField();
        BrandSnackField = new javax.swing.JTextField();
        sellTickButton = new javax.swing.JButton();
        sellTurnField = new javax.swing.JTextField();
        sellTurnLable = new javax.swing.JLabel();
        sellSnackButton = new javax.swing.JButton();
        cleanPanel = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        cleanTable = new javax.swing.JTable();
        adminArea = new javax.swing.JPanel();
        adminPane = new javax.swing.JTabbedPane();
        ticketPanel = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ticketsTable = new javax.swing.JTable();
        barPanel = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        barTable = new javax.swing.JTable();
        userPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersTable = new javax.swing.JTable();
        roomPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        roomsTable = new javax.swing.JTable();
        filmsPanel = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        filmsAdminTable = new javax.swing.JTable();
        workerPanel = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        workersTable = new javax.swing.JTable();
        turnsPanel = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        turnsTable = new javax.swing.JTable();
        insertionPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        insFiLabel = new javax.swing.JLabel();
        insTurLabel = new javax.swing.JLabel();
        insRomLabel = new javax.swing.JLabel();
        insProLabel = new javax.swing.JLabel();
        insProTitleField = new javax.swing.JTextField();
        insFiTitleField = new javax.swing.JTextField();
        insFiGenreField = new javax.swing.JTextField();
        insFiReleaseFieldD = new javax.swing.JTextField();
        insRomSeatsField = new javax.swing.JTextField();
        insRomCfField = new javax.swing.JTextField();
        insProTurField = new javax.swing.JTextField();
        insProRomField = new javax.swing.JTextField();
        insTurDateFieldD = new javax.swing.JTextField();
        insTurTimeStartField = new javax.swing.JTextField();
        insTurTimeEndField = new javax.swing.JTextField();
        insTurCfField = new javax.swing.JTextField();
        insTurnButton = new javax.swing.JButton();
        insFiButton = new javax.swing.JButton();
        insRomButton = new javax.swing.JButton();
        insProButton = new javax.swing.JButton();
        insFiReleaseFieldM = new javax.swing.JTextField();
        insFiReleaseFieldY = new javax.swing.JTextField();
        insTurDateFieldM = new javax.swing.JTextField();
        insTurDateFieldY = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        modifyPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        ticketPriceLabel = new javax.swing.JLabel();
        ticketPriceField = new javax.swing.JTextField();
        ticketPriceButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        changeRoomField = new javax.swing.JTextField();
        chRoomCfField = new javax.swing.JTextField();
        changeTurnField = new javax.swing.JTextField();
        chTurnCfField = new javax.swing.JTextField();
        chRoomButton = new javax.swing.JButton();
        chTurnButton = new javax.swing.JButton();
        userArea = new javax.swing.JPanel();
        reservationPanel = new javax.swing.JPanel();
        resTitle = new javax.swing.JLabel();
        resTitleField = new javax.swing.JTextField();
        resTurnField = new javax.swing.JTextField();
        resButton = new javax.swing.JButton();
        resRoomField = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        reservedTable = new javax.swing.JTable();
        roleButtonGroup = new javax.swing.ButtonGroup();
        MainMenu = new javax.swing.JTabbedPane();
        screeningsArea = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        filmsTable = new javax.swing.JTable();
        privateArea = new javax.swing.JPanel();
        loginScreen = new javax.swing.JPanel();
        loginTitle = new javax.swing.JLabel();
        userData = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        password = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        loginCheckBox = new javax.swing.JCheckBox();
        registrationArea = new javax.swing.JPanel();
        signInScreen = new javax.swing.JPanel();
        signInTitle = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        nameLable = new javax.swing.JLabel();
        surnameLabel = new javax.swing.JLabel();
        birthLabel = new javax.swing.JLabel();
        cityLabel = new javax.swing.JLabel();
        streetLabel = new javax.swing.JLabel();
        cvivcLabel = new javax.swing.JLabel();
        internLable = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        surnameField = new javax.swing.JTextField();
        birthFieldD = new javax.swing.JTextField();
        cityField = new javax.swing.JTextField();
        addressField = new javax.swing.JTextField();
        civicField = new javax.swing.JTextField();
        internField = new javax.swing.JTextField();
        capLabel = new javax.swing.JLabel();
        capField = new javax.swing.JTextField();
        fatLabel = new javax.swing.JLabel();
        cardNameLabel = new javax.swing.JLabel();
        cardNameField = new javax.swing.JTextField();
        cardNumLabel = new javax.swing.JLabel();
        cardNumField = new javax.swing.JTextField();
        cardExpLabel = new javax.swing.JLabel();
        cardExpFieldD = new javax.swing.JTextField();
        cardCvcLable = new javax.swing.JLabel();
        cardCvcField = new javax.swing.JTextField();
        signButton = new javax.swing.JButton();
        pwSignInLable = new javax.swing.JLabel();
        pwSignInField = new javax.swing.JTextField();
        birthFieldM = new javax.swing.JTextField();
        birthFieldY = new javax.swing.JTextField();
        cardExpFieldM = new javax.swing.JTextField();
        cardExpFieldY = new javax.swing.JTextField();
        cfLable = new javax.swing.JLabel();
        cfField = new javax.swing.JTextField();
        workerSignButton = new javax.swing.JCheckBox();
        roleLabel = new javax.swing.JLabel();
        roleSellButton = new javax.swing.JRadioButton();
        roleCleanButton = new javax.swing.JRadioButton();

        workerTurnsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Turno", "Data", "Ora Inizio", "Ora Fine"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        workerTurnsTable.setColumnSelectionAllowed(true);
        jScrollPane5.setViewportView(workerTurnsTable);
        workerTurnsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout turnsPaneLayout = new javax.swing.GroupLayout(turnsPane);
        turnsPane.setLayout(turnsPaneLayout);
        turnsPaneLayout.setHorizontalGroup(
            turnsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        );
        turnsPaneLayout.setVerticalGroup(
            turnsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        workerPane.addTab("Turni", turnsPane);

        ticketSellLable.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        ticketSellLable.setText("Biglietto");

        snackSellLable.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        snackSellLable.setText("Bar");

        snackTypeLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        snackTypeLable.setText("Tipo");

        snackPriceLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        snackPriceLable.setText("Prezzo");

        brandSnackLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        brandSnackLable.setText("Marca");

        sellPriceLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sellPriceLable.setText("Prezzo");

        filmSellLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        filmSellLable.setText("Film");

        roomSellLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        roomSellLable.setText("Sala");

        sellTickButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sellTickButton.setText("Registra");
        sellTickButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellTickButtonActionPerformed(evt);
            }
        });

        sellTurnLable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sellTurnLable.setText("Turno");

        sellSnackButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sellSnackButton.setText("Registra");
        sellSnackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellSnackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sellPanelLayout = new javax.swing.GroupLayout(sellPanel);
        sellPanel.setLayout(sellPanelLayout);
        sellPanelLayout.setHorizontalGroup(
            sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellPanelLayout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(ticketSellLable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(snackSellLable)
                .addGap(164, 164, 164))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sellPanelLayout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(sellPriceLable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(filmSellLable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(sellPanelLayout.createSequentialGroup()
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roomSellLable)
                            .addComponent(sellTurnLable))
                        .addGap(31, 31, 31)
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sellTickButton)
                            .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(sellTurnField)
                                .addComponent(roomSellField, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                .addComponent(filmSellField)
                                .addComponent(sellPriceField)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 338, Short.MAX_VALUE)
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(brandSnackLable)
                    .addComponent(snackPriceLable)
                    .addComponent(snackTypeLable, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sellSnackButton)
                    .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(snackTypeField)
                        .addComponent(snackPriceField)
                        .addComponent(BrandSnackField, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                .addGap(112, 112, 112))
        );
        sellPanelLayout.setVerticalGroup(
            sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sellPanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ticketSellLable)
                    .addComponent(snackSellLable))
                .addGap(18, 18, 18)
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sellPanelLayout.createSequentialGroup()
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sellPriceLable)
                            .addComponent(sellPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(filmSellLable)
                            .addComponent(filmSellField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(roomSellField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roomSellLable)))
                    .addGroup(sellPanelLayout.createSequentialGroup()
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(snackTypeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(snackTypeLable))
                        .addGap(18, 18, 18)
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(snackPriceLable)
                            .addComponent(snackPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brandSnackLable)
                            .addComponent(BrandSnackField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sellPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(sellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sellTurnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sellTurnLable))
                        .addGap(37, 37, 37)
                        .addComponent(sellTickButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(sellPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(sellSnackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        workerPane.addTab("Registra Vendita", sellPanel);

        cleanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sala", "Posti"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        cleanTable.setColumnSelectionAllowed(true);
        jScrollPane6.setViewportView(cleanTable);
        cleanTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout cleanPanelLayout = new javax.swing.GroupLayout(cleanPanel);
        cleanPanel.setLayout(cleanPanelLayout);
        cleanPanelLayout.setHorizontalGroup(
            cleanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 968, Short.MAX_VALUE)
        );
        cleanPanelLayout.setVerticalGroup(
            cleanPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        workerPane.addTab("Sale da pulire", cleanPanel);

        javax.swing.GroupLayout workerAreaLayout = new javax.swing.GroupLayout(workerArea);
        workerArea.setLayout(workerAreaLayout);
        workerAreaLayout.setHorizontalGroup(
            workerAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(workerPane)
        );
        workerAreaLayout.setVerticalGroup(
            workerAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(workerPane)
        );

        ticketsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Biglietto", "Prezzo", "Posto", "Sala", "Turno", "Film", "CF", "Email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ticketsTable.setColumnSelectionAllowed(true);
        jScrollPane7.setViewportView(ticketsTable);
        ticketsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout ticketPanelLayout = new javax.swing.GroupLayout(ticketPanel);
        ticketPanel.setLayout(ticketPanelLayout);
        ticketPanelLayout.setHorizontalGroup(
            ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        ticketPanelLayout.setVerticalGroup(
            ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        adminPane.addTab("Biglietti", ticketPanel);

        barTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Vendita", "Tipo", "Prezzo", "Marca", "CF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        barTable.setColumnSelectionAllowed(true);
        jScrollPane9.setViewportView(barTable);
        barTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout barPanelLayout = new javax.swing.GroupLayout(barPanel);
        barPanel.setLayout(barPanelLayout);
        barPanelLayout.setHorizontalGroup(
            barPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        barPanelLayout.setVerticalGroup(
            barPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        adminPane.addTab("Bar", barPanel);

        usersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Email", "Data di nascita", "CAP", "Nome proprietario carta", "Numero carta", "Scadenza carta", "CVC", "Nome", "Cognome", "Citta'", "Via", "Numer civico", "Interno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Long.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        usersTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(usersTable);
        usersTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        adminPane.addTab("Abbonati", userPanel);

        roomsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sala", "Posti", "Codice Fiscale"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        roomsTable.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(roomsTable);
        roomsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout roomPanelLayout = new javax.swing.GroupLayout(roomPanel);
        roomPanel.setLayout(roomPanelLayout);
        roomPanelLayout.setHorizontalGroup(
            roomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        roomPanelLayout.setVerticalGroup(
            roomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        adminPane.addTab("Sale", roomPanel);

        filmsAdminTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titolo", "Genere", "Data di uscita"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        filmsAdminTable.setColumnSelectionAllowed(true);
        jScrollPane8.setViewportView(filmsAdminTable);
        filmsAdminTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout filmsPanelLayout = new javax.swing.GroupLayout(filmsPanel);
        filmsPanel.setLayout(filmsPanelLayout);
        filmsPanelLayout.setHorizontalGroup(
            filmsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        filmsPanelLayout.setVerticalGroup(
            filmsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        adminPane.addTab("Film", filmsPanel);

        workersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CF", "Ruolo", "Nome", "Cognome", "Citt�", "Via", "Civico", "Interno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        workersTable.setColumnSelectionAllowed(true);
        jScrollPane10.setViewportView(workersTable);
        workersTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout workerPanelLayout = new javax.swing.GroupLayout(workerPanel);
        workerPanel.setLayout(workerPanelLayout);
        workerPanelLayout.setHorizontalGroup(
            workerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        workerPanelLayout.setVerticalGroup(
            workerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        adminPane.addTab("Dipendenti", workerPanel);

        turnsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Turno", "Data", "Ora inizio", "Ora fine", "CF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        turnsTable.setColumnSelectionAllowed(true);
        jScrollPane11.setViewportView(turnsTable);
        turnsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout turnsPanelLayout = new javax.swing.GroupLayout(turnsPanel);
        turnsPanel.setLayout(turnsPanelLayout);
        turnsPanelLayout.setHorizontalGroup(
            turnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        turnsPanelLayout.setVerticalGroup(
            turnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
        );

        adminPane.addTab("Turni", turnsPanel);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Inserire i dati Richiesti");

        insFiLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        insFiLabel.setText("Film");

        insTurLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        insTurLabel.setText("Turno");

        insRomLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        insRomLabel.setText("Sala");

        insProLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        insProLabel.setText("Proiezione");

        insProTitleField.setText("Titolo");

        insFiTitleField.setText("Titolo");

        insFiGenreField.setText("Genere");

        insFiReleaseFieldD.setText("giorno");

        insRomSeatsField.setText("Posti");

        insRomCfField.setText("Addetto alle pulizie");

        insProTurField.setText("Turno");

        insProRomField.setText("Sala");

        insTurDateFieldD.setText("giorno");

        insTurTimeStartField.setText("hh:mm:ss");

        insTurTimeEndField.setText("hh:mm:ss");

        insTurCfField.setText("Codice Fiscale (opzionale)");

        insTurnButton.setText("Inserisci");
        insTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insTurnButtonActionPerformed(evt);
            }
        });

        insFiButton.setText("Inserisci");
        insFiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insFiButtonActionPerformed(evt);
            }
        });

        insRomButton.setText("Inserisci");
        insRomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insRomButtonActionPerformed(evt);
            }
        });

        insProButton.setText("Inserisci");
        insProButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insProButtonActionPerformed(evt);
            }
        });

        insFiReleaseFieldM.setText("mese");

        insFiReleaseFieldY.setText("anno");

        insTurDateFieldM.setText("mese");

        insTurDateFieldY.setText("anno");

        jLabel2.setText("Ora Inizio:");

        jLabel3.setText("Data Uscita:");

        jLabel4.setText("Ora Fine:");

        javax.swing.GroupLayout insertionPanelLayout = new javax.swing.GroupLayout(insertionPanel);
        insertionPanel.setLayout(insertionPanelLayout);
        insertionPanelLayout.setHorizontalGroup(
            insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertionPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(insertionPanelLayout.createSequentialGroup()
                        .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(insProLabel)
                            .addComponent(insRomLabel)
                            .addComponent(insTurLabel)
                            .addComponent(insFiLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(insertionPanelLayout.createSequentialGroup()
                                .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(insertionPanelLayout.createSequentialGroup()
                                        .addComponent(insFiTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(insFiGenreField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(insertionPanelLayout.createSequentialGroup()
                                        .addComponent(insTurDateFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(insTurDateFieldM, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(insTurDateFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(insertionPanelLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(insTurTimeStartField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(insTurTimeEndField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(insTurCfField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(insertionPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(insFiReleaseFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(insFiReleaseFieldM, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(insFiReleaseFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(insFiButton))))
                            .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(insertionPanelLayout.createSequentialGroup()
                                    .addComponent(insRomSeatsField)
                                    .addGap(18, 18, 18)
                                    .addComponent(insRomCfField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(insRomButton))
                                .addGroup(insertionPanelLayout.createSequentialGroup()
                                    .addComponent(insProTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(insProTurField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(insProRomField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(insProButton))))
                        .addGap(18, 18, 18)
                        .addComponent(insTurnButton)))
                .addGap(86, 96, Short.MAX_VALUE))
        );
        insertionPanelLayout.setVerticalGroup(
            insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertionPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insFiLabel)
                    .addComponent(insFiTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insFiGenreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insFiReleaseFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insFiReleaseFieldM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insFiReleaseFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(insFiButton))
                .addGap(18, 18, 18)
                .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insTurLabel)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(insTurDateFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(insTurDateFieldM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(insTurDateFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(insTurTimeStartField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(insTurTimeEndField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(insTurCfField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(insTurnButton)))
                .addGap(18, 18, 18)
                .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insRomLabel)
                    .addComponent(insRomSeatsField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insRomCfField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insRomButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(insertionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(insProLabel)
                    .addComponent(insProTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insProTurField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insProRomField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insProButton))
                .addContainerGap(204, Short.MAX_VALUE))
        );

        adminPane.addTab("Inserzioni", insertionPanel);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("Inserire il dato da modificare insieme al valore richiesto:");

        ticketPriceLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ticketPriceLabel.setText("Prezzo del biglietto base");

        ticketPriceField.setText("0.00");

        ticketPriceButton.setText("Modifica");
        ticketPriceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ticketPriceButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Addetto alla pulizia");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Turno di lavoro");

        changeRoomField.setText("Sala");

        chRoomCfField.setText("Codice Fiscale");

        changeTurnField.setText("Turno");

        chTurnCfField.setText("Codice Fiscale");

        chRoomButton.setText("Modifica");
        chRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chRoomButtonActionPerformed(evt);
            }
        });

        chTurnButton.setText("Modifica");
        chTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chTurnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modifyPanelLayout = new javax.swing.GroupLayout(modifyPanel);
        modifyPanel.setLayout(modifyPanelLayout);
        modifyPanelLayout.setHorizontalGroup(
            modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(modifyPanelLayout.createSequentialGroup()
                        .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modifyPanelLayout.createSequentialGroup()
                                .addComponent(ticketPriceLabel)
                                .addGap(18, 18, 18)
                                .addComponent(ticketPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ticketPriceButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, modifyPanelLayout.createSequentialGroup()
                                .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(modifyPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, modifyPanelLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(43, 43, 43)))
                                .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(changeRoomField)
                                    .addComponent(changeTurnField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chRoomCfField)
                                    .addComponent(chTurnCfField))))
                        .addGap(18, 18, 18)
                        .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chRoomButton)
                            .addComponent(chTurnButton))))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        modifyPanelLayout.setVerticalGroup(
            modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyPanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel5)
                .addGap(29, 29, 29)
                .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(changeRoomField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chRoomCfField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chRoomButton))
                .addGap(18, 18, 18)
                .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(changeTurnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chTurnCfField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chTurnButton))
                .addGap(18, 18, 18)
                .addGroup(modifyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ticketPriceLabel)
                    .addComponent(ticketPriceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ticketPriceButton))
                .addContainerGap(247, Short.MAX_VALUE))
        );

        adminPane.addTab("Modifiche", modifyPanel);

        javax.swing.GroupLayout adminAreaLayout = new javax.swing.GroupLayout(adminArea);
        adminArea.setLayout(adminAreaLayout);
        adminAreaLayout.setHorizontalGroup(
            adminAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminPane)
        );
        adminAreaLayout.setVerticalGroup(
            adminAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(adminPane)
        );

        resTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        resTitle.setText("Prenota biglietto");

        resTitleField.setText("Titolo");

        resTurnField.setText("Turno");

        resButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        resButton.setText("Prenota");
        resButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resButtonActionPerformed(evt);
            }
        });

        resRoomField.setText("Sala");

        javax.swing.GroupLayout reservationPanelLayout = new javax.swing.GroupLayout(reservationPanel);
        reservationPanel.setLayout(reservationPanelLayout);
        reservationPanelLayout.setHorizontalGroup(
            reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationPanelLayout.createSequentialGroup()
                .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reservationPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(resButton))
                    .addGroup(reservationPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(resTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(resTitleField)
                            .addComponent(resTurnField)
                            .addComponent(resRoomField))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        reservationPanelLayout.setVerticalGroup(
            reservationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reservationPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(resTitle)
                .addGap(29, 29, 29)
                .addComponent(resTitleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resTurnField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(resRoomField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(resButton)
                .addContainerGap(219, Short.MAX_VALUE))
        );

        reservedTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Biglietto", "Film", "Posto", "Sala", "Data", "Orario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        reservedTable.setColumnSelectionAllowed(true);
        jScrollPane3.setViewportView(reservedTable);
        reservedTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout userAreaLayout = new javax.swing.GroupLayout(userArea);
        userArea.setLayout(userAreaLayout);
        userAreaLayout.setHorizontalGroup(
            userAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userAreaLayout.createSequentialGroup()
                .addComponent(reservationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE))
        );
        userAreaLayout.setVerticalGroup(
            userAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reservationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        filmsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Titolo", "Genere", "Data Uscita", "Posti rimanenti", "Sala", "Data proiezione", "Ora inizio", "Turno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        filmsTable.setColumnSelectionAllowed(true);
        filmsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(filmsTable);
        filmsTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout screeningsAreaLayout = new javax.swing.GroupLayout(screeningsArea);
        screeningsArea.setLayout(screeningsAreaLayout);
        screeningsAreaLayout.setHorizontalGroup(
            screeningsAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
        );
        screeningsAreaLayout.setVerticalGroup(
            screeningsAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );

        jScrollPane4.getAccessibleContext().setAccessibleName("");

        MainMenu.addTab("Proiezioni", screeningsArea);

        loginTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        loginTitle.setText("Inserire i dati utente");

        userData.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        userData.setText("E-Mail o CF");

        password.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        password.setText("Password");

        loginButton.setText("LOGIN");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        loginCheckBox.setText("Dipendente");

        javax.swing.GroupLayout loginScreenLayout = new javax.swing.GroupLayout(loginScreen);
        loginScreen.setLayout(loginScreenLayout);
        loginScreenLayout.setHorizontalGroup(
            loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginScreenLayout.createSequentialGroup()
                .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(loginScreenLayout.createSequentialGroup()
                        .addGap(306, 306, 306)
                        .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userData)
                            .addComponent(password))
                        .addGap(31, 31, 31)
                        .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(loginScreenLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(loginTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginScreenLayout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(loginScreenLayout.createSequentialGroup()
                        .addGap(418, 418, 418)
                        .addComponent(loginCheckBox)))
                .addContainerGap(337, Short.MAX_VALUE))
        );
        loginScreenLayout.setVerticalGroup(
            loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginScreenLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(loginTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userData, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(loginScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(loginCheckBox)
                .addGap(18, 18, 18)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout privateAreaLayout = new javax.swing.GroupLayout(privateArea);
        privateArea.setLayout(privateAreaLayout);
        privateAreaLayout.setHorizontalGroup(
            privateAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 969, Short.MAX_VALUE)
            .addGroup(privateAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(privateAreaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(loginScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        privateAreaLayout.setVerticalGroup(
            privateAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
            .addGroup(privateAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(privateAreaLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(loginScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        MainMenu.addTab("Login", privateArea);

        signInTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        signInTitle.setText("Inserire i dati richiesti");

        emailLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        emailLabel.setText("Email");

        nameLable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nameLable.setText("Nome");

        surnameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        surnameLabel.setText("Cognome");

        birthLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        birthLabel.setText("Data di nascita");

        cityLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cityLabel.setText("Citta'");

        streetLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        streetLabel.setText("Via");

        cvivcLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cvivcLabel.setText("Numero civico");

        internLable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        internLable.setText("interno (opzionale)");

        birthFieldD.setText("giorno");

        capLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        capLabel.setText("CAP");

        fatLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        fatLabel.setText("Estremi di fatturazione:");

        cardNameLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cardNameLabel.setText("Nome e cognome proprietario della carta");

        cardNumLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cardNumLabel.setText("Numero della carta");

        cardExpLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cardExpLabel.setText("Data di scadenza");

        cardExpFieldD.setText("giorno");

        cardCvcLable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cardCvcLable.setText("CVC");

        signButton.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        signButton.setText("Sottoscrivi");
        signButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signButtonActionPerformed(evt);
            }
        });

        pwSignInLable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pwSignInLable.setText("Password");

        birthFieldM.setText("mese");

        birthFieldY.setText("anno");

        cardExpFieldM.setText("mese");

        cardExpFieldY.setText("anno");

        cfLable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cfLable.setText("Codice fiscale");
        cfLable.setEnabled(false);

        cfField.setEnabled(false);

        workerSignButton.setText("Dipendente");
        workerSignButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workerSignButtonActionPerformed(evt);
            }
        });

        roleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        roleLabel.setText("Ruolo");
        roleLabel.setEnabled(false);

        roleSellButton.setText("vendite");
        roleSellButton.setEnabled(false);

        roleCleanButton.setText("pulizie");
        roleCleanButton.setEnabled(false);

        javax.swing.GroupLayout signInScreenLayout = new javax.swing.GroupLayout(signInScreen);
        signInScreen.setLayout(signInScreenLayout);
        signInScreenLayout.setHorizontalGroup(
            signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInScreenLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signInScreenLayout.createSequentialGroup()
                        .addComponent(birthLabel)
                        .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(signInTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(workerSignButton)
                                .addGap(255, 255, 255))
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(birthFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(birthFieldM, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(birthFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(cfLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cfField, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(roleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roleSellButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(roleCleanButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(signInScreenLayout.createSequentialGroup()
                        .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailLabel)
                            .addComponent(cityLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emailField)
                            .addComponent(cityField, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addComponent(nameLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addComponent(streetLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addressField)))
                        .addGap(18, 18, 18)
                        .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addComponent(pwSignInLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pwSignInField, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(signButton)
                                .addGap(12, 12, 12))
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(signInScreenLayout.createSequentialGroup()
                                        .addComponent(surnameLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(surnameField))
                                    .addGroup(signInScreenLayout.createSequentialGroup()
                                        .addComponent(cvivcLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(civicField, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(internLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(internField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE))))
                    .addGroup(signInScreenLayout.createSequentialGroup()
                        .addComponent(fatLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(signInScreenLayout.createSequentialGroup()
                        .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addComponent(cardNumLabel)
                                .addGap(18, 18, 18)
                                .addComponent(cardNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addComponent(cardNameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(cardNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addComponent(cardExpLabel)
                                .addGap(34, 34, 34)
                                .addComponent(cardExpFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardExpFieldM, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardExpFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addComponent(cardCvcLable)
                                .addGap(18, 18, 18)
                                .addComponent(cardCvcField, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(signInScreenLayout.createSequentialGroup()
                                .addComponent(capLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(capField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        signInScreenLayout.setVerticalGroup(
            signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInScreenLayout.createSequentialGroup()
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signInScreenLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(signInTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(workerSignButton))
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signInScreenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(birthLabel)
                            .addComponent(birthFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthFieldM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(birthFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cfLable)
                            .addComponent(cfField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roleLabel)
                            .addComponent(roleSellButton)
                            .addComponent(roleCleanButton))
                        .addGap(18, 18, 18)))
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(nameLable)
                    .addComponent(surnameLabel)
                    .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(surnameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(streetLabel)
                    .addComponent(cvivcLabel)
                    .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(civicField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(internField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(internLable))
                .addGap(35, 35, 35)
                .addComponent(fatLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(capLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(capField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNameLabel)
                    .addComponent(cardNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNumLabel)
                    .addComponent(cardNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardExpLabel)
                    .addComponent(cardExpFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardExpFieldM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardExpFieldY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cardCvcLable)
                        .addComponent(cardCvcField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(signInScreenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(signButton)
                        .addComponent(pwSignInLable)
                        .addComponent(pwSignInField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout registrationAreaLayout = new javax.swing.GroupLayout(registrationArea);
        registrationArea.setLayout(registrationAreaLayout);
        registrationAreaLayout.setHorizontalGroup(
            registrationAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(signInScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        registrationAreaLayout.setVerticalGroup(
            registrationAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(signInScreen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        MainMenu.addTab("Registrazione", registrationArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainMenu, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainMenu, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ticketPriceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ticketPriceButtonActionPerformed
        this.logic.setTicketPrice(Float.parseFloat(this.ticketPriceField.getText()));
        JOptionPane.showMessageDialog(this, "Prezzo del biglietto aggiornato");
    }//GEN-LAST:event_ticketPriceButtonActionPerformed

    private void insProButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insProButtonActionPerformed
        for (JTextField f : this.insProFields.values()) {
            if (f.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi");
                return;
            }
        }
        this.logic.insertScreening(Integer.parseInt(this.insProFields.get("turn").getText()),
            Integer.parseInt(this.insProFields.get("room").getText()),
            this.insProFields.get("film").getText());
        JOptionPane.showMessageDialog(this, "Proiezione inserita correttamente");
        for (JTextField f : this.insProFields.values()) {
            f.setText("");
        }
        this.loadScreenings();
        this.repaint();
    }//GEN-LAST:event_insProButtonActionPerformed

    private void insRomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insRomButtonActionPerformed
        for (JTextField f : this.insRoomFields.values()) {
            if (f.getText().isEmpty() && f.getName() != "cf") {
                JOptionPane.showMessageDialog(this, "Inserire tutti i campi");
                return;
            }
        }
        this.logic.insertRoom(Integer.parseInt(this.insRoomFields.get("seats").getText()),
            this.insRoomFields.get("cf").getText());
        JOptionPane.showMessageDialog(this, "Sala inserita correttamente");
        for (JTextField f : this.insRoomFields.values()) {
            f.setText("");
        }
        this.loadRooms();
        this.repaint();
    }//GEN-LAST:event_insRomButtonActionPerformed

    public void initLook() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BrandSnackField;
    private javax.swing.JTabbedPane MainMenu;
    private javax.swing.JTextField addressField;
    private javax.swing.JPanel adminArea;
    private javax.swing.JTabbedPane adminPane;
    private javax.swing.JPanel barPanel;
    private javax.swing.JTable barTable;
    private javax.swing.JTextField birthFieldD;
    private javax.swing.JTextField birthFieldM;
    private javax.swing.JTextField birthFieldY;
    private javax.swing.JLabel birthLabel;
    private javax.swing.JLabel brandSnackLable;
    private javax.swing.JTextField capField;
    private javax.swing.JLabel capLabel;
    private javax.swing.JTextField cardCvcField;
    private javax.swing.JLabel cardCvcLable;
    private javax.swing.JTextField cardExpFieldD;
    private javax.swing.JTextField cardExpFieldM;
    private javax.swing.JTextField cardExpFieldY;
    private javax.swing.JLabel cardExpLabel;
    private javax.swing.JTextField cardNameField;
    private javax.swing.JLabel cardNameLabel;
    private javax.swing.JTextField cardNumField;
    private javax.swing.JLabel cardNumLabel;
    private javax.swing.JTextField cfField;
    private javax.swing.JLabel cfLable;
    private javax.swing.JButton chRoomButton;
    private javax.swing.JTextField chRoomCfField;
    private javax.swing.JButton chTurnButton;
    private javax.swing.JTextField chTurnCfField;
    private javax.swing.JTextField changeRoomField;
    private javax.swing.JTextField changeTurnField;
    private javax.swing.JTextField cityField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JTextField civicField;
    private javax.swing.JPanel cleanPanel;
    private javax.swing.JTable cleanTable;
    private javax.swing.JLabel cvivcLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel fatLabel;
    private javax.swing.JTextField filmSellField;
    private javax.swing.JLabel filmSellLable;
    private javax.swing.JTable filmsAdminTable;
    private javax.swing.JPanel filmsPanel;
    private javax.swing.JTable filmsTable;
    private javax.swing.JButton insFiButton;
    private javax.swing.JTextField insFiGenreField;
    private javax.swing.JLabel insFiLabel;
    private javax.swing.JTextField insFiReleaseFieldD;
    private javax.swing.JTextField insFiReleaseFieldM;
    private javax.swing.JTextField insFiReleaseFieldY;
    private javax.swing.JTextField insFiTitleField;
    private javax.swing.JButton insProButton;
    private javax.swing.JLabel insProLabel;
    private javax.swing.JTextField insProRomField;
    private javax.swing.JTextField insProTitleField;
    private javax.swing.JTextField insProTurField;
    private javax.swing.JButton insRomButton;
    private javax.swing.JTextField insRomCfField;
    private javax.swing.JLabel insRomLabel;
    private javax.swing.JTextField insRomSeatsField;
    private javax.swing.JTextField insTurCfField;
    private javax.swing.JTextField insTurDateFieldD;
    private javax.swing.JTextField insTurDateFieldM;
    private javax.swing.JTextField insTurDateFieldY;
    private javax.swing.JLabel insTurLabel;
    private javax.swing.JTextField insTurTimeEndField;
    private javax.swing.JTextField insTurTimeStartField;
    private javax.swing.JButton insTurnButton;
    private javax.swing.JPanel insertionPanel;
    private javax.swing.JTextField internField;
    private javax.swing.JLabel internLable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JButton loginButton;
    private javax.swing.JCheckBox loginCheckBox;
    private javax.swing.JPanel loginScreen;
    private javax.swing.JLabel loginTitle;
    private javax.swing.JPanel modifyPanel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLable;
    private javax.swing.JLabel password;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JPanel privateArea;
    private javax.swing.JTextField pwSignInField;
    private javax.swing.JLabel pwSignInLable;
    private javax.swing.JPanel registrationArea;
    private javax.swing.JButton resButton;
    private javax.swing.JTextField resRoomField;
    private javax.swing.JLabel resTitle;
    private javax.swing.JTextField resTitleField;
    private javax.swing.JTextField resTurnField;
    private javax.swing.JPanel reservationPanel;
    private javax.swing.JTable reservedTable;
    private javax.swing.ButtonGroup roleButtonGroup;
    private javax.swing.JRadioButton roleCleanButton;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JRadioButton roleSellButton;
    private javax.swing.JPanel roomPanel;
    private javax.swing.JTextField roomSellField;
    private javax.swing.JLabel roomSellLable;
    private javax.swing.JTable roomsTable;
    private javax.swing.JPanel screeningsArea;
    private javax.swing.JPanel sellPanel;
    private javax.swing.JTextField sellPriceField;
    private javax.swing.JLabel sellPriceLable;
    private javax.swing.JButton sellSnackButton;
    private javax.swing.JButton sellTickButton;
    private javax.swing.JTextField sellTurnField;
    private javax.swing.JLabel sellTurnLable;
    private javax.swing.JButton signButton;
    private javax.swing.JPanel signInScreen;
    private javax.swing.JLabel signInTitle;
    private javax.swing.JTextField snackPriceField;
    private javax.swing.JLabel snackPriceLable;
    private javax.swing.JLabel snackSellLable;
    private javax.swing.JTextField snackTypeField;
    private javax.swing.JLabel snackTypeLable;
    private javax.swing.JLabel streetLabel;
    private javax.swing.JTextField surnameField;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JPanel ticketPanel;
    private javax.swing.JButton ticketPriceButton;
    private javax.swing.JTextField ticketPriceField;
    private javax.swing.JLabel ticketPriceLabel;
    private javax.swing.JLabel ticketSellLable;
    private javax.swing.JTable ticketsTable;
    private javax.swing.JPanel turnsPane;
    private javax.swing.JPanel turnsPanel;
    private javax.swing.JTable turnsTable;
    private javax.swing.JPanel userArea;
    private javax.swing.JLabel userData;
    private javax.swing.JPanel userPanel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JTable usersTable;
    private javax.swing.JPanel workerArea;
    private javax.swing.JTabbedPane workerPane;
    private javax.swing.JPanel workerPanel;
    private javax.swing.JCheckBox workerSignButton;
    private javax.swing.JTable workerTurnsTable;
    private javax.swing.JTable workersTable;
    // End of variables declaration//GEN-END:variables
}
