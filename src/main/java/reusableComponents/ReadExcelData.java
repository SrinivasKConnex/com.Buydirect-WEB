package reusableComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelData {

	static Map<String, String> data;

	public static Map<String, String> Getdatafromexcel(String Sheetname, String ColumnValue) {
		FileInputStream file = null;
		XSSFWorkbook workbook = null;
		try {
			file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\Test_Data.xlsx");

			workbook = new XSSFWorkbook(file);

			int sheets = workbook.getNumberOfSheets();
			for (int i = 0; i < sheets; i++) {
				if (workbook.getSheetName(i).equalsIgnoreCase(Sheetname)) {
					XSSFSheet s = workbook.getSheetAt(i);
					Iterator<Row> row = s.iterator();
					Row firstrow = row.next();

					int column = 0;
					boolean skipFirst = true;
					Map<String, Integer> columnIndices = new HashMap<>();
					for (Cell cel : firstrow) {
						if (skipFirst) {
							skipFirst = false;
							continue;
						}

						columnIndices.put(cel.getStringCellValue(), cel.getColumnIndex());
						data = new HashMap<>();
					}
					while (row.hasNext()) {
						Row r = row.next();
						if (r.getCell(column).getStringCellValue().equals(ColumnValue)) {

							for (Map.Entry<String, Integer> entry : columnIndices.entrySet()) {
								Cell cel = r.getCell(entry.getValue());

								if ((cel.getCellType().name().equals("NUMERIC"))||(cel.getCellType().name().equals("FORMULA"))) {
									data.put(entry.getKey(), NumberToTextConverter.toText(cel.getNumericCellValue()));
									if (DateUtil.isCellDateFormatted(cel)) {
										Date d = cel.getDateCellValue();
										String pattern = "dd/MMMM/yyyy";
										SimpleDateFormat df = new SimpleDateFormat(pattern);
										String dob = df.format(d);

										data.put(entry.getKey(), dob);
										
										}
								}
								
									
								if (cel.getCellType().name().equals("STRING")) {
									data.put(entry.getKey(), cel.getStringCellValue());
								}

							}
						}

					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}