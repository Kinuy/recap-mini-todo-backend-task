package de.neuefische.todobackend.chatgpt;

import java.util.List;

public record ChatGptRequest(
        String model,
        List<ChatGptMessage> messages,
        ChatGptFormat response_format
) {
}
