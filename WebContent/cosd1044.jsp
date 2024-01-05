<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="cosDataPack.Shohin"%>
<%@ page import="cosDataPack.ShohinPrimalData"%>
<%@ page import="cosDataPack.Pc"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.lang.IllegalArgumentException"%>

<!DOCTYPE html>
<jsp:useBean id="shohin" scope="session" class="cosDataPack.Shohin" />
<html lang="ja">
<%
	//商品データの取得
	ShohinPrimalData sp = shohin.getShohinPriData();
	Pc pc = (Pc) shohin;
	DecimalFormat nf = new DecimalFormat("###,###,###"); //カンマ編集用クラス
	//単価をカンマ編集する
	String sTanka = null;
	try {
		sTanka = nf.format(pc.getShohinPriData().getHtanka());
	} catch (IllegalArgumentException iae) {
		System.out.println("IllegalArgumentException");
	}
%>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/common1.css">
<title>Office Goody</title>
<body id="cosd1044">
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
					<p><small>COSD1044</small></p>
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
								<td><img src="<%=pc.getImgURL()%>"
									alt="<%=pc.getShohinPriData().getGoodsCD() %>" title="サンプル" />
								</td>
							</tr>
							<tr>
								<td
									style="border: 1px solid #000000; text-align: left; vertical-align: top; height: 10em;">
									<%=pc.getComments() %>
								</td>
							</tr>
						</table>
					</div>
					<div id="goods_exp">
						<table>
							<tr>
								<th>商品コード</th>
								<td><%=pc.getShohinPriData().getGoodsCD() %></td>
							</tr>
							<tr>
								<th>商品名</th>
								<td><%=pc.getShohinPriData().getGoodsName() %></td>
							</tr>
							<tr>
								<th>単価</th>
								<td><%=sTanka %>円</td>
							</tr>
							<tr>
								<th>在庫</th>
								<td><%=pc.getZaikoHyouji() %></td>
							</tr>
							<tr>
								<th>メーカー名</th>
								<td><%=pc.getMakerName() %></td>
							</tr>
							<tr>
							<tr>
								<th>セット数</th>
								<td><%=pc.getIrisu() %></td>
							</tr>
							<tr>
								<th>色</th>
								<td><%=pc.getColor() %></td>
							</tr>
							<tr>
								<th>型番</th>
								<td><%=pc.getKataban() %></td>
							</tr>
							<tr>
								<th>画面サイズ</th>
								<td><%=pc.getSize() %></td>
							</tr>
							<tr>
								<th>画面解像度</th>
								<td><%=pc.getResolution() %></td>
							</tr>
							<tr>
								<th>メモリ容量</th>
								<td><%=pc.getMemory_capacity() %></td>
							</tr>
							<tr>
								<th>HDD容量</th>
								<td><%=pc.getHdd_capacity() %></td>
							</tr>
							<tr>
								<th>ドライブ</th>
								<td><%=pc.getDrive() %></td>
							</tr>
							<tr>
								<th>ネットワーク</th>
								<td><%=pc.getNetwork() %></td>
							</tr>
							<tr>
								<th>OS</th>
								<td><%=pc.getOs() %></td>
							</tr>
							<tr>
								<th>統合ソフト</th>
								<td><%=pc.getSoft() %></td>
							</tr>
							<tr>
								<th>バッテリ</th>
								<td><%=pc.getBattery() %></td>
							</tr>
							<tr>
								<th>付属品</th>
								<td><%=pc.getAccessory() %></td>
							</tr>
							<tr>
								<th>保証期間</th>
								<td><%=pc.getAssurance() %></td>
							</tr>
							<tr>
								<th>質量</th>
								<td><%=pc.getMass() %></td>
							</tr>
							<tr>
								<th>寸法</th>
								<td><%=pc.getMeasure() %></td>
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