package com.ecole221.microsdervices.course.first.service.config;

import com.ecole221.microsdervices.course.common.service.event.PersonneEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Producer {

    @Value("${kafka.bootstrap-servers}")
    private String server;

    @Bean
    public ProducerFactory<String, PersonneEvent> producerFactory() {
        Map<String, Object> config
                = new HashMap<>();

        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                server);

        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);

        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }



    @Bean
    public KafkaTemplate<String, PersonneEvent> personnekafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
