package top.boking.spring.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetClasses {

    public static void main(String[] args) {
        String s = "top.boking.spring.a";
        int b = s.indexOf(".a");
        System.out.println("b = " + b);
        int length = s.length();
        System.out.println("length = " + length);
        String substring = s.substring(0, b);

        System.out.println("substring = " + substring);

    }

    public static List<String> getComponentClass() {



        URL resource = new GetClasses().getClass().getClassLoader().getResource("");
        String path = resource.getPath();
        String separator = File.separator;
        //System.out.println("path = " + path);
        List<String> classDescribeList = new ArrayList<>();

        File rootPath = new File(path);
        ArrayList<String> list = new ArrayList<>();
        getAllClass(rootPath, list);
        String rootStr = path.replace("/", separator).substring(1, path.length() - 1);
        //System.out.println("rootStr = " + rootStr);

        for (String s : list) {
            if (s.endsWith(".class")) {
                String replace = s.replace(rootStr, "").replace(separator, ".");
                String classDescribe = replace.substring(1, replace.length() - 6);
                if (!classDescribe.startsWith("top.boking.spring."))
                    classDescribeList.add(classDescribe);
            }
        }

        return classDescribeList;
    }

    private static void getAllClass(File rootFile, List<String> list) {
        File[] files = rootFile.listFiles();
        for (File file : files) {
            String path1 = file.getPath();
            //System.out.println("path1 = " + path1);
            if (file.isDirectory()) {
                getAllClass(file, list);
            } else {
                String path = file.getPath();
                list.add(path);
            }
        }

    }
}
