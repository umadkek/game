mod game;
mod location;
mod adventurer;

use std::io;
use std::io::Write;
use game::Game;

fn main() {
    let mut username = String::new();

    println!("Input username: ");

    print!(">>> ");
    io::stdout().flush().expect("Could not flush."); // Gonna be real don't really know what this does but have to use for prompt.
    io::stdin().read_line(&mut username).expect("Please input a valid string.");

    let mut game = Game::new(username.trim().to_string());

    game.start_game(); // Here we go...
}
