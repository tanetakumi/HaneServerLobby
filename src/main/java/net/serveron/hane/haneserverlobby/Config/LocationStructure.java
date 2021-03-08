package net.serveron.hane.haneserverlobby.Config;

import org.bukkit.Location;
import org.bukkit.World;

public class LocationStructure {
    private final double x;
    private final double y;
    private final double z;
    private final float yaw;
    private final float pitch;

    public LocationStructure(double x, double y, double z, double yaw, double pitch){
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = (float)yaw;
        this.pitch = (float) pitch;
    }

    public Location createLocation(World world){
        return new Location(world,x,y,z,yaw,pitch);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
