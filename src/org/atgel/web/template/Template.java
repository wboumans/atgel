package org.atgel.web.template;

import java.util.HashMap;
import java.util.Map;
 
public class Template {
	
	private Map<String, String> template = new HashMap<String, String>();
	
	public Template() {
		template.put("hello", "Hello ${profile.id}");
	}

	public String get(String name) {
		System.out.println();
		return template.get(name);
	}
}
