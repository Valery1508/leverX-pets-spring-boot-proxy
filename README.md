# LeverX Pets Service

# Task description:
1) Spring Boot
3) Hibernate
4) Validation API

# Database info:
- postgresql, h2
- one pet has one owner
- one owner may have many pets
- schema:
![image](https://user-images.githubusercontent.com/58391822/119480600-828e4400-bd5a-11eb-9b82-3a1f5f82d4ed.png)

# Postman link for testing:
https://www.getpostman.com/collections/905c0d4b291715293135

# Three profiles:
1) dev - postgres
2) prod - h2 database
3) cloud - postgreSQL service on cloud foundry

To change profile run: 
```
mvn -Pprod clean spring-boot:run
```
In this case we run application with **prod** profile.

# Deploy on SAP Cloud Foundry
1. run: 
   ```
   mvn -Pprod package
   ```
2. go to the root of project in command line and run:
   ```
   cf push
   ```
   
# Connect cloud foundry postgreSQL to IntelliJ IDEA
1. Open ssh-tunnel:
   
   1.1 Enable SSH for your application. Open terminal and run:
   ```
   cf enable-ssh <app_name_on_cloud_foundry>
   ```
   - <app_name_on_cloud_foundry> is name of your application on cloud foundry. 
     
   For instance, **cf enable-ssh pets-service**.

   1.2 Configure the SSH tunnel. Run in the terminal:
   ```
   cf ssh -L localhost:<port>:<cloud_foundry_db_host>:<cloud_foundry_db_port> <app_name_on_cloud_foundry> -N
   ```
   - <port> is any available port on your device;
   - <cloud_foundry_db_host> is database host on cloud foundry;
   - <cloud_foundry_db_port> is database port on cloud foundry;
   - <app_name_on_cloud_foundry> is name of your application on cloud foundry. 
     
   For instance, **cf ssh -L localhost:8081:postgres-7cd73d9a-76b6-421c-990a-98c01a684a0a.ce4jcviyvogb.eu-central-1.rds.amazonaws.com:2437 pets-service -N**.

2. Configure postgresql
   ```
   psql -d <cloud_foundry_db_name> -U <cloud_foundry_db_username> -p <port>
   ```
   - <cloud_foundry_db_name> is name of db on cloud foundry;
   - <cloud_foundry_db_username> is username of db on cloud foundry;
   - <port> is your selected port from 1.2 point.
   For instance, **psql -d dKrgScklCbkM -U f5cef68d7730 -p 8081**