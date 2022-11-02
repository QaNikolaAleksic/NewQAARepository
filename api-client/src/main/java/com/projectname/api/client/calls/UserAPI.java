package com.projectname.api.client.calls;

import com.projectname.api.client.constants.ApiEndpoints;
import com.projectname.api.client.data.model.users.byid.GetUserByIdResponse;
import com.projectname.api.client.data.model.users.create.*;
import com.projectname.api.client.data.model.users.byid.GetBooksByIdResponse;
import com.projectname.api.client.data.model.users.get.GetBooksRequest;
import com.projectname.api.client.data.model.users.update.UpdateBooksRequest;
import com.projectname.api.client.data.model.users.update.UpdateBooksResponse;
import com.projectname.api.client.data.model.users.update.UpdateUserRequest;
import com.projectname.api.client.data.model.users.update.UpdateUserResponse;
import com.projectname.api.client.utils.GsonFunctions;
import com.projectname.api.client.utils.ResponseValidation;
import com.projectname.api.client.utils.RestAssuredFunctions;
import io.restassured.response.Response;

//Methods that help parse json into model classes
public class UserAPI {

    public static CreateUserResponse createUser(CreateUserRequest createRequest) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post(createRequest, ApiEndpoints.USERS), CreateUserResponse.class);
    }

    public static GetUserByIdResponse getUserById(String userId) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get(ApiEndpoints.users(userId)), GetUserByIdResponse.class);
    }

    //Method used to parse and validate response for Integration tests
    public static ResponseValidation validateGetUserByIdResponse(String userId) {
        Response jsonResponse = RestAssuredFunctions.get(ApiEndpoints.users(userId));
        return GsonFunctions.verifyResponse(jsonResponse, GetUserByIdResponse.class);
    }

    public static UpdateUserResponse updateUser(UpdateUserRequest userRequest, String userId) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.put(userRequest, ApiEndpoints.users(userId)), UpdateUserResponse.class);
    }
    public static GetBooksByIdResponse[] getBooks() {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get(ApiEndpoints.BOOKS), GetBooksByIdResponse[].class);
    }

    public static CreateBooksResponse createBooks(CreateBooksRequest createBooksRequest) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.post(createBooksRequest, ApiEndpoints.BOOKS), CreateBooksResponse.class);
    }

    public static GetBooksByIdResponse getBooksById(String booksId) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.get(ApiEndpoints.books(booksId)), GetBooksByIdResponse.class);
    }

    public static UpdateBooksResponse updateBooks(UpdateBooksRequest updateBooksRequest, String booksId) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.put(updateBooksRequest, ApiEndpoints.users(booksId)), UpdateBooksResponse.class);
    }

    public static GetBooksRequest deleteBooks(String booksId) {
        return GsonFunctions.parseSuccessResponseToModel(RestAssuredFunctions.delete(ApiEndpoints.books(booksId)), GetBooksRequest.class);
    }


//example for retrieving list as response
//    public static List<ListOfObject> getListResponse(String accessToken) {
//        return GsonFunctions.parseSuccessResponseAsListToModel(RestAssuredFunctions.get(accessToken, ApiEndpoints.USERS), ListOfObject[].class);
//    }

//example for retrieving list as response for integration test
//    public static ResponseValidation validateListResponse(String accessToken) {
//        Response jsonResponse = RestAssuredFunctions.get(accessToken, ApiEndpoints.USERS);
//        return GsonFunctions.verifyResponse(jsonResponse, ResponseValidation[].class);
//    }
}
