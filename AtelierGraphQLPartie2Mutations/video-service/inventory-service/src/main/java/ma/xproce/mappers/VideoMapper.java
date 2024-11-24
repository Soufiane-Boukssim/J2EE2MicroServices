package ma.xproce.mappers;

import ma.xproce.dto.VideoDto;
import ma.xproce.entities.Video;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VideoMapper {
    private ModelMapper modelMapper=new ModelMapper();

    public Video toVideo(VideoDto videoDto){
        return modelMapper.map(videoDto, Video.class);
    }

    public VideoDto toVideoDto(Video video){
        return modelMapper.map(video, VideoDto.class);
    }
}
