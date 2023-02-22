package mytest;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Description FileUtil
 *
 * @author suntao(01408885)
 * @since 2021.06.18
 */

public class FileUtil {

    /**
     * @param path 文件夹路径
     * @return java.util.List<java.io.File>
     * @description 不使用递归的方法调用
     * @author https://blog.csdn.net/chen_2890
     * @date 2019/6/14 17:34
     * @version V1.0
     */
    public static List<File> traverseFolder1(String path) {
        List<File> fileList = new ArrayList<>();
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    folderNum++;
                } else {
                    fileList.add(file2);
                    System.out.println("文件:" + file2.getAbsolutePath());
                    fileNum++;
                }
            }
            File temp_file;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        list.add(file2);
                        folderNum++;
                    } else {
                        fileList.add(file2);
                        System.out.println("文件:" + file2.getAbsolutePath());
                        fileNum++;
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);
        return fileList;
    }

    /**
     * @param path 文件夹路径
     * @return java.util.List<java.io.File>
     * @description 使用递归的方法调用
     * @author https://blog.csdn.net/chen_2890
     * @date 2019/6/14 17:35
     * @version V1.0
     */
    public static List<File> traverseFolder2(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder2(file2.getAbsolutePath());
                    } else {
                        fileList.add(file2);
                        System.out.println("文件:" + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return fileList;
    }

    /**
     * @param path 文件夹路径
     * @return java.util.List<java.io.File>
     * @description 使用递归的方法调用
     * @author https://blog.csdn.net/chen_2890
     * @date 2019/6/14 17:35
     * @version V1.0
     */
    public static void traverseFolder3(String path, String targetFilePath) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        traverseFolder3(file2.getAbsolutePath(), targetFilePath);
                    } else {
                        String file2Name = file2.getName();
                        if (file2Name.contains(".java")) {
                            //System.out.println("copy file ：" + file2Name);
                            System.out.println("文件:" + file2.getAbsolutePath() + " .... " + file2Name);
                            copyFile(file2, targetFilePath);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return;
    }

    private static void copyFile(File file, String targetFilePath) {
        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            File f = new File(targetFilePath);
            fw = new FileWriter(f, true);

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str = "";
            while ((str = br.readLine()) != null) {
                //System.out.println(str + "\n");
                fw.write(str + "\n");
            }

            fw.flush();
            fw.close();

            fr.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param path 文件夹路径
     * @return java.util.List<java.io.File>
     * @description 使用递归的方法调用，并判断文件名是否以.jpg结尾
     * @author https://blog.csdn.net/chen_2890
     * @date 2019/6/14 17:35
     * @version V1.0
     */
    public static List<File> getFileList(String path) {
        List<File> fileList = new ArrayList<>();
        File dir = new File(path);
        // 该文件目录下文件全部放入数组
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                // 判断是文件还是文件夹
                if (files[i].isDirectory()) {
                    // 获取文件绝对路径
                    getFileList(files[i].getAbsolutePath());
                    // 判断文件名是否以.jpg结尾
                } else if (fileName.endsWith(".jpg")) {
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    fileList.add(files[i]);
                } else {
                    continue;
                }
            }
        }
        return fileList;
    }
}

