package com.rsabitov.testchatpos.fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.rsabitov.testchatpos.DB.Message;
import com.rsabitov.testchatpos.repository.MessageRepository;
import com.rsabitov.testchatpos.repository.MessageRepositoryImpl;

import java.util.List;

public class MessagesViewModel extends AndroidViewModel {
    private MessageRepository mMessageRepository;
    private LiveData<List<Message>> mAllMessages;
    private int mContactId;

    public MessagesViewModel(@NonNull Application application) {
        super(application);
    }

    void setContactId(int selectedContactId) {
        mContactId = selectedContactId;
        mMessageRepository = new MessageRepositoryImpl(getApplication(), mContactId);
        mAllMessages = mMessageRepository.getMessageById(mContactId);
    }

    int getContactId() {
        return mContactId;
    }

    LiveData<List<Message>> getAllMessages() { return mAllMessages; }

    void sendMessage(Message messageToSend) {
        mMessageRepository.insert(messageToSend);
    }
}
