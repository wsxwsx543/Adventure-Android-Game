package com.example.phase1;

public class Round {
    MonsterTurn monster;
    PlayerTurn player;
    int monster_hp;
    int player_hp;
    Property monster_property;
    Property player_property;
    MonsterMove move_monster;
    PlayerMove move_player;


    public Round(MonsterMove move_monster, PlayerMove move_player){
        this.monster_hp = monster.hp;
        this.player_hp = player.hp;
        this.move_monster = move_monster;
        this.move_player = move_player;

    }

    Property getMonster_property(MonsterTurn monster, Monster boss, MonsterMove move){
        monster.addProperty(move);
        return monster.getProperty();
    }

    Property getPlayer_property(PlayerTurn player, Player people, PlayerMove move){
        player.addProperty(move);
        return player.getProperty();
    }

    void battle(Monster boss, Player player){
    }


}
