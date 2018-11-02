package com.cykj.survey;

public class Test {

    private static Player player = new Player();


    private static Monster monster = new Monster();


    public static void main(String args[]){

        player.setName("勇者");
        player.setAtk(1000);
        player.setDef(500);
        player.setBaoji(0.2);
        player.setHp(10000);

        monster.setName("魔王");
        monster.setAtk(1000);
        monster.setDef(500);
        monster.setBaoji(0.3);
        monster.setHp(10000);

        battel(player,monster);
    }

    public static void battel( Player player,Monster monster ){


        int isEnd = 1;
        while (isEnd == 1){
            int random = (int)(1+Math.random()*(10-1+1));
            if (random % 2 == 0){
                System.out.println(player.getName()+"发起了攻击：");
                if (random == 2 || random == 4){
                    System.out.println("暴击，"+monster.getName()+"HP -"+(player.getAtk() * 2));
                    monster.setHp(monster.getHp() - (player.getAtk() * 2));
                    if (monster.getHp() <= 0){
                        System.out.println("魔王HP：0");
                        System.out.println(player.getName() + "获得胜利！");
                        isEnd = 0;
                    }else {
                        System.out.println("魔王HP：" + monster.getHp());
                        System.out.println("勇者HP："+ player.getHp());
                    }
                }else {
                    System.out.println(monster.getName()+"HP -"+player.getAtk());
                    monster.setHp(monster.getHp() - player.getAtk());
                    if (monster.getHp() <= 0){
                        System.out.println("魔王HP：0");
                        System.out.println(player.getName() + "获得胜利！");
                        isEnd = 0;
                    }else {
                        System.out.println("魔王HP：" + monster.getHp());
                        System.out.println("勇者HP："+ player.getHp());
                    }
                }
                System.out.println("---------------------------------------");
            }else {
                System.out.println(monster.getName()+"发起了攻击：");
                if (random == 1 || random == 3 || random == 5){
                    System.out.println("暴击，"+player.getName()+"HP -"+(monster.getAtk() * 2));
                    player.setHp(player.getHp() - (monster.getAtk() * 2));
                    if (player.getHp() <= 0){
                        System.out.println(player.getName() + "HP：0");
                        System.out.println(monster.getName() + "获得胜利！");
                        isEnd = 0;
                    }else {
                        System.out.println(player.getName() +"HP：" + player.getHp());
                        System.out.println(monster.getName() +"HP："+ monster.getHp());
                    }
                }else {
                    System.out.println(player.getName()+"HP -"+monster.getAtk());
                    player.setHp(player.getHp() - monster.getAtk());
                    if (player.getHp() <= 0){
                        System.out.println(player.getName() + "HP：0");
                        System.out.println(monster.getName() + "获得胜利！");
                        isEnd = 0;
                    }else {
                        System.out.println(player.getName() +"HP：" + player.getHp());
                        System.out.println(monster.getName() +"HP："+ monster.getHp());
                    }
                }
                System.out.println("---------------------------------------");
            }
        }
    }


    static class Player{
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

    static class Monster{
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
