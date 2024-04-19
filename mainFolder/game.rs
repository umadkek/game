use std::io::{self, Write};

use crate::adventurer::Adventurer;
use crate::location::Location;

pub struct Game {
    name: String,
    adventurer: Adventurer,
    current_location: Location,
    locations: Vec<Location>,
}

impl Game {
    pub fn new(name: String) -> Self {
        let adventurer = Adventurer::new(name.clone(), 100, 5);
        let goblin = Adventurer::new("Goblin".to_string(), 100, 2);

        let mut home_base = Location::new(
            String::from("Home Base"),
            String::from("The starting point."),
        );
        let mut forest = Location::new(
            String::from("Forest"),
            String::from("A dense forest with tall trees."),
        );

        home_base.add_adventurer(adventurer.clone());
        forest.add_adventurer(goblin.clone());

        let locations = vec![home_base.clone(), forest.clone()];

        let current_location = locations[0].clone();

        Game {
            name,
            adventurer,
            current_location,
            locations,
        }
    }

    pub fn move_to_location(&mut self, location_index: usize) {
        if location_index > self.locations.len() {
            println!("Enter a valid index.");
            return;
        };
        self.locations
            .iter_mut()
            .enumerate()
            .for_each(|(index, element)| {
                if element.adventurers.contains(&self.adventurer) {
                    element.remove_adventurer(&self.adventurer);
                }
            });
        self.locations[location_index].add_adventurer(self.adventurer.clone());
        self.current_location = self.locations[location_index].clone();
    }

    fn attack_adventurer(&mut self, index: usize) {
        // Not done yet
        // so what do i need to do here then
        // look at kek's code
    }

    pub fn list_adventurers(&self) {
        println!("\nLocations:");
        self.locations
            .iter()
            .enumerate()
            .for_each(|(index, element)| {
                println!(
                    "{}. [{}]\n   Description: {}",
                    index + 1,
                    element.get_name(),
                    element.get_description()
                );
                println!("   Adventurers:");
                element
                    .adventurers
                    .iter()
                    .enumerate()
                    .for_each(|(index, element)| {
                        println!("     [{}]", element.get_name());
                    });
            });
    }

    pub fn list_stats(&self) {
        println!("\nStats:");
        println!("  Name:   {}", self.adventurer.get_name());
        println!("  Health: {}", self.adventurer.get_health());
        println!("  Power:  {}", self.adventurer.get_attack_power());
    }

    pub fn start_game(&mut self) {
        'game_loop: loop {
            let mut user_command = String::new();
            println!("\nCurrent Location: [{}]", self.current_location.get_name());
            println!("Input a command:");
            print!(">>> ");
            io::stdout().flush().expect("Could not flush.");
            io::stdin()
                .read_line(&mut user_command)
                .expect("Failed to read line");
            user_command = user_command.trim().to_lowercase();

            match user_command.as_str() {
                "move" => {
                    let mut user_move_choice = String::new();
                    println!("\nLocations:");
                    self.locations
                        .iter()
                        .enumerate()
                        .for_each(|(index, element)| {
                            println!(
                                "{}. [{}]\n   Description: {}",
                                index + 1,
                                element.get_name(),
                                element.get_description()
                            );
                        });
                    println!(">---------------------------<>---------------------------<");
                    println!("Enter the index of the location you would like to move to:");
                    print!(">>> ");
                    io::stdout().flush().expect("Could not flush.");
                    io::stdin()
                        .read_line(&mut user_move_choice)
                        .expect("Failed to read line");
                    let user_move_choice: usize = match user_move_choice.trim().parse() {
                        Ok(num) => num,
                        Err(_) => {
                            println!("Please enter a number next time.");
                            continue 'game_loop;
                        }
                    };
                    self.move_to_location(user_move_choice - 1);
                }
                "attack" => {
                    println!("Who would you like to attack? UNDER DEVELOPMENT NOT DONE YET\n");
                    // List and check here
                    // Print stats and loop fight
                }
                "list" => {
                    self.list_adventurers();
                }
                "stats" => {
                    self.list_stats();
                }
                "help" => {
                    println!("\nCommands:");
                    println!(">---------------------------<>---------------------------<");
                    println!("move\n  Moves your character to a different location.");
                    println!("attack\n  Attacks an adventurer at your current location.");
                    println!("credits\n  Prints out the game's credits.");
                    println!("quit\n  Quits the game.");
                }
                "credits" => {
                    println!("\nDeveloped by: umadkek");
                    println!("Ported by: bislij");
                    println!("Testing by: bislij");
                    println!("Rust Version 1.57.0");
                    println!("JetBrains RustRover Preview");
                    println!("Git GUI Version 0.21");
                }
                "quit" => {
                    let mut user_quit_choice = String::new();
                    println!("\nAre you sure you want to quit? Progress will not be saved.");
                    print!(">>> (y/n): ");
                    io::stdout().flush().expect("Could not flush.");
                    io::stdin()
                        .read_line(&mut user_quit_choice)
                        .expect("Failed to read line");
                    if user_quit_choice.trim().to_lowercase().as_str() == "y" {
                        break 'game_loop;
                    }
                }
                _ => {
                    println!("Invalid command. Please try again.");
                }
            }
        }
        println!("Thanks for playing!");
    }
}
