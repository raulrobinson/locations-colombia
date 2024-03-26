package com.rasysbox.ws.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * The type Utilities.
 */
@Slf4j
@Component
public class Utilities {

    private Utilities() { }

    /**
     * Gets timestamp value.
     *
     * @return the timestamp value
     */
    public static String getTimestampValue() {
        var zoneIdCo = ZoneId.of("America/Bogota");
        var now = ZonedDateTime.now(zoneIdCo);
        var dtf = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return now.truncatedTo(ChronoUnit.MILLIS).format(dtf);
    }

    /**
     * Latency calculator long.
     *
     * @param startTime the start time
     * @param stopTime  the stop time
     * @return the long
     */
    public static long latencyCalculator(long startTime, long stopTime) {
        return stopTime - startTime;
    }

    /**
     * Convert str to json string.
     *
     * @param input the input
     * @return the string
     */
    public static String convertStrToJson(String input) {
        String[] parts = input.split(" ");
        var mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                var key = keyValue[0];
                var value = keyValue[1];
                json.put(key, value);
            }
        }
        return json.toString();
    }
}
