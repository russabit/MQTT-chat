package com.rsabitov.testchatpos.Data.repository;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Data.ChatDatabase;
import com.rsabitov.testchatpos.Data.MqttHelper;
import com.rsabitov.testchatpos.Data.RoomDao.ContactDao;
import com.rsabitov.testchatpos.Domain.model.Contact;
import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.repository.ContactRepository;
import com.rsabitov.testchatpos.Domain.repository.MessageRepository;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class ContactRepositoryImpl implements ContactRepository {
    private ContactDao mContactDao;
    private LiveData<List<Contact>> mAllContacts;

    public ContactRepositoryImpl(Application application) {
        ChatDatabase db = ChatDatabase.getDatabase(application);
        mContactDao = db.getContactDao();
        mAllContacts = mContactDao.getAll();
        startMqtt(application);
    }

    @Override
    public LiveData<List<Contact>> getAllContacts() {
        return mAllContacts;
    }

    @Override
    public void insert(Contact contact) {
        ChatDatabase.databaseWriteExecutor.execute(() -> mContactDao.insert(contact));
    }

    @Override
    public Contact getContactByName(String name) {
        return mContactDao.getByName(name);
    }

    private void startMqtt(Context context) {
        MqttHelper mqttHelper = MqttHelper.getInstance(context);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) { }

            @Override
            public void connectionLost(Throwable cause) { }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                Log.w("Debug", message.toString());
                //Toast.makeText(getApplication(), "new message from " + topic + ": " + message.toString(), Toast.LENGTH_SHORT).show();
                //insert(new Contact(topic));
/*                if (isExistingContact(topic)) {
                    mMessageRepository = new MessageRepositoryImpl(getApplication(), contactId);
                    //mIncomingMessageUseCase = new IncomingMessageUseCase(mContactRepository, mMessageRepository);
                }*/
                Contact contact = new Contact(topic);
                insert(contact);
                int contactId = getContactByName(contact.name).id;
                //MessageRepository messageRepository = new MessageRepositoryImpl(getApplication(), contactId);
                //messageRepository.insert(new Message(message.toString(), contact.id));
                //mMessagesViewModel.sendMessage(new Message(message, ));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) { }
        });
    }

    private Boolean isExistingContact(String topic) {
        String contactName = topic.substring(0, topic.length() - 1);
        for (Contact contact : getAllContacts().getValue()) {
            //if (contact.name.equals(contactName)) contactId = contact.id;
            return true;
        }
        return false;
    }
}
