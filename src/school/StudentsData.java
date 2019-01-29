package school;

public class StudentsData {
    private long id;
    private String firstName;
    private String lastName;

    StudentsData() {
    }

    public StudentsData(long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getFirstName() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }
}
