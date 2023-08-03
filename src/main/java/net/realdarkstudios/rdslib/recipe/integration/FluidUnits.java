package net.realdarkstudios.rdslib.recipe.integration;

import it.unimi.dsi.fastutil.doubles.Double2ObjectFunction;
import net.minecraft.network.chat.Component;

import java.text.DecimalFormat;

public enum FluidUnits implements ConfigurableEnum {
    LITERS("liters", a -> Component.translatable("emi.fluid.amount.liters",
            new DecimalFormat("0.##").format((int) (a / literDivisor())))),
    MILLIBUCKETS("millibuckets", a -> Component.translatable("emi.fluid.amount.millibuckets",
            new DecimalFormat("0.##").format((int) (a / literDivisor())))),
    DROPLETS("droplets", a -> Component.translatable("emi.fluid.amount.droplets",
            new DecimalFormat("0.##").format((int) a))),
    ;
    public static final int BUCKET = 1000;
    public static final int BOTTLE = 250;

    private final String name;
    private final Component translation;
    private final Double2ObjectFunction<Component> translator;

    private FluidUnits(String name, Double2ObjectFunction<Component> translator) {
        this.name = name;
        translation = Component.translatable("emi.unit." + name);
        this.translator = translator;
    }

    private static int literDivisor() {
        return 1;
    }

    @Override
    public String getName() {
        return name;
    }

    public Component translate(double amount) {
        return translator.apply(Double.valueOf(amount));
    }

    @Override
    public Component getText() {
        return translation;
    }
}
