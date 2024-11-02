package ma.xproce.inventoryservice.web;

import ma.xproce.inventoryservice.entities.Creator;
import ma.xproce.inventoryservice.entities.Video;
import ma.xproce.inventoryservice.repositories.CreatorRepository;
import ma.xproce.inventoryservice.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
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
        return videoRepository.findAll();
    }

    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Creator %s not found",id)));
    }
}