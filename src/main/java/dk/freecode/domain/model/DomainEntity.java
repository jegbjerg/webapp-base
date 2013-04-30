package dk.freecode.domain.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@Data
public abstract class DomainEntity {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private Long id;

    @Version
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private Integer version;
}
