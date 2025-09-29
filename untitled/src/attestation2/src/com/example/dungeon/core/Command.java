package attestation2.src.com.example.dungeon.core;

import attestation2.src.com.example.dungeon.model.GameState;
import java.util.List;

@FunctionalInterface
public interface Command { void execute(GameState ctx, List<String> args); }
