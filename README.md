# Amazing Numbers

## Description
This project is a JetBrains Academy Graduate Java project called Amazing Numbers. It covers basic operations on integers and collections. The program indicates the properties of numbers taken from the input. It can determine whether a number is Palindromic, Gapful, or distinguish Spy numbers from others. Numbers can be fascinating!

## GitHub Repository
The source code for this project is available on GitHub: [Amazing Numbers](https://github.com/MorgunovE/JetBrains-Academy-Graduate-Java-project-Amazing-Numbers)

## Features
- Determine properties of a single number.
- Determine properties of a list of numbers.
- Search for numbers with specific properties.
- Exclude numbers with specific properties.
- Handle mutually exclusive properties.
- Display error messages for invalid inputs.

## Project Structure
The project is structured as follows:
- `src/`: Contains the Java source files.
    - `utils/`: Contains utility classes such as `JumpingNumber`, `NaturalNumber`, `PalindromicNumber`, `SpyNumber`, `SunnyAndSquareNumber`.
    - `Main.java`: Main class to run the application.

## Compile the Project
To compile the project, use the following command:
```sh
javac -d out -sourcepath src -cp src src/Main.java
```

## Run the Project
To run the project, use the following command:
```sh
java -cp out Main
```

## Simulation Scenarios
The project includes simulation scenarios to test its functionalities:
- Determine properties of predefined numbers.
- Search for numbers with specific properties.
- Exclude numbers with specific properties.
- Handle mutually exclusive properties.

## Verification
To verify the project, run the simulation scenarios included in the `main` method of the `Main` class.

## Usage
1. Clone the GitHub repository.
2. Compile the project.
3. Run the project.
4. Follow the instructions displayed in the console to interact with the program.

## Author
This project was created by [Evgenii Morgunov](https://github.com/MorgunovE/).

## License
This project is licensed under the MIT License.