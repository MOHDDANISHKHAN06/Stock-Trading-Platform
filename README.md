# Stock-Trading-Platform
**Stock Trading Platform Development**

In a bid to cater to the ever-growing demands of modern stock trading, I undertook the development of a comprehensive stock trading platform, fulfilling a dual role of ensuring both customer interaction and administrative control. The platform was meticulously crafted with Java Spring Boot as the backbone, ensuring robustness and scalability in the backend operations.

For the data layer, I integrated MySQL, a relational database system, to guarantee efficient data management, rapid transactions, and security. The platform provided users with a myriad of functionalities ranging from creating user accounts, trading stocks at market or set prices, to monitoring their portfolio and transaction history. Moreover, users could seamlessly manage their cash accounts, ensuring fluidity in deposits, withdrawals, and the receipt of funds from stock sales.

On the administrative front, the system was enhanced with capabilities for stock creation, defining intricate attributes such as stock ticker, volume, and initial pricing. Vital controls over market hours and schedules were instituted, ensuring trades are permissible only during specific intervals, thereby mirroring real-world stock market operations.

To enrich user experience, a dynamic GUI was implemented using ReactJS. This intuitive interface showcased available stocks, vital statistics such as ticker, price, volume, market capitalization, as well as intra-day highs and lows. The interface acted as a bridge between clients and the underlying Java Spring Boot server, seamlessly facilitating user and administrative tasks.

Addressing the project's need for real-time price fluctuations, a custom random stock price generator was designed. This ensured that stock prices exhibited realistic, gradual movements, either ascending or descending, throughout the trading day.

The platform was architected in a Client/Server model where the ReactJS web application epitomized the client, communicating with the backend APIs on the server. This design ensured the system was primed to handle multiple clients concurrently, embodying the bustling nature of stock markets.

In summary, this project was a testament to my proficiency in harnessing Java Spring Boot, MySQL, and ReactJS to deliver a holistic stock trading platform, marrying user demands with administrative controls, all while ensuring concurrency and real-time responsiveness.


Client URL for GitHub: https://github.com/MOHDDANISHKHAN06/StockTradingPlatformClient 
Server URL for GitHub: https://github.com/MOHDDANISHKHAN06/Stock-Trading-Platform

Setting up Database:
●	Install MySQL: While installing create a user to access mysql in the terminal.

mysql -u root -p

●	Create a database which you will use for your project

Setting up server: Make sure Java is installed and environment variables are set up properly:

brew install maven

git clone https://github.com/MOHDDANISHKHAN06/Stock-Trading-Platform.git

●	Modify the following details in application.properties file:
#database configuration:mysql
->spring.datasource.url=jdbc:mysql://localhost/StockTradingPlatform(Database Name)
->spring.datasource.username=sql username
->spring.datasource.password=password
->spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#Hibernate configuration
->spring.jpa.hibernate.ddl-auto=update
->spring.jpa.show-sql=true

Cd Stock-Trading-Platform
sudo mvn spring-boot:run


Setting up client: Install npm and the follow following steps on terminal

git clone https://github.com/MOHDDANISHKHAN06/StockTradingPlatformClient.git
Cd StockTradingPlatformClient
Npm install
Npm run build
Npm start
