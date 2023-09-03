<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Intranet Tesouraria - Unidade de Tesouraria Global</title>
<c:import url="/WEB-INF/pages/publico/includes.jsp" />

<style>
.col-md-19 {
	position: relative;
	min-height: 1px;
	padding-right: 15px;
	padding-left: 15px;
	margin-right: 8%;
	margin-left: 8%;
}

.dataAtual {
	border-top-width: 0px;
	border-top-style: solid;
	font-size: 114%;
	line-height: 26px;
	letter-spacing: 0em;
	font-weight: 300;
	font-style: normal;
	margin-left: 110px;
	margin-top: -15px;
}

.tabela {
	background-color: hsla(0, 0%, 50.2%, .07058823529411765);
}

.box {
	width: 100%;
	height: 100%;
	text-align: center;
}

table caption+thead tr:first-child td, table caption+thead tr:first-child th,
	table colgroup+thead tr:first-child td, table colgroup+thead tr:first-child th,
	table thead:first-child tr:first-child td, table thead:first-child tr:first-child th
	{
	border-top: 1px solid hsla(0, 0%, 50.2%, .5019607843137255)
}

table tbody>tr:nth-child(odd)>td, table tbody>tr:nth-child(odd)>th {
	background-color: hsla(0, 0%, 50.2%, .07058823529411765);
}

table tbody tr:hover>td, table tbody tr:hover>th {
	background-color: hsl(0, 0%, 96.5%);
}

table tbody+tbody {
	border-top: 2px solid hsla(0, 0%, 50.2%, .5019607843137255)
}

@media ( max-width :767px) {
	table table {
		font-size: .8em
	}
	table table td, table table th {
		padding: 7px;
		line-height: 1.3
	}
	table table th {
		font-weight: 400
	}
}

.input {
	position: relative;
	background-color: hsla(0, 0%, 50.2%, .07058823529411765);
	border: none;
	maxlength: 4;
	width: 75px;
}

.btn {
	padding: 0px 0px;
	width: 70px;
	height: 25px;
	text-align: center;
	margin-rigth: 10px;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script>
	function botaoVoltar() {
		history.back();
	}
</script>
</head>

<body class="cor_fundo_principal">
	<c:import url="/WEB-INF/pages/publico/templateTopo.jsp" />


	<div class="container-fluid">

		<div class="row">
			<div class="col-md-12">
				<div class="menuPrincipalResolver">
					<div class="menuPrincipalResolverMenuPai">Agendamento de
						Shiatsu</div>
					<div class="menuPrincipalResolverMenuFilho">ECOA - QVT</div>
				</div>
			</div>
		</div>
	</div>


	<div class="row">
		<div class="col-md-12">
			<div class="container">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong>Dia: ${agenda.data}</strong>
						<button class="btn btn btn-primary" style="float: right;"
							onclick="botaoVoltar()">Voltar</button>
					</div>
					<table class="box" id="anexos_table">
						<thead>
							<tr>
								<th width="10%" style="text-align: center;">Hora</th>
								<th width="20%">Funcionário</th>
								<th width="10%" style="text-align: center;">Ramal</th>
								<th width="15%">Gerência</th>
								<th width="30%" style="text-align: center;">Situação</th>
							</tr>
						</thead>

						<c:set var="usuarioPossuiAgendamento" value="false" />
						<c:forEach items="${agenda.listaAgendamento}" var="agendamento">
							<c:if
								test="${agendamento.chaveFuncionario == usuarioLogado.chave}">
								<c:set var="usuarioPossuiAgendamento" value="true" />
							</c:if>
						</c:forEach>
						<tbody class="tabela">
							<c:forEach items="${agenda.listaAgendamento}" var="agendamento">
								<tr align="center">
									<td width="5%" height="30">${agendamento.horario}</td>
									<td width="20%" height="30" id="funcionario"
										style="text-align: left;">${agendamento.funcionario}</td>
									<td width="5%" height="30">${agendamento.ramal}</td>
									<td width="20%" height="30">&nbsp;</td>
									<td><c:choose>
											<c:when
												test="${agendamento.chaveFuncionario == usuarioLogado.chave}">
												<a
													href="<c:url value='/publico/ecoa/excluirAgendamento?idAgenda=${agendamento.idAgenda}&idHorario=${agendamento.idHorario}'/>"
													class="btn btn-danger">Cancelar</a>
											</c:when>
											<c:when test="${agendamento.chaveFuncionario != ''}">
												<button class="btn btn-success" disabled>Agendado</button>
											</c:when>
											<c:when test="${usuarioPossuiAgendamento == 'true'}">
												<button class="btn btn-secondary" disabled></button>
											</c:when>
											<c:otherwise>
												<a
													href="<c:url value='/publico/ecoa/modalAgendamentoForm?idAgenda=${agendamento.idAgenda}&idHorario=${agendamento.idHorario}'/>"
													class="btn btn-success">Agendar</a>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</c:forEach>
						</tbody>

						<tfoot align="center">
							<tr>
								<td colspan="6" style="">--- Intervalo entre: 12:40 até
									14:00 ---</td>
							</tr>
						</tfoot>
					</table>

				</div>
			</div>
		</div>
	</div>

	<c:import url="/WEB-INF/pages/publico/templateRodape.jsp" />
</body>
</html>