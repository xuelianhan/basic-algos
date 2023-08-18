package org.ict.algorithm.leetcode.coupang;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Message queues that implement producer-consumer.
 * Messages have ttl, expired messages must be released and cannot be consumed
 * @author sniper
 * @date 19 Jul 2023
 */
public class DesignMessageQueue {

    class MessageQueue {
        private ConcurrentLinkedQueue<Message> queue;

        public MessageQueue() {
            queue = new ConcurrentLinkedQueue<>();
        }

        public void put(Message message, long ttl, TimeUnit unit) {
            message.setExpirationTime(System.currentTimeMillis() + unit.toMillis(ttl));
            queue.add(message);
            //this.removeExpiredMessages();
        }

        public Message get() {
            Message message = queue.poll();
            if (message != null) {
                message.setReceivedTime(System.currentTimeMillis());
            }
            return message;
        }

        public void removeExpiredMessages() {
            queue.removeIf(message -> message.getExpirationTime() < System.currentTimeMillis());
        }
    }

    static class Message {
        private String id;
        private String payload;

        private long expirationTime;
        private long receivedTime;

        public Message(String id, String payload, long expirationTime) {
            this.id = id;
            this.payload = payload;
            this.expirationTime = expirationTime;
        }

        public String getId() {
            return id;
        }

        public String getPayload() {
            return payload;
        }

        public long getExpirationTime() {
            return expirationTime;
        }

        public void setExpirationTime(long expirationTime) {
            this.expirationTime = expirationTime;
        }

        public long getReceivedTime() {
            return receivedTime;
        }

        public void setReceivedTime(long receivedTime) {
            this.receivedTime = receivedTime;
        }
    }
}
