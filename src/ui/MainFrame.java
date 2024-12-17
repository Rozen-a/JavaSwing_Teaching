package ui;

import Entity.Class;
import Entity.Student;
import Entity.Elective;
import dao.ClassDAO;
import dao.StudentDAO;
import dao.ElectiveDAO;
import dao.SubjectDAO;
import util.DBUtil;
import util.SqlFileReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * 主窗体类，提供学生成绩管理系统的用户界面
 */
public class MainFrame extends JFrame {

    // 定义顶部面板组件
    private JTextField studentIdField;          // 学号输入框
    private JTextField studentNameField;        // 姓名输入框
    private JComboBox<String> classComboBox;    // 班级下拉框
    private JRadioButton maleRadioButton;       // 男性单选按钮
    private JRadioButton femaleRadioButton;     // 女性单选按钮
    private JTextField avgGardeField;           // 平均成绩输入框
    private JButton insertButton;   // 插入按钮
    private JButton deleteButton;   // 删除按钮
    private JButton cancelButton;   // 取消按钮
    private JButton copyButton;     //复制按钮

    // 定义成绩表格滚动面板组件
    private JTable gradeTable; // 成绩表格

    // 定义浏览按钮面板组件
    private JButton firstButton;    // 浏览首行按钮
    private JButton prevButton;     // 浏览上一行按钮
    private JButton nextButton;     // 浏览下一行按钮
    private JButton lastButton;     // 浏览尾行按钮

    // 定义右侧面板组件
    private JButton cancelAllButton;            // 取消全部
    private JButton saveButton;                 // 保存按钮
    private JTextField studentIDSearchField;    // 学号搜索输入框
    private JButton searchButton;               // 搜索按钮

    // 数据访问对象
    private StudentDAO studentDAO;  // 操作学生信息
    private ClassDAO classDAO;      // 操作班级信息
    private ElectiveDAO electiveDAO;    // 操作选课信息
    private SubjectDAO subjectDAO;      // 操作课程信息

    /**
     * 构造方法，初始化窗体
     * [没有显式调用 super()，Java 会自动在子类构造方法中调用父类（JFrame）的无参构造方法]
     */
    public MainFrame() {
        setTitle("学籍和成绩"); // 设置窗口标题
        setLayout(new BorderLayout()); // 设置布局管理器为边界布局
        studentDAO = new StudentDAO(); // 创建 StudentDAO 对象
        classDAO = new ClassDAO();  //创建 ClassDAO 对象
        electiveDAO = new ElectiveDAO();    // 创建 ElectiveDAO 对象
        subjectDAO = new SubjectDAO();      // 创建 SubjectDAO 对象
        initComponents(); // 初始化界面组件
        loadData(); // 加载数据
    }

    /**
     * 初始化界面组件
     */
    private void initComponents() {
        // 创建面板并定义布局方式
        JPanel topPanel = new JPanel(new GridLayout(5, 3));     // 顶部面板(5 行 3 列的网格布局)
        JPanel genderPanel = new JPanel();  // 性别选择面板
        JPanel gradesPanel = new JPanel(new BorderLayout());  //成绩面板(边界布局)
        JScrollPane scrollPane = new JScrollPane(gradeTable);     // 滚动面板
        JPanel scanButtonPanel = new JPanel(new GridLayout(1,4));      // 浏览按钮面板
        JPanel leftPanel = new JPanel(new BorderLayout());  //左侧面板
        JPanel rightMinPanel = new JPanel(new GridLayout(5,1));    // 右侧小面板
        JPanel rightPanel = new JPanel();   //右侧面板

        // 创建顶部面板组件
        studentIdField = new JTextField();
        studentNameField = new JTextField();
        classComboBox = new JComboBox<>();
        maleRadioButton = new JRadioButton("男");
        femaleRadioButton = new JRadioButton("女");
        avgGardeField = new JTextField();
        insertButton = new JButton("插入");
        deleteButton = new JButton("删除");
        cancelButton = new JButton("取消");
        copyButton = new JButton("复制");

        // 创建成绩表格滚动面板组件
        gradeTable = new JTable();

        // 创建浏览按钮面板组件
        firstButton = new JButton("|<");
        prevButton = new JButton("<");
        nextButton = new JButton(">");
        lastButton = new JButton(">|");

        // 创建右侧小面板组件
        cancelAllButton = new JButton("全部取消");
        saveButton = new JButton("保存");
        studentIDSearchField = new JTextField();
        searchButton = new JButton("搜索");

        // 将性别单选按钮分组，确保只能选择一个
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        // 添加性别选择按钮到性别选择面板
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

        // 添加组件到顶部面板
        topPanel.add(new JLabel("学号："));
        topPanel.add(studentIdField);
        topPanel.add(insertButton);
        topPanel.add(new JLabel("姓名："));
        topPanel.add(studentNameField);
        topPanel.add(deleteButton);
        topPanel.add(new JLabel("班级："));
        topPanel.add(classComboBox);
        topPanel.add(cancelButton);
        topPanel.add(new JLabel("性别："));
        topPanel.add(genderPanel);
        topPanel.add(copyButton);
        topPanel.add(new JLabel("平均成绩"));
        topPanel.add(avgGardeField);

        // 添加组件到滚动面板
        scrollPane.setViewportView(gradeTable);   // 将表格嵌入滚动面板
        gradeTable.setFillsViewportHeight(true);    // 使表格填满视口

        // 添加组件到成绩面板
        gradesPanel.add(new JLabel("成绩"), BorderLayout.NORTH);
        gradesPanel.add(scrollPane, BorderLayout.CENTER);  // 使用 CENTER 确保填充面板

        // 添加组件到浏览按钮面板
        scanButtonPanel.add(firstButton);
        scanButtonPanel.add(prevButton);
        scanButtonPanel.add(nextButton);
        scanButtonPanel.add(lastButton);

        // 添加组件到左侧面板
        leftPanel.add(topPanel, BorderLayout.NORTH);
        leftPanel.add(gradesPanel, BorderLayout.CENTER);
        leftPanel.add(scanButtonPanel, BorderLayout.SOUTH);

        // 添加组件到右侧小面板
        rightMinPanel.add(cancelAllButton);
        rightMinPanel.add(saveButton);
        rightMinPanel.add(new JLabel("搜索学号"));
        rightMinPanel.add(studentIDSearchField);
        rightMinPanel.add(searchButton);

        // 添加右侧小面板到右侧面板
        rightPanel.add(rightMinPanel);

        // 将各部分添加到主窗体中
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // 绑定事件处理
        insertButton.addActionListener(this::setInsertButton);
        deleteButton.addActionListener(this::setDeleteButton);
        cancelButton.addActionListener(this::setCancelButton);
        copyButton.addActionListener(this::setCopyButton);
        firstButton.addActionListener(this::setFirstButton);
        prevButton.addActionListener(this::setPrevButton);
        nextButton.addActionListener(this::setNextButton);
        lastButton.addActionListener(this::setLastButton);
        cancelAllButton.addActionListener(this::setCancelAllButton);
        saveButton.addActionListener(this::setSaveButton);
        searchButton.addActionListener(this::setSearchButton);
    }

    /**
     * 从数据库加载数据并更新界面
     */
    private void loadData() {
        try {
            // 调用 StudentDAO 获取所有班级信息
            List<Class> classes = classDAO.getAllClasses();

            // 将数据加载到 JComboBox
            for (Class class_ : classes) {
                classComboBox.addItem(class_.getClassName());
            }

            System.out.println("----数据加载成功----");
        } catch (Exception exception) {
            exception.printStackTrace(); // 捕获并打印异常
        }
    }

    /**
     * 插入按钮事件
     * @param e
     */
    private void setInsertButton(ActionEvent e) {
        System.out.println("该功能未完成");
    }

    /**
     * 删除按钮事件
     * @param e
     */
    private void setDeleteButton(ActionEvent e) {
        System.out.println("该功能未完成");
    }

    /**
     * 取消按钮事件
     * @param e
     */
    private void setCancelButton(ActionEvent e) {
        System.out.println("该功能未完成");
    }

    /**
     * 复制按钮事件
     * @param e
     */
    private void setCopyButton(ActionEvent e) {
        try {
            // 获取被复制学生ID
            String copiedStudentID = studentIdField.getText();

            // 文本框中无内容时抛出异常
            if (copiedStudentID.isEmpty()) {
                throw new IllegalArgumentException("文本框不能为空！");
            }

            // 获取被复制学生信息
            Student student = studentDAO.getStudent(copiedStudentID);

            // 修改复制学生ID为当前最大ID + 1
            Long copyStudentID = Long.parseLong(studentDAO.getStudentIDMax()) + 1;
            student.setStudentId(Long.toString(copyStudentID));

            // 将复制学生信息添加到数据库
            studentDAO.addStudent(student);

            System.out.println("----复制成功----");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * 浏览首行按钮事件
     * @param e
     */
    private void setFirstButton(ActionEvent e) {
        try {
            // 获取成绩表格的模型
            DefaultTableModel model = (DefaultTableModel) gradeTable.getModel();
            // 检查表格是否为空
            if (model.getRowCount() > 0) {
                gradeTable.setRowSelectionInterval(0, 0);  // 选中首行
                System.out.println("已选中首行");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 浏览上一行按钮事件
     * @param e
     */
    private void setPrevButton(ActionEvent e) {
        try {
            int currentRow = gradeTable.getSelectedRow(); // 获取当前选中的行
            if (currentRow > 0) {
                gradeTable.setRowSelectionInterval(currentRow - 1, currentRow - 1); // 选中上一行
                System.out.println("已选中上一行");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 浏览下一行按钮事件
     * @param e
     */
    private void setNextButton(ActionEvent e) {
        try {
            int currentRow = gradeTable.getSelectedRow(); // 获取当前选中的行
            int rowCount = gradeTable.getRowCount(); // 获取表格的总行数
            if (currentRow < rowCount - 1) {
                gradeTable.setRowSelectionInterval(currentRow + 1, currentRow + 1); // 选中下一行
                System.out.println("已选中下一行");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 浏览尾行按钮事件
     * @param e
     */
    private void setLastButton(ActionEvent e) {
        try {
            // 获取成绩表格的模型
            DefaultTableModel model = (DefaultTableModel) gradeTable.getModel();
            // 检查表格是否为空
            if (model.getRowCount() > 0) {
                int lastRow = model.getRowCount() - 1; // 获取尾行的索引
                gradeTable.setRowSelectionInterval(lastRow, lastRow); // 选中尾行
                System.out.println("已选中尾行");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 全部取消按钮事件
     * @param e
     */
    private void setCancelAllButton(ActionEvent e) {
        try {
            // 文件路径，假设.sql文件位于resources/sql目录下
            String sqlFilePath = "src/sql/Teaching.sql";

            // 读取 SQL 文件内容
            String sqlContent = SqlFileReader.readSqlFile(sqlFilePath);

            // 使用 DBUtil 执行 SQL 内容
            int affectedRows = DBUtil.executeUpdate(sqlContent);

            // 根据受影响的行数显示结果
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this,
                        "SQL 执行成功，影响了 " + affectedRows + " 行！",
                        "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "SQL 执行成功，但没有影响任何行。",
                        "提示", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "执行 SQL 文件时发生错误！", "错误",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 保存按钮事件
     * @param e
     */
    private void setSaveButton(ActionEvent e) {
        System.out.println("该功能未完成");
    }

    /**
     * 搜索按钮事件
     * @param e
     */
    private void setSearchButton(ActionEvent e) {
        try {
            System.out.println("");
            // 获取搜索学生ID
            String searchStudentID = studentIDSearchField.getText();
            // 获取搜索学生信息
            Student student = studentDAO.getStudent(searchStudentID);
            // 获取搜索学生班级信息
            Class class_ = classDAO.getClass(student.getClassId());
            // 获取选课信息
            List<Elective> electives = electiveDAO.getElectives(searchStudentID);

            // 计算平均成绩
            int gradeSum = 0;   // 成绩总和
            int count = 0;      //成绩条数
            for (Elective elective : electives) {
                gradeSum += elective.getGrade();
                count++;
            }
            double gradeAvg = 0.1*gradeSum / count;

            // 加载学生信息到窗口中
            studentIdField.setText(student.getStudentId());     // 学号
            studentNameField.setText(student.getStudentName());     // 姓名
            classComboBox.setSelectedItem(class_.getClassName());   // 班级
            if (student.isGender()) {       // 性别
                maleRadioButton.setSelected(true);
            } else {
                femaleRadioButton.setSelected(true);
            }
            avgGardeField.setText(Double.toString(gradeAvg));   // 平均成绩

            // 加载成绩数据到表格中
            String[] columnNames = {"课程名称", "成绩"};
            Object[][] data = new Object[electives.size()][2];

            for (int i = 0; i < electives.size(); i++) {
                Elective elective = electives.get(i);
                data[i][0] = subjectDAO.getSubject(elective.getSubjectId())
                        .getSubjectName();
                data[i][1] = elective.getGrade();
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            gradeTable.setModel(model);  // 设置表格模型

            System.out.println("搜索成功！");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}