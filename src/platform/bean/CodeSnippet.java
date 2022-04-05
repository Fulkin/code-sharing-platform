package platform.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fulkin
 * Created on 27.02.2022
 */

@Entity
@Table(name = "code")
@Getter
@NoArgsConstructor
public class CodeSnippet {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "code_snippet", columnDefinition = "TEXT")
    private String code;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "views")
    private int views;

    @Column(name = "time_restrictions")
    private boolean timeRestrictions;

    @Column(name = "views_restrictions")
    private boolean viewsRestrictions;

    public CodeSnippet(String code, LocalDateTime time, int views) {
        this.code = code;
        this.time = time;
        this.views = views;
        this.timeRestrictions = false;
        this.viewsRestrictions = false;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTimeRestrictions(boolean timeRestrictions) {
        this.timeRestrictions = timeRestrictions;
    }

    public void setViewsRestrictions(boolean viewsRestrictions) {
        this.viewsRestrictions = viewsRestrictions;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
