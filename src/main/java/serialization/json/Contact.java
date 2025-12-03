package serialization.json;


import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class Contact {

    private String phone;

    public Contact() {

    }

    public Contact(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "phone='" + phone + '\''
                + '}';
    }
}
