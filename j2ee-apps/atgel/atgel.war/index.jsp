<%@ taglib uri='/WEB-INF/tld/nucleus.tld' prefix="nucleus"%>
<%@ taglib uri="/WEB-INF/tld/dspjspELTaglib1_0.tld" prefix="dspel"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<dspel:page>
  profile.id=${profile.id} <br/>
  nucleus:find=${nucleus:findProfile(profile.id)}<br/>
  nucleus:find=${nucleus:findProfile('se-570045').firstName}<br/>
  nucleus:template=${nucleus:template('hello')}<br/>
</dspel:page> 
