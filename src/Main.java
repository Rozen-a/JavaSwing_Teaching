import ui.MainFrame;

import javax.swing.*;

/**
 * 主方法: Main
 * 作用: 启动程序
 */
public class Main {
    public static void main(String[] args) {
        // 使用 Swing 的事件分发线程启动界面
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();      // 创建主窗体
            frame.setBounds(1200, 500, 580, 500);    // 设置窗体位置、大小
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // 设置关闭操作
            frame.setVisible(true);     // 设置窗体可见
        });
    }
}
