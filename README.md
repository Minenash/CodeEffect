### For Users
This mod doesn't do anything on its own. This library allows mods to call code in data-driven enchantments.

### For Devs
**Registering your code:**
```java
CodeEffect.register(HOOK_ID, (world, level, context, user, pos) -> {
    // Code Here
});
```
or
```java
CodeEffect.register(HOOK_ID, this::stuff);
// ...
public void stuff(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
    // Code Here
}
```

**Using it:**
```json
"effect": {
     "type": "code_effect:run_code",
     "hook": "HOOK_ID"
}
```

Full Enchantment example:  
*Sharpness but modified to run the code every tick*
```json
{
  "anvil_cost": 1,
  "description": {
    "translate": "enchantment.minecraft.sharpness"
  },
  "effects": {
    "minecraft:tick": [
      {
        "effect": {
          "type": "code_effect:run_code",
          "hook": "HOOK_ID"
        }
      }
    ]
  },
  "exclusive_set": "#minecraft:exclusive_set/damage",
  "max_cost": {
    "base": 21,
    "per_level_above_first": 11
  },
  "max_level": 5,
  "min_cost": {
    "base": 1,
    "per_level_above_first": 11
  },
  "primary_items": "#minecraft:enchantable/sword",
  "slots": [
    "mainhand"
  ],
  "supported_items": "#minecraft:enchantable/weapon",
  "weight": 10
}
```