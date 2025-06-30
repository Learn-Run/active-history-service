package com.unionclass.activehistoryservice.common.config;

import com.unionclass.activehistoryservice.common.kafka.entity.event.CommentCreatedEvent;
import com.unionclass.activehistoryservice.common.kafka.entity.event.CommentDeletedEvent;
import com.unionclass.activehistoryservice.common.kafka.entity.event.PostCreatedEvent;
import com.unionclass.activehistoryservice.common.kafka.entity.event.ReviewCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    public Map<String, Object> consumerConfigs(Class<?> valueType) {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "active-history-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, valueType);

        return config;
    }

    @Bean
    public ConsumerFactory<String, CommentCreatedEvent> commentConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(CommentCreatedEvent.class),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CommentCreatedEvent.class, false)));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CommentCreatedEvent> commentCreatedEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, CommentCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(commentConsumerFactory());

        return factory;
    }

    @Bean
    public ConsumerFactory<String, ReviewCreatedEvent> reviewConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(ReviewCreatedEvent.class),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(ReviewCreatedEvent.class, false)));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ReviewCreatedEvent> reviewCreatedEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, ReviewCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(reviewConsumerFactory());

        return factory;
    }

    @Bean
    public ConsumerFactory<String, PostCreatedEvent> postConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(PostCreatedEvent.class),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(PostCreatedEvent.class, false)));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PostCreatedEvent> postCreatedEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, PostCreatedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(postConsumerFactory());

        return factory;
    }

    @Bean
    public ConsumerFactory<String, CommentDeletedEvent> commentDeletedConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(CommentDeletedEvent.class),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CommentDeletedEvent.class, false)));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CommentDeletedEvent> commentDeletedEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, CommentDeletedEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(commentDeletedConsumerFactory());

        return factory;
    }
}