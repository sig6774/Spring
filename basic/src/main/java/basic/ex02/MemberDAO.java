package basic.ex02;

public class MemberDAO {
	private DataBaseInfo dbInfo;
	// DataBaseInfo 객체를 받음 
	// MemberDAO를 실행시키기 위해서는 DataBaseInfo가 필요

	public void setDbInfo(DataBaseInfo dbInfo) {
		this.dbInfo = dbInfo;
	}

	public void showDBInfo() {
		System.out.println("url : " + dbInfo.getUrl());
		System.out.println("uid : " + dbInfo.getUid());
		System.out.println("upw : " + dbInfo.getUpw());
	}
}
