package com.example.jsf2;


import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/websocket")
public class WebSocketEndpoint {
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @Inject
    private MyBean myBean; // Injection du bean MyBean

    @PostConstruct
    public void init() {
        // Laissez cette méthode vide si aucune initialisation supplémentaire n'est requise
    }

    @OnOpen
    public void onOpen(Session session) {
        // Ajouter la nouvelle session à la liste des clients
        clients.add(session);

        // Envoyer la liste des éléments existants à la nouvelle session
        try {
            Gson gson = new Gson();
            String jsonElements = gson.toJson(myBean.getElements());
            session.getBasicRemote().sendText(jsonElements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        synchronized (clients) {
            for (Session client : clients) {
                if (!client.equals(session)) {
                    client.getBasicRemote().sendText(message);
                }
            }
        }
    }

    public static void broadcast(String message) throws IOException {
        synchronized (clients) {
            for (Session client : clients) {
                client.getBasicRemote().sendText(message);
            }
        }
    }
}
