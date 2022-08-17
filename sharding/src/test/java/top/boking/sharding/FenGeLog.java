package top.boking.sharding;

import java.io.*;
import java.util.Scanner;

/**
 * @author shxl
 * @data 2022/7/28 15:18
 **/
public class FenGeLog {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int i =0;

        while (true) {
            System.out.println("--------ready---------");
            String s = scanner.nextLine();
            File file = new File(s);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            String sf ;

            a:for (int i1 = 0; i1 < 10; i1++) {
                File file1 = new File("C:\\Data\\Spring-MyApp\\log\\a" + i1 + ".txt");
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file1));
                for (i = 0;((sf = bufferedReader.readLine()) !=null) &&i<1000 ;i++ ) {
                    bufferedWriter.write(sf,0,sf.length()-1);
                    bufferedWriter.newLine();
                    System.out.println(i);
                }
                if (sf == null) break a;
            }


        }
    }
}
