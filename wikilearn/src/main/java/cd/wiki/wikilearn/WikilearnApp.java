package cd.wiki.wikilearn;

import cd.wiki.wikilearn.dto.ArticleDTO;
import cd.wiki.wikilearn.producer.DataReader;
import cd.wiki.wikilearn.producer.KafkaMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("cd.wiki.wikilearn.dao")
public class WikilearnApp implements CommandLineRunner {

	@Autowired
	private DataReader dataReader;

	public static void main(String[] args) {
		SpringApplication.run(WikilearnApp.class, args);
	}

	@Override
	public void run(String... args) {
//		kafkaMessageSender.sendMessage(ArticleDTO.builder().text("test message 2").url("http://test/2").build());

		dataReader.read("/Users/charles.dsouza/dev/datasets/archive/articles/a.csv");


//		while(true) {
//			try {
//				Thread.sleep(10000);
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}
}
