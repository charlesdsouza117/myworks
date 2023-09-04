package cd.wiki.wikilearn.dao;

import cd.wiki.wikilearn.dto.ArticleDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WikiRepository extends MongoRepository<ArticleDTO, String> {

}
