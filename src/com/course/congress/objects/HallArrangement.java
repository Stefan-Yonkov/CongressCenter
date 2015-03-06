package com.course.congress.objects;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class HallArrangement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String BANQUET_TYPE = "Banquet";
	public static final String CLASSROOM_TYPE = "Classroom";
	public static final String EXECUTIVEBOARD_TYPE = "ExecutiveBoard";
	public static final String SQUARE_TYPE = "Square";
	public static final String THEATRE = "Theatre";
	public static final String USHAPE = "UShape";
	
	private static final HashMap<String, Image> arrangements = new HashMap<>();
	
	private static final HashMap<String, ArrayList<String>> arrangementsWithBanners = new HashMap<>();
	
	private transient Image image;
	
	private String type;
	private String bannersType;
	
	static {
		try {
			arrangements.put(HallArrangement.BANQUET_TYPE, ImageIO.read(new File("img\\Banquet.jpg")));
			arrangements.put(HallArrangement.CLASSROOM_TYPE, ImageIO.read(new File("img\\Classroom.jpg")));
			arrangements.put(HallArrangement.EXECUTIVEBOARD_TYPE, ImageIO.read(new File("img\\ExecutiveBoard.jpg")));
			arrangements.put(HallArrangement.SQUARE_TYPE, ImageIO.read(new File("img\\Square.jpg")));
			arrangements.put(HallArrangement.THEATRE, ImageIO.read(new File("img\\Theatre.jpg")));
			arrangements.put(HallArrangement.USHAPE, ImageIO.read(new File("img\\UShape.jpg")));
			arrangements.put("BanquetBackBanners", ImageIO.read(new File("img\\BanquetBackBanners.jpg")));
			arrangements.put("BanquetBackTables", ImageIO.read(new File("img\\BanquetBackTables.jpg")));
			arrangements.put("ClassroomBackBanner", ImageIO.read(new File("img\\ClassroomBackBanner.jpg")));
			arrangements.put("ClassroomFrontBanner", ImageIO.read(new File("img\\ClassroomFrontBanner.jpg")));
			
			ArrayList<String> types = new ArrayList<String>();
			types.add("BanquetBackBanners");
			types.add("BanquetBackTables");
			arrangementsWithBanners.put("Banquet", types);
			
			types = new ArrayList<String>();
			types.add("ClassroomBackBanner");
			types.add("ClassroomFrontBanner");
			arrangementsWithBanners.put("Classroom", types);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		this.image = arrangements.get(type);
	}
	
	public HallArrangement(String type) {
		image = arrangements.get(type);
		this.type = type;
	}


	public Image getImage() {
		return image;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public String getBannersType() {
		return bannersType;
	}

	public void setBannersType(String bannersType) {
		this.bannersType = bannersType;
	}

	public static HashMap<String, ArrayList<String>> getArrangementswithbanners() {
		return arrangementsWithBanners;
	}
	
	
	
}
