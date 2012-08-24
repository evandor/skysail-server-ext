package de.twenty11.skysail.server.ext.excel.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


public class ImportIT {

    private static final String FILE_EXTENSION_FOR_EXCEL = "*.xls";

    @Test
    public void test() {
        try {
            final HSSFWorkbook workbook = new HSSFWorkbook(findeExcelResource().getInputStream());
            HSSFSheet firstSheet = workbook.getSheetAt(0);
            final HSSFRow ersteZeile = firstSheet.getRow(0);
            
            for (int spaltenIndex = 0; spaltenIndex < ersteZeile.getLastCellNum(); spaltenIndex++) {
                System.out.println(ersteZeile.getCell(spaltenIndex).getStringCellValue());
            }
            for (int zeilenIndex = 1; zeilenIndex < firstSheet.getLastRowNum(); zeilenIndex++) {
                final HSSFRow row = firstSheet.getRow(zeilenIndex);
                if (row == null) {
                    return;
                } else {
                    for (int spaltenIndex = 0; spaltenIndex < row.getLastCellNum(); spaltenIndex++) {
                        HSSFCell cell = row.getCell(spaltenIndex);
                        int ultimateCellType = ultimateCellType(cell);
                        switch (ultimateCellType) {
                        case HSSFCell.CELL_TYPE_STRING:
                            System.out.println(cell.getStringCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_BOOLEAN:
                            System.out.println(cell.getBooleanCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_ERROR:
                            System.out.println(cell.getErrorCellValue());
                            break;
                        case HSSFCell.CELL_TYPE_NUMERIC:
                            System.out.println(cell.getNumericCellValue());
                            break;
                        default:
                            System.out.println(cell.getStringCellValue());
                            break;
                        }
                    }
                }
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //fail("Not yet implemented");
    }
    
    private static int ultimateCellType(Cell c) {
        int type = c.getCellType();
        if (type == Cell.CELL_TYPE_FORMULA)
            type = c.getCachedFormulaResultType();
        return type;
    }
    
    private Resource findeExcelResource() throws IOException {

        String pfad = "classpath*:/input/";
        String filter = System.getProperty("input.filter");

        if (filter != null && !filter.isEmpty()) {
            //log.debug("Filtering: " + filter);
            pfad = pfad + filter;
        }

        //log.info("Searching for files in [" + pfad + FILE_EXTENSION_FOR_EXCEL + "]");
        PathMatchingResourcePatternResolver pmrpr = new PathMatchingResourcePatternResolver();
        if (pmrpr.getResources(pfad + FILE_EXTENSION_FOR_EXCEL).length == 0) {
            throw new IllegalStateException("Keine Excel-Dateien gefunden!");
        }
        Resource[] resources = pmrpr.getResources(pfad + FILE_EXTENSION_FOR_EXCEL);
        return resources[0];
    }

}
