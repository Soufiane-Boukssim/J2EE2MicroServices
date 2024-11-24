package ma.xproce.entities;

import lombok.*;


@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class CreatorRequest {
    private String name;
    private String email;
}
