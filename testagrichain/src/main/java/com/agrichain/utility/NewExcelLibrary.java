package com.agrichain.utility;

public class NewExcelLibrary {
	public static String path = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\TestData.xlsx";
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;
    private XSSFWorkbook workbook = null;
    private XSSFSheet sheet = null;
    private XSSFRow row = null;
    private XSSFCell cell = null;

    public NewExcelLibrary() {
        path = path;

        try {
            this.fis = new FileInputStream(path);
            this.workbook = new XSSFWorkbook(this.fis);
            this.sheet = this.workbook.getSheetAt(0);
            this.fis.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public NewExcelLibrary(String path) {
        NewExcelLibrary.path = path;

        try {
            this.fis = new FileInputStream(path);
            this.workbook = new XSSFWorkbook(this.fis);
            this.sheet = this.workbook.getSheetAt(0);
            this.fis.close();
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public int getRowCount(String sheetName) {
        int index = this.workbook.getSheetIndex(sheetName);
        if (index == -1) {
            return 0;
        } else {
            this.sheet = this.workbook.getSheetAt(index);
            int number = this.sheet.getLastRowNum() + 1;
            return number;
        }
    }

    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0) {
                return "";
            } else {
                int index = this.workbook.getSheetIndex(sheetName);
                int col_Num = -1;
                if (index == -1) {
                    return "";
                } else {
                    this.sheet = this.workbook.getSheetAt(index);
                    this.row = this.sheet.getRow(0);

                    for(int i = 0; i < this.row.getLastCellNum(); ++i) {
                        if (this.row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                            col_Num = i;
                        }
                    }

                    if (col_Num == -1) {
                        return "";
                    } else {
                        this.sheet = this.workbook.getSheetAt(index);
                        this.row = this.sheet.getRow(rowNum - 1);
                        if (this.row == null) {
                            return "";
                        } else {
                            this.cell = this.row.getCell(col_Num);
                            if (this.cell == null) {
                                return "";
                            } else if (this.cell.getCellType().name().equals("STRING")) {
                                return this.cell.getStringCellValue();
                            } else if (!this.cell.getCellType().name().equals("NUMERIC") && !this.cell.getCellType().name().equals("FORMULA")) {
                                if (this.cell.getCellType().name().equals("BLANK")) {
                                    return "";
                                } else {
                                    return String.valueOf(this.cell.getBooleanCellValue());
                                }
                            } else {
                                String cellText = String.valueOf(this.cell.getNumericCellValue());
                                if (HSSFDateUtil.isCellDateFormatted(this.cell)) {
                                    double d = this.cell.getNumericCellValue();
                                    Calendar cal = Calendar.getInstance();
                                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                                    cellText = String.valueOf(cal.get(1)).substring(2);
                                    cellText = cal.get(5) + "/" + cal.get(2) + 1 + "/" + cellText;
                                }

                                return cellText;
                            }
                        }
                    }
                }
            }
        } catch (Exception var10) {
            var10.printStackTrace();
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0) {
                return "";
            } else {
                int index = this.workbook.getSheetIndex(sheetName);
                if (index == -1) {
                    return "";
                } else {
                    this.sheet = this.workbook.getSheetAt(index);
                    this.row = this.sheet.getRow(rowNum - 1);
                    if (this.row == null) {
                        return "";
                    } else {
                        this.cell = this.row.getCell(colNum);
                        if (this.cell == null) {
                            return "";
                        } else if (this.cell.getCellType().name().equals("STRING")) {
                            return this.cell.getStringCellValue();
                        } else if (!this.cell.getCellType().name().equals("NUMERIC") && !this.cell.getCellType().name().equals("FORMULA")) {
                            return this.cell.getCellType().name().equals("BLANK") ? "" : String.valueOf(this.cell.getBooleanCellValue());
                        } else {
                            String cellText = String.valueOf(this.cell.getNumericCellValue());
                            if (HSSFDateUtil.isCellDateFormatted(this.cell)) {
                                double d = this.cell.getNumericCellValue();
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(HSSFDateUtil.getJavaDate(d));
                                cellText = String.valueOf(cal.get(1)).substring(2);
                                cellText = cal.get(2) + 1 + "/" + cal.get(5) + "/" + cellText;
                            }

                            return cellText;
                        }
                    }
                }
            }
        } catch (Exception var9) {
            var9.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }

    public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
        try {
            this.fis = new FileInputStream(path);
            this.workbook = new XSSFWorkbook(this.fis);
            if (rowNum <= 0) {
                return false;
            } else {
                int index = this.workbook.getSheetIndex(sheetName);
                int colNum = -1;
                if (index == -1) {
                    return false;
                } else {
                    this.sheet = this.workbook.getSheetAt(index);
                    this.row = this.sheet.getRow(0);

                    for(int i = 0; i < this.row.getLastCellNum(); ++i) {
                        if (this.row.getCell(i).getStringCellValue().trim().equals(colName)) {
                            colNum = i;
                        }
                    }

                    if (colNum == -1) {
                        return false;
                    } else {
                        this.sheet.autoSizeColumn(colNum);
                        this.row = this.sheet.getRow(rowNum - 1);
                        if (this.row == null) {
                            this.row = this.sheet.createRow(rowNum - 1);
                        }

                        this.cell = this.row.getCell(colNum);
                        if (this.cell == null) {
                            this.cell = this.row.createCell(colNum);
                        }

                        this.cell.setCellValue(data);
                        this.fileOut = new FileOutputStream(path);
                        this.workbook.write(this.fileOut);
                        this.fileOut.close();
                        return true;
                    }
                }
            }
        } catch (Exception var8) {
            var8.printStackTrace();
            return false;
        }
    }

    public boolean addSheet(String sheetname) {
        try {
            this.workbook.createSheet(sheetname);
            FileOutputStream fileOut = new FileOutputStream(path);
            this.workbook.write(fileOut);
            fileOut.close();
            return true;
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }
    }

    public boolean removeSheet(String sheetName) {
        int index = this.workbook.getSheetIndex(sheetName);
        if (index == -1) {
            return false;
        } else {
            try {
                this.workbook.removeSheetAt(index);
                FileOutputStream fileOut = new FileOutputStream(path);
                this.workbook.write(fileOut);
                fileOut.close();
                return true;
            } catch (Exception var5) {
                var5.printStackTrace();
                return false;
            }
        }
    }

    public boolean addColumn(String sheetName, String colName) {
        try {
            this.fis = new FileInputStream(path);
            this.workbook = new XSSFWorkbook(this.fis);
            int index = this.workbook.getSheetIndex(sheetName);
            if (index == -1) {
                return false;
            } else {
                XSSFCellStyle style = this.workbook.createCellStyle();
                this.sheet = this.workbook.getSheetAt(index);
                this.row = this.sheet.getRow(0);
                if (this.row == null) {
                    this.row = this.sheet.createRow(0);
                }

                if (this.row.getLastCellNum() == -1) {
                    this.cell = this.row.createCell(0);
                } else {
                    this.cell = this.row.createCell(this.row.getLastCellNum());
                }

                this.cell.setCellValue(colName);
                this.cell.setCellStyle(style);
                this.fileOut = new FileOutputStream(path);
                this.workbook.write(this.fileOut);
                this.fileOut.close();
                return true;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public boolean removeColumn(String sheetName, int colNum) {
        try {
            if (!this.isSheetExist(sheetName)) {
                return false;
            } else {
                this.fis = new FileInputStream(path);
                this.workbook = new XSSFWorkbook(this.fis);
                this.sheet = this.workbook.getSheet(sheetName);
                XSSFCellStyle style = this.workbook.createCellStyle();

                for(int i = 0; i < this.getRowCount(sheetName); ++i) {
                    this.row = this.sheet.getRow(i);
                    if (this.row != null) {
                        this.cell = this.row.getCell(colNum);
                        if (this.cell != null) {
                            this.cell.setCellStyle(style);
                            this.row.removeCell(this.cell);
                        }
                    }
                }

                this.fileOut = new FileOutputStream(path);
                this.workbook.write(this.fileOut);
                this.fileOut.close();
                return true;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
            return false;
        }
    }

    public boolean isSheetExist(String sheetName) {
        int index = this.workbook.getSheetIndex(sheetName);
        if (index == -1) {
            index = this.workbook.getSheetIndex(sheetName.toUpperCase());
            return index != -1;
        } else {
            return true;
        }
    }

    public int getColumnCount(String sheetName) {
        if (!this.isSheetExist(sheetName)) {
            return -1;
        } else {
            this.sheet = this.workbook.getSheet(sheetName);
            this.row = this.sheet.getRow(0);
            return this.row == null ? -1 : this.row.getLastCellNum();
        }
    }

    public int getCellRowNum(String sheetName, String colName, String cellValue) {
        for(int i = 2; i <= this.getRowCount(sheetName); ++i) {
            if (this.getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
                return i;
            }
        }

        return -1;
    }
}

