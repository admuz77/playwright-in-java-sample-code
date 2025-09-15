package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@UsePlaywright
public class ASimplePlaywrightTest {

    @Test
    void shouldShowThePageTitle(Page page) {

        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();
        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }


    @Test
    void searchAndAssertVisibilityOfSearchTermsInTitle(Page page) {

        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("Pilers");
        page.locator("button:has-text('Search')").click();

        int matchingProducCount = page.locator(".card-title").count();

        Assertions.assertTrue(matchingProducCount > 0);
    }
}
