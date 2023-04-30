package org.example.Controller;

import lombok.RequiredArgsConstructor;
import org.example.Produser.TopicProduser;
import org.example.util.Measurement;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(value = "/kafka")
@RestController
public class TopicController {
    private final TopicProduser topicProduser;

    @PostMapping("/data")
    public Measurement sendMea(@RequestBody Measurement measurement) {
        topicProduser.sendMea(measurement);
        return measurement;
    }
}
