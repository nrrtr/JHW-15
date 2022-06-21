package ru.netology.game;

import ru.netology.data.Player;
import ru.netology.exceptions.NotRegisteredException;

import java.util.ArrayList;
import java.util.Collection;

public class Game {
    private Collection<Player> playerCollection = new ArrayList<>();

    public Game(Collection<Player> players) {
        this.playerCollection = players;
    }

    public void register(Player player) {
        this.playerCollection.add(player);
    }

    public Player findByName(String name) throws NotRegisteredException {
        for (Player player : playerCollection) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        throw new NotRegisteredException("Игрок с именем:" + name + " не зарегистрирован!");
    }

    public Collection<Player> findAll() {
        return playerCollection;
    }

    public int round(String playerName1, String playerName2) throws NotRegisteredException {
        Player p1 = this.findByName(playerName1);
        Player p2 = this.findByName(playerName2);
        if (!playerCollection.contains(p1)) {
            throw new NotRegisteredException("Игрок с именем:" + playerName1 + " не зарегистрирован!");
        }
        if (!playerCollection.contains(p2)) {
            throw new NotRegisteredException("Игрок с именем:" + playerName2 + " не зарегистрирован!");
        }
        if (p1.getStrength() == p2.getStrength()) return 0;
        if (p1.getStrength() > p2.getStrength()) return 1;
        else return 2;
    }

}
