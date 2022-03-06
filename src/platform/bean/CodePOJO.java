package platform.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;

/**
 * @author Fulkin
 * Created on 02.03.2022
 */

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CodePOJO {

    @JsonIgnore
    private UUID id;

    private String code;

    private String date;

    private long time;

    private int views;

    @JsonIgnore
    private boolean timeRestrictions;

    @JsonIgnore
    private boolean viewsRestrictions;
}
