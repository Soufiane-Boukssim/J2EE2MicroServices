package ma.xproce;

import ma.xproce.entities.Creator;
import ma.xproce.entities.Video;
import ma.xproce.repositories.CreatorRepository;
import ma.xproce.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CreatorRepository creatorRepository, VideoRepository videoRepository){
        return args -> {
            List<Creator> creators = List.of(
                    Creator.builder().name("name1").email("email1@gmail.com").build(),
                    Creator.builder().name("name2").email("email2@gmail.com").build(),
                    Creator.builder().name("name3").email("email3@gmail.com").build()
            );
            creatorRepository.saveAll(creators);
            List<Video> videos = List.of(
                    Video.builder().name("name1").url("url1").description("description1").datePublication("23/11/2024").creator(creators.get(0)).build(),
                    Video.builder().name("name2").url("url2").description("description2").datePublication("23/11/2024").creator(creators.get(0)).build(),
                    Video.builder().name("name3").url("url3").description("description3").datePublication("23/11/2024").creator(creators.get(1)).build(),
                    Video.builder().name("name4").url("url4").description("description4").datePublication("23/11/2024").creator(creators.get(1)).build(),
                    Video.builder().name("name5").url("url5").description("description5").datePublication("23/11/2024").creator(creators.get(2)).build(),
                    Video.builder().name("name6").url("url6").description("description6").datePublication("23/11/2024").creator(creators.get(2)).build()
            );
            videoRepository.saveAll(videos);
        };
    }

}
