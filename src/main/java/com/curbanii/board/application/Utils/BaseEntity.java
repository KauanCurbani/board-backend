package com.curbanii.board.application.Utils;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


@MappedSuperclass
@SuperBuilder
@Data
public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    @Id
    @GeneratedValue
    protected UUID id;


    @Getter
    @Version
    @ColumnDefault("0")
    private Long version;


    @Getter
    @Column
    private final LocalDateTime insertTimeStamp = LocalDateTime.now();
    @Getter
    private LocalDateTime updateTimeStamp;
    @Setter
    @Column
    private LocalDateTime deleteTimeStamp;


    public BaseEntity() {
    }

    public BaseEntity(UUID id) {
        this.id = id;
    }

    @PreUpdate
    public void preUpdate() {
        updateTimeStamp = LocalDateTime.now();
    }

    public void delete() {
        this.deleteTimeStamp = LocalDateTime.now();
    }

    public boolean isDeleted() {
        return deleteTimeStamp != null;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }


    public String toString() {
        return getClass().getName() + ": " + id;
    }
}