package com.rasysbox.ws.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rasysbox.ws.utils.Constants;
import lombok.Builder;
import lombok.Data;

/**
 * The type Exception message.
 */
@Data
@Builder
public class ExceptionMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.TIMESTAMP_FORMAT)
    private String timestamp;
    private String message;
    private String path;
}
