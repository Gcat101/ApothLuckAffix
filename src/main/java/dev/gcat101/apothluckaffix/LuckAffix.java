package dev.gcat101.apothluckaffix;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.mojang.serialization.Codec;
import dev.shadowsoffire.apotheosis.adventure.AdventureModule;
import dev.shadowsoffire.apotheosis.adventure.affix.Affix;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixType;
import dev.shadowsoffire.apotheosis.adventure.loot.LootCategory;
import dev.shadowsoffire.apotheosis.adventure.loot.LootRarity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.item.ItemStack;

public class LuckAffix extends Affix {
    public static final Codec<LuckAffix> CODEC = Codec.unit(LuckAffix::new);

    public LuckAffix() {
        super(AffixType.ANCIENT);
    }

    @Override
    public void addModifiers(ItemStack stack, LootRarity rarity, float level, EquipmentSlot type, BiConsumer<Attribute, AttributeModifier> map) {
        LootCategory cat = LootCategory.forItem(stack);
        if (cat.isNone()) {
            AdventureModule.LOGGER.debug("Attempted to apply the attributes of affix {} on item {}, but it is not an affix-compatible item!", this.getId(), stack.getHoverName().getString());
            return;
        }
        for (EquipmentSlot slot : cat.getSlots()) {
            if (slot == type) {
                map.accept(Attributes.LUCK, new AttributeModifier("affix:luck", rarity.ordinal()+1, Operation.ADDITION));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, LootRarity rarity, float level, Consumer<Component> list) {}

    @Override
    public boolean canApplyTo(ItemStack stack, LootCategory cat, LootRarity rarity) {
        return !cat.isNone();
    }

    @Override
    public Codec<? extends Affix> getCodec() {
        return CODEC;
    }
}