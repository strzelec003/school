package school;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

class DBHelper {

    static long insertDataToStudents(String firstName, String lastName) {
        Connection connection = SchoolConnection.connect();
        PreparedStatement query;
        long id = 0;
        try {
            query = connection.prepareStatement(
                    "insert into student (FIRST_NAME, LAST_NAME) values(?, ?)");
            query.setString(1, firstName);
            query.setString(2, lastName);
            id = query.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    static List<StudentsData> getStudentsList() {
        Connection connection = SchoolConnection.connect();
        List<StudentsData> studentsList = new ArrayList<>();
        Statement st;
        try {
            st = connection.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM student;");
            while (rs.next()) {
                StudentsData studentsData = new StudentsData();
                studentsData.setId(rs.getLong("ID"));
                studentsData.setFirstName(rs.getString("FIRST_NAME"));
                studentsData.setLastName(rs.getString("LAST_NAME"));
                studentsList.add(studentsData);
            }
            st.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    static long insertDataToExam(String subject, String desc) {
        Connection connection = SchoolConnection.connect();
        PreparedStatement query;
        long id = 0;
        try {
            query = connection.prepareStatement(
                    "insert into exam (SUBJECT_NAME, SUBJECT_DESCRIPTION) values(?, ?)");
            query.setString(1, subject);
            query.setString(2, desc);
            id = query.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    static List<ExamData> getExamList() {
        Connection connection = SchoolConnection.connect();
        List<ExamData> examList = new ArrayList<>();
        Statement st;
        try {
            st = connection.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM exam;");
            while (rs.next()) {
                ExamData exam = new ExamData();
                exam.setId(rs.getLong("ID"));
                exam.setSubject(rs.getString("SUBJECT_NAME"));
                exam.setDescription(rs.getString("SUBJECT_DESCRIPTION"));
                examList.add(exam);
            }
            st.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examList;
    }

    static long insertExamToStudent(long studentId, long examId, int grade) throws SQLException {
        Connection connection = SchoolConnection.connect();
        PreparedStatement query;
        long id;
        query = connection.prepareStatement(
                "insert into marks (GRADE, STUDENT_FK, EXAM_FK) values(?, ?, ?)");
        query.setInt(1, grade);
        query.setLong(2, studentId);
        query.setLong(3, examId);
        id = query.executeUpdate();
        
        return id;
    }

    static List<ExamsStudent> getExamsStudentList(long studentId) {
        Connection connection = SchoolConnection.connect();
        List<ExamsStudent> examList = new ArrayList<>();
        Statement st;
        try {
            st = connection.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT e.subject_name, e.subject_description, m.grade as grade, m.id " +
                            "FROM exam e join marks m on e.ID = m.EXAM_FK WHERE m.student_fk = "  + studentId + ";");
            while (rs.next()) {
                ExamsStudent examStud = new ExamsStudent();
                ExamData exam = new ExamData();
                exam.setSubject(rs.getString("SUBJECT_NAME"));
                exam.setDescription(rs.getString("SUBJECT_DESCRIPTION"));
                examStud.setExam(exam);
                examStud.setGrade(rs.getInt("grade"));
                examStud.setId(rs.getLong("ID"));
                examList.add(examStud);
            }
            st.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examList;
    }

    static List<StudentsExam> getStudentExamList(long examId) {
        Connection connection = SchoolConnection.connect();
        List<StudentsExam> examList = new ArrayList<>();
        Statement st;
        try {
            st = connection.createStatement();

            ResultSet rs = st.executeQuery(
                    "SELECT s.first_name, s.last_name, m.grade as grade, m.id FROM student s join marks m on s.ID = m.STUDENT_FK\n" +
                            "WHERE m.EXAM_FK = " + examId + ";");
            while (rs.next()) {
                StudentsExam studentsExam = new StudentsExam();
                StudentsData student = new StudentsData();
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                studentsExam.setStudent(student);
                studentsExam.setGrade(rs.getInt("grade"));
                studentsExam.setId(rs.getLong("ID"));
                examList.add(studentsExam);
            }
            st.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return examList;
    }
}