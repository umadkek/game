use crate::adventurer::Adventurer;

#[derive(Clone)]
pub struct Location {
    pub name:        String,
    pub description: String,
    pub adventurers: Vec<Adventurer>,
}

impl Location {
    pub fn new(name: String, description: String) -> Self {
        Location {
            name,
            description,
            adventurers: Vec::new(),
        }
    }

    pub fn get_name(&self) -> &str {
        &self.name
    }

    pub fn get_description(&self) -> &str {
        &self.description
    }

    pub fn add_adventurer(&mut self, adventurer: Adventurer) {
        self.adventurers.push(adventurer);
    }

    pub fn remove_adventurer(&mut self, adventurer: &Adventurer) {
        self.adventurers.retain(|adv| adv.get_name() != adventurer.get_name());
    }
}