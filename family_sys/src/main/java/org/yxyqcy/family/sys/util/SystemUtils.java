package org.yxyqcy.family.sys.util;

import org.yxyqcy.family.common.config.Global;
import org.yxyqcy.family.common.util.DateUtil;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with family.
 * author: cy
 * Date: 17/2/28
 * Time: 下午2:39
 * description: 系统工具
 */
public class SystemUtils {
    /**
     * backUp db
     */
    public static void backupDb(String dbName) {
        try {
            String ip = Global.getConfig("backup_ip");
            String user = Global.getConfig("backup_user");
            String passWd = Global.getConfig("backup_passWd");
            String backUpPath = Global.getConfig("backup_path");
            Runtime rt = Runtime.getRuntime();
            // 调用 调用mysql的安装目录的命令
            Process child = rt
                    .exec("mysqldump -h " + ip + " -u" + user + " -p" + passWd + "  -B " + dbName);
            // 设置导出编码为utf-8。这里必须是utf-8
            // 把进程执行中的控制台输出信息写入.sql文件，即生成了备份文件。注：如果不对控制台信息进行读出，则会导致进程堵塞无法运行
            InputStream in = child.getInputStream();// 控制台的输出信息作为输入流

            InputStreamReader xx = new InputStreamReader(in, "utf-8");
            // 设置输出流编码为utf-8。这里必须是utf-8，否则从流中读入的是乱码

            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            // 组合控制台输出信息字符串
            BufferedReader br = new BufferedReader(xx);
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();
            String backUpDate = DateUtil.getSimepleDate("yyyy_MM_dd_HH_mm",null);
            // 要用来做导入用的sql目标文件：
            FileOutputStream fout = new FileOutputStream(backUpPath + dbName + "_" + backUpDate + ".sql");
            OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
            writer.write(outStr);
            writer.flush();
            in.close();
            xx.close();
            br.close();
            writer.close();
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 还原数据库
     * @param databaseName
     */
    public static void restoreDb(String databaseName) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec("e:\\MySQL\\bin\\mysql.exe -hlocalhost -uroot -p123 --default-character-set=utf8 "
                            + databaseName);
            OutputStream outputStream = process.getOutputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    new FileInputStream("C:\\test.sql"), "utf-8"));
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            // System.out.println(str);
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                    "utf-8");
            writer.write(str);
            writer.flush();
            outputStream.close();
            br.close();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
