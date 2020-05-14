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

    private MutableLiveData<String> selectedContact = new MutableLiveData<>();

    public MessagesViewModel(@NonNull Application application) {
        super(application);
        mMessageRepository = new MessageRepositoryImpl(application);
        mAllMessages = mMessageRepository.getAllMessages();
    }

    public MutableLiveData<String> getContact() {
        return selectedContact;
    }

    LiveData<List<Message>> getAllMessages() { return mAllMessages; }
}
