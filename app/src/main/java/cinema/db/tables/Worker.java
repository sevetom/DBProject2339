package cinema.db.tables;

public class Worker {
    private final String CF;
    private final String role;
    private final String Name;
    private final String Surname;
    private final String password;
    private final String city;
    private final String street;
    private final int houseNumber;
    private final int intern;

    public Worker(final String CF, final String role, final String Name, final String Surname, final String password, final String city, final String street, final int houseNumber, final int intern) {
        this.CF = CF;
        this.role = role;
        this.Name = Name;
        this.Surname = Surname;
        this.password = password;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.intern = intern;
    }

    public String getCF() {
        return this.CF;
    }

    public String getRole() {
        return this.role;
    }

    public String getName() {
        return this.Name;
    }

    public String getSurname() {
        return this.Surname;
    }

    public String getPassword() {
        return this.password;
    }

    public String getCity() {
        return this.city;
    }

    public String getStreet() {
        return this.street;
    }

    public int getHouseNumber() {
        return this.houseNumber;
    }

    public int getIntern() {
        return this.intern;
    }
}
