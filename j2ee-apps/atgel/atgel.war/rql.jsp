<%@ taglib uri='/WEB-INF/tld/nucleus.tld' prefix="nucleus"%>
<%@ taglib uri="/WEB-INF/tld/dspjspELTaglib1_0.tld" prefix="dspel"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<dspel:page>
	<%
		long start = System.currentTimeMillis();
	%>
     <c:forEach var="product" items="${nucleus:rql('/atg/commerce/catalog/ProductCatalog','product','ALL')}">
        <p>
       ${product.description}
      </c:forEach>
     
	<%
		long total = System.currentTimeMillis() - start;
			out.println(total + "ms");
			System.err.println(total + "ms");
	%>
</dspel:page>
