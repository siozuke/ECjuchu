package cosDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cosDataPack.Av;
import cosDataPack.Shohin;
import cosDataPack.ShohinCategory;
import cosDataPack.ShohinPrimalData;

public class DaoAv extends DaoShohin {

	/**
	 * 引数なしコンストラクタ。
	 */
	public DaoAv() {
		super();
	}

	/**
	 * 引数ありコンストラクタ。
	 * @param con DB接続コネクション
	 */
	public DaoAv(Connection con) {
		//コネクションをセットする
		super(con);
	}

	public ArrayList<Shohin> selectData(String code, String eigyosyoCD) {

		ArrayList<Shohin> list = new ArrayList<Shohin>();//リストオブジェクト
		Av a1 = new Av();
		ShohinPrimalData sp1 = new ShohinPrimalData(); //商品基本情報オブジェクト
		ShohinCategory sc1 = new ShohinCategory(); //商品カテゴリオブジェクト
		String query = null; //クエリ格納用
		ResultSet rs = null; //ResultSet

		//statementのcreate
		creStatement();

		//引数で渡された商品コードと営業所コードにより１件の情報を取得するSQL
				query = "SELECT B.GOODSCD, B.GOODSNAME, B.H_TANKA, C.GCTGCD, C.GCTGNAME, G.GSUBCTGCD, " +
						"G.GSUBCTGNAME, C.CLASSNAME, M.MAKERNAME, B.IRISU, B.TANNI, B.MAKERCD, B.COMMENT, B.IMG_URI, " +
						"H.ZAIKO_CNT, B.SHUBETU, B.OTHER, B.COLOR, B.KATABAN, B.SIZE, B.RESOLUTION, B.SOUND, B.CAPACITY, B.MEDIA,"
						+ "B.FACE, B.POWER, B.ACCESSORY, B.ASSURANCE, B.MASS, B.MEASURE " +
						"FROM AV_TABLE B, GSUBCTG_TABLE G, CTR_GCTG_TABLE C, HANBAIZAIKO" + eigyosyoCD
						+ "_TABLE H, MAKER_TABLE M " +
						"WHERE ((LEFT(B.GOODSCD, 2) = C.GCTGCD) AND (LEFT(B.GOODSCD, 4) = G.GSUBCTGCD) " +
						"AND B.GOODSCD = H.GOODSCD AND B.MAKERCD = M.MAKERCD) " +
						"AND B.GOODSCD = '" + code + "'";

				try {
					//SQL（クエリ）実行
					rs = stmt.executeQuery(query);
					if (rs.next()) {
						//ResultSetより1件のオブジェクトを取得
						//商品カテゴリ情報を取得
						sc1.setGctgCD(rs.getString("GCTGCD"));
						sc1.setGctgName(rs.getString("GCTGNAME"));
						sc1.setGsubctgCD(rs.getString("GSUBCTGCD"));
						sc1.setGsubctgName(rs.getString("GSUBCTGNAME"));
						sc1.setClassName(rs.getString("CLASSNAME"));
						//商品基本情報を取得
						sp1.setGoodsCD(rs.getString("GOODSCD"));
						sp1.setGoodsName(rs.getString("GOODSNAME"));
						sp1.setHtanka(rs.getInt("H_TANKA"));
						//商品のその他の情報を取得
						a1.setShohinPriData(sp1);
						a1.setShohinCtgInfo(sc1);
						a1.setMakerName(rs.getString("MAKERNAME"));
						a1.setIrisu(rs.getInt("IRISU"));
						a1.setTanni(rs.getString("TANNI"));
						a1.setMakerCD(rs.getString("MAKERCD"));
						a1.setComments(rs.getString("COMMENT"));
						a1.setImgURL(rs.getString("IMG_URI"));
						a1.setZaikoCnt(rs.getInt("ZAIKO_CNT"));
						a1.setZaikoHyouji();
						a1.setShubetu(rs.getString("SHUBETU"));
						a1.setOther(rs.getString("OTHER"));
						a1.setColor(rs.getString("COLOR"));
						a1.setKataban(rs.getString("KATABAN"));
						a1.setSize(rs.getString("SIZE"));
						a1.setResolution(rs.getString("RESOLUTION"));
						a1.setSound(rs.getString("SOUND"));
						a1.setCapacity(rs.getString("CAPACITY"));
						a1.setMedia(rs.getString("MEDIA"));
						a1.setFace(rs.getString("FACE"));
						a1.setPower(rs.getString("POWER"));
						a1.setAccessory(rs.getString("ACCESSORY"));
						a1.setAssurance(rs.getString("ASSURANCE"));
						a1.setMass(rs.getString("MASS"));
						a1.setMeasure(rs.getString("MEASURE"));
						//リストに追加
						list.add(a1);
					} else {
						//対象の文具データがなければnullを返す
						list = null;
					}
					//ResultSetのクローズ
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
					//エラーの場合、nullを返す
					list = null;
				}
				//statementのclose
				closeStatement();
				return list;
			}


}
