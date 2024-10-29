package de.neuefische.todobackend.todo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.todobackend.chatgpt.ChatGptFormat;
import de.neuefische.todobackend.chatgpt.ChatGptMessage;
import de.neuefische.todobackend.chatgpt.ChatGptRequest;
import de.neuefische.todobackend.chatgpt.ChatGptResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class ChatGptApiService {
    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    @Value("${app.chatgpt.api.token}")
    String token;

    public ChatGptApiService(RestClient.Builder builder, ObjectMapper objectMapper) {
        restClient = builder.build();
        this.objectMapper = objectMapper;
    }

    public String checkTaskSpelling(String task) throws JsonProcessingException {
        ChatGptResponse body = restClient.post()
                .uri("https://api.openai.com/v1/chat/completions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(new ChatGptRequest("gpt-4o-mini",
                        List.of(new ChatGptMessage("Return me the provided task without spelling mistakes as text: " + task , "user")),
                        new ChatGptFormat("text")))
                .retrieve()
                .body(ChatGptResponse.class);

        //System.out.println(body.choices().get(0).message().content());



        //RequestResponse response = objectMapper.readValue(body.choices().get(0).message().content(), RequestResponse.class);

        //System.out.println(response);

        return body.choices().get(0).message().content();
    }
}
