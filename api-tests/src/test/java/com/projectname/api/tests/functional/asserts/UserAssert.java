package com.projectname.api.tests.functional.asserts;

import com.projectname.api.client.calls.UserAPI;
import com.projectname.api.client.data.model.users.byid.GetBooksByIdResponse;
import com.projectname.api.client.data.model.users.create.CreateBooksRequest;
import com.projectname.api.client.data.model.users.create.CreateBooksResponse;
import com.projectname.api.client.data.model.users.create.CreateUserResponse;
import com.projectname.api.client.data.model.users.get.GetBooksRequest;
import com.projectname.api.client.data.model.users.update.UpdateBooksResponse;
import com.projectname.api.client.data.model.users.update.UpdateUserResponse;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class UserAssert {

    private SoftAssert softAssert;

    public UserAssert() {
        this.softAssert = new SoftAssert();
    }

    // soft assert that do complete check and than log all errors found in test case
    public void assertCreatedUser(CreateUserResponse actualResponse, CreateUserResponse expectedResponse) {
        if (actualResponse == null) {
            Assert.fail("User was not created");
        }
        softAssert.assertEquals(actualResponse.getName(), expectedResponse.getName(), "name didn't match");
        softAssert.assertEquals(actualResponse.getJob(), expectedResponse.getJob(), "job didn't match");
        softAssert.assertNotNull(actualResponse.getCreatedAt(), "createdAt is null");
        softAssert.assertNotNull(actualResponse.getId(), "id is null");
        this.softAssert.assertAll();
    }

    public void assertUpdateUser(UpdateUserResponse actualResponse, UpdateUserResponse expectedUserResponse) {
        if (actualResponse == null) {
            Assert.fail("User is not updated");
        }
        softAssert.assertEquals(actualResponse.getName(), expectedUserResponse.getName(), "Name didn't match");
        softAssert.assertEquals(actualResponse.getJob(), expectedUserResponse.getJob(), "Job didn't match");
        softAssert.assertNotNull(actualResponse.getUpdatedAt(), "Updated at is null");
        softAssert.assertAll();
    }

    public void assertCreateBooks(CreateBooksResponse actualResponse, CreateBooksResponse expectedResponse) {
        if (actualResponse == null) {
            Assert.fail("Book is not created");
        }
        softAssert.assertEquals(actualResponse.getTitle(), expectedResponse.getTitle(), "Title didn't match");
        softAssert.assertEquals(actualResponse.getDescription(), expectedResponse.getDescription(), "Description didn't match");
        softAssert.assertEquals(actualResponse.getPageCount(), expectedResponse.getPageCount(), "Page count didn't match");
        softAssert.assertEquals(actualResponse.getExcerpt(), expectedResponse.getExcerpt(), "Excerpt didn't match");
        softAssert.assertEquals(actualResponse.getPublishDate(), expectedResponse.getPublishDate(), "Date didn't match");
        softAssert.assertAll();
    }

    public void assertGetBooksById(CreateBooksResponse actualResponse, GetBooksByIdResponse expectedResponse) {
        if (actualResponse == null) {
            Assert.fail("Books id doesnt match");
        }
        softAssert.assertEquals(actualResponse.getTitle(), expectedResponse.getTitle(), "Title didn't match");
        softAssert.assertEquals(actualResponse.getDescription(), expectedResponse.getDescription(), "Description didn't match");
        softAssert.assertEquals(actualResponse.getPageCount(), expectedResponse.getPageCount(), "Page count didn't match");
        softAssert.assertEquals(actualResponse.getExcerpt(), expectedResponse.getExcerpt(), "Excerpt didn't match");
        softAssert.assertEquals(actualResponse.getPublishDate(), expectedResponse.getPublishDate(), "Date didn't match");
        softAssert.assertAll();
    }

    public void assertUpdateBooks(UpdateBooksResponse actualResponse, UpdateBooksResponse expectedResponse) {
        if (actualResponse == null) {
            Assert.fail("Books id doesnt match");
        }
        softAssert.assertEquals(actualResponse.getTitle(), expectedResponse.getTitle(), "Title didn't match");
        softAssert.assertEquals(actualResponse.getDescription(), expectedResponse.getDescription(), "Description didn't match");
        softAssert.assertEquals(actualResponse.getPageCount(), expectedResponse.getPageCount(), "Page count didn't match");
        softAssert.assertEquals(actualResponse.getExcerpt(), expectedResponse.getExcerpt(), "Excerpt didn't match");
        softAssert.assertEquals(actualResponse.getPublishDate(), expectedResponse.getPublishDate(), "Date didn't match");
        softAssert.assertAll();
    }

    public void assertDeleteBooks(GetBooksByIdResponse getBooksByIdResponse) {
        softAssert.assertNull(getBooksByIdResponse, "Crocodile not deleted");
        softAssert.assertAll();
    }

    public void assertListOfBooks(GetBooksByIdResponse[] getBooksByIdResponse) {
        for(int i = 0; i < getBooksByIdResponse.length; i++) {
            softAssert.assertFalse(getBooksByIdResponse[i].getTitle().isEmpty(), "Title is not empty");
        }
        softAssert.assertAll();
    }
}
