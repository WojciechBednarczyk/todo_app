package io.github.WojciechBednarczyk.todo_app.model;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Embeddable
class Audit {
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    @PrePersist
    void prePersist(){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate(){
        updatedOn = LocalDateTime.now();
    }
}
