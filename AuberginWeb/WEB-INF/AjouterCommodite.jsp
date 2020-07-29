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
		<h3 class="text-center">Commodites</h3>
		<br>
		<div class="col-8 offset-2">
			<form action="AjouterCommodite" method="POST">
			    <div class="form-group">
				    <label for="IdCommodite">Id Commodite</label>
				    <input class="form-control" type="NUMBER" name="IdCommodite" value="<%= (request.getAttribute("IdCommodite") != null ? (String)request.getAttribute("IdCommodite") : "") %>">
			    </div>
			    <div class="form-group">
			    	<label for="Description">Description</label>
			    	<input class="form-control" type="TEXT" name="Description" value="<%= (request.getAttribute("Description") != null ? (String)request.getAttribute("Description") : "") %>">
			    </div>
			    <div class="form-group">
			    	<label for="SurPlusPrix">Surplus prix</label>
			    	<input class="form-control" type="NUMBER" name="SurPlusPrix" value="<%= (request.getAttribute("SurPlusPrix") != null ? (String)request.getAttribute("SurPlusPrix") : "") %>">
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