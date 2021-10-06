package model.chart;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Jcharts {

	private Map<String, Integer> toSchoolNumMap = null;
	private String freehartPath = null;

	public Map<String, Integer> getToSchoolNumMap() {
		return toSchoolNumMap;
	}

	public void setToSchoolNumMap(Map<String, Integer> toSchoolNumMap) {
		this.toSchoolNumMap = toSchoolNumMap;
	}

	public String getFreehartPath() {
		return freehartPath;
	}

	public Jcharts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jcharts(Map<String, Integer> toSchoolNumMap) {
		super();
		this.toSchoolNumMap = toSchoolNumMap;
	}

	// pieDataSet setting(計算產品數量)
	private PieDataset pieDataSet() {
		DefaultPieDataset dataset = new DefaultPieDataset();

		// 計算結果紀錄
		Map<String, Integer> calcNumMap = this.getToSchoolNumMap();
		try {
			// for (int i = 0; i < calcNumMap.size(); i++) {
			dataset.setValue(" " + "遲到", calcNumMap.get("Late"));
			System.out.println("遲到 = " + calcNumMap.get("Late"));
			dataset.setValue(" " + "準時", calcNumMap.get("onTime"));
			// }
		} catch (Exception e) {
			e.printStackTrace();
			dataset = null;
		} finally {
			return dataset;
		}

	}

	/**
	 * 生成圓餅圖
	 */
	public BufferedImage doPieChart() {
		BufferedImage bufferedImage = null;
		// String pichatPig = "";

		PieDataset dataset = pieDataSet();
		JFreeChart chart = ChartFactory.createPieChart3D("出席狀況統計", // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// 設置Label字體
		plot.setLabelFont(new java.awt.Font("微軟雅黑", java.awt.Font.PLAIN, 20));
		// 設置legend字體
		chart.getLegend().setItemFont(new java.awt.Font("微軟雅黑", java.awt.Font.PLAIN, 18));
		// 圖片中顯示百分比:默認方式
		// plot.setLabelGenerator(new
		// StandardPieSectionLabelGenerator(StandardPieToolTipGenerator.DEFAULT_TOOLTIP_FORMAT));
		// 圖片中顯示百分比:自定義方式，{0} 表示選項， {1} 表示數值， {2} 表示所占比例 ,小數點後兩位
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})", NumberFormat.getNumberInstance(),
				new DecimalFormat("0.00%")));
		// 圖例顯示百分比:自定義方式， {0} 表示選項， {1} 表示數值， {2} 表示所占比例
		plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
		// 設置背景色為白色
		chart.setBackgroundPaint(Color.white);
		// 指定圖片的透明度(0.0-1.0)
		plot.setForegroundAlpha(1.0f);
		// 指定顯示的餅圖上圓形(false)還橢圓形(true)
		plot.setCircular(true);
		// 設置圖標題的字體
		java.awt.Font font = new java.awt.Font("blaclbold", java.awt.Font.CENTER_BASELINE, 20);
		TextTitle title = new TextTitle("出席狀況統計");
		title.setFont(font);
		chart.setTitle(title);
		try {
			bufferedImage = chart.createBufferedImage(600, 400);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return bufferedImage;
		}

	}

}
