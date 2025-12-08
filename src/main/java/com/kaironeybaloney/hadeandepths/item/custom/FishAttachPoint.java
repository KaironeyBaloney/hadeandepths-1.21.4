package com.kaironeybaloney.hadeandepths.item.custom;

import net.minecraft.util.StringRepresentable;

public enum FishAttachPoint implements StringRepresentable {
    BOTTOM_LEFT("bottom_left"),
    BOTTOM_RIGHT("bottom_right"),
    TOP_LEFT("top_left"),
    TOP_RIGHT("top_right"),
    TOP_MIDDLE("top_middle");

    private final String name;
    FishAttachPoint(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
