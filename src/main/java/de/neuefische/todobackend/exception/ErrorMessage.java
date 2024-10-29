package de.neuefische.todobackend.exception;

import java.time.ZonedDateTime;

public record ErrorMessage(
        String content
        //ZonedDateTime time
) {
}
