package com.microsevices;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Consumer {
    public static void main(String[] args) {
        try (Connection connection = createConnection()) {
            Channel channel = connection.createChannel();
            channel.basicConsume("My queue", true, (consumerTag, message) -> {
                String m = new String(message.getBody(), StandardCharsets.UTF_8);
                System.out.println("I just received a message: " + m);
            }, consumerTag -> {});

            Thread.sleep(10000000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection createConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("rabbitmq");
        factory.setPassword("rabbitmq");
        factory.setVirtualHost("/");

        return factory.newConnection();
    }
}
