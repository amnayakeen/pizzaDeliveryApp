public abstract class Person {
    private String username;
    private String password;
    private int contactNumber;
    private String address;
    private String type;
    private static boolean isAuthenticated;

    public Person() {}

    public Person(String username, String password, int contactNumber, String address, String type){
        this.username = username;
        this.password = password;
        this.contactNumber = contactNumber;
        this.address = address;
        this.type = type;
    }

    public Person(String username) {
        this.username = username;
    }

    public abstract void login();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public static void setAuthenticated(boolean authenticated) {
        Person.isAuthenticated = authenticated;
    }
}
