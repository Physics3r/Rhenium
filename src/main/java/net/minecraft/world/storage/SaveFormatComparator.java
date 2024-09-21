package net.minecraft.world.storage;

import lombok.Getter;
import net.minecraft.world.WorldSettings;

public class SaveFormatComparator implements Comparable<SaveFormatComparator> {
    @Getter
    private final String fileName;
    @Getter
    private final String displayName;
    @Getter
    private final long lastTimePlayed;
    @Getter
    private final long sizeOnDisk;
    private final boolean requiresConversion;
    private final WorldSettings.GameType theEnumGameType;
    private final boolean hardcore;
    private final boolean cheatsEnabled;

    public SaveFormatComparator(String fileNameIn, String displayNameIn, long lastTimePlayedIn, long sizeOnDiskIn, WorldSettings.GameType theEnumGameTypeIn, boolean requiresConversionIn, boolean hardcoreIn, boolean cheatsEnabledIn) {
        this.fileName = fileNameIn;
        this.displayName = displayNameIn;
        this.lastTimePlayed = lastTimePlayedIn;
        this.sizeOnDisk = sizeOnDiskIn;
        this.theEnumGameType = theEnumGameTypeIn;
        this.requiresConversion = requiresConversionIn;
        this.hardcore = hardcoreIn;
        this.cheatsEnabled = cheatsEnabledIn;
    }

    public boolean requiresConversion() {
        return this.requiresConversion;
    }

    public int compareTo(SaveFormatComparator p_compareTo_1_) {
        return this.lastTimePlayed < p_compareTo_1_.lastTimePlayed ? 1 : (this.lastTimePlayed > p_compareTo_1_.lastTimePlayed ? -1 : this.fileName.compareTo(p_compareTo_1_.fileName));
    }

    public WorldSettings.GameType getEnumGameType() {
        return this.theEnumGameType;
    }

    public boolean isHardcoreModeEnabled() {
        return this.hardcore;
    }

    public boolean getCheatsEnabled() {
        return this.cheatsEnabled;
    }
}
