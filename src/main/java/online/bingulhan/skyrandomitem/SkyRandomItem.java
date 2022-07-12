package online.bingulhan.skyrandomitem;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public final class SkyRandomItem extends JavaPlugin {

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveConfig();

        run();
    }
    private void run() {
        getServer().getScheduler().runTaskLater(this, () -> {
            randomDropAll();
        }, 200);
    }

    private void randomDropAll() {
        for (Player player : getServer().getOnlinePlayers()) {
            Location location = player.getLocation().clone().add(0, 20, 0);
            location.getWorld().playSound(player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 100);
            location.getWorld().playEffect(location, Effect.ENDERDRAGON_GROWL, 25, 25);

            Location l2 = player.getLocation().clone().add(1, 20, 1);
            Location l3 = player.getLocation().clone().add(3, 20, 0);
            Location l4 = player.getLocation().clone().add(2, 20, 2);

            location.getWorld().dropItem(location, getRandomItem());
            location.getWorld().dropItem(l2, getRandomItem());
            location.getWorld().dropItem(l3, getRandomItem());
            location.getWorld().dropItem(l4, getRandomItem());

            player.sendTitle("", ChatColor.translateAlternateColorCodes('&', getConfig().getString("message")));

        }

        run();
    }

    private ItemStack getRandomItem() {
        int i = new Random().nextInt(Material.values().length);

        ArrayList<Material> materials = new ArrayList<>();
        for (Material material : Material.values()) {
            materials.add(material);
        }

        return new ItemStack(materials.get(i));
    }
}
