package ru.netology.game;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.netology.data.Player;
import ru.netology.exceptions.NotRegisteredException;

import java.util.HashMap;

public class GameTest {
    Player p1 = new Player(1, "lalka1", 10);
    Player p2 = new Player(2, "lalka2", 8);
    Player p3 = new Player(3, "lalka3", 10);
    Player p4 = new Player(4, "lalka4", 11);
    HashMap<String, Player> players = new HashMap<>();
    Game newGame = new Game(players);

    @Test
    void shouldRegisterPlayersToNotEmptyMap() {
        newGame.register(p1);
        newGame.register(p2);
        newGame.register(p4);

        HashMap<String, Player> ar = newGame.findAll();
        HashMap<String, Player> er = new HashMap<>();
        er.put("lalka1", p1);
        er.put("lalka2", p2);
        er.put("lalka4", p4);
        assertEquals(er, ar);
    }
    @Test
    void shouldRegisterPlayersToEmptyMap() {
        newGame.register(p1);

        HashMap<String, Player> ar = newGame.findAll();
        HashMap<String, Player> er = new HashMap<>();
        er.put("lalka1", p1);
        assertEquals(er, ar);
    }

    @Test
    void shouldFindByNameFromRegisteredMap() {
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
        assertThrows(NotRegisteredException.class, () -> newGame.findByName("INVALID_NAME"));
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
        assertThrows(NotRegisteredException.class, () -> newGame.round("ASDASD","lalka2"));
    }
    @Test
    void shouldThrowExceptionWhenRoundWithWrongSecondName() {
        newGame.register(p1);
        newGame.register(p2);
        assertThrows(NotRegisteredException.class, () -> newGame.round("lalka1","QWERTY"));
    }

    @Test
    void shouldThrowSameExceptionText(){
        newGame.register(p1);
        newGame.register(p2);
        NotRegisteredException exception = assertThrows(NotRegisteredException.class, () ->
                newGame.round("qwe","lalka2"));
        String expectedMessage = "Игрок с именем:" + "qwe" + " не зарегистрирован!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void justCantGetRatio(){
        newGame.register(p1);
        Throwable thrown = assertThrows(NotRegisteredException.class, () ->
                newGame.round("lalka1","asd"));
        assertNotNull(thrown.getMessage());
    }
}
