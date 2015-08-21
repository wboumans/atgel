<%@ taglib uri='/WEB-INF/tld/nucleus.tld' prefix="nucleus"%>
<%@ taglib uri="/WEB-INF/tld/dspjspELTaglib1_0.tld" prefix="dspel"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<dspel:page>
	<%
		long start = System.currentTimeMillis();
	%>
	<c:forEach var="i" begin="1" end="10000">
		<dspel:droplet name="/atg/dynamo/droplet/ItemLookupDroplet">
			<dspel:param name="id" value="se-570045" />
			<dspel:param name="itemDescriptor" value="user" />
			<dspel:param name="repository" bean="/atg/userprofiling/ProfileAdapterRepository" />

			<dspel:oparam name="output">
				<dspel:valueof param="element.firstName" />
				<dspel:tomap param="element" var="userProfile" recursive="true" />
				 ${userProfile.firstName}
			</dspel:oparam>
			<dspel:oparam name="empty">
				empty
			</dspel:oparam>
			<dspel:oparam name="error">
				error
			</dspel:oparam>

		</dspel:droplet>
	</c:forEach>
	<%
		long total = System.currentTimeMillis() - start;
		out.println(total + "ms");
		System.err.println(total + "ms");
	%>
</dspel:page>
