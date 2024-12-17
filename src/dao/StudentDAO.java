package dao;

import Entity.Student; // 导入Student模型类，用于封装学生信息
import util.DBUtil; // 导入数据库工具类，用于获取数据库连接

import java.sql.*; // 导入与数据库操作相关的类
import java.util.ArrayList; // 导入ArrayList类
import java.util.List; // 导入List接口

/**
 * 数据访问对象 (DAO) 类，用于操作 Students 表
 *
 * Students 表列名:
 * Studentid
 * classid
 * studentname
 * gender
 * graduated
 */
public class StudentDAO {

    /**
     * 获取所有学生的信息
     * @return 包含所有学生信息的列表
     * @throws Exception 如果数据库操作出现问题
     */
    public List<Student> getAllStudents() throws Exception {
        // 创建用于存储学生的列表
        List<Student> students = new ArrayList<>();
        // SQL 查询语句，获取 Students 表中的所有记录
        String sql = "SELECT * FROM Students";

        // 使用 DBUtil 的 executeQuery 方法
        try (ResultSet rs = DBUtil.executeQuery(sql)) { // 执行查询
            // 遍历结果集，将每条记录映射为 Student 对象
            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("Studentid")); // 设置学生ID
                student.setClassId(rs.getString("classid")); // 设置班级ID
                student.setStudentName(rs.getString("studentname")); // 设置学生姓名
                student.setGender(rs.getBoolean("gender")); // 设置性别
                student.setGraduated(rs.getBoolean("graduated")); // 设置是否毕业
                students.add(student); // 将学生对象添加到列表中
            }
        }

        // 返回学生列表
        return students;
    }

    /**
     * 获取学生信息
     * @param studentId 要获取的学生ID
     * @return 获取的学生信息
     * @throws Exception 如果数据库操作出现问题
     */
    public Student getStudent(String studentId) throws Exception {
        Student student = new Student();
        String sql = "SELECT * FROM Students WHERE Studentid = ?";

        // 使用 DBUtil 的 executeQuery 方法，传入参数 studentId
        try (ResultSet rs = DBUtil.executeQuery(sql, studentId)) {    // 执行 SQL 查询并获取结果集
            if (rs.next()) {
                student.setStudentId(rs.getString("Studentid")); // 设置学生ID
                student.setClassId(rs.getString("classid")); // 设置班级ID
                student.setStudentName(rs.getString("studentname")); // 设置学生姓名
                student.setGender(rs.getBoolean("gender")); // 设置性别
                student.setGraduated(rs.getBoolean("graduated")); // 设置是否毕业
            }
        }

        return student;
    }

    /**
     * 获取最大学生ID
     * @return 最大学生ID
     * @throws Exception 如果数据库操作出现问题
     */
    public String getStudentIDMax() throws Exception {
        long IDMax = -1;  // 使用 long 类型来避免超出 int 范围
        String sql = "SELECT * FROM Students";

        // 使用 DBUtil 的 executeQuery 方法
        try (ResultSet rs = DBUtil.executeQuery(sql)) {   // 执行查询
            // 遍历结果集，获取最大ID
            while (rs.next()) {
                long id = Long.parseLong(rs.getString("Studentid"));
                if (id > IDMax) {
                    IDMax = id;
                }
            }
        }

        return Long.toString(IDMax);
    }

    /**
     * 添加学生信息到数据库
     * @param student 要添加的学生对象
     * @throws Exception 如果数据库操作出现问题
     */
    public void addStudent(Student student) throws Exception {
        // SQL 插入语句
        String sql = "INSERT INTO Students(Studentid, classid, studentname, gender, graduated) VALUES (?, ?, ?, ?, ?)";

        // 使用 DBUtil 的 executeUpdate 方法执行插入操作
        DBUtil.executeUpdate(sql, student.getStudentId(), student.getClassId(), student.getStudentName(), student.isGender(), student.isGraduated());
    }

    /**
     * 更新学生信息
     * @param student 要更新的学生对象
     * @throws Exception 如果数据库操作出现问题
     */
    public void updateStudent(Student student) throws Exception {
        // SQL 更新语句
        String sql = "UPDATE Students SET classid = ?, studentname = ?, gender = ?, graduated = ? WHERE Studentid = ?";

        // 使用 DBUtil 的 executeUpdate 方法执行更新操作
        DBUtil.executeUpdate(sql, student.getClassId(), student.getStudentName(), student.isGender(), student.isGraduated(), student.getStudentId());
    }

    /**
     * 删除学生信息
     * @param studentId 要删除的学生ID
     * @throws Exception 如果数据库操作出现问题
     */
    public void deleteStudent(String studentId) throws Exception {
        // SQL 删除语句
        String sql = "DELETE FROM Students WHERE Studentid = ?";

        // 使用 DBUtil 的 executeUpdate 方法执行删除操作
        DBUtil.executeUpdate(sql, studentId);
    }
}
