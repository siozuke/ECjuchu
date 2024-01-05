package cosDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cosDataPack.Pc;
import cosDataPack.Shohin;
import cosDataPack.ShohinCategory;
import cosDataPack.ShohinPrimalData;

public class DaoPc extends DaoShohin {

	/**
	 * 引数なしコンストラクタ。
	 */
	public DaoPc() {
		super();
	}

	/**
	 * 引数ありコンストラクタ。
	 * @param con DB接続コネクション
	 */
	public DaoPc(Connection con) {
		//コネクションをセットする
		super(con);
	}

	public ArrayList<Shohin> selectData(String code, String eigyosyoCD) {

		ArrayList<Shohin> list = new ArrayList<Shohin>();//リストオブジェクト
		Pc p1 = new Pc();
		ShohinPrimalData sp1 = new ShohinPrimalData(); //商品基本情報オブジェクト
		ShohinCategory sc1 = new ShohinCategory(); //商品カテゴリオブジェクト
		String query = null; //クエリ格納用
		ResultSet rs = null; //ResultSet

		//statementのcreate
		creStatement();

		//引数で渡された商品コードと営業所コードにより１件の情報を取得するSQL
				query = "SELECT B.GOODSCD, B.GOODSNAME, B.H_TANKA, C.GCTGCD, C.GCTGNAME, G.GSUBCTGCD, " +
						"G.GSUBCTGNAME, C.CLASSNAME, M.MAKERNAME, B.IRISU, B.TANNI, B.MAKERCD, B.COMMENT, B.IMG_URI, " +
						"H.ZAIKO_CNT, B.SHUBETU, B.OTHER, B.COLOR, B.KATABAN, B.SIZE, B.RESOLUTION, B.CPU, B.MEMORY_CAPACITY, B.HDD_CAPACITY,"
						+ "B.DRIVE, B.NETWORK, B.OS, B.SOFT, B.BATTERY, B.ACCESSORY, B.ASSURANCE, B.MASS, B.MEASURE " +
						"FROM PC_TABLE B, GSUBCTG_TABLE G, CTR_GCTG_TABLE C, HANBAIZAIKO" + eigyosyoCD
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
						p1.setShohinPriData(sp1);
						p1.setShohinCtgInfo(sc1);
						p1.setMakerName(rs.getString("MAKERNAME"));
						p1.setIrisu(rs.getInt("IRISU"));
						p1.setTanni(rs.getString("TANNI"));
						p1.setMakerCD(rs.getString("MAKERCD"));
						p1.setComments(rs.getString("COMMENT"));
						p1.setImgURL(rs.getString("IMG_URI"));
						p1.setZaikoCnt(rs.getInt("ZAIKO_CNT"));
						p1.setZaikoHyouji();
						p1.setShubetu(rs.getString("SHUBETU"));
						p1.setOther(rs.getString("OTHER"));
						p1.setColor(rs.getString("COLOR"));
						p1.setKataban(rs.getString("KATABAN"));
						p1.setSize(rs.getString("SIZE"));
						p1.setResolution(rs.getString("RESOLUTION"));
						p1.setCpu(rs.getString("CPU"));
						p1.setMemory_capacity(rs.getString("MEMORY_CAPACITY"));
						p1.setHdd_capacity(rs.getString("HDD_CAPACITY"));
						p1.setDrive(rs.getString("DRIVE"));
						p1.setNetwork(rs.getString("NETWORK"));
						p1.setOs(rs.getString("OS"));
						p1.setSoft(rs.getString("SOFT"));
						p1.setBattery(rs.getString("BATTERY"));
						p1.setAccessory(rs.getString("ACCESSORY"));
						p1.setAssurance(rs.getString("ASSURANCE"));
						p1.setMass(rs.getString("MASS"));
						p1.setMeasure(rs.getString("MEASURE"));
						//リストに追加
						list.add(p1);
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
