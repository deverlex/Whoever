package vn.whoever.support.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@XmlRootElement(name = "returnPost")
@XmlType(propOrder = { "result" })
@JsonPropertyOrder(value = { "result" })
public class ReturnPost implements Serializable {

	private static final long serialVersionUID = 165453L;

	@XmlElement(name = "result")
	private Integer result;

	public ReturnPost(Integer result) {
		super();
		this.result = result;
	}

	public ReturnPost() {
		super();
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
}
