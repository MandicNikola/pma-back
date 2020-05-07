package rs.ftn.pma.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ftn.pma.enums.Sex;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String height;

    private String weight;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private int waterReminder;

    @OneToOne(mappedBy = "settings", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private User user;

}
