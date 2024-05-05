package dev.jakobt.code_effect;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.entity.Entity;
import net.minecraft.registry.*;
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
