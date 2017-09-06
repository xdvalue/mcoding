package com.mcoding.comp.broadcast;

public class ParkingMsg implements IBroadcastMsg {
	
	private String cardNum;
	private String parkNum;
	private String parkTime;
	private String parkName;
	private int rate = -2;
	private int volume = 100;
	
	public ParkingMsg(String cardNum, String parkNum, String parkTime, String parkName) {
		super();
		this.cardNum = cardNum;
		this.parkNum = parkNum;
		this.parkTime = parkTime;
		this.parkName = parkName;
	}

	@Override
	public String getContent() {
		StringBuffer cardNumTmp = new StringBuffer();
		char[] charArray = this.cardNum.toCharArray();
		for(int i=0; i<charArray.length; i++){
			cardNumTmp.append(charArray[i]).append(" ");
		}
		return String.format("请注意，%s 将在%s分钟后到达%s号码位。", cardNumTmp.toString(), this.parkTime, this.parkNum);
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getParkNum() {
		return parkNum;
	}

	public void setParkNum(String parkNum) {
		this.parkNum = parkNum;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getParkTime() {
		return parkTime;
	}

	public void setParkTime(String parkTime) {
		this.parkTime = parkTime;
	}
	
	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public static void main(String[] args) {
		ParkingMsg msg = new ParkingMsg("粤A1234", "12", "15", "东码头");
		System.out.println(msg.getContent());
	}

}
