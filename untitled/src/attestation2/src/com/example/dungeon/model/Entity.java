package attestation2.src.com.example.dungeon.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    private String name;
    private int hp;
    private List<Item> inventory = new ArrayList<>();

    public Entity(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public Entity(int hp, List<Item> inventory, String name) {
        this.hp = hp;
        this.inventory = inventory;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    @Override
    public String toString() {
        return "Entity [name=" + name + ", hp=" + hp + ", inventory=" + inventory + "]";
    }
}
