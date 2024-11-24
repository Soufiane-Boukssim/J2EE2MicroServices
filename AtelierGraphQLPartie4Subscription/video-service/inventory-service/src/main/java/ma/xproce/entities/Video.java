package ma.xproce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Video {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String url;
    private String description;
    private String datePublication;
    @ManyToOne
    private Creator creator;
}
