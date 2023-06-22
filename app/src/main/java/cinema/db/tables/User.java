package cinema.db.tables;

import java.util.Calendar;
import java.util.Date;

public class User {
    private final String Email;
    private final Date birth;
    private final String showBirth;
    private final int CAP;
    private final String cardName;
    private final long cardNumber;
    private final Date cardExpDate;
    private final String showCardExpDate;
    private final int cardCVC;
    private final String Name;
    private final String Surname;
    private final String password;
    private final String city;
    private final String street;
    private final int houseNumber;
    private final int intern;

    public User(final String Email, final Date birth, final int CAP, final String cardName, final long cardNumber, final Date cardExpDate, final int cardCVC, final String Name, final String Surname, final String password, final String city, final String street, final int houseNumber, final int intern) {
        this.Email = Email;
        this.birth = birth;
        final Calendar cal = Calendar.getInstance();
        cal.setTime(birth);
        this.showBirth = "" + cal.get(Calendar.DAY_OF_MONTH) +"/"+ cal.get(Calendar.MONTH) +"/"+ cal.get(Calendar.YEAR);
        this.CAP = CAP;
        this.cardName = cardName;
        this.cardNumber = cardNumber;
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(cardExpDate);
        this.showCardExpDate = "" + cal2.get(Calendar.DAY_OF_MONTH) +"/"+ cal2.get(Calendar.MONTH) +"/"+ cal2.get(Calendar.YEAR);
        this.cardExpDate = cardExpDate;
        this.cardCVC = cardCVC;
        this.Name = Name;
        this.Surname = Surname;
        this.password = password;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.intern = intern;
    }

    public String getEmail() {
        return this.Email;
    }

    public Date getBirth() {
        return this.birth;
    }

    public String getShowBirth() {
        return this.showBirth;
    }

    public int getCAP() {
        return this.CAP;
    }

    public String getCardName() {
        return this.cardName;
    }

    public long getCardNumber() {
        return this.cardNumber;
    }

    public Date getCardExpDate() {
        return this.cardExpDate;
    }

    public String getShowCardExpDate() {
        return this.showCardExpDate;
    }

    public int getCardCVC() {
        return this.cardCVC;
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
