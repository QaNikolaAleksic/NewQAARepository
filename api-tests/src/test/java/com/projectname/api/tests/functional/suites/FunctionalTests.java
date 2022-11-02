package com.projectname.api.tests.functional.suites;

import com.projectname.api.client.calls.UserAPI;
import com.projectname.api.client.data.model.users.byid.GetBooksByIdResponse;
import com.projectname.api.client.data.model.users.create.CreateBooksRequest;
import com.projectname.api.client.data.model.users.create.CreateBooksResponse;
import com.projectname.api.client.data.model.users.create.CreateUserRequest;
import com.projectname.api.client.data.model.users.create.CreateUserResponse;
import com.projectname.api.client.data.model.users.get.GetBooksRequest;
import com.projectname.api.client.data.model.users.update.UpdateBooksRequest;
import com.projectname.api.client.data.model.users.update.UpdateBooksResponse;
import com.projectname.api.client.data.model.users.update.UpdateUserRequest;
import com.projectname.api.client.data.model.users.update.UpdateUserResponse;
import com.projectname.api.tests.constants.DataProviderNames;
import com.projectname.api.tests.data.provider.UserProvider;
import com.projectname.api.tests.functional.asserts.UserAssert;
import com.projectname.api.tests.init.TestBase;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

import static com.projectname.api.client.utils.Allure.logStep;

// each test class should extend TestBase in order to inherit all common behaviors and to be logged properly in report
public class FunctionalTests extends TestBase {

    // each test case should be simple, readable, in few lines and without any to technical steps. It should represent
    // user actions like as those are done manually on UI
    @Test(groups = {"regression", "smoke"})
    @Description("Allure description for this test")
    public static void verifyCreateUser() {
        LocalDateTime date = LocalDateTime.now();

        CreateUserRequest createUserRequest = new CreateUserRequest("John Doe" + date, "QA Engineer");

        logStep("INFO: Create user");
        CreateUserResponse createdUserActual = UserAPI.createUser(createUserRequest);
        logStep("PASS: User is created");

        CreateUserResponse createdUserExpected = CreateUserResponse.parseCreatedUser(createUserRequest);

        UserAssert userAssert = new UserAssert();
        logStep("INFO: Verify user is created");
        userAssert.assertCreatedUser(createdUserActual, createdUserExpected);
        logStep("PASS: Response is verified");
    }

    // test case with provider
    @Test(groups = {"regression", "smoke"}, dataProvider = DataProviderNames.VERIFY_CREATE_USER, dataProviderClass = UserProvider.class)
    @Description("Verify can create user")
    public static void verifyCreateUserWithDataProvider(String methodNameSuffix, CreateUserRequest createUserRequest) {

        logStep("INFO: Create user");
        CreateUserResponse createdUserActual = UserAPI.createUser(createUserRequest);
        logStep("PASS: User is created");

        CreateUserResponse createdUserExpected = CreateUserResponse.parseCreatedUser(createUserRequest);

        UserAssert userAssert = new UserAssert();
        logStep("INFO: Verify user is created");
        userAssert.assertCreatedUser(createdUserActual, createdUserExpected);
        logStep("PASS: Response is verified");
    }
    @Test()
    @Description("Verify can update user")
    public static void verifyUpdateUser() {
        CreateUserResponse userResponse = UserAPI.createUser(new CreateUserRequest("John Doe", "QA Engineer"));

        UpdateUserRequest userRequest = new UpdateUserRequest("Stefan", "QA");

        UpdateUserResponse actualUpdateUserResponse = UserAPI.updateUser(userRequest, userResponse.getId());

        UpdateUserResponse expectedUpdateUserResponse = UpdateUserResponse.parseExpectedUserResponse(userRequest);

        UserAssert userAssert = new UserAssert();
        userAssert.assertUpdateUser(actualUpdateUserResponse, expectedUpdateUserResponse);

    }
    @Test(dataProvider = DataProviderNames.VERIFY_UPDATE_USER, dataProviderClass = UserProvider.class)
    @Description("Verify can update user")
    public static void verifyUpdateUserWithProvider(String suffix, UpdateUserRequest userRequest, String userId) {
        UpdateUserResponse actualResponse = UserAPI.updateUser(userRequest, userId);

        UpdateUserResponse expectedResponse = UpdateUserResponse.parseExpectedUserResponse(userRequest);

        UserAssert userAssert = new UserAssert();
        userAssert.assertUpdateUser(actualResponse, expectedResponse);
    }

    @Test
    //We will send the wrong user id on purpose to get a 404 error from the server
    public void verifyTestWillReturnError() {
        UserAPI.getUserById("55");
    }

    @Test
    public void verifyAssertWillFail() {
        LocalDateTime date = LocalDateTime.now();
        CreateUserRequest createUserRequest = new CreateUserRequest("milos" + date, "QA Engineer");
        CreateUserResponse actualResponse = UserAPI.createUser(createUserRequest);
        CreateUserResponse expectedResponse = CreateUserResponse.parseCreatedUser(createUserRequest);

        //We added hardcoded data to fail the assertion on purpose
        expectedResponse.setName("Made up name");

        UserAssert userAssert = new UserAssert();
        logStep("INFO: Verify user is created");
        userAssert.assertCreatedUser(actualResponse, expectedResponse);
        logStep("PASS: Response is verified");
    }

    @Test()
    @Description("Verify can create book")
    public static void verifyCreateBooks() {
        LocalDateTime date = LocalDateTime.now();

        CreateBooksRequest createBooksRequest = new CreateBooksRequest(10, "Title", "description", 100, "Excerpt", date.toString());

        CreateBooksResponse actualCreateBooksResponse = UserAPI.createBooks(createBooksRequest);

        CreateBooksResponse expectedCreateBooksResponse = CreateBooksResponse.parseExpectedCreateBooksResponse(createBooksRequest);

        UserAssert createBooksAssert = new UserAssert();
        createBooksAssert.assertCreateBooks(actualCreateBooksResponse, expectedCreateBooksResponse);
    }

    @Test(dataProvider = DataProviderNames.VERIFY_CREATE_BOOKS, dataProviderClass = UserProvider.class)
    @Description("Verify can create book")
    public static void verifyCreateBooksWithProvider(CreateBooksRequest createBooksRequest) {

        CreateBooksResponse actualCreateBooksResponse = UserAPI.createBooks(createBooksRequest);

        CreateBooksResponse expectedCreateBooksResponse = CreateBooksResponse.parseExpectedCreateBooksResponse(createBooksRequest);

        UserAssert createBooksAssert = new UserAssert();
        createBooksAssert.assertCreateBooks(actualCreateBooksResponse, expectedCreateBooksResponse);
    }

    @Test
    @Description("Get books list")
    public void getListOfBooks() {
        GetBooksByIdResponse[] getBooksByIdResponses = UserAPI.getBooks();

        UserAssert getBooksAssert = new UserAssert();
        getBooksAssert.assertListOfBooks(getBooksByIdResponses);
    }

    @Test()
    @Description("Verify can get book by ID")
    public static void verifyGetBooksById() {
        LocalDateTime date = LocalDateTime.now();

        CreateBooksRequest createBooksRequest = new CreateBooksRequest(15, "Title1", "description1", 100, "Excerpt", date.toString());

        CreateBooksResponse actualCreateBooksResponse = UserAPI.createBooks(createBooksRequest);

        Integer id = actualCreateBooksResponse.getId();

        GetBooksByIdResponse getBooksByIdResponse = UserAPI.getBooksById(id.toString());

        UserAssert getBooksByIdAssert = new UserAssert();
        getBooksByIdAssert.assertGetBooksById(actualCreateBooksResponse, getBooksByIdResponse);
    }

    @Test()
    @Description("Verify can update book")
    public static void verifyUpdateBooks() {
        LocalDateTime date = LocalDateTime.now();

        CreateBooksResponse createBooksResponse = UserAPI.createBooks(new CreateBooksRequest(10, "Title", "description", 100, "Excerpt", date.toString()));

        UpdateBooksRequest updateBooksRequest = new UpdateBooksRequest(10, "TitleX", "descriptionX", 200, "ExcerptX", date.toString());

        UpdateBooksResponse actualUpdateBooksResponse = UserAPI.updateBooks(updateBooksRequest, createBooksResponse.getId().toString());

        UpdateBooksResponse expectedUpdateBooksResponse = UpdateBooksResponse.parseExpectedUpdateBooksResponse(updateBooksRequest);

        UserAssert updateBooksAssert = new UserAssert();
        updateBooksAssert.assertUpdateBooks(actualUpdateBooksResponse, expectedUpdateBooksResponse);

    }

    @Test(dataProvider = DataProviderNames.VERIFY_UPDATE_BOOKS, dataProviderClass = UserProvider.class)
    @Description("Verify can update book")
    public static void verifyUpdateBooksWithProvider(UpdateBooksRequest updateBooksRequest) {

        UpdateBooksResponse actualUpdateBooksResponse = UserAPI.updateBooks(updateBooksRequest, updateBooksRequest.getId().toString());

        UpdateBooksResponse expectedUpdateBooksResponse = UpdateBooksResponse.parseExpectedUpdateBooksResponse(updateBooksRequest);

        UserAssert updateBooksAssert = new UserAssert();
        updateBooksAssert.assertUpdateBooks(actualUpdateBooksResponse, expectedUpdateBooksResponse);

    }

    @Test
    @Description("Verify if book is deleted")
    public void deleteBooks() {
        LocalDateTime date = LocalDateTime.now();

        CreateBooksResponse createBooksResponse = UserAPI.createBooks(new CreateBooksRequest(10, "Title", "description", 100, "Excerpt", date.toString()));

        UserAPI.deleteBooks(createBooksResponse.getId().toString());

        GetBooksByIdResponse getBooksByIdResponse = UserAPI.getBooksById(createBooksResponse.getId().toString());

        UserAssert deleteBooksAssert = new UserAssert();
        deleteBooksAssert.assertDeleteBooks(getBooksByIdResponse);
    }

}
