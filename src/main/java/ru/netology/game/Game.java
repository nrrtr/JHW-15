package ru.netology.game;

import ru.netology.data.Player;
import ru.netology.exceptions.NotRegisteredException;

import java.util.HashMap;

public class Game {
    private HashMap<String, Player> nameAndPlayer = new HashMap<>();

    public Game(HashMap<String, Player> someHashMap) {
        this.nameAndPlayer = someHashMap;
    }

    public void register(Player player) {
        this.nameAndPlayer.put(player.getName(), player);
    }

    public Player findByName(String name) throws NotRegisteredException {
        if (!nameAndPlayer.containsKey(name)) {
            throw new NotRegisteredException("Игрок с именем:" + name + " не зарегистрирован!");
        }
        return nameAndPlayer.get(name);
    }

    public HashMap<String, Player> findAll() {
        return nameAndPlayer;
    }

    public int round(String playerName1, String playerName2) throws NotRegisteredException {
        Player p1 = this.findByName(playerName1);
        Player p2 = this.findByName(playerName2);
        if (!nameAndPlayer.containsKey(p1.getName())) {
            throw new NotRegisteredException("Игрок с именем:" + playerName1 + " не зарегистрирован!");
        }
        if (!nameAndPlayer.containsKey(p2.getName())) {
            throw new NotRegisteredException("Игрок с именем:" + playerName2 + " не зарегистрирован!");
        }
        if (p1.getStrength() == p2.getStrength()) return 0;
        if (p1.getStrength() > p2.getStrength()) return 1;
        else return 2;
    }
}
