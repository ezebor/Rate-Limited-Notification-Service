package org.example.gateways;

public class GatewayImpl implements Gateway {

    public void send(String userId, String message) {
        System.out.println("[Gateway] Sending message to user " + userId);
    }
}
