package ma.xproce.mappers;

import ma.xproce.dto.CreatorDto;
import ma.xproce.entities.Creator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapper {
    private ModelMapper modelMapper=new ModelMapper();

    public Creator toCreator(CreatorDto creatorDto) {
        return modelMapper.map(creatorDto, Creator.class);
    }

    public CreatorDto toCreatorDto(Creator creator) {
        return modelMapper.map(creator, CreatorDto.class);
    }
}
