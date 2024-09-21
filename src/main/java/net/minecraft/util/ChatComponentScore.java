package net.minecraft.util;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;

public class ChatComponentScore extends ChatComponentStyle {
    @Getter
    private final String name;
    @Getter
    private final String objective;
    @Setter
    private String value = "";

    public ChatComponentScore(String nameIn, String objectiveIn) {
        this.name = nameIn;
        this.objective = objectiveIn;
    }

    public String getUnformattedTextForChat() {
        MinecraftServer minecraftserver = MinecraftServer.getServer();

        if (minecraftserver != null && minecraftserver.isAnvilFileSet() && StringUtils.isNullOrEmpty(this.value)) {
            Scoreboard scoreboard = minecraftserver.worldServerForDimension(0).getScoreboard();
            ScoreObjective scoreobjective = scoreboard.getObjective(this.objective);

            if (scoreboard.entityHasObjective(this.name, scoreobjective)) {
                Score score = scoreboard.getValueFromObjective(this.name, scoreobjective);
                this.setValue(String.format("%d", new Object[]{score.getScorePoints()}));
            } else {
                this.value = "";
            }
        }

        return this.value;
    }

    public ChatComponentScore createCopy() {
        ChatComponentScore chatcomponentscore = new ChatComponentScore(this.name, this.objective);
        chatcomponentscore.setValue(this.value);
        chatcomponentscore.setChatStyle(this.getChatStyle().createShallowCopy());

        for (IChatComponent ichatcomponent : this.getSiblings()) {
            chatcomponentscore.appendSibling(ichatcomponent.createCopy());
        }

        return chatcomponentscore;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        } else if (!(p_equals_1_ instanceof ChatComponentScore)) {
            return false;
        } else {
            ChatComponentScore chatcomponentscore = (ChatComponentScore) p_equals_1_;
            return this.name.equals(chatcomponentscore.name) && this.objective.equals(chatcomponentscore.objective) && super.equals(p_equals_1_);
        }
    }

    public String toString() {
        return "ScoreComponent{name=\'" + this.name + '\'' + "objective=\'" + this.objective + '\'' + ", siblings=" + this.siblings + ", style=" + this.getChatStyle() + '}';
    }
}
