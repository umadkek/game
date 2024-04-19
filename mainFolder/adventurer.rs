#[derive(Clone)]
#[derive(PartialEq)]
pub struct Adventurer {
    name: String,
    health: i32,
    attack_power: i32,
}

impl Adventurer {
    pub fn new(name: String, health: i32, attack_power: i32) -> Self {
        Adventurer {
            name,
            health,
            attack_power,
        }
    }

    pub fn get_name(&self) -> &str {
        &self.name
    }

    pub fn get_health(&self) -> i32 {
        self.health
    }

    pub fn get_attack_power(&self) -> i32 {
        self.attack_power
    }

    pub fn set_health(&mut self, health: i32) {
        self.health = health;
    }

    pub fn attack(&self, opponent: &mut Adventurer) {
        let damage = self.attack_power;
        opponent.set_health(opponent.get_health() - damage);
        println!("{} attacks {}!", self.name, opponent.get_name());
    }
}