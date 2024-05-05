package dev.jakobt.code_effect;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;

public record RunCodeEnchantmentEffectType(Identifier hook) implements EnchantmentEntityEffectType {
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final MapCodec<RunCodeEnchantmentEffectType> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(Identifier.CODEC.fieldOf("hook").forGetter(RunCodeEnchantmentEffectType::hook))
                    .apply(instance, RunCodeEnchantmentEffectType::new)
    );
    @Override
    public MapCodec<RunCodeEnchantmentEffectType> getCodec() { return CODEC; }

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        CodeEffect.CodeConsumer consumer = CodeEffect.get(hook);
        if (consumer != null)
            consumer.run(world, level, context, user, pos);
        else
            LOGGER.error("Enchantment run_code effect failed for non-existent hook {}", this.hook);
    }

}
