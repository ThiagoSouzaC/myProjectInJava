<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Questionário e Resultado</title>
</head>
<body>
	<%
		if (request.getMethod().equals("POST")) {
			int pontuacaoTotal = 0;

			String[] respostas = { request.getParameter("pergunta1"),
						// Adicione mais parâmetros para outras perguntas aqui
			};

			for (String resposta : respostas) {
				pontuacaoTotal += Integer.parseInt(resposta);
			}

			String mensagem = "";
			if (pontuacaoTotal <= 5) {
				mensagem = "Você teve uma pontuação baixa.";
			} else if (pontuacaoTotal <= 10) {
				mensagem = "Você teve uma pontuação média.";
			} else {
				mensagem = "Você teve uma pontuação alta!";
			}
	%>

	<h1>Resultado</h1>
	<p>
		Sua pontuação total:
		<%=pontuacaoTotal%></p>
	<p><%=mensagem%></p>

	<%
		} else {
	%>
	<form action="" method="post">
		<h1>Questionário</h1>
		<p>Pergunta 1: Como você se sente hoje?</p>
		<input type="radio" name="pergunta1" value="1">1 <input
			type="radio" name="pergunta1" value="2">2 <input type="radio"
			name="pergunta1" value="3">3 <input type="radio"
			name="pergunta1" value="4">4 <input type="radio"
			name="pergunta1" value="5">5
		<!-- Adicione mais perguntas aqui -->

		<br>
		<br> <input type="submit" value="Enviar">
	</form>
	<%
		}
	%>
</body>
</html>