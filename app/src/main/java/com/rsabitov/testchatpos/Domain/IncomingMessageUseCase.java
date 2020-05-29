package com.rsabitov.testchatpos.Domain;

import com.rsabitov.testchatpos.Domain.model.Topic;
import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.repository.TopicRepository;
import com.rsabitov.testchatpos.Domain.repository.MessageRepository;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class IncomingMessageUseCase {
    private TopicRepository mTopicRepository;
    private MessageRepository mMessageRepository;
    private String topicName;

    public IncomingMessageUseCase(TopicRepository topicRepository, MessageRepository messageRepository) {
        this.mTopicRepository = topicRepository;
        this.mMessageRepository = messageRepository;
    }

    public void handleIncomingMessage(String topic, MqttMessage message) {
        if (isExistingContact(topic)) mMessageRepository.insert(new Message(message.toString(), topicName));
        else {
            Topic contact = new Topic(topic);
            mTopicRepository.insert(contact);
            mMessageRepository.insert(new Message(message.toString(), contact.name));
        }
    }

    private Boolean isExistingContact(String topic) {
        String contactName = topic.substring(0, topic.length() - 1);
        for (Topic contact : mTopicRepository.getAllTopics().getValue()) {
            if (contact.name.equals(contactName)) topicName = contact.name;
            return true;
        }
        return false;
    }
}
