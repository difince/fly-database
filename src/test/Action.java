//package test;
//
//import java.util.ArrayList;
//
//public class Action {
//	private int id;
//	private int paintingId;
//	private int galleryId;
//	private double price;
//	private String state;
//	
//	public Action(int paintingId, int galleryId, double price, String state) {
//		this.paintingId = paintingId;
//		this.galleryId = galleryId;
//		this.price = price;
//		this.state = state;
//	}
//
//	public static ArrayList<Action> actions = new ArrayList<Action>() {{
//		add(new Action(2, 3, 120d, "Sold"));
//		add(new Action(3, 3, 65d, "InStore"));
//		add(new Action(4, 3, 70d, "Sold"));
//		add(new Action(5, 4, 40d, "InStore"));
//		add(new Action(6, 4, 35d, "InStore"));
//		add(new Action(7, 4, 120d, "InStore"));
//		add(new Action(12, 5, 120d, "InStore"));
//		add(new Action(15, 5, 50d, "InStore"));
//		add(new Action(16, 5, 55d, "Sold"));
//		add(new Action(17, 10, 65d, "InStore"));
//		add(new Action(18, 10, 65d, "InStore"));
//		add(new Action(20, 10, 200d, "Sold"));
//		add(new Action(22, 12, 150d, "InStore"));
//		add(new Action(23, 12, 150d, "InStore"));
//		add(new Action(24, 15, 170d, "Sold"));
//		
//		add(new Action(25, 15, 30d, "InStore"));
//		add(new Action(26, 17, 30d, "InStore"));
//		add(new Action(27, 17, 35d, "InStore"));
//		add(new Action(28, 20, 55d, "InStore"));
//		add(new Action(29, 20, 600d, "Sold"));
//		add(new Action(30, 18, 800d, "InStore"));
//		add(new Action(19, 19, 310d, "InStore"));
//		add(new Action(35, 19, 450d, "Sold"));
//		add(new Action(32, 19, 380d, "Sold"));
//		
//	}};
////	public enum ActionState {
////		AVAILABLE(1, "Available"), IN_STORE(2, "InStore"), SOLD(3, "Sold");
////		private final int id;
////		private final String state;
////
////		private ActionState(int id, String state) {
////			this.id = id;
////			this.state = state;
////		}
////
////		public int getIntValueOf(ActionState state) {
////			switch (state) {
////			case AVAILABLE:
////				return AVAILABLE.id;
////			case IN_STORE:
////				return IN_STORE.id;
////			case SOLD:
////				return SOLD.id;
////			}
////			throw new RuntimeException(String.format("Unsupported Action state %s", state));
////		}
////	}
//
//	public int getGallery_id() {
//		return galleryId;
//	}
//
//	public void setGallery_id(int gallery_id) {
//		this.galleryId = gallery_id;
//	}
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public int getPainting_id() {
//		return paintingId;
//	}
//
//	public void setPaintingId(int paintingId) {
//		this.paintingId = paintingId;
//	}
//
//	public double getPrice() {
//		return price;
//	}
//
//	public void setPrice(double price) {
//		this.price = price;
//	}
//
//	public String getState() {
//		return state;
//	}
//
//	public void setState(String state) {
//		this.state = state;
//	}
//}
