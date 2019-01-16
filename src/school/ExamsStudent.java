package school;

public class ExamsStudent {
    private long id;
    private int grade;
    private ExamData exam;

    public ExamData getExam() {
        return exam;
    }

    public void setExam(ExamData exam) {
        this.exam = exam;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}