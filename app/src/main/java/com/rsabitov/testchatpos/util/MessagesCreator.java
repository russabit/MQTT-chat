package com.rsabitov.testchatpos.util;

import java.util.ArrayList;
import java.util.Arrays;

public class MessagesCreator {
    public static ArrayList<String> getMessagesList() {
        ArrayList<String> listOfMessages = new ArrayList<>();

        {
            listOfMessages.addAll(Arrays.asList("Hi", "I just wanted to ask", "how are you?"));

            return listOfMessages;
        }
    }
}

