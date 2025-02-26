Restaurant self-order application

This is a RESTful web server for restaurant self-order application.
•	Tech Stack: Java, SpringBoot, MyBatis, Redis, MySQL, WebSocket, Spring MVC, JWT Authentication.
•	Customers scan QR code to browse items and place order.
•	Admin APIs for managing items and orders. 
•	Newly ordered item info will be pushed to frontend by WebSocket.
•	Redis as middleware to serve item query. 

The work flow:
1. Convert yourdomain.com/order/{tableNumber} to QR code. Customers scan this code to browse items and place order.
2. Since this API is excluded from JWT authentication, requiring a phone number to get temp code is necessary.
3. The kitchen staff should have access to a dedicated webpage. After the order has been created, dish items will be pushed (WebSocket) to that page.
4. The restaurant servers should have access to a page for fulfilling/cancelling dish items.
5. Cashiers perform checkout on the Orders page.
**Sending temp code to phone numbers for authentication is not implemented/integrated.

APIs:
JWT excluded:
GET  /order/{tableNUmber}     Get items info from (Redis cache if available) DB.
GET  /order/category          Get category list
POST /order/place             Place an order

JWT Authentication:
POST /admin/category/add      Add new category
POST /admin/category/update   Update an existing category
POST /admin/login             Login, if successful, return token.
POST /admin/addEmployee       Add a new employee, password hashed.
POST /admin/items/add         Add new item
POST /admin/items/update      Update an existing item
GET  /admin/orders            Get today's orders.
GET  /admin/historyOrders     Get all orders.
GET  /admin/orders/{orderId}  Get all info for an order.
POST /admin/orders/manage     Change the status of an order (fulfill/cancel).
POST /admin/orders/manageDish Change the status of an item in the order (fulfill/cancel).

SQL table examples:
![image](https://github.com/user-attachments/assets/8119fe28-69b7-41f5-83a7-4689556dab79)
