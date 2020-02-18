# rbstechnicalassignment
1)Checkout the code
2)Run as springboot application
3)Inmemory database details
  url:http://localhost:9999/h2-console/login.do 
  jdbcurl=jdbc:h2:mem:testdb 
  username =sa 
  password=password 
4)Add account using beow url:
    http://localhost:9999/addAccount/rbs123456/1000
5)To view Balance
    http://localhost:9999/balance/rbs123456
6)capture withdrawal- open postman and enter below details
    localhost:9999/withDrawal
    {"accountNumber":"rbs123456","amount":10}
7)capture deposit- open postman and enter below details
    localhost:9999/deposit
    {"accountNumber":"rbs123456","amount":10}
8) credit list details:
    http://localhost:9999/transactions/Cr/rbs123456
9) Debit list details:
    http://localhost:9999/transactions/Dr/rbs123456
