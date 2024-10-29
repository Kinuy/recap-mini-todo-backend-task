package de.neuefische.todobackend.chatgpt;

import java.util.List;

public record ChatGptResponse(
        List<ChatGptChoice> choices
) {
}
