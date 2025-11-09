package com.vanillage.raytraceantixray.util;

import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;

public class BukkitUtil {

    public static final boolean IS_PAPER = isPaper();
    public static final boolean IS_FOLIA = isFolia();

    public static boolean isRunning() {
        return MinecraftServer.getServer().getTickCount() > 0;
    }

    private static boolean isPaper() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        return cl.getResource("io/papermc/paper") != null || cl.getResource("io/papermc/paperclip") != null
                || cl.getResource("com/destroystokyo/paper") != null || cl.getResource("com/destroystokyo/paperclip") != null;
    }

    private static boolean isFolia() {
        return classForName(Bukkit.getServer().getClass().getClassLoader(), "io.papermc.paper.threadedregions.RegionizedServer") != null;
    }

    private static Class<?> classForName(ClassLoader ldr, String name) {
        try {
            return ldr.loadClass(name);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }

}
