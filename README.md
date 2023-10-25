# HotelBookingSystem
Hotel Management System
This is a simple Java code implementation of a Hotel Management System. It allows you to manage hotel rooms, customers, reservations, and generate reports. The system is built with object-oriented principles and uses serialization to save and load data.

Usage
Hotel Class
The Hotel class is the core component of the system. It includes the following functionalities:

Adding a Room: You can add a room to the hotel using the addRoom method.

Adding a Customer: You can add a customer to the hotel using the addCustomer method.

Booking a Room: You can book a room for a customer using the bookRoom method.

Finding a Customer by Email: You can find a customer by email using the findCustomer method.

Generating a Report: You can generate a report for a specific room using the generateReport method.

Serializing and Deserializing the Hotel Object: The system provides methods to serialize and deserialize the Hotel object to/from a file.

Room, Customer, and Booking History Classes
The Room, Customer, RoomBookHistory, and CustomerBookHistory classes are used for representing rooms, customers, and booking history.

TypeRoom Enum
The TypeRoom enum defines the types of rooms available in the hotel.

StartEndDate Class
The StartEndDate class represents the start and end date for room reservations.

Exception Handling
The code includes exception handling to handle input errors and file operations.

