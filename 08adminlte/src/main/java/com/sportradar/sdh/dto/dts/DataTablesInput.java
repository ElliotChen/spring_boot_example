package com.sportradar.sdh.dto.dts;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

public class DataTablesInput {
	@NotNull
	@Min(0L)
	private Integer draw = 1;
	@NotNull
	@Min(0L)
	private Integer start = 0;
	@NotNull
	@Min(-1L)
	private Integer length = 10;
	@NotNull
	private Search search = new Search();
	@NotEmpty
	private List<Order> order = new ArrayList();
	@NotEmpty
	private List<Column> columns = new ArrayList();

	public Map<String, Column> getColumnsAsMap() {
		Map<String, Column> map = new HashMap();
		Iterator var2 = this.columns.iterator();

		while(var2.hasNext()) {
			Column column = (Column)var2.next();
			map.put(column.getData(), column);
		}

		return map;
	}

	public Column getColumn(String columnName) {
		if (columnName == null) {
			return null;
		} else {
			Iterator var2 = this.columns.iterator();

			Column column;
			do {
				if (!var2.hasNext()) {
					return null;
				}

				column = (Column)var2.next();
			} while(!columnName.equals(column.getData()));

			return column;
		}
	}

	public void addColumn(String columnName, boolean searchable, boolean orderable, String searchValue) {
		this.columns.add(new Column(columnName, "", searchable, orderable, new Search(searchValue, false)));
	}

	public void addOrder(String columnName, boolean ascending) {
		if (columnName != null) {
			for(int i = 0; i < this.columns.size(); ++i) {
				if (columnName.equals(((Column)this.columns.get(i)).getData())) {
					this.order.add(new Order(i, ascending ? "asc" : "desc"));
				}
			}

		}
	}

	public DataTablesInput() {
	}

	public Integer getDraw() {
		return this.draw;
	}

	public Integer getStart() {
		return this.start;
	}

	public Integer getLength() {
		return this.length;
	}

	public Search getSearch() {
		return this.search;
	}

	public List<Order> getOrder() {
		return this.order;
	}

	public List<Column> getColumns() {
		return this.columns;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof DataTablesInput)) {
			return false;
		} else {
			DataTablesInput other = (DataTablesInput)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$draw = this.getDraw();
				Object other$draw = other.getDraw();
				if (this$draw == null) {
					if (other$draw != null) {
						return false;
					}
				} else if (!this$draw.equals(other$draw)) {
					return false;
				}

				Object this$start = this.getStart();
				Object other$start = other.getStart();
				if (this$start == null) {
					if (other$start != null) {
						return false;
					}
				} else if (!this$start.equals(other$start)) {
					return false;
				}

				Object this$length = this.getLength();
				Object other$length = other.getLength();
				if (this$length == null) {
					if (other$length != null) {
						return false;
					}
				} else if (!this$length.equals(other$length)) {
					return false;
				}

				label62: {
					Object this$search = this.getSearch();
					Object other$search = other.getSearch();
					if (this$search == null) {
						if (other$search == null) {
							break label62;
						}
					} else if (this$search.equals(other$search)) {
						break label62;
					}

					return false;
				}

				label55: {
					Object this$order = this.getOrder();
					Object other$order = other.getOrder();
					if (this$order == null) {
						if (other$order == null) {
							break label55;
						}
					} else if (this$order.equals(other$order)) {
						break label55;
					}

					return false;
				}

				Object this$columns = this.getColumns();
				Object other$columns = other.getColumns();
				if (this$columns == null) {
					if (other$columns != null) {
						return false;
					}
				} else if (!this$columns.equals(other$columns)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof DataTablesInput;
	}

	public String toString() {
		return "DataTablesInput(draw=" + this.getDraw() + ", start=" + this.getStart() + ", length=" + this.getLength() + ", search=" + this.getSearch() + ", order=" + this.getOrder() + ", columns=" + this.getColumns() + ")";
	}
}
