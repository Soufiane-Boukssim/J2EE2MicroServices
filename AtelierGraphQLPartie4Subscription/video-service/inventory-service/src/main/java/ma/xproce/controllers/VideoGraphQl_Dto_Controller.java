package ma.xproce.controllers;

import ma.xproce.dto.CreatorDto;
import ma.xproce.dto.VideoDto;
import ma.xproce.entities.Creator;
import ma.xproce.entities.CreatorRequest;
import ma.xproce.entities.Video;
import ma.xproce.mappers.CreatorMapper;
import ma.xproce.mappers.VideoMapper;
import ma.xproce.repositories.CreatorRepository;
import ma.xproce.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQl_Dto_Controller {
    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;
    private VideoMapper videoMapper;
    private CreatorMapper creatorMapper;


    VideoGraphQl_Dto_Controller(CreatorRepository creatorRepository, VideoRepository videoRepository, VideoMapper videoMapper, CreatorMapper creatorMapper){
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
        this.creatorMapper = creatorMapper;
    }

    @MutationMapping
    public CreatorDto saveCreator(@Argument CreatorDto creatorRequest){
        Creator creator=creatorMapper.toCreator(creatorRequest);
        creator= Creator.builder().email(creator.getEmail()).name(creator.getName()).build();
        creator= creatorRepository.save(creator);
        CreatorDto creatorDto=creatorMapper.toCreatorDto(creator);
        return creatorDto;
    }


    @MutationMapping
    public VideoDto saveVideo(@Argument VideoDto videoRequest){
        // 1. Convertir VideoDto en Video
        Video videoToSave = videoMapper.toVideo(videoRequest);
        // 2. Gérer le creator
        Creator creator = creatorRepository.findByEmail(videoToSave.getCreator().getEmail())
                .orElseGet(() -> {
                    Creator newCreator = Creator.builder()
                            .name(videoToSave.getCreator().getName())
                            .email(videoToSave.getCreator().getEmail())
                            .build();
                    return creatorRepository.save(newCreator);
                });
        // 3. Créer la vidéo avec tous ses attributs
        Video video = Video.builder()
                .name(videoToSave.getName())
                .url(videoToSave.getUrl())
                .description(videoToSave.getDescription())
                .datePublication(videoToSave.getDatePublication())
                .creator(creator)
                .build();
        // 4. Sauvegarder et convertir en DTO
        Video savedVideo = videoRepository.save(video);
        return videoMapper.toVideoDto(savedVideo);
    }

    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        // Simulation de délai
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    // Création d'un nouveau créateur et enregistrement
                    CreatorRequest creatorRequest = CreatorRequest.builder()
                            .name("x" + new Random().nextInt())
                            .email("x@gmail.com")
                            .build();
                    Creator creator = creatorRepository.save(Creator.builder()
                            .name(creatorRequest.getName())
                            .email(creatorRequest.getEmail())
                            .build());

                    // Recherche de la vidéo et mise à jour du créateur
                    Video video = videoRepository.findById(1L).orElseThrow();
                    video.setCreator(creator);
                    videoRepository.save(video);

                    return video;
                })
        );
    }

}