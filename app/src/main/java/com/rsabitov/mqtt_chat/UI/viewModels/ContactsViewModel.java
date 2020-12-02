package com.rsabitov.mqtt_chat.UI.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rsabitov.mqtt_chat.Domain.model.Message;
import com.rsabitov.mqtt_chat.Domain.model.Topic;
import com.rsabitov.mqtt_chat.Data.repository.TopicRepositoryImpl;
import com.rsabitov.mqtt_chat.Domain.repository.TopicRepository;

import java.util.List;

public class ContactsViewModel extends AndroidViewModel {
    private TopicRepository mTopicRepository;

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

    public LiveData<Message> getMessageFromThatTopic() {
        return mTopicRepository.getIncomingMessageFromThatTopic();
    }

    public void deleteTopic(Topic topic) {
        mTopicRepository.delete(topic);
    }

    public void subscribeToTopic(String topic) {
        mTopicRepository.subscribeToTopic(topic);
    }
}
