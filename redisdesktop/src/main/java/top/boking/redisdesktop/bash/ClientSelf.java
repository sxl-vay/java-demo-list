package top.boking.redisdesktop.bash;

import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.BuilderFactory;
import redis.clients.jedis.Connection;
import redis.clients.jedis.Protocol;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author shxl
 * @data 2022/5/30 23:09
 **/
public class ClientSelf extends BinaryClient {
   /* public static void main(String[] args)  {
        ClientSelf aa = new ClientSelf();
        aa.getConnection("192.168.1.12","123456",6379);

        aa.sentSelf("get a");

    }*/

    public void getConnection(String host,String password,Integer port) {
        if (!(password == null)) {
            setPassword(password);
        }
        setHost(host);
        setPort(port);
    }

    public void sentSelf(String s) {
        String[] s1 = s.split(" ");
        Protocol.Command set =null;
        try {
           set = Protocol.Command.valueOf(s1[0].toUpperCase());
            String[] args = new String[s1.length-1];
            for (int i = 0; i < args.length; i++) {
                args[i] =s1[i+1];
            }
            Connection connection = sendCommand(set, args);
            List<Object> all = connection.getAll();
            for (Object o : all) {
                if (o == null) {
                    System.out.println("null");
                    return;
                }
                if (o instanceof Long) {
                    System.out.println( o);
                    break;
                }
                if (o instanceof byte[]) {
                    byte[] o1 = (byte[]) o;
                    try {
                        String s2 = new String(o1, "utf-8");
                        System.out.println(  s2);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                if (o instanceof ArrayList) {
                    String s2 = o.toString();
                    System.out.println(s2);
                }


            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    private Map<String,Object> parseCmd(String s) {
        String[] s1 = s.split(" ",2);
        Protocol.Command set = Protocol.Command.valueOf(s1[0].toUpperCase());
        switch (set) {
            case KEYS:{
                String[] args = new String[s1.length-1];
                for (int i = 0; i < args.length; i++) {
                    args[i] =s1[i+1];
                }
                Connection connection = sendCommand(Protocol.Command.KEYS,s1[1]);
                List<Object> all = connection.getAll();
                Set<String> build = BuilderFactory.STRING_SET.build(all);
                HashMap<String,Object> m = new HashMap<>();
                m.put("KEYS",build);
                return m;
            }
        }
        return null;
    }
}

