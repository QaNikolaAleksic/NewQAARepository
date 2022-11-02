package com.projectname.api.client.constants;

//List of endpoint as constants
public class ApiEndpoints {

    public static final String USERS = "api/users";
    public static final String users(String userId) {
        return USERS + "/" + userId;
    }

    public static final String BOOKS = "api/books";

    public static final String books(String booksId) {
        return BOOKS + "/" + booksId;
    }

}
