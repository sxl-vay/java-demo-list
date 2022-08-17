package top.boking.DEMO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 8089;

    /**
     * 构造器
     * 初始化任务
     */
    public GroupChatServer(Integer port){
        try {
            // 得到选择器
            selector = Selector.open();
            // serverSocketChannel
            listenChannel = ServerSocketChannel.open();
            // 绑定端口
            ServerSocket socket = listenChannel.socket();
            socket.bind(new InetSocketAddress(port==null?PORT:port));
            // 设置非阻塞模式
            listenChannel.configureBlocking(false);
            // 将该listenChannel 注册到Selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *  监听
     */
    public void listen(){
        try {
            // 循环处理
            while (true){
                int count = selector.select();
                //System.out.println("**系统通知**：当前线程被唤醒！");
                if (count > 0){ // 有事件处理
                    // 遍历得到的selectionKey集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        // 取出selectionKey
                        SelectionKey key = iterator.next();
                        // 监听到OP_ACCEPT
                        if (key.isAcceptable()){
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            // 将该socketChannel注册到Selector
                            sc.register(selector, SelectionKey.OP_READ);
                            // 提示
                            System.out.println(sc.getRemoteAddress() + " connected to the chat");
                            //
                            sc.write(ByteBuffer.wrap(("您好，" + sc.getRemoteAddress() + "，欢迎来到聊天室~~~").getBytes()));
                        }
                        // 通道可读
                        if (key.isReadable()){
                            // TODO处理读
                            readData(key);
                        }
                        // 当前的key删除, 防止重复处理
                        iterator.remove();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *  读取客户端消息
     * @param key
     */
    private void readData(SelectionKey key){
        // 定义一个SocketChannel
        SocketChannel channel = null;
        String msg = null;
        try {
            // 得到channel
            channel = (SocketChannel) key.channel();
            // 创建缓冲buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            // 根据count的值做处理
            if (count > 0){
                // 把缓冲区数据转为字符串并输出
                msg = new String(buffer.array());
                // 输出该消息
                System.out.println("from Client: " + msg);
            }
        } catch (IOException e){
            try {
                msg = "**系统通知：**" + channel.getRemoteAddress() + " is offline";
                // 输出该消息
                System.out.println(msg+"\r\n");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                sendInfoToAllClients(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *  广播消息给所有客户端(channel）
     * @param msg
     */
    private void sendInfoToAllClients(String msg) throws IOException {
        // 遍历, 所有注册到selector上的SocketChannel
        for (SelectionKey key : selector.keys()){
            // 通过key取出对应的SocketChannel
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel.isOpen()){
                // 转型
                SocketChannel dest = (SocketChannel) targetChannel;
                // 将消息存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                // 将buffer数据写入到通道
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {
        // 创建服务器对象
        String arg = args[0];

        GroupChatServer groupChatServer = new GroupChatServer(Integer.parseInt(arg));
        groupChatServer.listen();
    }
}

