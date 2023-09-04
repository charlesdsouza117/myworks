package cd.wiki.wikilearn.producer;

import cd.wiki.wikilearn.dto.ArticleDTO;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;

@Service
public class DataReader {

    private final KafkaMessageSender kafkaMessageSender;

    public DataReader(KafkaMessageSender kafkaMessageSender) {
        this.kafkaMessageSender = kafkaMessageSender;
    }

    public void read(String baseDir) {
        try {
            File dir = new File(baseDir);
            if (dir.isDirectory()) {
                for (File file : dir.listFiles()) {
                    System.out.println("Processing file: " + file.getName());
                    readFile(file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readFile(File file) throws Exception {
        try (Reader reader = Files.newBufferedReader(file.toPath())) {
            CSVParser parser = new CSVParserBuilder()
                    .withStrictQuotes(true).build();
            try (CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).build()) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    if (line.length == 6 && StringUtils.isNotBlank(line[5])) {
                        kafkaMessageSender.sendMessage(ArticleDTO.builder().url(line[3]).text(line[5]).build());
                    }
                }
            }
        }
    }
}
