package com.rsabitov.mqtt_chat.Data.mqttDao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rsabitov.mqtt_chat.Data.MqttClient;
import com.rsabitov.mqtt_chat.Domain.model.Message;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MessageMqttDaoImpl implements MessageMqttDao {
    private MqttClient mqttClient;

    public MessageMqttDaoImpl(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @Override
    public LiveData<Message> getIncomingMessage() {
        MutableLiveData<Message> incomingMessage = new MutableLiveData<>();
        mqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                incomingMessage.setValue(new Message(message.toString(), topic));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        return incomingMessage;
    }

    @Override
    public void subscribeToTopic(String topic) {
        mqttClient.subscribeToTopic(topic);
    }

    @Override
    public void publishToTopic(String topic, String message) {
        mqttClient.publishMessageToTopic(topic, message);
    }

}
