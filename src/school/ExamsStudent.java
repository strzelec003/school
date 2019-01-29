package school;

class ExamsStudent {
    private long id;
    private int grade;
    private ExamData exam;

    ExamData getExam() {
        return exam;
    }

    void setExam(ExamData exam) {
        this.exam = exam;
    }

    int getGrade() {
        return grade;
    }

    void setGrade(int grade) {
        this.grade = grade;
    }

    void setId(long id) {
        this.id = id;
    }
}