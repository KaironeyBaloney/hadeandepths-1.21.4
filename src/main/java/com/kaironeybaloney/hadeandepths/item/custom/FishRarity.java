package com.kaironeybaloney.hadeandepths.item.custom;

import net.minecraft.util.StringRepresentable;
import net.neoforged.fml.common.asm.enumextension.IExtensibleEnum;
import org.w3c.dom.Text;

import java.awt.*;

public enum FishRarity implements StringRepresentable {
    COMMON("common"),
    UNCOMMON("uncommon"),
    RARE("rare"),
    LEGENDARY("legendary"),
    IMPOSSIBLE("impossible");

    private final String name;
    FishRarity(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
