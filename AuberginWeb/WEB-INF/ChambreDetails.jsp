<%@ page import="java.util.*,java.text.*,java.text.DecimalFormat,AubergeInnServlet.*,AubergeInn.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>IFT287 - Système de gestion de AubergeInn</title>
<meta name="author" content="Vincent Ducharme">
<meta name="description"
	content="Page d'accueil du système de gestion de AubergeInn.">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="general.css">

</head>
<body>
	<div class="container">
		<jsp:include page="/WEB-INF/navigation.jsp" />
		<h3 class="text-center">Chambre</h3>
		<br>
		<%
		Integer idChambre = Integer.parseInt(session.getAttribute("idChambre").toString());
		TupleChambre chambre = AubergeInnHelper.getAubergeInnUpdate(session).getGestionInterrogation().afficherChambre(idChambre);
		DecimalFormat df = new DecimalFormat("#0.00");
		%>
		<table style="width:100%;">
			<tbody>
				<tr>
					<td style="width:25%;">
						<table style="width:100%;">
							<tbody>
								<tr>
									<td>ID: <%=chambre.getIDChambre()%></td>
								<tr>
								<tr>
									<td>Nom: <%=chambre.getNom()%></td>
								<tr>
								<tr>
									<td>Type lit: <%=chambre.getTypeLit()%></td>
								<tr>
								<tr>
									<td>prixBase: <%=chambre.getPrixBase()%></td>
								<tr>
								<tr>
									<td>
										<br>
										<a class="btn btn-danger" href="SupprimerChambre?id=<%=chambre.getIDChambre()%>" style="width:100%;">Supprimer</a>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td>
						<a class="btn btn-primary" href="InclureCommodite?IdChambre=<%=chambre.getIDChambre()%>" style="width: 100%;">Inclure commodite</a>
						<p></p>
						<table class="table">
							<thead class="thead-dark">
								<tr>
									<th scope="col">idCommodite</th>
									<th scope="col">description</th>
									<th scope="col">surPlusPrix</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
<%
					        for (TupleCommodite c : chambre.getCommodites())
					        {
%>
								<tr>
									<td><%=c.getIDCommodite()%></td>
									<td><%=c.getDescription()%></td>
									<td><%=df.format(c.getSurplusPrix())%></td>
									<td>
										<a class="btn btn-danger" href="EnleverCommodite?IdChambre=<%=chambre.getIDChambre()%>&IdCommodite=<%=c.getIDCommodite()%>" style="width:100%;">Enlever</a>
									</td>
								<tr>
							<%} %>
								<tr>
									<td>Total: </td>
									<td><%=df.format(chambre.getPrixTotal())%></td>
								</tr>
							</tbody>
						</table>
					</td>
				<tr>
			</tbody>
		</table>
	</div>
</body>
</html>