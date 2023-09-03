<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Intranet Tesouraria - Unidade de Tesouraria Global</title>
<c:import url="/WEB-INF/pages/publico/includes.jsp" />
<script>
	function botaoVoltar() {
		history.back();
	}
</script>
</head>
   
<body class="cor_fundo_principal">
<c:import url="/WEB-INF/pages/publico/templateTopo.jsp" />



		<div class="container">
		
				<div class="row">
				  <div class="col-md-12">
					<div class="menuPrincipalResolver">
						<div class="menuPrincipalResolverMenuPai">Agenda de Massagem</div>
						<div class="menuPrincipalResolverMenuFilho">ECOA - QVT-Shiatsu</div>
					</div>
				</div>
			</div>
			
		
			<div class="col-md-12">
      			<div class="panel panel-default">
    				<div class="panel-heading"><strong>Não é possível agendar.</strong></div>
    					<div class="panel-body">
    						<h3>
		    					O funcionário já atingiu seu limite de agendamentos no mês.
		    				</h3>
		    				</br>
		    				<button class="btn btn btn-primary" style="float:left" onclick="botaoVoltar()">Voltar</button>
	    				</div>
    				</div>
    			</div>
			</div>
			   
<c:import url="/WEB-INF/pages/publico/templateRodape.jsp" />    
</body>
</html>