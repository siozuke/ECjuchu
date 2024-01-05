package cosDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cosDataPack.Foods;
import cosDataPack.Shohin;
import cosDataPack.ShohinCategory;
import cosDataPack.ShohinPrimalData;

public class DaoFoods extends DaoShohin {

	/**
	 * 引数なしコンストラクタ。
	 */
	public DaoFoods() {
		super();
	}

	/**
	 * 引数ありコンストラクタ。
	 * @param con DB接続コネクション
	 */
	public DaoFoods(Connection con) {
		//コネクションをセットする
		super(con);
	}

	public ArrayList<Shohin> selectData(String code, String eigyosyoCD) {

		ArrayList<Shohin> list = new ArrayList<Shohin>();//リストオブジェクト
		Foods f1 = new Foods();
		ShohinPrimalData sp1 = new ShohinPrimalData(); //商品基本情報オブジェクト
		ShohinCategory sc1 = new ShohinCategory(); //商品カテゴリオブジェクト
		String query = null; //クエリ格納用
		ResultSet rs = null; //ResultSet

		//statementのcreate
		creStatement();

		//引数で渡された商品コードと営業所コードにより１件の情報を取得するSQL
				query = "SELECT B.GOODSCD, B.GOODSNAME, B.H_TANKA, C.GCTGCD, C.GCTGNAME, G.GSUBCTGCD, " +
						"G.GSUBCTGNAME, C.CLASSNAME, M.MAKERNAME, B.IRISU, B.TANNI, B.MAKERCD, B.COMMENT, B.IMG_URI, " +
						"H.ZAIKO_CNT, B.SHUBETU, B.OTHER, B.TASTE, B.NET, B.PRODUCTION_COUNTRY, B.HEAT, B.MATERIALS, B.NUTRITION " +
						"FROM FOODS_TABLE B, GSUBCTG_TABLE G, CTR_GCTG_TABLE C, HANBAIZAIKO" + eigyosyoCD
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
						f1.setShohinPriData(sp1);
						f1.setShohinCtgInfo(sc1);
						f1.setMakerName(rs.getString("MAKERNAME"));
						f1.setIrisu(rs.getInt("IRISU"));
						f1.setTanni(rs.getString("TANNI"));
						f1.setMakerCD(rs.getString("MAKERCD"));
						f1.setComments(rs.getString("COMMENT"));
						f1.setImgURL(rs.getString("IMG_URI"));
						f1.setZaikoCnt(rs.getInt("ZAIKO_CNT"));
						f1.setZaikoHyouji();
						f1.setShubetu(rs.getString("SHUBETU"));
						f1.setOther(rs.getString("OTHER"));
						f1.setTaste(rs.getString("TASTE"));
						f1.setNet(rs.getString("NET"));
						f1.setProduction_country(rs.getString("PRODUCTION_COUNTRY"));
						f1.setHeat(rs.getString("HEAT"));
						f1.setMaterials(rs.getString("MATERIALS"));
						f1.setNutrition(rs.getString("NUTRITION"));
						//リストに追加
						list.add(f1);
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
