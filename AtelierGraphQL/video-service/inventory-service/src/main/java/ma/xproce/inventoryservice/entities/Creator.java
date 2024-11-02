package ma.xproce.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity @Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Creator {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    //cascade = CascadeType.ALL: Si un creator est supprimée les videos associées seront supprimées aussi ....
    //orphanRemoval = true: Si un video n'est pas associée à aucun creator il sera supprimé de la base de données.
    private List<Video> videos;

}
