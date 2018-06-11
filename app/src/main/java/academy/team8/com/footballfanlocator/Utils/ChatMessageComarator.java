package academy.team8.com.footballfanlocator.Utils;

import java.util.Comparator;

import academy.team8.com.footballfanlocator.model.ChatMessage;

public class ChatMessageComarator {
    public static Comparator<ChatMessage> byDate()
    {
        Comparator<ChatMessage> comp = new Comparator<ChatMessage>(){
            @Override
            public int compare(ChatMessage msg1, ChatMessage msg2)
            {
                return msg1.getDate().compareTo(msg2.getDate());
            }
        };
        return comp;
    }
}
