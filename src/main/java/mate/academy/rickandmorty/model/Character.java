package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Table(name = "characters")
@Data
@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long externalId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String gender;

    @Getter
    public enum SpecificationKey {
        NAME("name");
        private final String value;

        SpecificationKey(String value) {
            this.value = value;
        }
    }
}
