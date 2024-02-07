<h1 align="center">Welcome to Cart challenge 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/badge/version-0.1.0-blue.svg?cacheSeconds=2592000" />
  <a href="https://unlicense.org" target="_blank">
    <img alt="License: unlicense" src="https://img.shields.io/badge/License-unlicense-yellow.svg" />
  </a>
</p>

> A Perficient Challenge.

## Relevant technologies and tools

### ⚡️ [Micronaut](https://micronaut.io/)

> Version 4.3.0

### 📦️️ [Gradle](https://gradle.org/)

> Version 8.0

### ♻️ [Lombok](https://projectlombok.org/)

> Version 1.18.30

## Install

```sh
./gradlew clean build
```




## About the challenge

There are no unit test because of the time. However, all constraints have been added. It is necessary edit code to test it.
There are two pre-inserted payment methods "Visa" and "MasterCard" and the same products and cart from the example we saw.
you can test is if you run the project and open:
- localhost:8080/cart/checkout/Visa
- localhost:8080/cart/checkout/MasterCard


## Assumptions and modifications
- Database used: H2.
- Style used: multi-layer.
- If-else validations for value have been replaced by Jakarta Validations constraints.
- As we talked, no it is possible to add new payment fees without modify code. However, you need to add them to database.
  - I have used a combination between Strategy and Decorator patterns.
  - Code will read from DB all "Fees" and then it will "decorate" each one in order to apply these fees to final price.
- There are no fill DB scripts. But, you can add all you want to DB modifying "fillDatabase" method (com.perficient.challenge.service.CartService.fillDatabase, line 60). 
- I have used Micronaut as framework in order to make it easy to build a standalone app for testing.

## How can add more fees without modify code?

- Any fee has three fields: name, percentage_fee and plus_value_fee. 
  - The name is just the way to identify the payment method for us.
  - The percentage_fee is the value that will multiply total price.
  - The plus_value_fee is the value that will be added to total price.
  - For instance: name: "Visa", percentage_fee: 0.02, plus_value_fee: 800.
- You can just add as fees as you want to the DB, run project, and then go to localhost:8080/cart/checkout/{name}



There are so many things to improve. Which? It depends on the business domain. We can talk about it next time.  

## Author

👤 **Juan Guerrero**

* Github: [@mujuanp](https://github.com/mujuanp)

## Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2024 [Juan Guerrero](https://github.com/mujuanp). <br/>
This project is [unlicense](https://unlicense.org) licensed.

***
_This README was generated with ❤️ by [readme-md-generator](https://github.com/kefranabg/readme-md-generator)_
