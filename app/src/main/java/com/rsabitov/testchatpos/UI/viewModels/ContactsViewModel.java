package com.rsabitov.testchatpos.UI.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.model.Topic;
import com.rsabitov.testchatpos.Data.repository.TopicRepositoryImpl;
import com.rsabitov.testchatpos.Domain.repository.TopicRepository;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private TopicRepository mTopicRepository;
    //private IncomingMessageUseCase mIncomingMessageUseCase;
    //private int contactId;

    private LiveData<List<Topic>> mAllContacts;

    public ContactsViewModel(@NonNull Application application) {
        super(application);
        mTopicRepository = new TopicRepositoryImpl(application); //later will inject a singleton
        mAllContacts = mTopicRepository.getAllTopics();
    }

    public LiveData<List<Topic>> getAllTopics() { return mAllContacts; }

    public void insert(Topic topic) { mTopicRepository.insert(topic); }

    public Topic getContactByName(String name) {
        return mTopicRepository.getTopicByName(name);
    }

    public LiveData<Message> getMessageFromThatContact() {
        return mTopicRepository.getIncomingMessageFromThatTopic();
    }
}
