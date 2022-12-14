import cn.hutool.core.util.ArrayUtil;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyAndRenameTest {

    public static void main(String[] args) {
        String path = "D:\\dowork\\ssss\\src\\main\\java\\com\\xunmo\\webs\\dict";
        String[] sourceNameAttr = {"dict"};
        String[] targetNameAttr = {"user"};

        rename(sourceNameAttr, targetNameAttr, path);
    }

    private static void rename(String[] sourceNameAttr, String[] targetNameAttr, String sourcePath) {
        if (ArrayUtil.isEmpty(targetNameAttr)) {
            throw new IllegalArgumentException("targetName is Null or Empty");
        }
        if (sourcePath == null || "".equals(sourcePath)) {
            System.err.println(sourcePath);
            throw new IllegalArgumentException("sourcePath is Null or Empty");
        }
        boolean isError = true;
        String errorMsg = "";
        for (String sourceName : sourceNameAttr) {
            final int matchIndex = sourcePath.lastIndexOf(sourceName);
            if (matchIndex == -1) {
                errorMsg = sourceName;
            } else {
                isError = false;
            }
        }
        if (isError) {
            System.err.println(errorMsg);
            throw new IllegalArgumentException("sourcePath not find sourceName");
        }
        final File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            throw new IllegalArgumentException("sourcePath is not exists");
        }

        String sourceName = sourceNameAttr[0];
        String targetName = targetNameAttr[0];
        String sourceDirName = "";
        String targetDirName = "";
        if (sourceNameAttr.length > 1 && targetNameAttr.length > 1) {
            sourceDirName = sourceNameAttr[1];
            targetDirName = targetNameAttr[1];
        } else if (sourceNameAttr.length > 1) {
            sourceDirName = sourceNameAttr[1];
            targetDirName = targetNameAttr[0];
        } else if (targetNameAttr.length > 1) {
            sourceDirName = sourceNameAttr[0];
            targetDirName = targetNameAttr[1];
        } else {
            sourceDirName = sourceNameAttr[0];
            targetDirName = targetNameAttr[0];
        }
        final int matchIndex = sourcePath.lastIndexOf(sourceDirName);
        final String targetPath = sourcePath.substring(0, matchIndex) + targetDirName;
        copyDir(sourceName, targetName, sourceDirName, targetDirName, sourcePath, targetPath);
    }

    //??????????????????
    public static void copyDir(String sourceName, String targetName, String sourceDirName, String targetDirName, String sourcePath, String newPath) {
        File start = new File(sourcePath);
        File end = new File(newPath);
        String[] filePath = start.list();        //?????????????????????????????????????????????????????????
        if (!end.exists()) {
            end.mkdir();
        }
        for (String temp : filePath) {
            String newTemp = temp.replace(upperFirst(sourceName), upperFirst(targetName));
            //???????????????????????????????????????????????????
            if (new File(sourcePath + File.separator + newTemp).isDirectory()) {
                //???????????????????????????
                copyDir(sourceName, targetName, sourceDirName, targetDirName, sourcePath + File.separator + temp, newPath + File.separator + newTemp);
            } else {
                //????????????????????????
                copyFile(sourceName, targetName, sourceDirName, targetDirName, sourcePath + File.separator + temp, newPath + File.separator + newTemp);
            }
        }
    }

    //???????????????
    public static void copyFile(String sourceName, String targetName,String sourceDirName,String targetDirName, String sourcePath, String newPath) {
        File start = new File(sourcePath);
        File end = new File(newPath);
        try (FileReader bis = new FileReader(start);
             FileWriter bos = new FileWriter(end);
             BufferedReader br = new BufferedReader(bis);
             // ?????????, ???????????????
             CharArrayWriter tempStream = new CharArrayWriter();) {
            // ??????
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replace(upperFirst(sourceName), upperFirst(targetName));
                line = line.replace(sourceName, targetName);
                line = line.replace(sourceDirName, targetDirName);
                // ?????????????????????
                tempStream.write(line);
                // ???????????????
                tempStream.append(System.getProperty("line.separator"));
            }
            // ?????????????????? ?????? ??????
            tempStream.writeTo(bos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String upperFirst(String source) {
        if (null == source || "".equals(source)) {
            return source;
        }
        if (source.length() == 1) {
            return source.toUpperCase();
        }
        return source.substring(0, 1).toUpperCase() + source.substring(1);
    }


}
