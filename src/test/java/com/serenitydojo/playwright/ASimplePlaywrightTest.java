package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
@UsePlaywright
public class ASimplePlaywrightTest {

    @Test
    @Story("Verify page title")
    void shouldShowThePageTitle(Page page) {
        navigateToWebsite(page, "https://practicesoftwaretesting.com");
        String title = page.title();
        Allure.step("Page title is: " + title);
        Assertions.assertTrue(title.contains("Practice Software Testing"), "Page title verification failed");
    }

    @Test
    @Story("Search functionality")
    void searchAndAssertVisibilityOfSearchTermsInTitle(Page page) {
        navigateToWebsite(page, "https://practicesoftwaretesting.com");
        performSearch(page, "Pilers");
        int matchingProductCount = page.locator(".card-title").count();
        Allure.step("Number of matching products: " + matchingProductCount);
        Assertions.assertTrue(matchingProductCount > 0, "No matching products found");
    }

    @Step("Navigate to website {url}")
    private void navigateToWebsite(Page page, String url) {
        page.navigate(url);
    }

    @Step("Perform search for {query}")
    private void performSearch(Page page, String query) {
        page.locator("[placeholder=Search]").fill(query);
        page.locator("button:has-text('Search')").click();
    }
}