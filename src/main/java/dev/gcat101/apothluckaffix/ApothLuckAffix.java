package dev.gcat101.apothluckaffix;

import dev.shadowsoffire.apotheosis.Apotheosis;
import dev.shadowsoffire.apotheosis.adventure.affix.AffixRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod(ApothLuckAffix.MODID)
public class ApothLuckAffix
{
    public static final String MODID = "apothluckaffix";

    public ApothLuckAffix()
    {
        AffixRegistry.INSTANCE.registerCodec(Apotheosis.loc("luck"), LuckAffix.CODEC);
    }
}
