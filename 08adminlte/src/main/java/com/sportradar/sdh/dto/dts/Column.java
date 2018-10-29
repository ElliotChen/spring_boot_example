package com.sportradar.sdh.dto.dts;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Column {
	@NotBlank
	private String data;
	private String name;
	@NotNull
	private Boolean searchable;
	@NotNull
	private Boolean orderable;
	@NotNull
	private Search search;

	public void setSearchValue(String searchValue) {
		this.search.setValue(searchValue);
	}

	public String getData() {
		return this.data;
	}

	public String getName() {
		return this.name;
	}

	public Boolean getSearchable() {
		return this.searchable;
	}

	public Boolean getOrderable() {
		return this.orderable;
	}

	public Search getSearch() {
		return this.search;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSearchable(Boolean searchable) {
		this.searchable = searchable;
	}

	public void setOrderable(Boolean orderable) {
		this.orderable = orderable;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Column)) {
			return false;
		} else {
			Column other = (Column)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				label71: {
					Object this$data = this.getData();
					Object other$data = other.getData();
					if (this$data == null) {
						if (other$data == null) {
							break label71;
						}
					} else if (this$data.equals(other$data)) {
						break label71;
					}

					return false;
				}

				Object this$name = this.getName();
				Object other$name = other.getName();
				if (this$name == null) {
					if (other$name != null) {
						return false;
					}
				} else if (!this$name.equals(other$name)) {
					return false;
				}

				label57: {
					Object this$searchable = this.getSearchable();
					Object other$searchable = other.getSearchable();
					if (this$searchable == null) {
						if (other$searchable == null) {
							break label57;
						}
					} else if (this$searchable.equals(other$searchable)) {
						break label57;
					}

					return false;
				}

				Object this$orderable = this.getOrderable();
				Object other$orderable = other.getOrderable();
				if (this$orderable == null) {
					if (other$orderable != null) {
						return false;
					}
				} else if (!this$orderable.equals(other$orderable)) {
					return false;
				}

				Object this$search = this.getSearch();
				Object other$search = other.getSearch();
				if (this$search == null) {
					if (other$search == null) {
						return true;
					}
				} else if (this$search.equals(other$search)) {
					return true;
				}

				return false;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof Column;
	}


	public String toString() {
		return "Column(data=" + this.getData() + ", name=" + this.getName() + ", searchable=" + this.getSearchable() + ", orderable=" + this.getOrderable() + ", search=" + this.getSearch() + ")";
	}

	public Column() {
	}

	public Column(String data, String name, Boolean searchable, Boolean orderable, Search search) {
		this.data = data;
		this.name = name;
		this.searchable = searchable;
		this.orderable = orderable;
		this.search = search;
	}
}
