package com.kaironeybaloney.hadeandepths.item.custom;

import net.minecraft.util.StringRepresentable;

public enum FishType implements StringRepresentable {
    ANCIENT("ancient"),
    CAVERNOUS("cavernous"),
    OCEANIC("oceanic"),
    ABYSSAL("abyssal"),
    INLAND("inland"),
    FABLED("fabled"),
    GLACIAL("glacial"),
    MAGMATIC("magmatic"),
    UNEXPLAINED("unexplained");

    private final String name;
    FishType(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
