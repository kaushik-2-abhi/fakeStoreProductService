Project Overview: Integrating with Fake Store API
Description:
This project aims to seamlessly integrate with the Fake Store API to retrieve product data. By leveraging RESTful APIs, we connect to the Fake Store API using RestTemplate. Our implementation focuses on performance optimization and adheres to software design principles.

Key Features
RESTful Integration: We communicate with the Fake Store API to fetch product details, including pricing, descriptions, and availability.

Redis for Performance: To enhance data retrieval efficiency, we’ve employed Redis, a high-performance in-memory data store. Redis allows us to cache frequently accessed data, reducing the need for repeated requests and improving overall system responsiveness.

Builder Pattern: Our codebase follows the builder pattern for constructing complex objects. This design choice simplifies object creation and configuration, making our code more maintainable and extensible.

Solid Principles: We’ve incorporated SOLID principles into our design:

Getting Started
Clone this repository.
Configure your Fake Store API credentials in the appropriate configuration file.
Run the application and explore the product data endpoints.
