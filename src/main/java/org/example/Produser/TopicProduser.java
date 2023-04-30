package org.example.Produser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.util.Measurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicProduser {
    @Value("{topic.name.produser}")
    private String topicName;
    private final KafkaTemplate<String, Measurement> kafkaTemplate;

    public void sendMea(Measurement measurement) {
        kafkaTemplate.send(topicName, measurement);
    }
}
