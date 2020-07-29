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
		<h3 class="text-center">Client</h3>
		<br>
		<%
		Integer idClient = Integer.parseInt(session.getAttribute("idClient").toString());
		TupleClient client = AubergeInnHelper.getAubergeInnUpdate(session).getGestionInterrogation().afficherClient(idClient);
		%>
		<table style="width:100%;">
			<tbody>
				<tr>
					<td style="width:25%;">
						<table style="width:100%;">
							<tbody>
								<tr>
									<td>ID: <%=client.getIDClient()%></td>
								<tr>
								<tr>
									<td>Nom: <%=client.getNom()%></td>
								<tr>
								<tr>
									<td>Prenom: <%=client.getPrenom()%></td>
								<tr>
								<tr>
									<td>Age: <%=client.getAge()%></td>
								<tr>
								<tr>
									<td>
										<br>
										<a class="btn btn-danger" href="SupprimerClient?id=<%=client.getIDClient()%>" style="width:100%;">Supprimer</a>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
					<td>
						<a class="btn btn-primary" href="Reservation?IdClient=<%=client.getIDClient()%>" style="width: 100%;">Effectuer reservation</a>
						<p></p>
						<table class="table">
							<thead class="thead-dark">
								<tr>
									<th scope="col">Id Reservation</th>
									<th scope="col">Id Client</th>
									<th scope="col">Id Chambre</th>
									<th scope="col">Date de debut</th>
									<th scope="col">Date de fin</th>
									<th scope="col">Prix de location</th>
								</tr>
							</thead>
							<tbody>
<%
							DecimalFormat df = new DecimalFormat("#0.00");
					        for (TupleReservation r: client.getReservations())
					        {
%>
								<tr>
									<td><%=r.getIDReservation()%></td>
									<td><%=r.getIDClient()%></td>
									<td><%=r.getIDChambre()%></td>
									<td><%=r.getDateDebut()%></td>
									<td><%=r.getDateFin()%></td>
									<td><%=df.format(r.getPrix())%></td>
								<tr>
							<%} %>
								<tr>
									<td>Total: </td>
									<td><%=df.format(client.getPrixTotal())%></td>
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