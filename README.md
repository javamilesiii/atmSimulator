# ATM Simulator

A console-based ATM (Automated Teller Machine) simulator built in Java that demonstrates object-oriented programming principles and clean code practices.

## 🏧 Features

- **Card Selection**: Choose from multiple pre-loaded bank cards
- **PIN Authentication**: Secure 3-attempt PIN verification system
- **Card Expiration Check**: Validates card expiration dates
- **Withdrawal Operations**:
    - Quick amounts (CHF 10, 20, 50, 100)
    - Custom amount withdrawal
    - Balance validation
- **Deposit Operations**: Add money to your account
- **Balance Display**: View current account balance
- **Error Handling**: Robust input validation and error messages
- **Clean UI**: Bordered console display with consistent formatting

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- IDE (IntelliJ IDEA, Eclipse, or VS Code)
- Terminal/Command prompt

### Running the Application

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd atmSimulator
   ```

2. Compile and run:
   ```bash
   javac -d out src/main/java/ch/javamilesiii/*.java
   java -cp out main.java.ch.javamilesiii.Main
   ```

   Or run directly from your IDE by executing the `Main.java` file.

## 🎮 How to Use

1. **Start the application** - You'll see the welcome screen
2. **Insert a card** - Choose from 5 available test cards
3. **Enter PIN** - Each card has a specific PIN (see Test Data section)
4. **Select operation**:
    - `1` - Withdraw money
    - `2` - Deposit money
    - `3` - Eject card and exit
5. **Follow the prompts** for your selected operation

### Example Session

```
######################################################################
#                                                                    #
#    Welcome! Insert your card:                                     #
#                                                                    #
######################################################################
```

## 🧪 Test Data

The application comes with 5 pre-configured cards:

| Card Owner      | PIN    | Initial Balance |
|----------------|--------|----------------|
| Aubrey Thomas  | 738143 | CHF 1000.0     |
| Layla Roberts  | 612543 | CHF 5100.0     |
| Abigail Peterson | 867826 | CHF 1250.0   |
| Avery Phillips | 874256 | CHF 250.0      |
| Sarah Taylor   | 989631 | CHF 1000.0     |

## 🏗️ Architecture

The project follows **SOLID principles** and demonstrates clean code practices:

### Class Structure

```
src/main/java/ch/javamilesiii/
├── Main.java              # Application entry point
├── ATM.java               # Main ATM controller logic
├── BankAccount.java       # Account data and operations
├── Card.java              # Card information and validation
├── IOHandler.java         # User input handling
└── MessageDisplay.java    # Console UI formatting
```

### Design Principles Applied

- **Single Responsibility**: Each class has one clear purpose
- **Error Handling**: Comprehensive input validation and error recovery
- **DRY Principle**: Common functionality extracted into reusable methods
- **Method Extraction**: Large methods broken down into focused sub-methods

## 🔧 Key Components

### ATM Class
- Orchestrates the entire ATM workflow
- Handles card selection, authentication, and main operations
- Manages session state and cleanup

### BankAccount Class
- Stores account information (IBAN, owner, balance)
- Handles withdrawal and deposit operations
- Validates transaction amounts

### Card Class
- Contains card details and expiration information
- Provides PIN validation functionality
- Links to associated bank account

### MessageDisplay Class
- Creates consistent bordered console output
- Handles text wrapping for long messages
- Provides menu layouts with proper spacing

### IOHandler Class
- Manages all user input with error handling
- Validates numeric inputs and retries on errors
- Cleans input buffers to prevent issues

## 🛡️ Error Handling

- **Input Validation**: All user inputs are validated and sanitized
- **Balance Checks**: Prevents overdrawing from accounts
- **PIN Security**: Limited authentication attempts with lockout
- **Amount Validation**: Ensures positive transaction amounts
- **Card Expiration**: Checks and handles expired cards
- **Graceful Recovery**: User-friendly error messages with retry options

## 🔜 Potential Enhancements

- [ ] Transaction history logging
- [ ] Different account types (savings, checking)
- [ ] Transfer between accounts
- [ ] Receipt generation
- [ ] Admin mode for card management
- [ ] Configurable withdrawal limits
- [ ] Multi-language support

## 📝 Technical Details

- **Java Version**: 11+
- **Architecture**: Object-oriented with clear separation of concerns
- **UI**: Console-based with formatted output
- **Data Storage**: In-memory (resets on restart)
- **Input Handling**: Scanner-based with comprehensive error recovery

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENCE](LICENCE) file for details.

## 🎓 Learning Objectives

This project demonstrates:
- Object-oriented programming principles
- Clean code practices and SOLID principles
- Error handling and input validation
- Method extraction and code organization
- Console-based user interface design
- Separation of concerns in software architecture

## 👨‍💻 Author

Created as a learning project to demonstrate Java programming skills and software design principles.

---

*Note: This is a simulation for educational purposes. No real financial transactions are performed.*