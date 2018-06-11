package academy.team8.com.footballfanlocator.interfaces;

import java.util.List;

import academy.team8.com.footballfanlocator.model.ChatMessage;

public interface HolywarView {

    void onChatUpdate(List<ChatMessage> location);

}
