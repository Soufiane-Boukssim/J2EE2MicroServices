package ma.xproce.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Creator {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "creator")
    private List<Video> videos;
}
