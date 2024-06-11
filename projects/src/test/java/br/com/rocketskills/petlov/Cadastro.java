package br.com.rocketskills.petlov;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


class Cadastro {

	WebDriver driver; //transfroma a variavel em uma propriedade

	@BeforeEach
	void startTest() {

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@AfterEach
	void finishTest() {

		driver.close();
	}

	@Test
	@DisplayName("Deve cadastrar ponto de doação")
	void createPoint() {
	
		driver.get("https://petlov.vercel.app/signup");

		//checkpoint 1

		WebElement title = driver.findElement(By.cssSelector("h1"));

		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(d -> title.isDisplayed());

		assertEquals("Cadastro de ponto de doação", title.getText(), "Verificando o titulo da pagina");

		// fim do chekpoint 1 

		WebElement ponto_de_doação = driver.findElement(By.cssSelector("input[placeholder='Nome do ponto de doação']")); 
		ponto_de_doação.sendKeys("Oeiras Valley"); 

		WebElement email = driver.findElement(By.cssSelector("input[name='email']")); 
		email.sendKeys("alisson.t.bucchi@gmail.com");

		WebElement cep = driver.findElement(By.cssSelector("input[name='cep']")); 
		cep.sendKeys("16015110");

		WebElement buscar_cep = driver.findElement(By.cssSelector("input[value='Buscar CEP']")); 
		buscar_cep.click();


		WebElement numero = driver.findElement(By.cssSelector("input[name='addressNumber']")); 
		numero.sendKeys("166");

		WebElement complemento = driver.findElement(By.cssSelector("input[name='addressDetails']")); 
		complemento.sendKeys("lado Esquerdo");

		driver.findElement(By.xpath("//span[text()=\"Cachorros\"]/..")).click();

		driver.findElement(By.className("button-register")).click(); 

		//checkpoint 2 

		WebElement end_page = driver.findElement(By.cssSelector("#success-page p"));

		Wait<WebDriver> wait_end_page = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait_end_page.until(d -> end_page.isDisplayed());

		String end_page_text = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem."; 

		assertEquals(end_page_text, end_page.getText(), "Verificando a mensagem da pagina de cadastro completo");

	
	}
}
