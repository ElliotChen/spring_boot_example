package com.sportradar.sdh.dto.dts;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Order {
	@NotNull
	@Min(0L)
	private Integer column;
	@NotNull
	@Pattern(
			regexp = "(desc|asc)"
	)
	private String dir;

	public Integer getColumn() {
		return this.column;
	}

	public String getDir() {
		return this.dir;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof Order)) {
			return false;
		} else {
			Order other = (Order)o;
			if (!other.canEqual(this)) {
				return false;
			} else {
				Object this$column = this.getColumn();
				Object other$column = other.getColumn();
				if (this$column == null) {
					if (other$column != null) {
						return false;
					}
				} else if (!this$column.equals(other$column)) {
					return false;
				}

				Object this$dir = this.getDir();
				Object other$dir = other.getDir();
				if (this$dir == null) {
					if (other$dir != null) {
						return false;
					}
				} else if (!this$dir.equals(other$dir)) {
					return false;
				}

				return true;
			}
		}
	}

	protected boolean canEqual(Object other) {
		return other instanceof Order;
	}

	public String toString() {
		return "Order(column=" + this.getColumn() + ", dir=" + this.getDir() + ")";
	}

	public Order() {
	}

	public Order(Integer column, String dir) {
		this.column = column;
		this.dir = dir;
	}
}
