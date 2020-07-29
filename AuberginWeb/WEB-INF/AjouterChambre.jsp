<%@ page import="java.util.*,java.text.*,AubergeInnServlet.*,AubergeInn.*"
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
		<h3 class="text-center">Chambres</h3>
		<br>
		<div class="col-8 offset-2">
			<form action="AjouterChambre" method="POST">
			    <div class="form-group">
				    <label for="IdChambre">Id Chambre</label>
				    <input class="form-control" type="NUMBER" name="IdChambre" value="<%= (request.getAttribute("IdChambre") != null ? (String)request.getAttribute("IdChambre") : "") %>">
			    </div>
			    <div class="form-group">
			    	<label for="Nom">Nom</label>
			    	<input class="form-control" type="TEXT" name="Nom" value="<%= (request.getAttribute("Nom") != null ? (String)request.getAttribute("Nom") : "") %>">
			    </div>
			    <div class="form-group">
				    <label for="TypeLit">Type de lit</label>
					<input class="form-control" type="TEXT" name="TypeLit" value="<%= (request.getAttribute("TypeLit") != null ? (String)request.getAttribute("TypeLit") : "") %>">
			    </div>
			    <div class="form-group">
			    	<label for="PrixBase">Prix de base</label>
			    	<input class="form-control" type="NUMBER" name="PrixBase" value="<%= (request.getAttribute("PrixBase") != null ? (String)request.getAttribute("PrixBase") : "") %>">
			    </div>
				<input class="btn btn-primary" type="SUBMIT" name="ajoute" value="Ajouter" style="width: 100%;">
			</form>
		</div>
	</div>
	<br>
	<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
	<jsp:include page="/WEB-INF/messageErreur.jsp" />
	<br>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>