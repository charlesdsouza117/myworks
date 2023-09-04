package cd.wiki.wikilearn.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
@ToString
@Document(collection = "articles")
public class ArticleDTO {

    private String url;
    private String text;
}
