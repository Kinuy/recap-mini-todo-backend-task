package de.neuefische.todobackend.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestResponse {
    private String phrase;
    private String correction;
}
