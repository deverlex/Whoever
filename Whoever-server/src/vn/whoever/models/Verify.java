package vn.whoever.models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Verify {
	
	private boolean result;
	
	public Verify(boolean result) {
		this.result = result;
	}

	public boolean isResult() {
		return result;
	}

	@XmlElement
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
