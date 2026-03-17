# Product Inventory
Description: I am building this profuct project to understand how the REST API works in springboot 

# How to run:
mvn spring-boot: run

# API Endpoints

| Method     | Endpoint             | Description                |
| ---------- | -------------------- | -------------------------- |
| **POST**   | `/api/products`      | Create a new product       |
| **GET**    | `/api/products`      | Get all products           |
| **GET**    | `/api/products/{id}` | Get a product by its ID    |
| **PUT**    | `/api/products/{id}` | Update an existing product |
| **DELETE** | `/api/products/{id}` | Delete a product by its ID |

# Example cURL Commands:
Note: if you are running on vs code -> use the cmd terminal

**Create a Product** 

curl -X POST http://localhost:8080/api/products \
-H "Content-Type: application/json" \
-d "{\"name\":\"ipad\",\"category\":\"Electronics\",\"price\":999.99,\"quantity\":10}"

**Update a Product**

curl -X PUT http://localhost:8080/api/products/1 \
-H "Content-Type: application/json" \
-d "{\"name\":\"iPad Pro\",\"category\":\"Electronics\",\"price\":1299.99,\"quantity\":5}"

**Get all the products**

curl http://localhost:8080/api/products

**Get Product by Id**

curl http://localhost:8080/api/products/1
