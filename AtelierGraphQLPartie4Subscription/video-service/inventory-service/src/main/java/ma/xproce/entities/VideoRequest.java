package ma.xproce.entities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VideoRequest {
    private String name;
    private String url;
    private String description;
    private String datePublication;
    private Creator creator;

}
