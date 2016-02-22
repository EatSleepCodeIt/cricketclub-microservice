package com.ratedforum.user.api.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlType;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;

/**
 * Custom RP Value object for links.
 * 
 */
@XmlType(name = "link", namespace = Link.ATOM_NAMESPACE)
public class ExtendedLink extends Link implements Serializable {

	private static final long serialVersionUID = 4580040790923646222L;
	
	private String title = "";
	private String name;
	private String type;
	private String description;
	private String[] methods;

	protected ExtendedLink() {

	}
	
	public static ExtendedLink extend(Link link) {
		return new ExtendedLink(link.getHref(), link.getRel());
	}
	
	/**
	 * Creates a new link to the given URI with the self rel.
	 * 
	 * @see #REL_SELF
	 * @param href must not be {@literal null} or empty.
	 */
	public ExtendedLink(String href) {
		super(href, REL_SELF);
	}

	/**
	 * Creates a new {@link ExtendedLink} to the given URI with the given rel.
	 * 
	 * @param href must not be {@literal null} or empty.
	 * @param rel must not be {@literal null} or empty.
	 */
	public ExtendedLink(String href, String rel) {
		super(new UriTemplate(href), rel);
	}
	
	/**
	 * Returns a {@link ExtendedLink} pointing to the same URI but with the given relation.
	 * 
	 * @param rel must not be {@literal null} or empty.
	 * @return
	 */
	public ExtendedLink withRel(String rel) {
		return new ExtendedLink(getHref(), rel);
	}
	
	public ExtendedLink withMethods(String... methods) {
		this.methods = methods;
		return this;
	}
	
	public ExtendedLink withDescription(String description) {
		this.description = description;
		return this;
	}
	
	public ExtendedLink withName(String name) {
		this.name = name;
		return this;
	}
	
	public ExtendedLink withType(String type) {
		this.type = type;
		return this;
	}
	
	public ExtendedLink withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public String[] getMethods() {
		return methods;
	}
}
