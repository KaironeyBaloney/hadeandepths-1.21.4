package com.kaironeybaloney.hadeandepths.event;

import com.kaironeybaloney.hadeandepths.item.custom.TidalMorningStarItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class CombatEvents {

    @SubscribeEvent
    public void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity().level().isClientSide()) return;
        if (event.getSource().getEntity() instanceof LivingEntity attacker) {
            ItemStack weapon = attacker.getMainHandItem();

            if (weapon.getItem() instanceof TidalMorningStarItem piercingWeapon) {
                float originalDamage = event.getAmount();

                LivingEntity target = event.getEntity();
                float armor = target.getArmorValue();

                int piercePercent = piercingWeapon.getArmorPiercePercentage(weapon) / 100;
                float armorIgnored = armor * piercePercent;

                float armorContribution = armorIgnored * 0.04f;

                float finalDamage = originalDamage * (1 + armorContribution);

                event.setAmount(finalDamage);
            }
        }
    }
}
