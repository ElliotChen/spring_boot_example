package com.sportradar.sdh.dto.dts;

import javax.validation.constraints.NotNull;

public class Search {
	@NotNull
	private String value;
	@NotNull
	private Boolean regex;

	public String getValue() {
		return this.value;
	}

	public Boolean getRegex() {
		return this.regex;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setRegex(Boolean regex) {
		this.regex = regex;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Search)) {
			return false;
		} else {
			Search other = (Search)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$value = this.getValue();
				Object other$value = other.getValue();
				if (this$value == null) {
					if (other$value != null) {
						return false;
					}
				} else if (!this$value.equals(other$value)) {
					return false;
				}

				Object this$regex = this.getRegex();
				Object other$regex = other.getRegex();
				if (this$regex == null) {
					if (other$regex != null) {
						return false;
					}
				} else if (!this$regex.equals(other$regex)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof Search;
	}

	public String toString() {
		return "Search(value=" + this.getValue() + ", regex=" + this.getRegex() + ")";
	}

	public Search() {
	}

	public Search(String value, Boolean regex) {
		this.value = value;
		this.regex = regex;
	}
}
