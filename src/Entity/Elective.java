package Entity;

/**
 * 实体类: Elective 选课
 */
public class Elective {
    private String studentId;   //学生ID
    private String subjectId;   //课程ID
    private int grade;          //成绩


    //Getter&Setter

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
