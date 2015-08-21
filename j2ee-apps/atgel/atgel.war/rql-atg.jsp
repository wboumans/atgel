<%@ taglib uri='/WEB-INF/tld/nucleus.tld' prefix="nucleus"%>
<%@ taglib uri="/WEB-INF/tld/dspjspELTaglib1_0.tld" prefix="dspel"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<dspel:page>
	<%
		long start = System.currentTimeMillis();
	%>
	<c:forEach var="i" begin="1" end="1">

		<dspel:droplet name="/atg/dynamo/droplet/RQLQueryForEach">
			<dspel:param name="queryRQL" value="ALL" />
			<dspel:param name="repository" value="/atg/commerce/catalog/ProductCatalog" />
			<dspel:param name="itemDescriptor" value="product" />
			<dspel:oparam name="output">
				<p/>
				    <dspel:tomap param="element" var="product" recursive="true" />
					 ${product.description}
			</dspel:oparam>
		</dspel:droplet>
	</c:forEach>
	<%
		long total = System.currentTimeMillis() - start;
			out.println(total + "ms");
			System.err.println(total + "ms");
	%>
</dspel:page>
