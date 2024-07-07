# OnlineStore
![OnlineStore](https://github.com/pmzpro/OnlineStore/assets/142250942/24d3f3b0-7985-494c-bf03-ae687d52c966)

## Introduction

OnlineStore is an e-commerce application developed in Java, designed to simulate the management of an online clothing store. The application allows interaction between two main types of users: administrators and end customers. Administrators have access to advanced stock management and sales report functionalities, while end customers can browse, purchase, and manage their purchase history.

## Application Structure

The application structure is implemented through various Java classes, each playing a specific role in the management and operation of the online store. Below are the main classes and their significance in the project.

## Main Classes

### OnlineStore

The `OnlineStore` class is the core of the application, responsible for managing users, item stock, and sold items. It implements the `OnlineStoreInterface` that defines the methods callable by the store users. Key methods include:

- **registerClient**: Registers a new client in the store. It takes the client's email, password, name, and category as parameters. It checks if the email is already in use and, if not, creates a new client.
- **login**: Authenticates users (client or administrator). It checks the provided credentials and returns the corresponding user if authentication is successful.
- **addItem**: Adds a new item to the store's stock. It takes the item code, category, type, selling price, purchase price, and initial stock quantity as parameters.
- **removeItem**: Removes an item from the store's stock based on the provided item code.
- **updateItem**: Updates the data of an existing item. It allows modification of the selling price, purchase price, category, and type of the item.
- **displayItemsWithLessStock**: Displays items with stock quantity below a specified threshold. It takes a limit value as a parameter and shows items with stock less than this value.
- **displayItemsSold**: Displays items sold so far. It shows the item code, quantity sold, and total revenue from sales.
- **exportItemsSold**: Exports sales data to a file, allowing external analysis of the sales made.
- **changeItem**: Changes the details of an item, such as prices. It allows modification of the selling and purchase prices of a specific item.
- **changeStockItem**: Changes the quantity of an item in stock. It allows incrementing or decrementing the available quantity of a specific item.
- **addItemToCart**: Adds an item to a client's shopping cart. It takes the item code and desired quantity as parameters.
- **checkout**: Finalizes a client's purchase, calculating the total purchase value and updating the stock and client's purchase history.
- **displayItemsBought**: Displays items bought by a client, showing the purchase history with details about each acquired item.

### Item

The `Item` class represents a product available for sale in the store. It includes attributes such as item code, category, type, selling price, and purchase price. Important methods include:

- **getters and setters**: For each attribute, allowing controlled access and modification of values.
- **isValidCategory**: Checks if the provided category is valid, returning a boolean value.
- **isValidTypeCategory**: Checks if the provided type is valid for the specified category, returning a boolean value.

### StockItem

The `StockItem` class associates an `Item` with its available stock quantity. Essential methods include:

- **getters and setters**: For quantity and the item itself, allowing access and modification of values.
- **incrementStock**: Increases the stock quantity of the item.
- **decrementStock**: Decreases the stock quantity of the item, ensuring the resulting quantity is not negative.

### Client

The `Client` class represents a store client, extending the `User` class. In addition to inherited attributes like email and password, it has additional attributes such as name and category (male or female). Important methods include:

- **getters and setters**: For name and category, allowing controlled access and modification of values.
- **showMenu**: Displays the interaction menu available to clients, allowing operations like adding items to the cart, finalizing purchases, and viewing purchase history.

### Administrator

The `Administrator` class also extends `User` and represents a store administrator. It has an additional attribute `isGlobalAdmin` that determines if the administrator has permission to export sales data. Important methods include:

- **getters and setters**: For the `isGlobalAdmin` attribute, allowing controlled access and modification of its value.
- **showMenu**: Displays the interaction menu available to administrators, allowing operations like adding, removing, and updating items, as well as viewing and exporting sales reports.

## Custom Exceptions

Custom exceptions were created to handle specific error cases in the application:

- **InvalidCategoryException**: Thrown when an invalid category is specified for an item type.
- **InvalidEmailException**: Thrown when an invalid email is used during client registration.
- **InvalidQuantityException**: Thrown when an invalid quantity is specified for a stock item.

## Menu and Main

The `Menu` and `Main` classes provide the command-line interface for interacting with the application:

### Menu

Displays menus for clients and administrators, allowing the execution of available operations. Key methods include:

- **displayClientMenu**: Displays the menu of options available to clients, such as viewing items, adding items to the cart, and finalizing purchases.
- **displayAdminMenu**: Displays the menu of options available to administrators, such as adding, removing, and updating items, viewing sales reports, and exporting data.

### Main

Contains test methods for creating items, administrators, and clients, validating the basic functionality of the application. Key methods include:

- **main**: The main method that initializes the application, creates instances of administrators and clients, and presents the initial menu for user interaction.
- **createSampleData**: Creates sample data to facilitate application testing, including items in stock and user registrations.

## How to Run the Program

- Right-click on the `Menu` class.
- Select the option `new Menu()` and name the menu.
- Right-click on the created object and select `void showMenu()`.

## Interacting with the Application

- Follow the instructions displayed in the menu to register clients, add items to stock, log in as a client or administrator, and utilize the available functionalities according to the user type.

## Conclusion

The OnlineStore application implemented in Java offers a robust structure for e-commerce management, allowing efficient and secure interaction between administrators and end customers. The classes are organized to ensure modularity, reusability, and easy code maintenance, following good object-oriented programming practices. This report summarizes the architecture and main functionality of the OnlineStore application, highlighting the importance of each class and the methods implemented for the project's success.
