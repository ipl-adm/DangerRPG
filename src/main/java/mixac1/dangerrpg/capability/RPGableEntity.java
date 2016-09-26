package mixac1.dangerrpg.capability;

import mixac1.dangerrpg.api.entity.IRPGEntity;
import mixac1.dangerrpg.api.entity.LvlEAProvider.DafailtLvlEAProvider;
import mixac1.dangerrpg.api.event.RegEAEvent;
import mixac1.dangerrpg.capability.data.RPGEntityRegister.RPGEntityData;
import mixac1.dangerrpg.capability.ea.EntityAttributes;
import mixac1.dangerrpg.capability.ea.PlayerAttributes;
import mixac1.dangerrpg.init.RPGCapability;
import mixac1.dangerrpg.init.RPGConfig;
import mixac1.dangerrpg.util.IMultiplier;
import mixac1.dangerrpg.util.IMultiplier.IMulConfigurable;
import mixac1.dangerrpg.util.IMultiplier.MultiplierAdd;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public abstract class RPGableEntity
{
    public static boolean isRPGable(EntityLivingBase entity)
    {
        return RPGCapability.rpgEntityRegistr.isActivated(entity);
    }

    public static boolean registerEntity(Class entityClass)
    {
        if (EntityLivingBase.class.isAssignableFrom(entityClass)) {
            if (RPGCapability.rpgEntityRegistr.containsKey(entityClass)) {
                return true;
            }

            IRPGEntity iRPG = EntityPlayer.class.isAssignableFrom(entityClass) ? IRPGEntity.DEFAULT_PLAYER:
                              EntityMob.class.isAssignableFrom(entityClass)    ? IRPGEntity.DEFAULT_MOB:
                                                                                 IRPGEntity.DEFAULT_LIVING;

            RPGCapability.rpgEntityRegistr.put(entityClass, new RPGEntityData(iRPG, false));
            return true;
        }
        return false;
    }

    public static void registerEntityDefault(Class<? extends EntityLivingBase> entityClass, RPGEntityData map)
    {
        map.addEntityAttribute(EntityAttributes.LVL, 1);
        MinecraftForge.EVENT_BUS.post(new RegEAEvent.DefaultEAEvent(entityClass, map));
    }

    public static void registerEntityLiving(Class<? extends EntityLiving> entityClass, RPGEntityData map)
    {
        map.addEntityAttribute(EntityAttributes.HEALTH, 0f);
        MinecraftForge.EVENT_BUS.post(new RegEAEvent.EntytyLivingEAEvent(entityClass, map));
    }

    public static void registerEntityMob(Class<? extends EntityMob> entityClass, RPGEntityData map)
    {
        map.addEntityAttribute(EntityAttributes.MELEE_DAMAGE, 0f);
        MinecraftForge.EVENT_BUS.post(new RegEAEvent.EntytyMobEAEvent(entityClass, map));
    }

    public static void registerEntityPlayer(Class<? extends EntityPlayer> entityClass, RPGEntityData map)
    {
        IMulConfigurable ADD_1     = IMultiplier.ADD_1;
        IMulConfigurable ADD_2     = new MultiplierAdd(2F);
        IMulConfigurable ADD_0d001 = new MultiplierAdd(0.001F);
        IMulConfigurable ADD_0d01  = new MultiplierAdd(0.01F);
        IMulConfigurable ADD_0d014 = new MultiplierAdd(0.014F);
        IMulConfigurable ADD_0d025 = new MultiplierAdd(0.025F);
        IMulConfigurable ADD_0d2   = new MultiplierAdd(0.2F);

        float q0 = RPGConfig.entityConfig.playerStartManaValue;
        float q1 = RPGConfig.entityConfig.playerStartManaRegenValue;

        map.addLvlableEntityAttribute(PlayerAttributes.HEALTH,        0f,  new DafailtLvlEAProvider(2, 1000, ADD_2));
        map.addLvlableEntityAttribute(PlayerAttributes.MANA,          q0,  new DafailtLvlEAProvider(2, 1000, ADD_2));
        map.addLvlableEntityAttribute(PlayerAttributes.STRENGTH,      0f,  new DafailtLvlEAProvider(2, 1000, ADD_1));
        map.addLvlableEntityAttribute(PlayerAttributes.AGILITY,       0f,  new DafailtLvlEAProvider(2, 1000, ADD_1));
        map.addLvlableEntityAttribute(PlayerAttributes.INTELLIGENCE,  0f,  new DafailtLvlEAProvider(2, 1000, ADD_1));
        map.addLvlableEntityAttribute(PlayerAttributes.EFFICIENCY,    0f,  new DafailtLvlEAProvider(2, 1000, ADD_2));
        map.addLvlableEntityAttribute(PlayerAttributes.MANA_REGEN,    q1,  new DafailtLvlEAProvider(2, 1000, ADD_0d2));
        map.addLvlableEntityAttribute(PlayerAttributes.HEALTH_REGEN,  0f,  new DafailtLvlEAProvider(2, 1000, ADD_0d2));

        map.addLvlableEntityAttribute(PlayerAttributes.MOVE_SPEED,    0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d001));
        map.addLvlableEntityAttribute(PlayerAttributes.SNEAK_SPEED,   0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d001));
        map.addLvlableEntityAttribute(PlayerAttributes.FLY_SPEED,     0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d001));
        map.addLvlableEntityAttribute(PlayerAttributes.SWIM_SPEED,    0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d001));
        map.addLvlableEntityAttribute(PlayerAttributes.JUMP_HEIGHT,   0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d014));
        map.addLvlableEntityAttribute(PlayerAttributes.JUMP_RANGE,    0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d001));

        map.addLvlableEntityAttribute(PlayerAttributes.PHISIC_RESIST, 0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d01));
        map.addLvlableEntityAttribute(PlayerAttributes.MAGIC_RESIST,  0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d01));
        map.addLvlableEntityAttribute(PlayerAttributes.FALL_RESIST,   0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d025));
        map.addLvlableEntityAttribute(PlayerAttributes.FIRE_RESIST,   0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d025));
        map.addLvlableEntityAttribute(PlayerAttributes.LAVA_RESIST,   0f,  new DafailtLvlEAProvider(2, 20,   ADD_0d025));

        map.addEntityAttribute(PlayerAttributes.CURR_MANA, 0f);
        map.addEntityAttribute(PlayerAttributes.SPEED_COUNTER, 0f);

        MinecraftForge.EVENT_BUS.post(new RegEAEvent.PlayerEAEvent(entityClass, map));
    }
}
