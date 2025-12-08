package com.kaironeybaloney.hadeandepths.client.renderer.armor;

import com.kaironeybaloney.hadeandepths.HadeanDepths;
import com.kaironeybaloney.hadeandepths.item.custom.TidalArmorItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class TidalArmorRenderer extends GeoArmorRenderer<TidalArmorItem> {
    public TidalArmorRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(HadeanDepths.MODID, "armor/tidal_armor")));
    }
}
