package com.weili.wechat.vo;


public class StoreCommInfoVO {
		
		private String id;
		private StoreInfoVO storeInfoVO;
		private StoreCommClassificationInfoVO storeCommClassificationInfoVO;
		private String name;
		private double price;
		private double preferentialPrice;
		private Integer onhand;
		private String pictureLink;
		private Integer onShelves;
		
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public StoreInfoVO getStoreInfoVO() {
			return storeInfoVO;
		}
		public void setStoreInfoVO(StoreInfoVO storeInfoVO) {
			this.storeInfoVO = storeInfoVO;
		}
		public StoreCommClassificationInfoVO getStoreCommClassificationInfoVO() {
			return storeCommClassificationInfoVO;
		}
		public void setStoreCommClassificationInfoVO(
				StoreCommClassificationInfoVO storeCommClassificationInfoVO) {
			this.storeCommClassificationInfoVO = storeCommClassificationInfoVO;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public double getPreferentialPrice() {
			return preferentialPrice;
		}
		public void setPreferentialPrice(double preferentialPrice) {
			this.preferentialPrice = preferentialPrice;
		}
		public Integer getOnhand() {
			return onhand;
		}
		public void setOnhand(Integer onhand) {
			this.onhand = onhand;
		}
		public String getPictureLink() {
			return pictureLink;
		}
		public void setPictureLink(String pictureLink) {
			this.pictureLink = pictureLink;
		}
		public Integer getOnShelves() {
			return onShelves;
		}
		public void setOnShelves(Integer onShelves) {
			this.onShelves = onShelves;
		}
		
}