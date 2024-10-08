package net.minecraft.village;

import lombok.Getter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class MerchantRecipe {
    @Getter
    private ItemStack itemToBuy;
    @Getter
    private ItemStack secondItemToBuy;
    @Getter
    private ItemStack itemToSell;
    @Getter
    private int toolUses;
    @Getter
    private int maxTradeUses;
    private boolean rewardsExp;

    public MerchantRecipe(NBTTagCompound tagCompound) {
        this.readFromTags(tagCompound);
    }

    public MerchantRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell) {
        this(buy1, buy2, sell, 0, 7);
    }

    public MerchantRecipe(ItemStack buy1, ItemStack buy2, ItemStack sell, int toolUsesIn, int maxTradeUsesIn) {
        this.itemToBuy = buy1;
        this.secondItemToBuy = buy2;
        this.itemToSell = sell;
        this.toolUses = toolUsesIn;
        this.maxTradeUses = maxTradeUsesIn;
        this.rewardsExp = true;
    }

    public MerchantRecipe(ItemStack buy1, ItemStack sell) {
        this(buy1, null, sell);
    }

    public MerchantRecipe(ItemStack buy1, Item sellItem) {
        this(buy1, new ItemStack(sellItem));
    }

    public boolean hasSecondItemToBuy() {
        return this.secondItemToBuy != null;
    }

    public void incrementToolUses() {
        ++this.toolUses;
    }

    public void increaseMaxTradeUses(int increment) {
        this.maxTradeUses += increment;
    }

    public boolean isRecipeDisabled() {
        return this.toolUses >= this.maxTradeUses;
    }

    public void compensateToolUses() {
        this.toolUses = this.maxTradeUses;
    }

    public boolean getRewardsExp() {
        return this.rewardsExp;
    }

    public void readFromTags(NBTTagCompound tagCompound) {
        NBTTagCompound nbttagcompound = tagCompound.getCompoundTag("buy");
        this.itemToBuy = ItemStack.loadItemStackFromNBT(nbttagcompound);
        NBTTagCompound nbttagcompound1 = tagCompound.getCompoundTag("sell");
        this.itemToSell = ItemStack.loadItemStackFromNBT(nbttagcompound1);

        if (tagCompound.hasKey("buyB", 10)) {
            this.secondItemToBuy = ItemStack.loadItemStackFromNBT(tagCompound.getCompoundTag("buyB"));
        }

        if (tagCompound.hasKey("uses", 99)) {
            this.toolUses = tagCompound.getInteger("uses");
        }

        if (tagCompound.hasKey("maxUses", 99)) {
            this.maxTradeUses = tagCompound.getInteger("maxUses");
        } else {
            this.maxTradeUses = 7;
        }

        if (tagCompound.hasKey("rewardExp", 1)) {
            this.rewardsExp = tagCompound.getBoolean("rewardExp");
        } else {
            this.rewardsExp = true;
        }
    }

    public NBTTagCompound writeToTags() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        nbttagcompound.setTag("buy", this.itemToBuy.writeToNBT(new NBTTagCompound()));
        nbttagcompound.setTag("sell", this.itemToSell.writeToNBT(new NBTTagCompound()));

        if (this.secondItemToBuy != null) {
            nbttagcompound.setTag("buyB", this.secondItemToBuy.writeToNBT(new NBTTagCompound()));
        }

        nbttagcompound.setInteger("uses", this.toolUses);
        nbttagcompound.setInteger("maxUses", this.maxTradeUses);
        nbttagcompound.setBoolean("rewardExp", this.rewardsExp);
        return nbttagcompound;
    }
}
