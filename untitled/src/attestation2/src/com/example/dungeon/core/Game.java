package attestation2.src.com.example.dungeon.core;

import attestation2.src.com.example.dungeon.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Game {
    private final GameState state = new GameState();
    private final Map<String, Command> commands = new LinkedHashMap<>();

    static {
        WorldInfo.touch("Game");
    }

    public Game() {
        registerCommands();
        bootstrapWorld();
    }

    private void registerCommands() {
        commands.put("help", (ctx, a) -> System.out.println("Команды: " + String.join(", ", commands.keySet())));
        commands.put("gc-stats", (ctx, a) -> {
            long totalMemory = Runtime.getRuntime().totalMemory();
            long freeMemory = Runtime.getRuntime().freeMemory();
            long usedMemory = totalMemory - freeMemory;

            System.out.println("Информация о памяти:");
            System.out.println("Всего памяти: " + convertSize(totalMemory));
            System.out.println("Свободно: " + convertSize(freeMemory));
            System.out.println("Занято: " + convertSize(usedMemory));
        });

        commands.put("look", (ctx, a) -> System.out.println(ctx.getCurrent().describe()));
        commands.put("move", (ctx, a) -> {
            String direction = a.getFirst();
            Room nextRoom = ctx.getCurrent().getNeighbors().get(direction);
            if (nextRoom == null) {
                throw new InvalidCommandException(String.format("В направление $s выхода нет", direction));
            }
            ctx.setCurrent(nextRoom);
            System.out.println("Вы перешли в: " + nextRoom.getName());
            System.out.println(ctx.getCurrent().describe());
        });
        commands.put("take", (ctx, a) -> {
            var itemName = String.join(" ", a).strip();
            var items = ctx.getCurrent().getItems().stream()
                    .filter(i -> i.getName().equals(itemName))
                    .toList();
            if (items.isEmpty()) {
                throw new InvalidCommandException(String.format("Такого придмета тут нет (%s)", itemName));
            }
            ctx.getPlayer().getInventory().addAll(items);
            ctx.getCurrent().getItems().removeAll(items);
            System.out.println("Взято: " + itemName);
        });
        commands.put("inventory", (ctx, a) -> {
            StringBuilder sb = new StringBuilder();
            List<String> potions = ctx.getPlayer().getInventory().stream()
                    .filter(obj -> obj instanceof Potion)
                    .map(Item::getName)
                    .sorted()
                    .collect(Collectors.toList());
            List<String> weapons = ctx.getPlayer().getInventory().stream()
                    .filter(obj -> obj instanceof Weapon)
                    .map(Item::getName)
                    .sorted()
                    .toList();
            sb.append("Зелья: ").append(String.join(", ", potions)).append("\n");
            System.out.print(sb.toString());
        });
        commands.put("use", (ctx, a) -> {
            var itemName = String.join(" ", a).strip();
            var optItem = ctx.getPlayer().getInventory().stream()
                    .filter(i -> i.getName().equals(itemName))
                    .findFirst();
            if (optItem.isEmpty()) {
                throw new InvalidCommandException(String.format("Предмет %s отсутсвует в вашем инвентаре", itemName));
            }
            optItem.get().apply(ctx);
        });
        commands.put("about", (ctx, a) -> {
            System.out.println("Dungeon Mini\nВерсия: 1.0\nJava-версия: " + System.getProperty("java.version"));
        });
        commands.put("fight", (ctx, a) -> {
            var monster = ctx.getCurrent().getMonster();
            var player = ctx.getPlayer();
            if (monster == null) {
                throw new InvalidCommandException("В комнате нет монстра");
            }
            monster.setHp(monster.getHp()-player.getAttack());
            player.setHp(player.getHp()-monster.getLevel());
            if (monster.getHp() <= 0) {
                System.out.printf("Монстр %s побежден!%n", monster.getName());
                ctx.getCurrent().setMonster(null);
                if (!monster.getInventory().isEmpty()) {
                    ctx.getCurrent().getItems().addAll(monster.getInventory());
                    monster.getInventory()
                            .forEach(i -> System.out.printf("Из %s выпало %s%n", monster.getName(),i.getName()));
                }
                System.out.printf("Заработано очков %d!%n", monster.getLevel());
                ctx.addScore(monster.getLevel());
            }
            else {
                System.out.printf("Вы бьёте %s на %d. Здоровье врага: %d%n", monster.getName(),player.getAttack(), monster.getHp());
            }
            if (player.getHp() <= 0) {
                System.out.println("Игрок побежден!%n");
                System.out.println("Пока!");
                System.exit(0);
            }
            else {
                System.out.printf("%s атаковал на %d. Осталось здоровья: %d%n",monster.getName(), monster.getLevel(),player.getHp());
            }
        });

        commands.put("save", (ctx, a) -> SaveLoad.save(ctx));
        commands.put("load", (ctx, a) -> SaveLoad.load(ctx));
        commands.put("scores", (ctx, a) -> SaveLoad.printScores());
        commands.put("exit", (ctx, a) -> {
            System.out.println("Пока!");
            System.exit(0);
        });
    }


    private void bootstrapWorld() {
        Player hero = new Player("Герой", 20, 5);
        state.setPlayer(hero);

        Room square = new Room("Площадь", "Каменная площадь с фонтаном.");
        Room forest = new Room("Лес", "Шелест листвы и птичий щебет.");
        Room cave = new Room("Пещера", "Темно и сыро.");
        square.getNeighbors().put("north", forest);
        forest.getNeighbors().put("south", square);
        forest.getNeighbors().put("east", cave);
        cave.getNeighbors().put("west", forest);

        forest.getItems().add(new Potion("Малое зелье", 5));
        forest.setMonster(new Monster("Волк", 1, 8));

        state.setCurrent(square);
    }

    public void run() {
        System.out.println("DungeonMini (TEMPLATE). 'help' — команды.");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.print("> ");
                String line = in.readLine();
                if (line == null) break;
                line = line.trim();
                if (line.isEmpty()) continue;
                List<String> parts = Arrays.asList(line.split("\s+"));
                String cmd = parts.getFirst().toLowerCase(Locale.ROOT);
                List<String> args = parts.subList(1, parts.size());
                Command c = commands.get(cmd);
                try {
                    if (c == null) throw new InvalidCommandException("Неизвестная команда: " + cmd);
                    c.execute(state, args);
                    state.addScore(1);
                } catch (InvalidCommandException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Непредвиденная ошибка: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода: " + e.getMessage());
        }
    }

    private String convertSize(long sizeInBytes) {
        float kb = sizeInBytes / 1024F;
        float mb = kb / 1024F;
        float gb = mb / 1024F;

        if (gb > 1) return String.format("%.2f ГБ", gb);
        if (mb > 1) return String.format("%.2f МБ", mb);
        return String.format("%.2f КБ", kb);
    }

}
