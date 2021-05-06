
public class Osoba extends Wpis {

    private String name;
    private String lastName;
    private Adress address;
    private NrTelefoniczny phoneNumber;

    public Osoba(String name, String lastName, String country, String street, String city, int postal_code, int kierunkowy, int nrTelefonu) {
        this.name = name;
        this.lastName = lastName;
        this.address = new Adress(country, street, city, postal_code);
        phoneNumber = new NrTelefoniczny(kierunkowy, nrTelefonu);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Adress getAddress() {
        return address;
    }

    public void setAddress(Adress address) {
        this.address = address;
    }

    public NrTelefoniczny getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(NrTelefoniczny phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    String opis() {
        return phoneNumber.toString() + " " + name + " " + lastName + " " + address;
    }
}
