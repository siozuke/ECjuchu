<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="cosDataPack.Shohin"%>
<%@ page import="cosDataPack.ShohinPrimalData"%>
<%@ page import="cosDataPack.Foods"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.lang.IllegalArgumentException"%>

<!DOCTYPE html>
<jsp:useBean id="shohin" scope="session" class="cosDataPack.Shohin" />
<html lang="ja">
<%
	//商品データの取得
	ShohinPrimalData sp = shohin.getShohinPriData();
	Foods foods = (Foods) shohin;
	DecimalFormat nf = new DecimalFormat("###,###,###"); //カンマ編集用クラス
	//単価をカンマ編集する
	String sTanka = null;
	try {
		sTanka = nf.format(foods.getShohinPriData().getHtanka());
	} catch (IllegalArgumentException iae) {
		System.out.println("IllegalArgumentException");
	}
%>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/common1.css">
<title>Office Goody</title>
<body id="cosd1042">
	<!-- コンテナ部分（ページ全体） -->
	<div id="container">
		<!-- ヘッダー部分 -->
		<div id="header">
			<h1>Office Goody</h1>
			<h2>Customer Order System</h2>
		</div>
		<!-- コンテンツ部分 -->
		<div id="contents">
			<form action="CosServlet" method="post">
				<!-- コンテンツ内ヘッダー部分 -->
				<div id="contents_header">
					<p><small>COSD1042</small></p>
					<h3>商品の詳細説明</h3>
				</div>
				<!-- コンテンツ内ナビゲーション部分 -->
				<div id="contents_nav"></div>
				<hr>
				<!-- コンテンツ内ボディ部分 -->
				<div id="contents_body">
					<div id="goods_img">
						<table>
							<tr>
								<td><img src="<%=foods.getImgURL()%>"
									alt="<%=foods.getShohinPriData().getGoodsCD() %>" title="サンプル" />
								</td>
							</tr>
							<tr>
								<td
									style="border: 1px solid #000000; text-align: left; vertical-align: top; height: 10em;">
									<%=foods.getComments() %>
								</td>
							</tr>
						</table>
					</div>
					<div id="goods_exp">
						<table>
							<tr>
								<th>商品コード</th>
								<td><%=foods.getShohinPriData().getGoodsCD() %></td>
							</tr>
							<tr>
								<th>商品名</th>
								<td><%=foods.getShohinPriData().getGoodsName() %></td>
							</tr>
							<tr>
								<th>単価</th>
								<td><%=sTanka %>円</td>
							</tr>
							<tr>
								<th>在庫</th>
								<td><%=foods.getZaikoHyouji() %></td>
							</tr>
							<tr>
								<th>メーカー名</th>
								<td><%=foods.getMakerName() %></td>
							</tr>
							<tr>
							<tr>
								<th>セット数</th>
								<td><%=foods.getIrisu() %></td>
							</tr>
							<tr>
								<th>味</th>
								<td><%=foods.getTaste() %></td>
							</tr>
							<tr>
								<th>内容量</th>
								<td><%=foods.getNet() %></td>
							</tr>
							<tr>
								<th>製造国</th>
								<td><%=foods.getProduction_country() %></td>
							</tr>
							<tr>
								<th>熱量</th>
								<td><%=foods.getHeat() %></td>
							</tr>
							<tr>
								<th>原材料</th>
								<td><%=foods.getMaterials() %></td>
							</tr>
							<tr>
								<th>栄養成分</th>
								<td><%=foods.getNutrition() %></td>
							</tr>
						</table>
						<br>
					</div>
				</div>
			</form>
		</div>
		<!-- フッター部分 -->
		<div id="footer">
			<address>&copy;Office Goody 2011 All rights reserved Office
				Goody Co,Ltd.</address>
		</div>
	</div>
</body>
</html>