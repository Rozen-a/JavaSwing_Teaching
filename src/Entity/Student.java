package Entity;

/**
 *  实体类: Student 学生
 */
public class Student {
    private String studentId;   //学生ID
    private String classId;     //班级ID
    private String studentName; //姓名
    private boolean gender;     //性别(1：男; 0：女)
    private boolean graduated;  //是否毕业（1：毕业; 2：未毕业）

    public Student() {
    }

    public Student(String studentId, String classId, String studentName, boolean gender, boolean graduated) {
        this.studentId = studentId;
        this.classId = classId;
        this.studentName = studentName;
        this.gender = gender;
        this.graduated = graduated;
    }

    //Getter&Setter

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isGraduated() {
        return graduated;
    }

    public void setGraduated(boolean graduated) {
        this.graduated = graduated;
    }
}
