package net.minecraft.entity.ai;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class EntityAIBase {
    private int mutexBits;

    public abstract boolean shouldExecute();

    public boolean continueExecuting() {
        return this.shouldExecute();
    }

    public boolean isInterruptible() {
        return true;
    }

    public void startExecuting() {
    }

    public void resetTask() {
    }

    public void updateTask() {
    }

}
