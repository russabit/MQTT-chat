package com.rsabitov.testchatpos.UI.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.rsabitov.testchatpos.Domain.model.Message;
import com.rsabitov.testchatpos.Domain.repository.MessageRepository;
import com.rsabitov.testchatpos.Data.repository.MessageRepositoryImpl;

import java.util.List;

public class MessagesViewModel extends AndroidViewModel {
    private MessageRepository mMessageRepository;
    private LiveData<List<Message>> mAllMessages;
    private String mTopic;

    public MessagesViewModel(@NonNull Application application) {
        super(application);
    }

    public void setContactTopic(String topic) {
        mTopic = topic;
        mMessageRepository = new MessageRepositoryImpl(getApplication(), mTopic);
        mAllMessages = mMessageRepository.getMessageByTopic(mTopic);
    }

    public String getContactTopic() {
        return mTopic;
    }

    public LiveData<List<Message>> getAllMessages() { return mAllMessages; }

    public void sendMessage(Message messageToSend) {
        mMessageRepository.insert(messageToSend);
    }

    public LiveData<Message> getNewMessage() {
        return mMessageRepository.getIncomingMessage();
    }

    public void deleteTopicMessages(String topic) { mMessageRepository.deleteTopicMessages(topic);}

    public void publishMessage(String message) {
        mMessageRepository.publishMessage(mTopic, message);
    }
}
