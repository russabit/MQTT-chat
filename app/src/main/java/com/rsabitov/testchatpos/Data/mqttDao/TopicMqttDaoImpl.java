package com.rsabitov.testchatpos.Data.mqttDao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rsabitov.testchatpos.Data.MqttClient;
import com.rsabitov.testchatpos.Domain.model.Topic;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class TopicMqttDaoImpl implements TopicMqttDao {
    private MqttClient mqttClient;

    public TopicMqttDaoImpl(MqttClient mqttClient) {
        this.mqttClient = mqttClient;
    }

    @Override
    public LiveData<Topic> getIncomingTopic() {
        MutableLiveData<Topic> contactName = new MutableLiveData<>();
        mqttClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {

            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                //logic for extracting contact name from topic
                contactName.setValue(new Topic(topic));
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    return contactName;
    }
}
