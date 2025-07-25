# Postman Tests for Contacts API

## Setup Instructions

1. **Import the Collection**: Import `Contacts_API_Postman_Collection.json` into Postman
2. **Set Environment Variables**:
   - `baseUrl`: `http://localhost:8080/contact-app`
   - `jwtToken`: (will be set automatically after login)

## Test Scenarios

### 1. User Registration Test

**Request**: `POST {{baseUrl}}/api/auth/signup`
**Headers**: 
```
Content-Type: application/json
```
**Body**:
```json
{
    "userName": "testuser",
    "password": "testpass"
}
```

**Expected Response**: `200 OK`
```json
"User registered successfully!"
```

### 2. User Login Test

**Request**: `POST {{baseUrl}}/api/auth/signin`
**Headers**: 
```
Content-Type: application/json
```
**Body**:
```json
{
    "userName": "testuser",
    "password": "testpass"
}
```

**Expected Response**: `200 OK`
```json
"eyJhbGciOiJIUzI1NiJ9..." // JWT Token
```

**Postman Test Script** (to automatically set JWT token):
```javascript
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

if (pm.response.code === 200) {
    const responseJson = pm.response.json();
    pm.environment.set("jwtToken", responseJson);
}
```

### 3. Add Contact Test (Requires SUPERADMIN role)

**Request**: `POST {{baseUrl}}/api/contact/add`
**Headers**: 
```
Content-Type: application/json
Authorization: Bearer {{jwtToken}}
```
**Body**:
```json
{
    "name": "John Doe",
    "number": "+1234567890"
}
```

**Expected Response**: `200 OK`
```json
{
    "id": 1,
    "name": "John Doe",
    "number": "+1234567890",
    "user": null
}
```

### 4. Add/Edit Contact Test (by-param endpoint)

**Request**: `POST {{baseUrl}}/api/contact/by-param`
**Headers**: 
```
Content-Type: application/json
Authorization: Bearer {{jwtToken}}
```
**Body**:
```json
{
    "name": "Jane Smith",
    "number": "+0987654321"
}
```

**Expected Response**: `200 OK`
```json
{
    "id": 2,
    "name": "Jane Smith",
    "number": "+0987654321",
    "user": null
}
```

### 5. Update Contact Test

**Request**: `PUT {{baseUrl}}/api/contact/update`
**Headers**: 
```
Content-Type: application/json
Authorization: Bearer {{jwtToken}}
```
**Body**:
```json
{
    "id": 1,
    "name": "John Doe Updated",
    "number": "+1234567890"
}
```

**Expected Response**: `200 OK`
```json
{
    "id": 1,
    "name": "John Doe Updated",
    "number": "+1234567890",
    "user": null
}
```

### 6. Get All Contacts Test

**Request**: `GET {{baseUrl}}/api/contact/show-all-contatcs`
**Headers**: 
```
Authorization: Bearer {{jwtToken}}
```

**Expected Response**: `200 OK`
```json
[
    {
        "id": 1,
        "name": "John Doe Updated",
        "number": "+1234567890",
        "user": null
    },
    {
        "id": 2,
        "name": "Jane Smith",
        "number": "+0987654321",
        "user": null
    }
]
```

### 7. Get Contact by ID Test

**Request**: `GET {{baseUrl}}/api/contact/show-contact/1`
**Headers**: 
```
Authorization: Bearer {{jwtToken}}
```

**Expected Response**: `200 OK`
```json
{
    "id": 1,
    "name": "John Doe Updated",
    "number": "+1234567890",
    "user": null
}
```

### 8. Delete Contact Test

**Request**: `DELETE {{baseUrl}}/api/contact/delete/1`
**Headers**: 
```
Authorization: Bearer {{jwtToken}}
```

**Expected Response**: `200 OK`
```json
"Contact has been deleted"
```

## Error Test Cases

### 1. Unauthorized Access Test

**Request**: `POST {{baseUrl}}/api/contact/add` (without Authorization header)
**Expected Response**: `401 Unauthorized`

### 2. Invalid Contact Data Test

**Request**: `POST {{baseUrl}}/api/contact/add`
**Headers**: 
```
Content-Type: application/json
Authorization: Bearer {{jwtToken}}
```
**Body**:
```json
{
    "name": "",
    "number": ""
}
```

**Expected Response**: `400 Bad Request` or `500 Internal Server Error`

### 3. Contact Not Found Test

**Request**: `GET {{baseUrl}}/api/contact/show-contact/999`
**Headers**: 
```
Authorization: Bearer {{jwtToken}}
```

**Expected Response**: `500 Internal Server Error` (RuntimeException)

## Test Automation Script

Add this to the Collection's "Tests" tab for automatic validation:

```javascript
// Global test script for all requests
pm.test("Response time is less than 2000ms", function () {
    pm.expect(pm.response.responseTime).to.be.below(2000);
});

pm.test("Response has required headers", function () {
    pm.response.to.have.header("Content-Type");
});

// For successful responses
if (pm.response.code === 200) {
    pm.test("Response is valid JSON", function () {
        pm.response.to.be.json;
    });
}

// For error responses
if (pm.response.code >= 400) {
    pm.test("Error response has message", function () {
        const responseJson = pm.response.json();
        pm.expect(responseJson).to.have.property('message');
    });
}
```

## Running the Tests

1. **Start the Application**: Ensure your Spring Boot application is running on `http://localhost:8080`
2. **Set up Database**: Ensure PostgreSQL is running and the `cw_26` database exists
3. **Run Tests in Order**: 
   - First run the signup test
   - Then run the signin test to get the JWT token
   - Then run the contact management tests
4. **Verify Results**: Check that all tests pass and the responses match the expected format

## Troubleshooting

- **Database Connection Issues**: Check if PostgreSQL is running and the database `cw_26` exists
- **Authentication Issues**: Ensure the JWT token is properly set in the environment variable
- **Role-based Access**: Some endpoints require specific roles (SUPERADMIN, ADMIN, MODERATOR)
- **CORS Issues**: The application has CORS disabled, so requests should work from Postman 