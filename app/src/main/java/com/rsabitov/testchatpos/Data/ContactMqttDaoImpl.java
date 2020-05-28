package com.rsabitov.testchatpos.Data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rsabitov.testchatpos.Domain.model.Contact;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class ContactMqttDaoImpl implements ContactMqttDao {
    private MqttHelper mqttHelper;

    public ContactMqttDaoImpl(MqttHelper mqttHelper) {
        this.mqttHelper = mqttHelper;
    }

    @Override
    public LiveData<String> getIncomingContact() {
        MutableLiveData<String> contactName = new MutableLiveData<>();
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                //logic for extracting contact name from topic
                contactName.setValue(topic);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    return contactName;
    }
}
