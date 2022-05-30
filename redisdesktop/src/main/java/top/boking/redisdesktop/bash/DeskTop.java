package top.boking.redisdesktop.bash;

import java.util.Scanner;

/**
 * @author shxl
 * @data 2022/5/29 21:27
 **/
public class DeskTop {
    public static void main(String[] args) {
        System.out.println("waiting...");
        ClientSelf clientSelf = new ClientSelf();
        clientSelf.getConnection("192.168.1.11","123456",6379);
        clientSelf.sentSelf("ping connection");
        System.out.println("create!");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(">");
            String s = scanner.nextLine();

            if ("".equals(s))continue;
            if ("exit".equalsIgnoreCase(s)) {
                return;
            }
            clientSelf.sentSelf(s);

        }
    }
}
