package com.agrichain.dataprovider;

public class dataProvider {
	 NewExcelLibrary obj = new NewExcelLibrary();

	    public dataProvider() {
	    }

	    @Test(
	        dataProvider = "DDT testing"
	    )
	    public Object[][] getCredentials() {
	        int rows = this.obj.getRowCount("input");
	        int column = this.obj.getColumnCount("input");
	        int actRows = rows - 1;
	        Object[][] data = new Object[actRows][column];

	        for(int i = 0; i < actRows; ++i) {
	            for(int j = 0; j < column; ++j) {
	                data[i][j] = this.obj.getCellData("input", j, i + 2);
	            }
	        }

	        return data;
	    }
	}


}
