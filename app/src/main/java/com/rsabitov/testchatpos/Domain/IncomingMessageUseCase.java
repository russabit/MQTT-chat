package com.rsabitov.testchatpos.Domain;

import com.rsabitov.testchatpos.Domain.model.Contact;
import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.repository.ContactRepository;
import com.rsabitov.testchatpos.Domain.repository.MessageRepository;

import org.eclipse.paho.client.mqttv3.MqttMessage;

public class IncomingMessageUseCase {
    private ContactRepository mContactRepository;
    private MessageRepository mMessageRepository;
    private int contactId;

    public IncomingMessageUseCase(ContactRepository contactRepository, MessageRepository messageRepository) {
        this.mContactRepository = contactRepository;
        this.mMessageRepository = messageRepository;
    }

    public void handleIncomingMessage(String topic, MqttMessage message) {
        if (isExistingContact(topic)) mMessageRepository.insert(new Message(message.toString(), contactId));
        else {
            Contact contact = new Contact(topic);
            mContactRepository.insert(contact);
            mMessageRepository.insert(new Message(message.toString(), contact.id));
        }
    }

    private Boolean isExistingContact(String topic) {
        String contactName = topic.substring(0, topic.length() - 1);
        for (Contact contact : mContactRepository.getAllContacts().getValue()) {
            if (contact.name.equals(contactName)) contactId = contact.id;
            return true;
        }
        return false;
    }
}
