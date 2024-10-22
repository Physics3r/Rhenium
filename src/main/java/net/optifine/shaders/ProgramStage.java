package net.optifine.shaders;

import lombok.Getter;

@Getter
public enum ProgramStage {
    NONE(""),
    SHADOW("shadow"),
    GBUFFERS("gbuffers"),
    DEFERRED("deferred"),
    COMPOSITE("composite");

    private String name;

    private ProgramStage(String name) {
        this.name = name;
    }

}
