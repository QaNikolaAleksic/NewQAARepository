package com.projectname.api.tests.data.provider;

import com.projectname.api.client.data.model.users.create.CreateBooksRequest;
import com.projectname.api.client.data.model.users.create.CreateUserRequest;
import com.projectname.api.client.data.model.users.update.UpdateBooksRequest;
import com.projectname.api.client.data.model.users.update.UpdateUserRequest;
import com.projectname.api.client.utils.RandomStringGenerator;
import com.projectname.api.tests.constants.DataProviderNames;
import org.testng.annotations.DataProvider;

import java.time.LocalDateTime;

public class UserProvider {

    @DataProvider(name = DataProviderNames.VERIFY_CREATE_USER)
    public static Object[][] verifyCreateUser() {
        LocalDateTime date = LocalDateTime.now();
        return new Object[][]{
                {"WithRandomNameAndJob", new CreateUserRequest(RandomStringGenerator.createRandomStringAlphanumericWithLen(5),
                        RandomStringGenerator.createRandomStringAlphanumericWithLen(5))},
                {"AsQaAutomationEngineer", new CreateUserRequest("Jason Kidd " + date,
                        "QA Automation Engineer")},
        };
    }
    @DataProvider(name = DataProviderNames.VERIFY_UPDATE_USER)
    public static Object[][] verifyUpdateUser() {
        return new Object[][]{
                {"WithRandomNameAndJob", prepareRandomUpdateUserRequest(), getRandomId()},
                {"AsQaAutomationEngineer", new UpdateUserRequest("Jason Kidd ",
                        "QA Automation Engineer"), getRandomId()}
        };
    }

    public static UpdateUserRequest prepareRandomUpdateUserRequest() {
        UpdateUserRequest userRequest = new UpdateUserRequest();
        userRequest.setJob(RandomStringGenerator.createRandomStringWithLen(5));
        userRequest.setName(RandomStringGenerator.createRandomStringAlphanumericWithLen(6));
        return userRequest;
    }

    public static String getRandomId() {
        return "2";
    }

    @DataProvider(name = DataProviderNames.VERIFY_CREATE_BOOKS)
    public static Object[][] verifyCreateBooks() {
        LocalDateTime date = LocalDateTime.now();
        return new Object[][]{
                {"WithSetData", new CreateBooksRequest(10, "Book", "description", 100, "Excerpt", date.toString())},
        };
    }

    @DataProvider(name = DataProviderNames.VERIFY_UPDATE_BOOKS)
    public static Object[][] verifyUpdateBooks() {
        LocalDateTime date = LocalDateTime.now();
        return new Object[][]{
                {"WithSetData", new UpdateBooksRequest(10, "Book", "description", 100, "Excerpt", date.toString())},
        };
    }
}
