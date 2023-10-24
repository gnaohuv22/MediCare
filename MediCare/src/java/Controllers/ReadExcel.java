/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Branch;
import Models.Employee;
import Models.EmployeeRole;
import Models.Province;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.CellValue;

public class ReadExcel {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_EMAIL = 1;
    public static final int COLUMN_INDEX_BRANCH = 2;
    public static final int COLUMN_INDEX_NAME = 3;
    public static final int COLUMN_INDEX_BIRTHDATE = 4;
    public static final int COLUMN_INDEX_GENDER = 5;
    public static final int COLUMN_INDEX_ADDRESS = 6;
    public static final int COLUMN_INDEX_WORKPLACE = 7;
    public static final int COLUMN_INDEX_PROVINCE = 8;
    public static final int COLUMN_INDEX_PHONE = 9;
    public static final int COLUMN_INDEX_ETHNIC = 10;
    public static final int COLUMN_INDEX_ROLE = 11;

    public static void main(String[] args) throws IOException {
        final String excelFilePath = "D:/All_Employee.xlsx";
        final List<Employee> books = readExcel(excelFilePath);
        for (Employee book : books) {
            System.out.println(book.toString());
        }
    }

    public static List<Employee> readExcel(String excelFilePath) throws IOException {
        List<Employee> listBooks = new ArrayList<>();

        // Get file
        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        // Get workbook
        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);

        // Get all rows
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }

            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Employee test = new Employee();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_ID:
                        test.setId(new BigDecimal((double) cellValue).intValue() + "");
                        break;
                    case COLUMN_INDEX_EMAIL:
                        test.setEmail((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_BRANCH:
                        test.setBranch(new Branch("", (String) getCellValue(cell), "", ""));
                        break;
                    case COLUMN_INDEX_NAME:
                        test.setName((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_BIRTHDATE:
                        test.setBirthDate((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_GENDER:
                        test.setGender((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_ADDRESS:
                        test.setAddress((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_WORKPLACE:
                        test.setWorkplace((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_PROVINCE:
                        test.setProvince(new Province("", (String) getCellValue(cell)));
                        break;
                    case COLUMN_INDEX_PHONE:
                    try {
                        test.setPhone(new BigDecimal((double) getCellValue(cell)).intValue() + "");
                    } catch (Exception e) {
                        test.setPhone((String) getCellValue(cell));
                    }
                    break;
                    case COLUMN_INDEX_ETHNIC:
                        test.setEthnic((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_ROLE:
                        test.setEmployeeRole(new EmployeeRole("", (String) getCellValue(cell)));
                        break;
                    default:
                        break;
                }

            }
            listBooks.add(test);
        }

        workbook.close();
        inputStream.close();

        return listBooks;
    }

    // Get Workbook
    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Get cell value
    private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
