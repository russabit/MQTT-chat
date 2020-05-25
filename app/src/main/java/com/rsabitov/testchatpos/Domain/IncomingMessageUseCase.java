package com.rsabitov.testchatpos.Domain;

import com.rsabitov.testchatpos.Domain.model.Contact;
import com.rsabitov.testchatpos.Domain.repository.ContactRepository;
import com.rsabitov.testchatpos.Domain.repository.MessageRepository;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class IncomingMessageUseCase {
    ContactRepository mContactRepository;
    MessageRepository mMessageRepository;

    public IncomingMessageUseCase(ContactRepository contactRepository, MessageRepository messageRepository) {
        this.mContactRepository = contactRepository;
        this.mMessageRepository = messageRepository;
    }

    public void handleIncomingMessage(String topic, MqttMessage message) {
        mContactRepository.insert(new Contact(topic));
    }

    private void checkContact(String topic) {
        int contactId;
        String contactName = topic.substring(0, topic.length() - 1);
        for (Contact contact : mContactRepository.getAllContacts().getValue()) {
            if (contact.name == contactName) contactId = contact.id;
            return;
        }

    }
}
