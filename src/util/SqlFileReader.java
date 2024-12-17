package util;

import java.io.*;

public class SqlFileReader {

    /**
     * 读取 SQL 文件内容
     * @param filePath SQL 文件路径
     * @return 文件内容作为字符串
     * @throws IOException 如果文件读取失败
     */
    public static String readSqlFile(String filePath) throws IOException {
        StringBuilder sqlContent = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            sqlContent.append(line).append("\n"); // 拼接每一行
        }
        reader.close();
        return sqlContent.toString();
    }
}
