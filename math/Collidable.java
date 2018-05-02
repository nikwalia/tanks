package math;

public interface Collidable
{
    Value3D[] base = new Value3D[4];
    
    void updateCorners();
    boolean hasCollided(Collidable other);
    void onCollision(Collidable other);
}
