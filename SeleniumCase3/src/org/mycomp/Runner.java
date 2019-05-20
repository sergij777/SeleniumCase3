package org.mycomp;

import java.util.ArrayList;
import java.util.List;

import org.mycomp.model.Good;
import org.mycomp.service.FileWriterXLS;
import org.mycomp.service.Parser;

public class Runner {

	public static void main(String[] args) {
		Parser parser = new Parser();
		
		List<Good> goodsByPrice = new ArrayList();
		goodsByPrice = parser.selectListGoodsByPrice("���������, �� � �����������", "��������", "���������", 5);
		goodsByPrice = parser.sorterGoodsByPrice(goodsByPrice);
		
		List<Good> goodsByTop = new ArrayList();
		goodsByTop = parser.selectListGoodsByTopOfSale("���������, �� � �����������", "��������", "���������", 3);
		goodsByTop = parser.sorterGoodsByPrice(goodsByTop);
		
		FileWriterXLS writer = new FileWriterXLS();
		writer.createSheet("goods_price", goodsByPrice);
		writer.createSheet("goods_top", goodsByTop);
		writer.writeDataToFile();		   
	}
}
