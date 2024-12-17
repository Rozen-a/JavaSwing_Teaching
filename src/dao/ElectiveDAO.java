package dao;

import Entity.Elective;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问对象 (DAO) 类，用于操作 Elective 表
 *
 * Elective 表列名:
 * Studentid
 * subjectid
 * grade
 */
public class ElectiveDAO {

    /**
     * 获取选课信息
     * @param studentId 要获取的学生ID
     * @return 包含对应学生的所有选课信息列表
     * @throws Exception
     */
    public List<Elective> getElectives(String studentId) throws Exception {

        // 选课信息列表
        List<Elective> electives = new ArrayList<>();

        // 查询对应 Studentid 的 Elective 表记录
        String sql = "SELECT * FROM Elective WHERE Studentid = ?";

        try (ResultSet rs = DBUtil.executeQuery(sql, studentId)) {
            // 遍历结果集，将每条记录映射为 Elective 对象
            while (rs.next()) {
                Elective elective = new Elective();
                // 设置 elective 对象属性
                elective.setStudentId(rs.getString("Studentid"));
                elective.setSubjectId(rs.getString("subjectid"));
                elective.setGrade(rs.getInt("grade"));
                // 将选课对象添加到列表中
                electives.add(elective);
            }
        } catch (Exception e) {
            throw new Exception("Error occurred while fetching elective data", e);
        }

        return electives;
    }
}
