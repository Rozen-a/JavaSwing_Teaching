package Entity;

/**
 * 实体类: Subject 课程
 */
public class Subject {
    private String subjectId;   //课程ID
    private String subjectName; //课程名

    //Getter&Setter

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
