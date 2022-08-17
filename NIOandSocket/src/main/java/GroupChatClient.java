import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;


public class GroupChatClient {

    // 定义相关属性
    private final int PORT = 9706;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    /**
     *  构造器, 完成初始化工作
     */
    public GroupChatClient(String ip) throws IOException {
        selector = Selector.open();
        // 连接服务器
        socketChannel = SocketChannel.open(new InetSocketAddress(ip,PORT));
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 将channel 注册到selector
        socketChannel.register(selector, SelectionKey.OP_READ);
        // 得到username
        username = socketChannel.getLocalAddress().toString().substring(1);
        System.out.println(username + " is fine");
    }

    /**
     * 向服务器发送消息
     * @param info
     */
    public void sendInfo(String info){
//        info = username + " said: " + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void  readInfo(){
        try{
            int readChannels = selector.select();
            //System.out.println("**系统通知**：当前线程被唤醒！");
            // 有可用的通道
            if (readChannels > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    // 客户端只考虑可读
                    if (key.isReadable()){
                        // 得到相关通道
                        SocketChannel sc = (SocketChannel) key.channel();
                        // 得到一个Buffer
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        // 读取
                        sc.read(buffer);
                        // 把读到的缓冲区数据转成字符串
                        String msg = new String(((buffer.array())));
                        //String msg = getString(buffer);
                        System.out.println(msg.trim());
                    }
                }
                iterator.remove(); // 删除当前的selectionKey, 防止重复操作
            } else {
                System.out.println("No channel available");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        // 启动客户端
        //  String ip = "192.168.1.11";
                String ip = "localhost";
        final GroupChatClient chaClient = new GroupChatClient(ip);
        // 启动一个线程
        new Thread(){
            @Override
            public void run() {
                while (true){
                    chaClient.readInfo();
                }
            }
        }.start();
        // 发送数据给服务器端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            chaClient.sendInfo(s);
        }
    }

}

