package com.rsabitov.testchatpos.UI.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rsabitov.testchatpos.Domain.model.Topic;
import com.rsabitov.testchatpos.R;
import com.rsabitov.testchatpos.UI.viewModels.ContactsViewModel;
import com.rsabitov.testchatpos.UI.viewModels.MessagesViewModel;
import com.rsabitov.testchatpos.UI.adapters.ContactsRecyclerViewAdapter;

import java.util.List;

public class ContactsFragment extends Fragment implements ContactsRecyclerViewAdapter.OnViewListener {

    private ContactsViewModel mContactsViewModel;
    private MessagesViewModel mMessagesViewModel;
    private ContactsRecyclerViewAdapter mAdapter;
    private List<Topic> mTopicNames;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contacts_fragment, container, false);
        mContactsViewModel = new ViewModelProvider(requireActivity()).get(ContactsViewModel.class);
        mMessagesViewModel = new ViewModelProvider(requireActivity()).get(MessagesViewModel.class);
        initRecyclerView(view);
        mContactsViewModel.getAllTopics().observe(getViewLifecycleOwner(), topicList -> {
            mAdapter.setContactsList(topicList);
            mTopicNames = topicList;
        });
        final FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.new_contact));

        mContactsViewModel.getMessageFromThatTopic().observe(getActivity(), message -> {
            Toast.makeText(getContext(),  "'" + message.message + "'" + " from " + message.topic, Toast.LENGTH_SHORT).show();
            mContactsViewModel.insert(new Topic(message.topic));
            mMessagesViewModel.setContactTopic(message.topic);
            mMessagesViewModel.sendMessage(message);
        });

        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.contacts_recycler);
        mAdapter = new ContactsRecyclerViewAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    @Override
    public void onViewClick(int position) {
        mMessagesViewModel.setContactTopic(mTopicNames.get(position).name);
        Navigation.findNavController(getView()).navigate(R.id.messages);
    }

    private void deleteTopic(Topic topic) {
        mContactsViewModel.deleteTopic(topic);
        mAdapter.notifyDataSetChanged();
        mMessagesViewModel.deleteTopicMessages(topic.name);
    }

    private ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            deleteTopic(mTopicNames.get(viewHolder.getAdapterPosition()));
        }
    };

}
