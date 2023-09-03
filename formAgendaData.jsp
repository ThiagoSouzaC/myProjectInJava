<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Intranet Tesouraria - Unidade de Tesouraria Global</title>
<c:import url="/WEB-INF/pages/publico/includes.jsp" />
<style >

</style>
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
	
	<div class="panel panel-default" style='text-align:left'>
	
	
		<div class="panel-heading"><strong>Data Agenda</strong></div>
		
		<div class="panel-body" style='text-align:left'>
		
                <c:if test="${not empty param.msgSucesso}">
                    <div class="row">
                       <div class="col-md-12">
                          <div class="alert alert-success">
                              <a href="#" class="close" data-dismiss="alert">&times;</a>
                              A agenda foi registrada com <strong>SUCESSO!</strong>
                          </div>
                       </div>
                    </div>
                 </c:if>
                 
                 <c:if test="${not empty param.msgErro}">
                    <div class="row">
                       <div class="col-md-12">
                          <div class="alert alert-danger">
                              <a href="#" class="close" data-dismiss="alert">&times;</a>
                              A agenda não foi registrada, <strong>ERRO!</strong>
                          </div>
                       </div>
                    </div>
                </c:if>
                
                
                
         	   <c:url var="post_url"  value="/publico/ecoa/agendaData" />     
               <form:form action="${post_url}" method="post" class="form-horizontal" modelAttribute="agenda">	
             		
             		
             		<fieldset>
                  
                  	<legend>Selecione uma data para abertura da Agenda</legend>
                  	
                  	 <div class="row" style="margin-top:5px;">&nbsp;</div>
                  	
                  		<div class="row">
                           <div class="col-md-6">
                              <div class="input-group" style="display: flex; text-align: left">
                                 <span class="input-group-addon" style="width: 60px">Data</span>                               
                                   <form:input path="data" id ="dataDaAgenda" placeholder="" class="form-control campoData" style="width:200px;" />	
                                                                                                         
                              </div>
                           </div> 
                        </div>
                        
                        <div class="row" style="margin-top:5px;">&nbsp;</div>
                       
                        <div class="row">
                           <div class="col-md-6">
                              <div class="input-group" style="display: flex; text-align: left">
                                 <span class="input-group-addon" style="width: 140px; align-text">Nome Profissional</span>                                
                                 	<form:input path="nomeProf" id ="nomeProf" placeholder="Ex: Simone" class="form-control" style="width:200px;" />	                                                                         
                              </div>
                           </div> 
                        </div>

                  </fieldset>
                  
                  <div class="row" style="margin-top:40px;">
                        <div class="col-xs-12">
                           <div class="pull-right">
                              <button type="submit" id="submitForm" name="submitForm" class="btn btn-primary"><span ></span> <b>Enviar</b></button>
                           </div>
                        </div>
                  </div>                                    
               </form:form>			
		</div>
	</div>
</div>	
		
<c:import url="/WEB-INF/pages/publico/templateRodape.jsp" />
</body>
</html>