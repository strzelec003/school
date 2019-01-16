package school;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener {

    private DefaultListModel studentsModel;
    private DefaultListModel studentsExamModel;
    private DefaultListModel examsStudentModel;
    private DefaultListModel examModel;

    private JList studentsExamList;
    private JList examsStudentList;
    private JList examList;
    private JList studentsList;

    private String inputFName, inputLName, inputSubject, inputDesc;

    private JButton addButton, addStudent, addExam;
    private JTextField grade, firstName, lastName, subject, desc;


    public GUI() {

        super();
        setTitle("School");
        setLocation(50, 50);

        pack();
        add(UpperPanel(), BorderLayout.NORTH);
        add(LowerPanel(), BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

    }

    public JPanel Students() {

        JPanel students = new JPanel();
        students.setPreferredSize(new Dimension(200, 250));
        students.setMinimumSize(new Dimension(200, 250));
        students.setMaximumSize(new Dimension(200, 250));
        students.setBorder(BorderFactory.createTitledBorder("Students"));
        students.setLayout(new GridLayout(1, 1));

        studentsModel = new DefaultListModel<>();
        studentsList = new JList(studentsModel);

        java.util.List<StudentsData> studentsDataList = DBHelper.getStudentsList();
        students.add(studentsList);

        JScrollPane sp = new JScrollPane();
        sp.setViewportView(studentsList);

        students.add(sp);
        sp.doLayout();

        for (StudentsData item : studentsDataList) {
            studentsModel.addElement(item.getId() + ". " + item.getFirstName() + " " + item.getLastName());
        }

        MouseListener eventOnStudentsList = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selectedItem = (String) studentsList.getSelectedValue();
                Long id = Long.parseLong(selectedItem.substring(0, selectedItem.indexOf('.')));

                loadStudentsExamData(id);

                checkEnabledAddButton();
            }
        };
        studentsList.addMouseListener(eventOnStudentsList);
        return students;
    }

    public void checkEnabledAddButton() {
        if (studentsList.getSelectedValue() != null && examList.getSelectedValue() != null) {
            addButton.setEnabled(true);
        } else {
            addButton.setEnabled(false);
        }
    }

    public void addGradeToUser() {
        int gradeInt = 0;
        try {
            gradeInt = Integer.parseInt(grade.getText());
            String studentString = (String) studentsList.getSelectedValue();
            Long studentId = Long.parseLong(studentString.substring(0, studentString.indexOf('.')));

            String examString = (String) examList.getSelectedValue();
            Long examId = Long.parseLong(examString.substring(0, examString.indexOf('.')));

            DBHelper.insertExamToStudent(studentId, examId, gradeInt);

            loadExamsStudentData(examId);
            loadStudentsExamData(studentId);
            grade.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Insert number");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Student already passed this exam");
        }
    }

    public JPanel Edition() {
        JPanel edition = new JPanel();
        edition.setLayout(new GridLayout(8, 1));
        edition.setBorder(BorderFactory.createTitledBorder("Edition"));

        grade = new JTextField();
        grade.setText("Grade");

        addButton = new JButton();
        addButton.setEnabled(false);
        addButton.setText("Add Grade");

        firstName = new JTextField();
        firstName.setText("First name");

        lastName = new JTextField();
        lastName.setText("Last name");

        addStudent = new JButton();
        addStudent.setText("Add Student");

        subject = new JTextField();
        subject.setText("Subject");

        desc = new JTextField();
        desc.setText("Description");

        addExam = new JButton();
        addExam.setText("Add exam");

        addButton.addActionListener(this);
        addButton.setActionCommand("add");

        addStudent.addActionListener(this);
        addStudent.setActionCommand("addS");

        addExam.addActionListener(this);
        addExam.setActionCommand("addE");

        edition.add(grade);
        edition.add(addButton);
        edition.add(firstName);
        edition.add(lastName);
        edition.add(addStudent);
        edition.add(subject);
        edition.add(desc);
        edition.add(addExam);


        return edition;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "add": {
                addGradeToUser();
                break;
            }
            case "addS": {
                inputFName = firstName.getText();
                inputLName = lastName.getText();
                DBHelper.insertDataToStudents(inputFName, inputLName);
                loadStudentData();
                break;
            }
            case "addE": {
                inputSubject = subject.getText();
                inputDesc = desc.getText();
                DBHelper.insertDataToExam(inputSubject, inputDesc);
                loadExamData();
                break;
            }
        }
    }

    public JPanel Exam() {
        JPanel exam = new JPanel();
        exam.setPreferredSize(new Dimension(200, 250));
        exam.setMinimumSize(new Dimension(200, 250));
        exam.setMaximumSize(new Dimension(200, 250));
        exam.setBorder(BorderFactory.createTitledBorder("Exam"));
        exam.setLayout(new GridLayout(1, 1));

        examModel = new DefaultListModel<>();
        examList = new JList(examModel);
        java.util.List<ExamData> examDataList = DBHelper.getExamList();
        exam.add(examList);

        JScrollPane sp = new JScrollPane();
        sp.setViewportView(examList);

        exam.add(sp);
        sp.doLayout();

        for (ExamData item : examDataList) {
            examModel.addElement(item.getId() + ". " + item.getSubject() + " (" + item.getDescription() + ")");
        }

        MouseListener eventOnExamList = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String selectedItem = (String) examList.getSelectedValue();
                Long id = Long.parseLong(selectedItem.substring(0, selectedItem.indexOf('.')));

                loadExamsStudentData(id);
                checkEnabledAddButton();
            }
        };
        examList.addMouseListener(eventOnExamList);

        return exam;
    }

    public JPanel UpperPanel() {

        JPanel upper = new JPanel();
        upper.setLayout(new BoxLayout(upper, BoxLayout.X_AXIS));
        upper.add(Students());
        upper.add(Edition());
        upper.add(Exam());

        return upper;
    }

    public JPanel StudentsExam() {
        JPanel studentsExam = new JPanel();
        studentsExam.setPreferredSize(new Dimension(270, 150));
        studentsExam.setMinimumSize(new Dimension(270, 150));
        studentsExam.setMaximumSize(new Dimension(270, 150));
        studentsExam.setBorder(BorderFactory.createTitledBorder("Student's Exam"));
        studentsExam.setLayout(new GridLayout(1, 1));

        studentsExamModel = new DefaultListModel<>();
        studentsExamList = new JList(studentsExamModel);
        studentsExam.add(studentsExamList);

        JScrollPane sp = new JScrollPane();
        sp.setViewportView(studentsExamList);

        studentsExam.add(sp);
        sp.doLayout();


        return studentsExam;
    }

    public JPanel ExamsStudent() {
        JPanel examsStudent = new JPanel();
        examsStudent.setPreferredSize(new Dimension(270, 150));
        examsStudent.setMinimumSize(new Dimension(270, 150));
        examsStudent.setMaximumSize(new Dimension(270, 150));
        examsStudent.setBorder(BorderFactory.createTitledBorder("Exam's Student"));
        examsStudent.setLayout(new GridLayout(1, 1));


        examsStudentModel = new DefaultListModel<>();
        examsStudentList = new JList(examsStudentModel);
        java.util.List<StudentsData> studentsDataList = DBHelper.getStudentsList();
        examsStudent.add(examsStudentList);

        JScrollPane sp = new JScrollPane();
        sp.setViewportView(examsStudentList);

        examsStudent.add(sp);
        sp.doLayout();

        return examsStudent;
    }

    public JPanel LowerPanel() {

        JPanel lower = new JPanel();
        lower.setLayout(new BoxLayout(lower, BoxLayout.X_AXIS));
        lower.add(StudentsExam());
        lower.add(ExamsStudent());
        return lower;

    }

    public void loadStudentData() {
        studentsModel.removeAllElements();
        List<StudentsData> studentsData = DBHelper.getStudentsList();
        for (StudentsData item : studentsData) {
            studentsModel.addElement(item.getId()+". "+item.getFirstName()+" "+item.getLastName());
        }
    }

    public void loadExamData() {
        examModel.removeAllElements();
        List<ExamData> examData = DBHelper.getExamList();
        for (ExamData item : examData) {
            examModel.addElement(item.getId()+". "+item.getSubject()+" "+ item.getDescription());
        }
    }

    public void loadExamsStudentData(long id) {
        examsStudentModel.removeAllElements();
        List<StudentsExam> studentsExams = DBHelper.getStudentExamList(id);
        for (StudentsExam studentsExam : studentsExams) {
            examsStudentModel.addElement(studentsExam.getStudent().getFirstName() + " "
                    + studentsExam.getStudent().getLastName() + " ocena: " + studentsExam.getGrade());
        }
    }

    public void loadStudentsExamData(long id) {
        studentsExamModel.removeAllElements();
        List<ExamsStudent> examsStudentList = DBHelper.getExamsStudentList(id);
        for (ExamsStudent examsStudent : examsStudentList) {
            studentsExamModel.addElement(examsStudent.getExam().getSubject() + " ocena: " + examsStudent.getGrade());
        }
    }
}