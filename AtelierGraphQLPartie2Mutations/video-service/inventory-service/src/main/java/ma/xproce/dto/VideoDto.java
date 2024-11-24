package ma.xproce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VideoDto {
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private CreatorDto creator;// Assurez-vous que le nom est "creator" et non "creatorDto"

}
