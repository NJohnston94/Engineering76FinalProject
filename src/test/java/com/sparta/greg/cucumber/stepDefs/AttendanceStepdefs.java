package com.sparta.greg.cucumber.stepdefs;

import com.sparta.greg.pom.pages.components.Login;
import com.sparta.greg.pom.pages.components.PropertyLoader;
import com.sparta.greg.pom.pages.trainee.HomeTrainee;
import com.sparta.greg.pom.pages.trainee.Attendance;
import com.sparta.greg.pom.pages.trainer.HomeTrainer;
import com.sparta.greg.pom.pages.trainer.TraineeProfile;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AttendanceStepdefs {

    WebDriver webDriver;
    Login loginPage;
    HomeTrainee homePageTrainee;
    HomeTrainer homePageTrainer;
    Attendance traineeAttendance;
    Attendance trainerAttendance;
    TraineeProfile traineeProfile;
    String usernameTrainee;
    String passwordTrainee;
    String usernameTrainer;
    String passwordTrainer;

    @Given("I am logged in as a trainee and on the Trainee Attendance page")
    public void iAmLoggedInAsATraineeAndOnTheTraineeAttendancePage() {
        webDriver = new ChromeDriver();
        loginPage = new Login(webDriver);
        PropertyLoader.loadProperties();
        usernameTrainee = PropertyLoader.properties.getProperty("traineeUsername");
        passwordTrainee = PropertyLoader.properties.getProperty("traineePassword");
        homePageTrainee = loginPage.logInAsTrainee(usernameTrainee, passwordTrainee);
        traineeAttendance = homePageTrainee.goToWeeklyAttendance();
    }

    @Given("I am logged in as a trainer and on the Trainee Attendance page")
    public void iAmLoggedInAsATrainerAndOnTheTraineeAttendancePage() {
        webDriver = new ChromeDriver();
        loginPage = new Login(webDriver);
        PropertyLoader.loadProperties();
        usernameTrainer = PropertyLoader.properties.getProperty("trainerUsername");
        passwordTrainer = PropertyLoader.properties.getProperty("trainerPassword");
        homePageTrainer = loginPage.logInAsTrainer(usernameTrainer, passwordTrainer);
        traineeProfile = homePageTrainer.goToTraineeProfile();
        trainerAttendance = traineeProfile.goToTraineeAttendance();
    }

    @When("I click on week {int}")
    public void iClickOnWeek(int arg0) {
        traineeAttendance.clickWeek(arg0);
    }

    @Then("The dropdown for week {int} will be toggled")
    public void theDropdownForWeekWillBeToggled(int arg0) {
        Assertions.assertTrue(traineeAttendance.isToggledOnWeek(arg0));
        webDriver.close();
        webDriver.quit();
    }

    @When("I click on week {int} twice")
    public void iClickOnWeekNumberTwice(int arg0) {
        traineeAttendance.clickWeek(arg0);
        traineeAttendance.clickWeek(arg0);
    }

    @Then("The dropdown for week {int} will not be toggled")
    public void theDropdownForWeekNumberWillNotBeToggled(int arg0) {
        Assertions.assertFalse(traineeAttendance.isToggledOnWeek(arg0));
        webDriver.close();
        webDriver.quit();
    }

    @When("I click on week {int} as a trainer")
    public void iClickOnWeekNumberAsATrainer(int arg0) {
        trainerAttendance.clickWeek(arg0);
    }

    @Then("The dropdown for week {int} will be toggled as a trainer")
    public void theDropdownForWeekNumberWillBeToggledAsATrainer(int arg0) {
        Assertions.assertTrue(trainerAttendance.isToggledOnWeek(arg0));
        webDriver.quit();
    }

    @When("I click on week {int} twice as a trainer")
    public void iClickOnWeekNumberTwiceAsATrainer(int arg0) {
        trainerAttendance.clickWeek(arg0);
        trainerAttendance.clickWeek(arg0);
    }

    @Then("The dropdown for week {int} will not be toggled as a trainer")
    public void theDropdownForWeekNumberWillNotBeToggledAsATrainer(int arg0) {
        Assertions.assertFalse(trainerAttendance.isToggledOnWeek(arg0));
        webDriver.quit();
    }

    @When("I check the number of days in week {int}")
    public void iCheckTheNumberOfDaysInWeekNumber(int arg0) {
    }

    @Then("There will be {int} days in week {int}")
    public void thereWillBeDaysInWeek(int arg0, int arg1) {
        Assertions.assertEquals(arg0, traineeAttendance.getNumberOfDaysInWeek(arg1));
        webDriver.quit();
    }

    @When("I check the number of days in week {int} as a trainer")
    public void iCheckTheNumberOfDaysInWeekAsATrainer(int arg0) {
    }

    @Then("There will be {int} days in week {int} as a trainer")
    public void thereWillBeDaysInWeekAsATrainer(int arg0, int arg1) {
        Assertions.assertEquals(arg0, trainerAttendance.getNumberOfDaysInWeek(arg1));
        webDriver.quit();
    }
}