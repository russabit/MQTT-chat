package com.rsabitov.testchatpos.UI.ViewModels;

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
    private int mContactId;

    public MessagesViewModel(@NonNull Application application) {
        super(application);
    }

    public void setContactId(int selectedContactId) {
        mContactId = selectedContactId;
        mMessageRepository = new MessageRepositoryImpl(getApplication(), mContactId);
        mAllMessages = mMessageRepository.getMessageById(mContactId);
    }

    public int getContactId() {
        return mContactId;
    }

    public LiveData<List<Message>> getAllMessages() { return mAllMessages; }

    public void sendMessage(Message messageToSend) {
        mMessageRepository.insert(messageToSend);
    }
}
