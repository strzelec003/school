package school;

public class StudentsExam {
    private long id;
    private int grade;
    private StudentsData student;

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public StudentsData getStudent() {
        return student;
    }

    public void setStudent(StudentsData student) {
        this.student = student;
    }
}