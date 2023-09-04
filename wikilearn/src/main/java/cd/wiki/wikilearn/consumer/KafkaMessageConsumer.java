package cd.wiki.wikilearn.consumer;

import cd.wiki.wikilearn.dao.WikiRepository;
import cd.wiki.wikilearn.dto.ArticleDTO;
import cd.wiki.wikilearn.producer.KafkaMessageSender;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KafkaMessageConsumer {

    private final WikiRepository wikiRepository;

    public KafkaMessageConsumer(WikiRepository wikiRepository) {
        this.wikiRepository = wikiRepository;
    }

    @KafkaListener(topics = "wiki", groupId = "wiki_01")
    public void listen(ArticleDTO message) {
        wikiRepository.save(message);
    }
}
