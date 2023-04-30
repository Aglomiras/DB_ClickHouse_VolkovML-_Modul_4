package org.example.util;

import org.apache.kafka.common.serialization.Serializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomSerializer implements Serializer<Measurement> {
    @Override
    public byte[] serialize(String s, Measurement commandDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (commandDto != null) {
            try {
                return objectMapper.writeValueAsBytes(commandDto);
            } catch (JsonProcessingException e) {
                return new byte[0];
            }
        }
        return new byte[0];
    }
}
