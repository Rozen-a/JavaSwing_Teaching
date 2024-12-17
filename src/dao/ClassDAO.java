package dao;

import Entity.Class;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据访问对象 (DAO) 类，用于操作 Classes 表
 *
 * Classes 表列名:
 * Classid
 * classname
 */
public class ClassDAO {

    /**
     * 获取所有班级信息
     * @return 包含所有班级信息的列表
     * @throws Exception
     */
    public List<Class> getAllClasses() throws Exception {
        // 创建用于存储班级的列表
        List<Class> classes = new ArrayList<>();
        // SQL 查询获取 Classes 表中的所有记录
        String sql = "SELECT * FROM Classes";

        // 使用 DBUtil 提供的 executeQuery 方法
        try (ResultSet rs = DBUtil.executeQuery(sql)) {
            // 遍历结果集，将每条记录映射为 Class 对象
            while (rs.next()) {
                Class class_ = new Class();
                class_.setClassId(rs.getString("Classid"));
                class_.setClassName(rs.getString("className"));
                classes.add(class_);
            }
        }

        return classes;
    }

    /**
     * 获取班级信息
     * @param classId 要获取的班级信息
     * @return 获取的班级信息
     * @throws Exception
     */
    public Class getClass(String classId) throws Exception {
        Class class_ = null;
        // SQL 查询班级信息
        String sql = "SELECT * FROM Classes WHERE Classid = ?";

        // 使用 DBUtil 提供的 executeQuery 方法
        try (ResultSet rs = DBUtil.executeQuery(sql, classId)) {
            if (rs.next()) { // 只会有一个结果
                class_ = new Class();
                class_.setClassId(rs.getString("Classid"));
                class_.setClassName(rs.getString("className"));
            }
        }

        return class_;
    }
}
