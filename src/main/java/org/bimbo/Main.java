package org.bimbo;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.excel.GeneracionCampos;
import org.google.Login;
import org.google.RegistroCliente;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Rutas
        String perfilOriginal = "C:\\Users\\danie\\AppData\\Local\\Google\\Chrome\\User Data";
        String rutaExcel = "C:\\Users\\danie\\OneDrive\\Escritorio\\RETIRAR RUTA INDEP.xlsx";

        // Configuración del WebDriver
        ChromeOptions opciones = new ChromeOptions();
        opciones.addArguments("--user-data-dir=" + perfilOriginal);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\danie\\Documents\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(opciones);

        // Lectura del archivo Excel
        FileInputStream fis = new FileInputStream(rutaExcel);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Instancias de clases
        GeneracionCampos generacionCampos = new GeneracionCampos();
        AsignarCliente asignarCliente = new AsignarCliente();
        RetirarCliente retirarCliente=new RetirarCliente();
        Login login = new Login();
        RegistroCliente registroCliente = new RegistroCliente();

        // Creación de celdas y fila en la hoja de Excel
        generacionCampos.CreacionCeldaFila(sheet);

        //------------------------------------------------------------------
        login.InicioSesion(driver);
        registroCliente.IngresoCentrodeVentas(driver);
        int filaInicio = 1903, filaFinal = 2674;
        for (int i = filaInicio; i <= filaFinal; i++) {
            XSSFRow row = sheet.getRow(i - 1);
            System.out.println("Se esta realizando la fila "+i);
            retirarCliente.RetiradaCliente(row, driver);
            System.out.println("---------------------------------");
            saveWorkbook(workbook, rutaExcel);
        }
        workbook.close();
        driver.quit();
    }


    private static void saveWorkbook(XSSFWorkbook workbook, String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }
}
