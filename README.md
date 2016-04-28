# atgel
ATG Oracle Commerce Expression Language Support
You can download pre-build modules from the [Releases page](https://github.com/sparkred-community/atgel/releases "ATGEL Releases").

## Standard ATG code
```
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
```
## ATG code using ATGEL
```
<c:forEach var="i" begin="1" end="10000">
  ${nucleus:findProfile('se-570045').firstName}
</c:forEach>
```

These example are in the test app, its simpler and much faster approximately **x600** times as fast. 
This software was created by Jon Pallas, ATG architect - jon@varelma.com
