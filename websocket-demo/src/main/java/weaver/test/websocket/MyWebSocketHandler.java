package weaver.test.websocket;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    // 存储所有活跃的 WebSocket 会话
    private static Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 客户端连接成功后，将会话存储到集合中
        sessions.add(session);
        System.out.println("New connection established, session ID: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理收到的客户端消息
        String clientMessage = message.getPayload();
        System.out.println("Received: " + clientMessage);

        // 向客户端发送确认消息
        session.sendMessage(new TextMessage("Server received: " + clientMessage));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 客户端断开连接后，从集合中移除会话
        sessions.remove(session);
        System.out.println("Connection closed, session ID: " + session.getId());
    }

    // 向所有连接的客户端推送消息
    public void sendMessageToAll(String message) {
        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
