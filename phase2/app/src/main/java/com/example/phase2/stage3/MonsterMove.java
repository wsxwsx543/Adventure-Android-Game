package com.example.phase2.stage3;

import com.example.phase2.appcore.game.Property;


public class MonsterMove implements Move {
private MoveFactory factory;

    /** A monster move. */
    MonsterMove() {
        factory = new MoveFactory();
    }

    /**
     * use monsterDoMove method to get property of monster after each move.
     * @param id      an random number to decide what the monster would do.
     * @param monster the monster we decided.
     * @return the property of monster after the move.
     */
    Property monsterDoMove(int id, Monster monster) {

        Property m = monster.getProperty();
        Property MP = new Property(m.getAttack(), m.getDefence(), m.getFlexibility(), m.getLuckiness());
        return factory.chooseMove(MP, id);
    }

    /**
     * Get the string of monster's move
     * @param id An number which represent a move.
     * @return string of monster's move
     */
    public String getString(int id) {
        return factory.getString(id);
    }
}
