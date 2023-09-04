package cd.wiki.wikilearn.producer;

import cd.wiki.wikilearn.dto.ArticleDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessageSender {

    private final String topicName = "wiki";

    private final KafkaTemplate<String, ArticleDTO> kafkaTemplate;

    public KafkaMessageSender(KafkaTemplate<String, ArticleDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ArticleDTO msg) {
        kafkaTemplate.send(topicName, msg);
    }

    public void sendMessageWithCallback(ArticleDTO msg) {
        CompletableFuture<SendResult<String, ArticleDTO>> future = kafkaTemplate.send(topicName, msg);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        msg + "] due to : " + ex.getMessage());
            }
        });
    }
}
