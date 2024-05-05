package dev.jakobt.code_effect;

import com.mojang.datafixers.util.Either;
import net.fabricmc.api.ModInitializer;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.DamageSourcePropertiesLootCondition;
import net.minecraft.predicate.TagPredicate;
import net.minecraft.predicate.entity.DamageSourcePredicate;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;

public class CodeEffect implements ModInitializer {

    private static final Map<Identifier, CodeConsumer> registry = new HashMap<>();
    public static void register(Identifier hook, CodeConsumer code) {
        registry.put(hook, code);
    }
    public static CodeConsumer get(Identifier hook) {
       return registry.get(hook);
    }

    @FunctionalInterface
    public interface CodeConsumer {
        void run(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos);
    }

    @Override
    public void onInitialize() {
        Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, new Identifier("code_effect", "run_code"), RunCodeEnchantmentEffectType.CODEC);
    }

}
