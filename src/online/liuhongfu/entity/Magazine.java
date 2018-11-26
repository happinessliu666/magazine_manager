package online.liuhongfu.entity;

public class Magazine {
    private int id;
    private String issn;
    private String name;
    private String location;
    private String postalCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Magazine() {
        super();
    }

    public Magazine(String issn) {
        this.issn = issn;
    }

    public Magazine(String issn, String name, String location, String postalCode) {
        this.issn = issn;
        this.name = name;
        this.location = location;
        this.postalCode = postalCode;
    }

    public Magazine(int id, String issn, String name, String location, String postalCode) {
        this.id = id;
        this.issn = issn;
        this.name = name;
        this.location = location;
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + id +
                ", issn='" + issn + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
