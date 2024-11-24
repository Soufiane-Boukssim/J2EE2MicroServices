package ma.xproce.controllers;

import ma.xproce.entities.Creator;
import ma.xproce.entities.CreatorRequest;
import ma.xproce.entities.Video;
import ma.xproce.entities.VideoRequest;
import ma.xproce.repositories.CreatorRepository;
import ma.xproce.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideoGraphQlController {
    private CreatorRepository creatorRepository;
    private VideoRepository videoRepository;


    VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository){
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
    }

    @QueryMapping
    public List<Video> videoList(){
        List<Video> videos= videoRepository.findAll();
        if(videos.isEmpty()){
            throw new RuntimeException("No videos found in the database");
        }
        return videos;
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Creator %s not found",id)));
    }

//    @MutationMapping
//    public Creator saveCreator(@Argument CreatorRequest creatorRequest){
//        Creator creator= Creator.builder().email(creatorRequest.getEmail()).name(creatorRequest.getName()).build();
//        return creatorRepository.save(creator) ;
//    }

//    @MutationMapping
//    public Video saveVideo(@Argument VideoRequest videoRequest){
//        Creator creator = creatorRepository.findByEmail(videoRequest.getCreator().getEmail())
//                .orElseGet(() -> {
//                    // Si le creator n'existe pas, on le crée
//                    Creator newCreator = Creator.builder()
//                            .name(videoRequest.getCreator().getName())
//                            .email(videoRequest.getCreator().getEmail())
//                            .build();
//                    return creatorRepository.save(newCreator);
//                });
//
//        Video video=Video.builder().name(videoRequest.getName()).url(videoRequest.getUrl()).description(videoRequest.getDescription()).datePublication(videoRequest.getDatePublication()).creator(creator).build();
//        return videoRepository.save(video) ;
//    }

}
