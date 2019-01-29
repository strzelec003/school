package school;

class ExamData {
    private long id;
    private String subject;
    private String description;

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    String getSubject() {
        return subject;
    }

    void setSubject(String subject) {
        this.subject = subject;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }
}
