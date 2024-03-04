package org.example.impl;

import org.example.interfaces.Gateway;

public class GatewayImpl implements Gateway {

    public void send(String userId, String message) {
        System.out.println("[Gateway] Sending message to user " + userId);
    }
}
