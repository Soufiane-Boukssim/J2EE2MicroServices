package ma.xproce.inventoryservice.config;

import ma.xproce.inventoryservice.entities.Creator;
import ma.xproce.inventoryservice.entities.Video;
import ma.xproce.inventoryservice.repositories.CreatorRepository;
import ma.xproce.inventoryservice.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DatabaseConfig {
    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        return args -> {
            // Créer une liste de créateurs
            List<Creator> creators = List.of(
                    Creator.builder().name("John Doe").email("john.doe@example.com").build(),
                    Creator.builder().name("Jane Smith").email("jane.smith@example.com").build()
            );
            // Sauvegarder les créateurs dans la base de données
            creatorRepository.saveAll(creators);

            // Créer une liste de vidéos
            List<Video> videos = List.of(
                    Video.builder().name("Video 1").url("http://video1.com").description("Première vidéo").datePublication(LocalDate.now()).creator(creators.get(0)).build(),
                    Video.builder().name("Video 2").url("http://video2.com").description("Deuxième vidéo").datePublication(LocalDate.now()).creator(creators.get(1)).build()
            );
            // Sauvegarder les vidéos dans la base de données
            videoRepository.saveAll(videos);
        };
    }
}

