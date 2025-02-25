package com.example.websocket;

import com.example.common.VO.ItemForKitchenVO;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ServerEndpoint("/ws/{wsId}")
public class WebSocketServer {
    private static Map<String, Session> sessionMap = new HashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("wsId") String wsId){
        sessionMap.put(wsId, session);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("wsId") String wsId){
        System.out.println("Received message from " + wsId + ": " + message);
    }

    @OnClose
    public void onClose(@PathParam("wsId") String wsId){
        System.out.println("Closing connection from " + wsId);
        sessionMap.remove(wsId);
    }

    public void sendToAllClients(List<ItemForKitchenVO> orderDetails){
        Collection<Session> sessions = sessionMap.values();
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendObject(orderDetails);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
