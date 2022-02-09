package janemiro.tests;

import janemiro.helpers.DriverUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;


public class UITests extends TestBase {
    @Test
    @Disabled
    @Description("Here is the enhanced description of the test")
    @DisplayName("Example Test")
    void exampleTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });

        step("Нажать на баннер", () -> {
            $(".top-slider-wrapper").click();
        });
    }

    @Test
    @Description("Проверка основного (верхнего) баннера")
    @DisplayName("Проверка основного баннера")
    @Severity(NORMAL)
    void bannerTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });

        step("Нажать на основной баннер", () -> {
            $(".top-slider-wrapper").click();
        });

        step("Проверить редирект на страницу предложения", () -> {
            $("hrefhref=https://www.petshop.ru/news/actions/");
        });
    }

    @Test
    @Description("Проверка добавления в корзину с баннера с Товаром дня")
    @DisplayName("Товар дня - добавление в корзину")
    @Severity(NORMAL)
    void goodOfTheDayPurchaseTest() {
        step("open " + URL, () -> {
            open(URL);
        });

        step("кликнуть на кнопку КУПИТЬ баннера с Товаром дня", () -> {
            $(".day-product__buy-btn btn_buy btn_orange j_add-to-cart-simple").click();
        });

        step("проверка добавления товара в корзину", () -> {
            byText("Товар добавлен в корзину");
        });
    }



    @Test
    @Description("На главной странице должен пристутствовать заголовок")
    @DisplayName("Smoke UI test")
    void titleTest() {
        step("open " + URL, () ->
            open(URL));

        step("Проверить текст заголовка на странице", () -> {
            String expectedTitle = "Товары для животных, зоотовары онлайн, корма для домашних животных купить в интернет-магазине Petshop.ru";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Description("Тест на проверку наличия SEVERE ошибок в консоли")
    @DisplayName("Smoke console test")
    void consoleShouldNotHaveErrorsTest() {
        step("Open url 'https://www.petshop.ru/'", () ->
            open("https://www.petshop.ru/"));

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}