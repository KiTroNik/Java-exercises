
public class Firma extends Wpis {

    private String name;
    private Adress address;
    private NrTelefoniczny phoneNumber;

    public Firma(String name, String country, String street, String city, int postal_code, int kierunkowy, int nrTelefonu) {
        this.name = name;
        this.address = new Adress(country, street, city, postal_code);
        this.phoneNumber = new NrTelefoniczny(kierunkowy, nrTelefonu);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return phoneNumber.toString() + " " + name + " " + address;
    }
}
