package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Object[][] getTestData(String filePath, String sheetName) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

			DataFormatter formatter = new DataFormatter();

			List<Object[]> dataList = new ArrayList<>();

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);
				if (row == null)
					continue;

				boolean isEmptyRow = true;
				Object[] rowData = new Object[colCount];

				for (int j = 0; j < colCount; j++) {

					Cell cell = row.getCell(j);
					String value = formatter.formatCellValue(cell);

					if (!value.isEmpty()) {
						isEmptyRow = false;
					}

					rowData[j] = value;
				}

				if (!isEmptyRow) {
					dataList.add(rowData);
				}
			}

			workbook.close();
			fis.close();

			return dataList.toArray(new Object[0][]);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}