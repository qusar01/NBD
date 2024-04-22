package Mapping;

import java.util.UUID;

public class UniqueIdMgd {
    private final UUID id;
    UniqueIdMgd(UUID id) {
        this.id = id;
    }
    UniqueIdMgd() {
        this.id = UUID.randomUUID();
    }
    public UUID getUUID() {
        return id;
    }
}