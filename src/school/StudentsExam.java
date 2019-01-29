package school;

class StudentsExam {
    private long id;
    private int grade;
    private StudentsData student;

    void setId(long id) {
        this.id = id;
    }

    int getGrade() {
        return grade;
    }

    void setGrade(int grade) {
        this.grade = grade;
    }

    StudentsData getStudent() {
        return student;
    }

    void setStudent(StudentsData student) {
        this.student = student;
    }
}