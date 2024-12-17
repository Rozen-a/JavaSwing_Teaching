package dao;

import Entity.Subject;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 * 数据访问对象 (DAO) 类，用于操作 Subjects 表
 *
 * Subjects 表列名：
 * subjectid
 * subjectname
 */
public class SubjectDAO {

    /**
     * 获取课程信息
     * @param subjectId 获取课程ID
     * @return 获取课程对象
     * @throws Exception
     */
    public Subject getSubject(String subjectId) throws Exception {
        Subject subject = new Subject();

        // 查询 subjectid = subjectId 的 Subjects 表记录
        String sql = "SELECT * FROM Subjects WHERE subjectid = ?";

        // 使用 DBUtil 执行查询操作
        try (ResultSet rs = DBUtil.executeQuery(sql, subjectId)) {
            if (rs.next()) { // 确保有查询结果
                subject.setSubjectId(rs.getString("subjectid"));
                subject.setSubjectName(rs.getString("subjectname"));
            }
        }

        return subject;
    }
}
