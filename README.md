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

![image](https://github.com/user-attachments/assets/5c56bf00-c46c-4f04-9e3b-815cbb609977)


SQL table examples:
![image](https://github.com/user-attachments/assets/8119fe28-69b7-41f5-83a7-4689556dab79)
