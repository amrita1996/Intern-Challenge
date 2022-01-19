# Shopify technical backend challenge submission

1. [Requirements](#requirements)
2. [Running the application locally](#running-the-application-locally)
3. [Deploying the application to Heroku](#deploying-the-application-to-heroku)
4. [Testing the application](#testing-the-application)
5. [Sample Request Items API](#sample-request-items-api)
6. [Sample Request Inventory API](#sample-request-inventory-api)
7. [Current values of Category and Warehouse](#current-values-of-category-and-warehouse)

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/ca-en/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org)
<hr>

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.logistics.shopifytechnicalchallenge.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run 
```
or 
```shell
./mvnw spring-boot:run 
```
<hr>

## Deploying the application to Heroku

The application is currently deployed in Heroku.

The link to the application: https://shopify-backend-challenge-am.herokuapp.com/

Swagger link is: https://shopify-backend-challenge-am.herokuapp.com/swagger-ui.html
<hr>

## Testing the application

You can run the application locally and access the application at http://localhost:8080 and execute the APIs 
via [Postman](https://www.postman.com/).

Alternatively, you can access the application hosted on Heroku and use Postman to test the APIs.

By using the swagger link in both of the above options, you will be able to see all the APIs listed and use the try now 
option to test them.
<hr>

## Sample Request Items API

<li> Get All Items

```shell
GET https://shopify-backend-challenge-am.herokuapp.com/items
```
Request Object: None
<hr>
<li> Create a new Item 

```shell
POST https://shopify-backend-challenge-am.herokuapp.com/items
```
Sample request Object: 
```json
{
  "description": "White Tank Top",
  "sku": "WHI-TAN-TOP-124642",
  "categoryId": 1,
  "purchasedPrice": 2.97,
  "manufacturedDate": "2021-02-12",
  "warrantyDays": 400,
  "warehouseId": 1,
  "quantity": 12900
}
```
<hr>
<li> Update an existing Item 

```shell
PUT https://shopify-backend-challenge-am.herokuapp.com/items/8
```
Sample request Object:
```json
{
  "description": "White Tank Top",
  "sku": "WHI-TAN-TOP-124642",
  "categoryId": 1,
  "purchasedPrice": 2.97,
  "manufacturedDate": "2021-02-12",
  "warrantyDays": 400,
  "warehouseId": 1,
  "quantity": 12900
}
```
<hr>
<li> Delete an existing Item 

```shell
DELETE https://shopify-backend-challenge-am.herokuapp.com/items/8
```
Request Object: None
<hr>
<li> Get All items via CSV 

```shell
GET https://shopify-backend-challenge-am.herokuapp.com/items/csv
```
Request Object: None

On Postman: Download as file.
  
On SwaggerUI: Click download file button.
<hr>

## Sample Request Inventory API

<li> Get All Inventory

```shell
GET https://shopify-backend-challenge-am.herokuapp.com/inventory
```
Request Object: None
<hr>
<li> Create a new Inventory with an existing Item 

```shell
POST https://shopify-backend-challenge-am.herokuapp.com/items
```
Sample request Object:
```json
{
  "quantity": 1000,
  "itemId": 8,
  "warehouseId": 1
}
```
<hr>
<li> Update an existing Item 

```shell
PUT https://shopify-backend-challenge-am.herokuapp.com/inventory/4
```
Sample request Object:
```json
{
  "quantity": 1000,
  "itemId": 8,
  "warehouseId": 1
}
```
<hr>
<li> Delete an existing Item 

```shell
DELETE https://shopify-backend-challenge-am.herokuapp.com/inventory/4
```
Request Object: None
<hr>
<li> Get All items via CSV 

```shell
GET https://shopify-backend-challenge-am.herokuapp.com/inventory/csv
```
Request Object: None

On Postman: Download as file.
  
On SwaggerUI: Click download file button.
<hr>

## Current values of Category and Warehouse

Warehouse is currently a predefined table, and it has the following values

| warehouse_id  | warehouse_code| name                      |
| ------------- |:-------------:| -------------------------:|
| 1             | HAL-1990      | South Park Street Halifax |
| 2             | HAL-1921      | Mumford Terminal Halifax  |

Category is currently a predefined table with the following values.

| warehouse_id  | warehouse_code| name                          |
| ------------- |:-------------:| -----------------------------:|
| 1             | clo-top-1243  | Clothing Top Sleeveless       |
| 2             | clo-top-1233  | Clothing Top Half Sleeve      |
| 3             | clo-bot-3212  | Clothing-bottom-skirt-half    |
| 4             | acc-bag-1242  | Accessories Bag Tote          |

<hr>

