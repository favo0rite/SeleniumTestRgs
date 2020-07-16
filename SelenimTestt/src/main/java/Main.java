import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.Assert.assertEquals;


public class Main extends Settings {

    @Test
    public void register() throws InterruptedException {
        //2 - Кликаем на меню
        driver.findElement(By.xpath("//li[@class='dropdown adv-analytics-navigation-line1-link current']")).click();
        //3 - Кликаем на ДМС
        driver.findElement(By.xpath("//a[contains(text(), 'ДМС')]")).click();
        //4 - проверяем заголовок "Добровольное медицинское страхование"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[@class='content-document-header']")));
        WebElement head = driver.findElement(By.xpath("//h1[@class='content-document-header']"));
        assertEquals("Заголовок не совпал", "ДМС — добровольное медицинское страхование", head.getText());
        //5 - Нажимаем на кнопку "Отправить заявку"
        driver.findElement(By.xpath("//a[@class='btn btn-default text-uppercase hidden-xs adv-analytics-navigation-desktop-floating-menu-button']")).click();
        //6 - Проверяем заголовок "Заявка на добровольное медицинское страхование"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-header']//b")));

        WebElement headInForm = driver.findElement(By.xpath("//div[@class='modal-header']//b"));
        assertEquals("Заголовок не совпал","Заявка на добровольное медицинское страхование", headInForm.getText());


        //7 - Заполняем Поля
        //Имя
        String xpathFirstName = "//input[@name='FirstName']";
        WebElement inputFirstName = driver.findElement(By.xpath(xpathFirstName));
        inputFirstName.sendKeys("Имя");
        //Фамилия
        String xpathLastName = "//input[@name='LastName']";
        WebElement inputLastName = driver.findElement(By.xpath(xpathLastName));
        inputLastName.sendKeys("Фамилия");
        //Отчество
        String xpathMiddleName = "//input[@name='MiddleName']";
        WebElement inputMiddleName = driver.findElement(By.xpath(xpathMiddleName));
        inputMiddleName.sendKeys("Отчество");
        //Выбираем регион
        String xpathRegion = "//select[@name='Region']";
        driver.findElement(By.xpath(xpathRegion)).click();

        driver.findElement(By.xpath("//select[@name='Region']/option[text()='Москва']")).click();
        //Вводим телефон
        String xpathPhone = "//label[contains(text(),'Телефон')]//following-sibling::input";
        WebElement phone = driver.findElement(By.xpath(xpathPhone));
        phone.click();
        phone.sendKeys("9165244678");
        //Вводим емейл
        String xpathEmail ="//input[@name='Email']";
        driver.findElement(By.xpath(xpathEmail)).sendKeys("qwertyqwerty");
        //Вводим текст в комментарий
        String xpathComment = "//textarea";
        driver.findElement(By.xpath(xpathComment)).sendKeys("Я согласен на обработку");
        //Нажимаем на чекбокс
        driver.findElement(By.xpath("//input[@class='checkbox']")).click();

        //8 Проверяем введенные значения
        String valueAttribute = "value";
        //Имя
        String actualFirstName = driver.findElement(By.xpath(xpathFirstName)).getAttribute(valueAttribute);
        assertEquals("Имя не совпадает", "Имя", actualFirstName );
        //Фамалия
        String actualLastName = driver.findElement(By.xpath(xpathLastName)).getAttribute(valueAttribute);
        assertEquals("Фамилия не совпадает", "Фамилия", actualLastName);
        //Отчество
        String actualMiddleName = driver.findElement(By.xpath(xpathMiddleName)).getAttribute(valueAttribute);
        assertEquals("Отчество не совпадает", "Отчество", actualMiddleName);
        //Регион
        String actualRegion = driver.findElement(By.xpath(xpathRegion)).getAttribute(valueAttribute);
        assertEquals("Регион не совпадает","77", actualRegion);
        //Телефон
        String actualPhone = driver.findElement(By.xpath(xpathPhone)).getAttribute(valueAttribute);
        assertEquals("Телефон не совпадает","+7 (916) 524-46-78", actualPhone);
        //Email
        String actualEmail = driver.findElement(By.xpath(xpathEmail)).getAttribute(valueAttribute);
        assertEquals("Email не совпадает", "qwertyqwerty", actualEmail);
        //Комментарий
        String actualComment = driver.findElement(By.xpath(xpathComment)).getAttribute(valueAttribute);
        assertEquals("Комментарий не совпадает", "Я согласен на обработку", actualComment);

        //9 Нажимаем кнопку "Отправить"
        driver.findElement(By.xpath("//button[@id='button-m']")).click();
        //10 Проверяем, что у эл. почте есть сообщение об ошибке
        String xpathEmailError = "//span[text()='Введите адрес электронной почты']";
        String actualEmailError = driver.findElement(By.xpath(xpathEmailError)).getText();
        assertEquals("Сообщение об ошибке не совпадает", "Введите адрес электронной почты", actualEmailError);



    }


}
