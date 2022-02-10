package janemiro.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import janemiro.helpers.DriverUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static io.qameta.allure.SeverityLevel.*;
import static org.assertj.core.api.Assertions.assertThat;


public class UITests extends TestBase {
    @Test
    @Disabled
    @Description("Here is the enhanced description of the test")
    @DisplayName("Example Test")
    @Severity(MINOR)
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
        step("Открыть " + URL, () -> {
            open(URL);
        });
        sleep(5000); //ждём появления баннера с куками
        step("закрыть баннер с cookie", () -> {
            $(".CookieInformer_button__1M5XF").click();
        });
        step("кликнуть на кнопку КУПИТЬ баннера с Товаром дня", () -> {
            $((".j_add-to-cart-simple")).click();
        });
        step("проверить поп-ап", () -> {
            $(byText("Товар добавлен в корзину")).click();
        });
    }

    @Test
    @Description("Ищем товар и покупаем его в 1 клик")
    @DisplayName("Покупка в 1 клик")
    @Issue("Предложение скидки загораживает страницу")
    @Severity(NORMAL)
    void oneClickPurchaseTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });

        step("Ввводим текст в поле поиска", () -> {
            $(byClassName("MuiInputBase-inputTypeSearch")).setValue(item).pressEnter();
        });

        step("Переходим на страницу товара", () -> {
            $(byText(itemName)).click();
        });

        step("Нажимаем Купить в 1 клик", () -> {
            $(".style_one_click__2cDvD").click();
        });

        step("Проверяем, что появилась форма и можно ввести телефон", () -> {
            $(".TextInput_phone").setValue(phone).pressEnter();
        });
    }

    @Test
    @Disabled
    @Description("Поиск и добавление товара в корзину -> Оформить заказ -> Проверка формы (без настоящего заказа")
    @DisplayName("Обычнная поокупка товара")
    @Severity(NORMAL)
    void PurchaseFromCartTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });


    }

    @Test
    @Disabled
    @Description("Негативный тест на добавление товара в избранное без авторизации на сайте")
    @DisplayName("Добавление товара в избранное без авторизации на сайте")
    @Severity(NORMAL)
    void addToFavoritesUnauthorizedNegativeTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });

        step("кликнуть на кнопку КУПИТЬ баннера с Товаром дня", () -> {
            $(".day-product__buy-btn btn_buy btn_orange j_add-to-cart-simple")
                    .$(byText("Купить")).scrollTo().click();
        });

    }

    @Test
    @Disabled
    @Description("Негативный тест на оформление автозаказа без авторизации на сайте")
    @DisplayName("Автозаказ без авторизации на сайте")
    @Severity(NORMAL)
    void autoPurchaseNegativeTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });

        step("кликнуть на кнопку КУПИТЬ баннера с Товаром дня", () -> {
            $(".day-product__buy-btn btn_buy btn_orange j_add-to-cart-simple")
                    .$(byText("Купить")).scrollTo().click();
        });

    }

    @Test
    @Disabled
    @Description("Просмотр товаров из вет. аптеки")
    @DisplayName("Вет. аптека")
    @Severity(NORMAL)
    void DrugstoreTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });

        step("кликнуть на кнопку КУПИТЬ баннера с Товаром дня", () -> {
            $(".day-product__buy-btn btn_buy btn_orange j_add-to-cart-simple")
                    .$(byText("Купить")).scrollTo().click();
        });

    }

    @Test
    @Disabled
    @Description("Магазины -> Выбор города -> Поиск магазина -> Карта")
    @DisplayName("Контакты на карте")
    @Severity(NORMAL)
    void storeOnMapTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });

        step("кликнуть на кнопку КУПИТЬ баннера с Товаром дня", () -> {
            $(".day-product__buy-btn btn_buy btn_orange j_add-to-cart-simple")
                    .$(byText("Купить")).scrollTo().click();
        });

    }

    @Test
    @Description("На главной странице должен пристутствовать заголовок")
    @DisplayName("Smoke UI test")
    @Severity(BLOCKER)
    void titleTest() {
        step("Открыть" + URL, () -> {
            open(URL);
        });

        step("Проверить текст заголовка на странице", () -> {
            String expectedTitle = "Товары для животных, зоотовары онлайн, корма для домашних животных купить в интернет-магазине Petshop.ru";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Description("Тест на проверку наличия SEVERE ошибок в консоли")
    @DisplayName("Smoke console test")
    @Severity(TRIVIAL)
    void consoleShouldNotHaveErrorsTest() {
        step("Открыть " + URL, () -> {
            open(URL);
        });

        step("Console logs should not contain text 'SEVERE'", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}