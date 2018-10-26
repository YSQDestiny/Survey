package com.cykj.survey;

public class Test {

    public static void main(String args[]){


    }

    public void battel(){
        Player player = new Player();
        player.setName("勇者");
        player.setAtk(1000);
        player.setDef(500);
        player.setBaoji(0.2);
        player.setHp(10000);

        Monster monster = new Monster();
        monster.setName("勇者");
        monster.setAtk(1000);
        monster.setDef(500);
        monster.setBaoji(0.3);
        monster.setHp(10000);


        int isEnd = 1;
        while (isEnd == 1){
            int random = (int)(1+Math.random()*(10-1+1));
            if (random % 2 == 0){
                System.out.println(player.getName()+"发起了攻击：");
                
            }
        }
    }


    class Player{
        private String name;
        private int hp;
        private int mp;
        private int atk;
        private int def;
        private double baoji;

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

        public int getMp() {
            return mp;
        }

        public void setMp(int mp) {
            this.mp = mp;
        }

        public int getAtk() {
            return atk;
        }

        public void setAtk(int atk) {
            this.atk = atk;
        }

        public int getDef() {
            return def;
        }

        public void setDef(int def) {
            this.def = def;
        }

        public double getBaoji() {
            return baoji;
        }

        public void setBaoji(double baoji) {
            this.baoji = baoji;
        }
    }

    class Monster{
        private String name;
        private int hp;
        private int mp;
        private int atk;
        private int def;
        private double baoji;

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

        public int getMp() {
            return mp;
        }

        public void setMp(int mp) {
            this.mp = mp;
        }

        public int getAtk() {
            return atk;
        }

        public void setAtk(int atk) {
            this.atk = atk;
        }

        public int getDef() {
            return def;
        }

        public void setDef(int def) {
            this.def = def;
        }

        public double getBaoji() {
            return baoji;
        }

        public void setBaoji(double baoji) {
            this.baoji = baoji;
        }
    }
}
