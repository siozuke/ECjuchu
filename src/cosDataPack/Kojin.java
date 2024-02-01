package cosDataPack;

/**
 * @author 455983
 * 顧客個人クラス。
 * 個人情報を保持するクラス。個人特有な情報と機能を持つ
 */
public class Kojin extends Kokyaku {
	//誕生日
	private String birthday;

	/**
	 * 引数なしコンストラクタ
	 */
	public Kojin() {
		super();
	}

	/**
	 * 誕生日を取得する。
	 * @return
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * 誕生日を設定する。
	 * @param birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * フィールドをブランクで埋める（画面表示用）。
	 */
	@Override
	public void setBrank() {
		super.setBrank();
		this.setBirthday("");
	}

}
