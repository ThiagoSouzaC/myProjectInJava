<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Intranet Tesouraria - Unidade de Tesouraria Global</title>

<c:url value="/publico/ecoa/agendaDataUsu" var="pagedLink">

</c:url>

<c:import url="/WEB-INF/pages/restrito/includes.jsp"/>



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
      
      
            <c:if test="${not empty param.msgSucesso}">
         <div class="row">
            <div class="col-md-12">
               <div class="alert alert-success">
                   Seu comando foi executado com <strong>SUCESSO!</strong>${param.msgSucessoRetorno}
               </div>
          </div>
          </div>
      </c:if>
      
      <c:if test="${not empty param.msgErro}">
         <div class="row">
            <div class="col-md-12">
               <div class="alert alert-danger">
                   <a href="#" class="close" data-dismiss="alert">&times;</a>
                   Seu comando foi executado com <strong>ERRO!</strong>${param.msgErroRetorno}
               </div>
            </div>
         </div>
      </c:if>  
      <div class="row">
         <div class="col-md-12">
      
            <div class="panel panel-default">            
                <div class="panel-heading">
                  <strong>Dias disponíveis </strong>
                </div>          
               <table class="table table-striped table-hover" style="font-size:12px;">         
                  <thead>
                     <tr>
                        <th width="15%">Data</th>
                        <th width="15%">Profissional </th>
                     </tr>
                  </thead>                
                  <tbody>
                     <c:forEach items="${pagedListHolder.pageList}" var="agenda">
	                     <tr>	                     
	                        <fmt:formatDate var='valueDate' type='date' pattern='dd.MM.yyyy' value='${agenda.data}'/>
	                        <td>${valueDate}</td>	                        
	                        <td>${agenda.nomeProf}</td>                       
	                        <td><a style="float: right;" href="<c:url value="/publico/ecoa/agendaMassagem?idAgenda=${agenda.idAgenda}"/>" class="btn btn-sm btn-warning"><span class="glyphicon glyphicon-plus-sign"></span> Abrir</a></td>            
	                     </tr>
                     </c:forEach>                   
                  </tbody>                  
               </table>               
            </div>          
         </div>
      </div>
      <div class="row">
         <div class="col-md-12">    
            <div id="paginacao" class="span12" style="float: right;">
               <%-- // load our paging tag, pass pagedListHolder and the link --%>
               <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"/>
            </div>
         </div>
      </div>
</div>


<c:import url="/WEB-INF/pages/publico/templateRodape.jsp" />

</body>
</html>