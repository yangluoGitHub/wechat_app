package com.weili.wechat.vo;

/**
 * 自适应行高基准cell
 * 
 * @author yyc
 * 
 */
public class AutoReportRowHeightCellVO {

	private int sheetIndex;
	private int rowIndex;
	private int columnIndex;

	private float defColumnWidthInByte; // 字节数
	private float defRowHeightInPoint = 15;

	public AutoReportRowHeightCellVO(int sheetIndex, int rowIndex, int columnIndex, float defColumnWidthInByte) {
		this.sheetIndex = sheetIndex;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.defColumnWidthInByte = defColumnWidthInByte;
	}

	public AutoReportRowHeightCellVO(int sheetIndex, int rowIndex, int columnIndex, float defColumnWidthInByte, float defRowHeightInPoint) {
		this.sheetIndex = sheetIndex;
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.defColumnWidthInByte = defColumnWidthInByte;
		this.defRowHeightInPoint = defRowHeightInPoint;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	public float getDefColumnWidthInByte() {
		return defColumnWidthInByte;
	}

	public void setDefColumnWidthInByte(float defColumnWidthInByte) {
		this.defColumnWidthInByte = defColumnWidthInByte;
	}

	public float getDefRowHeightInPoint() {
		return defRowHeightInPoint;
	}

	public void setDefRowHeightInPoint(float defRowHeightInPoint) {
		this.defRowHeightInPoint = defRowHeightInPoint;
	}

}
