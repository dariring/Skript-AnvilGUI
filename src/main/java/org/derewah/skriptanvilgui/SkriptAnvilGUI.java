package org.derewah.skriptanvilgui;

import org.bstats.bukkit.Metrics;
import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bstats.charts.SimplePie;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.derewah.skriptanvilgui.utils.UpdateChecker;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SkriptAnvilGUI extends JavaPlugin {

    /** Newest Minecraft version this build has been made compatible with. */
    private static final String LATEST_COMPATIBLE = "26.2";

    /** Matches the version inside a server version string, e.g. "git-Paper-9 (MC: 26.2)". */
    private static final Pattern MC_VERSION = Pattern.compile("(?i)\\(MC: (\\d+(?:\\.\\d+)*)");
    /** Matches Bukkit version strings, e.g. "26.2-R0.1-SNAPSHOT" or "1.21.11-R0.1-SNAPSHOT". */
    private static final Pattern BUKKIT_VERSION = Pattern.compile("^(\\d+(?:\\.\\d+)*)-R");

    static SkriptAnvilGUI instance;
    SkriptAddon addon;
    public static FileConfiguration config;
    public void onEnable(){
        instance = this;
        String mcVersion = extractVersion(this.getServer().getVersion());
        if (mcVersion == null) {
            // Paper does not guarantee the format of getVersion(), so fall back to the Bukkit version
            mcVersion = extractVersion(Bukkit.getBukkitVersion());
        }
        if (mcVersion == null) {
            getLogger().warning("Could not detect the Minecraft version of this server (\""
                    + this.getServer().getVersion() + "\"). Assuming Skript-AnvilGUI is compatible.");
        } else if (compareVersions(mcVersion, LATEST_COMPATIBLE) > 0) {
            getInstance().getLogger().severe("Skript-AnvilGUI is not compatible yet with this MC version." +
                    "Current version: " + mcVersion + ", latest compatible version: "
                    + LATEST_COMPATIBLE);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }



        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("org.derewah.skriptanvilgui");
        } catch(IOException e){
            e.printStackTrace();
        }
        addon.setLanguageFileDirectory("lang");
        // Register Metrics
        Metrics metrics = new Metrics(this, 18524);

        metrics.addCustomChart(new SimplePie("skript_version", () ->
                Bukkit.getServer().getPluginManager().getPlugin("Skript").getDescription().getVersion()));
        metrics.addCustomChart(new SimplePie("skript-anvilgui_version", () ->
                this.getDescription().getVersion()));

            new UpdateChecker(this, 110009).getVersion(version -> {
            if(this.getDescription().getVersion().equals(version)){
                getInstance().getLogger().info("Skript-AnvilGUI is up to date! Current:" + version);
            }else{
                getInstance().getLogger().info("Skript-AnvilGUI is out of date. Current: "+ this.getDescription().getVersion() + " Please update to make sure " +
                        "all of your anvil GUIs Skripts will work correctly. New version: " + version);
            }
        });




        Bukkit.getLogger().info("[Skript-AnvilGUI] has been enabled!");
    }

    public static boolean isCompatible(String version, String limit) {
        String mcVersion = extractVersion(version);
        // Version pattern not found
        if (mcVersion == null) return false;
        return compareVersions(mcVersion, limit) <= 0;
    }

    /**
     * Extracts the Minecraft version out of a server version string. Handles both the classic
     * scheme (1.21.11) and the calendar based one introduced with 26.1.
     *
     * @return the plain version number, or null if the string holds none
     */
    public static String extractVersion(String version) {
        Matcher matcher = MC_VERSION.matcher(version);
        if (matcher.find()) {
            return matcher.group(1);
        }
        matcher = BUKKIT_VERSION.matcher(version);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Compares two plain version numbers part by part, missing parts counting as 0.
     *
     * @return a negative number if version is older than other, 0 if they are equal, a positive one if it is newer
     */
    public static int compareVersions(String version, String other) {
        String[] versionNumbers = version.split("\\.");
        String[] otherNumbers = other.split("\\.");

        for (int i = 0; i < Math.max(versionNumbers.length, otherNumbers.length); i++) {
            int versionPart = i < versionNumbers.length ? Integer.parseInt(versionNumbers[i]) : 0;
            int otherPart = i < otherNumbers.length ? Integer.parseInt(otherNumbers[i]) : 0;
            if (versionPart != otherPart) {
                return versionPart < otherPart ? -1 : 1;
            }
        }
        return 0;
    }

    public static SkriptAnvilGUI getInstance(){
        return instance;
    }

    public SkriptAddon getAddonInstance(){
        return addon;
    }
}
