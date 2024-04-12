public class Adventurer {
    private String name;
    private int health;
    private int attackPower;

    public Adventurer(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower; //code to create new adventurer
    }    

    public String getName() {
        return name; //gets name
    }

    public int getHealth() {
        return health; //gets health
    }

    public int getAttackPower() {
        return attackPower; //gets attackpower 
    }

    public void setHealth(int health) {
        this.health = health; //sets health
    }

    public void attack(Adventurer opponent) {
        int damage = this.attackPower; //logic for attacking an opponent
        opponent.setHealth(opponent.getHealth() - damage);
        System.out.println(this.name + " attacks " + opponent.getName());
    }
}