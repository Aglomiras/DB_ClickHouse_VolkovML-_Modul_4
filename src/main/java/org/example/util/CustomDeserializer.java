package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomDeserializer implements Deserializer<Measurement> {
    @Override
    public Measurement deserialize(String topic, byte[] data) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.readValue(data, Measurement.class);
        } catch (IOException exception) {
            String message = new String(data, StandardCharsets.UTF_8);
            return null;
        }
    }
}
