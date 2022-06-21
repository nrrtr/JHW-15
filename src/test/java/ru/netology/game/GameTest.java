package ru.netology.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.data.Player;
import ru.netology.exceptions.NotRegisteredException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class GameTest {
    Player p1 = new Player(1, "lalka1", 10);
    Player p2 = new Player(2, "lalka2", 8);
    Player p3 = new Player(3, "lalka3", 10);
    Player p4 = new Player(4, "lalka4", 11);
    Collection<Player> players = new ArrayList<>();
    Game newGame = new Game(players);

    @Test
    void shouldRegisterPlayersToEmptyCollection() {
        newGame.register(p1);

        Collection<Player> ar = newGame.findAll();
        Collection<Player> er = Arrays.asList(p1);
        assertEquals(er, ar);
    }

    @Test
    void shouldRegisterPlayersToNotEmptyCollection() {
        newGame.register(p1);
        newGame.register(p2);
        newGame.register(p4);

        Collection<Player> ar = newGame.findAll();
        Collection<Player> er = Arrays.asList(p1, p2, p4);
        assertEquals(er, ar);
    }

    @Test
    void shouldFindByNameFromRegisteredCollection() {
        newGame.register(p1);
        newGame.register(p2);

        Player ar = newGame.findByName("lalka2");
        Player er = p2;
        assertEquals(er, ar);
    }

    @Test
    void shouldThrowExceptionWhenFindByNameWithWrongAttribute() {
        newGame.register(p1);
        newGame.register(p2);
        assertThrows(NotRegisteredException.class, () -> {
            newGame.findByName("INVALID_NAME");
        });
    }
    @Test
    void shouldReturnPlayer1Wins(){
        newGame.register(p1);
        newGame.register(p2);

        int ar = newGame.round("lalka1","lalka2");
        int er = 1;
        assertEquals(er, ar);
    }
    @Test
    void shouldReturnPlayer2Wins(){
        newGame.register(p2);
        newGame.register(p4);

        int ar = newGame.round("lalka2","lalka4");
        int er = 2;
        assertEquals(er, ar);
    }
    @Test
    void shouldReturnDraw(){
        newGame.register(p1);
        newGame.register(p3);

        int ar = newGame.round("lalka1","lalka3");
        int er = 0;
        assertEquals(er, ar);
    }
    @Test
    void shouldThrowExceptionWhenRoundWithWrongFirstName() {
        newGame.register(p1);
        newGame.register(p2);
        assertThrows(NotRegisteredException.class, () -> {
            newGame.round("ASDASD","lalka2");
        });
    }
    @Test
    void shouldThrowExceptionWhenRoundWithWrongSecondName() {
        newGame.register(p1);
        newGame.register(p2);
        assertThrows(NotRegisteredException.class, () -> {
            newGame.round("lalka1","QWERTY");
        });
    }

}
