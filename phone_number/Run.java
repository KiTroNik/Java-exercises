import javax.swing.text.html.HTMLDocument;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class Run {

    public static TreeMap<NrTelefoniczny, Wpis> createEntries(){
        Osoba firstPerson = new Osoba("Jakub", "Tomala", "Polska", "Zawadzka", "Tomaszow", 97-200, 48, 505963323);
        Osoba secondPerson = new Osoba("John", "Doe", "Polska", "Zawaddzka", "Tomaszow", 97-200, 48, 123456789);
        Osoba thirdPerson = new Osoba("Jan", "Kowalski", "Polska", "Zawadzzka", "Tomaszow", 97-200, 48, 503876423);
        Osoba duplicate = new Osoba("sdsdf", "df sf", "Polska", "Zawadzka", "Tomaszow", 97-200, 34, 12345567);
        Firma firstCompany = new Firma("Microsoft", "Polska", "Zawadzkka", "Tomaszow", 97-200, 48, 345825910);
        Firma secondCompany = new Firma("Apple", "Polska", "Zawadzkka", "Tomaszow", 97-200, 48, 246826789);
        Firma thirdCompany = new Firma("Samsung", "Polska", "Zaawadzka", "Tomaszow", 97-200, 48, 458234786);

        TreeMap<NrTelefoniczny, Wpis> result = new TreeMap<>();
        result.put(firstPerson.getPhoneNumber(), firstPerson);
        result.put(secondPerson.getPhoneNumber(), secondPerson);
        result.put(thirdPerson.getPhoneNumber(), thirdPerson);
        result.put(firstCompany.getPhoneNumber(), firstCompany);
        result.put(secondCompany.getPhoneNumber(), secondCompany);
        result.put(thirdCompany.getPhoneNumber(), thirdCompany);
        result.put(duplicate.getPhoneNumber(), duplicate);

        return result;
    }

    public static void deleteDuplicates(TreeMap<NrTelefoniczny, Wpis> map) {
        TreeMap<String, Wpis> antiDuplicat = new TreeMap<>();

        for(Map.Entry<NrTelefoniczny,Wpis> entry : map.entrySet()) {
            if (entry.getValue() instanceof Osoba) {
                antiDuplicat.put(((Osoba) entry.getValue()).getAddress().getStreet(), entry.getValue());
            } else {
                antiDuplicat.put(((Firma) entry.getValue()).getAddress().getStreet(), entry.getValue());
            }
        }

        map.clear();
        for(Map.Entry<String, Wpis> entry : antiDuplicat.entrySet()) {
            if (entry.getValue() instanceof Osoba) {
                map.put(((Osoba) entry.getValue()).getPhoneNumber(), entry.getValue());
            } else {
                map.put(((Firma) entry.getValue()).getPhoneNumber(), entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        TreeMap<NrTelefoniczny, Wpis> phoneBook = createEntries();

        Iterator<Map.Entry<NrTelefoniczny, Wpis>> iterator = phoneBook.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = iterator.next();
            Wpis w = (Wpis)entry.getValue();
            System.out.println(w.opis());
        }

        System.out.println("----------------------");
        deleteDuplicates(phoneBook);
        for(Map.Entry<NrTelefoniczny,Wpis> entry : phoneBook.entrySet()) {
            System.out.println(entry.getValue().opis());
        }
    }
}
