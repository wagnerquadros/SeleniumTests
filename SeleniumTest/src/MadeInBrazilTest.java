import java.io.FileReader;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class MadeInBrazilTest {
	private WebDriver browser = new ChromeDriver();;
	private static final String URL_INDEX = "https://www.madeinbrazil.com.br/";
	private String csvFile = "teste.csv";
	private CSVReader csvReader;
	private String[] arquivoCsv;
	
	
	@Before
	public void chromeDriverSetup() throws IOException {
		System.setProperty("webdriver.chrome.driver","D:\\Chrome\\chromedriver.exe");
		browser.get(URL_INDEX);
		this.csvReader = new CSVReader(new FileReader(csvFile));
	}

	@Test
	public void testBuscarProdutoFender() throws CsvValidationException, IOException {
		
		while((arquivoCsv = csvReader.readNext())!=null) {
			for(int i = 0; i < 1; i++) {
				browser.findElement(By.id("txtBuscaPrincipal")).sendKeys(arquivoCsv[0]);
				browser.findElement(By.id("btnBusca")).click();
				String buscaRealizada = browser.findElement(By.className("spotTitle")).getText();
				Assert.assertTrue(buscaRealizada.contains(arquivoCsv[0]));
			}
		}
		browser.quit();
	}
	
	@Test
	public void testVerificaFreteGratisParaCompraAcimade99() throws CsvValidationException, IOException, InterruptedException {
		
		while((arquivoCsv = csvReader.readNext())!=null) {
			for(int i = 0; i < 1; i++) {
				browser.findElement(By.id("txtBuscaPrincipal")).sendKeys(arquivoCsv[0]);
				browser.findElement(By.id("btnBusca")).click();
				browser.findElement(By.id("produto-spot-item-129387")).click();
				browser.findElement(By.id("txtCalculaFreteProduto")).sendKeys(arquivoCsv[1]);
				browser.findElement(By.id("btnCalculaFreteProduto")).click();
				
				Thread.sleep(2000);
				Assert.assertTrue(browser.getPageSource().contains(arquivoCsv[5]));
			}
		}
		browser.quit();
	}
	
	
	
	@Test
	public void testLoginUsuarioNaoCadastrado() throws CsvValidationException, IOException, InterruptedException {
		
		browser.findElement(By.className("fbits-login-link-login")).click();
		Thread.sleep(500);
		
		while((arquivoCsv = csvReader.readNext())!=null) {
			for(int i = 0; i < 1; i++) {
				browser.findElement(By.id("input")).sendKeys(arquivoCsv[2]);
				browser.findElement(By.name("senha")).sendKeys(arquivoCsv[3]);
				browser.findElement(By.name("senha")).submit();
				
				Thread.sleep(500);
				Assert.assertTrue(browser.getPageSource().contains(arquivoCsv[4]));
			}
		}
		browser.quit();
	}
	
	@Test
	public void testLoginUsuarioCadastradoSenhaInvalida() throws CsvValidationException, IOException, InterruptedException {
		
		browser.findElement(By.className("fbits-login-link-login")).click();
		Thread.sleep(500);
		
		while((arquivoCsv = csvReader.readNext())!=null) {
			for(int i = 0; i < 1; i++) {
				browser.findElement(By.id("input")).sendKeys(arquivoCsv[6]);
				browser.findElement(By.name("senha")).sendKeys(arquivoCsv[3]);
				browser.findElement(By.name("senha")).submit();
				
				Thread.sleep(500);
				Assert.assertTrue(browser.getPageSource().contains(arquivoCsv[7]));
			}
		}
		browser.quit();
	}
	
	@Test
	public void testNavegarAtePaginaNossasLojas() throws InterruptedException, CsvValidationException, IOException {
		browser.findElement(By.className("lojas-link")).click();
		Thread.sleep(500);
		
		while((arquivoCsv = csvReader.readNext())!=null) {
			for(int i = 0; i < 1; i++) {

				Assert.assertEquals(browser.getCurrentUrl(),arquivoCsv[8]);
			}
		}
		browser.quit();
	}
	
	@Test
	public void testTentarCadastrarUsurioJaCadastrado() throws InterruptedException, CsvValidationException, IOException {
		browser.findElement(By.className("fbits-login-link-cadastro")).click();
		Thread.sleep(500);
		
		while((arquivoCsv = csvReader.readNext())!=null) {
			for(int i = 0; i < 1; i++) {
				browser.findElement(By.id("Email")).sendKeys(arquivoCsv[6]);
				browser.findElement(By.id("CPF")).sendKeys(arquivoCsv[9]);
				browser.findElement(By.id("NomeCompleto")).sendKeys(arquivoCsv[10]);
				browser.findElement(By.id("DataNascimento")).sendKeys(arquivoCsv[11]);
				browser.findElement(By.id("Genero")).sendKeys(arquivoCsv[12]);
				browser.findElement(By.id("Senha")).sendKeys(arquivoCsv[3]);
				browser.findElement(By.id("ConfirmacaoSenha")).sendKeys(arquivoCsv[3]);
				browser.findElement(By.id("TelefonePrincipalDDD")).sendKeys(arquivoCsv[13]);
				browser.findElement(By.id("TelefonePrincipal")).sendKeys(arquivoCsv[14]);
				browser.findElement(By.id("NomeEndereco")).sendKeys(arquivoCsv[10]);
				browser.findElement(By.id("CEP")).sendKeys(arquivoCsv[1]);
	
				Thread.sleep(500);
				Assert.assertEquals("E-mail já cadastrado.", browser.findElement(By.id("Email-error")).getText());
			}
		}
		browser.quit();	
	}
}
