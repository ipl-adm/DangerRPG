package mixac1.dangerrpg.item;

import java.util.HashMap;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class RPGToolMaterial
{
    public static HashMap<ToolMaterial, RPGToolMaterial> map = new HashMap<ToolMaterial, RPGToolMaterial>();

    public static final RPGToolMaterial WOOD            = new RPGToolMaterial("wood",           ToolMaterial.WOOD);
    public static final RPGToolMaterial STONE           = new RPGToolMaterial("stone",          ToolMaterial.STONE);
    public static final RPGToolMaterial IRON            = new RPGToolMaterial("iron",           ToolMaterial.IRON);
    public static final RPGToolMaterial GOLD            = new RPGToolMaterial("gold",           ToolMaterial.GOLD);
    public static final RPGToolMaterial DIAMOND         = new RPGToolMaterial("diamond",        ToolMaterial.EMERALD);
    public static final RPGToolMaterial OBSIDIAN        = new RPGToolMaterial("obsidian",       EnumHelper.addToolMaterial("OBSIDIAN",     3, 2000,  8.0F,  5.0F,  12));
    public static final RPGToolMaterial BEDROCK         = new RPGToolMaterial("bedrock",        EnumHelper.addToolMaterial("BEDROCK",      3, 4000,  12.0F, 11.0F, 14));
    public static final RPGToolMaterial BLACK_MATTER    = new RPGToolMaterial("black_matter",   EnumHelper.addToolMaterial("BLACK_MATTER", 3, 8000,  18.0F, 21.0F, 19));
    public static final RPGToolMaterial WHITE_MATTER    = new RPGToolMaterial("white_matter",   EnumHelper.addToolMaterial("WHITE_MATTER", 3, 10000, 24.0F, 36.0F, 22));

    static
    {
        WOOD.init();
        STONE.init();
        IRON.init();
        GOLD.init();
        DIAMOND.init();
        OBSIDIAN.init();
        BEDROCK.init();
        BLACK_MATTER.init();
        WHITE_MATTER.init();
    }

    public ToolMaterial material;
    public String name;

    public RPGToolMaterial(String name, ToolMaterial material)
    {
        this.name = name;
        this.material = material;
        map.put(material, this);
    }

    protected void init()
    {

    }

    public static RPGToolMaterial getRPGToolMaterial(ToolMaterial material)
    {
        return map.get(material);
    }
}
