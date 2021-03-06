package com.rsabitov.mqtt_chat.Data;

import android.content.Context;
import android.util.Log;

import com.rsabitov.mqtt_chat.Data.mqttDao.MessageMqttDao;
import com.rsabitov.mqtt_chat.Data.mqttDao.MessageMqttDaoImpl;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MqttClient {
    private MqttAndroidClient mqttAndroidClient;

    private MessageMqttDao mMessageMqttDao;

    public MessageMqttDao getMessageMqttDao() {
        if (mMessageMqttDao != null) {
            return mMessageMqttDao;
        }
        else {
            if (mMessageMqttDao == null) {
                mMessageMqttDao = new MessageMqttDaoImpl(this);
            }
        }
        return mMessageMqttDao;
    }

    private final String serverUri = "tcp://broker.mqttdashboard.com:1883"; //in the form of tcp://server:port "tcp://broker.mqttdashboard.com:1883"

    private final String clientId = org.eclipse.paho.client.mqttv3.MqttClient.generateClientId();
    private final String subscriptionTopic = "/temperature";
    //final String username = "";
    //final String password = "";

    private static volatile MqttClient INSTANCE;

    public static MqttClient getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MqttClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MqttClient(context);
                    //INSTANCE.setCallback(onDeliveryCallback);
                }
            }
        }
        return INSTANCE;
    }

    private MqttClient(Context context) {
        mqttAndroidClient = new MqttAndroidClient(context, serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                Log.w("mqtt", serverURI);
            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                Log.w("mqtt", message.toString());
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        connect();
    }

    public void setCallback(MqttCallbackExtended callback) {
        mqttAndroidClient.setCallback(callback);
    }

    private void connect() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);
        //mqttConnectOptions.setUserName(username);
        //mqttConnectOptions.setPassword(password.toCharArray());

        try {
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);
                    subscribeToTopic(subscriptionTopic);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.w("Mqtt", "Failed to connect to: " + serverUri + " " + exception.toString());
                }
            });
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }

    public void subscribeToTopic(String topic) {
        try {
            mqttAndroidClient.subscribe(topic, 0, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.w("Mqtt " + topic,"Subscribed!");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.w("Mqtt", "Subscribed fail!");
                }
            });
        } catch (MqttException ex) {
            ex.printStackTrace();
        }
    }

    public void publishMessageToTopic(String topic, String message) {
        try {
            mqttAndroidClient.publish(topic, new MqttMessage(message.getBytes()));
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
