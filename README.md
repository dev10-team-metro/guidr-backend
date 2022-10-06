# Guidr Backend

The backend for Team Metro's Capstone Project `Guidr`.

This backend will allow users to login, as well as perform CRUD operations for `Landmarks`, `Reviews`, and `Collections` (referred to as SiteCollections).

## Database Schema Diagram

![Guidr Backend Database Schema](https://i.imgur.com/Gmwvhuf.png)

## Getting Started

- Run database creation script in `capstone-schema.sql` or `capstone-schema-test.sql`
- Make sure information is provided for `DB_URL`, `DB_USER`, and `DB_PASS` environment variables
- Run `App.java`

## Endpoints

Quick Links: [Users Overview](#users-overview) | [Collections Overview](#collections-overview) | [Landmarks Overview](#landmarks-overview) | [Reviews Overview](#reviews-overview) | [Address Overview](#address-overview) | [Facts Overview](#facts-overview)

---

### Users Overview

| Method | Endpoint                  | Requires               | Description            |
| ------ | ------------------------- | ---------------------- | ---------------------- |
| POST   | `/api/guidr/authenticate` | `username`, `password` | Used to log in a user. |

---

### User Login

Method used: **[POST]** `/api/guidr/authenticate`

On Success: Returns an object containing a token.

Parameters:

| Parameter Name | Type   | Required |
| -------------- | ------ | -------- |
| username       | string | yes      |
| password       | string | yes      |

Example of what to use:

```
{
    username: "johnsmith",
    password: "testpassword"
}
```

[Top](#endpoints)

---

### Collections Overview

| Method | Endpoint                            | Requires                             | Description                                                                      |
| ------ | ----------------------------------- | ------------------------------------ | -------------------------------------------------------------------------------- |
| GET    | `/api/guidr/collection`             | N/A                                  | Used to find all collections in the database.                                    |
| GET    | `/api/guidr/collection/:id`         | N/A                                  | Ued to find a specific collection by id.                                         |
| GET    | `api/guidr/collection/:state/:city` | N/A                                  | Used to find all collections related to a specific city within a specific state. |
| POST   | `api/guidr/collection`              | Successful Login of Any Account      | Used to create a collection.                                                     |
| PUT    | `api/guidr/collection/:id`          | Successful Login of Any Account      | Used to update a specific collection.                                            |
| DELETE | `api/guidr/collection/:id`          | Successful Login of an Admin Account | Used to delete a specific collection.                                            |

---

### Get Collections

Method used: **[GET]** `/api/guidr/collection`

On Success: Returns an array of all collections in database. Landmarks and Reviews for each Collection are not included.

Parameters:

None.

---

### Get Specific Collection

Method used: **[GET]** `/api/guidr/collection/:id`

On Success: Returns an array with just the collection specified with landmarks and reviews included.

Parameters:

None.

---

### Get Collection By City

Method used: **[GET]** `api/guidr/collection/:state/:city`

On Success: Returns an array with the collections located within a specific city with landmarks and reviews included.

Parameters:

None.

---

### Add Collection

Method used: **[POST]** `api/guidr/collection`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type                      | Required                               | Notes                                       |
| -------------- | ------------------------- | -------------------------------------- | ------------------------------------------- |
| Authorization  | **Header**                | Yes, Acquired from a successful login. | Must have `Bearer ` before token.           |
| name           | string                    | yes                                    | The name of the collection.                 |
| description    | string                    | yes                                    | The description for the collection.         |
| landmarks      | Array of Landmark Objects | no                                     | The Landmarks associated with a collection. |
| reviews        | Array of Review Objects   | no                                     | The Reviews associated with a collection.   |

Example of what to use:

```
{
    name: "Zoos of New York",
    description: "A Collection of All New York City Zoos"
}
```

---

### Update Collection

Method used: **[PUT]** `api/guidr/collection/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type                      | Required                               | Notes                                       |
| -------------- | ------------------------- | -------------------------------------- | ------------------------------------------- |
| Authorization  | **Header**                | Yes, Acquired from a successful login. | Must have `Bearer ` before token.           |
| collectionId   | integer                   | yes                                    | The id of the collection.                   |
| name           | string                    | yes                                    | The name of the collection.                 |
| description    | string                    | yes                                    | The description for the collection.         |
| landmarks      | Array of Landmark Objects | no                                     | The Landmarks associated with a collection. |
| reviews        | Array of Review Objects   | no                                     | The Reviews associated with a collection.   |

Example of what to use:

```
{
    collectionId: 1,
    name: "Zoos of New York",
    description: "A Collection of All Operating New York City Zoos"
}
```

---

### Delete Collection

Method used: **[DELETE]** `api/guidr/collection/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required | Notes                                                                                            |
| -------------- | ---------- | -------- | ------------------------------------------------------------------------------------------------ |
| Authorization  | **Header** | yes      | Acquired from a successful login. Requires a token from an account with an Admin role to delete. |

[Top](#endpoints)

---

### Landmarks Overview

| Method | Endpoint                  | Requires                             | Description                                       |
| ------ | ------------------------- | ------------------------------------ | ------------------------------------------------- |
| GET    | `/api/guidr/landmark`     | N/A                                  | Used to show all landmarks in the database.       |
| GET    | `/api/guidr/landmark/:id` | N/A                                  | Used to show a specific landmark in the database. |
| POST   | `/api/guidr/landmark`     | Successful Login of Any Account      | Used to add a new landmark.                       |
| PUT    | `/api/guidr/landmark/:id` | Successful Login of Any Account      | Used to edit a specific landmark.                 |
| DELETE | `/api/guidr/landmark/:id` | Successful Login of an Admin Account | Used to delete a specific landmark.               |

---

### Get Landmarks

Method used: **[GET]** `/api/guidr/landmark`

On Success: Returns an array of all landmarks in database.

Parameters:

None.

---

### Get Specific Landmark

Method used: **[GET]** `/api/guidr/landmark/:id`

On Success: Returns an array with just the landmark specified.

Parameters:

None.

---

### Add Landmark

Method used: **[POST]** `/api/guidr/landmark`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type                   | Required                               | Notes                                                      |
| -------------- | ---------------------- | -------------------------------------- | ---------------------------------------------------------- |
| Authorization  | **Header**             | Yes, Acquired from a successful login. | Must have `Bearer ` before token.                          |
| name           | string                 | yes                                    | The name of the landmark.                                  |
| price          | BigDecimal             | yes                                    | The description for the collection.                        |
| address        | Address Object         | yes                                    | The address associated with a landmark as an object.       |
| collectionId   | integer                | yes                                    | The id of the collection this landmark is associated with. |
| facts          | Array of Facts Objects | no                                     | The Facts associated with a landmark.                      |

Example of what to use:

```
{
    name: "Bronx Zoo",
    price: 41.95,
    address: {
      addressId : 1,
      address: "2300 Southern Boulevard",
      city: "Bronx",
      state: "NY",
      zipCode: 10460
    },
    collectionId: 1,
}
```

---

### Update Landmark

Method used: **[PUT]** `api/guidr/landmark/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type                   | Required                               | Notes                                                      |
| -------------- | ---------------------- | -------------------------------------- | ---------------------------------------------------------- |
| Authorization  | **Header**             | Yes, Acquired from a successful login. | Must have `Bearer ` before token.                          |
| landmarkId     | integer                | yes                                    | The id of the landmark.                                    |
| name           | string                 | yes                                    | The name of the landmark.                                  |
| price          | BigDecimal             | yes                                    | The description for the collection.                        |
| address        | Address Object         | yes                                    | The address associated with a landmark as an object.       |
| collectionId   | integer                | yes                                    | The id of the collection this landmark is associated with. |
| facts          | Array of Facts Objects | no                                     | The Facts associated with a landmark.                      |

Example of what to use:

```
{
    landmarkId: 1,
    name: "Bronx Zoo",
    price: 41.95,
    address: {
      addressId : 1,
      address: "2300 Southern Boulevard",
      city: "Bronx",
      state: "NY",
      zipCode: 10460
    },
    collectionId: 1,
}
```

---

### Delete Landmark

Method used: **[DELETE]** `api/guidr/landmark/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required | Notes                                                                                            |
| -------------- | ---------- | -------- | ------------------------------------------------------------------------------------------------ |
| Authorization  | **Header** | yes      | Acquired from a successful login. Requires a token from an account with an Admin role to delete. |

[Top](#endpoints)

---

### Reviews Overview

| Method | Endpoint            | Requires                            | Description                               |
| ------ | ------------------- | ----------------------------------- | ----------------------------------------- |
| GET    | `/api/guidr/review` | N/A                                 | Used to show all reviews in the database. |
| POST   | `/api/review/`      | Successful Login with Any Account   | Used to add a review.                     |
| PUT    | `/api/review/:id/`  | Successful Login with Any Account   | Used to edit a specific review.           |
| DELETE | `/api/review/:id/`  | Successful Login with Admin Account | Used to delete a specific review.         |

---

### Get Reviews

Method used: **[GET]** `api/guidr/review`

On Success: Returns an array with the reviews in the database.

Parameters:

None.

---

### Add Review

Method used: **[POST]** `api/guidr/review`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required                               | Notes                                                |
| -------------- | ---------- | -------------------------------------- | ---------------------------------------------------- |
| Authorization  | **Header** | Yes, Acquired from a successful login. | Must have `Bearer ` before token.                    |
| description    | string     | no                                     | The text associated with the review.                 |
| rating         | BigDecimal | yes                                    | The rating associated with the review.               |
| userId         | integer    | yes                                    | The id of the User associated with the review.       |
| collectionId   | integer    | yes                                    | The id of the Collection associated with the review. |

Example of what to use:

```
{
   description: "We loved it!",
   rating: 5.0,
   userId: 1,
   collectionId: 1
}
```

---

### Update Review

Method used: **[PUT]** `api/guidr/review/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required                               | Notes                                                |
| -------------- | ---------- | -------------------------------------- | ---------------------------------------------------- |
| Authorization  | **Header** | Yes, Acquired from a successful login. | Must have `Bearer ` before token.                    |
| reviewId       | integer    | yes                                    | The id of the review to edit.                        |
| description    | string     | no                                     | The text associated with the review.                 |
| rating         | BigDecimal | yes                                    | The rating associated with the review.               |
| userId         | integer    | yes                                    | The id of the User associated with the review.       |
| collectionId   | integer    | yes                                    | The id of the Collection associated with the review. |

Example of what to use:

```
{
   reviewId: 1,
   description: "We loved it!",
   rating: 5.0,
   userId: 1,
   collectionId: 1
}
```

---

### Delete Review

Method used: **[DELETE]** `api/guidr/review/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required | Notes                                                                                            |
| -------------- | ---------- | -------- | ------------------------------------------------------------------------------------------------ |
| Authorization  | **Header** | yes      | Acquired from a successful login. Requires a token from an account with an Admin role to delete. |

[Top](#endpoints)

---

### Address Overview

| Method | Endpoint                  | Requires                            | Description                                 |
| ------ | ------------------------- | ----------------------------------- | ------------------------------------------- |
| GET    | `/api/guidr/address`      | N/A                                 | Used to show all addresses in the database. |
| POST   | `/api/guidr/address/`     | Successful Login with Any Account   | Used to add a address.                      |
| PUT    | `/api/guidr/address/:id/` | Successful Login with Any Account   | Used to edit a specific address.            |
| DELETE | `/api/guidr/address/:id/` | Successful Login with Admin Account | Used to delete a specific address.          |

---

### Get Addresses

Method used: **[GET]** `api/guidr/address`

On Success: Returns an array with the addresses in the database.

Parameters:

None.

---

### Add Address

Method used: **[POST]** `api/guidr/address`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required                               | Notes                                                                              |
| -------------- | ---------- | -------------------------------------- | ---------------------------------------------------------------------------------- |
| Authorization  | **Header** | Yes, Acquired from a successful login. | Must have `Bearer ` before token.                                                  |
| address        | string     | yes                                    | The street address of an address.                                                  |
| city           | string     | yes                                    | The city the address is located in.                                                |
| state          | string     | yes                                    | The state the address is located in. **Note: State must be 2 letter abbreviation** |
| zipCode        | integer    | yes                                    | The zip code the address is located in.                                            |
| latitude       | BigDecimal | no                                     | The latitude of the address.                                                       |
| longitude      | BigDecimal | no                                     | The longitude of the address.                                                      |

Example of what to use:

```
{
    address: "2300 Southern Boulevard",
    city: "Bronx",
    state: "NY",
    zipCode: 10460
}
```

---

### Update Address

Method used: **[PUT]** `api/guidr/address/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required                               | Notes                                                                              |
| -------------- | ---------- | -------------------------------------- | ---------------------------------------------------------------------------------- |
| Authorization  | **Header** | Yes, Acquired from a successful login. | Must have `Bearer ` before token.                                                  |
| addressId      | integer    | yes                                    | The id of the address object.                                                      |
| address        | string     | yes                                    | The street address of an address.                                                  |
| city           | string     | yes                                    | The city the address is located in.                                                |
| state          | string     | yes                                    | The state the address is located in. **Note: State must be 2 letter abbreviation** |
| zipCode        | integer    | yes                                    | The zip code the address is located in.                                            |
| latitude       | BigDecimal | no                                     | The latitude of the address.                                                       |
| longitude      | BigDecimal | no                                     | The longitude of the address.                                                      |

Example of what to use:

```
{
    addressId : 1,
    address: "2300 Southern Boulevard",
    city: "Bronx",
    state: "NY",
    zipCode: 10460
}
```

---

### Delete Address

Method used: **[DELETE]** `api/guidr/address/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required | Notes                                                                                            |
| -------------- | ---------- | -------- | ------------------------------------------------------------------------------------------------ |
| Authorization  | **Header** | yes      | Acquired from a successful login. Requires a token from an account with an Admin role to delete. |

[Top](#endpoints)

---

### Facts Overview

| Method | Endpoint          | Requires                            | Description                             |
| ------ | ----------------- | ----------------------------------- | --------------------------------------- |
| GET    | `/api/guidr/fact` | N/A                                 | Used to show all facts in the database. |
| POST   | `/api/fact/`      | Successful Login with Any Account   | Used to add a fact.                     |
| PUT    | `/api/fact/:id/`  | Successful Login with Any Account   | Used to edit a specific fact.           |
| DELETE | `/api/fact/:id/`  | Successful Login with Admin Account | Used to delete a specific fact.         |

---

### Get Facts

Method used: **[GET]** `api/guidr/fact`

On Success: Returns an array with the facts in the database.

Parameters:

None.

---

### Add Fact

Method used: **[POST]** `api/guidr/fact`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required                               | Notes                                                                  |
| -------------- | ---------- | -------------------------------------- | ---------------------------------------------------------------------- |
| Authorization  | **Header** | Yes, Acquired from a successful login. | Must have `Bearer ` before token.                                      |
| description    | string     | yes                                    | The text associated with the fact.                                     |
| image          | string     | no                                     | An image associated with the fact, written as a path to a file or URL. |
| landmarkId     | integer    | yes                                    | The landmark associated with the fact.                                 |

Example of what to use:

```
{
   description: "The Bronx Zoo receives over 2 million visitors a year!",
   landmarkId: 1
}
```

---

### Update Fact

Method used: **[PUT]** `api/guidr/fact/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required                               | Notes                                                                  |
| -------------- | ---------- | -------------------------------------- | ---------------------------------------------------------------------- |
| Authorization  | **Header** | Yes, Acquired from a successful login. | Must have `Bearer ` before token.                                      |
| factId         | integer    | yes                                    | The id of the fact.                                                    |
| description    | string     | yes                                    | The text associated with the fact.                                     |
| image          | string     | no                                     | An image associated with the fact, written as a path to a file or URL. |
| landmarkId     | integer    | yes                                    | The landmark associated with the fact.                                 |

Example of what to use:

```
{
   factId: 1,
   description: "The Bronx Zoo receives over 2 million visitors a year!",
   landmarkId: 1
}
```

---

### Delete Fact

Method used: **[DELETE]** `api/guidr/fact/:id`

On Success: Returns nothing.

Parameters:

| Parameter Name | Type       | Required | Notes                                                                                            |
| -------------- | ---------- | -------- | ------------------------------------------------------------------------------------------------ |
| Authorization  | **Header** | yes      | Acquired from a successful login. Requires a token from an account with an Admin role to delete. |

[Top](#endpoints)
