package weaver.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import weaver.test.websocket.MyWebSocketHandler;

@RestController
public class WebSocketController {

    @Autowired
    private MyWebSocketHandler myWebSocketHandler;

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        myWebSocketHandler.sendMessageToAll(message);
        return "Message sent: " + message;
    }
}
