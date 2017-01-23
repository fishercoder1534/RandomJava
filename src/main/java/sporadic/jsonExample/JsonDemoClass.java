package sporadic.jsonExample;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.ToString;

/**This package is to demo how @JsonIgnore and @JsonIgnorProperties and @JsonProperty work:
 * @JsonIgnore is to annotate a field or method to make it free from being seriazlied by json
 * @JsonIgnoreProperties is to annotate a class, and you must/may specifiy which properties you want json to skip serializing.
 * @JsonProperty is to annotate a field and give this field a specific name when Jackson does serialization, e.g. a name.*/


@NoArgsConstructor
@Service
@ToString
@JsonIgnoreProperties({"noInterestingMember", "forgetThisField"})
public class JsonDemoClass {
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoInterestingMember() {
		return noInterestingMember;
	}

	public void setNoInterestingMember(String noInterestingMember) {
		this.noInterestingMember = noInterestingMember;
	}

	public int getAnotherMember() {
		return anotherMember;
	}

	public void setAnotherMember(int anotherMember) {
		this.anotherMember = anotherMember;
	}

	public double getForgetThisField() {
		return forgetThisField;
	}

	public void setForgetThisField(double forgetThisField) {
		this.forgetThisField = forgetThisField;
	}

	@JsonProperty("ID")
	public long id;

	@JsonProperty("NAME")
	public String name;

	public String noInterestingMember;

	@JsonIgnore
	@JsonProperty("ANOTHER-MEMEBR")
	public int anotherMember;

	public double forgetThisField;
}